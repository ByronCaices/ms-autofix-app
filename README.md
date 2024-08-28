# AutoFix - Sistema de Gestión de Reparaciones

## Descripción del Proyecto

**AutoFix** es un sistema integral diseñado para la gestión eficiente de talleres especializados en el mantenimiento y reparación de vehículos. El sistema tiene como objetivo mejorar la calidad y eficiencia de los servicios ofrecidos por la cadena de talleres AutoFix, enfrentando desafíos relacionados con la creciente demanda y la diversidad de modelos de vehículos que requieren reparaciones.

Este proyecto se centra en la creación de un sistema que permita:
- Registrar los vehículos que llegan por primera vez al taller.
- Registrar las reparaciones realizadas a cada vehículo.
- Calcular el costo total de las reparaciones.
- Generar reportes que permitan un mejor seguimiento de los servicios realizados.

## Arquitectura del Proyecto

El proyecto ha sido desarrollado utilizando una arquitectura de microservicios, lo que permite una gestión modular e independiente de cada uno de los servicios del sistema. A continuación, se detallan los aspectos principales del frontend, backend y despliegue en producción.

### 1. Frontend

- **Tecnología:** ReactJS.
- **Descripción:** El frontend de la aplicación es una interfaz única que interactúa con los microservicios del backend para mostrar la información relevante a los usuarios.
- **Herramienta de Desarrollo:** Visual Studio Code.

### 2. Backend

- **Patrón Arquitectural:** Microservicios.
- **Tecnología:** Spring Boot con programación orientada a objetos.
- **Descripción:**
  - Cada microservicio es un proyecto independiente que utiliza su propia base de datos relacional (MySQL o PostgreSQL).
  - El backend sigue una arquitectura por capas, incluyendo capas de controlador (@RestController), servicio (@Service), repositorio (@Repository), y entidad (@Entity).
  - Los microservicios implementan los patrones de **ConfigServer**, **Service Discovery (Eureka Server)**, y **API Gateway**.
- **Herramienta de Desarrollo:** IntelliJ o Visual Studio Code.

### 3. Despliegue en Producción

- **Tecnologías Utilizadas:**
  - **Docker:** Todos los microservicios del backend y el frontend son empaquetados en contenedores Docker independientes y almacenados en Docker Hub.
  - **Kubernetes:** El despliegue se realiza utilizando un clúster de Kubernetes (por ejemplo, Minikube), desde las imágenes almacenadas en Docker Hub.
  - **Cluster Deployment:** Se utilizan scripts de Deployment, Service, etc., para el deployment del clúster.
- **Acceso a la Aplicación:** La aplicación web es accesible desde un navegador web.

## Requisitos del Sistema

- **Node.js**: Para ejecutar el frontend.
- **Java**: Para ejecutar los microservicios de Spring Boot.
- **Docker y Docker Compose**: Para crear y gestionar los contenedores de los servicios.
- **Kubernetes**: Para el despliegue y la orquestación de los contenedores en producción.

## Instrucciones de Instalación y Ejecución

1. Clona este repositorio en tu máquina local.
2. Sigue las instrucciones en el archivo `docker-compose.yml` para construir y ejecutar los contenedores Docker.
3. Utiliza Kubernetes para desplegar la aplicación en un entorno de producción.
4. Accede a la aplicación web desde tu navegador preferido.

## Contribuciones

Las contribuciones son bienvenidas. Si deseas contribuir, por favor sigue los lineamientos de contribución y haz un pull request.

## Licencia

Este proyecto está licenciado bajo los términos de la [Licencia MIT](LICENSE).

