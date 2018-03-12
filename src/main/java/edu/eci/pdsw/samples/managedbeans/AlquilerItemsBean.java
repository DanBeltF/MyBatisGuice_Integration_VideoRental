/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.managedbeans;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquilerFactory;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author 2106913
 */
@ManagedBean(name = "AlquilerItems")
@SessionScoped
public class AlquilerItemsBean implements Serializable {

    ServiciosAlquiler sp = ServiciosAlquilerFactory.getInstance().getServiciosAlquiler();
    private Cliente selected;
    private Cliente nuevoCliente;
    private Date fechaEntrega;
    private Item nuevoItemRentar;
    private int diasAlquiler;
    private long costoAlquiler;
    

    public AlquilerItemsBean() {
        nuevoCliente = new Cliente();
        nuevoCliente.setRentados(new ArrayList<ItemRentado>());
    }
    
    public List<Cliente> getClientes() throws ExcepcionServiciosAlquiler {
        return sp.consultarClientes();
    }
    
    public void registrarCliente() throws ExcepcionServiciosAlquiler{
        sp.registrarCliente(nuevoCliente);
        nuevoCliente = new Cliente();
        nuevoCliente.setRentados(new ArrayList<ItemRentado>());
    }

    public List<Item> getItemsDisponibles() {
        return sp.consultarItemsDisponibles();
    }
    
    public long multasItems(ItemRentado i) throws ExcepcionServiciosAlquiler{
        
        try {
            return sp.consultarMultaAlquiler(i.getItem().getId(), Date.valueOf(LocalDate.now()));
        } catch (java.lang.NullPointerException ex) {
            return 0;
        }
    }
    
    public void consultarCostoAlquiler() throws ExcepcionServiciosAlquiler{
        setCostoAlquiler(sp.consultarCostoAlquiler(nuevoItemRentar.getId(), diasAlquiler));
    }
    
    public void alquilar() throws ExcepcionServiciosAlquiler{
        if(nuevoItemRentar==null)throw new ExcepcionServiciosAlquiler("Debe seleccionar un Item!");
        sp.registrarAlquilerCliente(Date.valueOf(LocalDate.now()), selected.getDocumento(), nuevoItemRentar, diasAlquiler);
    }

    public Cliente getSelected() {
        return selected;
    }

    public void setSelected(Cliente selected) {
        this.selected = selected;
    }

    public Cliente getNuevoCliente() {
        return nuevoCliente;
    }

    public void setNuevoCliente(Cliente nuevoCliente) {
        this.nuevoCliente = nuevoCliente;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Item getNuevoItemRentar() {
        return nuevoItemRentar;
    }

    public void setNuevoItemRentar(Item nuevoItemRentar) {
        this.nuevoItemRentar = nuevoItemRentar;
    }

    public int getDiasAlquiler() {
        return diasAlquiler;
    }

    public void setDiasAlquiler(int diasAlquiler) {
        this.diasAlquiler = diasAlquiler;
    }

    public long getCostoAlquiler() {
        return costoAlquiler;
    }

    public void setCostoAlquiler(long costoAlquiler) {
        this.costoAlquiler = costoAlquiler;
    }
    
}
