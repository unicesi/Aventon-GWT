/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aventon.client.partesPagina;

import com.aventon.client.GWTService;
import com.aventon.client.GWTServiceAsync;
import com.aventon.client.util.Parser;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.DefaultFormat;

/**
 *
 * @author Andres
 */
public class CrearUsuario extends Composite implements ClickHandler{

   private TextBox txbCedula;
    private TextBox textBoxNombreR;
    private TextBox textBoxApellido;
    private TextBox textBoxTelefono;
    private TextBox textBoxEmail;
    private TextBox textBoxPassword;
    private DateBox dateBox;
    private RadioButton radioButtonMujer;
    private RadioButton radioButtonHombre;
    private Button btnRegistrar;
    private ListBox cbxSemestre;
    private ListBox cbxTipo;
    private ListBox cbxEstado;
    private HorizontalPanel horizontalPanel;

    public CrearUsuario() {
      
        FormPanel formPanelRegistro = new FormPanel();
        formPanelRegistro.setStyleName("formularioInicio");
        formPanelRegistro.setSize("294px", "432px");

        VerticalPanel verticalPanelDatosFormulario = new VerticalPanel();
        verticalPanelDatosFormulario.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        formPanelRegistro.setWidget(verticalPanelDatosFormulario);
        verticalPanelDatosFormulario.setSize("100%", "100%");
        verticalPanelDatosFormulario.setStyleName((String) null);

        Label lblRegistrate = new Label("Registrate");
        lblRegistrate.setStyleName("gwt-LabelTitulos");
        verticalPanelDatosFormulario.add(lblRegistrate);

        Grid gridDatosRegistro = new Grid(12, 2);
        gridDatosRegistro.setStyleName("tablaFormularios");
        verticalPanelDatosFormulario.add(gridDatosRegistro);
        gridDatosRegistro.setSize("100%", "100%");

        Label lblCedula = new Label("Cedula");
        gridDatosRegistro.setWidget(0, 0, lblCedula);
        lblCedula.setWidth("100%");

        txbCedula = new TextBox();
        gridDatosRegistro.setWidget(0, 1, txbCedula);
        txbCedula.setWidth("155");

        Label label = new Label("Nombre");
        gridDatosRegistro.setWidget(1, 0, label);

        textBoxNombreR = new TextBox();
        gridDatosRegistro.setWidget(1, 1, textBoxNombreR);
        textBoxNombreR.setWidth("155");

        Label label_1 = new Label("Apellido");
        gridDatosRegistro.setWidget(2, 0, label_1);

        textBoxApellido = new TextBox();
        gridDatosRegistro.setWidget(2, 1, textBoxApellido);
        textBoxApellido.setWidth("155");

        Label label_2 = new Label("Telefono");
        gridDatosRegistro.setWidget(3, 0, label_2);

        textBoxTelefono = new TextBox();
        gridDatosRegistro.setWidget(3, 1, textBoxTelefono);
        textBoxTelefono.setWidth("155");

        Label label_3 = new Label("Email");
        gridDatosRegistro.setWidget(4, 0, label_3);

        textBoxEmail = new TextBox();
        gridDatosRegistro.setWidget(4, 1, textBoxEmail);
        textBoxEmail.setWidth("155");

        Label label_4 = new Label("Password");
        gridDatosRegistro.setWidget(5, 0, label_4);

        textBoxPassword = new TextBox();
        gridDatosRegistro.setWidget(5, 1, textBoxPassword);
        textBoxPassword.setWidth("155");

        Label label_5 = new Label("Fecha Nacimiento");
        gridDatosRegistro.setWidget(6, 0, label_5);

        dateBox = new DateBox();
        dateBox.setFormat(new DefaultFormat(DateTimeFormat.getShortDateFormat()));
        gridDatosRegistro.setWidget(6, 1, dateBox);
        dateBox.setWidth("155");

        Label label_6 = new Label("Genero");
        gridDatosRegistro.setWidget(7, 0, label_6);
        label_6.setWidth("100%");

        horizontalPanel = new HorizontalPanel();
        gridDatosRegistro.setWidget(7, 1, horizontalPanel);

        radioButtonHombre = new RadioButton("new name", "Hombre");
        radioButtonHombre.setValue(true);
        horizontalPanel.add(radioButtonHombre);

        radioButtonMujer = new RadioButton("new name", "Mujer");
       
        horizontalPanel.add(radioButtonMujer);

        Label lblEstadoCivil = new Label("Estado Civil");
        gridDatosRegistro.setWidget(8, 0, lblEstadoCivil);

        cbxEstado = new ListBox();
        cbxEstado.addItem("Soltero");
        cbxEstado.addItem("Casado");
        cbxEstado.addItem("Viudo");
        cbxEstado.addItem("Divorciado");
        cbxEstado.addItem("Union libre");
        gridDatosRegistro.setWidget(8, 1, cbxEstado);

        Label lblTipo = new Label("Tipo");
        gridDatosRegistro.setWidget(9, 0, lblTipo);

        cbxTipo = new ListBox();
        cbxTipo.addItem("Estudiante");
        cbxTipo.addItem("Profesor");
        gridDatosRegistro.setWidget(9, 1, cbxTipo);

        btnRegistrar = new Button("Registrar");
        btnRegistrar.addClickHandler(this);

        Label lblSemestre = new Label("Semestre");
        gridDatosRegistro.setWidget(10, 0, lblSemestre);

        cbxSemestre = new ListBox();
        cbxSemestre.addItem("1");
        cbxSemestre.addItem("2");
        cbxSemestre.addItem("3");
        cbxSemestre.addItem("4");
        cbxSemestre.addItem("5");
        cbxSemestre.addItem("6");
        cbxSemestre.addItem("7");
        cbxSemestre.addItem("8");
        cbxSemestre.addItem("9");
        cbxSemestre.addItem("10");
        gridDatosRegistro.setWidget(10, 1, cbxSemestre);

        gridDatosRegistro.setWidget(11, 1, btnRegistrar);
        gridDatosRegistro.getCellFormatter().setHorizontalAlignment(11, 1, HasHorizontalAlignment.ALIGN_CENTER);
        gridDatosRegistro.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
        gridDatosRegistro.getCellFormatter().setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_LEFT);
        gridDatosRegistro.getCellFormatter().setHorizontalAlignment(2, 1, HasHorizontalAlignment.ALIGN_LEFT);
        gridDatosRegistro.getCellFormatter().setHorizontalAlignment(3, 1, HasHorizontalAlignment.ALIGN_LEFT);
        gridDatosRegistro.getCellFormatter().setHorizontalAlignment(4, 1, HasHorizontalAlignment.ALIGN_LEFT);
        gridDatosRegistro.getCellFormatter().setHorizontalAlignment(5, 1, HasHorizontalAlignment.ALIGN_LEFT);
        gridDatosRegistro.getCellFormatter().setHorizontalAlignment(6, 1, HasHorizontalAlignment.ALIGN_LEFT);
            initWidget(formPanelRegistro);
    }

    @Override
    public void onClick(ClickEvent event) {

        GWTServiceAsync servicioRPC = GWTService.App.getInstance();
        servicioRPC.crearUsuario(Parser.Usuario2String(txbCedula.getText(), textBoxNombreR.getText(), textBoxApellido.getText(), radioButtonHombre.getValue() ? 'M' : 'F', dateBox.getValue(), textBoxEmail.getText(), textBoxTelefono.getText(), cbxEstado.getItemText(cbxEstado.getSelectedIndex()), cbxTipo.getItemText(cbxTipo.getSelectedIndex()).charAt(0), cbxSemestre.getSelectedIndex() + 1, textBoxPassword.getText()), new AsyncCallback<String>() {

            @Override
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void onSuccess(String result) {
                Window.alert(result);
                RootPanel.get("contenidoC").getElement().removeFromParent();
              


                HorizontalPanel horizontalPanel = new HorizontalPanel();
                horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
                RootPanel.get("contenido").add(horizontalPanel);
                horizontalPanel.setSize("100%", "100%");

                horizontalPanel.setSize("100%", "100%");
                CrearUsuario crearUsuario = new CrearUsuario();
                horizontalPanel.add(crearUsuario);
                horizontalPanel.getElement().setId("contenidoC");
            }
        });
    }
}
