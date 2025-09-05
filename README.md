Este proyecto demuestra la refactorización completa de un microservicio Java con Spring Boot, eliminando malas prácticas, aplicando principios de Clean Code y alcanzando alta cobertura de pruebas.

🎯 Objetivos Alcanzados

✅ Identificación y corrección de todas las malas prácticas en Controller, Service, Repository y Model.

✅ Aplicación de principios SOLID, DRY y Clean Code.

✅ Código más legible, mantenible y robusto.

✅ Pruebas unitarias completas con cobertura 89%+ en lógica de negocio.

🚀 Características Clave

API REST profesional con endpoints claros y consistentes

Validación de datos con Bean Validation (@Valid)

Manejo centralizado de excepciones con @RestControllerAdvice

Logging profesional con SLF4J

DTOs tipados para requests y responses

Thread-safety mejorada (ConcurrentHashMap)

Tests unitarios completos con JUnit 5 y Mockito

Reporte de cobertura con JaCoCo

📋 Endpoints Refactorizados
Método	Endpoint	Descripción	Antes
GET	/api/users	Obtener todos los usuarios	/listAll
POST	/api/users	Crear un usuario	/createUserNow
GET	/api/users/{id}	Obtener usuario por ID	/user/{id}
DELETE	/api/users/{id}	Eliminar usuario	/del/{id}
🛠 Tecnologías Utilizadas

Lenguaje: Java 17

Framework: Spring Boot 3.3.1

Pruebas: JUnit 5, Mockito

Cobertura: JaCoCo

Logging: SLF4J

Validación: Jakarta Bean Validation

📊 Resultados de Cobertura (JaCoCo)
Clase	Cobertura Instrucciones	Cobertura Métodos
UserService	89.7%	100%
UserRepository	97.5%	100%
Excepciones	100%	100%
Global	48.9%	—

Ver reporte completo: target/site/jacoco/index.html

🏃‍♂️ Cómo Ejecutar
Prerrequisitos

Java 17+

Maven 3.6+

Comandos Principales
# Compilar y ejecutar tests con cobertura
mvn clean test jacoco:report

# Ejecutar solo tests unitarios
mvn test -Dtest="*ServiceTest,*RepositoryTest"

# Ejecutar la aplicación
mvn spring-boot:run

# Ver reporte de cobertura
start target/site/jacoco/index.html

📁 Estructura Refactorizada
src/
├── main/java/pe/edu/vallegrande/quality/
│   ├── controller/
│   │   ├── UserController.java
│   │   └── GlobalExceptionHandler.java
│   ├── service/
│   │   ├── UserService.java
│   │   └── exception/
│   │       ├── UserNotFoundException.java
│   │       └── UserValidationException.java
│   ├── repository/
│   │   └── UserRepository.java
│   ├── model/
│   │   └── User.java
│   ├── dto/
│   │   ├── UserRequest.java
│   │   └── ApiResponse.java
│   └── VgQualityChallengeApplication.java
└── test/java/
    ├── controller/UserControllerTest.java
    ├── service/UserServiceTest.java
    └── repository/UserRepositoryTest.java

✅ Problemas Corregidos
Problema	Antes	Después
Inyección de dependencias	new UserService()	@Autowired constructor injection
Validación	Manual y repetitiva	@Valid + Bean Validation
Nombres poco descriptivos	a(), b()	getAllUsers(), createUser()
Excepciones genéricas	RuntimeException("error")	Excepciones específicas + GlobalHandler
Logging	System.out.println()	logger.info() con SLF4J
Modelo	Campos públicos	Encapsulación completa (private + getters/setters)
Respuestas	Strings simples	ResponseEntity<ApiResponse<T>>
Thread-safety	ArrayList compartida	ConcurrentHashMap
✨ Mejoras Implementadas

Estereotipos Spring: @Service, @Repository, @RestController

Inyección por constructor (@Autowired)

DTOs tipados para requests/responses

Validación centralizada con jakarta.validation

Manejo de errores con @RestControllerAdvice

Eliminación de código duplicado y valores mágicos

Encapsulación completa del modelo

API versionada /api/users

Tests unitarios con alta cobertura

Logging profesional reemplazando System.out

🎉 Impacto del Refactor

El código ahora es más profesional y mantenible, con:

✅ Mayor legibilidad

✅ Mayor robustez y manejo de errores adecuado

✅ Tests confiables con alta cobertura

✅ API consistente y documentada

El proyecto sirve como ejemplo de refactorización integral de un microservicio Spring Boot, listo para producción.
