from django.urls import path, include
from rest_framework import routers
from .views import CreditosViewSet


router = routers.DefaultRouter()
router.register('TbCreditos', CreditosViewSet)

urlpatterns = [
    path('', include(router.urls)),
]