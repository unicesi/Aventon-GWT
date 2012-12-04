package com.aventon.client.partesPagina;

import com.aventon.client.GWTService;
import com.aventon.client.GWTServiceAsync;
import com.aventon.client.util.Parser;
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


/**import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
*/
import java.util.Date;
import java.util.Iterator;

public class PedirCupo extends Composite implements ClickHandler {

    private TextBox textBoxRuta;
    private ListBox comboBoxPtoFin;
    private ListBox comboBoxPtoInicio;
    private ListBox comboBox;
    private Button btnPedir = new Button("New button");
    private String ced;
    public ArrayList<PuntoRuta> puntosRuta = new ArrayList<PuntoRuta>();
    public MapWidget mapW;
    private final String SMTP_HOST_NAME = "smtp.gmail.com";
    private final String SMTP_PORT = "465";
    private final String emailMsgTxt = "Test Message Contents";
    private final String emailSubjectTxt = "A test from gmail";
    private final String emailFromAddress = "aventon.icesi@gmail.com";
    private final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
 
/**
    private void mandarEmail(String sendto) {

   String [] recieper=new String[1];
   recieper[0]=sendto;

        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        try {
            sendSSLMessage(recieper,"Notificacion Aventon" , "El usuario "+ced +" desea aplicar a un Aventon Ofrecido por ud", emailFromAddress);
        } catch (MessagingException ex) {
            Logger.getLogger(PedirCupo.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Sucessfully Sent mail to All Users");

    }

    private void sendSSLMessage(String recipients[], String subject,
            String message, String from) throws MessagingException {
        boolean debug = true;

        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.socketFactory.port", SMTP_PORT);
        props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.put("mail.smtp.socketFactory.fallback", "false");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("aventon.icesi@gmail.com", "aventon2011");
                    }
                });

        session.setDebug(debug);

        Message msg = new MimeMessage(session);
        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);

        InternetAddress[] addressTo = new InternetAddress[recipients.length];
        for (int i = 0; i < recipients.length; i++) {
            addressTo[i] = new InternetAddress(recipients[i]);
        }
        msg.setRecipients(Message.RecipientType.TO, addressTo);

// Setting the Subject and Content Type
        msg.setSubject(subject);
        msg.setContent(message, "text/plain");
        Transport.send(msg);
    }
*/
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


