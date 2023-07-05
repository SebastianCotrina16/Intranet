from django.db import models

# Create your models here.
class TbCreditos(models.Model):
    CodCredito = models.AutoField(primary_key=True)
    TipCredito= models.CharField(max_length=50)
    class Meta:
        db_table = 'TbCreditos'