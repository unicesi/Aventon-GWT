package com.aventon.client.partesPagina;

import com.aventon.client.GWTService;
import com.aventon.client.GWTServiceAsync;
import com.aventon.client.util.Parser;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.Maps;
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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ConsultarCupoPorAventon extends Composite implements ClickHandler {

    private String cedulaUser;
    VerticalPanel verticalPanelDatosFormulario = new VerticalPanel();
    FormPanel formPanelRegistro = new FormPanel();

    @Override
    protected void initWidget(Widget widget) {
        super.initWidget(widget);

        GWTServiceAsync serviceRPC = GWTService.App.getInstance();
        serviceRPC.listarRutas(cedulaUser, new AsyncCallback<ArrayList<String>>() {

            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }

            @Override
            public void onSuccess(ArrayList<String> result) {


                for (String string : result) {
                    comboBoxRutas.addItem(string.split(";")[1], string.split(";")[0]);
                }
            }
        });





    }
    ListBox comboBoxRutas = new ListBox();
    TextBox fechaSalida = new TextBox();
    ListBox comboBoxAventones = new ListBox();
    TextBox fechaLLegada = new TextBox();
    TextBox numeroCupos = new TextBox();

    public ConsultarCupoPorAventon(String cedulaUser) {


        Maps.loadMapsApi("", "2", false, new Runnable() {

            public void run() {
                construirMapa();
            }
        });

        this.cedulaUser = cedulaUser;

        formPanelRegistro.setSize("500px", "100%");
        formPanelRegistro.setStyleName("formulario");

        verticalPanelDatosFormulario.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        formPanelRegistro.setWidget(verticalPanelDatosFormulario);
        verticalPanelDatosFormulario.setSize("100%", "100%");
        verticalPanelDatosFormulario.setStyleName((String) null);


        Label lblCrearCarro = new Label("Consultar Cupo Por Aventon");
        verticalPanelDatosFormulario.add(lblCrearCarro);

        Grid gridDatosRegistro = new Grid(13, 2);
        gridDatosRegistro.setStyleName("tablaFormularios");
        verticalPanelDatosFormulario.add(gridDatosRegistro);
        gridDatosRegistro.setSize("100%", "100%");


        Label lblRuta = new Label("Ruta");
        gridDatosRegistro.setWidget(0, 0, lblRuta);


        gridDatosRegistro.setWidget(0, 1, comboBoxRutas);
        comboBoxRutas.addItem(" ");

        Button botonConsultarRutaPorAv = new Button("New button");
        botonConsultarRutaPorAv.setText("Consultar Aventones de la Ruta");
        botonConsultarRutaPorAv.addClickHandler(this);
        gridDatosRegistro.setWidget(1, 0, botonConsultarRutaPorAv);

        Label lblAventon = new Label("Aventon");
        gridDatosRegistro.setWidget(2, 0, lblAventon);


        gridDatosRegistro.setWidget(2, 1, comboBoxAventones);

        Button btnConsultarAventon = new Button("New button");
        btnConsultarAventon.setText("Consultar Avent\u00F3n");
        gridDatosRegistro.setWidget(3, 1, btnConsultarAventon);
        btnConsultarAventon.addClickHandler(this);

        Label lblSalida = new Label("Fecha Salida");
        gridDatosRegistro.setWidget(4, 0, lblSalida);


        fechaSalida.setEnabled(false);
        gridDatosRegistro.setWidget(4, 1, fechaSalida);

        Label lblFechaLlegada = new Label("Fecha Llegada");
        gridDatosRegistro.setWidget(5, 0, lblFechaLlegada);


        fechaLLegada.setEnabled(false);
        gridDatosRegistro.setWidget(5, 1, fechaLLegada);

        Label lblNumeroCupos = new Label("N\u00FAmero de cupos");
        gridDatosRegistro.setWidget(6, 0, lblNumeroCupos);


        numeroCupos.setEnabled(false);
        gridDatosRegistro.setWidget(6, 1, numeroCupos);

        Label lblDesvios = new Label("Desvios");
        gridDatosRegistro.setWidget(7, 0, lblDesvios);

        desvios.setEnabled(false);
        gridDatosRegistro.setWidget(7, 1, desvios);

        Label lblDesc = new Label("Descripci\u00F3n");
        gridDatosRegistro.setWidget(8, 0, lblDesc);


        descripcion.setEnabled(false);
        gridDatosRegistro.setWidget(8, 1, descripcion);

        Label lblPlaca = new Label("Placa Veh\u00EDculo");
        gridDatosRegistro.setWidget(9, 0, lblPlaca);


        vehiculo.setEnabled(false);
        gridDatosRegistro.setWidget(9, 1, vehiculo);
        vehiculo.setSize("169px", "27px");



        Button btnConsultarCuposPara = new Button("New button");
        btnConsultarCuposPara.setText("Consultar Cupos para Este Avent\u00F3n");
        gridDatosRegistro.setWidget(10, 1, btnConsultarCuposPara);

        btnConsultarCuposPara.addClickHandler(this);
        Label lblCuposConsultados = new Label("Cupos Asociados al Avent\u00F3n");
        gridDatosRegistro.setWidget(11, 0, lblCuposConsultados);


        gridDatosRegistro.setWidget(11, 1, cupos);

        Button btnRechazarCupo = new Button("Rechazar Cupo");
        gridDatosRegistro.setWidget(12, 0, btnRechazarCupo);
        btnRechazarCupo.addClickHandler(this);


        Button btnAceptarCupo = new Button("New button");
        btnAceptarCupo.setText("Aceptar Cupo");
        gridDatosRegistro.setWidget(12, 1, btnAceptarCupo);
        btnAceptarCupo.setWidth("155px");
        btnAceptarCupo.addClickHandler(this);
        comboBoxRutas.addChangeHandler(new ChangeHandler() {

            public void onChange(ChangeEvent event) {

                puntosRuta.clear();
                mapW.clearOverlays();
                GWTServiceAsync serviceRPC = GWTService.App.getInstance();
                serviceRPC.puntosRuta(comboBoxRutas.getValue(comboBoxRutas.getSelectedIndex()), new AsyncCallback<ArrayList<String>>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    @Override
                    public void onSuccess(ArrayList<String> result) {
                        for (String string1 : result) {
                            String latitud = string1.split(";")[3];
                            String longitud = string1.split(";")[4];
                            String descripcion = string1.split(";")[2];
                            String direccion = string1.split(";")[1];
                            String indice = string1.split(";")[5];
                            PuntoRuta pr = new PuntoRuta(LatLng.newInstance(new Double(latitud), new Double(longitud)), descripcion, direccion, Integer.parseInt(indice));
                            pr.setCodigo(comboBoxRutas.getValue(comboBoxRutas.getSelectedIndex()));
                            puntosRuta.add(pr);
                            mapW.addOverlay(crearMarcador(descripcion, direccion, LatLng.newInstance(new Double(latitud), new Double(longitud)), mapW, pr.getCodigo()));



                            Collections.sort(puntosRuta, new PuntoRuta());

                            LatLng[] puntosI = new LatLng[puntosRuta.size()];

                            for (int i = 0; i < puntosRuta.size(); i++) {
                                puntosI[i] = puntosRuta.get(i).getCoordenadas();
                            }

                            mapW.addOverlay(new Polyline(puntosI));
                        }
                    }
                });

            }
        });
        initWidget(formPanelRegistro);




    }
    TextBox desvios = new TextBox();
    TextBox vehiculo = new TextBox();
    TextBox descripcion = new TextBox();
    ListBox cupos = new ListBox();

    @Override
    public void onClick(ClickEvent event) {

        Button bt = (Button) event.getSource();

        if (bt.getText().equals("Consultar Aventones de la Ruta")) {
            comboBoxAventones.clear();

            GWTServiceAsync serviceRPC = GWTService.App.getInstance();

            serviceRPC.aventones(comboBoxRutas.getValue(comboBoxRutas.getSelectedIndex()), new AsyncCallback<ArrayList<String>>() {

                @Override
                public void onFailure(Throwable caught) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
                public void onSuccess(ArrayList<String> result) {

                    for (Iterator<String> it = result.iterator(); it.hasNext();) {

                        String string = it.next();


                        comboBoxAventones.addItem(string.split(";")[6], string.split(";")[0] + ";" + string.split(";")[1]);


                    }


                }
            });
        }

        if (bt.getText().equals("Consultar Avent\u00F3n")) {
            GWTServiceAsync serviceRPC = GWTService.App.getInstance();
            serviceRPC.consultarAventon(Parser.aventonPK2String(new Date(new Long(comboBoxAventones.getValue(comboBoxAventones.getSelectedIndex()).split(";")[0]).longValue()), Integer.parseInt(comboBoxAventones.getValue(comboBoxAventones.getSelectedIndex()).split(";")[1])), new AsyncCallback<String>() {

                @Override
                public void onFailure(Throwable caught) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
                public void onSuccess(String result) {
                    fechaSalida.setText(new Date(new Long(comboBoxAventones.getValue(comboBoxAventones.getSelectedIndex()).split(";")[0]).longValue()) + "");
                    fechaLLegada.setText(new Date(Long.parseLong(result.split(";")[3])) + "");
                    numeroCupos.setText(result.split(";")[4]);
                    descripcion.setText(result.split(";")[6]);
                    vehiculo.setText(result.split(";")[7]);
                    Boolean bol = new Boolean(result.split(";")[5]);
                    if (bol) {
                        desvios.setText("SI");
                    } else {
                        desvios.setText("NO");
                    }
                }
            });
        }
        if (bt.getText().equals("Consultar Cupos para Este Avent\u00F3n")) {
            GWTServiceAsync serviceRPC = GWTService.App.getInstance();
            serviceRPC.cupos(Parser.aventonPK2String(new Date(new Long(comboBoxAventones.getValue(comboBoxAventones.getSelectedIndex()).split(";")[0]).longValue()), Integer.parseInt(comboBoxAventones.getValue(comboBoxAventones.getSelectedIndex()).split(";")[1])), new AsyncCallback<ArrayList<String>>() {

                @Override
                public void onFailure(Throwable caught) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
                public void onSuccess(ArrayList<String> result) {
                    cupos.clear();

                    for (Iterator<String> it = result.iterator(); it.hasNext();) {
                        final String string = it.next();
                        GWTServiceAsync serviceRPC = GWTService.App.getInstance();
                        serviceRPC.consultarUsuario(string.split(";")[2], new AsyncCallback<String>() {

                            @Override
                            public void onFailure(Throwable caught) {
                                throw new UnsupportedOperationException("Not supported yet.");
                            }

                            @Override
                            public void onSuccess(String result2) {
                                cupos.addItem(result2.split(";")[1] + " " + result2.split(";")[2], string.split(";")[0] + ";" + string.split(";")[1] + ";"+ string.split(";")[2]);
                            }
                        });

                    }
                }
            });
        }
        if (bt.getText().equals("Rechazar Cupo")) {
              GWTServiceAsync serviceRPC = GWTService.App.getInstance();
              serviceRPC.eliminarCupo(Parser.cupoPK2String(new Date(Long.parseLong(cupos.getValue(cupos.getSelectedIndex()).split(";")[0])), Integer.parseInt(cupos.getValue(cupos.getSelectedIndex()).split(";")[1]), (cupos.getValue(cupos.getSelectedIndex()).split(";")[2])), new AsyncCallback<Void>() {

                @Override
                public void onFailure(Throwable caught) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
                public void onSuccess(Void result) {
                   Window.alert("Cupo Rechazado");
                   cupos.clear();
                   RootPanel.get("contenidoC").getElement().removeFromParent();
               SimplePanel nuevoSP = new SimplePanel();
                nuevoSP.getElement().setId("contenidoC");
                nuevoSP.add(new ConsultarCupoPorAventon(cedulaUser));
                RootPanel.get("contenido").add(nuevoSP);
                }
            });
        }
         if (bt.getText().equals("Aceptar Cupo")) {
             Window.alert("Cupo Aceptado");

                   cupos.clear();
                     RootPanel.get("contenidoC").getElement().removeFromParent();
               SimplePanel nuevoSP = new SimplePanel();
                nuevoSP.getElement().setId("contenidoC");
                nuevoSP.add(new ConsultarCupoPorAventon(cedulaUser));
                RootPanel.get("contenido").add(nuevoSP);
         }
    }
    public ArrayList<PuntoRuta> puntosRuta = new ArrayList<PuntoRuta>();
    public MapWidget mapW;
    private String rutaUno;

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

        map.setZoomLevel(18);

        //añadimos control selector de tipo de mapa

        map.addControl(new MapTypeControl());

        //añadimos control de desplazamiento con zoom

        map.addControl(new LargeMapControl());

        //añadimos control escala de mapa

        map.addControl(new ScaleControl());

        //cambiamos a vista mapa satelital

        map.setCurrentMapType(MapType.getHybridMap());

        //creamos un marcador en la coordenadas (-18.0376, -70.2506)
        puntosRuta.clear();
        mapW.clearOverlays();
        GWTServiceAsync serviceRPC = GWTService.App.getInstance();

        serviceRPC.listarRutas(cedulaUser, new AsyncCallback<ArrayList<String>>() {

            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }

            @Override
            public void onSuccess(ArrayList<String> result) {

                rutaUno = result.get(0);

            }
        });
        serviceRPC.puntosRuta(rutaUno, new AsyncCallback<ArrayList<String>>() {

            @Override
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void onSuccess(ArrayList<String> result) {
                for (String string1 : result) {
                    String latitud = string1.split(";")[3];
                    String longitud = string1.split(";")[4];
                    String descripcion = string1.split(";")[2];
                    String direccion = string1.split(";")[1];
                    String indice = string1.split(";")[5];
                    Window.alert(comboBoxRutas.getValue(0));
                    PuntoRuta pr = new PuntoRuta(LatLng.newInstance(new Double(latitud), new Double(longitud)), descripcion, direccion, Integer.parseInt(indice));
                    pr.setCodigo(comboBoxRutas.getValue(0));
                    puntosRuta.add(pr);
                    mapW.addOverlay(crearMarcador(descripcion, direccion, LatLng.newInstance(new Double(latitud), new Double(longitud)), mapW, pr.getCodigo()));



                    Collections.sort(puntosRuta, new PuntoRuta());

                    LatLng[] puntosI = new LatLng[puntosRuta.size()];

                    for (int i = 0; i < puntosRuta.size(); i++) {
                        puntosI[i] = puntosRuta.get(i).getCoordenadas();
                    }

                    mapW.addOverlay(new Polyline(puntosI));
                }
            }
        });

        verticalPanelDatosFormulario.add(map);


    }

    /**
     * @wbp.parser.entryPoint
     */
    public Marker crearMarcador(final String descripcion, final String direccion, final LatLng coordenadas, final MapWidget map, final String ruta) {

        final Marker mrk = new Marker(coordenadas);

        mrk.addMarkerClickHandler(new MarkerClickHandler() {

            public void onClick(MarkerClickEvent event) {


                map.getInfoWindow().open(coordenadas, new InfoWindowContent(descripcion + "," + direccion));

            }
        });




        return mrk;

    }
}
