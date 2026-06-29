# Manual de Pruebas — Sistema Centro de Salud

Guía paso a paso para validar el flujo completo del sistema en Java nativo (Swing + JDBC).

---

## 0. Preparación del entorno

### 0.1 Base de datos

```bash
mysql -u TU_USUARIO -p < sql/migracion-v2.sql
```

Esto crea la base `salud_db` con tablas, catálogos y **datos de prueba precargados**.

### 0.2 Configuración local

Asegúrate de tener `config.properties` en la raíz del proyecto:

```properties
db.url=jdbc:mysql://localhost:3306/salud_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
db.user=TU_USUARIO_MYSQL
db.password=TU_CONTRASEÑA_MYSQL
```

### 0.3 Ejecutar la aplicación

Desde VS Code/Cursor ejecuta `com.salud.Main`, o en terminal:

```bash
cd /home/adilson/Documents/ProyectoSalud
java -cp bin com.salud.Main
```

> Si usas el driver MySQL externo: `java -cp "bin:lib/mysql-connector-j.jar" com.salud.Main`

---

## 1. Credenciales de prueba (precargadas en SQL)

| Usuario     | Contraseña    | Rol            | Uso principal                          |
|-------------|---------------|----------------|----------------------------------------|
| `admin`     | `admin123`    | ADMINISTRADOR  | Acceso total + catálogos + reportes    |
| `admision`  | `admision123` | ADMISION       | Pacientes y citas                      |
| `enfermeria`| `enfermeria123`| ENFERMERIA    | Triaje                                 |
| `medico`    | `medico123`   | MEDICO         | Atención médica (vinculado al médico ID 1: Carlos Mendoza) |

---

## 2. Datos de prueba precargados

### Pacientes

| DNI      | Nombre | Apellido | Seguro     |
|----------|--------|----------|------------|
| 72345678 | Juan   | Pérez    | SIS        |
| 73456789 | Lucía  | García   | PARTICULAR |

### Médicos

| ID | DNI      | Nombre          | Especialidad     | Consultorio |
|----|----------|-----------------|------------------|-------------|
| 1  | 45678901 | Carlos Mendoza  | Medicina General | C-101       |
| 2  | 45678902 | Ana Torres      | Pediatría        | C-102       |
| 3  | 45678903 | Luis Ramírez    | Ginecología      | C-103       |

### Códigos CIE-10 disponibles

`J00`, `J06.9`, `I10`, `E11.9`, `M54.5`, `R51`, `K29.7`, `J02.9`

---

## 3. Flujo de prueba completo (recomendado)

### Paso 1 — Login como Admisión

1. Usuario: `admision` / Contraseña: `admision123`
2. Verifica que el menú muestre: Registrar Paciente, Ver Pacientes, Registrar Cita, Gestionar Citas.

### Paso 2 — Registrar un paciente nuevo

1. Clic en **Registrar Paciente**
2. Ingresar:

| Campo            | Valor de prueba        |
|------------------|------------------------|
| DNI              | `74567890`             |
| Nombre           | `Pedro`                |
| Apellido         | `Sánchez`              |
| Fecha nacimiento | `1995-03-10`           |
| Teléfono         | `998877665`            |
| Dirección        | `Av. Universitaria 200`|
| Seguro           | `SIS`                  |

3. Clic **Guardar** → debe aparecer mensaje de éxito.
4. El sistema crea automáticamente su historia clínica.

### Paso 3 — Buscar y editar paciente

1. Menú → **Ver Pacientes**
2. En buscar escribir: `74567890` → **Buscar**
3. Seleccionar fila → **Editar**
4. Cambiar teléfono a `999888777` → **Guardar**

### Paso 4 — Registrar cita médica

1. Menú → **Registrar Cita**
2. Ingresar:

| Campo         | Valor de prueba   |
|---------------|-------------------|
| Paciente      | Pedro Sánchez     |
| Especialidad  | Medicina General  |
| Médico        | Carlos Mendoza    |
| Fecha         | Fecha de **hoy** (`yyyy-MM-dd`, ej: `2026-06-29`) |
| Hora          | `10:00`           |

3. **Registrar Cita** → debe mostrar comprobante con estado `PROGRAMADA`.

### Paso 5 — Confirmar llegada del paciente

1. Menú → **Gestionar Citas**
2. Filtrar por fecha de hoy
3. Seleccionar la cita de Pedro Sánchez
4. **Confirmar Llegada** → estado cambia a `POR_TRIAR`

### Paso 6 — Triaje (Enfermería)

1. Cerrar sesión → login `enfermeria` / `enfermeria123`
2. Menú → **Triaje**
3. Seleccionar cita de Pedro Sánchez → **Registrar Triaje**
4. Ingresar:

