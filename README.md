# Sistema de Gestión de Transporte y Logística

# Trabajo Practico Integrador Backend
# Grupo 64

# Estructuracion Proyecto (Posible puede cambiar)

com/tup/tpfinal/
|
|--- TpFinalApplication.java    (La clase principal que inicia toda la aplicación)
|
|--- controller/            (Controladores, API REST. Reciben peticiones HTTP)
|
|--- service/               (Servicios. Aquí reside la lógica de negocio)
|
|--- model/                 (Modelos/Entidades. Representan las tablas de la BD)
|
|--- repository/            (Repositorios. Interfaces que consultan la BD)
|
\--- dto/                   (Data Transfer Object. Para enviar y recibir datos)

Este proyecto es el backend para una aplicación de gestión de transporte, desarrollado en **Java** con **Spring Boot**. Permite a los clientes crear solicitudes de envío y a los administradores y operadores gestionar todo el ciclo de vida de la logística, incluyendo la asignación de rutas, camiones y el seguimiento de los tramos. También incluye un módulo completo para la administración de clientes (CRUD).

La autenticación y autorización se manejan a través de **Keycloak**.

## Características Principales

* **Gestión de Clientes**: CRUD completo (Crear, Leer, Actualizar, Eliminar) para los clientes del sistema.
* **Gestión de Solicitudes**: Creación y seguimiento detallado del estado de las solicitudes de transporte.
* **Gestión de Operaciones**: Asignación de rutas y camiones a las solicitudes.
* **Seguimiento de Tramos**: Registro del inicio y fin de cada tramo por parte de los transportistas.
* **Control de Roles**: Endpoints protegidos por rol (Cliente, Operador, Administrador, Transportista) usando Keycloak.

## Tecnologías Utilizadas

* **Lenguaje**: `Java (JDK 17+)`
* **Framework**: `Spring Boot 3.x`
* **Base de Datos**: `PostgreSQL`
* **Gestor de Paquetes**: `Maven`
* **Autenticación y Autorización**: `Keycloak` (Integrado con Spring Security)

## Instalación y Puesta en Marcha

Sigue estos pasos para levantar el proyecto en tu entorno local.

**1. Prerrequisitos:**

Asegúrate de tener instalado y corriendo:
* `Java Development Kit (JDK) 17` o superior.
* `Apache Maven 3.8` o superior.
* `PostgreSQL` (una base de datos creada para el proyecto).
* `Keycloak` (una instancia corriendo con un *realm* y los *roles* configurados).

**2. Clonar el Repositorio:**

```bash
git clone [https://github.com/](https://github.com/)[TU_USUARIO]/[NOMBRE-DEL-PROYECTO].git
cd [NOMBRE-DEL-PROYECTO]