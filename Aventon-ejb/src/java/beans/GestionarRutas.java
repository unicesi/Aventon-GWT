/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Puntointermedio;
import entities.Puntoruta;
import entities.PuntorutaPK;
import entities.Ruta;
import entities.Usuario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class GestionarRutas implements GestionarRutasRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "Aventon-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    private void createRuta(Ruta entity) throws Exception {
        try {
            getEntityManager().persist(entity);
            getEntityManager().flush();
        } catch (Exception e) {
            throw e;
        }
    }

    public ArrayList<String>RutaPuntos(Integer pntI,Integer pntF){
        Puntointermedio pi=em.find(Puntointermedio.class,pntI );
        Puntointermedio pf=em.find(Puntointermedio.class,pntF );
       ArrayList<Puntoruta>listPi=new  ArrayList<Puntoruta>(pi.getPuntorutaCollection());

       ArrayList<Puntoruta>listPf=new  ArrayList<Puntoruta>(pf.getPuntorutaCollection());
       ArrayList<Ruta>lisRF=new ArrayList<Ruta>();
       ArrayList<Ruta>lisTmp=new ArrayList<Ruta>();

       for (Puntoruta puntoruta : listPf) {
           lisRF.add(puntoruta.getRuta());
        }
       for (Puntoruta puntoruta : listPi) {
            if (lisRF.contains(puntoruta.getRuta())) {
                if (!lisTmp.contains(puntoruta.getRuta())) {
                    lisTmp.add(puntoruta.getRuta());
                }
           }
        }

        ArrayList<String>listRet=new ArrayList<String>();
        for (Ruta ruta : lisTmp) {
            listRet.add(ruta.getCodigo()+"");
        }
        return  listRet;
       

    }

    private void createPuntoRuta(Puntoruta entity) throws Exception {
        try {
            getEntityManager().persist(entity);
            getEntityManager().flush();
        } catch (Exception e) {
            throw e;
        }
    }

    private void createPuntoIntermedio(Puntointermedio entity) throws Exception {
        try {
            getEntityManager().persist(entity);
            getEntityManager().flush();
        } catch (Exception e) {
            throw e;
        }
    }

    private Ruta findRuta(String id) {
        return getEntityManager().find(Ruta.class, id);
    }

    private Puntoruta findPuntoRuta(PuntorutaPK id) {
        return getEntityManager().find(Puntoruta.class, id);
    }

    private Puntointermedio findPuntoIntermedio(String id) {
        return getEntityManager().find(Puntointermedio.class, id);
    }

    private List<Ruta> findAllRutas() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Ruta.class));
        return getEntityManager().createQuery(cq).getResultList();
    }

    private List<Puntointermedio> findAllPuntosIntermedios() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Puntointermedio.class));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public ArrayList<String> puntosRuta(Integer ruta) {
        Ruta Miruta = em.find(Ruta.class, ruta);
        em.refresh(Miruta);
        Collection<Puntoruta> puntos = Miruta.getPuntorutaCollection();

        ArrayList<String> lista = new ArrayList<String>();
        for (Puntoruta puntoruta : puntos) {
            Puntointermedio miptoInt = puntoruta.getPuntointermedio();
            lista.add(puntoIntermedio2String(miptoInt.getCodigo(), miptoInt.getDireccion(), miptoInt.getDetalle(), miptoInt.getAltitud(), miptoInt.getLongitud()) + ";" + puntoruta.getIndice());
        }



        return lista;
    }

    public ServiceResponse crearRuta(ServiceRequest request) {
        try {
            String ruta = request.getRequest();
            Ruta miruta = string2Ruta(ruta);
            createRuta(miruta);
            ServiceResponse response = new ServiceResponse();
            response.setResponse(findAllRutas().get(findAllRutas().size() - 1).getCodigo() + "");
            return response;
        } catch (Exception ex) {
            Logger.getLogger(GestionarRutas.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public ServiceResponse crearPuntoIntermedio(ServiceRequest request) {
        try {
            String puntoI = request.getRequest();
            Puntointermedio mipunto = string2PuntoIntermedio(puntoI);
            createPuntoIntermedio(mipunto);
            ServiceResponse response = new ServiceResponse();
            response.setResponse(findAllPuntosIntermedios().get(findAllPuntosIntermedios().size() - 1).getCodigo() + "");
            return response;
        } catch (Exception ex) {
            Logger.getLogger(GestionarRutas.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void crearPuntoRuta(ServiceRequest request) {
        try {
            String puntoR = request.getRequest();
            Puntoruta mipunto = string2PuntoRuta(puntoR);
            createPuntoRuta(mipunto);
        } catch (Exception ex) {
            Logger.getLogger(GestionarRutas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String ruta2String(String cod, String des, String usu) {
        String resp = cod + ";" + des + ";" + ";" + usu;
        return resp;
    }

    private Ruta string2Ruta(String ru) {
        String[] ruArray = ru.split(";");
        Ruta rut = new Ruta(new Integer(ruArray[0]), ruArray[1]);
        rut.setUsuario(new Usuario(ruArray[2]));
        return rut;
    }

    private String puntoRutaPK2String(int codigoRuta, int codigoPuntointermedio) {
        String resp = (new Integer(codigoRuta)).toString() + ";" + (new Integer(codigoPuntointermedio)).toString();
        return resp;
    }

    private PuntorutaPK string2PuntoRutaPK(String rpk) {
        String[] rpkArray = rpk.split(";");
        PuntorutaPK tmp = new PuntorutaPK(Integer.parseInt(rpkArray[0]), Integer.parseInt(rpkArray[1]));

        return tmp;
    }

    private String puntoRuta2String(int codigoRuta, int codigoPuntointermedio, int indice, char puntoFin) {
        String prpk = puntoRutaPK2String(codigoRuta, codigoPuntointermedio);
        String resp = prpk + ";" + indice + ";" + puntoFin;
        return resp;
    }

    private Puntoruta string2PuntoRuta(String pr) {
        String[] prArray = pr.split(";");
        Puntoruta tmp = new Puntoruta(string2PuntoRutaPK(prArray[0] + ";" + prArray[1]),
                Integer.parseInt(prArray[2]), prArray[3].charAt(0));
        return tmp;
    }

    private String puntoIntermedio2String(Integer codigo, String direccion, String detalle, double altitud, double longitud) {
        StringBuilder resp = new StringBuilder();
        resp.append(codigo.toString());
        resp.append(";");
        resp.append(direccion);
        resp.append(";");
        resp.append(detalle);
        resp.append(";");
        resp.append(altitud);
        resp.append(";");
        resp.append(longitud);
        return resp.toString();
    }

    private Puntointermedio string2PuntoIntermedio(String pi) {
        String[] piArray = pi.split(";");
        Puntointermedio tmp = new Puntointermedio(new Integer(piArray[0]), piArray[1], piArray[2], Double.parseDouble(piArray[3]), Double.parseDouble(piArray[4]));
        return tmp;
    }

    public ArrayList<String> consultarRutasUsuario(String ced) {
        Usuario us = em.find(Usuario.class, ced);
        em.refresh(us);
        Collection<Ruta> MiLista = us.getRutaCollection();


        ArrayList<String> rutas = new ArrayList<String>();
        for (Ruta ruta : MiLista) {

            rutas.add(ruta.getCodigo() + ";" + ruta.getDescripcion());
        }
        return rutas;

    }

    public ArrayList<String> listarPuntosI() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Puntointermedio.class));
        List<Puntointermedio> lista = em.createQuery(cq).getResultList();
        ArrayList<String> ret = new ArrayList<String>();
        for (Puntointermedio puntointermedio : lista) {
            ret.add(puntoIntermedio2String(puntointermedio.getCodigo(), puntointermedio.getDireccion(), puntointermedio.getDetalle(), puntointermedio.getAltitud(), puntointermedio.getLongitud()));
        }
        return ret;

    }

    @Override
    public String nombreRuta(Integer ruta) {
          Ruta Miruta = em.find(Ruta.class, ruta);
          return  Miruta.getDescripcion();
    }
}
