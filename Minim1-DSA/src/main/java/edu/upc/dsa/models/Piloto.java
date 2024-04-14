package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class Piloto {
    List<Reserva> reservas;
    String idPiloto;
    String nombre;
    String apellidos;
    int horasVuelo;

    public Piloto(String identificador, String nombre, String apellidos){
        this.idPiloto=identificador;
        this.nombre=nombre;
        this.apellidos=apellidos;
        this.horasVuelo=0;
        this.reservas=new ArrayList<>();
    }
    public String getIdPiloto() {
        return idPiloto;
    }
    public void setIdPiloto(String id) {
        this.idPiloto=id;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getApellidos(){
        return apellidos;
    }
    public void setApellidos(String apellidos){
        this.apellidos = apellidos;
    }
    public int getHorasVuelo(){
        return horasVuelo;
    }
    public void setHorasVuelo(int horasVuelo){
        this.horasVuelo = horasVuelo;
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

    public static Piloto buscarPilotoPorId(List<Piloto> pilotos, String  pilotoId){
        for(Piloto piloto : pilotos){
            if(piloto.getIdPiloto().equals(pilotoId)) {
                return piloto;
            }
        }
        return null;
    }
}
