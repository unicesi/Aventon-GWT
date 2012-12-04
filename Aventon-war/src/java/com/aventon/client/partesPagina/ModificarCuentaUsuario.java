/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aventon.client.partesPagina;

/**
 *
 * @author Fabio
 */
import com.aventon.client.GWTService;
import com.aventon.client.GWTServiceAsync;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import java.util.Date;

public class ModificarCuentaUsuario extends Composite implements ClickHandler {


    private TextBox txbCedula;
    private TextBox textBoxNombreR;
    private TextBox textBoxApellido;
    private TextBox textBoxTelefono;
    private TextBox textBoxEmail;
    private DateBox dateBox;
    private RadioButton radioButtonMujer;
    private RadioButton radioButtonHombre;
    private ListBox cbxSemestre;
    private ListBox cbxTipo;
    private ListBox cbxEstado;
    private HorizontalPanel horizontalPanel;
    private PasswordTextBox passwordTextBox;
    private String ced;
    public ModificarCuentaUsuario(String cedula) {
        ced=cedula;
        FormPanel formPanelRegistro = new FormPanel();
        formPanelRegistro.setStyleName("contenidoFormulario");
        formPanelRegistro.setSize("500px", "100%");

        VerticalPanel verticalPanelDatosFormulario = new VerticalPanel();
        verticalPanelDatosFormulario.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        formPanelRegistro.setWidget(verticalPanelDatosFormulario);
        verticalPanelDatosFormulario.setSize("100%", "100%");
        verticalPanelDatosFormulario.setStyleName((String) null);

        Label lblRegistrate = new Label("Mis Datos");
        lblRegistrate.setStyleName("gwt-LabelTitulos");
        verticalPanelDatosFormulario.add(lblRegistrate);

        Grid gridDatosRegistro = new Grid(11, 2);
        gridDatosRegistro.setStyleName("tablaFormularios");
        verticalPanelDatosFormulario.add(gridDatosRegistro);
        gridDatosRegistro.setSize("100%", "100%");

        Label lblCedula = new Label("Cedula");
        gridDatosRegistro.setWidget(0, 0, lblCedula);
        lblCedula.setWidth("100%");

        txbCedula = new TextBox();
        txbCedula.setEnabled(false);
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

        passwordTextBox = new PasswordTextBox();
        gridDatosRegistro.setWidget(5, 1, passwordTextBox);
        passwordTextBox.setWidth("155");

        Label label_5 = new Label("Fecha Nacimiento");
        gridDatosRegistro.setWidget(6, 0, label_5);

        dateBox = new DateBox();
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
        cbxEstado.setWidth("155");

        Label lblTipo = new Label("Tipo");
        gridDatosRegistro.setWidget(9, 0, lblTipo);

        cbxTipo = new ListBox();
        cbxTipo.addItem("Estudiante");
        cbxTipo.addItem("Profesor");
        gridDatosRegistro.setWidget(9, 1, cbxTipo);
        cbxTipo.setWidth("155");

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
        cbxSemestre.setWidth("155");
        gridDatosRegistro.getCellFormatter().setHorizontalAlignment(7, 1,
                HasHorizontalAlignment.ALIGN_LEFT);

               
        initWidget(formPanelRegistro);
    }

    @Override
    protected void initWidget(Widget widget) {
        super.initWidget(widget);


        GWTServiceAsync servicioRPC = GWTService.App.getInstance();
        servicioRPC.consultarUsuario(ced, new AsyncCallback<String>() {

            @Override
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void onSuccess(String result) {
               
                String [] datos = result.split(";");
                txbCedula.setText(datos[0]);
                textBoxNombreR.setText(datos[1]);
                textBoxApellido.setText(datos[2]);
                textBoxTelefono.setText(datos[6]);
                textBoxEmail.setText(datos[5]);
                passwordTextBox.setText(datos[10]);
                String fechaCadena=datos[4];
                long fecha=Long.parseLong(fechaCadena);
                dateBox.setValue(new Date(fecha));

                if (datos[3].equalsIgnoreCase("F")) {
                    radioButtonMujer.setChecked(true);
                }

                if (datos[3].equalsIgnoreCase("M")) {
                    radioButtonHombre.setChecked(true);
                }



                if (datos[4].equalsIgnoreCase("Soltero")) {
                    cbxEstado.setSelectedIndex(0);
                }

                if (datos[4].equalsIgnoreCase("Casado")) {
                    cbxEstado.setSelectedIndex(1);
                }

                if (datos[4].equalsIgnoreCase("Viudo")) {
                    cbxEstado.setSelectedIndex(2);
                }

                if (datos[4].equalsIgnoreCase("Divorciado")) {
                    cbxEstado.setSelectedIndex(3);
                }

                if (datos[4].equalsIgnoreCase("Union libre")) {
                    cbxEstado.setSelectedIndex(4);
                }



                if (datos[8].equalsIgnoreCase("Estudiante")) {
                    cbxEstado.setSelectedIndex(0);
                }

                if (datos[8].equalsIgnoreCase("Profesor")) {
                    cbxEstado.setSelectedIndex(1);
                }

                if (datos[8].equalsIgnoreCase("1")) {
                    cbxSemestre.setSelectedIndex(0);
                }

                if (datos[8].equalsIgnoreCase("2")) {
                    cbxSemestre.setSelectedIndex(1);
                }

                if (datos[8].equalsIgnoreCase("3")) {
                    cbxSemestre.setSelectedIndex(2);
                }

                if (datos[8].equalsIgnoreCase("4")) {
                    cbxSemestre.setSelectedIndex(3);
                }

                if (datos[8].equalsIgnoreCase("5")) {
                    cbxSemestre.setSelectedIndex(4);
                }

                if (datos[8].equalsIgnoreCase("6")) {
                    cbxSemestre.setSelectedIndex(5);
                }

                if (datos[8].equalsIgnoreCase("7")) {
                    cbxSemestre.setSelectedIndex(6);
                }

                if (datos[8].equalsIgnoreCase("8")) {
                    cbxSemestre.setSelectedIndex(7);
                }

                if (datos[8].equalsIgnoreCase("9")) {
                    cbxSemestre.setSelectedIndex(8);
                }

                if (datos[8].equalsIgnoreCase("10")) {
                    cbxSemestre.setSelectedIndex(9);
                }
           }
        });
    }

    @Override
    public void onClick(ClickEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }



    
}
