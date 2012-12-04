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
public interface GestionarServiciosRemote {
    public ArrayList<String> aventones(String ruta2);
    public ArrayList<String> cupos(String aventon);
     public ArrayList<String> cuposU(String user);
      public void eliminarCupo(String cupo);
    public ServiceResponse crearAventon(ServiceRequest aventon);

    public ServiceResponse existeAventon(ServiceRequest aventon);

    public ServiceResponse consultarAventon(ServiceRequest aventon);

    public ServiceResponse modificarAventon(ServiceRequest aventon);

    public ServiceResponse eliminarAventon(ServiceRequest aventon);

    public ServiceResponse pedirCupo(ServiceRequest cupo);

    public ServiceResponse calificarCupo(ServiceRequest calificarCupo);

    
}
