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
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "usuarios")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByCedula", query = "SELECT u FROM Usuario u WHERE u.cedula = :cedula"),
    @NamedQuery(name = "Usuario.findByNombres", query = "SELECT u FROM Usuario u WHERE u.nombres = :nombres"),
    @NamedQuery(name = "Usuario.findByApellidos", query = "SELECT u FROM Usuario u WHERE u.apellidos = :apellidos"),
    @NamedQuery(name = "Usuario.findByGenero", query = "SELECT u FROM Usuario u WHERE u.genero = :genero"),
    @NamedQuery(name = "Usuario.findByFechaNacimiento", query = "SELECT u FROM Usuario u WHERE u.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email"),
    @NamedQuery(name = "Usuario.findByTelefono", query = "SELECT u FROM Usuario u WHERE u.telefono = :telefono"),
    @NamedQuery(name = "Usuario.findByEstadoCivil", query = "SELECT u FROM Usuario u WHERE u.estadoCivil = :estadoCivil"),
    @NamedQuery(name = "Usuario.findByTipo", query = "SELECT u FROM Usuario u WHERE u.tipo = :tipo"),
    @NamedQuery(name = "Usuario.findBySemestre", query = "SELECT u FROM Usuario u WHERE u.semestre = :semestre"),
    @NamedQuery(name = "Usuario.findByContrasena", query = "SELECT u FROM Usuario u WHERE u.contrasena = :contrasena")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CEDULA")
    private String cedula;
    @Basic(optional = false)
    @Column(name = "NOMBRES")
    private String nombres;
    @Basic(optional = false)
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Basic(optional = false)
    @Column(name = "GENERO")
    private char genero;
    @Basic(optional = false)
    @Column(name = "FECHA_NACIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @Column(name = "TELEFONO")
    private String telefono;
    @Basic(optional = false)
    @Column(name = "ESTADO_CIVIL")
    private String estadoCivil;
    @Basic(optional = false)
    @Column(name = "TIPO")
    private char tipo;
    @Basic(optional = false)
    @Column(name = "SEMESTRE")
    private int semestre;
    @Basic(optional = false)
    @Column(name = "CONTRASENA")
    private String contrasena;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<Ruta> rutaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<Vehiculo> vehiculoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<Cupo> cupoCollection;
    @JoinColumn(name = "CODIGO_CARRERA", referencedColumnName = "CODIGO")
    @ManyToOne
    private Carrera carrera;

    public Usuario() {
    }

    public Usuario(String cedula) {
        this.cedula = cedula;
    }

    public Usuario(String cedula, String nombres, String apellidos, char genero, Date fechaNacimiento, String email, String telefono, String estadoCivil, char tipo, int semestre, String contrasena) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.telefono = telefono;
        this.estadoCivil = estadoCivil;
        this.tipo = tipo;
        this.semestre = semestre;
        this.contrasena = contrasena;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Collection<Ruta> getRutaCollection() {
        return rutaCollection;
    }

    public void setRutaCollection(Collection<Ruta> rutaCollection) {
        this.rutaCollection = rutaCollection;
    }

    public Collection<Vehiculo> getVehiculoCollection() {
        return vehiculoCollection;
    }

    public void setVehiculoCollection(Collection<Vehiculo> vehiculoCollection) {
        this.vehiculoCollection = vehiculoCollection;
    }

    public Collection<Cupo> getCupoCollection() {
        return cupoCollection;
    }

    public void setCupoCollection(Collection<Cupo> cupoCollection) {
        this.cupoCollection = cupoCollection;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cedula != null ? cedula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.cedula == null && other.cedula != null) || (this.cedula != null && !this.cedula.equals(other.cedula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Usuario[cedula=" + cedula + "]";
    }

}
