from django.db import models
from APIProfesor.models import TbProfesor

class TbEstado(models.Model):
    IdEstado=models.AutoField(primary_key=True)
    Estado = models.CharField(max_length=255)
    class Meta:
        db_table = 'TbEstado'

class TbCursos(models.Model):
    CodigoCurso = models.CharField(max_length=255,primary_key=True)
    NombreCurso= models.CharField(max_length=255)
    THCurso= models.PositiveIntegerField()
    PreRequisitoCurso= models.CharField(max_length=255)
    CicloCurso= models.CharField(max_length=255)
    CodigoProfesor= models.ForeignKey(TbProfesor, on_delete=models.CASCADE)
    FkEstado= models.ForeignKey(TbEstado, on_delete=models.CASCADE)
    class Meta:
        db_table = 'TbCursos'