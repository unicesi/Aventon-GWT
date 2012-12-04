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
@Table(name = "vehiculos")
@NamedQueries({
    @NamedQuery(name = "Vehiculo.findAll", query = "SELECT v FROM Vehiculo v"),
    @NamedQuery(name = "Vehiculo.findByPlaca", query = "SELECT v FROM Vehiculo v WHERE v.placa = :placa"),
    @NamedQuery(name = "Vehiculo.findByModelo", query = "SELECT v FROM Vehiculo v WHERE v.modelo = :modelo"),
    @NamedQuery(name = "Vehiculo.findByMarca", query = "SELECT v FROM Vehiculo v WHERE v.marca = :marca"),
    @NamedQuery(name = "Vehiculo.findByColor", query = "SELECT v FROM Vehiculo v WHERE v.color = :color"),
    @NamedQuery(name = "Vehiculo.findByTipoCombustible", query = "SELECT v FROM Vehiculo v WHERE v.tipoCombustible = :tipoCombustible"),
    @NamedQuery(name = "Vehiculo.findByAireAcondicionado", query = "SELECT v FROM Vehiculo v WHERE v.aireAcondicionado = :aireAcondicionado")})
public class Vehiculo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PLACA")
    private String placa;
    @Basic(optional = false)
    @Column(name = "MODELO")
    private String modelo;
    @Basic(optional = false)
    @Column(name = "MARCA")
    private String marca;
    @Basic(optional = false)
    @Column(name = "COLOR")
    private String color;
    @Basic(optional = false)
    @Column(name = "TIPO_COMBUSTIBLE")
    private String tipoCombustible;
    @Basic(optional = false)
    @Column(name = "AIRE_ACONDICIONADO")
    private char aireAcondicionado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehiculo")
    private Collection<Aventon> aventonCollection;
    @JoinColumn(name = "CEDULA_USUARIO", referencedColumnName = "CEDULA")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Vehiculo() {
    }

    public Vehiculo(String placa) {
        this.placa = placa;
    }

    public Vehiculo(String placa, String modelo, String marca, String color, String tipoCombustible, char aireAcondicionado) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.color = color;
        this.tipoCombustible = tipoCombustible;
        this.aireAcondicionado = aireAcondicionado;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public char getAireAcondicionado() {
        return aireAcondicionado;
    }

    public void setAireAcondicionado(char aireAcondicionado) {
        this.aireAcondicionado = aireAcondicionado;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (placa != null ? placa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehiculo)) {
            return false;
        }
        Vehiculo other = (Vehiculo) object;
        if ((this.placa == null && other.placa != null) || (this.placa != null && !this.placa.equals(other.placa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Vehiculo[placa=" + placa + "]";
    }

}
