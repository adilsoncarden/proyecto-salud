# Plan de Expansión del Sistema — Centro de Salud San Martín de Porres

**Documento:** Guía técnica de requerimientos (nivel regular)  
**Base de análisis:** `proyecto-contexto.md`  
**Arquitectura objetivo:** Java nativo + Swing + JDBC + patrón MVC + DAO  
**Estado actual del código:** Módulo básico de login, registro/listado de pacientes y creación de citas  

---

## Resumen ejecutivo

El informe académico describe un sistema integral con **5 actores**, **7 casos de uso principales**, **11 entidades de dominio** y un flujo clínico completo (admisión → triaje → consulta → historia clínica → receta).  

La implementación actual cubre aproximadamente **el 25–30 %** del alcance funcional del informe. Este plan propone cerrar la brecha en **6 fases ordenadas**, manteniendo complejidad **regular**: sin microservicios, sin frameworks web y sin reportes avanzados con librerías externas. Todo se resuelve con **Swing, JDBC estático y MySQL**.

### Lo que ya existe (baseline)

| Componente | Implementado | Limitaciones actuales |
|------------|:------------:|----------------------|
| RF01 — Registro de pacientes | Parcial | Solo `INSERT`; sin edición, baja lógica ni búsqueda por DNI |
| RF02 — Agendar citas | Parcial | Solo creación; sin cancelar, reprogramar ni validar cupos |
| RF08 — Autenticación | Parcial | Un solo rol implícito (admin); sin permisos por perfil |
| RF09 — Búsqueda rápida | No | Listado completo sin filtros |
| RF03/RF10 — Historia clínica | No | No existe módulo clínico |
| RF04 — Consulta por fecha/especialidad | No | Citas sin especialidad ni médico asignado |
| RF05 — Reportes | No | Sin reportes ni estadísticas |
| RF06 — Actualización de paciente | No | Sin formulario de edición |
| RF07 — Integración de áreas | No | Menú único sin flujo admisión → triaje → médico |
| Triaje (CU03) | No | Entidad y pantallas inexistentes |
| Atención médica (CU04) | No | Sin diagnóstico, anamnesis ni CIE-10 |
| Receta médica (CU06) | No | Sin prescripción digital |
| Gestión de especialidades/médicos | No | Catálogos inexistentes |

---

## 1. Módulos completos detectados

Comparativa entre lo que exige el informe y lo que falta implementar en Java.

### 1.1 Paquete de Seguridad y Accesos

| Requerimiento del informe | Estado | Qué falta (nivel regular) |
|---------------------------|:------:|---------------------------|
| CU07 — Autenticar usuario | Parcial | Tabla `usuario` con campo `rol`; menú dinámico según rol |
| Roles: Administrador, Admisión, Enfermería, Médico | No | Constantes de rol y validación en controladores |
| Gestión de usuarios (admin) | No | CRUD básico de usuarios del sistema |
| Cierre de sesión con confirmación | Parcial | Botón "Cerrar sesión" existe; falta confirmación y limpieza de sesión |

**Entregable de fase:** Login con roles + `MenuFrame` que muestre opciones según el usuario autenticado + pantalla admin para dar de alta usuarios internos.

---

### 1.2 Paquete de Admisión y Citas

| Requerimiento del informe | Estado | Qué falta (nivel regular) |
|---------------------------|:------:|---------------------------|
| CU01 — Registrar paciente | Parcial | Campos `fecha_nacimiento`, `direccion`, `tipo_seguro` (SIS/Particular) |
| CU01 — Actualizar paciente (RF06) | No | Formulario de edición desde listado |
| CU02 — Programar cita | Parcial | Vincular cita a médico y especialidad |
| CU02 — Reprogramar cita | No | Cambiar fecha/hora con validación |
| CU02 — Cancelar cita (A3) | No | Cambio de estado a `CANCELADA` + motivo |
| CU02 — Consultar citas por fecha/especialidad (RF04) | No | Pantalla de búsqueda con filtros |
| RF09 — Búsqueda rápida de pacientes | No | Campo de búsqueda por DNI o apellido |
| Ticket/comprobante de cita | No | `JOptionPane` o `JDialog` con resumen imprimible en texto |

