/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Andres
 */
@Entity
@Table(name = "aventones")
@NamedQueries({
    @NamedQuery(name = "Aventon.findAll", query = "SELECT a FROM Aventon a"),
    @NamedQuery(name = "Aventon.findByCodigo", query = "SELECT a FROM Aventon a WHERE a.codigo = :codigo"),
    @NamedQuery(name = "Aventon.findByFechaSalida", query = "SELECT a FROM Aventon a WHERE a.aventonPK.fechaSalida = :fechaSalida"),
    @NamedQuery(name = "Aventon.findByFechaLlegada", query = "SELECT a FROM Aventon a WHERE a.fechaLlegada = :fechaLlegada"),
    @NamedQuery(name = "Aventon.findByNumCupos", query = "SELECT a FROM Aventon a WHERE a.numCupos = :numCupos"),
    @NamedQuery(name = "Aventon.findByDesvios", query = "SELECT a FROM Aventon a WHERE a.desvios = :desvios"),
    @NamedQuery(name = "Aventon.findByDescripcion", query = "SELECT a FROM Aventon a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "Aventon.findByCodigoRuta", query = "SELECT a FROM Aventon a WHERE a.aventonPK.codigoRuta = :codigoRuta")})
public class Aventon implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AventonPK aventonPK;
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private String codigo;
    @Basic(optional = false)
    @Column(name = "FECHA_LLEGADA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLlegada;
    @Basic(optional = false)
    @Column(name = "NUM_CUPOS")
    private int numCupos;
    @Basic(optional = false)
    @Column(name = "DESVIOS")
    private boolean desvios;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @JoinColumn(name = "PLACA_VEHICULO", referencedColumnName = "PLACA")
    @ManyToOne(optional = false)
    private Vehiculo vehiculo;
    @JoinColumn(name = "CODIGO_RUTA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ruta ruta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aventon")
    private Collection<Cupo> cupoCollection;

    public Aventon() {
    }

    public Aventon(AventonPK aventonPK) {
        this.aventonPK = aventonPK;
    }

    public Aventon(AventonPK aventonPK, String codigo, Date fechaLlegada, int numCupos, boolean desvios, String descripcion) {
        this.aventonPK = aventonPK;
        this.codigo = codigo;
        this.fechaLlegada = fechaLlegada;
        this.numCupos = numCupos;
        this.desvios = desvios;
        this.descripcion = descripcion;
    }

    public Aventon(Date fechaSalida, int codigoRuta) {
        this.aventonPK = new AventonPK(fechaSalida, codigoRuta);
    }

    public AventonPK getAventonPK() {
        return aventonPK;
    }

    public void setAventonPK(AventonPK aventonPK) {
        this.aventonPK = aventonPK;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public int getNumCupos() {
        return numCupos;
    }

    public void setNumCupos(int numCupos) {
        this.numCupos = numCupos;
    }

    public boolean getDesvios() {
        return desvios;
    }

    public void setDesvios(boolean desvios) {
        this.desvios = desvios;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public Collection<Cupo> getCupoCollection() {
        return cupoCollection;
    }

    public void setCupoCollection(Collection<Cupo> cupoCollection) {
        this.cupoCollection = cupoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aventonPK != null ? aventonPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aventon)) {
            return false;
        }
        Aventon other = (Aventon) object;
        if ((this.aventonPK == null && other.aventonPK != null) || (this.aventonPK != null && !this.aventonPK.equals(other.aventonPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Aventon[aventonPK=" + aventonPK + "]";
    }

}
