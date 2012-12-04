/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.util.ArrayList;
import javax.ejb.Remote;
import modelo.ServiceRequest;
import modelo.ServiceResponse;

/**
 *
 * @author Andres
 */
@Remote
public interface GestionarRutasRemote {
     public ServiceResponse crearRuta(ServiceRequest request);
     public ServiceResponse crearPuntoIntermedio(ServiceRequest request);
     public void crearPuntoRuta(ServiceRequest request);
     public ArrayList<String>consultarRutasUsuario(String ced);
     public ArrayList<String>listarPuntosI();
 public ArrayList<String>RutaPuntos(Integer pntI,Integer pntF);
     public ArrayList<String> puntosRuta(Integer ruta);
        public String nombreRuta(Integer ruta);

}
