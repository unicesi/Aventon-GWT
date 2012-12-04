/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aventon.client.partesPagina;

import com.google.gwt.maps.client.geom.LatLng;
import java.util.Comparator;

/**
 *
 * @author Jaime A
 */
public class PuntoRuta implements Comparator{

    public LatLng coordenadas;
    public String codigo;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }


    public LatLng getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(LatLng coordenadas) {
        this.coordenadas = coordenadas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String descripcion;
    public String direccion;
    public int indice;

    public PuntoRuta(LatLng coordenadas,String descripcion,String direccion,int indice){

    this.coordenadas=coordenadas;
    this.descripcion=descripcion;
    this.direccion=direccion;
    this.indice=indice;
    }

    public PuntoRuta(){
    }

    public int compare(Object o1, Object o2) {
    PuntoRuta u1 = (PuntoRuta) o1;
    PuntoRuta u2 = (PuntoRuta) o2;
    return u1.indice - u2.indice;
  }

  public boolean equals(Object o) {
    return this == o;
  }


}
