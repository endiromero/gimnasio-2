package com.gimnasio.model;

import com.gimnasio.util.Util;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emimaster16
 */
public class Model {

    private List<Object> listPersist;
    private Conexion conexion;

    public Model() {

    }

    /**
     *
     * @param idCliente
     * @param numeroDocuemnto
     * @return
     * @throws SQLException
     */
    public List<ClienteDto> getClienteDatos(String idCliente, String numeroDocuemnto) throws SQLException {
        List<ClienteDto> list = new ArrayList();
        Statement stat = this.conexion.getConexion().createStatement();
        String sql = "SELECT cli.*, per.* FROM clientes cli INNER JOIN personas per ON cli.persona_id=per.id WHERE 1 ";
        if (!Util.getVacio(idCliente)) {
            sql += " AND cli.id=" + idCliente;
        }
        if (!Util.getVacio(numeroDocuemnto)) {
            sql += " AND per.numero_identificacion='" + numeroDocuemnto + "'";
        }
        sql += " ORDER BY cli.id ASC ";
        ResultSet res = stat.executeQuery(sql);
        while (res.next()) {
            ClienteDto dto = new ClienteDto();
            dto.setId(res.getLong("id"));
            dto.getPersonaDto().setId(res.getLong("id_persona"));
            dto.getPersonaDto().setPrimerNombre(res.getString("primer_nombre"));
            dto.getPersonaDto().setSegundoNombre(res.getString("segundo_nombre"));
            dto.getPersonaDto().setPrimerApellido(res.getString("primer_apellido"));
            dto.getPersonaDto().setSegundoApellido(res.getString("segundo_apellido"));
            dto.getPersonaDto().setTipoIdentificacion(res.getShort("tipo_identificacion"));
            dto.getPersonaDto().setNumeroIdentificacion(res.getString("numero_identificacion"));
            dto.getPersonaDto().setLugarExpedicion(res.getString("lugar_expedicion"));
            dto.getPersonaDto().setGenero(res.getShort("genero"));
            dto.getPersonaDto().setEstadoCivil(res.getShort("estado_civil"));
            dto.getPersonaDto().setFechaNacimiento(res.getString("fecha_nacimiento"));
            dto.getPersonaDto().setDireccion(res.getString("direccion"));
            dto.getPersonaDto().setBarrio(res.getString("barrio"));
            dto.getPersonaDto().setTelefono(res.getString("telefono"));
            dto.getPersonaDto().setMovil(res.getString("movil"));
            dto.getPersonaDto().setHuellaDactilar(res.getBytes("huella_dactilar"));
            dto.getPersonaDto().setFechaRegistro(res.getString("fecha_registro"));
            dto.getPersonaDto().setFechaModificacion(res.getString("fecha_modificacion"));
            list.add(dto);
        }
        stat.close();
        return list;
    }