        verticalPanelDatosFormulario.add(map);


    }

    /**
     * @wbp.parser.entryPoint
     */
    public Marker crearMarcador(final String descripcion, final String direccion, final LatLng coordenadas, final MapWidget map, final String ruta) {

        final Marker mrk = new Marker(coordenadas);

        mrk.addMarkerClickHandler(new MarkerClickHandler() {

            public void onClick(MarkerClickEvent event) {
                comboBox.clear();
                textBoxRuta.setText(ruta);
                GWTServiceAsync serviceRPC = GWTService.App.getInstance();

                serviceRPC.aventones(ruta, new AsyncCallback<ArrayList<String>>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    @Override
                    public void onSuccess(ArrayList<String> result) {

                        for (Iterator<String> it = result.iterator(); it.hasNext();) {

                            String string = it.next();


                            comboBox.addItem(string.split(";")[6], string.split(";")[0] + ";" + string.split(";")[1]);


                        }
                        map.getInfoWindow().open(coordenadas, new InfoWindowContent(descripcion + "," + direccion));

                    }
                });

            }
        });

        return mrk;

    }
    public VerticalPanel verticalPanelDatosFormulario = new VerticalPanel();

    public PedirCupo(String ced) {
        this.ced = ced;
        Maps.loadMapsApi("", "2", false, new Runnable() {

            public void run() {
                construirMapa();
            }
        });
        FormPanel formPanelRegistro = new FormPanel();
    formPanelRegistro.setSize("500px", "100%");
        formPanelRegistro.setStyleName("formulario");

        verticalPanelDatosFormulario.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        formPanelRegistro.setWidget(verticalPanelDatosFormulario);
        verticalPanelDatosFormulario.setSize("100%", "100%");
        verticalPanelDatosFormulario.setStyleName((String) null);

        Label lblConsultarCarro = new Label("Pedir Cupo");
        verticalPanelDatosFormulario.add(lblConsultarCarro);

        Grid gridDatosRegistro = new Grid(5, 2);
       gridDatosRegistro.setStyleName("tablaFormularios");
        verticalPanelDatosFormulario.add(gridDatosRegistro);
        gridDatosRegistro.setSize("100%", "100%");

        Label lblPlaca = new Label("Punto de Inicio");
        gridDatosRegistro.setWidget(0, 0, lblPlaca);

        comboBoxPtoInicio = new ListBox();
        gridDatosRegistro.setWidget(0, 1, comboBoxPtoInicio);
        comboBoxPtoInicio.setWidth("168px");

        Label lblModelo = new Label("Punto de Fin");
        gridDatosRegistro.setWidget(1, 0, lblModelo);

        comboBoxPtoFin = new ListBox();
        gridDatosRegistro.setWidget(1, 1, comboBoxPtoFin);
        comboBoxPtoFin.setWidth("170px");

        Label lblMarca = new Label("Ruta");
        gridDatosRegistro.setWidget(2, 0, lblMarca);

        textBoxRuta = new TextBox();
        gridDatosRegistro.setWidget(2, 1, textBoxRuta);

        Label lblTipoCombustible = new Label("Aventones");
        gridDatosRegistro.setWidget(3, 0, lblTipoCombustible);

        comboBox = new ListBox();
        comboBox.setStyleName("ComboBoxAventones");
        gridDatosRegistro.setWidget(3, 1, comboBox);
        comboBox.setWidth("172px");

        Button btnConsultar = new Button("New button");
        btnConsultar.setText("Registrar Cupo");
        gridDatosRegistro.setWidget(4, 1, btnConsultar);

        btnConsultar.setWidth("170px");


        btnPedir.setText("Consultar Ruta");
        gridDatosRegistro.setWidget(4, 0, btnPedir);

        btnPedir.setWidth("170px");
        btnPedir.addClickHandler(this);
        btnConsultar.addClickHandler(this);
        initWidget(formPanelRegistro);
    }

    @Override
    public void onClick(ClickEvent event) {

        Button source = (Button) event.getSource();
        if (source.getText().equals("Consultar Ruta")) {
            GWTServiceAsync serviceRPC = GWTService.App.getInstance();
            serviceRPC.RutaPuntos(new Integer(comboBoxPtoInicio.getValue(comboBoxPtoInicio.getSelectedIndex())), new Integer(comboBoxPtoFin.getValue(comboBoxPtoFin.getSelectedIndex())), new AsyncCallback<ArrayList<String>>() {

                @Override
                public void onFailure(Throwable caught) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
                public void onSuccess(ArrayList<String> result) {
                    for (final String string : result) {

                        puntosRuta.clear();
                        mapW.clearOverlays();
                        GWTServiceAsync serviceRPC = GWTService.App.getInstance();
                        serviceRPC.puntosRuta(string, new AsyncCallback<ArrayList<String>>() {

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
                                    pr.setCodigo(string);
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
                }
            });
        } else {


            String cupo = Parser.cupo2String(new Date(new Long(comboBox.getValue(comboBox.getSelectedIndex()).split(";")[0]).longValue()), Integer.parseInt(comboBox.getValue(comboBox.getSelectedIndex()).split(";")[1]), ced, 0, 0, 0, 0, "Comentario");

            GWTServiceAsync serviceRPC = GWTService.App.getInstance();
            serviceRPC.guardarCupo(cupo, new AsyncCallback<Void>() {

                @Override
                public void onFailure(Throwable caught) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
                public void onSuccess(Void result) {
                    Window.alert("Agregado Con Exito");
                    RootPanel.get("contenidoC").getElement().removeFromParent();
               SimplePanel nuevoSP = new SimplePanel();
                nuevoSP.getElement().setId("contenidoC");
                nuevoSP.add(new PedirCupo(ced));
                RootPanel.get("contenido").add(nuevoSP);
                   
                }
            });
        }
    }

    @Override
    protected void initWidget(Widget widget) {
        super.initWidget(widget);

        GWTServiceAsync serviceRPC = GWTService.App.getInstance();
        serviceRPC.listarPuntosI(new AsyncCallback<ArrayList<String>>() {

            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }

            @Override
            public void onSuccess(ArrayList<String> result) {


                for (String string : result) {
                    comboBoxPtoInicio.addItem(string.split(";")[2], string.split(";")[0]);
                    comboBoxPtoFin.addItem(string.split(";")[2], string.split(";")[0]);

                }
            }
        });

    }
}
