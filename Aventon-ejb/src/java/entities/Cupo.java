/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Andres
 */
@Entity
@Table(name = "cupos")
@NamedQueries({
    @NamedQuery(name = "Cupo.findAll", query = "SELECT c FROM Cupo c"),
    @NamedQuery(name = "Cupo.findByFechaSalida", query = "SELECT c FROM Cupo c WHERE c.cupoPK.fechaSalida = :fechaSalida"),
    @NamedQuery(name = "Cupo.findByCodigoRuta", query = "SELECT c FROM Cupo c WHERE c.cupoPK.codigoRuta = :codigoRuta"),
    @NamedQuery(name = "Cupo.findByCedulaUsuario", query = "SELECT c FROM Cupo c WHERE c.cupoPK.cedulaUsuario = :cedulaUsuario"),
    @NamedQuery(name = "Cupo.findByAmabilidad", query = "SELECT c FROM Cupo c WHERE c.amabilidad = :amabilidad"),
    @NamedQuery(name = "Cupo.findByComodidad", query = "SELECT c FROM Cupo c WHERE c.comodidad = :comodidad"),
    @NamedQuery(name = "Cupo.findBySeguridad", query = "SELECT c FROM Cupo c WHERE c.seguridad = :seguridad"),
    @NamedQuery(name = "Cupo.findByPuntualidad", query = "SELECT c FROM Cupo c WHERE c.puntualidad = :puntualidad"),
    @NamedQuery(name = "Cupo.findByComentarios", query = "SELECT c FROM Cupo c WHERE c.comentarios = :comentarios")})
public class Cupo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CupoPK cupoPK;
    @Column(name = "AMABILIDAD")
    private Integer amabilidad;
    @Column(name = "COMODIDAD")
    private Integer comodidad;
    @Column(name = "SEGURIDAD")
    private Integer seguridad;
    @Column(name = "PUNTUALIDAD")
    private Integer puntualidad;
    @Column(name = "COMENTARIOS")
    private String comentarios;
    @JoinColumn(name = "CEDULA_USUARIO", referencedColumnName = "CEDULA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumns({
        @JoinColumn(name = "FECHA_SALIDA", referencedColumnName = "FECHA_SALIDA", insertable = false, updatable = false),
        @JoinColumn(name = "CODIGO_RUTA", referencedColumnName = "CODIGO_RUTA", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Aventon aventon;

    public Cupo() {
    }

    public Cupo(CupoPK cupoPK) {
        this.cupoPK = cupoPK;
    }

    public Cupo(Date fechaSalida, int codigoRuta, String cedulaUsuario) {
        this.cupoPK = new CupoPK(fechaSalida, codigoRuta, cedulaUsuario);
    }

    public CupoPK getCupoPK() {
        return cupoPK;
    }

    public void setCupoPK(CupoPK cupoPK) {
        this.cupoPK = cupoPK;
    }

    public Integer getAmabilidad() {
        return amabilidad;
    }

    public void setAmabilidad(Integer amabilidad) {
        this.amabilidad = amabilidad;
    }

    public Integer getComodidad() {
        return comodidad;
    }

    public void setComodidad(Integer comodidad) {
        this.comodidad = comodidad;
    }

    public Integer getSeguridad() {
        return seguridad;
    }

    public void setSeguridad(Integer seguridad) {
        this.seguridad = seguridad;
    }

    public Integer getPuntualidad() {
        return puntualidad;
    }

    public void setPuntualidad(Integer puntualidad) {
        this.puntualidad = puntualidad;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Aventon getAventon() {
        return aventon;
    }

    public void setAventon(Aventon aventon) {
        this.aventon = aventon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cupoPK != null ? cupoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cupo)) {
            return false;
        }
        Cupo other = (Cupo) object;
        if ((this.cupoPK == null && other.cupoPK != null) || (this.cupoPK != null && !this.cupoPK.equals(other.cupoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Cupo[cupoPK=" + cupoPK + "]";
    }

}