    /**
     *
     * @tutorial Method Description: valida que la informacion este correcta
     * @author Eminson Mendoza ~~ emimaster16@gmail.com
     * @date 08/07/2016
     * @param clienteDto
     * @throws java.sql.SQLException
     */
    public void setGuardarCliente(ClienteDto clienteDto) throws SQLException {
        PreparedStatement stat;
        ResultSet res;
        boolean correcto = false;
        try {
            if (clienteDto.getId() != null && !Util.getVacio(String.valueOf(clienteDto.getId()))) {
                stat = this.conexion.getConexion().prepareStatement("UPDATE personas SET primer_nombre = ?, segundo_nombre = ?, "
                        + "primer_apellido = ?, segundo_apellido = ?, "
                        + "tipo_identificacion = ?, numero_identificacion = ?, "
                        + "lugar_expedicion = ?, genero = ?, "
                        + "estado_civil = ?, fecha_nacimiento = ?, "
                        + "direccion = ?, barrio = ?, "
                        + "telefono = ?, movil = ?, "
                        + "email = ?, huella_dactilar = ?, "
                        + "foto_perfil = ? "
                        + "WHERE id=? ");
                stat.setString(1, clienteDto.getPersonaDto().getPrimerNombre());
                stat.setString(2, clienteDto.getPersonaDto().getSegundoNombre());
                stat.setString(3, clienteDto.getPersonaDto().getPrimerApellido());
                stat.setString(4, clienteDto.getPersonaDto().getSegundoApellido());
                stat.setShort(5, clienteDto.getPersonaDto().getTipoIdentificacion());
                stat.setString(6, clienteDto.getPersonaDto().getNumeroIdentificacion());
                stat.setString(7, clienteDto.getPersonaDto().getLugarExpedicion());
                stat.setShort(8, clienteDto.getPersonaDto().getGenero());
                stat.setShort(9, clienteDto.getPersonaDto().getEstadoCivil());
                stat.setString(10, clienteDto.getPersonaDto().getFechaNacimiento());
                stat.setString(11, clienteDto.getPersonaDto().getDireccion());
                stat.setString(12, clienteDto.getPersonaDto().getBarrio());
                stat.setString(13, clienteDto.getPersonaDto().getTelefono());
                stat.setString(14, clienteDto.getPersonaDto().getMovil());
                stat.setString(15, clienteDto.getPersonaDto().getEmail());
                stat.setBytes(16, clienteDto.getPersonaDto().getHuellaDactilar());
                stat.setString(17, clienteDto.getPersonaDto().getNumeroIdentificacion() + ".jpg");
                stat.setLong(18, clienteDto.getId());
                correcto = stat.executeUpdate() > 0;
            } else {
                stat = this.conexion.getConexion().prepareStatement("INSERT INTO personas (primer_nombre, segundo_nombre, "
                        + "primer_apellido, segundo_apellido, "
                        + "tipo_identificacion, numero_identificacion, "
                        + "lugar_expedicion, genero, "
                        + "estado_civil, fecha_nacimiento, "
                        + "direccion, barrio, "
                        + "telefono, movil, "
                        + "email, huella_dactilar, "
                        + "foto_perfil)  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                stat.setString(1, clienteDto.getPersonaDto().getPrimerNombre());
                stat.setString(2, clienteDto.getPersonaDto().getSegundoNombre());
                stat.setString(3, clienteDto.getPersonaDto().getPrimerApellido());
                stat.setString(4, clienteDto.getPersonaDto().getSegundoApellido());
                stat.setShort(5, clienteDto.getPersonaDto().getTipoIdentificacion());
                stat.setString(6, clienteDto.getPersonaDto().getNumeroIdentificacion());
                stat.setString(7, clienteDto.getPersonaDto().getLugarExpedicion());
                stat.setShort(8, clienteDto.getPersonaDto().getGenero());
                stat.setShort(9, clienteDto.getPersonaDto().getEstadoCivil());
                stat.setString(10, clienteDto.getPersonaDto().getFechaNacimiento());
                stat.setString(11, clienteDto.getPersonaDto().getDireccion());
                stat.setString(12, clienteDto.getPersonaDto().getBarrio());
                stat.setString(13, clienteDto.getPersonaDto().getTelefono());
                stat.setString(14, clienteDto.getPersonaDto().getMovil());
                stat.setString(15, clienteDto.getPersonaDto().getEmail());
                stat.setBytes(16, clienteDto.getPersonaDto().getHuellaDactilar());
                stat.setString(17, clienteDto.getPersonaDto().getNumeroIdentificacion() + ".jpg");
                if (stat.executeUpdate() > 0) {
                    res = stat.getGeneratedKeys();
                    if (res.next()) {
                        correcto = stat.execute();
                        clienteDto.getPersonaDto().setId(res.getLong(1));
                    }
                }
            }
            if (correcto) {
                if (clienteDto.getId() != null && !Util.getVacio(String.valueOf(clienteDto.getId()))) {
                    stat = this.conexion.getConexion().prepareStatement("UPDATE  clientes SET persona_id = ? WHERE id = ?");
                    stat.setLong(1, clienteDto.getPersonaDto().getId());
                    stat.setLong(2, clienteDto.getId());
                    stat.executeUpdate();
                } else {
                    stat = this.conexion.getConexion().prepareStatement("INSERT INTO clientes(persona_id) " + "VALUES (?)", Statement.RETURN_GENERATED_KEYS);
                    stat.setLong(1, clienteDto.getPersonaDto().getId());
                    if (stat.executeUpdate() > 0) {
                        res = stat.getGeneratedKeys();
                        if (res.next()) {
                            correcto = true;
                            clienteDto.setId(res.getLong(1));
                        }
                    }
                }
            }
            stat.close();
        } catch (SQLException ex) {
            this.conexion.rollback();
            throw ex;
        } finally {
            this.conexion.commt();
        }
    }

    public void setGuardarUsuario(UsuarioDto usuarioDto) throws SQLException {
        PreparedStatement stat = null;
        if (usuarioDto.getId() > 0) {
            stat = this.conexion.getConexion().prepareStatement("UPDATE personas SET primer_nombre = ?, "
                    + "segundo_nombre = ?, primer_apellido = ?, "
                    + "tipo_identificacion = ?, numero_identificacion = ?, "
                    + "luga_expedicion = ?, genero = ?, "
                    + "estado_civil = ?, fecha_nacimiento = ?, "
                    + "direccion = ?, barrio = ?, "
                    + "telefono = ?, movil = ?, "
                    + "email = ?, huella_dactilar = ?, "
                    + "foto_perfil = ? "
                    + "WHERE id=? ");
            stat.setLong(18, usuarioDto.getId());
        } else {
            stat = this.conexion.getConexion().prepareStatement("INSERT INTO personas (primer_nombre, segundo_nombre, "
                    + "primer_apellido, segundo_apellido, "
                    + "tipo_identificacion, numero_identificacion, "
                    + "luga_expedicion, genero, "
                    + "estado_civil, fecha_nacimiento, "
                    + "direccion, barrio, "
                    + "telefono, movil, "
                    + "email, huella_dactilar, "
                    + "foto_perfil)  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        }
        stat.setString(1, usuarioDto.getPersonaDto().getPrimerNombre());
        stat.setString(2, usuarioDto.getPersonaDto().getSegundoNombre());
        stat.setString(3, usuarioDto.getPersonaDto().getPrimerApellido());
        stat.setString(4, usuarioDto.getPersonaDto().getSegundoApellido());
        stat.setShort(5, usuarioDto.getPersonaDto().getTipoIdentificacion());
        stat.setString(6, usuarioDto.getPersonaDto().getNumeroIdentificacion());
        stat.setString(7, usuarioDto.getPersonaDto().getLugarExpedicion());
        stat.setShort(8, usuarioDto.getPersonaDto().getGenero());
        stat.setShort(9, usuarioDto.getPersonaDto().getEstadoCivil());
        stat.setString(10, usuarioDto.getPersonaDto().getFechaNacimiento());
        stat.setString(11, usuarioDto.getPersonaDto().getDireccion());
        stat.setString(12, usuarioDto.getPersonaDto().getBarrio());
        stat.setString(13, usuarioDto.getPersonaDto().getTelefono());
        stat.setString(14, usuarioDto.getPersonaDto().getMovil());
        stat.setString(15, usuarioDto.getPersonaDto().getEmail());
        stat.setBytes(16, usuarioDto.getPersonaDto().getHuellaDactilar());
        stat.setString(17, usuarioDto.getPersonaDto().getNumeroIdentificacion() + ".jpg");
        if (stat.execute()) {
            if (usuarioDto.getId() > 0) {
                stat = this.conexion.getConexion().prepareStatement("UPDATE  usuarios  SET tipo_usuario = '?', yn_activo = '?', WHERE id = '?';");
                stat.setShort(1, usuarioDto.getTipoUsuario());
                stat.setShort(2, usuarioDto.getYnActivo());
                stat.setLong(3, usuarioDto.getId());
            } else {
                stat = this.conexion.getConexion().prepareStatement("INSERT INTO usuarios (tipo_usuario, persona_id, loggin, password, yn_activo, fecha_registro) "
                        + "VALUES ('?', '?', '?', '?', '?', 'NOW()') ;");
                stat.setShort(1, usuarioDto.getTipoUsuario());
                stat.setLong(2, usuarioDto.getPersonaDto().getId());
                stat.setString(3, usuarioDto.getLoggin());
                stat.setString(4, usuarioDto.getPassword());
                stat.setShort(5, usuarioDto.getYnActivo());
            }
            stat.execute();
        }
        stat.close();
    }

    public boolean setGuardarPaquete(PaqueteDto paquete) throws SQLException {
        PreparedStatement stat = null;
        if (paquete.getId() > 0) {
            stat = this.conexion.getConexion().prepareStatement("UPDATE paquetes SET nombre = ?, precio_base = ?, yn_tiquetera = ?, dias_aplazamiento = ? WHERE id=?");
            stat.setInt(5, paquete.getId());
        } else {
            stat = this.conexion.getConexion().prepareStatement("INSERT INTO paquetes (nombre, precio_base, yn_tiquetera, dias_aplazamiento) VALUES (?,?,?,?)");

        }
        stat.setString(1, paquete.getNombre());
        stat.setDouble(2, paquete.getPrecioBase());
        stat.setShort(3, paquete.getYnTiquetera());
        stat.setShort(4, paquete.getDiasAplazamiento());
        stat.execute();
        stat.close();
        return true;
    }

    public List<UsuarioDto> getUsuariosDatos(String loggin) throws SQLException {
        List<UsuarioDto> list = new ArrayList();
        Statement stat = this.conexion.getConexion().createStatement();
        String sql = "SELECT usu.*, per.*, per.id as idPersona FROM usuarios usu INNER JOIN personas per ON usu.persona_id=per.id WHERE loggin='" + loggin + "'";
        ResultSet res = stat.executeQuery(sql);
        while (res.next()) {
            UsuarioDto dto = new UsuarioDto();
            dto.setId(res.getLong("id"));
            dto.setLoggin(res.getString("loggin"));
            dto.setPassword(res.getString("password"));
            dto.setPersonaId(res.getLong("persona_id"));
            dto.getPersonaDto().setId(res.getLong("idPersona"));
            dto.getPersonaDto().setPrimerNombre(res.getString("primer_nombre"));
            dto.getPersonaDto().setSegundoNombre(res.getString("segundo_nombre"));
            dto.getPersonaDto().setPrimerApellido(res.getString("primer_apellido"));
            dto.getPersonaDto().setSegundoApellido(res.getString("segundo_apellido"));
            dto.getPersonaDto().setTipoIdentificacion(res.getShort("tipo_identificacion"));
            dto.getPersonaDto().setNumeroIdentificacion(res.getString("tipo_identificacion"));
            dto.getPersonaDto().setLugarExpedicion(res.getString("tipo_identificacion"));
            dto.getPersonaDto().setGenero(res.getShort("genero"));
            dto.getPersonaDto().setEstadoCivil(res.getShort("tipo_identificacion"));
            dto.getPersonaDto().setGenero(res.getShort("tipo_identificacion"));
            dto.getPersonaDto().setEstadoCivil(res.getShort("estado_civil"));
            dto.getPersonaDto().setFechaNacimiento(res.getString("fecha_nacimiento"));
            dto.getPersonaDto().setDireccion(res.getString("direccion"));
            dto.getPersonaDto().setBarrio(res.getString("barrio"));
            dto.getPersonaDto().setTelefono(res.getString("telefono"));
            dto.getPersonaDto().setMovil(res.getString("movil"));
            dto.getPersonaDto().setFechaRegistro(res.getString("fecha_registro"));
            dto.getPersonaDto().setFechaModificacion(res.getString("fecha_modificacion"));
            list.add(dto);
        }
        stat.close();
        return list;
    }

    /**
     *
     * @param idPaquete
     * @return
     * @throws SQLException
     */
    public List<PaqueteDto> getPaquetesDatos(String idPaquete) throws SQLException {
        List<PaqueteDto> list = new ArrayList();
        Statement stat = this.conexion.getConexion().createStatement();
        String sql = "SELECT * FROM paquetes WHERE 1";
        if (!Util.getVacio(idPaquete)) {
            sql += " AND id=" + idPaquete;

        }
        sql += " ORDER BY id ASC ";
        ResultSet res = stat.executeQuery(sql);
        while (res.next()) {
            PaqueteDto dto = new PaqueteDto();
            dto.setId(res.getInt("id"));
            dto.setNombre(res.getString("nombre"));
            dto.setPrecioBase(res.getDouble("precio_base"));
            dto.setYnTiquetera(res.getShort("yn_tiquetera"));
            dto.setDiasAplazamiento(res.getShort("dias_aplazamiento"));
            list.add(dto);
        }
        stat.close();
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
