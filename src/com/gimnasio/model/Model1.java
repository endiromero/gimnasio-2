package com.gimnasio.model;

import com.gimnasio.util.Util;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emimaster16
 */
public class Model1 {

    private List<Object> listPersist;
    private Conexion conexion;

    public Model1() {

    }   
    
    /**
     * 
     * @param idDescuentos
     * @return
     * @throws SQLException 
     */
    public List<DescuentoDto> getDatosDescuentos(String idDescuentos) throws SQLException {
        List<DescuentoDto> list = new ArrayList();
        try (Statement stat = this.conexion.getConexion().createStatement()) {
            String sql = "SELECT * FROM descuentos WHERE 1";
            if (!Util.getVacio(idDescuentos)) {
                sql += " AND id=" + idDescuentos;
                
            }
            sql += " ORDER BY id ASC ";
            ResultSet res = stat.executeQuery(sql);
            while (res.next()) {
                DescuentoDto dto = new DescuentoDto();
                dto.setId(res.getInt("id"));
                dto.setNombre(res.getString("nombre"));
                dto.setPorcentaje(res.getBigDecimal("porcentaje"));                
                list.add(dto);
            }
        }
        return list;
    }
    

    public List<Object> getListPersist() {
        return listPersist;
    }

    public void setListPersist(List<Object> listPersist) {
        this.listPersist = listPersist;
    }

    public Conexion getConexion() {
        return conexion;
    }

    public void setConexion(Conexion conexion) {
        this.conexion = conexion;
    }

}
