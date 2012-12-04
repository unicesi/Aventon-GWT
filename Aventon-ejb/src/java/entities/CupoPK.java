/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Andres
 */
@Embeddable
public class CupoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "FECHA_SALIDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSalida;
    @Basic(optional = false)
    @Column(name = "CODIGO_RUTA")
    private int codigoRuta;
    @Basic(optional = false)
    @Column(name = "CEDULA_USUARIO")
    private String cedulaUsuario;

    public CupoPK() {
    }

    public CupoPK(Date fechaSalida, int codigoRuta, String cedulaUsuario) {
        this.fechaSalida = fechaSalida;
        this.codigoRuta = codigoRuta;
        this.cedulaUsuario = cedulaUsuario;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public int getCodigoRuta() {
        return codigoRuta;
    }

    public void setCodigoRuta(int codigoRuta) {
        this.codigoRuta = codigoRuta;
    }

    public String getCedulaUsuario() {
        return cedulaUsuario;
    }

    public void setCedulaUsuario(String cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fechaSalida != null ? fechaSalida.hashCode() : 0);
        hash += (int) codigoRuta;
        hash += (cedulaUsuario != null ? cedulaUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CupoPK)) {
            return false;
        }
        CupoPK other = (CupoPK) object;
        if ((this.fechaSalida == null && other.fechaSalida != null) || (this.fechaSalida != null && !this.fechaSalida.equals(other.fechaSalida))) {
            return false;
        }
        if (this.codigoRuta != other.codigoRuta) {
            return false;
        }
        if ((this.cedulaUsuario == null && other.cedulaUsuario != null) || (this.cedulaUsuario != null && !this.cedulaUsuario.equals(other.cedulaUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CupoPK[fechaSalida=" + fechaSalida + ", codigoRuta=" + codigoRuta + ", cedulaUsuario=" + cedulaUsuario + "]";
    }

}
