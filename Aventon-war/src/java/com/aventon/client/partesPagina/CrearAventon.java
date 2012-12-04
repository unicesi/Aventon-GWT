package com.aventon.client.partesPagina;

import com.aventon.client.GWTService;
import com.aventon.client.GWTServiceAsync;
import com.aventon.client.util.Parser;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.control.MapTypeControl;
import com.google.gwt.maps.client.control.ScaleControl;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.MapClickHandler.MapClickEvent;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.event.MarkerClickHandler.MarkerClickEvent;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.Polyline;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import java.util.ArrayList;
import java.util.Collections;

public class CrearAventon extends Composite implements ClickHandler {

    private ListBox comboBoxPlacas;
    private RadioButton rdbtnDesviosSi;
    private RadioButton rdbtnDesviosNo;
    private Button btnGuardarAventon;
    private ListBox comboBoxRuta;
    private DateBox dateBoxFechaSalida;
    private DateBox dateBoxFechaLlegada;
    private ListBox comboBoxNumCupos;
    private TextBox textBoxDescripcion;
    private String ced;
    public   VerticalPanel verticalPanelDatosFormulario = new VerticalPanel();
      public ArrayList<PuntoRuta> puntosRuta = new ArrayList<PuntoRuta>();
    public MapWidget mapW;

