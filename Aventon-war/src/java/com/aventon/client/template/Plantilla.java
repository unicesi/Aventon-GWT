/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aventon.client.template;

import com.aventon.client.GWTService;
import com.aventon.client.GWTServiceAsync;
import com.aventon.client.partesPagina.Bienvenido;
import com.aventon.client.partesPagina.CalificarAventon;
import com.aventon.client.partesPagina.ConsultarCarro;
import com.aventon.client.partesPagina.ConsultarCupoPorAventon;
import com.aventon.client.partesPagina.ConsultarRuta;
import com.aventon.client.partesPagina.CrearAventon;
import com.aventon.client.partesPagina.CrearCarro;
import com.aventon.client.partesPagina.CrearRuta;
import com.aventon.client.partesPagina.CrearUsuario;
import com.aventon.client.partesPagina.ModificarCuentaUsuario;
import com.aventon.client.partesPagina.PedirCupo;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.MenuItemSeparator;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import java.util.ArrayList;
/**
 *
 * @author Fabio
 */
public class Plantilla extends Composite implements ClickHandler {
    private static Plantilla instanciaPlantilla=null;
    private SimplePanel nuevoSP;
    public ArrayList<String> vehiculosUsuario = new ArrayList<String>();
    private TextBox textBoxNombreL;
    private PasswordTextBox passwordTextBox;
    private VerticalPanel verticalPanelWrap;
    private SimplePanel simplePanelTop;
    private SimplePanel simplePanelTopC;
    private HorizontalPanel horizontalPanelSesion;
    private SimplePanel simplePanelNavegacion;
    private SimplePanel simplePanelContenido;

    public static Plantilla getInstancePlantilla()
    {
        if(instanciaPlantilla==null)
        {
            instanciaPlantilla=new Plantilla();
        }
        return instanciaPlantilla;
    }

