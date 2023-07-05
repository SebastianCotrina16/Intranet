from typesgraphs import Curso, Matricula, Grade, CursoInput, GradeInput
import graphene
from db import collectionGrades
from bson import ObjectId
from graphene import List
from rabitmqconnect import rabbitmqMessage


# Definición de la mutación "Mutation" en GraphQL
class CreateGrade(graphene.Mutation):
    class Arguments:
        codigoMatricula = graphene.String(required=True)
        codigoAlumno = graphene.String(required=True)
        nombresAlumno = graphene.String(required=True)
        apellidosAlumno = graphene.String(required=True)
        curso = List(CursoInput, required=True)

    grade = graphene.Field(Grade)

    def mutate(
        self, info, codigoMatricula, codigoAlumno, nombresAlumno, apellidosAlumno, curso
    ):
        
        cursos = []
        for curso_input in curso:
            curso_obj = Curso(
                codigoCurso=curso_input.codigoCurso,
                nombreCurso=curso_input.nombreCurso,
                thCurso=curso_input.thCurso,
                creditos=curso_input.creditos,
                preRequisitoCurso=curso_input.preRequisitoCurso,
                idProfesor=curso_input.idProfesor,
                nota=curso_input.nota,
            )
            cursos.append(curso_obj)

        matricula = Matricula(
            codigoMatricula=codigoMatricula,
            codigoAlumno=codigoAlumno,
            nombresAlumno=nombresAlumno,
            apellidosAlumno=apellidosAlumno,
            curso=cursos,
        )

        grade_document = {
            "codigoMatricula": matricula.codigoMatricula,
            "codigoAlumno": matricula.codigoAlumno,
            "nombresAlumno": matricula.nombresAlumno,
            "apellidosAlumno": matricula.apellidosAlumno,
            "curso": [
                {
                    "codigoCurso": curso.codigoCurso,
                    "nombreCurso": curso.nombreCurso,
                    "thCurso": curso.thCurso,
                    "creditos": curso.creditos,
                    "preRequisitoCurso": curso.preRequisitoCurso,
                    "idProfesor": curso.idProfesor,
                    "nota": curso.nota,
                }
                for curso in matricula.curso
            ],
        }

        # Inserta el documento en la colección
        result = collectionGrades.insert_one(grade_document)

        # Asigna el ID insertado al documento
        grade_document["_id"] = str(result.inserted_id)

        # Crea y devuelve el objeto Grade correspondiente
        grade = Grade(
            codigoMatricula=grade_document["codigoMatricula"],
            codigoAlumno=grade_document["codigoAlumno"],
            nombresAlumno=grade_document["nombresAlumno"],
            apellidosAlumno=grade_document["apellidosAlumno"],
            curso=[
                Curso(
                    codigoCurso=curso["codigoCurso"],
                    nombreCurso=curso["nombreCurso"],
                    thCurso=curso["thCurso"],
                    creditos=curso["creditos"],
                    preRequisitoCurso=curso["preRequisitoCurso"],
                    idProfesor=curso["idProfesor"],
                    nota=curso["nota"],
                )
                for curso in grade_document["curso"]
            ],
        )

        rabbitmqMessage(f"New grade created with ID: {grade_document['_id']}",'new_grade')

        return CreateGrade(grade=grade)


