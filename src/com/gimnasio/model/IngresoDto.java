package com.gimnasio.model;
// Generated ago 3, 2016 11:34:53 p.m. by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;

/**
 * IngresoDto generated by hbm2java
 */
public class IngresoDto implements java.io.Serializable {

    private Long id;
    private UsuarioDto usuarioDto;
    private String tipoIngreso;
    private BigDecimal valor;
    private Date fechaRegistro;

    public IngresoDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioDto getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto usuarioDto) {
        this.usuarioDto = usuarioDto;
    }

    public String getTipoIngreso() {
        return tipoIngreso;
    }

    public void setTipoIngreso(String tipoIngreso) {
        this.tipoIngreso = tipoIngreso;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

}
