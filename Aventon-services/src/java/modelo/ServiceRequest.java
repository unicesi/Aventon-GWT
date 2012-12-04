/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.Serializable;

/**
 *
 * @author Administrador
 */
public class ServiceRequest implements Serializable{

    private static final long serialVersionUID = 1L;

    private String request;

    public ServiceRequest() {
    }

    /**
     * @return the greeting
     */
    public String getRequest() {
        return request;
    }

    /**
     * @param greeting the greeting to set
     */
    public void setRequest(String request) {
        this.request = request;
    }

}
