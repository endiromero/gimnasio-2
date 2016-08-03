package com.gimnasio.model;
// Generated ago 2, 2016 10:46:44 p.m. by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Clientes generated by hbm2java
 */
public class Clientes  implements java.io.Serializable {


     private long id;
     private Personas personas;
     private BigDecimal peso;
     private BigDecimal talla;
     private BigDecimal muslos;
     private BigDecimal cintura;
     private BigDecimal torax;
     private BigDecimal brazos;
     private Short contextura;
     private Set clientePaquetes = new HashSet(0);

    public Clientes() {
    }

	
    public Clientes(long id, Personas personas) {
        this.id = id;
        this.personas = personas;
    }
    public Clientes(long id, Personas personas, BigDecimal peso, BigDecimal talla, BigDecimal muslos, BigDecimal cintura, BigDecimal torax, BigDecimal brazos, Short contextura, Set clientePaquetes) {
       this.id = id;
       this.personas = personas;
       this.peso = peso;
       this.talla = talla;
       this.muslos = muslos;
       this.cintura = cintura;
       this.torax = torax;
       this.brazos = brazos;
       this.contextura = contextura;
       this.clientePaquetes = clientePaquetes;
    }
   
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public Personas getPersonas() {
        return this.personas;
    }
    
    public void setPersonas(Personas personas) {
        this.personas = personas;
    }
    public BigDecimal getPeso() {
        return this.peso;
    }
    
    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }
    public BigDecimal getTalla() {
        return this.talla;
    }
    
    public void setTalla(BigDecimal talla) {
        this.talla = talla;
    }
    public BigDecimal getMuslos() {
        return this.muslos;
    }
    
    public void setMuslos(BigDecimal muslos) {
        this.muslos = muslos;
    }
    public BigDecimal getCintura() {
        return this.cintura;
    }
    
    public void setCintura(BigDecimal cintura) {
        this.cintura = cintura;
    }
    public BigDecimal getTorax() {
        return this.torax;
    }
    
    public void setTorax(BigDecimal torax) {
        this.torax = torax;
    }
    public BigDecimal getBrazos() {
        return this.brazos;
    }
    
    public void setBrazos(BigDecimal brazos) {
        this.brazos = brazos;
    }
    public Short getContextura() {
        return this.contextura;
    }
    
    public void setContextura(Short contextura) {
        this.contextura = contextura;
    }
    public Set getClientePaquetes() {
        return this.clientePaquetes;
    }
    
    public void setClientePaquetes(Set clientePaquetes) {
        this.clientePaquetes = clientePaquetes;
    }




}


