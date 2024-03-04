## Tabla de Contenido
1. [Informacion General](#general-info)
2. [Tecnologias](#tecnologias)
3. [Instalacion](#instalacion)
4. [Pruebas](#Pruebas)

### General Info
***
Proyecto de prueba API REST Creacion de usuarios en java springboot, con base de datos en memoria (H2) 

## Tecnologias
***
Listado de tecnologias usadas en el proyecto:
* Apache Maven: Version 3.9.6 
* Java: Version 17
* Spring Boot: Version 3.2.3
  
## Instalacion
***
```
$ git clone [https://github.com/eddcecc/prueba_rest]
$ cd ../path/to/the/file
$ mvn spring-boot:run
```

## Pruebas
***
Una vez ejecutado el proyecto, se envia el siguiente mensaje de prueba mediante Postman:
> POST
url: http://localhost:8080/api/users
```
{
    "name": "Juan Rodriguez",
    "email": "Juan@rodriguez.org",
    "password": "Cred020202$",
    "phones": [
        {
            "number": "7777665",
            "citycode": "1",
            "countrycode": "57"
        },
        {
            "number": "4444343",
            "citycode": "2",
            "countrycode": "57"
        }
    ]
}
```
> GET
url: http://localhost:8080/api/users

