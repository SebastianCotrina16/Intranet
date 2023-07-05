from pymongo import MongoClient

# Configuración de la conexión a MongoDB
client = MongoClient('mongodb+srv://admin:admin@apinotas.vhdopja.mongodb.net/')
db = client['APINotas']

collectionGrades = db['grades']
