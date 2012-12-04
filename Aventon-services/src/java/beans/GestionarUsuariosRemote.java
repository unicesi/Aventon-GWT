/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import javax.ejb.Remote;
import modelo.ServiceRequest;
import modelo.ServiceResponse;

/**
 *
 * @author Andres
 */
@Remote
public interface GestionarUsuariosRemote {

    public ServiceResponse crearUsuario(ServiceRequest usuario);

    public ServiceResponse existeUsuario(ServiceRequest usuario);
    
    public ServiceResponse consultarUsuario(ServiceRequest usuario);
    
    public ServiceResponse modificarUsuario(ServiceRequest usuario);

    public ServiceResponse eliminarUsuario(ServiceRequest usuario);

    public ServiceResponse iniciarSesion(ServiceRequest idpass);
}