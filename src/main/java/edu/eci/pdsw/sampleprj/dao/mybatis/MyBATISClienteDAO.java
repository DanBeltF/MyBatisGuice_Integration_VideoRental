/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.pdsw.sampleprj.dao.ClienteDAO;
import edu.eci.pdsw.sampleprj.dao.PersistenceException;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author 2135494
 */
public class MyBATISClienteDAO implements ClienteDAO{
    @Inject
    private ClienteMapper clienteMapper;
    
    private final static long MILLISECONDS_IN_DAY = 24 * 60 * 60 * 1000;
    
    @Override
    public void save(Cliente c) throws PersistenceException{
        clienteMapper.agregarCliente(c.getDocumento(), c.getNombre(), c.getTelefono(), c.getDireccion(), c.getEmail(), c.isVetado());
    }
    
    @Override
    public Cliente load(long id) throws PersistenceException{
        try{
            return clienteMapper.consultarCliente(id);
        }catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar el cliente "+id,e);
        }catch(java.lang.IndexOutOfBoundsException ex){
            throw new PersistenceException("Un cliente con Documento "+id+" no ha sido encontrado",ex);
        }
    }

    @Override
    public void addItemACliente(long docu, Item i, Date date,int numdias) throws PersistenceException {
        try{
            clienteMapper.agregarItemRentadoACliente(docu, i.getId(), date, new Date(date.getTime()+(long)numdias*MILLISECONDS_IN_DAY));
        }catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al agregar el item con id: "+i.getId()+", al cliente con documento: "+docu,e);
        }
    }

    @Override
    public List<ItemRentado> consultarItemsRentados(long doc) throws PersistenceException {
        try{
            return clienteMapper.consultarCliente(doc).getRentados();
        }catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar los items del cliente con documento: "+doc,e);
        }
    }

    @Override
    public List<Cliente> loadClientes() throws PersistenceException {
        try{
            return clienteMapper.consultarClientes();
        }catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar el clientes.",e);
        }
    }

    @Override
    public void vetarCliente(long doc,boolean estado) throws PersistenceException {
        try{
            clienteMapper.vetarCliente(doc,estado);
        }catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar el clientes.",e);
        }
    }
    
}
