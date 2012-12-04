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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Andres
 */
@Entity
@Table(name = "rutas")
@NamedQueries({
    @NamedQuery(name = "Ruta.findAll", query = "SELECT r FROM Ruta r"),
    @NamedQuery(name = "Ruta.findByCodigo", query = "SELECT r FROM Ruta r WHERE r.codigo = :codigo"),
    @NamedQuery(name = "Ruta.findByDescripcion", query = "SELECT r FROM Ruta r WHERE r.descripcion = :descripcion")})
public class Ruta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ruta")
    private Collection<Aventon> aventonCollection;
    @JoinColumn(name = "CEDULA_USUARIO", referencedColumnName = "CEDULA")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ruta")
    private Collection<Puntoruta> puntorutaCollection;

    public Ruta() {
    }

    public Ruta(Integer codigo) {
        this.codigo = codigo;
    }

    public Ruta(Integer codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<Aventon> getAventonCollection() {
        return aventonCollection;
    }

    public void setAventonCollection(Collection<Aventon> aventonCollection) {
        this.aventonCollection = aventonCollection;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        if (!(object instanceof Ruta)) {
            return false;
        }
        Ruta other = (Ruta) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Ruta[codigo=" + codigo + "]";
    }

}
