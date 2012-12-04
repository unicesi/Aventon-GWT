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
public class ServiceResponse implements Serializable{

    private static final long serialVersionUID = 1L;

    private String response;

    public ServiceResponse() {
    }

    /**
     * @return the response
     */
    public String getResponse() {
        return response;
    }

    /**
     * @param response the response to set
     */
    public void setResponse(String response) {
        this.response = response;
    }

}
