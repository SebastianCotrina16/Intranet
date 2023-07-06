from typesgraphs import Grade
import graphene
from db import collectionGrades
from bson import ObjectId
from rabitmqconnect import rabbitmqMessage
# Definición de la consulta "Query" en GraphQL

def resolve_allGrades(self, info):
    # Obtener todos los registros de la base de datos
    grades = collectionGrades.find()
    grades_message=list(grades)
    rabbitmqMessage(f"Total grades found: {len(grades_message)}", "all_grades")
    return [Grade(
        id=str(grade['_id']),  # Agregar el campo 'id' con el valor del _id de MongoDB
        codigoMatricula=grade['codigoMatricula'],
        codigoAlumno=grade['codigoAlumno'],
        nombresAlumno=grade['nombresAlumno'],
        apellidosAlumno=grade['apellidosAlumno'],
        curso=grade['curso']
    ) for grade in grades_message]

def resolve_gradeById(self, info, id):
    if id:
        # Buscar un registro por su ID de la base de datos
        grade = collectionGrades.find_one({"_id": ObjectId(id)})
    else:
        # Si no se proporciona el ID, retornar None
        return None

    rabbitmqMessage(f"Resolve_GradeById: {id}", "grade_by_id")
    
    if grade:
        return Grade(
            codigoMatricula=grade['codigoMatricula'],
            codigoAlumno=grade['codigoAlumno'],
            nombresAlumno=grade['nombresAlumno'],
            apellidosAlumno=grade['apellidosAlumno'],
            curso=grade['curso']
        )
    else:
        return None

def resolve_gradeByCodigoMatricula(self, info, codigoMatricula):
    if codigoMatricula:
        # Buscar registros por su código de matrícula en la base de datos
        grades = collectionGrades.find({"codigoMatricula": codigoMatricula})
        grade_list = [Grade(
            codigoMatricula=grade['codigoMatricula'],
            codigoAlumno=grade['codigoAlumno'],
            nombresAlumno=grade['nombresAlumno'],
            apellidosAlumno=grade['apellidosAlumno'],
            curso=grade['curso']
        ) for grade in grades]
        print(grade_list)
        rabbitmqMessage(f"Resolve_GradeByCodigoMatricula: {codigoMatricula}", "grade_by_codigo_matricula")

        return grade_list
    else:
        # Si no se proporciona el código de matrícula, retornar None
        return None
