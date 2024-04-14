package edu.upc.dsa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import edu.upc.dsa.models.Dron;
import edu.upc.dsa.models.Piloto;

import static org.junit.Assert.*;

public class ManagerImplTest {
    private Manager mng;
    @Before
    public void Up(){
        this.mng = new ManagerImpl();
        mng = ManagerImpl.getInstance();

        mng.añadirDron("1", "Dron 1", "Fabrica 1", "Modelo 1");
        mng.añadirDron("2", "Dron 2", "Fabrica 2", "Modelo 2");
        mng.añadirDron("3", "Dron 3", "Fabrica 3", "Modelo 3");

        mng.añadirPiloto("1", "Nombre 1", "Apellidos 1");
        mng.añadirPiloto("2", "Nombre 2", "Apellidos 2");
        mng.añadirPiloto("3", "Nombre 3", "Apellidos 3");
    }
    @After
    public void Down(){
        this.mng = null;
    }
    @Test
    public void testAñadirPiloto() throws Exception {
        Piloto piloto = mng.añadirPiloto("4", "Nombre 4", "Apellidos 4");
        assertNotNull(piloto);
        assertEquals("4", piloto.getIdPiloto());
        assertEquals("Nombre 4", piloto.getNombre());
        assertEquals("Apellidos 4", piloto.getApellidos());
    }
    @Test
    public void testAñadirDron() throws Exception {
        Dron dron = mng.añadirDron("4", "Dron 4", "Fabrica 4", "Modelo 4");
        assertNotNull(dron);
        assertEquals("4", dron.getIdDron());
        assertEquals("Dron 4", dron.getNombre());
        assertEquals("Fabrica 4", dron.getFabricante());
        assertEquals("Modelo 4", dron.getModelo());
    }
    @Test
    public void testGuardarDronEnAlmacen() throws Exception {
        Dron dron = mng.añadirDron("4", "Dron 4", "Fabrica 4", "Modelo 4");
        mng.guardarDronEnAlmacen("4");
        assertTrue(dron.isMantenimiento());
    }
    @Test
    public void testRepararDronEnAlmacen() throws Exception {
        Dron dron = mng.añadirDron("4", "Dron 4", "Fabrica 4", "Modelo 4");
        mng.guardarDronEnAlmacen("4");
        mng.repararDronEnAlmacen();
        assertFalse(dron.isMantenimiento());
    }
}
