/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aventon.client.partesPagina;

/**
 *
 * @author Diego
 */
import com.aventon.client.GWTService;
import com.aventon.client.GWTServiceAsync;
import com.aventon.client.util.Parser;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;
import java.util.ArrayList;
import java.util.Date;
public class CalificarAventon extends Composite implements ClickHandler{
		private DateBox dateBoxFecha;
	private ListBox listBoxSalida;
	private RadioButton radioButtonSe1;
	private RadioButton radioButtonSe2;
	private RadioButton radioButtonSe3;
	private RadioButton radioButtonSe4;
	private RadioButton radioButtonSe5;
	private RadioButton radioButtonComo1;
	private RadioButton radioButtonComo2;
	private RadioButton radioButtonComo3;
	private RadioButton radioButtonComo4;
	private RadioButton radioButtonComo5;
	private RadioButton radioButtonPun1;
	private RadioButton radioButtonPun2;
	private RadioButton radioButtonPun3;
	private RadioButton radioButtonPun4;
	private RadioButton radioButtonPun5;
	private RadioButton radioButtonAma1;
	private RadioButton radioButtonAma2;
	private RadioButton radioButtonAma3;
	private RadioButton radioButtonAma4;
	private RadioButton radioButtonAma5;
	private Button button;
        private String ced;
	public CalificarAventon(String cedula)
	{
            ced=cedula;
		FormPanel formPanel = new FormPanel();
		formPanel.setStyleName("formulario");
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		formPanel.setWidget(verticalPanel);
		verticalPanel.setSize("100%", "100%");

		Label label = new Label("Califica el servicio");
		label.setStyleName("gwt-LabelTitulos");
		verticalPanel.add(label);

		Grid grid = new Grid(7, 2);
		grid.setStyleName("tablaFormularios");
		verticalPanel.add(grid);
		grid.setSize("100%", "100%");

		Label lblSalida = new Label("Salida");
		grid.setWidget(0, 0, lblSalida);

		listBoxSalida = new ListBox();
		grid.setWidget(0, 1, listBoxSalida);
		listBoxSalida.setWidth("148px");

		Label lblFecha = new Label("Fecha ");
		grid.setWidget(1, 0, lblFecha);

		dateBoxFecha = new DateBox();
		grid.setWidget(1, 1, dateBoxFecha);
		dateBoxFecha.setWidth("155");

		Label lblSeguridad = new Label("Seguridad");
		grid.setWidget(2, 0, lblSeguridad);

		HorizontalPanel horizontalPanelSeguridad = new HorizontalPanel();
		grid.setWidget(2, 1, horizontalPanelSeguridad);

		radioButtonSe1 = new RadioButton("seg", "1");
		horizontalPanelSeguridad.add(radioButtonSe1);


radioButtonSe2 = new RadioButton("seg", "2");
		horizontalPanelSeguridad.add(radioButtonSe2);

		radioButtonSe3 = new RadioButton("seg", "3");
		horizontalPanelSeguridad.add(radioButtonSe3);

		radioButtonSe4 = new RadioButton("seg", "4");
		horizontalPanelSeguridad.add(radioButtonSe4);

		radioButtonSe5 = new RadioButton("seg", "5");
		horizontalPanelSeguridad.add(radioButtonSe5);

		Label lblComodidad = new Label("Comodidad");
		grid.setWidget(3, 0, lblComodidad);

		HorizontalPanel horizontalPanelComodidad = new HorizontalPanel();
		grid.setWidget(3, 1, horizontalPanelComodidad);

		radioButtonComo1 = new RadioButton("com", "1");
		horizontalPanelComodidad.add(radioButtonComo1);

		radioButtonComo2 = new RadioButton("com", "2");
		horizontalPanelComodidad.add(radioButtonComo2);

		radioButtonComo3 = new RadioButton("com", "3");
		horizontalPanelComodidad.add(radioButtonComo3);

		radioButtonComo4 = new RadioButton("com", "4");
		horizontalPanelComodidad.add(radioButtonComo4);

		radioButtonComo5 = new RadioButton("com", "5");
		horizontalPanelComodidad.add(radioButtonComo5);

		Label lblPuntualidad = new Label("Puntualidad");
		grid.setWidget(4, 0, lblPuntualidad);

		HorizontalPanel horizontalPanelPuntualidad = new HorizontalPanel();
		grid.setWidget(4, 1, horizontalPanelPuntualidad);

		radioButtonPun1 = new RadioButton("pun", "1");
		horizontalPanelPuntualidad.add(radioButtonPun1);

		radioButtonPun2 = new RadioButton("pun", "2");
		horizontalPanelPuntualidad.add(radioButtonPun2);

		radioButtonPun3 = new RadioButton("pun", "3");
		horizontalPanelPuntualidad.add(radioButtonPun3);

		radioButtonPun4 = new RadioButton("pun", "4");
		horizontalPanelPuntualidad.add(radioButtonPun4);

		radioButtonPun5 = new RadioButton("pun", "5");
		horizontalPanelPuntualidad.add(radioButtonPun5);

		Label lblAmabilidad = new Label("Amabilidad");
		grid.setWidget(5, 0, lblAmabilidad);

		HorizontalPanel horizontalPanelAmabilidad = new HorizontalPanel();
		grid.setWidget(5, 1, horizontalPanelAmabilidad);

		radioButtonAma1 = new RadioButton("ama", "1");
		horizontalPanelAmabilidad.add(radioButtonAma1);

		radioButtonAma2 = new RadioButton("ama", "2");
		horizontalPanelAmabilidad.add(radioButtonAma2);

		radioButtonAma3 = new RadioButton("ama", "3");
		horizontalPanelAmabilidad.add(radioButtonAma3);

		radioButtonAma4 = new RadioButton("ama", "4");
		horizontalPanelAmabilidad.add(radioButtonAma4);

		radioButtonAma5 = new RadioButton("ama", "5");
		horizontalPanelAmabilidad.add(radioButtonAma5);

		Label lblComentarios = new Label("Comentarios");
		grid.setWidget(6, 0, lblComentarios);

		HorizontalPanel horizontalPanel = new HorizontalPanel();
		grid.setWidget(6, 1, horizontalPanel);

		
		horizontalPanel.add(txtrComent);

		grid.getCellFormatter().setHorizontalAlignment(6, 1, HasHorizontalAlignment.ALIGN_CENTER);

				button = new Button("Registrar");
				verticalPanel.add(button);
				button.addClickHandler(this);
				button.setText("Calificar Servicio");
	            initWidget(formPanel);
	            formPanel.setSize("500px", "100%");
	        
	}

