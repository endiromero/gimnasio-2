package com.gimnasio.model;

import com.gimnasio.util.Util;
import java.security.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
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
    
    /**
     * 
     * @param idProducto
     * @return
     * @throws SQLException 
     */
    public List<ProductoDto> getDatosProductos(String idProducto) throws SQLException {
        List<ProductoDto> list = new ArrayList();
        try (Statement stat = this.conexion.getConexion().createStatement()) {
            String sql = "SELECT * FROM productos WHERE 1";
            if (!Util.getVacio(idProducto)) {
                sql += " AND id=" + idProducto;                
            }
            sql += " ORDER BY id ASC ";
            ResultSet res = stat.executeQuery(sql);
            while (res.next()) {
                ProductoDto dto = new ProductoDto();
                dto.setId(res.getInt("id"));
                dto.setNombre(res.getString("nombre"));
                dto.setPrecio(res.getDouble("precio"));                
                list.add(dto);
            }
        }
        return list;
    }
   
    /**
     * 
     * @param descuento
     * @return
     * @throws SQLException 
     */
    public boolean setGuardarDescuento(DescuentoDto descuento) throws SQLException {
        PreparedStatement stat = null;
        if (descuento.getId() != null && descuento.getId() > 0) {
            stat = this.conexion.getConexion().prepareStatement("UPDATE descuentos SET nombre = ?, porcentaje = ? WHERE id=?");
            stat.setInt(3, descuento.getId());
        } else {
            stat = this.conexion.getConexion().prepareStatement("INSERT INTO descuentos (nombre, porcentaje) VALUES (?,?)");
        }
        stat.setString(1, descuento.getNombre());
        stat.setDouble(2, descuento.getPorcentaje().doubleValue());                
        stat.execute();
        stat.close();
        return true;
    }
    
    /**
     * 
     * @param producto
     * @return
     * @throws SQLException 
     */
    public boolean setGuardarProducto(ProductoDto producto) throws SQLException {
        PreparedStatement stat = null;
        if (producto.getId() != null && producto.getId() > 0) {
            stat = this.conexion.getConexion().prepareStatement("UPDATE productos SET nombre = ?, precio = ? WHERE id=?");
            stat.setInt(3, producto.getId());
        } else {
            stat = this.conexion.getConexion().prepareStatement("INSERT INTO productos (nombre, precio) VALUES (?,?)");
        }
        stat.setString(1, producto.getNombre());
        stat.setDouble(2, producto.getPrecio());                
        stat.execute();
        stat.close();
        return true;
    }
    
    /**
     * 
     * @param idProducto
     * @param idUsuario
     * @param cantidad
     * @param valor_total
     * @param fecha
     * @return
     * @throws SQLException 
     */
    public boolean setGuardarCafeteria(int idProducto, long idUsuario, String cantidad, String valor_total, Date fecha) throws SQLException {
        PreparedStatement stat = null;
        
        stat = this.conexion.getConexion().prepareStatement("INSERT INTO producto_ventas (productos_id, cantidad, valor_total, fecha_registro, usuario_id) VALUES (?,?,?,?,?)");
        
        stat.setInt(1, idProducto);
        stat.setDouble(2, Double.valueOf(cantidad));
        stat.setDouble(3, Double.valueOf(valor_total));
        stat.setTimestamp(4, new java.sql.Timestamp(fecha.getTime()));
        stat.setInt(5, (int)idUsuario);                
        stat.execute();
        stat.close();
        return true;
    }
    
    /**
     * 
     * @param idDescuentos
     * @return
     * @throws SQLException 
     */
    public List<ClienteDto> getDatosClientes(String idDescuentos) throws SQLException {
        List<ClienteDto> list = new ArrayList();
        try (Statement stat = this.conexion.getConexion().createStatement()) {
            String sql = "SELECT "
                           + ""
                           + " FROM clientes "
                           + " INNER JOIN ";
            if (!Util.getVacio(idDescuentos)) {
                sql += " AND id=" + idDescuentos;
                
            }
            sql += " ORDER BY id ASC ";
            ResultSet res = stat.executeQuery(sql);
            while (res.next()) {
                ClienteDto dto = new ClienteDto();
                               
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
