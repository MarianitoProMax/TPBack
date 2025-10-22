# Sistema de Gestión de Transporte y Logística# Sistema de Gestión de Transporte y Logística



## 📋 Descripción General# Estructuracion Proyecto (Posible puede cambiar)



Este proyecto es un **backend de microservicios** para una aplicación de gestión de transporte y logística, desarrollado en **Java** con **Spring Boot 3.x**. La arquitectura modular permite gestionar de forma independiente diferentes aspectos del sistema:

```

- Gestión de clientes y usuarios com/tup/tpfinal/

- Gestión de flota de vehículos y depósitos|

- Gestión de envíos y rutas|--- TpFinalApplication.java    (La clase principal que inicia toda la aplicación)

- Servicios de geolocalización|

- Cálculo de tarifas y costos|--- controller/            (Controladores, API REST. Reciben peticiones HTTP)

|

La autenticación y autorización se manejan a través de **Keycloak** con Spring Security.|--- service/               (Servicios. Aquí reside la lógica de negocio)

|

---|--- model/                 (Modelos/Entidades. Representan las tablas de la BD)

|

## 📦 Estructura del Proyecto|--- repository/            (Repositorios. Interfaces que consultan la BD)

|

Este es un **monorepo** con los siguientes microservicios:\--- dto/                   (Data Transfer Object. Para enviar y recibir datos)

```

```

TP-BACKEND/Este proyecto es el backend para una aplicación de gestión de transporte, desarrollado en **Java** con **Spring Boot**. Permite a los clientes crear solicitudes de envío y a los administradores y operadores gestionar todo el ciclo de vida de la logística, incluyendo la asignación de rutas, camiones y el seguimiento de los tramos. También incluye un módulo completo para la administración de clientes (CRUD).

│

├── flotaydepositos/          # Gestión de flota de vehículos y depósitosLa autenticación y autorización se manejan a través de **Keycloak**.

├── usuarios/                 # Gestión de usuarios y clientes

├── gestiondeenvios/          # Gestión de solicitudes de envío y rutas## Características Principales

├── geoapi/                   # Servicios de geolocalización y mapeo

├── tarifasycostos/           # Cálculo de tarifas y costos* **Gestión de Clientes**: CRUD completo (Crear, Leer, Actualizar, Eliminar) para los clientes del sistema.

├── gateway/                  # API Gateway (enrutamiento y orquestación)* **Gestión de Solicitudes**: Creación y seguimiento detallado del estado de las solicitudes de transporte.

├── keycloack/                # Configuración de autenticación* **Gestión de Operaciones**: Asignación de rutas y camiones a las solicitudes.

│* **Seguimiento de Tramos**: Registro del inicio y fin de cada tramo por parte de los transportistas.

└── pom.xml                   # POM padre (Maven)* **Control de Roles**: Endpoints protegidos por rol (Cliente, Operador, Administrador, Transportista) usando Keycloak.

```

## Tecnologías Utilizadas

### Estructura Interna de Cada Microservicio

* **Lenguaje**: `Java (JDK 17+)`

```* **Framework**: `Spring Boot 3.x`

microservicio/* **Base de Datos**: `PostgreSQL`

│* **Gestor de Paquetes**: `Maven`

├── src/main/java/utnfrc/isi/backend/* **Autenticación y Autorización**: `Keycloak` (Integrado con Spring Security)

│   ├── controller/           # Controladores REST API

│   ├── service/              # Lógica de negocio## Instalación y Puesta en Marcha

│   ├── repository/           # Acceso a datos (JPA)

│   ├── model/                # Entidades de base de datosSigue estos pasos para levantar el proyecto en tu entorno local.

│   ├── dto/                  # Data Transfer Objects

│   └── Application.java      # Clase principal**1. Prerrequisitos:**

│

├── src/main/resources/Asegúrate de tener instalado y corriendo:

│   └── application.properties # Configuración* `Java Development Kit (JDK) 17` o superior.

│* `Apache Maven 3.8` o superior.

├── src/test/java/            # Tests unitarios* `PostgreSQL` (una base de datos creada para el proyecto).

├── pom.xml                   # Configuración Maven* `Keycloak` (una instancia corriendo con un *realm* y los *roles* configurados).

└── mvnw                      # Maven Wrapper

```**2. Clonar el Repositorio:**



---```bash

git clone [https://github.com/](https://github.com/)[TU_USUARIO]/[NOMBRE-DEL-PROYECTO].git

## 🎯 Características Principalescd [NOMBRE-DEL-PROYECTO]

- ✅ **Gestión de Usuarios y Clientes**: CRUD completo
- ✅ **Gestión de Flota**: Vehículos, depósitos y rutas
- ✅ **Gestión de Envíos**: Creación, seguimiento y asignación de transportes
- ✅ **Geolocalización**: Servicios de mapeo y rutas optimizadas
- ✅ **Tarifas y Costos**: Cálculo automático de precios
- ✅ **Control de Roles**: Endpoints protegidos (Cliente, Operador, Administrador, Transportista)
- ✅ **API Gateway**: Enrutamiento centralizado

---

## 🛠️ Tecnologías Utilizadas

| Componente | Tecnología |
|-----------|-----------|
| **Lenguaje** | Java 17+ |
| **Framework** | Spring Boot 3.x |
| **Base de Datos** | PostgreSQL |
| **Build Tool** | Maven 3.8+ |
| **Autenticación** | Keycloak + Spring Security |
| **Patrones** | Microservicios, RESTful API |

---

## 📋 Requisitos Previos

Asegúrate de tener instalado:

- **Java Development Kit (JDK)**: 17 o superior
- **Apache Maven**: 3.8 o superior
- **PostgreSQL**: Base de datos configurada
- **Keycloak**: Instancia ejecutándose con realm y roles configurados
- **Git**: Para clonar el repositorio

---

## 🚀 Instalación y Puesta en Marcha

### 1. Clonar el Repositorio

```bash
git clone https://github.com/MarianitoProMax/TPBack.git
cd TP-BACKEND
```

### 2. Compilar el Proyecto

```bash
mvn clean install
```

### 3. Configurar Propiedades

Edita el archivo `application.properties` en cada microservicio con tus credenciales:

```properties
# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/tu_base_datos
spring.datasource.username=postgres
spring.datasource.password=tu_contraseña

# Keycloak
keycloak.auth-server-url=http://localhost:8080
keycloak.realm=tu_realm
keycloak.resource=tu_client
```

### 4. Ejecutar un Microservicio

```bash
cd flotaydepositos
mvn spring-boot:run
```

---

## 📡 Microservicios Disponibles

| Microservicio | Puerto | Descripción |
|--------------|--------|-----------|
| Gateway | 8080 | API Gateway central |
| FlotaYDepositos | 8081 | Gestión de vehículos y depósitos |
| Usuarios | 8082 | Gestión de usuarios |
| GestionDeEnvios | 8083 | Gestión de envíos |
| GeoAPI | 8084 | Servicios geoespaciales |
| TarifasYCostos | 8085 | Cálculo de tarifas |

---

## 📚 Endpoints Principales

*Documentación de endpoints específicos por servicio disponibles en cada módulo.*

---

## 🔒 Autenticación

Todos los endpoints están protegidos por Keycloak. Para acceder:

1. Obtén un token JWT desde Keycloak
2. Incluye el token en el header `Authorization: Bearer <token>`

---

## 📝 Licencia

Proyecto académico - Universidad Tecnológica Nacional

---

## 👥 Equipo

Grupo 64 - 3er Año