**Entregable de fase:** Admisión completa con pacientes editables, citas con ciclo de vida (`PROGRAMADA`, `POR_TRIAR`, `PENDIENTE_ATENCION`, `ATENDIDA`, `CANCELADA`) y filtros de consulta.

---

### 1.3 Paquete de Triaje

| Requerimiento del informe | Estado | Qué falta (nivel regular) |
|---------------------------|:------:|---------------------------|
| CU03 — Realizar triaje | No | Módulo completo nuevo |
| Signos vitales: PA, FC, FR, temperatura, SpO₂ | No | Formulario con campos numéricos |
| Peso, talla, IMC automático | No | Cálculo en controlador: `peso / (talla²)` |
| Motivo de consulta | No | Campo texto en triaje |
| Validación de rangos anómalos | No | Alertas visuales si valores fuera de rango |
| Cola de pacientes del día | No | `JTable` con citas en estado `POR_TRIAR` |

**Entregable de fase:** Pantalla de enfermería que liste citas del día, registre triaje y cambie estado de cita a `PENDIENTE_ATENCION`.

> **Alcance regular:** No implementar gráficos ni semáforos complejos; basta resaltar filas o mostrar `JOptionPane` de advertencia.

---

### 1.4 Paquete de Atención Médica e Historia Clínica

| Requerimiento del informe | Estado | Qué falta (nivel regular) |
|---------------------------|:------:|---------------------------|
| CU04 — Registrar atención médica | No | Formulario de consulta para el médico |
| CU05 — Consultar historia clínica | No | Vista de solo lectura con historial cronológico |
| Historia clínica electrónica (RF03) | No | Expediente único por paciente |
| CIE-10 en diagnóstico | Parcial* | *Nivel regular: campo `codigo_cie10` + descripción manual; catálogo CIE-10 reducido en tabla local (20–30 códigos frecuentes) con `JComboBox` |
| RF10 — Historial de atenciones | No | Tabla de atenciones previas por paciente |
| CU06 — Emitir receta | No | Receta como texto estructurado vinculada a la atención |
| Bloqueo de edición post-atención | No | Flag `editable = false` o no exponer botón editar |

**Entregable de fase:** Flujo médico: cola de pacientes triados → consulta → registro en `atencion_medica` → actualización de `historia_clinica` → receta opcional → cita `ATENDIDA`.

---

### 1.5 Módulo de Reportes (RF05)

| Requerimiento del informe | Estado | Qué falta (nivel regular) |
|---------------------------|:------:|---------------------------|
| Reportes de atención médica | No | Reportes simples en pantalla (sin PDF en fase 1) |
| Estadísticas operativas | No | Conteos básicos por rango de fechas |

**Entregable de fase (regular):**

- Reporte 1: Citas del día (total por estado).
- Reporte 2: Atenciones por médico en un rango de fechas.
- Reporte 3: Pacientes registrados en el mes.

> Mostrar resultados en `JTextArea` dentro de un `JDialog` o segunda `JTable`, con botón "Copiar al portapapeles". Exportar a `.txt` es opcional en esta fase.

---

### 1.6 Catálogos de soporte

| Catálogo | Estado | Qué falta |
|----------|:------:|-----------|
| Especialidades médicas | No | Medicina General, Pediatría, Ginecología, Odontología, etc. |
| Personal de salud / Médicos | No | Tabla con DNI, nombres, especialidad, activo |
| Códigos CIE-10 frecuentes | No | Tabla auxiliar pequeña para combo de diagnóstico |

---

## 2. Modificaciones en la base de datos

Script sugerido: `sql/migracion-v2.sql` (ejecutar sobre `salud_db`).

