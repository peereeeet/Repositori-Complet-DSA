package edu.upc.dsa;

import java.util.*;

import edu.upc.dsa.models.Dron;
import edu.upc.dsa.models.Piloto;
import edu.upc.dsa.models.Reserva;
import org.apache.log4j.Logger;

public class ManagerImpl implements Manager {
    private static Manager instance;
    protected List<Dron> drones;
    protected List<Piloto> pilotos;
    protected List<Reserva> reservas;
    protected Queue<Dron> colaMantenimiento;
    final static Logger logger = Logger.getLogger(ManagerImpl.class);

    public ManagerImpl(){
        this.pilotos = new ArrayList<>();
        this.drones = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.colaMantenimiento = new LinkedList<>();
    }
    public static Manager getInstance() {
        if (instance==null) instance = new ManagerImpl();
        return instance;
    }
    @Override
    public Dron añadirDron(String identificador, String nombre, String fabricante, String modelo) {
        Dron newDron = new Dron(identificador, nombre, fabricante, modelo);
        this.drones.add(newDron);
        logger.info("Dron añadido");
        return newDron;
    }
    @Override
    public Piloto añadirPiloto(String identificador, String nombre, String apellidos){
        Piloto newPiloto = new Piloto(identificador, nombre, apellidos);
        this.pilotos.add(newPiloto);
        logger.info("Piloto añadido");
        return newPiloto;
    }
    public List<Dron> listarDronesPorHorasDeVuelo(){
        List<Dron> dronesHoras = new ArrayList<>(this.drones);
        dronesHoras.sort((d1, d2) -> d2.getHorasVuelo() - d1.getHorasVuelo());
        logger.info("Consulta de usuarios que han participado en un juego ordenado por puntuación (descendente). " + dronesHoras);
        return dronesHoras;
    }
    public List<Piloto> listarPilotosPorHorasDeVuelo(){
        List<Piloto> pilotosHoras = new ArrayList<>();
        pilotosHoras.sort((p1, p2) -> p2.getHorasVuelo() - p2.getHorasVuelo());
        logger.info("Consulta de usuarios que han participado en un juego ordenado por puntuación (descendente). " + pilotosHoras);
        return pilotosHoras;
    }
    @Override
    public void guardarDronEnAlmacen(String identificadorDron){
        for (Dron dron : this.drones){
            if (dron.getIdDron().equals(identificadorDron)){
                dron.setMantenimiento(true);
                this.colaMantenimiento.add(dron);
                logger.info("Nuevo dron en mantenimiento");
                break;
            }
        }
    }
    @Override
    public Dron repararDronEnAlmacen(){
        if (!this.colaMantenimiento.isEmpty()){
            Dron dron = this.colaMantenimiento.poll();
            dron.setMantenimiento(false);
            logger.info("Dron sale del mantenimiento");
            return dron;
        }
        return null;
    }
    @Override
    public void añadirReservaPlanVuelo(String identificadorDron, Date fecha, int duracion, String posicionInicio, String posicionDestino, String identificadorPiloto){
        Dron dron = null;
        Piloto piloto = null;
        for (Dron dron2 : this.drones){
            if (dron2.getIdDron().equals(identificadorDron))
            {
                dron = dron2;
                break;
            }
        }
        for (Piloto piloto2 : this.pilotos){
            if (piloto2.getIdPiloto().equals(identificadorPiloto))
            {
                piloto = piloto2;
                break;
            }
        }

        if (dron == null || piloto == null){
            logger.info("No encontrado");
        }
        if (dron.isMantenimiento()){
            logger.info("Dron está es mantenimiento");
        }

        Date ffecha = new Date(fecha.getTime() + duracion * 60 * 60 * 1000);
        for (Reserva reserva : this.reservas){
            if (reserva.getDronId().equals(dron.getIdDron()) || reserva.getPilotoId().equals(piloto.getIdPiloto())){
                Date rDuracion = new Date(reserva.getFecha().getTime() + reserva.getDuracion() * 60 * 60 * 1000);
                if ((fecha.before(rDuracion) && ffecha.after(reserva.getFecha()))){
                    logger.info("Ya tiene asignado una reserva en ese tiempo");
                }
            }
        }
        Reserva newReserva = new Reserva(dron.getIdDron(), fecha, duracion, posicionInicio, posicionDestino, piloto.getIdPiloto());
        this.reservas.add(newReserva);
        dron.añadirReserva(newReserva);
        piloto.añadirReserva(newReserva);
        logger.info("Reserva añadida");
    }
    @Override
    public List<Reserva> listarReservasPorPiloto(String identificadorPiloto){
        List<Reserva> resPiloto = new ArrayList<>();
        for (Reserva reserva : this.reservas){
            if (reserva.getPilotoId().equals(identificadorPiloto))
                resPiloto.add(reserva);
        }
        return resPiloto;
    }
    @Override
    public List<Reserva> listarPlanesVueloPorDron(String identificadorDron){
        List<Reserva> resDron = new ArrayList<>();
        for (Reserva reserva : this.reservas){
            if (reserva.getDronId().equals(identificadorDron))
                resDron.add(reserva);
        }
        return resDron;
    }
    public int size(){
        int q =this.drones.size();
        logger.info("Tamaño: " + q);
        return q;
    }
}