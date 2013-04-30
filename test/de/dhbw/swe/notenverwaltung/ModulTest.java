package de.dhbw.swe.notenverwaltung;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

/**
 * Tests der Klasse Modul
 * 
 * @author Michael Strobel
 *
 */
public class ModulTest {
	
	@Test
	public void testConstructor() {
		Modul modul;
		
		modul = new Modul("name");
		assertEquals("name", modul.getName());
		assertEquals(new ArrayList<Unit>(), modul.getUnits());
	}
	
	
	@Test
	public void testUnitList() {
		Modul modul = new Modul("name");
		
		Unit u1 = new Unit("unit1");
		Unit u2 = new Unit("unit2");
		Unit u3 = new Unit("unit2");
		
		assertEquals(0, modul.getUnits().size());
		
		// Units hinzufügen
		modul.addUnit(u1);
		assertEquals(1, modul.getUnits().size());
		assertEquals(u1, modul.getUnits().get(0));
		
		assertEquals(modul.getUnits().get(0).getModul(), modul);
		
		modul.addUnit(u2);
		assertEquals(2, modul.getUnits().size());
		assertTrue(modul.getUnits().contains(u1));
		assertTrue(modul.getUnits().contains(u2));
		assertEquals(u1, modul.getUnits().get(0));
		assertEquals(u2, modul.getUnits().get(1));
		
		// enthaltene Unit entfernen
		modul.removeUnit(u1);
		assertEquals(1, modul.getUnits().size());
		assertTrue(modul.getUnits().contains(u2));
		
		// nicht enthaltene Unit entfernen
		modul.removeUnit(u3);
		assertEquals(1, modul.getUnits().size());
		assertTrue(modul.getUnits().contains(u2));
	}
	
	
	@Test
	public void testModulnoteBerechnen() {
		
		Modul modul;
		Unit unit1, unit2, unit3;
		
		/*
		 * benotetes Modul - bestanden
		 */
		
		// Gewichtung 50:50
		modul = new Modul("name");
		
		unit1 = new Unit("unit1", "prüfungsform", 50);
		unit2 = new Unit("unit2", "prüfungsform", 50);
		
		modul.addUnit(unit1);
		modul.addUnit(unit2);
		
		modul.getUnits().get(0).setNote(2);
		modul.getUnits().get(1).setNote(3);
		
		assertEquals(2.5, modul.getModulnote(), 0.01);
		assertTrue(modul.isBestanden());
		
		// Gewichtung 75:25 + eine unbenotete Unit
		modul = new Modul("name");
		
		unit1 = new Unit("unit1", "prüfungsform", 75);
		unit2 = new Unit("unit2", "prüfungsform", 25);
		unit3 = new Unit("unit3", "", 0);
		
		modul.addUnit(unit1);
		modul.addUnit(unit2);
		modul.addUnit(unit3);
		
		modul.getUnits().get(0).setNote(2);
		modul.getUnits().get(1).setNote(3);
		modul.getUnits().get(2).setBestanden(true);
		
		assertEquals(2.25, modul.getModulnote(), 0.01);
		assertTrue(modul.isBestanden());
		
		
		/*
		 * benotetes Modul - nicht bestanden
		 */
		
		// benotete Unit
		modul = new Modul("name");
		
		unit1 = new Unit("unit1", "prüfungsform", 50);
		unit2 = new Unit("unit2", "prüfungsform", 50);
		
		modul.addUnit(unit1);
		modul.addUnit(unit2);
		
		modul.getUnits().get(0).setNote(2);
		modul.getUnits().get(1).setNote(4.5);
		
		assertEquals(0, modul.getModulnote(), 0.01);
		assertFalse(modul.isBestanden());
		
		// unbenotete Unit
		modul = new Modul("name");
		
		unit1 = new Unit("unit1", "prüfungsform", 75);
		unit2 = new Unit("unit2", "prüfungsform", 25);
		unit3 = new Unit("unit3", "", 0);
		
		modul.addUnit(unit1);
		modul.addUnit(unit2);
		modul.addUnit(unit3);
		
		modul.getUnits().get(0).setNote(2);
		modul.getUnits().get(1).setNote(3);
		modul.getUnits().get(2).setBestanden(false);
		
		assertEquals(0, modul.getModulnote(), 0.01);
		assertFalse(modul.isBestanden());
		
		
		/*
		 * unbenotetes Modul - bestanden
		 */
		modul = new Modul("name");
		modul.setBenotet(false);
		
		unit1 = new Unit("unit1", "", 0);
		unit2 = new Unit("unit2", "", 0);
		
		modul.addUnit(unit1);
		modul.addUnit(unit2);
		
		modul.getUnits().get(0).setBestanden(true);
		modul.getUnits().get(1).setBestanden(true);
		
		assertTrue(modul.isBestanden());
		
		
		/*
		 * unbenotetes Modul - nicht bestanden
		 */
		modul = new Modul("name");
		modul.setBenotet(false);
		
		unit1 = new Unit("unit1", "", 0);
		unit2 = new Unit("unit2", "", 0);
		
		modul.addUnit(unit1);
		modul.addUnit(unit2);
		
		modul.getUnits().get(0).setBestanden(true);
		modul.getUnits().get(1).setBestanden(false);
		
		assertFalse(modul.isBestanden());
	}
	
}