### 2.1 Modificar tablas existentes

```sql
-- PACIENTE: ampliar según modelo físico del informe
ALTER TABLE paciente
    ADD COLUMN fecha_nacimiento DATE NULL AFTER apellido,
    ADD COLUMN direccion VARCHAR(255) NULL AFTER telefono,
    ADD COLUMN tipo_seguro VARCHAR(20) DEFAULT 'SIS' AFTER direccion,
    ADD COLUMN activo TINYINT(1) NOT NULL DEFAULT 1 AFTER tipo_seguro,
    ADD UNIQUE KEY uk_paciente_dni (dni);

-- USUARIO: soportar roles del informe
ALTER TABLE usuario
    ADD COLUMN rol VARCHAR(30) NOT NULL DEFAULT 'ADMINISTRADOR' AFTER password,
    ADD COLUMN nombres VARCHAR(100) NULL AFTER rol,
    ADD COLUMN apellidos VARCHAR(100) NULL AFTER nombres,
    ADD COLUMN activo TINYINT(1) NOT NULL DEFAULT 1 AFTER apellidos;

-- CITA: convertir en CitaMedica del informe
ALTER TABLE cita
    ADD COLUMN id_medico INT NULL AFTER paciente_id,
    ADD COLUMN id_especialidad INT NULL AFTER id_medico,
    ADD COLUMN estado VARCHAR(30) NOT NULL DEFAULT 'PROGRAMADA' AFTER hora,
    ADD COLUMN consultorio VARCHAR(50) NULL AFTER estado,
    ADD COLUMN motivo_cancelacion VARCHAR(255) NULL AFTER consultorio,
    ADD CONSTRAINT fk_cita_medico FOREIGN KEY (id_medico) REFERENCES medico(id),
    ADD CONSTRAINT fk_cita_especialidad FOREIGN KEY (id_especialidad) REFERENCES especialidad(id);
```

> Si las tablas `medico` y `especialidad` aún no existen, crearlas **antes** de aplicar las FK de `cita`.

### 2.2 Tablas nuevas

