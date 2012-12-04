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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.ArrayList;

public class ConsultarCarro extends Composite implements ClickHandler {

    private TextBox textBoxModelo;
    private TextBox textBoxMarca;
    private TextBox textBoxColor;
    private ListBox comboBoxTipoCombustible;
    private RadioButton rdbtnAireSi;
    private RadioButton rdbtnAireNo;
    private Button btnConsultarCarro;
    private ListBox comboBoxPlaca;
    private Button btnGuardarCambios;
    private String ced;
    public ConsultarCarro(String ced) {
        this.ced=ced;
       
        FormPanel formPanelRegistro = new FormPanel();
        formPanelRegistro.setSize("500px", "100%");
        formPanelRegistro.setStyleName("formulario");

        VerticalPanel verticalPanelDatosFormulario = new VerticalPanel();
        verticalPanelDatosFormulario.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        formPanelRegistro.setWidget(verticalPanelDatosFormulario);
        verticalPanelDatosFormulario.setSize("100%", "100%");
        verticalPanelDatosFormulario.setStyleName((String) null);

        Label lblConsultarCarro = new Label("Consultar Carro");
        lblConsultarCarro.setStyleName("gwt-LabelTitulos");
        verticalPanelDatosFormulario.add(lblConsultarCarro);

        Grid gridDatosRegistro = new Grid(6, 2);
        gridDatosRegistro.setStyleName("tablaFormularios");
        verticalPanelDatosFormulario.add(gridDatosRegistro);
        gridDatosRegistro.setSize("100%", "100%");

        Label lblPlaca = new Label("Placa");
        gridDatosRegistro.setWidget(0, 0, lblPlaca);

        comboBoxPlaca = new ListBox();
        gridDatosRegistro.setWidget(0, 1, comboBoxPlaca);

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

        HorizontalPanel horizontalPanelAire = new HorizontalPanel();
        gridDatosRegistro.setWidget(5, 1, horizontalPanelAire);

        rdbtnAireSi = new RadioButton("new name", "Si");
        rdbtnAireSi.setChecked(true);
        horizontalPanelAire.add(rdbtnAireSi);

        rdbtnAireNo = new RadioButton("new name", "No");
        horizontalPanelAire.add(rdbtnAireNo);

                HorizontalPanel horizontalPanelBotones = new HorizontalPanel();
                verticalPanelDatosFormulario.add(horizontalPanelBotones);

                        btnConsultarCarro = new Button("Registrar");
                        horizontalPanelBotones.add(btnConsultarCarro);
                        btnConsultarCarro.setText("Consultar");
                        btnConsultarCarro.addClickHandler(this);

                                btnGuardarCambios = new Button("Guardar Cambios");
                                btnGuardarCambios.setVisible(false);
                                horizontalPanelBotones.add(btnGuardarCambios);
                                btnGuardarCambios.addClickHandler(this);

        initWidget(formPanelRegistro);
    }

    @Override
    protected void initWidget(Widget widget) {
        super.initWidget(widget);
            GWTServiceAsync serviceRPC = GWTService.App.getInstance();
        serviceRPC.listarCarro(ced, new AsyncCallback<ArrayList<String>>() {

            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }

            @Override
            public void onSuccess(ArrayList<String> result) {
              
               
                for (String string : result) {
                    comboBoxPlaca.addItem(string);
                }
            }
        });
    }



    @Override
    public void onClick(ClickEvent event) {

        GWTServiceAsync serviceRPC = GWTService.App.getInstance();

        serviceRPC.consultarCarro(comboBoxPlaca.getItemText(comboBoxPlaca.getSelectedIndex()), new AsyncCallback<String>() {

            @Override
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void onSuccess(String result) {

                String[] datos = result.split(";");
                textBoxModelo.setText(datos[1]);
                textBoxMarca.setText(datos[2]);
                textBoxColor.setText(datos[3]);
                if (datos[4].equalsIgnoreCase("Gasolina")) {
                    comboBoxTipoCombustible.setSelectedIndex(0);
                }

                if (datos[4].equalsIgnoreCase("Diesel")) {
                    comboBoxTipoCombustible.setSelectedIndex(1);
                }

                if (datos[4].equalsIgnoreCase("Hidrogeno")) {
                    comboBoxTipoCombustible.setSelectedIndex(2);
                }

                if (datos[4].equalsIgnoreCase("Gas")) {
                    comboBoxTipoCombustible.setSelectedIndex(3);
                }

                if (datos[5].equalsIgnoreCase("S")) {
                    rdbtnAireSi.setChecked(true);
                }

                if (datos[5].equalsIgnoreCase("N")) {
                    rdbtnAireNo.setChecked(true);
                }
            }
        });
    }

  
}
