from rest_framework import serializers
from .models import TbCreditos

class CreditosSerializer(serializers.ModelSerializer):
    class Meta:
        model = TbCreditos
        fields = '__all__'
