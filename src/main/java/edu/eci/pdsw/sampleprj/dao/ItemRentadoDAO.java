package edu.eci.pdsw.sampleprj.dao;

import java.sql.Date;

/**
 *
 * @author Jonathan Prieto
 */
public interface ItemRentadoDAO {
    
    public long consultarMultaAlquiler(int idItem, Date fechaDevolucion) throws PersistenceException;
    
    public void registrarDevolucion(int idItem) throws PersistenceException;
}