     private FormPanel formPanelRegistro = new FormPanel();
    public CrearAventon(String us) {

           

        Maps.loadMapsApi("", "2", false, new Runnable() {

            public void run() {
                construirMapa();
            }
        });

        this.ced = us;
        FormPanel formPanelRegistro = new FormPanel();
        formPanelRegistro.setSize("500px", "100%");
        formPanelRegistro.setStyleName("panelSesion");

       
        verticalPanelDatosFormulario.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        formPanelRegistro.setWidget(verticalPanelDatosFormulario);
        verticalPanelDatosFormulario.setSize("100%", "100%");
        verticalPanelDatosFormulario.setStyleName((String) null);

        Label lblCrearCarro = new Label("Crear Aventon");
        lblCrearCarro.setStyleName("gwt-LabelTitulos");
        verticalPanelDatosFormulario.add(lblCrearCarro);

        Grid gridDatosRegistro = new Grid(7, 2);
        gridDatosRegistro.setStyleName("tablaFormularios");
        verticalPanelDatosFormulario.add(gridDatosRegistro);
        gridDatosRegistro.setSize("100%", "100%");

        Label lblRuta = new Label("Descripci\u00F3n de ruta a elegir:");
        gridDatosRegistro.setWidget(0, 0, lblRuta);

        comboBoxRuta = new ListBox();
        gridDatosRegistro.setWidget(0, 1, comboBoxRuta);
        comboBoxRuta.setWidth("155");

        Label lblPlacaCarro = new Label("Placa de carro");
        lblPlacaCarro.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        gridDatosRegistro.setWidget(1, 0, lblPlacaCarro);

        Label lblFechaSalida = new Label("Fecha de salida");
        lblFechaSalida.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        gridDatosRegistro.setWidget(2, 0, lblFechaSalida);

        dateBoxFechaSalida = new DateBox();
        gridDatosRegistro.setWidget(2, 1, dateBoxFechaSalida);
        dateBoxFechaSalida.setWidth("155");

        Label lblFechaLlegada = new Label("Fecha de llegada");
        gridDatosRegistro.setWidget(3, 0, lblFechaLlegada);

        dateBoxFechaLlegada = new DateBox();
        gridDatosRegistro.setWidget(3, 1, dateBoxFechaLlegada);
        dateBoxFechaLlegada.setWidth("155");

        Label lblNumCupos = new Label("N\u00FAmero de cupos");
        lblNumCupos.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        gridDatosRegistro.setWidget(4, 0, lblNumCupos);

        comboBoxPlacas = new ListBox();

        gridDatosRegistro.setWidget(1, 1, comboBoxPlacas);
        comboBoxPlacas.setWidth("155");
       

        comboBoxNumCupos = new ListBox();
        gridDatosRegistro.setWidget(4, 1, comboBoxNumCupos);
        comboBoxNumCupos.setWidth("155");

        Label lblDesvios = new Label("Acepta desvios");
        lblDesvios.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        gridDatosRegistro.setWidget(5, 0, lblDesvios);
        HorizontalPanel horizontalPanel = new HorizontalPanel();
        gridDatosRegistro.setWidget(5, 1, horizontalPanel);

        rdbtnDesviosSi = new RadioButton("new name", "Si");
        rdbtnDesviosSi.setChecked(true);
        horizontalPanel.add(rdbtnDesviosSi);

        rdbtnDesviosNo = new RadioButton("new name", "No");
        horizontalPanel.add(rdbtnDesviosNo);

        Label lblDescripcion = new Label("Descripcion");
        lblDescripcion.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        gridDatosRegistro.setWidget(6, 0, lblDescripcion);

        textBoxDescripcion = new TextBox();
        gridDatosRegistro.setWidget(6, 1, textBoxDescripcion);
        textBoxDescripcion.setWidth("155");

                btnGuardarAventon = new Button("Registrar");
                verticalPanelDatosFormulario.add(btnGuardarAventon);
                btnGuardarAventon.setText("Guardar Cambios");
                btnGuardarAventon.addClickHandler(this);
           comboBoxRuta.addChangeHandler(new ChangeHandler() {

            @Override
            public void onChange(ChangeEvent event) {
                GWTServiceAsync servicioRPC = GWTService.App.getInstance();
                servicioRPC.puntosRuta(comboBoxRuta.getValue(comboBoxRuta.getSelectedIndex())+"", new AsyncCallback<ArrayList<String>>() {

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
        initWidget(formPanelRegistro);

    }

    @Override
    public void onClick(ClickEvent event) {
        GWTServiceAsync serviceRPC = GWTService.App.getInstance();
        serviceRPC.crearAventon(Parser.aventon2String(dateBoxFechaSalida.getValue(), Integer.parseInt(comboBoxRuta.getValue(comboBoxRuta.getSelectedIndex())), "0", dateBoxFechaLlegada.getValue(), Integer.parseInt(comboBoxNumCupos.getValue(comboBoxNumCupos.getSelectedIndex())), rdbtnDesviosSi.getValue(), textBoxDescripcion.getText(), comboBoxPlacas.getValue(comboBoxPlacas.getSelectedIndex())), new AsyncCallback<String>() {

            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Error");
            }

            @Override
            public void onSuccess(String result) {
                Window.alert(result);
                RootPanel.get("contenidoC").getElement().removeFromParent();
               SimplePanel nuevoSP = new SimplePanel();
                nuevoSP.getElement().setId("contenidoC");
                nuevoSP.add(new CrearAventon(ced));
                RootPanel.get("contenido").add(nuevoSP);
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

        map.setWidth("400px");

        map.setHeight("400px");

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

       
        verticalPanelDatosFormulario.add(map);


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

        serviceRPC.listarRutas(ced, new AsyncCallback<ArrayList<String>>() {

            @Override
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void onSuccess(ArrayList<String> result) {
                for (String string : result) {
                    comboBoxRuta.addItem(string.split(";")[1], string.split(";")[0]);
                }
                GWTServiceAsync serviceRPC = GWTService.App.getInstance();

                serviceRPC.puntosRuta(comboBoxRuta.getValue(0), new AsyncCallback<ArrayList<String>>() {

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
        serviceRPC.listarCarro(ced, new AsyncCallback<ArrayList<String>>() {

            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }

            @Override
            public void onSuccess(ArrayList<String> result) {


                for (String string : result) {
                    comboBoxPlacas.addItem(string);
                }
            }
        });
        comboBoxNumCupos.addItem("1");
        comboBoxNumCupos.addItem("2");
        comboBoxNumCupos.addItem("3");
        comboBoxNumCupos.addItem("4");
      
       
    }
}
