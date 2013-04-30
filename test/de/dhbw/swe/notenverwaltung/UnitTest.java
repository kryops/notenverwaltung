package de.dhbw.swe.notenverwaltung;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.junit.Test;

/**
 * Tests der Klasse Unit
 * 
 * @author Michael Strobel
 *
 */
public class UnitTest {
	
	/*
	 * Konstruktoren-Tests
	 */
	
	@Test
	public void testConstructor() {
		
		Unit unit;
		
		/*
		 * kurzer Konstruktor nur mit Namen
		 * Gewichtung wird automatisch auf 100 gesetzt
		 */
		unit = new Unit("name");
		assertEquals("name", unit.getName());
		assertEquals(100, unit.getGewichtung());
		
		/*
		 * langer Konstruktor mit Name, Pr�fungsform und Gewichtung
		 */
		unit = new Unit("name", "pr�fungsform", 50);
		assertEquals("name", unit.getName());
		assertEquals("pr�fungsform", unit.getPruefungsform());
		assertEquals(50, unit.getGewichtung());
		
		// Grenzf�lle f�r die Gewichtung
		unit = new Unit("name", "pr�fungsform", 100);
		assertEquals(100, unit.getGewichtung());
		
		unit = new Unit("name", "pr�fungsform", 0);
		assertEquals(0, unit.getGewichtung());
	}
	
	
	@Test(expected = InvalidParameterException.class)
	public void testConstructorInvalidGewichtung() {
		new Unit("name", "pr�fungsform", 101);
	}
	
	
	@Test(expected = InvalidParameterException.class)
	public void testConstructorNegativeGewichtung() {
		new Unit("name", "pr�fungsform", -1);
	}
	
	/*
	 * Tests der Setter und Getter mit zus�tzlichen Aktionen
	 */
	
	// Gewichtung
	
	@Test
	public void testGewichtung() {
		Unit unit;
		
		unit = new Unit("name");
		unit.setGewichtung(50);
		assertEquals(50, unit.getGewichtung());
		
		// Grenzf�lle
		unit = new Unit("name");
		unit.setGewichtung(100);
		assertEquals(100, unit.getGewichtung());
		
		unit = new Unit("name");
		unit.setGewichtung(0);
		assertEquals(0, unit.getGewichtung());
	}
	
	
	@Test(expected = InvalidParameterException.class)
	public void testGewichtungInvalid() {
		Unit unit = new Unit("name");
		unit.setGewichtung(101);
	}
	
	@Test(expected = InvalidParameterException.class)
	public void testGewichtungNegative() {
		Unit unit = new Unit("name");
		unit.setGewichtung(-1);
	}
	
	// Note
	
	@Test
	public void testNote() {
		Unit unit;
		
		// eine Note von 4 oder besser markiert die Unit als bestanden
		unit = new Unit("name");
		unit.setNote(4.1);
		assertEquals(4.1, unit.getNote(), 0.01);
		assertFalse(unit.isBestanden());
		
		// Grenzf�lle
		unit = new Unit("name");
		unit.setNote(1);
		assertEquals(1.0, unit.getNote(), 0.01);
		assertTrue(unit.isBestanden());
		
		unit = new Unit("name");
		unit.setNote(4);
		assertEquals(4.0, unit.getNote(), 0.01);
		assertTrue(unit.isBestanden());
		
		unit = new Unit("name");
		unit.setNote(5);
		assertEquals(5.0, unit.getNote(), 0.01);
		assertFalse(unit.isBestanden());
	}
	
	
	@Test(expected = InvalidParameterException.class)
	public void testNoteTooHigh() {
		Unit unit = new Unit("name");
		unit.setNote(5.1);
	}
	
	
	@Test(expected = InvalidParameterException.class)
	public void testNoteTooLow() {
		Unit unit = new Unit("name");
		unit.setNote(0.9);
	}
	
	// bestanden
	
	@Test
	public void testBestanden() {
		Unit unit;
		
		// unbenotete Units werden beim Bestehen / Nichtbestehen auf 1 bzw. 5 gesetzt
		
		unit = new Unit("name", "pr�fungsform", 0);
		unit.setBestanden(true);
		assertEquals(1, unit.getNote(), 0.01);
		
		unit = new Unit("name", "pr�fungsform", 0);
		unit.setBestanden(false);
		assertEquals(5, unit.getNote(), 0.01);
	}
	
	
	/*
	 * andere Funktionen
	 */
	
	@Test
	public void testIsBenotet() {
		Unit unit;
		
		unit = new Unit("name", "pr�fungsform", 0);
		assertFalse(unit.isBenotet());
		
		unit.setGewichtung(1);
		assertTrue(unit.isBenotet());
		
		unit.setGewichtung(100);
		assertTrue(unit.isBenotet());
	}
	
}
