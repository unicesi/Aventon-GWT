/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aventon.server;

import beans.GestionarCarrosRemote;
import beans.GestionarRutasRemote;
import beans.GestionarServiciosRemote;
import beans.GestionarUsuariosRemote;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.aventon.client.GWTService;
import com.google.gwt.user.client.Window;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.ServiceRequest;
import modelo.ServiceResponse;

/**
 *
 * @author Andres
 */
public class GWTServiceImpl extends RemoteServiceServlet implements GWTService {

    @Override
    public String crearUsuario(String req) throws Exception {
        GestionarUsuariosRemote service = (GestionarUsuariosRemote) ServiceLocator.getInstance().getRemoteSession(GestionarUsuariosRemote.class.getCanonicalName());
        ServiceRequest request = new ServiceRequest();
        request.setRequest(req);
        ServiceResponse response = service.crearUsuario(request);
        return response.getResponse();
    }

    @Override
    public String consultarUsuario(String id) throws Exception {
        GestionarUsuariosRemote service = (GestionarUsuariosRemote) ServiceLocator.getInstance().getRemoteSession(GestionarUsuariosRemote.class.getCanonicalName());
        ServiceRequest request = new ServiceRequest();
        request.setRequest(id);
        ServiceResponse response = service.consultarUsuario(request);
        return response.getResponse();
    }

    @Override
    public String crearCarro(String vehiculo) throws Exception {
        GestionarCarrosRemote service = (GestionarCarrosRemote) ServiceLocator.getInstance().getRemoteSession(GestionarCarrosRemote.class.getCanonicalName());
        ServiceRequest carro = new ServiceRequest();
        carro.setRequest(vehiculo);
        ServiceResponse response = service.crearCarro(carro);
        return response.getResponse();

    }

    @Override
    public String crearRuta(String ruta) throws Exception {
        GestionarRutasRemote service = (GestionarRutasRemote) ServiceLocator.getInstance().getRemoteSession(GestionarRutasRemote.class.getCanonicalName());
        ServiceRequest request = new ServiceRequest();
        request.setRequest(ruta);
        ServiceResponse resp = service.crearRuta(request);

        return resp.getResponse();
    }

    @Override
    public String crearPuntoIntermedio(String punto, int cont) throws Exception {
        GestionarRutasRemote service = (GestionarRutasRemote) ServiceLocator.getInstance().getRemoteSession(GestionarRutasRemote.class.getCanonicalName());
        ServiceRequest request = new ServiceRequest();
        request.setRequest(punto);
        ServiceResponse resp = service.crearPuntoIntermedio(request);
        return cont + ";" + resp.getResponse();
    }

    @Override
    public void crearPuntoRuta(String punto) throws Exception {
        GestionarRutasRemote service = (GestionarRutasRemote) ServiceLocator.getInstance().getRemoteSession(GestionarRutasRemote.class.getCanonicalName());
        ServiceRequest request = new ServiceRequest();
        request.setRequest(punto);
        service.crearPuntoRuta(request);
    }

    @Override
    public boolean iniciarSesion(String id, String pass) throws Exception {
        GestionarUsuariosRemote service = (GestionarUsuariosRemote) ServiceLocator.getInstance().getRemoteSession(GestionarUsuariosRemote.class.getCanonicalName());
        ServiceRequest request = new ServiceRequest();
        request.setRequest(id + ";" + pass);
        ServiceResponse response = service.iniciarSesion(request);
        return response.getResponse().equals("correcto");
    }

    @Override
    public String consultarCarro(String placa) throws Exception {
        GestionarCarrosRemote service = (GestionarCarrosRemote) ServiceLocator.getInstance().getRemoteSession(GestionarCarrosRemote.class.getCanonicalName());
        ServiceRequest placaR = new ServiceRequest();
        placaR.setRequest(placa);
        ServiceResponse response = service.consultarCarro(placaR);
        return response.getResponse();
    }

    @Override
    public ArrayList<String> listarCarro(String ced) throws Exception {
        GestionarCarrosRemote service = (GestionarCarrosRemote) ServiceLocator.getInstance().getRemoteSession(GestionarCarrosRemote.class.getCanonicalName());
        ServiceRequest cedu = new ServiceRequest();
        cedu.setRequest(ced);
        ArrayList<String> miLista = service.listarCarros(ced);
        return miLista;
    }

    @Override
    public String crearAventon(String aventon) throws Exception {
        GestionarServiciosRemote service = (GestionarServiciosRemote) ServiceLocator.getInstance().getRemoteSession(GestionarServiciosRemote.class.getCanonicalName());
        ServiceRequest request = new ServiceRequest();
        request.setRequest(aventon);
        ServiceResponse response = service.crearAventon(request);
        return response.getResponse();
    }

    @Override
    public String pedirCupo(String aventon) throws Exception {
        Window.alert("ENTRA");
        GestionarServiciosRemote service = (GestionarServiciosRemote) ServiceLocator.getInstance().getRemoteSession(GestionarServiciosRemote.class.getCanonicalName());
        ServiceRequest request = new ServiceRequest();
        request.setRequest(aventon);
        ServiceResponse response = service.pedirCupo(request);
        return response.getResponse();
    }

