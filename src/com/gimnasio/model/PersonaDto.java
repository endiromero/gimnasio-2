package com.gimnasio.model;
// Generated ago 3, 2016 11:34:53 p.m. by Hibernate Tools 4.3.1

import com.digitalpersona.onetouch.DPFPTemplate;
import com.gimnasio.util.Util;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import jdk.nashorn.internal.codegen.CompilerConstants;

/**
 * PersonaDto generated by hbm2java
 */
public class PersonaDto implements java.io.Serializable {

    private Long id;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private short tipoIdentificacion;
    private String numeroIdentificacion;
    private String lugarExpedicion;
    private short genero;
    private Short estadoCivil;
    private String fechaNacimiento;
    private String direccion;
    private String barrio;
    private String telefono;
    private String movil;
    private String email;
    private String fotoPerfil;
    private byte[] huellaDactilar;
    private DPFPTemplate templateHuella;
    private String fechaRegistro;
    private String fechaModificacion;
    
    
    public PersonaDto() {
        this.genero = 1;
        this.fechaNacimiento = "1990-01-03";
    }

    public String getNombreCompleto() {
        String nombre = "";
        if (!Util.getVacio(this.primerNombre)) {
            nombre += this.primerNombre;
            if (!Util.getVacio(this.segundoNombre)) {
                nombre += " " + this.segundoNombre;
            }
            nombre += " " + this.primerApellido;
            if (!Util.getVacio(this.segundoApellido)) {
                nombre += " " + this.segundoApellido;
            }
        }
        return nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public short getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(short tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getLugarExpedicion() {
        return lugarExpedicion;
    }

    public void setLugarExpedicion(String lugarExpedicion) {
        this.lugarExpedicion = lugarExpedicion;
    }

    public short getGenero() {
        return genero;
    }

    public void setGenero(short genero) {
        this.genero = genero;
    }

    public Short getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(Short estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public byte[] getHuellaDactilar() {
        return huellaDactilar;
    }

    public void setHuellaDactilar(byte[] huellaDactilar) {
        this.huellaDactilar = huellaDactilar;
    }

    public DPFPTemplate getTemplateHuella() {
        return templateHuella;
    }

    public void setTemplateHuella(DPFPTemplate templateHuella) {
        this.templateHuella = templateHuella;
    }
    
    /**
     * 
     * @return 
     */    
    public double calcularEdad() { 
        double age = 0;        
        try {            
            Calendar birth = new GregorianCalendar();
            Calendar today = new GregorianCalendar();            
            int factor = 0;            
            Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(this.getFechaNacimiento()); //Date();//  
            Date currentDate = new Date(); //current date
            birth.setTime(birthDate);
            today.setTime(currentDate);            
            double mes_act = today.get(Calendar.MONTH);
            double mes_nac = birth.get(Calendar.MONTH);
            double ano = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
            age = ano +((mes_act - mes_nac)/12);            
        } catch (ParseException e) {
            
        }
        BigDecimal edad = new BigDecimal(age);
        return edad.setScale(1, RoundingMode.HALF_UP).doubleValue();
    }
    
}
    

