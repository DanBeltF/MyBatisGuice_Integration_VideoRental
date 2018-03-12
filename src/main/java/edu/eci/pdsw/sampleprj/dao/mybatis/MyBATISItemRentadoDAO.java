package edu.eci.pdsw.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.pdsw.sampleprj.dao.ItemRentadoDAO;
import edu.eci.pdsw.sampleprj.dao.PersistenceException;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ItemRentadoMapper;
import edu.eci.pdsw.samples.entities.ItemRentado;
import java.sql.Date;

/**
 *
 * @author Jonathan Prieto
 */
public class MyBATISItemRentadoDAO implements ItemRentadoDAO{
    
    @Inject
    private ItemRentadoMapper itemRentadoMapper;
    
    private static final int MULTA_DIARIA = 5000;

    @Override
    public long consultarMultaAlquiler(int idItem, Date fechaDevolucion) throws PersistenceException{
        try {
            Long multa = 0L;
            ItemRentado ir = itemRentadoMapper.consultarItemRentado(idItem);
            if(ir.getFechafinrenta().getTime()-fechaDevolucion.getTime() < 0){
                multa = (long) ((int) ((ir.getFechafinrenta().getTime()-fechaDevolucion.getTime())/86400000))*MULTA_DIARIA;
            }
            return multa;
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar el item "+idItem,e);
        } catch (java.lang.IndexOutOfBoundsException ex){
            throw new PersistenceException("No se encontro un item con un id: "+idItem,ex);
        }
    }
    
}
