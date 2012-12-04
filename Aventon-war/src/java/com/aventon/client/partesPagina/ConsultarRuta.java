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
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author Jaime A
 */
public class ConsultarRuta extends Composite{


/**
 *
 * @author Jaime A
 */

    public ConsultarRuta(String usu) {        
        usuarioLinea = usu;
   
        onModuleLoad();
       
       
       

    }


    

    public String usuarioLinea;
    public ArrayList<PuntoRuta> puntosRuta = new ArrayList<PuntoRuta>();
    public MapWidget mapW;
    public TextBox descTextBox = new TextBox();
    public TextBox direccionTextBox = new TextBox();
    public TextBox descRutaTextBox = new TextBox();
    public VerticalPanel verticalPanelMapa=new VerticalPanel();
    public ListBox listaRutas=new ListBox();

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

        verticalPanelMapa.add(listaRutas);

        Button botonAceptar = new Button();
        botonAceptar.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                GWTServiceAsync servicioRPC = GWTService.App.getInstance();
                servicioRPC.puntosRuta(listaRutas.getValue(listaRutas.getSelectedIndex())+"", new AsyncCallback<ArrayList<String>>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    @Override
                    public void onSuccess(ArrayList<String> result) {
                       
                        puntosRuta.clear();
                        mapW.clearOverlays();

                        for (String string : result) {

                            String latitud=string.split(";")[3];                            
                            String longitud=string.split(";")[4];
                            String descripcion=string.split(";")[2];
                            String direccion=string.split(";")[1];
                            String indice=string.split(";")[5];

                            puntosRuta.add(new PuntoRuta(LatLng.newInstance(new Double(latitud),new Double(longitud)),descripcion,direccion,Integer.parseInt(indice) ));
                            mapW.addOverlay(crearMarcador(descripcion, direccion, LatLng.newInstance(new Double(latitud),new Double(longitud)), mapW));

                        }

                        Collections.sort(puntosRuta,new PuntoRuta());
                       
                        LatLng[] puntosI = new LatLng[puntosRuta.size()];

                        for (int i = 0; i < puntosRuta.size(); i++) {
                        puntosI[i] = puntosRuta.get(i).getCoordenadas();
                        }

                        mapW.addOverlay(new Polyline(puntosI));

                    }
                });



              

            }
        });

        botonAceptar.setText("Aceptar");
        verticalPanelMapa.add(botonAceptar);
        initWidget(verticalPanelMapa);

    }


      public static ArrayList ordenar(ArrayList<PuntoRuta> puntos){

        PuntoRuta temp;
        int t = puntos.size();
        for (int i = 0; i < t; i++) {
            for (int k = t- 1; k >= i; k--) {
                if(puntos.get(k).indice < puntos.get(k-1).indice){
                    temp = puntos.get(k);
                    puntos.add(k,puntos.get(k-1));
                    puntos.add(k-1,temp);
                }
            }
        }


        return puntos;
    }





    /**
     * @wbp.parser.entryPoint
     */
    public void guardarPuntoIntermedio(int i, String str,int total) {

        char fin = 'N';
        if (i == total - 1) {
            fin = 'S';
        }

        GWTServiceAsync serviceRPC = GWTService.App.getInstance();
        serviceRPC.crearPuntoRuta(Parser.puntoRuta2String(Integer.parseInt(codigoRuta), Integer.parseInt(str), i + 1, fin), new AsyncCallback<Void>() {

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

    @Override
    protected void initWidget(Widget widget) {
        super.initWidget(widget);
              GWTServiceAsync serviceRPC = GWTService.App.getInstance();
        serviceRPC.listarRutas(usuarioLinea, new AsyncCallback<ArrayList<String>>() {

            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }

            @Override
            public void onSuccess(ArrayList<String> result) {


                for (String string : result) {
                    listaRutas.addItem(string.split(";")[1],string.split(";")[0]);
                }
            }
        });
    }



    
}
