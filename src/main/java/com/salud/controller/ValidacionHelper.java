package com.salud.controller;

import com.salud.model.Paciente;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public final class ValidacionHelper {

    private static final Pattern SOLO_NUMEROS = Pattern.compile("\\d+");
    private static final Pattern SOLO_LETRAS = Pattern.compile("[a-zA-ZÁÉÍÓÚáéíóúÑñ\\s]+");
    private static final Pattern PRESION_ARTERIAL = Pattern.compile("\\d{2,3}/\\d{2,3}");

    private ValidacionHelper() {
    }

    public static boolean esVacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }

    public static String validarLogin(String usuario, String password) {
        if (esVacio(usuario)) {
            return "El usuario no puede estar vacío.";
        }
        if (esVacio(password)) {
            return "La contraseña no puede estar vacía.";
        }
        return null;
    }

    public static String validarDni(String dni) {
        if (esVacio(dni)) {
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
        if (esVacio(nombre)) {
            return "El nombre es obligatorio.";
        }
        if (!SOLO_LETRAS.matcher(nombre).matches()) {
            return "El nombre solo debe contener letras.";
        }
        return null;
    }

    public static String validarApellido(String apellido) {
        if (esVacio(apellido)) {
            return "El apellido es obligatorio.";
        }
        return null;
    }

    public static String validarTelefono(String telefono) {
        if (esVacio(telefono)) {
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

    public static String validarFechaNacimiento(String fechaTexto) {
        if (esVacio(fechaTexto)) {
            return null;
        }
        try {
            LocalDate.parse(fechaTexto, DateTimeFormatter.ISO_LOCAL_DATE);
            return null;
        } catch (DateTimeParseException ex) {
            return "Fecha de nacimiento inválida. Use yyyy-MM-dd.";
        }
    }

    public static String validarPaciente(String dni, String nombre, String apellido, String telefono,
            String fechaNacimiento, String tipoSeguro) {
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
        error = validarTelefono(telefono);
        if (error != null) {
            return error;
        }
        error = validarFechaNacimiento(fechaNacimiento);
        if (error != null) {
            return error;
        }
        if (esVacio(tipoSeguro)) {
            return "Seleccione el tipo de seguro.";
        }
        return null;
    }

    public static String validarFechaCita(String fechaTexto) {
        if (esVacio(fechaTexto)) {
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
        if (esVacio(horaTexto)) {
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

    public static String validarCita(String fechaTexto, String horaTexto, Paciente paciente,
            Integer medicoId, Integer especialidadId) {
        String error = validarFechaCita(fechaTexto);
        if (error != null) {
            return error;
        }
        error = validarHora(horaTexto);
        if (error != null) {
            return error;
        }
        error = validarPacienteSeleccionado(paciente);
        if (error != null) {
            return error;
        }
        if (medicoId == null || medicoId <= 0) {
            return "Debe seleccionar un médico.";
        }
        if (especialidadId == null || especialidadId <= 0) {
            return "Debe seleccionar una especialidad.";
        }
        return null;
    }

    public static String validarTriaje(String peso, String talla, String presion, String temperatura,
            String motivo) {
        if (esVacio(peso) || esVacio(talla) || esVacio(presion) || esVacio(temperatura) || esVacio(motivo)) {
            return "Complete todos los campos obligatorios del triaje.";
        }
        try {
            double pesoKg = Double.parseDouble(peso);
            double tallaM = Double.parseDouble(talla);
            double temp = Double.parseDouble(temperatura);
            if (pesoKg <= 0 || pesoKg > 500) {
                return "Peso fuera de rango lógico.";
            }
            if (tallaM <= 0 || tallaM > 3) {
                return "Talla debe estar en metros (ej: 1.70).";
            }
            if (temp < 30 || temp > 43) {
                return "Temperatura fuera de rango lógico (30-43 °C).";
            }
            if (!PRESION_ARTERIAL.matcher(presion.trim()).matches()) {
                return "Presión arterial inválida. Use formato 120/80.";
            }
        } catch (NumberFormatException e) {
            return "Los valores numéricos del triaje son inválidos.";
        }
        return null;
    }

    public static String validarAtencion(String codigoCie10, String diagnostico, String anamnesis) {
        if (esVacio(codigoCie10)) {
            return "Seleccione un código CIE-10.";
        }
        if (esVacio(diagnostico)) {
            return "Ingrese el diagnóstico detallado.";
        }
        if (esVacio(anamnesis)) {
            return "Ingrese la anamnesis.";
        }
        return null;
    }

    public static String validarReceta(String medicamentos, String indicaciones) {
        if (esVacio(medicamentos) || esVacio(indicaciones)) {
            return "Complete medicamentos e indicaciones de la receta.";
        }
        return null;
    }

    public static double calcularImc(double pesoKg, double tallaM) {
        if (tallaM <= 0) {
            return 0;
        }
        return Math.round((pesoKg / (tallaM * tallaM)) * 100.0) / 100.0;
    }
}
