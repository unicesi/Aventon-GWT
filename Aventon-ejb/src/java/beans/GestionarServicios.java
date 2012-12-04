/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Aventon;
import entities.AventonPK;
import entities.Cupo;
import entities.CupoPK;
import entities.Ruta;
import entities.Usuario;
import entities.Vehiculo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
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
public class GestionarServicios implements GestionarServiciosRemote {

    @PersistenceContext(unitName = "Aventon-ejbPU")
    private EntityManager em;

    @Override
    public ServiceResponse crearAventon(ServiceRequest aventon) {
        ServiceResponse response = new ServiceResponse();
        response.setResponse("El aventon ya existe");
        if (em.find(Aventon.class, string2AventonPK(aventon.getRequest())) != null) {
            return response;
        }
        em.persist(string2Aventon(aventon.getRequest()));
        em.flush();
        response.setResponse("Hecho");
        return response;
    }

    @Override
    public ServiceResponse existeAventon(ServiceRequest aventonpk) {
        ServiceResponse response = new ServiceResponse();
        if (consultarAventon(aventonpk) == null) {
            response.setResponse("No existe");
        } else {
            response.setResponse("Existe");
        }
        return response;
    }


    public ArrayList<String> aventones(String ruta2){
        Integer inte=new Integer(ruta2);
        Ruta ruta=em.find(Ruta.class, inte);
        em.refresh(ruta);
        Collection<Aventon> ave=ruta.getAventonCollection();
        ArrayList<String>list=new ArrayList<String>();
        for (Iterator<Aventon> it = ave.iterator(); it.hasNext();) {
            Aventon aventon = it.next();
            list.add(aventon2String(aventon));

        }
        return  list;
    }
    @Override
    public ServiceResponse consultarAventon(ServiceRequest aventonpk) {
        Aventon user = em.find(Aventon.class,string2AventonPK(aventonpk.getRequest()));
        ServiceResponse response = new ServiceResponse();
        response.setResponse(aventon2String(user));
        return response;
    }

    @Override
    public ServiceResponse modificarAventon(ServiceRequest aventon) {
        em.merge(string2Aventon(aventon.getRequest()));
        em.flush();
        ServiceResponse response = new ServiceResponse();
        response.setResponse("Hecho");
        return response;
    }

    @Override
    public ServiceResponse eliminarAventon(ServiceRequest aventon) {
        em.remove(string2Aventon(aventon.getRequest()));
        ServiceResponse response = new ServiceResponse();
        response.setResponse("Hecho");
        return response;
    }

    @Override
    public ServiceResponse pedirCupo(ServiceRequest cupo) {
        ServiceResponse response = new ServiceResponse();
        response.setResponse("El cupo ya existe");
        if (em.find(Cupo.class, String2CupoPK(cupo.getRequest())) != null) {
            return response;
        }
        em.persist(string2Cupo(cupo.getRequest()));
        em.flush();
        response.setResponse("Hecho");
        return response;
    }

    private static AventonPK string2AventonPK(String apk) {
        String[] apkArray = apk.split(";");
        AventonPK tmp = new AventonPK(new Date(Long.parseLong(apkArray[0])), Integer.parseInt(apkArray[1]));
        return tmp;
    }

    private static Aventon string2Aventon(String av) {
        String[] avArray = av.split(";");
        AventonPK apk = string2AventonPK(avArray[0] + ";" + avArray[1]);
        Aventon avn = new Aventon(apk, avArray[2], new Date(Long.parseLong(avArray[3])), Integer.parseInt(avArray[4]),
                Boolean.parseBoolean(avArray[5]), avArray[6]);
        avn.setVehiculo(new Vehiculo(avArray[7]));
        return avn;
    }

    private static CupoPK String2CupoPK(String cpk) {
        String[] cpkArray = cpk.split(";");
        CupoPK tmp = new CupoPK((new Date(Long.parseLong(cpkArray[0]))), Integer.parseInt(cpkArray[1]),
                cpkArray[2]);
        return tmp;
    }

