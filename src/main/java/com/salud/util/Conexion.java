package com.salud.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase utilitaria para gestionar la conexión JDBC con MySQL.
 */
public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/salud_db";
    private static final String USER = "adilson";
    private static final String PASSWORD = "Adilson@2004#Secure";

    private Conexion() {
        // Evita instanciación
    }

    /**
     * Obtiene una conexión activa a la base de datos.
     */
    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
