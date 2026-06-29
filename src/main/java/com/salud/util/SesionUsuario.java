package com.salud.util;

import com.salud.model.Usuario;

/**
 * Almacena el usuario autenticado durante la sesión activa.
 */
public final class SesionUsuario {

    private static Usuario usuarioActual;

    private SesionUsuario() {
    }

    public static void iniciar(Usuario usuario) {
        usuarioActual = usuario;
    }

    public static void cerrar() {
        usuarioActual = null;
    }

    public static Usuario getUsuario() {
        return usuarioActual;
    }

    public static boolean haySesion() {
        return usuarioActual != null;
    }

    public static String getRol() {
        return usuarioActual != null ? usuarioActual.getRol() : null;
    }
}
