package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;


import edu.eci.pdsw.samples.entities.TipoItem;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author 2106913
 */
public interface TipoItemMapper {
    
    
    default List<TipoItem> getTiposItems(){
        return getTipoItemGeneral(null);
    }
    
    default TipoItem getTipoItem(int id){
        return getTipoItemGeneral(id).get(0);
    }
    
    public List<TipoItem> getTipoItemGeneral(@Param("tiid") Integer id);
    
    public void addTipoItem(String des);

}
