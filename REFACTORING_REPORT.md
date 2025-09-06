Este proyecto demuestra un proceso de refactorización completo para mejorar la calidad, la mantenibilidad y la robustez de una aplicación Spring Boot. A través de este trabajo, se corrigieron malas prácticas de codificación, se implementaron patrones de diseño modernos y se aumentó la cobertura de tests unitarios, resultando en un sistema más limpio y confiable.

<p align="center">
  <img src="https://media.giphy.com/media/LmN8gLd3Hh41W/giphy.gif" width="400" alt="Refactoring process">
</p>

---

## ✨ Características y Mejoras Clave

* **Arquitectura Limpia**: Se implementó una clara separación de responsabilidades, siguiendo el patrón de diseño **Service-Repository**, lo que hace que el código sea más modular y fácil de entender.
* **Encapsulación y POJOs**: Las entidades ahora utilizan **campos privados** con `getters` y `setters`, garantizando la encapsulación de los datos y la integridad de los objetos.
* **Inyección de Dependencias Correcta**: La inyección de dependencias ahora se realiza a través del **constructor** en lugar de por campo, lo cual facilita la creación de `beans` inmutables y mejora la testabilidad.
* **Manejo de Errores Robusto**: Se reemplazaron las excepciones genéricas por **excepciones personalizadas** y se centralizó el manejo de errores con un **`GlobalExceptionHandler`** para proporcionar respuestas de API consistentes y descriptivas.
* **Logging Profesional**: Se migró de `System.out.println` a **SLF4J**, permitiendo un control más detallado y flexible de los logs de la aplicación.
* **Respuestas de API Consistentes**: Se introdujeron **`Data Transfer Objects` (DTOs)** y `ResponseEntity` tipados para asegurar que todas las respuestas de la API sigan un formato consistente y predecible.
* **Seguridad y Concurrencia**: Se abordaron problemas de `thread-safety` al utilizar **`ConcurrentHashMap`** para la gestión de colecciones internas, protegiendo contra el acceso concurrente y mejorando la robustez.

---

## 🚀 Cómo Empezar

### Requisitos

* **Java 17+**
* **Maven 3.8+**
* **Docker** (opcional, para ejecutar la base de datos)

### Compilación y Ejecución

1.  Clona el repositorio:
    ```bash
    git clone [[https://github.com/vcuaresmadev/crud_user]]
    ```
2.  Compila el proyecto:
    ```bash
    mvn clean install
    ```
3.  Ejecuta la aplicación:
    ```bash
    mvn spring-boot:run
    ```

La aplicación se ejecutará en `http://localhost:8080`.

---

## 📈 Cobertura de Código

Se utilizó **JaCoCo** para analizar la cobertura de código, logrando mejoras significativas en las áreas clave de la lógica de negocio.

| Componente | Instrucciones | Ramas | Líneas | Métodos |
| :--- | :--- | :--- | :--- | :--- |
| **UserService** | 89.7% | 64.3% | 92.9% | 100% |
| **UserRepository** | 97.5% | 75% | 93.3% | 100% |
| **Excepciones** | 100% | 100% | 100% | 100% |
| User (Model) | 35.2% | 0% | 52.4% | 46.2% |

### Análisis Detallado

* **Lógica de Negocio (Service/Repository)**: Alcanzamos un 90%+ de cobertura de instrucciones, lo que garantiza que la lógica central de la aplicación está bien probada con tests unitarios.
* **Áreas por Mejorar**: La cobertura del modelo (`User`) es baja, lo cual es esperado ya que son clases de datos simples. Se planean tests de integración para mejorar la cobertura de los controladores y manejadores de excepciones.

---

## 🤝 Contribución

¡Las contribuciones son bienvenidas! Si encuentras un `bug` o tienes sugerencias para mejorar el código, no dudes en abrir un `issue` o enviar un `pull request`.

---

**© 2025 [Tu Nombre o Compañía]**
