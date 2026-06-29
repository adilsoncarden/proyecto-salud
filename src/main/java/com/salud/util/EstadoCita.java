package com.salud.util;

/**
 * Estados del ciclo de vida de una cita médica.
 */
public final class EstadoCita {

    public static final String PROGRAMADA = "PROGRAMADA";
    public static final String POR_TRIAR = "POR_TRIAR";
    public static final String PENDIENTE_ATENCION = "PENDIENTE_ATENCION";
    public static final String ATENDIDA = "ATENDIDA";
    public static final String CANCELADA = "CANCELADA";

    private EstadoCita() {
    }
}
