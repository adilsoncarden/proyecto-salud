package com.salud.controller;

import com.salud.model.Paciente;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

/**
 * Métodos reutilizables de validación para formularios del sistema.
 */
public final class ValidacionHelper {

    private static final Pattern SOLO_NUMEROS = Pattern.compile("\\d+");
    private static final Pattern SOLO_LETRAS = Pattern.compile("[a-zA-ZÁÉÍÓÚáéíóúÑñ\\s]+");

    private ValidacionHelper() {
    }

    public static String validarLogin(String usuario, String password) {
        if (usuario == null || usuario.isEmpty()) {
            return "El usuario no puede estar vacío.";
        }
        if (password == null || password.isEmpty()) {
            return "La contraseña no puede estar vacía.";
        }
        return null;
    }

    public static String validarDni(String dni) {
        if (dni == null || dni.isEmpty()) {
            return "El DNI es obligatorio.";
        }
        if (!SOLO_NUMEROS.matcher(dni).matches()) {
            return "DNI inválido: solo se permiten números.";
        }
        if (dni.length() != 8) {
            return "DNI inválido: debe tener exactamente 8 dígitos.";
        }
        return null;
    }

    public static String validarNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            return "El nombre es obligatorio.";
        }
        if (!SOLO_LETRAS.matcher(nombre).matches()) {
            return "El nombre solo debe contener letras.";
        }
        return null;
    }

    public static String validarApellido(String apellido) {
        if (apellido == null || apellido.isEmpty()) {
            return "El apellido es obligatorio.";
        }
        return null;
    }

    public static String validarTelefono(String telefono) {
        if (telefono == null || telefono.isEmpty()) {
            return null;
        }
        if (!SOLO_NUMEROS.matcher(telefono).matches()) {
            return "El teléfono solo debe contener números.";
        }
        if (telefono.length() > 9) {
            return "El teléfono debe tener máximo 9 dígitos.";
        }
        return null;
    }

    public static String validarPaciente(String dni, String nombre, String apellido, String telefono) {
        String error = validarDni(dni);
        if (error != null) {
            return error;
        }
        error = validarNombre(nombre);
        if (error != null) {
            return error;
        }
        error = validarApellido(apellido);
        if (error != null) {
            return error;
        }
        return validarTelefono(telefono);
    }

    public static boolean esPacienteValido(String dni, String nombre, String apellido, String telefono) {
        return validarPaciente(dni, nombre, apellido, telefono) == null;
    }

    public static String validarFechaCita(String fechaTexto) {
        if (fechaTexto == null || fechaTexto.isEmpty()) {
            return "Ingrese la fecha de la cita.";
        }
        try {
            LocalDate fecha = LocalDate.parse(fechaTexto, DateTimeFormatter.ISO_LOCAL_DATE);
            if (fecha.isBefore(LocalDate.now())) {
                return "No se permiten fechas pasadas.";
            }
            return null;
        } catch (DateTimeParseException ex) {
            return "Formato de fecha inválido. Use yyyy-MM-dd.";
        }
    }

    public static String validarHora(String horaTexto) {
        if (horaTexto == null || horaTexto.isEmpty()) {
            return "Ingrese la hora de la cita.";
        }
        try {
            LocalTime.parse(horaTexto, DateTimeFormatter.ofPattern("HH:mm"));
            return null;
        } catch (DateTimeParseException ex) {
            return "Formato de hora inválido. Use HH:mm (ej: 09:30).";
        }
    }

    public static String validarPacienteSeleccionado(Paciente paciente) {
        if (paciente == null) {
            return "Debe seleccionar un paciente.";
        }
        return null;
    }

    public static String validarCita(String fechaTexto, String horaTexto, Paciente paciente) {
        String error = validarFechaCita(fechaTexto);
        if (error != null) {
            return error;
        }
        error = validarHora(horaTexto);
        if (error != null) {
            return error;
        }
        return validarPacienteSeleccionado(paciente);
    }

    public static boolean esCitaValida(String fechaTexto, String horaTexto, Paciente paciente) {
        return validarCita(fechaTexto, horaTexto, paciente) == null;
    }
}
