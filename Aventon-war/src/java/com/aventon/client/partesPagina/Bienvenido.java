/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aventon.client.partesPagina;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 *
 * @author No S
 */
public class Bienvenido extends Composite {

    public Bienvenido() {
           FormPanel formPanelRegistro = new FormPanel();
         formPanelRegistro.setSize("297px", "235px");
        formPanelRegistro.setStyleName("contenidoFormulario");

        VerticalPanel verticalPanelDatosFormulario = new VerticalPanel();
        verticalPanelDatosFormulario.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        formPanelRegistro.setWidget(verticalPanelDatosFormulario);
        verticalPanelDatosFormulario.setSize("100%", "100%");
        verticalPanelDatosFormulario.setStyleName((String) null);

        Label lblCrearCarro = new Label("Bienvenido");
        verticalPanelDatosFormulario.add(lblCrearCarro);

        Grid gridDatosRegistro = new Grid(7, 2);
        gridDatosRegistro.setStyleName("datosFormulario");
        verticalPanelDatosFormulario.add(gridDatosRegistro);
        gridDatosRegistro.setSize("100%", "100%");

        Label lblPlaca = new Label("AQUI VA EL TEXTO DE BIENVENIDA");
        gridDatosRegistro.setWidget(0, 0, lblPlaca);
initWidget(formPanelRegistro);
    }



    
}
