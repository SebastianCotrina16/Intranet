from django.db import models

# Create your models here.
class TbProfesor(models.Model):
    CodigoProfesor = models.AutoField(primary_key=True)
    NombreProfesor= models.CharField(max_length=50)
    ApellidoProfesor= models.CharField(max_length=50)
    EmailProfesor= models.CharField(max_length=50)
    NumeroProfesor= models.PositiveIntegerField()
    DireccionProfesor= models.CharField(max_length=50)
    class Meta:
        db_table = 'TbProfesor'