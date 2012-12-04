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
public interface GestionarCarrosRemote {

    public ServiceResponse crearCarro(ServiceRequest request) throws Exception;

    public ServiceResponse existeCarro(ServiceRequest placa);

    public ServiceResponse consultarCarro(ServiceRequest placa);

    public ArrayList<java.lang.String> listarCarros(String ced);
}
