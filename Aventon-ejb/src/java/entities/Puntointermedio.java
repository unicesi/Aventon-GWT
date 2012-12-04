/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Andres
 */
@Entity
@Table(name = "puntosintermedios")
@NamedQueries({
    @NamedQuery(name = "Puntointermedio.findAll", query = "SELECT p FROM Puntointermedio p"),
    @NamedQuery(name = "Puntointermedio.findByCodigo", query = "SELECT p FROM Puntointermedio p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Puntointermedio.findByDireccion", query = "SELECT p FROM Puntointermedio p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Puntointermedio.findByDetalle", query = "SELECT p FROM Puntointermedio p WHERE p.detalle = :detalle"),
    @NamedQuery(name = "Puntointermedio.findByAltitud", query = "SELECT p FROM Puntointermedio p WHERE p.altitud = :altitud"),
    @NamedQuery(name = "Puntointermedio.findByLongitud", query = "SELECT p FROM Puntointermedio p WHERE p.longitud = :longitud")})
public class Puntointermedio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "DIRECCION")
    private String direccion;
    @Basic(optional = false)
    @Column(name = "DETALLE")
    private String detalle;
    @Basic(optional = false)
    @Column(name = "ALTITUD")
    private double altitud;
    @Basic(optional = false)
    @Column(name = "LONGITUD")
    private double longitud;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "puntointermedio")
    private Collection<Puntoruta> puntorutaCollection;

    public Puntointermedio() {
    }

    public Puntointermedio(Integer codigo) {
        this.codigo = codigo;
    }

    public Puntointermedio(Integer codigo, String direccion, String detalle, double altitud, double longitud) {
        this.codigo = codigo;
        this.direccion = direccion;
        this.detalle = detalle;
        this.altitud = altitud;
        this.longitud = longitud;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public double getAltitud() {
        return altitud;
    }

    public void setAltitud(double altitud) {
        this.altitud = altitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public Collection<Puntoruta> getPuntorutaCollection() {
        return puntorutaCollection;
    }

    public void setPuntorutaCollection(Collection<Puntoruta> puntorutaCollection) {
        this.puntorutaCollection = puntorutaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Puntointermedio)) {
            return false;
        }
        Puntointermedio other = (Puntointermedio) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Puntointermedio[codigo=" + codigo + "]";
    }

}
