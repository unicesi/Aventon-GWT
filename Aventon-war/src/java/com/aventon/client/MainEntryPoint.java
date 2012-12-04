/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aventon.client;

import com.aventon.client.template.Plantilla;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Main entry point.
 *
 * @author Andres
 */
public class MainEntryPoint implements EntryPoint {
    /** 
     * Creates a new instance of MainEntryPoint
     */
    public MainEntryPoint() {
    }

    /** 
     * The entry point method, called automatically by loading a module
     * that declares an implementing class as an entry-point
     */
    @Override
    public void onModuleLoad() {

        RootPanel rootPanel=RootPanel.get();
           rootPanel.setStyleName("body");
        rootPanel.add(Plantilla.getInstancePlantilla());
        
    }
}
