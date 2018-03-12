package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;

import edu.eci.pdsw.samples.entities.ItemRentado;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author Jonathan Prieto
 */
public interface ItemRentadoMapper {
    
    default ItemRentado consultarItemRentado(Integer id){
        return consultarItemRentadoGeneral(id).get(0);
    }
    
    default List<ItemRentado> consultarItemsRentados(){
        return consultarItemRentadoGeneral(null);
    }
    
    public List<ItemRentado> consultarItemRentadoGeneral(@Param("iid") Integer id);
    
    public void registrarDevolucion(@Param("iid") Integer id);
}
