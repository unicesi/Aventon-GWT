/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Andres
 */
@Embeddable
public class PuntorutaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "CODIGO_RUTA")
    private int codigoRuta;
    @Basic(optional = false)
    @Column(name = "CODIGO_PUNTOINTERMEDIO")
    private int codigoPuntointermedio;

    public PuntorutaPK() {
    }

    public PuntorutaPK(int codigoRuta, int codigoPuntointermedio) {
        this.codigoRuta = codigoRuta;
        this.codigoPuntointermedio = codigoPuntointermedio;
    }

    public int getCodigoRuta() {
        return codigoRuta;
    }

    public void setCodigoRuta(int codigoRuta) {
        this.codigoRuta = codigoRuta;
    }

    public int getCodigoPuntointermedio() {
        return codigoPuntointermedio;
    }

    public void setCodigoPuntointermedio(int codigoPuntointermedio) {
        this.codigoPuntointermedio = codigoPuntointermedio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoRuta;
        hash += (int) codigoPuntointermedio;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PuntorutaPK)) {
            return false;
        }
        PuntorutaPK other = (PuntorutaPK) object;
        if (this.codigoRuta != other.codigoRuta) {
            return false;
        }
        if (this.codigoPuntointermedio != other.codigoPuntointermedio) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PuntorutaPK[codigoRuta=" + codigoRuta + ", codigoPuntointermedio=" + codigoPuntointermedio + "]";
    }

}
