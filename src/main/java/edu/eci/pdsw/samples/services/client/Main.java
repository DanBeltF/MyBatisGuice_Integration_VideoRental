/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.services.client;

import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.TipoItem;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquilerFactory;
import java.time.LocalDate;

/**
 *
 * @author hcadavid
 */
public class Main {

    public static void main(String a[]) throws ExcepcionServiciosAlquiler{
        System.out.println("---Costo de una multa---" + ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().consultarMultaAlquiler(8, java.sql.Date.valueOf(LocalDate.now())));
        System.out.println(ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().consultarItem(2));
        System.out.println(ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().consultarCliente(1684264984));
//        ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().registrarCliente(new Cliente("Sergio", 317658, "3175598", "AK 205", "sergio@mail.com"));
//        ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().registrarItem(new Item(new TipoItem(2, "Juego"), 8, "Need for Speed: Payback", "NFS es un videojuego de carreras de acción creada por Electronic Arts.", java.sql.Date.valueOf("2017-11-10"), 3000, "DVD", "Carreras"));
        System.out.println("--------------\nDespues de registrar\n--------------\n"+ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().consultarItem(8));
        System.out.println(ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().consultarCliente(317658));
        System.out.println(ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().consultarItemsDisponibles());
        System.out.println(ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().consultarItem(7));
        ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().actualizarTarifaItem(7, 4500);
        System.out.println(ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().consultarItem(7));
        ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().vetarCliente(317658, true);
        System.out.println(ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().consultarCliente(317658));
        //System.out.println("---Costo de una multa--- " + ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().consultarMultaAlquiler(8, java.sql.Date.valueOf(LocalDate.now())));
        //ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().registrarDevolucion(8);
        //ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().registrarAlquilerCliente(java.sql.Date.valueOf("2018-03-9"), 317658, ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().consultarItem(8), 1);
        System.out.println("---Costo del alquiler--- :" + ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().consultarCostoAlquiler(8, 4));
        System.out.println(ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().consultarTipoItem(1));
        System.out.println("-------Consulta items-----");
        System.out.println(ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().consultarTiposItem());
        System.out.println("-------fin consulta items-----");
        Item it = new Item(new TipoItem(2, "Juego"), 8, "Need for Speed: Payback", "NFS es un videojuego de carreras de acción creada por Electronic Arts.", java.sql.Date.valueOf("2017-11-10"), 3000, "DVD", "Carreras");
        long ll = 9999111122L;
        System.out.println("----Consulta cliente ---\n\n"+ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().consultarCliente(ll) +"\nItems: "+ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().consultarItemsCliente(ll));
//        ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().registrarAlquilerCliente(Date.valueOf(LocalDate.now()), ll, it, 2);
        System.out.println(ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().consultarItemsCliente(ll));
        System.exit(0);
    }
    
}
//<packaging>war</packaging>