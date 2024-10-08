package org.example.Models;

import java.util.List;

public class OrdenTrabajo {
    private String id;
    private String fecha;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private Mecanico mecanico;
    private List<ParteDañada> partesDañadas;
    private List<Repuesto> repuestos;
    private HojaDeTrabajo hojaDeTrabajo;
    private String estado;
    private String fechaInicio;
    private String fechaFinalizacion;

    // Getters y setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Mecanico getMecanico() {
        return mecanico;
    }

    public void setMecanico(Mecanico mecanico) {
        this.mecanico = mecanico;
    }

    public List<ParteDañada> getPartesDañadas() {
        return partesDañadas;
    }

    public void setPartesDañadas(List<ParteDañada> partesDañadas) {
        this.partesDañadas = partesDañadas;
    }

    public List<Repuesto> getRepuestos() {
        return repuestos;
    }

    public void setRepuestos(List<Repuesto> repuestos) {
        this.repuestos = repuestos;
    }

    public HojaDeTrabajo getHojaDeTrabajo() {
        return hojaDeTrabajo;
    }

    public void setHojaDeTrabajo(HojaDeTrabajo hojaDeTrabajo) {
        this.hojaDeTrabajo = hojaDeTrabajo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(String fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    // Clase Cliente
    public static class Cliente {
        private String nombre;
        private String telefono;

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        public String getCorreo() {
            return correo;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }

        private String correo;

        // Getters y setters
    }

    // Clase Vehiculo
    public static class Vehiculo {
        private String marca;
        private String modelo;
        private int año;
        private String numeroPlaca;

        public String getMarca() {
            return marca;
        }

        public void setMarca(String marca) {
            this.marca = marca;
        }

        public String getModelo() {
            return modelo;
        }

        public void setModelo(String modelo) {
            this.modelo = modelo;
        }

        public int getAño() {
            return año;
        }

        public void setAño(int año) {
            this.año = año;
        }

        public String getNumeroPlaca() {
            return numeroPlaca;
        }

        public void setNumeroPlaca(String numeroPlaca) {
            this.numeroPlaca = numeroPlaca;
        }
        // Getters y setters
    }

    // Clase Mecanico
    public static class Mecanico {
        private String nombre;
        private String idEmpleado;

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getIdEmpleado() {
            return idEmpleado;
        }

        public void setIdEmpleado(String idEmpleado) {
            this.idEmpleado = idEmpleado;
        }
// Getters y setters
    }

    // Clase ParteDañada
    public static class ParteDañada {
        private String descripcion;
        private String fotoUrl;

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getFotoUrl() {
            return fotoUrl;
        }

        public void setFotoUrl(String fotoUrl) {
            this.fotoUrl = fotoUrl;
        }

// Getters y setters
    }

    // Clase Repuesto
    public static class Repuesto {
        private String descripcion;
        private int cantidad;
        private double precio;
        private String fotoUrl;

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public double getPrecio() {
            return precio;
        }

        public void setPrecio(double precio) {
            this.precio = precio;
        }

        public String getFotoUrl() {
            return fotoUrl;
        }

        public void setFotoUrl(String fotoUrl) {
            this.fotoUrl = fotoUrl;
        }

        // Getters y setters
    }

    // Clase HojaDeTrabajo
    public static class HojaDeTrabajo {
        private String descripcionTrabajo;
        private String tiempoEstimado;
        private double costoManoDeObra;
        private List<String> fotosTrabajo;

        public String getDescripcionTrabajo() {
            return descripcionTrabajo;
        }

        public void setDescripcionTrabajo(String descripcionTrabajo) {
            this.descripcionTrabajo = descripcionTrabajo;
        }

        public String getTiempoEstimado() {
            return tiempoEstimado;
        }

        public void setTiempoEstimado(String tiempoEstimado) {
            this.tiempoEstimado = tiempoEstimado;
        }

        public double getCostoManoDeObra() {
            return costoManoDeObra;
        }

        public void setCostoManoDeObra(double costoManoDeObra) {
            this.costoManoDeObra = costoManoDeObra;
        }

        public List<String> getFotosTrabajo() {
            return fotosTrabajo;
        }

        public void setFotosTrabajo(List<String> fotosTrabajo) {
            this.fotosTrabajo = fotosTrabajo;
        }

        // Getters y setters
    }
}