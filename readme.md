# Evaluación técnica para Vault
## Requisitos
- Java 1.8
- Maven 3 o superior

## Tests
Luego de clonar el proyecto, se pueden lanzar pruebas de los servicios para corroborar el correcto funcionamiento:
```bash
mvn test
```
## Configuración
Para una rápida ejecución sin configuración, ejecutar el siguiente comando:

```bash
mvn spring-boot:run -Dspring.config.location=classpath:quick-app.properties
```

Dicho comando inicializará el servicio web con una DB en memoria (H2) con datos por defecto.

Si se desea configurar una base de datos MySQL, se puede acceder al archivo application.properties en classpath/resources/application.properties
y completar los campos segun corresponda con los datos de su instancia.

Si se desea que se inserten de todas formas los datos dummy, mantener la propiedad:
spring.datasource.initialization-mode=always

En caso que se desee utilizar una DB ya creada con datos, cambiar el valor de la propiedad por *never*

Para ejecutar el servicio con ésta configuración
```bash
mvn spring-boot:run
```

Por defecto se ejecutará la aplicación en un Tomcat embebido en el puerto 8080.

## API

### Obtener empleado sin relaciones
1. Método: GET
2. Ruta: /employee/{id}
3. Modelo respuesta:
```javascript
{
"firstName": "Jean",
"lastName": "Leduc",
"email": "jean.leduc@vault.com",
"phoneNumber": "1132445212",
"hireDate": "2015-02-14T03:00:00.000+0000",
"salary": 550,
"commissionPercentage": 0.15
}
```

### Insertar empleado sin relaciones
1. Método: POST
2. Ruta: /employee
3. En el header de la respuesta 201 se podrá ver la ruta para consultar el empleado creado
4. Modelo del request body como la respuesta del método GET

### Modificar empleado sin relaciones
1. Método: PUT
2. Ruta: /employee/{ID}
3. Modelo del request body como la respuesta del método GET

### Eliminar empleado sin relaciones
1. Método: DELETE
2. Ruta: /employee/{ID}

### Obtener empleados con relaciones y filtros
1. Método: GET
2. Ruta: /employee
3. Parámetros opcionales: 
* Filtros: jobId, managerId, lastName
* Paginación: startAt, maxResults
4. Modelo respuesta:
```javascript
{
	"id": 1,
	"firstName": "Señor",
	"lastName": "Manager",
	"email": "elmanager@vault.com",
	"phoneNumber": "1122671112",
	"hireDate": "2010-06-01T03:00:00.000+0000",
	"job": {...},
	"salary": 800,
	"commissionPercentage": 0,
	"manager": null,
	"department": {
		"id": 1,
		"name": "Headquarters ARG",
		"manager": {...},
		"location": {...}
	},
	"jobsHistory": [],
}
```

### Insertar departamento
1. Método: POST
2. Ruta: /department
3. Modelo request body a enviar:
```javascript
{
	"name": "department",
	"managerId": 1,
	"locationId": 2"
}
```