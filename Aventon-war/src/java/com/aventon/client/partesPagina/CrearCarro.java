package com.aventon.client.partesPagina;

import com.aventon.client.GWTService;
import com.aventon.client.GWTServiceAsync;
import com.aventon.client.util.Parser;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CrearCarro extends Composite implements ClickHandler {

    private TextBox textBoxMarca;
    private TextBox textBoxModelo;
    private TextBox textBoxPlaca;
    private TextBox textBoxColor;
    private ListBox comboBoxTipoCombustible;
    private RadioButton rdbtnAireSi;
    private RadioButton rdbtnAireNo;
    private Button btnGuardarCarro;
    private String cedulaUser;

    public CrearCarro(String cedulaUser) {

        this.cedulaUser = cedulaUser;
        FormPanel formPanelRegistro = new FormPanel();
        formPanelRegistro.setSize("500px", "100%");
        formPanelRegistro.setStyleName("formulario");

        VerticalPanel verticalPanelDatosFormulario = new VerticalPanel();
        verticalPanelDatosFormulario.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        formPanelRegistro.setWidget(verticalPanelDatosFormulario);
        verticalPanelDatosFormulario.setSize("100%", "100%");
        verticalPanelDatosFormulario.setStyleName((String) null);

        Label lblCrearCarro = new Label("Crear Carro");
        lblCrearCarro.setStyleName("gwt-LabelTitulos");
        verticalPanelDatosFormulario.add(lblCrearCarro);

        Grid gridDatosRegistro = new Grid(6, 2);
        gridDatosRegistro.setStyleName("tablaFormularios");
        verticalPanelDatosFormulario.add(gridDatosRegistro);
        gridDatosRegistro.setSize("100%", "100%");

        Label lblPlaca = new Label("Placa");
        gridDatosRegistro.setWidget(0, 0, lblPlaca);

        textBoxPlaca = new TextBox();
        gridDatosRegistro.setWidget(0, 1, textBoxPlaca);

        Label lblModelo = new Label("Modelo");
        gridDatosRegistro.setWidget(1, 0, lblModelo);

        textBoxModelo = new TextBox();
        gridDatosRegistro.setWidget(1, 1, textBoxModelo);

        Label lblMarca = new Label("Marca");
        gridDatosRegistro.setWidget(2, 0, lblMarca);

        textBoxMarca = new TextBox();
        gridDatosRegistro.setWidget(2, 1, textBoxMarca);

        Label lblColor = new Label("Color");
        gridDatosRegistro.setWidget(3, 0, lblColor);

        textBoxColor = new TextBox();
        gridDatosRegistro.setWidget(3, 1, textBoxColor);

        Label lblTipoCombustible = new Label("Tipo Combustible");
        gridDatosRegistro.setWidget(4, 0, lblTipoCombustible);

        comboBoxTipoCombustible = new ListBox();
        comboBoxTipoCombustible.addItem("Gasolina");
        comboBoxTipoCombustible.addItem("Diesel");
        comboBoxTipoCombustible.addItem("Hidrogeno");
        comboBoxTipoCombustible.addItem("Gas");
        gridDatosRegistro.setWidget(4, 1, comboBoxTipoCombustible);
        comboBoxTipoCombustible.setWidth("153px");

        Label lblAireAcondicionado = new Label("Aire Acondicionado");
        gridDatosRegistro.setWidget(5, 0, lblAireAcondicionado);

        HorizontalPanel horizontalPanel = new HorizontalPanel();
        gridDatosRegistro.setWidget(5, 1, horizontalPanel);

        rdbtnAireSi = new RadioButton("new name", "Si");
        rdbtnAireSi.setChecked(true);
        horizontalPanel.add(rdbtnAireSi);

        rdbtnAireNo = new RadioButton("new name", "No");
        horizontalPanel.add(rdbtnAireNo);

                btnGuardarCarro = new Button("Registrar");
                verticalPanelDatosFormulario.add(btnGuardarCarro);
                btnGuardarCarro.setText("Guardar Cambios");
                btnGuardarCarro.addClickHandler(this);
        initWidget(formPanelRegistro);
    }
    @Override
    public void onClick(ClickEvent event) {

        char aire = 0;

        if (rdbtnAireSi.getValue() == true) {
            aire = 'S';
        } else {
            if (rdbtnAireNo.getValue() == true) {
                aire = 'N';
            }
        }
        GWTServiceAsync serviceRPC = GWTService.App.getInstance();
        serviceRPC.crearCarro(Parser.vehiculo2String(textBoxPlaca.getText(), textBoxModelo.getText(), textBoxMarca.getText(), textBoxColor.getText(), comboBoxTipoCombustible.getItemText(comboBoxTipoCombustible.getSelectedIndex()), aire + "", cedulaUser), new AsyncCallback() {

            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Pailas");
            }

            @Override
            public void onSuccess(Object result) {
                Window.alert("Registro Exitoso");
                  RootPanel.get("contenidoC").getElement().removeFromParent();
               SimplePanel nuevoSP = new SimplePanel();
                nuevoSP.getElement().setId("contenidoC");
                nuevoSP.add(new CrearCarro(cedulaUser));
                RootPanel.get("contenido").add(nuevoSP);
            }
        });
    }
}
