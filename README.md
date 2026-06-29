# Sistema de Gestión de Atención Médica

Aplicación de escritorio en **Java Swing** con arquitectura **MVC + DAO** y conexión a **MySQL** mediante JDBC.

## Estructura del proyecto

```
ProyectoSalud/
├── lib/                          # Driver JDBC MySQL (mysql-connector-j.jar)
├── sql/
│   └── database.sql              # Script de creación de tablas
├── src/main/java/com/salud/
│   ├── Main.java                 # Punto de entrada
│   ├── model/                    # Entidades (Usuario, Paciente, Cita)
│   ├── view/                     # Interfaces Swing
│   ├── controller/               # Lógica de presentación
│   ├── dao/                      # Acceso a datos JDBC
│   └── util/
│       └── Conexion.java         # Conexión a MySQL
└── .vscode/                      # Configuración para VSCode
```

## Requisitos

- JDK 11 o superior
- MySQL 8+
- VSCode con extensión **Extension Pack for Java**
- Driver JDBC: [mysql-connector-j](https://dev.mysql.com/downloads/connector/j/)  
  Descargar el `.jar` y colocarlo en la carpeta `lib/`

## Configuración de la base de datos

1. Iniciar MySQL.
2. Ejecutar el script:

```bash
mysql -u root -p < sql/database.sql
```

3. Ajustar credenciales en `src/main/java/com/salud/util/Conexion.java` si es necesario:

```java
private static final String URL = "jdbc:mysql://localhost:3306/centro_salud";
private static final String USER = "root";
private static final String PASSWORD = "";
```

## Ejecutar la aplicación

1. Abrir la carpeta `ProyectoSalud` en VSCode.
2. Esperar a que Java cargue el proyecto.
3. Ejecutar `com.salud.Main` (F5 o Run Java).

## Credenciales por defecto

| Usuario | Contraseña |
|---------|------------|
| admin   | admin123   |

## Funcionalidades

- Login de administrador
- Registro de pacientes (DNI, nombre, apellido, teléfono)
- Listado de pacientes en tabla
- Registro de citas médicas (fecha, hora, paciente)
- Navegación entre pantallas con botón "Volver al menú"

## Formato de citas

- **Fecha:** `yyyy-MM-dd` (ejemplo: `2026-06-18`)
- **Hora:** `HH:mm` (ejemplo: `09:30`)