    @Override
    protected void initWidget(Widget widget) {
        super.initWidget(widget);

           GWTServiceAsync serviceRPC = GWTService.App.getInstance();
        serviceRPC.cuposU(ced, new AsyncCallback<ArrayList<String>>() {

            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }

            @Override
            public void onSuccess(ArrayList<String> result) {


                for (final String string : result) {

                     GWTServiceAsync serviceRPC = GWTService.App.getInstance();
                     serviceRPC.nombreRuta(Integer.parseInt(string.split(";")[1]), new AsyncCallback<String>() {

                        @Override
                        public void onFailure(Throwable caught) {
                            throw new UnsupportedOperationException("Not supported yet.");
                        }

                        @Override
                        public void onSuccess(String result) {
                         listBoxSalida.addItem(result, string.split(";")[0] + ";" + string.split(";")[1] + ";"+ string.split(";")[2]);
                        }
                    });
                }
            }
        });
    }

        
        private TextArea txtrComent = new TextArea();
	@Override
	public void onClick(ClickEvent event) {
               GWTServiceAsync serviceRPC = GWTService.App.getInstance();

               int seguridad=0;

               if (radioButtonSe1.getValue() == true) {
                   seguridad=1;
                }
               if (radioButtonSe2.getValue() == true) {
                   seguridad=2;
                }
               if (radioButtonSe3.getValue() == true) {
                   seguridad=3;
                }
               if (radioButtonSe4.getValue() == true) {
                   seguridad=4;
                }
               if (radioButtonSe5.getValue() == true) {
                   seguridad=5;
                }


               int comodidad=0;

               if (radioButtonComo1.getValue() == true) {
                   comodidad=1;
                }
               if (radioButtonComo2.getValue() == true) {
                   comodidad=2;
                }
               if (radioButtonComo3.getValue() == true) {
                   comodidad=3;
                }
               if (radioButtonComo4.getValue() == true) {
                   comodidad=4;
                }
               if (radioButtonComo5.getValue() == true) {
                   comodidad=5;
                }


               int puntualidad=0;

               if (radioButtonPun1.getValue() == true) {
                   puntualidad=1;
                }
               if (radioButtonPun2.getValue() == true) {
                   puntualidad=2;
                }
               if (radioButtonPun3.getValue() == true) {
                   puntualidad=3;
                }
               if (radioButtonPun4.getValue() == true) {
                   puntualidad=4;
                }
               if (radioButtonPun5.getValue() == true) {
                   puntualidad=5;
                }

               int amabilidad=0;

               if (radioButtonAma1.getValue() == true) {
                   amabilidad=1;
                }
               if (radioButtonAma2.getValue() == true) {
                   amabilidad=2;
                }
               if (radioButtonAma3.getValue() == true) {
                   amabilidad=3;
                }
               if (radioButtonAma4.getValue() == true) {
                   amabilidad=4;
                }
               if (radioButtonAma5.getValue() == true) {
                   amabilidad=5;
                }
            serviceRPC.calificarCupo(Parser.cupo2String(new Date(Long.parseLong(listBoxSalida.getValue(listBoxSalida.getSelectedIndex()).split(";")[0])), Integer.parseInt(listBoxSalida.getValue(listBoxSalida.getSelectedIndex()).split(";")[1]), listBoxSalida.getValue(listBoxSalida.getSelectedIndex()).split(";")[2], amabilidad, comodidad, seguridad, puntualidad, txtrComent.getText()), new AsyncCallback<String>() {

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
                nuevoSP.add(new CalificarAventon(ced));
                RootPanel.get("contenido").add(nuevoSP);
            }
        });

	}
}