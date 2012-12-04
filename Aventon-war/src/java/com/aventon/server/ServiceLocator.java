/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aventon.server;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Administrador
 */
public class ServiceLocator {

    /**
    * Initial Context
    */
    private InitialContext context;

    //----------------------------------------
    //Singleton
    //----------------------------------------

    private static ServiceLocator serviceLocator;

    /**
     * Constructor
     * @throws NamingException
     * @throws IOException
     * @throws FileNotFoundException
     */
    private ServiceLocator() throws Exception{
        this.context = new InitialContext();
    }

    /**
     * Singleton Instance
     * @return
     * @throws NamingException
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static ServiceLocator getInstance() throws Exception{

        if(serviceLocator == null){
            serviceLocator = new ServiceLocator();
        }
        return serviceLocator;

    }

    /**
     * This method returns an instance of the Group EJB.
     * @return GroupRemoteSession
     * @throws NamingException: when the context can't find the EJB
     */
    public Object getRemoteSession(String canonicalClassName) throws NamingException{
    	return context.lookup(canonicalClassName);
    }
}