    private static Cupo string2Cupo(String cup) {
        String[] cupArray = cup.split(";");
        CupoPK cpk = String2CupoPK(cupArray[0] + ";" + cupArray[1]
                + ";" + cupArray[2]);
        Cupo cu = new Cupo(cpk);
        if (!cupArray[3].equals("-")) {
            cu.setAmabilidad(new Integer(cupArray[3]));
        }
        if (!cupArray[4].equals("-")) {
            cu.setComodidad(new Integer(cupArray[4]));
        }
        if (!cupArray[5].equals("-")) {
            cu.setSeguridad(new Integer(cupArray[5]));
        }
        if (!cupArray[6].equals("-")) {
            cu.setPuntualidad(new Integer(cupArray[6]));
        }
        if (!cupArray[7].equals("-")) {
            cu.setComentarios(cupArray[7]);
        }
        return cu;
    }

    private static String aventon2String(Aventon aventon) {
        String apk = aventonPK2String(aventon.getAventonPK());
        StringBuilder resp = new StringBuilder();
        resp.append(apk);
        resp.append(";");
        resp.append(aventon.getCodigo());
        resp.append(";");
        resp.append(aventon.getFechaLlegada().getTime());
        resp.append(";");
        resp.append(aventon.getNumCupos());
        resp.append(";");
        resp.append(aventon.getDesvios());
        resp.append(";");
        resp.append(aventon.getDescripcion());
        resp.append(";");
        resp.append(aventon.getVehiculo().getPlaca());
        return resp.toString();
    }

    private static String aventonPK2String(AventonPK pk) {
        StringBuilder resp = new StringBuilder();
        resp.append(pk.getFechaSalida().getTime());
        resp.append(";");
        resp.append(pk.getCodigoRuta());
        return resp.toString();
    }


    public ServiceResponse calificarCupo(ServiceRequest calificarCupo) {

        ServiceResponse response = new ServiceResponse();
        response.setResponse("El cupo no existe");
        if (em.find(Cupo.class, String2CupoPK(calificarCupo.getRequest())) == null) {
            return response;
        }
        em.merge(string2Cupo(calificarCupo.getRequest()));
        em.flush();
        response.setResponse("Hecho");
        return response;
    }

    @Override
    public ArrayList<String> cupos(String aventon) {
        Aventon ruta=em.find(Aventon.class, string2AventonPK(aventon));
        em.refresh(ruta);

        Collection<Cupo> ave=ruta.getCupoCollection();

        ArrayList<String> tmp=new ArrayList<String>();
         for (Cupo cupo : ave) {
            tmp.add(cupo2String(cupo.getCupoPK().getFechaSalida(), cupo.getCupoPK().getCodigoRuta(), cupo.getCupoPK().getCedulaUsuario(), cupo.getAmabilidad(), cupo.getComodidad(), cupo.getSeguridad(), cupo.getPuntualidad(), cupo.getComentarios()));
        }
         return tmp;
    }
 private String cupoPK2String(Date fechaSalida, int codigoRuta, String cedulaUsuario) {
        StringBuilder resp = new StringBuilder();
        resp.append(fechaSalida.getTime());
        resp.append(";");
        resp.append(codigoRuta);
        resp.append(";");
        resp.append(cedulaUsuario);
        return resp.toString();
    }
    private  String cupo2String(Date fechaSalida, int codigoRuta, String cedulaUsuario, Integer amabil, Integer comod, Integer segur, Integer puntu, String come) {
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

    @Override
    public void eliminarCupo(String cupo) {
       Cupo cupo2=em.find(Cupo.class, String2CupoPK(cupo));
       em.remove(cupo2);
    }

    @Override
    public ArrayList<String> cuposU(String user) {
          Usuario  us=em.find(Usuario.class, user);
     em.refresh(us);
        Collection<Cupo> MiLista=us.getCupoCollection();

        ArrayList<String> vehiculos=new ArrayList<String>();
        for (Cupo cupo : MiLista) {

          vehiculos.add(cupo2String(cupo.getCupoPK().getFechaSalida(), cupo.getCupoPK().getCodigoRuta(), cupo.getCupoPK().getCedulaUsuario(), cupo.getAmabilidad(), cupo.getComodidad(), cupo.getSeguridad(), cupo.getPuntualidad(), cupo.getComentarios()));
       
        }
        return  vehiculos;

    }
}
