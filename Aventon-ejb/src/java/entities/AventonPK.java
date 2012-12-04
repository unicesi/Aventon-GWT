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
public class AventonPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "FECHA_SALIDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSalida;
    @Basic(optional = false)
    @Column(name = "CODIGO_RUTA")
    private int codigoRuta;

    public AventonPK() {
    }

    public AventonPK(Date fechaSalida, int codigoRuta) {
        this.fechaSalida = fechaSalida;
        this.codigoRuta = codigoRuta;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fechaSalida != null ? fechaSalida.hashCode() : 0);
        hash += (int) codigoRuta;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AventonPK)) {
            return false;
        }
        AventonPK other = (AventonPK) object;
        if ((this.fechaSalida == null && other.fechaSalida != null) || (this.fechaSalida != null && !this.fechaSalida.equals(other.fechaSalida))) {
            return false;
        }
        if (this.codigoRuta != other.codigoRuta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.AventonPK[fechaSalida=" + fechaSalida + ", codigoRuta=" + codigoRuta + "]";
    }

}
