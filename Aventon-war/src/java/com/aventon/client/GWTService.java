/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aventon.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import java.util.ArrayList;

/**
 *
 * @author Andres
 */
@RemoteServiceRelativePath("gwtservice")
public interface GWTService extends RemoteService {

    public ArrayList<String> aventones(String ruta2);

    public String crearUsuario(String req) throws Exception;

    public String consultarUsuario(String id) throws Exception;

    public String crearCarro(String vehiculo) throws Exception;

    public String consultarCarro(String placa) throws Exception;

    public String crearRuta(String ruta) throws Exception;

    public String crearPuntoIntermedio(String punto, int cont) throws Exception;

    public void crearPuntoRuta(String punto) throws Exception;

    public boolean iniciarSesion(String id, String pass) throws Exception;

    public ArrayList<String> listarCarro(String ced) throws Exception;

    public ArrayList<String> listarRutas(String ced) throws Exception;

    public String crearAventon(String aventon) throws Exception;

    public String pedirCupo(String aventon) throws Exception;

    public String calificarCupo(String aventon) throws Exception;

    public ArrayList<String> listarPuntosI();

    public ArrayList<String> consultarRutasUsuario(String ced);

    public ArrayList<String> puntosRuta(String ruta);

    public ArrayList<String> cuposU(String user);

    public String nombreRuta(Integer ruta);

    public ArrayList<String> RutaPuntos(Integer pntI, Integer pntF);

    public void eliminarCupo(String cupo);

    public ArrayList<String> cupos(String aventon);

    public void guardarCupo(String cupo);

    public String consultarAventon(String aventon);

    public static class App {

        private static GWTServiceAsync instance = null;

        public static synchronized GWTServiceAsync getInstance() {
            if (instance == null) {
                instance = (GWTServiceAsync) GWT.create(GWTService.class);
                String urlService = GWT.getModuleBaseURL() + "gwtservice";
                System.out.println(urlService);
                ((ServiceDefTarget) instance).setServiceEntryPoint(urlService);
            }
            return instance;
        }
    }
}
