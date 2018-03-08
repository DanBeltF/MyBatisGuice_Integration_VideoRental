package edu.eci.pdsw.samples.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.pdsw.sampleprj.dao.ClienteDAO;
import edu.eci.pdsw.sampleprj.dao.ItemDAO;
import edu.eci.pdsw.sampleprj.dao.PersistenceException;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.entities.TipoItem;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hcadavid
 */
@Singleton
public class ServiciosAlquilerItemsImpl implements ServiciosAlquiler {

    @Inject
    private ItemDAO daoItem;

    @Inject
    private ClienteDAO daoCliente;

    private static final int MULTA_DIARIA = 5000;

    @Override
    public int valorMultaRetrasoxDia() {
        return MULTA_DIARIA;
    }

    @Override
    public Cliente consultarCliente(long docu) throws ExcepcionServiciosAlquiler {
        try {
            return daoCliente.load(docu);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consulta al cliente con Documento: " + docu, ex);
        }
    }

    @Override
    public List<ItemRentado> consultarItemsCliente(long idcliente) throws ExcepcionServiciosAlquiler {
        try {
            return daoCliente.consultarItemsRentados(idcliente);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consulta los items del cliente con Documento: " + idcliente, ex);
        }
    }

    @Override
    public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler {
        try {
            return daoCliente.loadClientes();
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consulta al clientes.", ex);
        }
    }

    @Override
    public Item consultarItem(int id) throws ExcepcionServiciosAlquiler {
        try {
            return daoItem.load(id);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar el item " + id, ex);
        }
    }

    @Override
    public List<Item> consultarItemsDisponibles() {
        return daoItem.itemsDisponibles();

    }

    @Override
    public long consultarMultaAlquiler(int iditem, Date fechaDevolucion) throws ExcepcionServiciosAlquiler {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TipoItem consultarTipoItem(int id) throws ExcepcionServiciosAlquiler {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TipoItem> consultarTiposItem() throws ExcepcionServiciosAlquiler {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void registrarAlquilerCliente(Date date, long docu, Item item, int numdias) throws ExcepcionServiciosAlquiler {
        try {
            daoCliente.addItemACliente(docu, item, date, numdias);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al agregar el item con id: " + item.getId() + ", al cliente con documento: " + docu, ex);
        }
    }

    @Override
    public void registrarCliente(Cliente p) throws ExcepcionServiciosAlquiler {
        try {
            daoCliente.save(p);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al registar el cliente con docuemnto: " + p.getDocumento(), ex);
        }
    }

    @Override
    public void registrarDevolucion(int iditem) throws ExcepcionServiciosAlquiler {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long consultarCostoAlquiler(int iditem, int numdias) throws ExcepcionServiciosAlquiler {
        try {
            return daoItem.consultarCostoAlquiler(iditem, numdias);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar el costo del alquiler del item: " + iditem, ex);
        }
    }

    @Override
    public void actualizarTarifaItem(int id, long tarifa) throws ExcepcionServiciosAlquiler {
        try {
            daoItem.actualizarTarifaItem(id, tarifa);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al actualizar la tarifa del item: " + id, ex);
        }
    }

    @Override
    public void registrarItem(Item i) throws ExcepcionServiciosAlquiler {
        try {
            daoItem.save(i);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al registar el item: " + i.getId(), ex);
        }
    }

    @Override
    public void vetarCliente(long docu, boolean estado) throws ExcepcionServiciosAlquiler {
        try {
            daoCliente.vetarCliente(docu, estado);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar al cliente con Documento: " + docu, ex);
        }

    }
}
