/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.pdsw.sampleprj.dao.PersistenceException;
import edu.eci.pdsw.sampleprj.dao.TipoItemDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.TipoItemMapper;
import edu.eci.pdsw.samples.entities.TipoItem;
import java.util.List;

/**
 *
 * @author sergiort
 */
public class MyBATISTipoItemDAO implements TipoItemDAO{
    
    @Inject
    private TipoItemMapper tipoItemMapper;

    @Override
    public TipoItem load(int id) throws PersistenceException {
        try{
            return tipoItemMapper.getTipoItem(id);
        }catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar el tipo de item con id: "+id,e);
        }catch(java.lang.IndexOutOfBoundsException ex){
            throw new PersistenceException("Un tipo de item con id "+id+" no ha sido encontrado.",ex);
        }
    }

    @Override
    public List<TipoItem> loadTipoItems() throws PersistenceException {
        try{
            return tipoItemMapper.getTiposItems();
        }catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar los tipos de item.",e);
        }
    }
    
 
    
}
