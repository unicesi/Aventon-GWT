/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Carrera;
import entities.Usuario;
import java.util.Date;
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
public class GestionarUsuarios implements GestionarUsuariosRemote {

    @PersistenceContext(unitName = "Aventon-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public ServiceResponse crearUsuario(ServiceRequest usuario) {
        ServiceResponse response = new ServiceResponse();
        response.setResponse("El usuario ya existe");
        if(em.find(Usuario.class, String2Usuario(usuario).getCedula())!=null){
            return response;
        }
        em.persist(String2Usuario(usuario));
        em.flush();
        response.setResponse("Hecho");
        return response;
    }

    @Override
    public ServiceResponse existeUsuario(ServiceRequest cedula) {
        ServiceResponse response = new ServiceResponse();
        if (consultarUsuario(cedula) == null) {
            response.setResponse("No existe");
        } else {
            response.setResponse("Existe");
        }
        return response;
    }

    @Override
    public ServiceResponse consultarUsuario(ServiceRequest cedula) {
        Usuario user = em.find(Usuario.class, cedula.getRequest());
        ServiceResponse response = new ServiceResponse();
        response.setResponse(Usuario2String(user));
        return response;
    }

    @Override
    public ServiceResponse modificarUsuario(ServiceRequest usuario) {
        em.merge(String2Usuario(usuario));
        em.flush();
        ServiceResponse response = new ServiceResponse();
        response.setResponse("Hecho");
        return response;
    }

    @Override
    public ServiceResponse eliminarUsuario(ServiceRequest usuario) {
        em.remove(String2Usuario(usuario));
        ServiceResponse response = new ServiceResponse();
        response.setResponse("Hecho");
        return response;
    }

    private Usuario String2Usuario(ServiceRequest req) {
        String[] partes = req.getRequest().split(";");
        String cedula = partes[0];
        String nombres = partes[1];
        String apellidos = partes[2];
        char genero = partes[3].charAt(0);
        Date nacimiento = new Date(Long.parseLong(partes[4]));
        String email = partes[5];
        String telefono = partes[6];
        String estadoCivil = partes[7];
        char tipo = partes[8].charAt(0);
        int semestre = Integer.parseInt(partes[9]);
        String contrasena = partes[10];

        Usuario usuario = new Usuario(cedula, nombres, apellidos, genero, nacimiento, email, telefono, estadoCivil, tipo, semestre, contrasena);
        return usuario;
    }

    private static Carrera string2Carrera(String car) {
        String[] carArray = car.split(";");
        Carrera c = new Carrera(carArray[0], carArray[1]);
        return c;
    }

    private static String Usuario2String(Usuario user) {
        StringBuilder resp = new StringBuilder();
        resp.append(user.getCedula());
        resp.append(";");
        resp.append(user.getNombres());
        resp.append(";");
        resp.append(user.getApellidos());
        resp.append(";");
        resp.append(user.getGenero());
        resp.append(";");
        resp.append(user.getFechaNacimiento().getTime());
        resp.append(";");
        resp.append(user.getEmail());
        resp.append(";");
        resp.append(user.getTelefono());
        resp.append(";");
        resp.append(user.getEstadoCivil());
        resp.append(";");
        resp.append(user.getTipo());
        resp.append(";");
        resp.append(user.getSemestre());
        resp.append(";");
        resp.append(user.getContrasena());
        return resp.toString();
    }

    @Override
    public ServiceResponse iniciarSesion(ServiceRequest idpass) {
        String id=idpass.getRequest().split(";")[0];
        String pass=idpass.getRequest().split(";")[1];
        Usuario user=em.find(Usuario.class, id);
        ServiceResponse response= new ServiceResponse();
        response.setResponse("incorrecto");
        if(pass.equals(user.getContrasena())){
            response.setResponse("correcto");
        }
        return response;
    }
}
