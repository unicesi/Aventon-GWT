/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aventon.client.util;

import com.google.gwt.user.client.Window;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Andres
 */
public class Parser implements Serializable {

    public static String Usuario2String(String cedula, String nombres, String apellidos, char genero, Date fechaNacimiento, String email, String telefono, String estadoCivil, char tipo, int semestre, String contraseña) {
        StringBuilder resp = new StringBuilder();
        resp.append(cedula);
        resp.append(";");
        resp.append(nombres);
        resp.append(";");
        resp.append(apellidos);
        resp.append(";");
        resp.append(genero);
        resp.append(";");
        resp.append(fechaNacimiento.getTime());
        resp.append(";");
        resp.append(email);
        resp.append(";");
        resp.append(telefono);
        resp.append(";");
        resp.append(estadoCivil);
        resp.append(";");
        resp.append(tipo);
        resp.append(";");
        resp.append(semestre);
        resp.append(";");
        resp.append(contraseña);
        return resp.toString();
    }

    public static String vehiculo2String(String plac, String mod, String marc, String col, String tCombus, String AC, String usuario) {
        StringBuilder resp = new StringBuilder();
        resp.append(plac);
        resp.append(";");
        resp.append(mod);
        resp.append(";");
        resp.append(marc);
        resp.append(";");
        resp.append(col);
        resp.append(";");
        resp.append(tCombus);
        resp.append(";");
        resp.append(AC);
        resp.append(";");
        resp.append(usuario);
        return resp.toString();
    }

    public static String ruta2String(String cod, String des, String usu) {
        String resp = cod + ";" + des + ";" + usu;
        return resp;
    }

    public static String puntoRutaPK2String(int codigoRuta, int codigoPuntointermedio) {
        String resp = (new Integer(codigoRuta)).toString() + ";" + (new Integer(codigoPuntointermedio)).toString();
        return resp;
    }

    public static String puntoRuta2String(int codigoRuta, int codigoPuntointermedio, int indice, char puntoFin) {
        String prpk = puntoRutaPK2String(codigoRuta, codigoPuntointermedio);
        String resp = prpk + ";" + indice + ";" + puntoFin;
        return resp;
    }

    public static String puntoIntermedio2String(Integer codigo, String direccion, String detalle, double altitud, double longitud) {
        StringBuilder resp = new StringBuilder();
        resp.append(codigo.toString());
        resp.append(";");
        resp.append(direccion);
        resp.append(";");
        resp.append(detalle);
        resp.append(";");
        resp.append(altitud);
        resp.append(";");
        resp.append(longitud);
        return resp.toString();
    }

    public static String carrera2String(String codigo, String nombre) {
        String resp = codigo + ";" + nombre;
        return resp;
    }

    public static String aventonPK2String(Date fechaSalida, int codigoRuta) {
        StringBuilder resp = new StringBuilder();
        resp.append(fechaSalida.getTime());
        resp.append(";");
        resp.append(codigoRuta);
        return resp.toString();
    }

    public static String aventon2String(Date fechaSalida, int codigoRuta, String codigo, Date fechaLlegada, int numCupos, boolean desvios, String descripcion, String placa) {
        String apk = aventonPK2String(fechaSalida, codigoRuta);
        StringBuilder resp = new StringBuilder();
        resp.append(apk);
        resp.append(";");
        resp.append(codigo);
        resp.append(";");
        resp.append(fechaLlegada.getTime());
        resp.append(";");
        resp.append(numCupos);
        resp.append(";");
        resp.append(desvios);
        resp.append(";");
        resp.append(descripcion);
        resp.append(";");
        resp.append(placa);
        return resp.toString();
    }

    public static String cupoPK2String(Date fechaSalida, int codigoRuta, String cedulaUsuario) {
        StringBuilder resp = new StringBuilder();
        resp.append(fechaSalida.getTime());
        resp.append(";");
        resp.append(codigoRuta);
        resp.append(";");
        resp.append(cedulaUsuario);
        return resp.toString();
    }

    public static String cupo2String(Date fechaSalida, int codigoRuta, String cedulaUsuario, Integer amabil, Integer comod, Integer segur, Integer puntu, String come) {
        try {
            StringBuilder resp = new StringBuilder();
            String cpk = cupoPK2String(fechaSalida, codigoRuta, cedulaUsuario);
            resp.append(cpk);
            resp.append(";");
            if (amabil == null) {
                resp.append("-");
            } else {
                resp.append(amabil.toString());
            }
            resp.append(";");

            if (comod == null) {
                resp.append("-");
            } else {
                resp.append(comod.toString());
            }
            resp.append(";");

            if (segur == null) {
                resp.append("-");
            } else {
                resp.append(segur.toString());
            }
            resp.append(";");
            if (puntu == null) {
                resp.append("-");
            } else {
                resp.append(puntu.toString());
            }
            resp.append(";");
            if (come == null) {
                resp.append("-");
            } else {
                resp.append(come);
            }

            return resp.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