```sql
-- Catálogo de especialidades
CREATE TABLE especialidad (
    id            INT AUTO_INCREMENT PRIMARY KEY,
    nombre        VARCHAR(100) NOT NULL UNIQUE,
    descripcion   VARCHAR(255) NULL,
    activo        TINYINT(1) NOT NULL DEFAULT 1
);

-- Médicos / personal de salud con rol médico
CREATE TABLE medico (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    dni             VARCHAR(8) NOT NULL UNIQUE,
    nombres         VARCHAR(100) NOT NULL,
    apellidos       VARCHAR(100) NOT NULL,
    id_especialidad INT NOT NULL,
    consultorio     VARCHAR(50) NULL,
    activo          TINYINT(1) NOT NULL DEFAULT 1,
    CONSTRAINT fk_medico_especialidad
        FOREIGN KEY (id_especialidad) REFERENCES especialidad(id)
);

-- Historia clínica (1 por paciente)
CREATE TABLE historia_clinica (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    id_paciente     INT NOT NULL UNIQUE,
    fecha_creacion  DATE NOT NULL,
    tipo_sangre     VARCHAR(5) NULL,
    alergias        TEXT NULL,
    CONSTRAINT fk_historia_paciente
        FOREIGN KEY (id_paciente) REFERENCES paciente(id)
);

-- Triaje vinculado a cita
CREATE TABLE triaje (
    id                      INT AUTO_INCREMENT PRIMARY KEY,
    id_cita                 INT NOT NULL UNIQUE,
    id_enfermera_usuario    INT NULL,
    peso_kg                 DECIMAL(5,2) NOT NULL,
    talla_m                 DECIMAL(4,2) NOT NULL,
    presion_arterial        VARCHAR(15) NOT NULL,
    frecuencia_cardiaca     INT NULL,
    frecuencia_respiratoria INT NULL,
    temperatura_c           DECIMAL(4,2) NOT NULL,
    saturacion_oxigeno      INT NULL,
    imc                     DECIMAL(5,2) NULL,
    motivo_consulta         TEXT NOT NULL,
    fecha_registro          DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_triaje_cita
        FOREIGN KEY (id_cita) REFERENCES cita(id),
    CONSTRAINT fk_triaje_usuario
        FOREIGN KEY (id_enfermera_usuario) REFERENCES usuario(id)
);

-- Atención médica
CREATE TABLE atencion_medica (
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    id_historia         INT NOT NULL,
    id_cita             INT NOT NULL UNIQUE,
    id_medico           INT NOT NULL,
    codigo_cie10        VARCHAR(10) NOT NULL,
    diagnostico_detalle VARCHAR(255) NOT NULL,
    anamnesis           TEXT NULL,
    examen_fisico       TEXT NULL,
    plan_tratamiento    TEXT NULL,
    fecha_atencion      DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_atencion_historia
        FOREIGN KEY (id_historia) REFERENCES historia_clinica(id),
    CONSTRAINT fk_atencion_cita
        FOREIGN KEY (id_cita) REFERENCES cita(id),
    CONSTRAINT fk_atencion_medico
        FOREIGN KEY (id_medico) REFERENCES medico(id)
);

-- Receta médica (1 por atención en nivel regular)
CREATE TABLE receta_medica (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    id_atencion     INT NOT NULL UNIQUE,
    fecha_emision   DATE NOT NULL,
    medicamentos    TEXT NOT NULL,
    indicaciones    TEXT NOT NULL,
    CONSTRAINT fk_receta_atencion
        FOREIGN KEY (id_atencion) REFERENCES atencion_medica(id)
);

-- Catálogo CIE-10 reducido (nivel regular)
CREATE TABLE catalogo_cie10 (
    codigo      VARCHAR(10) PRIMARY KEY,
    descripcion VARCHAR(255) NOT NULL
);
```

### 2.3 Datos semilla mínimos

```sql
INSERT INTO especialidad (nombre) VALUES
('Medicina General'), ('Pediatría'), ('Ginecología'), ('Odontología'), ('Laboratorio');

INSERT INTO catalogo_cie10 (codigo, descripcion) VALUES
('J00', 'Rinofaringitis aguda'),
('J06.9', 'Infección aguda de vías respiratorias superiores'),
('I10', 'Hipertensión esencial'),
('E11.9', 'Diabetes mellitus tipo 2'),
('M54.5', 'Lumbago no especificado');
```

### 2.4 Índices recomendados

```sql
CREATE INDEX idx_cita_fecha_estado ON cita (fecha, estado);
CREATE INDEX idx_paciente_apellido ON paciente (apellido, nombre);
CREATE INDEX idx_atencion_fecha ON atencion_medica (fecha_atencion);
```

### 2.5 Bajas lógicas

No eliminar registros clínicos con `DELETE`. Usar:

- `paciente.activo = 0` para pacientes dados de baja administrativa.
- `cita.estado = 'CANCELADA'` para citas anuladas.
- `usuario.activo = 0` para deshabilitar accesos.

---

## 3. Nuevas clases por capa (nivel regular)

### Convención general

- **Conexion.java** se mantiene como utilidad estática; **no** agregar SQL de negocio allí.
- Toda consulta va en clases `*DAO` usando `Conexion.getConexion()` dentro de `try-with-resources`.
- Los controladores instancian DAOs con `new` (patrón actual del proyecto).
- Reutilizar `ValidacionHelper` y `UIStyles` para no duplicar estilos.

---

### 3.1 Modelos (`com.salud.model`)

#### Modificar entidades existentes

| Clase | Atributos nuevos |
|-------|------------------|
| `Paciente` | `fechaNacimiento` (Date), `direccion` (String), `tipoSeguro` (String), `activo` (boolean) |
| `Usuario` | `rol` (String), `nombres` (String), `apellidos` (String), `activo` (boolean) |
| `Cita` | `medicoId`, `especialidadId`, `estado` (String), `consultorio` (String), `motivoCancelacion` (String) |

