# Foruhub 🖥️💬

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.0-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-8-blue)
![JWT](https://img.shields.io/badge/JWT-secure-yellow)
![Maven](https://img.shields.io/badge/Maven-3.8.8-red)

Foruhub es una **API REST** desarrollada en **Java Spring Boot** para la gestión de usuarios y autenticación mediante **JWT (JSON Web Tokens)**.  
Permite crear, listar, actualizar y eliminar usuarios, además de iniciar sesión y generar tokens seguros.

---

## 📋 Tabla de Contenidos
- [Tecnologías](#tecnologías)
- [Instalación](#instalación)
- [Configuración](#configuración)
- [Endpoints](#endpoints)
- [Ejemplos](#ejemplos)
- [Notas de seguridad](#notas-de-seguridad)
- [Licencia](#licencia)

---

## 🛠️ Tecnologías
- **Java 17**
- **Spring Boot 3**
- **Spring Security**
- **MySQL 8**
- **Maven**
- **JWT**
- **Lombok**

---

## ⚡ Instalación

1. Clonar el repositorio:

```bash
git clone https://github.com/marchena290/foruhub.git
cd foruhub

2. Crear un archivo application.properties a partir del ejemplo:

cp src/main/resources/application.properties.example src/main/resources/application.properties

3. Completar tus credenciales y secreto JWT en application.properties:

spring.datasource.url=jdbc:mysql://localhost/foruhub
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_PASSWORD
api.security.token.secret=TU_SECRETO

4 ejecutar el proyecto:
mvn spring-boot:run

🚀 Endpoints Principales
Método	Endpoint	Descripción
POST	/usuarios	Crear un usuario
GET	/usuarios	Listar todos los usuarios
GET	/usuarios/{id}	Obtener detalles de un usuario
PUT	/usuarios/{id}	Actualizar un usuario
DELETE	/usuarios/{id}	Eliminar un usuario
POST	/login	Iniciar sesión y obtener JWT

💡 Ejemplos de uso
Crear usuario
POST /usuarios
Content-Type: application/json

{
  "nombre": "Juan Pérez",
  "email": "juan@correo.com",
  "password": "123456"
}

Iniciar sesión
POST /login
Content-Type: application/json

{
  "email": "usuario@foro.com",
  "password": "777777"
}


Respuesta:

{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}

🔒 Seguridad

No subir el archivo application.properties real con credenciales a GitHub.

Usa application.properties.example como guía para configuración local.

Las contraseñas se almacenan encriptadas con BCrypt.

📝 Licencia

MIT License.

👨‍💻 Autor

Javier Marchena 

✅ Este README es limpio, profesional y fácil de leer.  

Si quieres, puedo hacer otra versión **con capturas o GIFs** de la API funcionando, que suele impresionar mucho en GitHub.  

¿Quieres que haga esa versión visual?