    private Plantilla() {
        verticalPanelWrap = new VerticalPanel();
        verticalPanelWrap.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        verticalPanelWrap.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        verticalPanelWrap.setStyleName("wrap");
        verticalPanelWrap.setSize("100%", "100%");

        simplePanelTop = new SimplePanel();
        simplePanelTop.getElement().setId("top");
        simplePanelTop.setStyleName("top");
        verticalPanelWrap.add(simplePanelTop);
        verticalPanelWrap.setCellHeight(simplePanelTop, "135");
        simplePanelTop.setHeight("182px");
        verticalPanelWrap.setCellHorizontalAlignment(simplePanelTop,
                HasHorizontalAlignment.ALIGN_CENTER);

        simplePanelTopC = new SimplePanel();
        simplePanelTopC.getElement().setId("topC");
        simplePanelTop.setWidget(simplePanelTopC);
        simplePanelTopC.setSize("500px", "51px");

        horizontalPanelSesion = new HorizontalPanel();
        simplePanelTopC.setWidget(horizontalPanelSesion);
        horizontalPanelSesion.setSize("100%", "100%");
        horizontalPanelSesion.getElement().setId("sesion");
        horizontalPanelSesion.setStyleName("panelSesion");
        horizontalPanelSesion.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        horizontalPanelSesion.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

        Label lblNewLabelNombre = new Label("Cedula");
        horizontalPanelSesion.add(lblNewLabelNombre);

        textBoxNombreL = new TextBox();
        horizontalPanelSesion.add(textBoxNombreL);

        Label lblNewLabelContrasena = new Label("Contrase\u00F1a");
        horizontalPanelSesion.add(lblNewLabelContrasena);

        passwordTextBox = new PasswordTextBox();
        horizontalPanelSesion.add(passwordTextBox);

        Button btnNewLogin = new Button("Entrar");
        btnNewLogin.addClickHandler(this);
        horizontalPanelSesion.add(btnNewLogin);

        simplePanelNavegacion = new SimplePanel();
        simplePanelNavegacion.setVisible(false);
        verticalPanelWrap.add(simplePanelNavegacion);
        verticalPanelWrap.setCellHeight(simplePanelNavegacion, "30px");

        MenuBar menuBar = new MenuBar(false);
        simplePanelNavegacion.setWidget(menuBar);
        menuBar.setSize("100%", "40px");
        menuBar.setStyleName("gwt-MenuBar");
        
        MenuItem mntmInicio = new MenuItem("Inicio", false, new Command() {

            @Override
            public void execute() {
                RootPanel.get("contenidoC").getElement().removeFromParent();
                nuevoSP = new SimplePanel();
                nuevoSP.getElement().setId("contenidoC");
                nuevoSP.add(new Bienvenido());
                RootPanel.get("contenido").add(nuevoSP);
            }
        });
        menuBar.addItem(mntmInicio);

        MenuItemSeparator separator = new MenuItemSeparator();
        menuBar.addSeparator(separator);
        MenuBar menuBar_1 = new MenuBar(true);

        MenuItem mntmCarros = new MenuItem("Carros", false, menuBar_1);

        MenuItem mntmCrearCarro = new MenuItem("Crear Carro", false,
                new Command() {

                    @Override
                    public void execute() {
                        RootPanel.get("contenidoC").getElement().removeFromParent();
                        nuevoSP = new SimplePanel();
                        nuevoSP.getElement().setId("contenidoC");
                        nuevoSP.add(new CrearCarro(cedulaIniciaSeccion));
                        RootPanel.get("contenido").add(nuevoSP);
                    }
                });
                 mntmCrearCarro.setStyleName("gwt-MenuBar gwt-MenuBar-vertical");
        menuBar_1.addItem(mntmCrearCarro);

        MenuItem mntmModificarCarro = new MenuItem("Consultar Carro", false,
                new Command() {

                    @Override
                    public void execute() {
                        RootPanel.get("contenidoC").getElement().removeFromParent();
                        nuevoSP = new SimplePanel();
                        nuevoSP.getElement().setId("contenidoC");
                        nuevoSP.add(new ConsultarCarro(cedulaIniciaSeccion));
                        RootPanel.get("contenido").add(nuevoSP);
                    }
                });
        menuBar_1.addItem(mntmModificarCarro);
          mntmModificarCarro.setStyleName("gwt-MenuBar gwt-MenuBar-vertical");
        menuBar.addItem(mntmCarros);

        MenuItemSeparator separator_1 = new MenuItemSeparator();
        menuBar.addSeparator(separator_1);
        MenuBar menuBar_2 = new MenuBar(true);

        MenuItem mntmRutas = new MenuItem("Rutas", false, menuBar_2);

        MenuItem mntmCrearRuta = new MenuItem("Crear Ruta", false,
                new Command() {

                    @Override
                    public void execute() {
                        RootPanel.get("contenidoC").getElement().removeFromParent();
                        nuevoSP = new SimplePanel();
                        nuevoSP.getElement().setId("contenidoC");
                        nuevoSP.add(new CrearRuta(cedulaIniciaSeccion));
                        RootPanel.get("contenido").add(nuevoSP);
                    }
                });
        menuBar_2.addItem(mntmCrearRuta);
   mntmCrearRuta.setStyleName("gwt-MenuBar gwt-MenuBar-vertical");
        MenuItem mntmConsultarRutas = new MenuItem("Consultar Rutas", false,
                new Command() {

                    @Override
                    public void execute() {
                         RootPanel.get("contenidoC").getElement().removeFromParent();
                        nuevoSP = new SimplePanel();
                        nuevoSP.getElement().setId("contenidoC");
                        nuevoSP.add(new ConsultarRuta(cedulaIniciaSeccion));
                        RootPanel.get("contenido").add(nuevoSP);
                    }
                });
        menuBar_2.addItem(mntmConsultarRutas);
        menuBar.addItem(mntmRutas);
   mntmConsultarRutas.setStyleName("gwt-MenuBar gwt-MenuBar-vertical");
        MenuItemSeparator separator_2 = new MenuItemSeparator();
        menuBar.addSeparator(separator_2);
        MenuBar menuBar_3 = new MenuBar(true);

        MenuItem mntmAventones = new MenuItem("Aventones", false, menuBar_3);

        MenuItem mntmTomarAventon = new MenuItem("Consultar  Cupo por Aventon", false,
                 new Command() {

                    @Override
                    public void execute() {
                        RootPanel.get("contenidoC").getElement().removeFromParent();
                        nuevoSP = new SimplePanel();
                        nuevoSP.getElement().setId("contenidoC");
                        nuevoSP.add(new ConsultarCupoPorAventon(cedulaIniciaSeccion));
                        RootPanel.get("contenido").add(nuevoSP);
                    }
                });


        menuBar_3.addItem(mntmTomarAventon);
 mntmTomarAventon.setStyleName("gwt-MenuBar gwt-MenuBar-vertical");
        MenuItem mntmCrearAventones = new MenuItem("Crear Aventones", false,
                new Command() {

                    @Override
                    public void execute() {
                        RootPanel.get("contenidoC").getElement().removeFromParent();
                        nuevoSP = new SimplePanel();
                        nuevoSP.getElement().setId("contenidoC");
                        nuevoSP.add(new CrearAventon(cedulaIniciaSeccion));
                        RootPanel.get("contenido").add(nuevoSP);
                    }
                });
        menuBar_3.addItem(mntmCrearAventones);
 mntmCrearAventones.setStyleName("gwt-MenuBar gwt-MenuBar-vertical");
         MenuItem calificarAv = new MenuItem("Calificar Aventones", false,
                new Command() {

                    @Override
                    public void execute() {
                        RootPanel.get("contenidoC").getElement().removeFromParent();
                        nuevoSP = new SimplePanel();
                        nuevoSP.getElement().setId("contenidoC");
                        nuevoSP.add(new CalificarAventon(cedulaIniciaSeccion));
                        RootPanel.get("contenido").add(nuevoSP);
                    }
                });
        menuBar_3.addItem(calificarAv);
 calificarAv.setStyleName("gwt-MenuBar gwt-MenuBar-vertical");
              MenuItem pedirCUpo = new MenuItem("Pedir Cupo ", false,
                new Command() {

                    @Override
                    public void execute() {
                        RootPanel.get("contenidoC").getElement().removeFromParent();
                        nuevoSP = new SimplePanel();
                        nuevoSP.getElement().setId("contenidoC");
                        nuevoSP.add(new PedirCupo(cedulaIniciaSeccion));
                        RootPanel.get("contenido").add(nuevoSP);
                    }
                });
        menuBar_3.addItem(pedirCUpo);
        pedirCUpo.setStyleName("gwt-MenuBar gwt-MenuBar-vertical");
        menuBar.addItem(mntmAventones);

        MenuItemSeparator separator_3 = new MenuItemSeparator();
        menuBar.addSeparator(separator_3);

        MenuItem mntmDatosPersonales = new MenuItem("Datos Personales", false,
                new Command() {

                    @Override
                    public void execute() {
                        RootPanel.get("contenidoC").getElement().removeFromParent();
                        nuevoSP = new SimplePanel();
                        nuevoSP.getElement().setId("contenidoC");
                        nuevoSP.add(new ModificarCuentaUsuario(cedulaIniciaSeccion));
                        RootPanel.get("contenido").add(nuevoSP);
                    }
                });
        menuBar.addItem(mntmDatosPersonales);
 
        simplePanelContenido = new SimplePanel();
        simplePanelContenido.setStyleName("contenido");
        simplePanelContenido.getElement().setId("contenido");
        verticalPanelWrap.add(simplePanelContenido);
        verticalPanelWrap.setCellHeight(simplePanelContenido, "100%");
        verticalPanelWrap.setCellWidth(simplePanelContenido, "100%");
        verticalPanelWrap.setCellVerticalAlignment(simplePanelContenido,
                HasVerticalAlignment.ALIGN_MIDDLE);
        simplePanelContenido.setSize("100%", "100%");

        HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        simplePanelContenido.setWidget(horizontalPanel);
        horizontalPanel.setSize("100%", "100%");

        horizontalPanel.setSize("100%", "100%");
        CrearUsuario crearUsuario = new CrearUsuario();
        horizontalPanel.add(crearUsuario);
        horizontalPanel.getElement().setId("contenidoC");

        initWidget(verticalPanelWrap);
    }


    
    private String cedulaIniciaSeccion = "";