#### Crear entidades nuevas

| Clase nueva | Atributos principales |
|-------------|----------------------|
| `Especialidad` | id, nombre, descripcion, activo |
| `Medico` | id, dni, nombres, apellidos, especialidadId, consultorio, activo |
| `HistoriaClinica` | id, pacienteId, fechaCreacion, tipoSangre, alergias |
| `Triaje` | id, citaId, enfermeraUsuarioId, pesoKg, tallaM, presionArterial, frecuenciaCardiaca, frecuenciaRespiratoria, temperaturaC, saturacionOxigeno, imc, motivoConsulta, fechaRegistro |
| `AtencionMedica` | id, historiaId, citaId, medicoId, codigoCie10, diagnosticoDetalle, anamnesis, examenFisico, planTratamiento, fechaAtencion |
| `RecetaMedica` | id, atencionId, fechaEmision, medicamentos, indicaciones |
| `DiagnosticoCie10` | codigo, descripcion (para el catálogo auxiliar) |

#### Clase de soporte (opcional, regular)

| Clase | Propósito |
|-------|-----------|
| `SesionUsuario` | Almacenar en memoria el `Usuario` logueado (rol, id, nombre) para el menú dinámico |

---

### 3.2 DAOs (`com.salud.dao`)

> **Nota:** Los métodos usan JDBC tradicional con `Conexion.getConexion()`. No requiere cambios en `Conexion.java` salvo, opcionalmente, un helper `cerrarRecursos(ResultSet, PreparedStatement, Connection)` estático para reducir repetición.

#### Ampliar DAOs existentes

**`PacienteDAO`**

| Método | Descripción SQL |
|--------|-----------------|
| `buscarPorDni(String dni)` | `SELECT ... WHERE dni = ? AND activo = 1` |
| `buscarPorId(int id)` | Búsqueda por PK |
| `buscarPorTexto(String texto)` | `LIKE` en dni, nombre o apellido |
| `actualizar(Paciente p)` | `UPDATE paciente SET ...` |
| `desactivar(int id)` | `UPDATE paciente SET activo = 0` |
| `existeDni(String dni)` | Validar duplicados al registrar/editar |

**`CitaDAO`**

| Método | Descripción SQL |
|--------|-----------------|
| `listarPorFecha(LocalDate fecha)` | Citas de un día |
| `listarPorEstado(String estado)` | Cola de triaje o consulta médica |
| `listarPorPaciente(int pacienteId)` | Historial de citas del paciente |
| `listarConFiltros(fecha, especialidadId, estado)` | RF04 |
| `actualizarEstado(int idCita, String estado)` | Cambios de flujo |
| `reprogramar(int idCita, Date fecha, Time hora)` | CU02 flujo reprogramación |
| `cancelar(int idCita, String motivo)` | Estado `CANCELADA` + motivo |
| `existeConflictoHorario(int medicoId, Date fecha, Time hora)` | Evitar doble reserva |

**`UsuarioDAO`**

| Método | Descripción SQL |
|--------|-----------------|
| `login(username, password)` | Incluir `rol` y `activo = 1` en el SELECT |
| `listarTodos()` | Para administración |
| `insertar(Usuario u)` | Alta de usuario interno |
| `actualizar(Usuario u)` | Edición básica |
| `desactivar(int id)` | Baja lógica |

#### Crear DAOs nuevos

**`EspecialidadDAO`**

- `listarActivas()` → para `JComboBox`
- `insertar(Especialidad e)` (solo admin)

**`MedicoDAO`**

- `listarActivos()`
- `listarPorEspecialidad(int especialidadId)`
- `buscarPorId(int id)`
- `insertar(Medico m)` (solo admin)

**`HistoriaClinicaDAO`**