    @Override
    public String calificarCupo(String aventon) throws Exception {
        GestionarServiciosRemote service = (GestionarServiciosRemote) ServiceLocator.getInstance().getRemoteSession(GestionarServiciosRemote.class.getCanonicalName());
        ServiceRequest request = new ServiceRequest();
        request.setRequest(aventon);
        ServiceResponse response = service.calificarCupo(request);
        return response.getResponse();
    }

    @Override
    public ArrayList<String> listarRutas(String ced) throws Exception {
        GestionarRutasRemote service = (GestionarRutasRemote) ServiceLocator.getInstance().getRemoteSession(GestionarRutasRemote.class.getCanonicalName());
        ServiceRequest cedu = new ServiceRequest();
        cedu.setRequest(ced);
        ArrayList<String> miLista = service.consultarRutasUsuario(ced);
        return miLista;
    }

    public ArrayList<String> consultarRutasUsuario(String ced) {
        try {
            GestionarRutasRemote service = (GestionarRutasRemote) ServiceLocator.getInstance().getRemoteSession(GestionarRutasRemote.class.getCanonicalName());
            ServiceRequest cedu = new ServiceRequest();
            cedu.setRequest(ced);
            ArrayList<String> miLista = service.consultarRutasUsuario(ced);

            return miLista;

        } catch (Exception ex) {
            Logger.getLogger(GWTServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<String> puntosRuta(String ruta) {

        try {
            GestionarRutasRemote service = (GestionarRutasRemote) ServiceLocator.getInstance().getRemoteSession(GestionarRutasRemote.class.getCanonicalName());

            ArrayList<String> miLista = service.puntosRuta(new Integer(ruta));

            return miLista;

        } catch (Exception ex) {
            Logger.getLogger(GWTServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<String> listarPuntosI() {
        try {
            GestionarRutasRemote service = (GestionarRutasRemote) ServiceLocator.getInstance().getRemoteSession(GestionarRutasRemote.class.getCanonicalName());

            ArrayList<String> miLista = service.listarPuntosI();

            return miLista;

        } catch (Exception ex) {
            Logger.getLogger(GWTServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<String> RutaPuntos(Integer pntI, Integer pntF) {
        try {
            GestionarRutasRemote service = (GestionarRutasRemote) ServiceLocator.getInstance().getRemoteSession(GestionarRutasRemote.class.getCanonicalName());

            ArrayList<String> miLista = service.RutaPuntos(pntI, pntF);

            return miLista;

        } catch (Exception ex) {
            Logger.getLogger(GWTServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<String> aventones(String ruta2) {
        try {
            GestionarServiciosRemote service = (GestionarServiciosRemote) ServiceLocator.getInstance().getRemoteSession(GestionarServiciosRemote.class.getCanonicalName());

            ArrayList<String> miLista = service.aventones(ruta2);

            return miLista;

        } catch (Exception ex) {
            Logger.getLogger(GWTServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void guardarCupo(String cupo) {
       try {
            GestionarServiciosRemote service = (GestionarServiciosRemote) ServiceLocator.getInstance().getRemoteSession(GestionarServiciosRemote.class.getCanonicalName());
            ServiceRequest req=new ServiceRequest();
            req.setRequest(cupo);
            service.pedirCupo(req );

            

        } catch (Exception ex) {
            Logger.getLogger(GWTServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public String consultarAventon(String aventon) {
       try {
            GestionarServiciosRemote service = (GestionarServiciosRemote) ServiceLocator.getInstance().getRemoteSession(GestionarServiciosRemote.class.getCanonicalName());
            ServiceRequest req=new ServiceRequest();
            req.setRequest(aventon);
            ServiceResponse resp=service.consultarAventon(req );
               return resp.getResponse();


        } catch (Exception ex) {
            Logger.getLogger(GWTServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
    }

    @Override
    public ArrayList<String> cupos(String aventon) {
        try {
            GestionarServiciosRemote service = (GestionarServiciosRemote) ServiceLocator.getInstance().getRemoteSession(GestionarServiciosRemote.class.getCanonicalName());

            ArrayList<String> miLista = service.cupos(aventon);

            return miLista;

        } catch (Exception ex) {
            Logger.getLogger(GWTServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void eliminarCupo(String cupo) {
          try {
            GestionarServiciosRemote service = (GestionarServiciosRemote) ServiceLocator.getInstance().getRemoteSession(GestionarServiciosRemote.class.getCanonicalName());

           service.eliminarCupo(cupo);

         

        } catch (Exception ex) {
            Logger.getLogger(GWTServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<String> cuposU(String user) {
         try {
            GestionarServiciosRemote service = (GestionarServiciosRemote) ServiceLocator.getInstance().getRemoteSession(GestionarServiciosRemote.class.getCanonicalName());

            ArrayList<String> miLista = service.cuposU(user);

            return miLista;

        } catch (Exception ex) {
            Logger.getLogger(GWTServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String nombreRuta(Integer ruta) {
        try {
            GestionarRutasRemote service = (GestionarRutasRemote) ServiceLocator.getInstance().getRemoteSession(GestionarRutasRemote.class.getCanonicalName());
           return service.nombreRuta(ruta);
        } catch (Exception ex) {
            Logger.getLogger(GWTServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
}
