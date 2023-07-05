from django.db import models
from APICursos.models import TbCursos
from APICreditos.models import TbCreditos

# Create your models here.
class TbDetalleCredito(models.Model):
    CodigoDetalleCredito=models.AutoField(primary_key=True)
    Cantidad = models.PositiveIntegerField()
    CodigoCredito = models.ForeignKey(TbCreditos, on_delete=models.CASCADE)
    CodigoCurso = models.ForeignKey(TbCursos, on_delete=models.CASCADE)
    class Meta:
        db_table = 'TbDetalleCredito'