- `buscarPorPaciente(int pacienteId)`
- `crearSiNoExiste(int pacienteId)` → llamar al registrar primer paciente o primera atención
- `actualizarAntecedentes(id, tipoSangre, alergias)`

**`TriajeDAO`**

- `insertar(Triaje t)`
- `buscarPorCita(int citaId)`
- `existeTriajeParaCita(int citaId)`

**`AtencionMedicaDAO`**

- `insertar(AtencionMedica a)`
- `listarPorHistoria(int historiaId)` → cronología RF10
- `buscarPorCita(int citaId)`

**`RecetaMedicaDAO`**

- `insertar(RecetaMedica r)`
- `buscarPorAtencion(int atencionId)`

**`CatalogoCie10DAO`**

- `listarTodos()` → llenar combo de diagnóstico
- `buscarPorCodigo(String codigo)`

**`ReporteDAO`** (consultas de agregación simples)

- `contarCitasPorEstadoEnFecha(LocalDate fecha)`
- `contarAtencionesPorMedico(LocalDate desde, LocalDate hasta)`
- `contarPacientesRegistradosEnMes(int anio, int mes)`

---

### 3.3 Controladores (`com.salud.controller`)

#### Ampliar controladores existentes

**`LoginController`**

- Guardar usuario en `SesionUsuario`.
- Redirigir a `MenuFrame` pasando el rol (o leerlo desde `SesionUsuario`).
- Validar que el usuario esté activo.

**`PacienteController`**

- Acción **Editar** desde `PacienteListFrame`.
- Búsqueda en tiempo real o por botón "Buscar".
- Confirmación antes de desactivar paciente.
- Al registrar paciente: llamar `HistoriaClinicaDAO.crearSiNoExiste()`.

**`CitaController`**

- Selección de especialidad → cargar médicos disponibles.
- Validar cupo antes de guardar.
- Pantalla de listado/gestión: cancelar y reprogramar.
- Mostrar comprobante al registrar.

#### Crear controladores nuevos

| Controlador | Responsabilidades |
|-------------|-------------------|
| `MenuController` | Armar menú dinámico según rol; centralizar navegación |
| `TriajeController` | Cargar cola del día; validar rangos; calcular IMC; guardar triaje |
| `AtencionMedicaController` | Cola de pacientes triados; registrar consulta; cerrar cita |
| `HistoriaClinicaController` | Mostrar expediente y lista de atenciones previas |
| `RecetaMedicaController` | Emitir receta opcional al finalizar consulta |
| `CatalogoController` | Admin: CRUD básico de especialidades y médicos |
| `UsuarioAdminController` | Admin: gestión de usuarios del sistema |
| `ReporteController` | Ejecutar consultas de `ReporteDAO` y formatear salida |

#### Lógica de negocio clave (en controladores o `ValidacionHelper`)

| Regla | Dónde aplicarla |
|-------|-----------------|
| Solo admisión registra pacientes y citas | `PacienteController`, `CitaController` |
| Solo enfermería registra triaje | `TriajeController` |
| Solo médico registra atención | `AtencionMedicaController` |
| No atender sin triaje | Verificar `TriajeDAO.existeTriajeParaCita` |
| No triaje si cita no está `POR_TRIAR` | `TriajeController` |
| IMC automático | `TriajeController` al guardar |
| Temperatura entre 30–43 °C, peso > 0 | `ValidacionHelper.validarTriaje(...)` |

---

### 3.4 Vistas Swing (`com.salud.view`)

#### Modificar vistas existentes

| Vista | Mejoras |
|-------|---------|
| `MenuFrame` | Botones condicionados por rol; nuevas entradas a Triaje, Consulta, Historia, Reportes, Catálogos |
| `PacienteForm` | Campos: fecha nacimiento (`JTextField` o `JSpinner`), dirección, tipo seguro (`JComboBox`) |
| `PacienteListFrame` | `JTextField` búsqueda + botones Editar / Desactivar / Ver historia |
| `CitaForm` | `JComboBox` especialidad y médico; label de estado |
| `LoginFrame` | Tooltip en campos; mensaje si credenciales vacías |

