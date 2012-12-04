/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aventon.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.ArrayList;

/**
 *
 * @author Andres
 */
public interface GWTServiceAsync {

    public void crearUsuario(String req, AsyncCallback<String> asyncCallback);

    public void crearCarro(String vehiculo, AsyncCallback<String> asyncCallback);

    public void crearRuta(String ruta, AsyncCallback<String> asyncCallback);

    public void crearPuntoIntermedio(String punto, int cont, AsyncCallback<String> asyncCallback);

    public void crearPuntoRuta(String punto, AsyncCallback<Void> asyncCallback);

    public void consultarUsuario(String id, AsyncCallback<String> asyncCallback);

    

    public void consultarCarro(String placa, AsyncCallback<String> asyncCallback);

    public void listarCarro(String ced, AsyncCallback<ArrayList<String>> asyncCallback);

    public void crearAventon(String aventon, AsyncCallback<String> asyncCallback);

    public void pedirCupo(String aventon, AsyncCallback<String> asyncCallback);

    public void calificarCupo(String aventon, AsyncCallback<String> asyncCallback);

    public void iniciarSesion(String id, String pass, AsyncCallback<java.lang.Boolean> asyncCallback);

    
    public void listarRutas(String ced, AsyncCallback<ArrayList<String>> asyncCallback);

    public void puntosRuta(String ruta, AsyncCallback<ArrayList<String>> asyncCallback);

    public void consultarRutasUsuario(String ced, AsyncCallback<ArrayList<String>> asyncCallback);

    public void listarPuntosI(AsyncCallback<ArrayList<String>> asyncCallback);

    public void RutaPuntos(Integer pntI, Integer pntF, AsyncCallback<ArrayList<String>> asyncCallback);

    public void aventones(String ruta2, AsyncCallback<ArrayList<String>> asyncCallback);

    public void guardarCupo(String cupo, AsyncCallback<Void> asyncCallback);

    public void consultarAventon(String aventon, AsyncCallback<String> asyncCallback);

    public void cupos(String aventon, AsyncCallback<ArrayList<String>> asyncCallback);

    public void eliminarCupo(String cupo, AsyncCallback<Void> asyncCallback);

    public void cuposU(String user, AsyncCallback<ArrayList<String>> asyncCallback);

    public void nombreRuta(Integer ruta, AsyncCallback<String> asyncCallback);

    
}
