/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aventon.client.partesPagina;

import com.aventon.client.GWTServiceAsync;
import com.google.gwt.user.client.ui.Composite;
import com.aventon.client.GWTService;
import com.aventon.client.util.Parser;
import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.control.MapTypeControl;
import com.google.gwt.maps.client.control.ScaleControl;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.Polyline;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import java.util.ArrayList;
import modelo.ServiceResponse;

/**
 *
 * @author Jaime A
 */
public class CrearRuta extends Composite {

    public CrearRuta(String usu) {
        onModuleLoad();
        usuarioLinea = usu;

    }
    public String usuarioLinea;
    public ArrayList<PuntoRuta> puntosRuta = new ArrayList<PuntoRuta>();
    public MapWidget mapW;
    public TextBox descTextBox = new TextBox();
    public TextBox direccionTextBox = new TextBox();
    public TextBox descRutaTextBox = new TextBox();
    public VerticalPanel verticalPanelMapa = new VerticalPanel();
    /**
     * @wbp.parser.entryPoint
     */
    public String codigoRuta = "";
    public int contador = 0;

    public void onModuleLoad() {

        Maps.loadMapsApi("", "2", false, new Runnable() {

            public void run() {
                construirMapa();
            }
        });
        Button botonLimpiarRuta = new Button();
        botonLimpiarRuta.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                puntosRuta.clear();
                mapW.clearOverlays();
            }
        });

        final Label label3 = new Label("Descripcion Ruta ");


        verticalPanelMapa.add(label3);
        verticalPanelMapa.add(descRutaTextBox);
        botonLimpiarRuta.setText("Eliminar Ruta");
        verticalPanelMapa.add(botonLimpiarRuta);


        final Label label = new Label("Descripcion Punto Ruta");
        final Label label2 = new Label("Direccion");


        verticalPanelMapa.add(label);
        verticalPanelMapa.add(descTextBox);
        verticalPanelMapa.add(label2);
        verticalPanelMapa.add(direccionTextBox);

        Button botonFinalizar = new Button();
        botonFinalizar.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                contador = puntosRuta.size();
                GWTServiceAsync serviceRPC = GWTService.App.getInstance();
                serviceRPC.crearRuta(Parser.ruta2String("1", descRutaTextBox.getText(), usuarioLinea), new AsyncCallback<String>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    @Override
                    public void onSuccess(String result) {


                        guardarPuntos(result);
                    }
                });







            



            }
        });

        botonFinalizar.setText("Finalizar");
        verticalPanelMapa.add(botonFinalizar);
        initWidget(verticalPanelMapa);


    }

    public void guardarPuntos(final String ruta) {
        for (int i = 0; i < puntosRuta.size(); i++) {
            PuntoRuta puntoRuta = puntosRuta.get(i);

            GWTServiceAsync serviceRPC = GWTService.App.getInstance();


            serviceRPC.crearPuntoIntermedio(Parser.puntoIntermedio2String(1, puntoRuta.getDireccion(), puntoRuta.getDescripcion(), puntoRuta.getCoordenadas().getLatitude(), puntoRuta.getCoordenadas().getLongitude()), i, new AsyncCallback<String>() {

                @Override
                public void onFailure(Throwable caught) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
                public void onSuccess(String result) {

                    guardarPuntoIntermedio(ruta, Integer.parseInt(result.split(";")[0]), result.split(";")[1], contador);


                }
            });

        }
        Window.alert("Rutas Ingresadas Con exito");
            descTextBox.setText("");
                direccionTextBox.setText("");
                descRutaTextBox.setText("");
                puntosRuta.clear();
                mapW.clearOverlays();

    }

    /**
     * @wbp.parser.entryPoint
     */
    public void guardarPuntoIntermedio(String ruta, int i, String str, int total) {

        char fin = 'N';
        if (i == total - 1) {
            fin = 'S';
        }

        GWTServiceAsync serviceRPC = GWTService.App.getInstance();
        serviceRPC.crearPuntoRuta(Parser.puntoRuta2String(Integer.parseInt(ruta), Integer.parseInt(str), i + 1, fin), new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void onSuccess(Void result) {
            }
        });


    }

    private void construirMapa() {

        final MapWidget map = new MapWidget();
        map.setSize("100%", "50%");
        mapW = map;

        map.addMapClickHandler(new MapClickHandler() {

            public void onClick(MapClickEvent event) {

                PuntoRuta punto = new PuntoRuta();
                punto.setCoordenadas(event.getLatLng());
                punto.setDescripcion(descTextBox.getText());
                punto.setDireccion(direccionTextBox.getText());
                puntosRuta.add(punto);

                LatLng[] puntosI = new LatLng[puntosRuta.size()];

                for (int i = 0; i < puntosRuta.size(); i++) {
                    puntosI[i] = puntosRuta.get(i).getCoordenadas();
                }
                map.addOverlay(crearMarcador(descTextBox.getText(), direccionTextBox.getText(), event.getLatLng(), map));
                map.addOverlay(new Polyline(puntosI));

            }
        });

        map.setWidth("600px");

        map.setHeight("600px");

        LatLng coordenadas = LatLng.newInstance(3.34180, -76.52999);

        //mostramos el mapa centrado con las coordenas (3.34180,-76.52894)

        map.setCenter(coordenadas);

        //establecemos en nivel de zoom

        map.setZoomLevel(15);

        //añadimos control selector de tipo de mapa

        map.addControl(new MapTypeControl());

        //añadimos control de desplazamiento con zoom

        map.addControl(new LargeMapControl());

        //añadimos control escala de mapa

        map.addControl(new ScaleControl());

        //cambiamos a vista mapa satelital

        map.setCurrentMapType(MapType.getHybridMap());

        //creamos un marcador en la coordenadas (-18.0376, -70.2506)

        Marker mrk = crearMarcador(descTextBox.getText(), direccionTextBox.getText(), coordenadas, map);

        verticalPanelMapa.add(map);


    }

    /**
     * @wbp.parser.entryPoint
     */
    public Marker crearMarcador(final String descripcion, final String direccion, final LatLng coordenadas, final MapWidget map) {

        final Marker mrk = new Marker(coordenadas);

        mrk.addMarkerClickHandler(new MarkerClickHandler() {

            public void onClick(MarkerClickEvent event) {

                map.getInfoWindow().open(coordenadas, new InfoWindowContent(descripcion + "," + direccion));

            }
        });

        return mrk;

    }
}
