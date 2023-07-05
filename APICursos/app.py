from flask import Flask
from flask_graphql import GraphQLView
from schema import schema

# Configuración de la aplicación Flask
app = Flask(__name__)

# Configuración de la ruta GraphQL y el esquema
app.add_url_rule('/',view_func=GraphQLView.as_view('graphql', schema=schema,graphiql=True))

# Punto de entrada para ejecutar la aplicación Flask
if __name__ == '__main__':
    app.run(debug=False)
