package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class Dron {
    List<Reserva> reservas;
    String idDron;
    String nombre;
    String fabricante;
    String modelo;
    int horasVuelo;
    boolean mantenimiento;

    public Dron(String identificador, String nombre, String fabricante, String modelo){
        this.idDron=identificador;
        this.nombre=nombre;
        this.fabricante=fabricante;
        this.modelo=modelo;
        this.horasVuelo=0;
        this.mantenimiento=false;
        this.reservas=new ArrayList<>();
    }
    public String getIdDron() {
        return idDron;
    }

    public void setIdDron(String id) {
        this.idDron=id;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getFabricante(){
        return fabricante;
    }
    public void setFabricante(String fabricante){
        this.fabricante = fabricante;
    }
    public String getModelo(){
        return modelo;
    }
    public void setModelo(String modelo){
        this.modelo = modelo;
    }
    public int getHorasVuelo(){
        return horasVuelo;
    }
    public void setHorasVuelo(int horasVuelo){
        this.horasVuelo = horasVuelo;
    }
    public boolean isMantenimiento(){
        return mantenimiento;
    }
    public void setMantenimiento(boolean mantenimiento){
        this.mantenimiento = mantenimiento;
    }
    public List<Reserva> getReservas(){
        return reservas;
    }
    public void setReservas(List<Reserva> reservas){
        this.reservas = reservas;
    }
    public void añadirReserva(Reserva reserva){
        this.reservas.add(reserva);
    }

    public static Dron buscarDronPorId(List<Dron> drones, String  dronId){
        for(Dron dron : drones){
            if(dron.getIdDron().equals(dronId)) {
                return dron;
            }
        }
        return null;
    }
}
