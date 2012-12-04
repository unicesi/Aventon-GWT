/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Usuario;
import entities.Vehiculo;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.ServiceRequest;
import modelo.ServiceResponse;

/**
 *
 * @author Andres
 */
@Stateless
public class GestionarCarros implements GestionarCarrosRemote {

    @PersistenceContext(unitName = "Aventon-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public ServiceResponse crearCarro(ServiceRequest request) throws Exception {
        Vehiculo vehi = string2Vehiculo(request.getRequest());
        em.persist(vehi);
        em.flush();
        ServiceResponse response = new ServiceResponse();
        response.setResponse("Agregado");
        return response;
    }

    @Override
    public ServiceResponse consultarCarro(ServiceRequest placa) {
        Vehiculo carro = em.find(Vehiculo.class,placa.getRequest());
        ServiceResponse response = new ServiceResponse();
        response.setResponse(carro.getPlaca()+";"+carro.getModelo()+";"+carro.getMarca()+";"+carro.getColor()+";"+carro.getTipoCombustible()+";"+carro.getAireAcondicionado());
        return response;
    }

    @Override
    public ArrayList<String> listarCarros(String ced){
    
      
        Usuario  us=em.find(Usuario.class, ced);
     em.refresh(us);
        Collection<Vehiculo> MiLista=us.getVehiculoCollection();

        ArrayList<String> vehiculos=new ArrayList<String>();
        for (Vehiculo vehiculo : MiLista) {

         vehiculos.add(vehiculo.getPlaca());
        }
        return  vehiculos;
    }

    @Override
    public ServiceResponse existeCarro(ServiceRequest placa) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static Vehiculo string2Vehiculo(String ve) {
        String[] veArray = ve.split(";");
        Vehiculo tmp = new Vehiculo(veArray[0], veArray[1], veArray[2], veArray[3], veArray[4], veArray[5].charAt(0));
        tmp.setUsuario(new Usuario(veArray[6]));
        return tmp;
    }
}
