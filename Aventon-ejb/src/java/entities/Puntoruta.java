/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Andres
 */
@Entity
@Table(name = "puntosruta")
@NamedQueries({
    @NamedQuery(name = "Puntoruta.findAll", query = "SELECT p FROM Puntoruta p"),
    @NamedQuery(name = "Puntoruta.findByCodigoRuta", query = "SELECT p FROM Puntoruta p WHERE p.puntorutaPK.codigoRuta = :codigoRuta"),
    @NamedQuery(name = "Puntoruta.findByCodigoPuntointermedio", query = "SELECT p FROM Puntoruta p WHERE p.puntorutaPK.codigoPuntointermedio = :codigoPuntointermedio"),
    @NamedQuery(name = "Puntoruta.findByIndice", query = "SELECT p FROM Puntoruta p WHERE p.indice = :indice"),
    @NamedQuery(name = "Puntoruta.findByPuntoFin", query = "SELECT p FROM Puntoruta p WHERE p.puntoFin = :puntoFin")})
public class Puntoruta implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PuntorutaPK puntorutaPK;
    @Basic(optional = false)
    @Column(name = "INDICE")
    private int indice;
    @Basic(optional = false)
    @Column(name = "PUNTO_FIN")
    private char puntoFin;
    @JoinColumn(name = "CODIGO_PUNTOINTERMEDIO", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Puntointermedio puntointermedio;
    @JoinColumn(name = "CODIGO_RUTA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ruta ruta;

    public Puntoruta() {
    }

    public Puntoruta(PuntorutaPK puntorutaPK) {
        this.puntorutaPK = puntorutaPK;
    }

    public Puntoruta(PuntorutaPK puntorutaPK, int indice, char puntoFin) {
        this.puntorutaPK = puntorutaPK;
        this.indice = indice;
        this.puntoFin = puntoFin;
    }

    public Puntoruta(int codigoRuta, int codigoPuntointermedio) {
        this.puntorutaPK = new PuntorutaPK(codigoRuta, codigoPuntointermedio);
    }

    public PuntorutaPK getPuntorutaPK() {
        return puntorutaPK;
    }

    public void setPuntorutaPK(PuntorutaPK puntorutaPK) {
        this.puntorutaPK = puntorutaPK;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public char getPuntoFin() {
        return puntoFin;
    }

    public void setPuntoFin(char puntoFin) {
        this.puntoFin = puntoFin;
    }

    public Puntointermedio getPuntointermedio() {
        return puntointermedio;
    }

    public void setPuntointermedio(Puntointermedio puntointermedio) {
        this.puntointermedio = puntointermedio;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (puntorutaPK != null ? puntorutaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Puntoruta)) {
            return false;
        }
        Puntoruta other = (Puntoruta) object;
        if ((this.puntorutaPK == null && other.puntorutaPK != null) || (this.puntorutaPK != null && !this.puntorutaPK.equals(other.puntorutaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Puntoruta[puntorutaPK=" + puntorutaPK + "]";
    }

}
