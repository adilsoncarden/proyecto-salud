-- =============================================================================
-- Migración v2 — Centro de Salud (esquema completo + datos de prueba)
-- Ejecutar: mysql -u root -p < sql/migracion-v2.sql
-- =============================================================================

CREATE DATABASE IF NOT EXISTS salud_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE salud_db;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS receta_medica;
DROP TABLE IF EXISTS atencion_medica;
DROP TABLE IF EXISTS triaje;
DROP TABLE IF EXISTS historia_clinica;
DROP TABLE IF EXISTS cita;
DROP TABLE IF EXISTS paciente;
DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS medico;
DROP TABLE IF EXISTS especialidad;
DROP TABLE IF EXISTS catalogo_cie10;

SET FOREIGN_KEY_CHECKS = 1;

-- -----------------------------------------------------------------------------
-- Catálogos
-- -----------------------------------------------------------------------------

CREATE TABLE especialidad (
    id            INT AUTO_INCREMENT PRIMARY KEY,
    nombre        VARCHAR(100) NOT NULL UNIQUE,
    descripcion   VARCHAR(255) NULL,
    activo        TINYINT(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB;

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
) ENGINE=InnoDB;

CREATE TABLE catalogo_cie10 (
    codigo      VARCHAR(10) PRIMARY KEY,
    descripcion VARCHAR(255) NOT NULL
) ENGINE=InnoDB;

-- -----------------------------------------------------------------------------
-- Seguridad
-- -----------------------------------------------------------------------------

CREATE TABLE usuario (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    username   VARCHAR(50) NOT NULL UNIQUE,
    password   VARCHAR(100) NOT NULL,
    rol        VARCHAR(30) NOT NULL DEFAULT 'ADMINISTRADOR',
    nombres    VARCHAR(100) NULL,
    apellidos  VARCHAR(100) NULL,
    id_medico  INT NULL,
    activo     TINYINT(1) NOT NULL DEFAULT 1,
    CONSTRAINT fk_usuario_medico
        FOREIGN KEY (id_medico) REFERENCES medico(id)
) ENGINE=InnoDB;

-- -----------------------------------------------------------------------------
-- Pacientes y clínica
-- -----------------------------------------------------------------------------

CREATE TABLE paciente (
    id               INT AUTO_INCREMENT PRIMARY KEY,
    dni              VARCHAR(8) NOT NULL UNIQUE,
    nombre           VARCHAR(100) NOT NULL,
    apellido         VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE NULL,
    telefono         VARCHAR(15) NULL,
    direccion        VARCHAR(255) NULL,
    tipo_seguro      VARCHAR(20) NOT NULL DEFAULT 'SIS',
    activo           TINYINT(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB;

CREATE TABLE historia_clinica (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    id_paciente     INT NOT NULL UNIQUE,
    fecha_creacion  DATE NOT NULL,
    tipo_sangre     VARCHAR(5) NULL,
    alergias        TEXT NULL,
    CONSTRAINT fk_historia_paciente
        FOREIGN KEY (id_paciente) REFERENCES paciente(id)
) ENGINE=InnoDB;

CREATE TABLE cita (
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    paciente_id         INT NOT NULL,
    id_medico           INT NOT NULL,
    id_especialidad     INT NOT NULL,
    fecha               DATE NOT NULL,
    hora                TIME NOT NULL,
    estado              VARCHAR(30) NOT NULL DEFAULT 'PROGRAMADA',
    consultorio         VARCHAR(50) NULL,
    motivo_cancelacion  VARCHAR(255) NULL,
    CONSTRAINT fk_cita_paciente
        FOREIGN KEY (paciente_id) REFERENCES paciente(id),
    CONSTRAINT fk_cita_medico
        FOREIGN KEY (id_medico) REFERENCES medico(id),
    CONSTRAINT fk_cita_especialidad
        FOREIGN KEY (id_especialidad) REFERENCES especialidad(id)
) ENGINE=InnoDB;

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
) ENGINE=InnoDB;

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
) ENGINE=InnoDB;

CREATE TABLE receta_medica (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    id_atencion     INT NOT NULL UNIQUE,
    fecha_emision   DATE NOT NULL,
    medicamentos    TEXT NOT NULL,
    indicaciones    TEXT NOT NULL,
    CONSTRAINT fk_receta_atencion
        FOREIGN KEY (id_atencion) REFERENCES atencion_medica(id)
) ENGINE=InnoDB;

CREATE INDEX idx_cita_fecha_estado ON cita (fecha, estado);
CREATE INDEX idx_paciente_apellido ON paciente (apellido, nombre);
CREATE INDEX idx_atencion_fecha ON atencion_medica (fecha_atencion);

-- -----------------------------------------------------------------------------
-- Datos semilla
-- -----------------------------------------------------------------------------

INSERT INTO especialidad (nombre, descripcion) VALUES
('Medicina General', 'Atención primaria'),
('Pediatría', 'Atención infantil'),
('Ginecología', 'Salud de la mujer'),
('Odontología', 'Salud bucal'),
('Laboratorio', 'Exámenes de laboratorio');

INSERT INTO medico (dni, nombres, apellidos, id_especialidad, consultorio) VALUES
('45678901', 'Carlos', 'Mendoza', 1, 'C-101'),
('45678902', 'Ana', 'Torres', 2, 'C-102'),
('45678903', 'Luis', 'Ramírez', 3, 'C-103');

INSERT INTO catalogo_cie10 (codigo, descripcion) VALUES
('J00', 'Rinofaringitis aguda'),
('J06.9', 'Infección aguda de vías respiratorias superiores'),
('I10', 'Hipertensión esencial'),
('E11.9', 'Diabetes mellitus tipo 2'),
('M54.5', 'Lumbago no especificado'),
('R51', 'Cefalea'),
('K29.7', 'Gastritis no especificada'),
('J02.9', 'Faringitis aguda no especificada');

INSERT INTO usuario (username, password, rol, nombres, apellidos, id_medico) VALUES
('admin', 'admin123', 'ADMINISTRADOR', 'Sistema', 'Admin', NULL),
('admision', 'admision123', 'ADMISION', 'María', 'López', NULL),
('enfermeria', 'enfermeria123', 'ENFERMERIA', 'Rosa', 'Vega', NULL),
('medico', 'medico123', 'MEDICO', 'Carlos', 'Mendoza', 1);

INSERT INTO paciente (dni, nombre, apellido, fecha_nacimiento, telefono, direccion, tipo_seguro) VALUES
('72345678', 'Juan', 'Pérez', '1990-05-15', '987654321', 'Av. Los Olivos 123', 'SIS'),
('73456789', 'Lucía', 'García', '1985-11-20', '912345678', 'Jr. Las Flores 45', 'PARTICULAR');

INSERT INTO historia_clinica (id_paciente, fecha_creacion, tipo_sangre, alergias) VALUES
(1, CURDATE(), 'O+', 'Ninguna'),
(2, CURDATE(), 'A+', 'Penicilina');