    @Override
    public void onClick(ClickEvent event) {
        GWTServiceAsync servicioRPC = GWTService.App.getInstance();

        servicioRPC.iniciarSesion(textBoxNombreL.getText(), passwordTextBox.getText(), new AsyncCallback<Boolean>() {

            @Override
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void onSuccess(Boolean result) {
                if (result) {
                    RootPanel.get("sesion").getElement().removeFromParent();
                      Button cerrar=new Button("Cerrar Sesion");
                    nuevoSP = new SimplePanel();
                    nuevoSP.getElement().setId("sesion");
                    HorizontalPanel hor=new HorizontalPanel();
                    hor.add(new Label("Bievenido " + textBoxNombreL.getText()));
                    hor.add(cerrar);
                    nuevoSP.add(hor);
                  
                    
                 
                  
                    cerrar.addClickHandler(new ClickHandler() {

                        @Override
                        public void onClick(ClickEvent event) {
                                RootPanel rootPanel=RootPanel.get();
                                rootPanel.clear();
                                rootPanel.add(new Plantilla());
                        }
                    });

                    cedulaIniciaSeccion = textBoxNombreL.getText();
                   
                    RootPanel.get("topC").add(nuevoSP);
                    Window.alert("Autenticado");
                    simplePanelNavegacion.setVisible(true);
                    RootPanel.get("contenidoC").getElement().removeFromParent();
                nuevoSP = new SimplePanel();
                nuevoSP.getElement().setId("contenidoC");
                nuevoSP.add(new Bienvenido());
                RootPanel.get("contenido").add(nuevoSP);

                
                } else {
                    Window.alert("Los Datos estan Incorrectos");
                }
            }
        });

    }
}
