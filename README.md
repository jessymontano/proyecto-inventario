# Sistema de gestión de inventario
[![Java](https://img.shields.io/badge/Java-%23ED8B00.svg?logo=openjdk&logoColor=white)](#)
[![JavaFX](https://img.shields.io/badge/JavaFX-FF0000?logoColor=white)](#)
[![SQLite](https://img.shields.io/badge/SQLite-%2307405e.svg?logo=sqlite&logoColor=white)](#)
[![JUnit5](https://img.shields.io/badge/JUnit5-C21325?logo=junit5&logoColor=fff)](#)

Proyecto de reingeniería y mantenimiento de un sistema de gestión de inventario. Realizado para la materia de Administración de Proyectos Informáticos II.

## Funciones

- Autenticación segura con roles.
- Gestión de almacenes.
- Gestión de productos.
- Búsqueda dinámica en tiempo real.
- Registro automático de la fecha, hora y último usuario que modificó o creó un registro.

<img width="450" alt="login" src="https://github.com/user-attachments/assets/7d38beb4-1f95-44b7-8ad7-d35da88b2d14" />
<img width="450" alt="home" src="https://github.com/user-attachments/assets/b384c771-c105-41b9-b261-aeb0a0959d8b" />
<img width="450" alt="almacenes" src="https://github.com/user-attachments/assets/2dfb7556-87fc-49e7-8be2-81f40efa3927" />
<img width="450" alt="productos" src="https://github.com/user-attachments/assets/65624b7f-0859-412b-b851-99971049c517" />

## Instalación

Descargar el instalador más reciente desde la sección de [Releases](../../releases)

## Usuarios por defecto

| Nombre  | Contraseña |
|-------|--------|
| ADMIN | admin23  |
| PRODUCTOS   | productos19  |
| ALMACENES  | almacenes11  |

## Compilación y desarrollo

### Requisitos
- Java Development Kit (JDK) 24 o superior.
- Apache Maven.
- Git.

### Compilar y generar el ejecutable (.jar)
1. Clonar el repositorio:

```bash
git clone https://github.com/jessymontano/proyecto-inventario.git
cd proyecto-inventario
```

2. Compilar el proyecto:
```bash
mvn clean package
```

3. Ejecutar la aplicación:
```bash
java -jar target/proyecto-inventario-1.0-SNAPSHOT.jar
```

### Estructura del proyecto
```
proyecto-inventario
├── src
│   ├── main
│   │   └── java/mx/unison/proyectoinventario
│   │       ├── controller   Controladores de la interfaz
│   │       ├── dao          Data Access Objects
│   │       ├── model        Entidades
│   │       ├── util         Herramientas 
│   │       ├── App.java     Launcher de JavaFX
│   │       └── Main.java    Clase principal
│   ├── resources
│   │   ├── css              Estilos y paleta de colores
│   │   ├── img              Imágenes
│   │   └── view             Interfaces FXML
│   └── test                 Pruebas unitarias y de integración
└── pom.xml                  Configuración de dependencias
```

### Ejecución de pruebas

Ejecutar todas las pruebas JUnit:
```bash
mvn test
```

### Generación de documentación
```bash
mvn javadoc:javadoc
```

La documentación estará disponible en **target/site/apidocs/index.html**
