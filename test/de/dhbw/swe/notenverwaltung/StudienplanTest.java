package de.dhbw.swe.notenverwaltung;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

/**
 * Tests der Klasse Studienplan
 * 
 * @author Michael Strobel
 *
 */
public class StudienplanTest {
	
	/**
	 * Konstruktor testen
	 */
	@Test
	public void testConstructor() {
		Studienplan studienplan = new Studienplan("name", "abschluss");
		assertEquals("name", studienplan.getName());
		assertEquals("abschluss", studienplan.getAbschluss());
		assertEquals(new ArrayList<Modul>(), studienplan.getModule());
	}
	
	
	/**
	 * Modul-Liste testen
	 * Module hinzufügen und entfernen
	 */
	@Test
	public void testModulList() {
		Studienplan studienplan = new Studienplan("name", "abschluss");
		
		Modul modul1 = new Modul("modul1");
		Modul modul2 = new Modul("modul2");
		Modul modul3 = new Modul("modul3");
		
		// Module hinzufügen
		studienplan.addModul(modul1);
		assertEquals(1, studienplan.getModule().size());
		assertEquals(modul1, studienplan.getModule().get(0));
		
		assertEquals(studienplan, studienplan.getModule().get(0).getStudienplan());
		
		studienplan.addModul(modul2);
		assertEquals(2, studienplan.getModule().size());
		assertTrue(studienplan.getModule().contains(modul1));
		assertTrue(studienplan.getModule().contains(modul2));
		
		// enthaltenes Modul entfernen
		studienplan.removeModul(modul1);
		assertEquals(1, studienplan.getModule().size());
		assertFalse(studienplan.getModule().contains(modul1));
		assertTrue(studienplan.getModule().contains(modul2));
		
		// nicht enthaltenes Modul entfernen
		studienplan.removeModul(modul3);
		assertEquals(1, studienplan.getModule().size());
		assertTrue(studienplan.getModule().contains(modul2));
	}
	
	
	/**
	 * Unit anhand des Namens finden
	 * Units existieren
	 */
	@Test
	public void testFindUnitByNameExists() {
		Studienplan studienplan = new Studienplan("name", "abschluss");
		Modul modul1 = new Modul("modul1");
		Modul modul2 = new Modul("modul2");
		
		Unit unit1 = new Unit("unit1");
		Unit unit2 = new Unit("unit2");
		Unit unit3 = new Unit("unit3");
		
		modul1.addUnit(unit1);
		modul1.addUnit(unit2);
		modul2.addUnit(unit3);
		
		studienplan.addModul(modul1);
		studienplan.addModul(modul2);
		
		assertEquals(unit1, studienplan.findUnitByName("unit1"));
		assertEquals(unit2, studienplan.findUnitByName("unit2"));
		assertEquals(unit3, studienplan.findUnitByName("unit3"));
	}
	
	
	/**
	 * Unit anhand des Namens finden
	 * Unit existiert nicht
	 */
	@Test
	public void testFindUnitByNameExistsNot() {
		Studienplan studienplan = new Studienplan("name", "abschluss");
		Modul modul1 = new Modul("modul1");
		Modul modul2 = new Modul("modul2");
		
		Unit unit1 = new Unit("unit1");
		Unit unit2 = new Unit("unit2");
		Unit unit3 = new Unit("unit3");
		
		modul1.addUnit(unit1);
		modul1.addUnit(unit2);
		modul2.addUnit(unit3);
		
		studienplan.addModul(modul1);
		studienplan.addModul(modul2);
		
		assertEquals(null, studienplan.findUnitByName("unit4"));
	}
	
}
