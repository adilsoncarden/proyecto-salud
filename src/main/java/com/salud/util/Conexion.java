package com.salud.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Clase utilitaria para gestionar la conexión JDBC con MySQL.
 * Las credenciales se cargan desde {@code config.properties}.
 */
public class Conexion {

    private static final Properties PROPIEDADES = cargarPropiedades();

    private Conexion() {
        // Evita instanciación
    }

    private static Properties cargarPropiedades() {
        Properties propiedades = new Properties();
        try (InputStream entrada = abrirArchivoConfig()) {
            propiedades.load(entrada);
        } catch (IOException e) {
            throw new ExceptionInInitializerError(
                    "No se pudo cargar config.properties. "
                            + "Copie config.properties.example a config.properties y complete sus credenciales.");
        }
        return propiedades;
    }

    private static InputStream abrirArchivoConfig() throws IOException {
        java.io.File archivoRaiz = new java.io.File("config.properties");
        if (archivoRaiz.isFile()) {
            return new FileInputStream(archivoRaiz);
        }

        InputStream recurso = Conexion.class.getClassLoader().getResourceAsStream("config.properties");
        if (recurso != null) {
            return recurso;
        }

        throw new IOException("Archivo config.properties no encontrado en la raíz del proyecto ni en recursos.");
    }

    /**
     * Obtiene una conexión activa a la base de datos.
     */
    public static Connection getConexion() throws SQLException {
        String url = PROPIEDADES.getProperty("db.url");
        String usuario = PROPIEDADES.getProperty("db.user");
        String contrasena = PROPIEDADES.getProperty("db.password");

        return DriverManager.getConnection(url, usuario, contrasena);
    }
}
