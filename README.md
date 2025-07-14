# AcademySystem-BackEnd

## Descripción General

**AcademySystem** es un proyecto basado en Spring Boot, diseñado para gestionar sistemas académicos. Utiliza Java 21 y sigue la estructura estándar de un proyecto Maven, lo que facilita la integración, pruebas y despliegue.

## Dependencias Principales

El proyecto utiliza una serie de dependencias clave para proporcionar funcionalidades web, seguridad, persistencia, pruebas y utilidades adicionales.

| Dependencia                        | Propósito principal                               | Alcance    |
|------------------------------------|---------------------------------------------------|------------|
| spring-boot-starter-web            | API REST y controladores web                      | Principal  |
| spring-boot-starter-validation     | Validaciones de datos                             | Principal  |
| spring-boot-starter-data-jpa       | Persistencia con JPA y acceso a bases de datos    | Principal  |
| spring-boot-devtools               | Recarga automática en desarrollo                  | Runtime    |
| mysql-connector-j                  | Conector para base de datos MySQL                 | Runtime    |
| spring-boot-starter-test           | Pruebas unitarias e integración                   | Test       |
| spring-security-config             | Configuración de seguridad                        | Principal  |
| spring-security-test               | Pruebas de seguridad                              | Test       |
| io.jsonwebtoken (jjwt)             | Manejo de JWT para autenticación                  | Principal  |
| mapstruct                          | Mapeo de objetos DTO/Entidad                      | Principal  |
| azure-storage-blob                 | Integración con almacenamiento Azure Blob         | Principal  |
| mockito-core                       | Mocking para pruebas                              | Test       |
| junit-jupiter                      | Framework de pruebas                              | Test       |

## Plugins de Build

- **spring-boot-maven-plugin:** Facilita el empaquetado y despliegue de la aplicación Spring Boot.
- **maven-surefire-plugin:** Ejecuta pruebas unitarias y de integración durante el build.

## Requisitos Técnicos

- **Java:** 21
- **Gestor de dependencias:** Maven
- **Base de datos recomendada:** MySQL (por el conector incluido)
- **Almacenamiento opcional:** Azure Blob Storage (para archivos o recursos externos)
- **Seguridad:** Spring Security con soporte para JWT

## Funcionalidades Esperadas

- Exposición de servicios RESTful para la gestión académica.
- Validación de datos de entrada.
- Persistencia de datos en MySQL utilizando JPA.
- Seguridad basada en roles y autenticación JWT.
- Mapeo eficiente de entidades y DTOs con MapStruct.
- Pruebas unitarias y de integración con JUnit y Mockito.
- Posibilidad de almacenar archivos en Azure Blob Storage.
