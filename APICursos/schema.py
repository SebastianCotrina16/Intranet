import graphene
from mutations import CreateGrade,UpdateGrade,DeleteGrade,UpdateCursoInGrade
from queries import resolve_allGrades,resolve_gradeByCodigoMatricula, resolve_gradeById
from typesgraphs import Grade

class Mutation(graphene.ObjectType):
    create_grade = CreateGrade.Field()
    update_grade = UpdateGrade.Field()
    update_cursoingrade = UpdateCursoInGrade.Field()
    delete_grade = DeleteGrade.Field()

class Query(graphene.ObjectType):
    allGrades = graphene.List(Grade)
    gradeById = graphene.Field(Grade, id=graphene.String())
    gradeByCodigoMatricula = graphene.List(Grade, codigoMatricula=graphene.String())
    
    def resolve_allGrades(self, info):
        return resolve_allGrades(self, info)

    def resolve_gradeById(self, info, id=None):
        return resolve_gradeById(self, info, id)

    def resolve_gradeByCodigoMatricula(self, info, codigoMatricula=None):
        return resolve_gradeByCodigoMatricula(self, info, codigoMatricula)


# Definición del esquema de GraphQL
schema = graphene.Schema(query=Query, mutation=Mutation)