| Campo              | Valor   |
|--------------------|---------|
| Peso (kg)          | `70`    |
| Talla (m)          | `1.75`  |
| Presión arterial   | `120/80`|
| Temperatura (°C)   | `36.5`  |
| FC                 | `72`    |
| FR                 | `18`    |
| SpO2               | `98`    |
| Motivo consulta    | `Dolor de cabeza y fiebre desde ayer` |

5. **Guardar Triaje** → IMC calculado ≈ `22.86`, estado cita → `PENDIENTE_ATENCION`

### Paso 7 — Atención médica (Médico)

1. Cerrar sesión → login `medico` / `medico123`
2. Menú → **Atención Médica**
3. Seleccionar cita de Pedro → **Atender**
4. Ingresar:

| Campo               | Valor de prueba                              |
|---------------------|----------------------------------------------|
| CIE-10              | `R51 - Cefalea`                              |
| Diagnóstico         | `Cefalea tensional`                            |
| Anamnesis           | `Paciente refiere cefalea frontal de 2 días` |
| Examen físico       | `Paciente consciente, orientado, sin rigidez` |
| Plan tratamiento    | `Reposo, hidratación, analgésicos`           |

5. **Registrar Atención** → confirmar → estado cita → `ATENDIDA`
6. **Emitir Receta**:
   - Medicamentos: `Paracetamol 500mg`
   - Indicaciones: `1 tableta cada 8 horas por 3 días`

### Paso 8 — Consultar historia clínica

1. Login como `admin` / `admin123` (o cualquier rol con acceso)
2. Menú → **Consultar Historia**
3. DNI: `74567890`
4. Verificar:
   - Antecedentes editables (tipo sangre, alergias)
   - Tabla con la atención registrada
5. Seleccionar atención → **Ver Receta** → debe mostrar Paracetamol

### Paso 9 — Reportes (Administrador)

1. Login `admin` / `admin123`
2. Menú → **Reportes**
3. **Citas del día**: fecha de hoy → ver conteo por estado
4. **Atenciones por médico**: desde/hasta fecha de hoy → ver Carlos Mendoza: 1
5. **Pacientes activos**: debe mostrar total ≥ 3

### Paso 10 — Administración de catálogos

1. Como `admin`:
   - **Especialidades** → agregar "Cardiología"
   - **Médicos** → agregar DNI `45678904`, nombres `Elena`, apellidos `Díaz`, especialidad ID `1`, consultorio `C-104`
   - **Usuarios** → agregar usuario `recep01` / `recep123` / rol `ADMISION`

### Paso 11 — Cancelar y reprogramar cita

1. Login `admision`
2. Registrar otra cita para Juan Pérez (DNI `72345678`) en fecha futura
3. **Gestionar Citas** → seleccionar → **Cancelar Cita** → motivo: `Paciente no puede asistir`
4. Registrar nueva cita y probar **Reprogramar** con nueva fecha/hora

---

## 4. Validaciones a verificar

| Acción                    | Resultado esperado                                      |
|---------------------------|---------------------------------------------------------|
| Login con campos vacíos   | JOptionPane de advertencia                              |
| DNI duplicado             | Error al registrar paciente                             |
| Cita en horario ocupado   | Error de conflicto de horario                           |
| Triaje sin datos          | JOptionPane pidiendo completar campos                   |
| Temperatura > 38.5°C      | Alerta de advertencia (pero permite guardar)            |
| Atención sin triaje       | No aparece en cola o error al atender                   |
| Desactivar paciente       | Confirmación antes de desactivar                        |
| Cerrar sesión             | Confirmación y vuelta al login                          |

---

## 5. Solución de problemas

| Problema | Solución |
|----------|----------|
| `ExceptionInInitializerError` en Conexion | Crear `config.properties` desde `config.properties.example` |
| `Communications link failure` | Verificar que MySQL esté activo y credenciales correctas |
| Menú vacío o incompleto | Verificar que el usuario tenga `rol` y `activo=1` en tabla `usuario` |
| No aparecen citas en triaje | Confirmar llegada primero (estado `POR_TRIAR`) y fecha = hoy |
| No aparecen citas en atención | Completar triaje primero (estado `PENDIENTE_ATENCION`) |

---

## 6. Resumen del flujo de estados de cita

```
PROGRAMADA → (Confirmar llegada) → POR_TRIAR → (Triaje) → PENDIENTE_ATENCION → (Atención) → ATENDIDA
                                                                                    ↘ CANCELADA
```

---

*Documento generado para validación funcional del sistema expandido.*
