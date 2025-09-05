# **Refactorización de Aplicación Spring Boot** 🧑‍💻

Este proyecto presenta la **refactorización completa** de una aplicación RESTful en Java con Spring Boot. El objetivo principal fue transformar un codebase inicial con malas prácticas en un sistema robusto, limpio y profesional, aplicando principios de **Clean Code** y **arquitectura sólida**.

---

## **Visión General y Logros Clave** 🎯

El proyecto se centró en la mejora integral del código, abordando áreas críticas para la calidad del software. Los logros más importantes incluyen:

* **Aplicación de Principios SOLID y DRY**: Se eliminó la duplicidad de código y se mejoró la modularidad y el diseño de los componentes.
* **Mejora de la Mantenibilidad**: El código es ahora significativamente más legible, fácil de entender y de mantener.
* **Robustez y Manejo de Errores**: Se implementó un manejo de excepciones centralizado, lo que hace que la API sea más estable y predecible.
* **Alta Cobertura de Pruebas**: Se lograron más del **89% de cobertura en la lógica de negocio**, lo que garantiza la fiabilidad del sistema.

---

## **Características de la Implementación** 🛠️

La refactorización incorporó las siguientes características y buenas prácticas:

* **Arquitectura RESTful**: Diseño de una API profesional con endpoints claros y consistentes, utilizando DTOs tipados para las interacciones.
* **Validación de Datos**: Implementación de **Bean Validation** con anotaciones para validar los datos de entrada de forma declarativa.
* **Manejo Centralizado de Excepciones**: Uso de `@RestControllerAdvice` para gestionar y centralizar la respuesta a errores.
* **Pruebas Unitarias Exhaustivas**: Se crearon tests con **JUnit 5 y Mockito** para cubrir la lógica de negocio del servicio y el repositorio.
* **Métricas de Calidad**: Integración de **JaCoCo** para generar reportes detallados de la cobertura de código.

---

## **Endpoints Refactorizados** 🗺️

| Método | Endpoint Refactorizado | Descripción | Endpoint Original (Problema) |
|:-------|:-----------------------|:-------------------------------------------------|:-----------------------------------|
| `GET` | `/api/users` | Obtiene la lista completa de usuarios | `/listAll` |
| `POST` | `/api/users` | Crea un nuevo usuario | `/createUserNow` |
| `GET` | `/api/users/{id}`| Obtiene un usuario por su identificador único | `/user/{id}` |
| `DELETE`| `/api/users/{id}`| Elimina un usuario por su ID | `/del/{id}` |

---

## **Resultados de la Refactorización** 📊

La refactorización no solo mejoró la calidad del código, sino que también se evidenció en métricas de cobertura y en la corrección de problemas específicos.

### **Métricas JaCoCo**

| Módulo | Cobertura de Instrucciones | Cobertura de Métodos |
|:---|:---|:---|
| **UserService** | 89.7% | 100% |
| **UserRepository**| 97.5% | 100% |
| **Excepciones** | 100% | 100% |

El reporte de cobertura completo se encuentra en `target/site/jacoco/index.html`.

### **Comparación: Antes vs. Después**

| Área | Problema Original | Solución Implementada |
|:---|:---|:---|
| **Inyección de Dependencias**| Instanciación manual (`new UserService()`) | **Inyección por constructor** con `@Autowired` |
| **Validación de Datos**| Validación manual y repetitiva | `@Valid` + **Bean Validation** |
| **Manejo de Excepciones**| Excepciones genéricas (`new RuntimeException()`) | **Excepciones específicas** + `@RestControllerAdvice` |
| **Nomenclatura** | Nombres poco descriptivos (`a()`, `b()`) | **Nombres claros y semánticos** (`getAllUsers()`) |
| **Logging**| `System.out.println()` | **SLF4J** para logging profesional |
| **Respuestas de la API** | Retorno de `String` o `Object` | **`ResponseEntity<ApiResponse<T>>`** para respuestas estandarizadas |

---

## **Guía de Ejecución** 🏃‍♂️

Para compilar, ejecutar y analizar el proyecto, sigue estos pasos.

### **Prerrequisitos**

* Java Development Kit (JDK) 17+
* Apache Maven 3.6+

### **Comandos Principales**

```bash
# Compila, ejecuta los tests y genera el reporte de cobertura
mvn clean test jacoco:report

# Inicia la aplicación Spring Boot
mvn spring-boot:run
