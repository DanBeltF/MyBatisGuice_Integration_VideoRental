/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.services;

import com.google.inject.Injector;
import edu.eci.pdsw.samples.services.impl.ServiciosAlquilerItemsImpl;
import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;

import static com.google.inject.Guice.createInjector;
import edu.eci.pdsw.sampleprj.dao.ClienteDAO;
import edu.eci.pdsw.sampleprj.dao.ItemDAO;
import edu.eci.pdsw.sampleprj.dao.ItemRentadoDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.MyBATISItemRentadoDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.MyBATISClienteDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.MyBATISItemDAO;


/**
 *
 * @author hcadavid
 */
public class ServiciosAlquilerFactory {

    private static ServiciosAlquilerFactory instance = new ServiciosAlquilerFactory();

    private static Injector injector;

    private static Injector testInjector;

    private Injector myBatisInjector(String pathResource) {
        return createInjector(new XMLMyBatisModule() {
            @Override
            protected void initialize() {
                setClassPathResource(pathResource);
                bind(ServiciosAlquiler.class).to(ServiciosAlquilerItemsImpl.class);
                bind(ClienteDAO.class).to(MyBATISClienteDAO.class);
                bind(ItemDAO.class).to(MyBATISItemDAO.class);
                bind(ItemRentadoDAO.class).to(MyBATISItemRentadoDAO.class);
            }
        });
    }

    private ServiciosAlquilerFactory(){

        injector = myBatisInjector("mybatis-config.xml");
        testInjector = myBatisInjector("mybatis-config-h2.xml");
    }

    public ServiciosAlquiler getServiciosAlquiler(){
        return injector.getInstance(ServiciosAlquiler.class);
    }


    public ServiciosAlquiler getServiciosAlquilerTesting(){
        return testInjector.getInstance(ServiciosAlquiler.class);
    }



    public static ServiciosAlquilerFactory getInstance(){
        return instance;
    }

}
