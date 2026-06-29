package com.salud.util;

/**
 * Roles del sistema según el informe de análisis.
 */
public final class RolConstantes {

    public static final String ADMINISTRADOR = "ADMINISTRADOR";
    public static final String ADMISION = "ADMISION";
    public static final String ENFERMERIA = "ENFERMERIA";
    public static final String MEDICO = "MEDICO";

    private RolConstantes() {
    }

    public static boolean esAdmin(String rol) {
        return ADMINISTRADOR.equals(rol);
    }

    public static boolean esAdmision(String rol) {
        return ADMISION.equals(rol);
    }

    public static boolean esEnfermeria(String rol) {
        return ENFERMERIA.equals(rol);
    }

    public static boolean esMedico(String rol) {
        return MEDICO.equals(rol);
    }
}