#### Crear vistas nuevas

| Vista nueva | Componentes Swing principales |
|-------------|------------------------------|
| `CitaListFrame` | `JTable` + filtros (`JComboBox` estado/especialidad, `JTextField` fecha) + botones Reprogramar / Cancelar |
| `CitaComprobanteDialog` | `JDialog` con datos de la cita y botón Cerrar |
| `TriajeListFrame` | `JTable` pacientes del día en estado `POR_TRIAR` |
| `TriajeForm` | Formulario signos vitales + `JTextArea` motivo consulta |
| `AtencionMedicaListFrame` | Cola de citas `PENDIENTE_ATENCION` del médico logueado |
| `AtencionMedicaForm` | Panel con datos triaje (solo lectura) + campos anamnesis, examen, `JComboBox` CIE-10, plan tratamiento |
| `HistoriaClinicaFrame` | Cabecera paciente + `JTable` atenciones previas + antecedentes editables (tipo sangre, alergias) |
| `RecetaMedicaDialog` | `JTextArea` medicamentos e indicaciones |
| `ReporteFrame` | Filtros de fecha + `JTextArea` resultado + botón Generar |
| `EspecialidadAdminFrame` | CRUD simple en tabla |
| `MedicoAdminFrame` | CRUD simple con combo especialidad |
| `UsuarioAdminFrame` | Alta de usuarios con combo de rol |

---

## 4. Mejoras de experiencia de usuario (UX/UI en Swing)

Aplicar de forma transversal usando `UIStyles` y `JOptionPane`.

### 4.1 Tooltips de ayuda

| Pantalla | Tooltip sugerido |
|----------|------------------|
| DNI | "Ingrese 8 dígitos numéricos sin espacios" |
| Fecha cita | "Formato: yyyy-MM-dd. Ejemplo: 2026-06-29" |
| Hora cita | "Formato 24 h: HH:mm. Ejemplo: 09:30" |
| Presión arterial | "Formato: 120/80" |
| CIE-10 | "Seleccione el código de diagnóstico principal" |

Implementación: `componente.setToolTipText("...")` en cada `*Form` y `*Frame`.

### 4.2 Alertas de confirmación

| Acción | Tipo de diálogo |
|--------|-----------------|
| Desactivar paciente | `JOptionPane.showConfirmDialog` — "¿Confirma desactivar este paciente?" |
| Cancelar cita | Confirmación + `JTextField` o `JInputPane` para motivo |
| Cerrar sesión | "¿Desea salir del sistema?" |
| Finalizar atención médica | "¿Registrar atención? No podrá editarse después." |

### 4.3 Validaciones con JOptionPane

Mantener el patrón actual de `ValidacionHelper` + `JOptionPane.showMessageDialog`:

- Campos vacíos antes de guardar.
- Formatos incorrectos (fecha, hora, DNI).
- Rangos clínicos imposibles en triaje.
- Intento de doble reserva de horario.

### 4.4 Refresco automático de tablas

| Pantalla | Comportamiento |
|----------|----------------|
| `PacienteListFrame` | Método `recargarTabla()` invocado tras guardar, editar o buscar |
| `CitaListFrame` | Recargar tras crear, cancelar o reprogramar |
| `TriajeListFrame` | Recargar tras completar un triaje |
| `AtencionMedicaListFrame` | Recargar tras finalizar consulta |

Patrón recomendado: el controlador expone `cargarDatos(JTable/Frame)` y lo llama después de cada operación exitosa.

### 4.5 Feedback visual adicional (regular)

- Deshabilitar botones `Guardar` / `Registrar` hasta que la validación sea correcta (ya implementado en pacientes y citas; replicar en triaje y atención).
- Mostrar `JOptionPane.INFORMATION_MESSAGE` tras operaciones exitosas.
- Resaltar en `JTable` las filas con citas urgentes o signos vitales anómalos (`DefaultTableCellRenderer`).