class UpdateGrade(graphene.Mutation):
    class Arguments:
        id = graphene.String(required=True)
        codigoMatricula = graphene.String()
        codigoAlumno = graphene.String()
        nombresAlumno = graphene.String()
        apellidosAlumno = graphene.String()
        curso_add = graphene.List(CursoInput)  # Nuevos cursos a agregar
        curso_remove = graphene.List(graphene.String)  # IDs de cursos a eliminar

    grade = graphene.Field(Grade)

    def mutate(self, info, id, curso_add=None, curso_remove=None, **kwargs):
        
        # Convertir el ID de cadena a ObjectId de MongoDB
        obj_id = ObjectId(id)

        # Obtener los campos a actualizar del argumento kwargs
        updated_fields = {
            key: value for key, value in kwargs.items() if value is not None
        }

        # Actualizar el arreglo 'curso' si se proporcionan los argumentos de agregar o eliminar
        if curso_add or curso_remove:
            # Obtener el registro actual de la base de datos
            current_grade = collectionGrades.find_one({"_id": obj_id})

            # Obtener el arreglo 'curso' actual
            current_curso = current_grade.get("curso", [])

            # Agregar nuevos cursos al arreglo 'curso'
            if curso_add:
                for curso in curso_add:
                    current_curso.append(curso)

            # Eliminar cursos del arreglo 'curso'
            if curso_remove:
                current_curso = [
                    curso
                    for curso in current_curso
                    if curso["codigoCurso"] not in curso_remove
                ]

            # Actualizar el campo 'curso' en los campos actualizados
            updated_fields["curso"] = current_curso

        # Actualizar el registro en la base de datos
        collectionGrades.update_one({"_id": obj_id}, {"$set": updated_fields})

        # Obtener el registro actualizado de la base de datos
        updated_grade = collectionGrades.find_one({"_id": obj_id})

        # Crear un objeto Grade a partir del registro actualizado
        grade = Grade(
            id=str(updated_grade["_id"]),
            codigoMatricula=updated_grade["codigoMatricula"],
            codigoAlumno=updated_grade["codigoAlumno"],
            nombresAlumno=updated_grade["nombresAlumno"],
            apellidosAlumno=updated_grade["apellidosAlumno"],
            curso=updated_grade["curso"],
        )
        rabbitmqMessage(f"Grade updated with ID: {id}",'grade_update')


        return UpdateGrade(grade=grade)


class UpdateCursoInGrade(graphene.Mutation):
    grade = graphene.Field(Grade)

    class Arguments:
        id = graphene.String(required=True)
        index = graphene.Int(required=True)
        data = CursoInput(required=True)

    def mutate(self, info, id, index, data):
        # Obtener el grado por su ID
        grade = collectionGrades.find_one({"_id": ObjectId(id)})

        if not grade:
            raise Exception("Grado no encontrado")

        # Verificar si el índice está dentro del rango válido
        if 0 <= index < len(grade["curso"]):
            # Obtener el objeto curso en el índice especificado
            curso = grade["curso"][index]

            # Actualizar los campos proporcionados en el objeto curso
            if data.codigoCurso is not None:
                curso["codigoCurso"] = data.codigoCurso
            if data.nombreCurso is not None:
                curso["nombreCurso"] = data.nombreCurso
            if data.thCurso is not None:
                curso["thCurso"] = data.thCurso
            if data.creditos is not None:
                curso["creditos"] = data.creditos
            if data.preRequisitoCurso is not None:
                curso["preRequisitoCurso"] = data.preRequisitoCurso
            if data.idProfesor is not None:
                curso["idProfesor"] = data.idProfesor
            if data.nota is not None:
                curso["nota"] = data.nota

            # Actualizar el grado en la base de datos
            collectionGrades.update_one({"_id": ObjectId(id)}, {"$set": grade})

            rabbitmqMessage(f"Grade updated with ID: {id}",'grade_update')

            # Devolver el grado actualizado
            return UpdateCursoInGrade(
                grade=Grade(
                    id=str(grade["_id"]),
                    codigoMatricula=grade["codigoMatricula"],
                    codigoAlumno=grade["codigoAlumno"],
                    nombresAlumno=grade["nombresAlumno"],
                    apellidosAlumno=grade["apellidosAlumno"],
                    curso=grade["curso"],
                )
            )
        else:
            raise Exception("Índice de curso inválido")


class DeleteGrade(graphene.Mutation):
    class Arguments:
        id = graphene.String(required=True)

    success = graphene.Boolean()

    def mutate(self, info, id):
        # Elimina el grade de la base de datos utilizando su ID
        result = collectionGrades.delete_one({"_id": ObjectId(id)})

        # Si el grade fue eliminado exitosamente, result.deleted_count será mayor a cero
        success = result.deleted_count > 0

        rabbitmqMessage(f"Grade deleted with ID: {id}",'grade_delete')

        return DeleteGrade(success=success)
