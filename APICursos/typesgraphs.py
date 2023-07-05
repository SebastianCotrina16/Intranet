import graphene


class Curso(graphene.ObjectType):
    codigoCurso = graphene.String()
    nombreCurso = graphene.String()
    thCurso = graphene.String()
    creditos = graphene.String()
    preRequisitoCurso = graphene.String()
    idProfesor = graphene.String()
    nota = graphene.String()

class CursoInput(graphene.InputObjectType):
    codigoCurso = graphene.String()
    nombreCurso = graphene.String()
    thCurso = graphene.String()
    creditos = graphene.String()
    preRequisitoCurso = graphene.String()
    idProfesor = graphene.String()
    nota = graphene.String()
    
    def to_dict(self):
        return {
            "codigoCurso": self.codigoCurso,
            "nombreCurso": self.nombreCurso,
            "thCurso": self.thCurso,
            "creditos": self.creditos,
            "preRequisitoCurso": self.preRequisitoCurso,
            "idProfesor": self.idProfesor,
            "nota": self.nota,
        }


class Matricula(graphene.ObjectType):
    codigoMatricula = graphene.String()
    codigoAlumno = graphene.String()
    nombresAlumno = graphene.String()
    apellidosAlumno = graphene.String()
    curso = graphene.List(Curso)

class Grade(graphene.ObjectType):
    id = graphene.String()
    codigoMatricula = graphene.String()
    codigoAlumno = graphene.String()
    nombresAlumno = graphene.String()
    apellidosAlumno = graphene.String()
    curso = graphene.List(Curso)
    
class GradeInput(graphene.InputObjectType):
    codigoMatricula = graphene.String()
    codigoAlumno = graphene.String()
    nombresAlumno = graphene.String()
    apellidosAlumno = graphene.String()