---

## 5. Orden de implementación recomendado (paso a paso)

Implementar en este orden para minimizar errores de integración:

| Fase | Entregable | Dependencias |
|:----:|------------|--------------|
| **1** | Script SQL `migracion-v2.sql` + datos semilla | MySQL configurado |
| **2** | Modelos nuevos + ampliación de Paciente, Usuario, Cita | Fase 1 |
| **3** | DAOs ampliados + EspecialidadDAO + MedicoDAO | Fase 2 |
| **4** | Roles en login + MenuFrame dinámico + SesionUsuario | Fase 3 |
| **5** | Edición/búsqueda de pacientes + HistoriaClinica automática | Fase 4 |
| **6** | Citas completas: médico, especialidad, estados, listado, cancelar/reprogramar | Fase 5 |
| **7** | Módulo Triaje (lista + formulario + cambio de estado) | Fase 6 |
| **8** | Módulo Atención Médica + Receta + consulta de historia | Fase 7 |
| **9** | Reportes básicos en pantalla | Fase 8 |
| **10** | Admin de usuarios, especialidades y médicos | Fase 4 (puede paralelizarse) |
| **11** | Pulido UX: tooltips, confirmaciones, refresco de tablas | Transversal |

---

## 6. Matriz de trazabilidad (informe → implementación)

| ID | Requerimiento / Caso de uso | Fase del plan |
|----|----------------------------|:-------------:|
| RF01 | Registro digital de pacientes | 5 |
| RF02 | Agendar citas médicas | 6 |
| RF03 | Historiales clínicos electrónicos | 8 |
| RF04 | Consulta de citas por fecha y especialidad | 6 |
| RF05 | Reportes de atención | 9 |
| RF06 | Actualización de datos del paciente | 5 |
| RF07 | Integración de áreas | 4–8 (menú por rol + flujo de estados) |
| RF08 | Autenticación de usuarios | 4 |
| RF09 | Búsqueda rápida de pacientes | 5 |
| RF10 | Historial de atenciones médicas | 8 |
| CU01 | Registrar paciente | 5 |
| CU02 | Gestionar citas médicas | 6 |
| CU03 | Realizar triaje | 7 |
| CU04 | Registrar atención médica | 8 |
| CU05 | Consultar historia clínica | 8 |
| CU06 | Emitir receta médica | 8 |
| CU07 | Autenticar usuario | 4 |

---

## 7. Fuera de alcance (nivel regular — no implementar ahora)

Para mantener el proyecto acotado y entregable:

- Integración con SIS externo, DNI electrónico o código de barras.
- Catálogo CIE-10 completo (miles de códigos).
- Exportación PDF profesional (JasperReports, iText).
- Aplicación web o API REST.
- Cifrado avanzado de contraseñas (bcrypt) — recomendable en fase futura.
- Copias de seguridad automáticas (RNF06).
- Multi-sede o despliegue en nube.

---

## 8. Criterios de aceptación final

El sistema se considerará alineado con el informe en **nivel regular** cuando:

1. Un usuario de **admisión** registre pacientes, programe/cancele/reprograme citas y consulte citas por filtros.
2. Un usuario de **enfermería** vea la cola del día y registre triaje con validaciones.
3. Un usuario **médico** atienda pacientes triados, registre diagnóstico CIE-10 y emita receta.
4. Cualquier rol autorizado consulte la **historia clínica** de un paciente.
5. Un **administrador** gestione usuarios, especialidades y médicos.
6. El sistema genere al menos **3 reportes operativos** en pantalla.
7. No existan credenciales en el código fuente; todo use `config.properties`.
8. El proyecto compile y ejecute con `Main.java` sin dependencias de Spring u otros frameworks.

---

*Documento generado para revisión previa a la implementación. No modifica código fuente.*
