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
		 * langer Konstruktor mit Name, Prüfungsform und Gewichtung
		 */
		unit = new Unit("name", "prüfungsform", 50);
		assertEquals("name", unit.getName());
		assertEquals("prüfungsform", unit.getPruefungsform());
		assertEquals(50, unit.getGewichtung());
		
		// Grenzfälle für die Gewichtung
		unit = new Unit("name", "prüfungsform", 100);
		assertEquals(100, unit.getGewichtung());
		
		unit = new Unit("name", "prüfungsform", 0);
		assertEquals(0, unit.getGewichtung());
	}
	
	
	/**
	 * Konstruktor testen
	 * Gewichtung über 100 - Fehlre
	 */
	@Test(expected = InvalidParameterException.class)
	public void testConstructorInvalidGewichtung() {
		new Unit("name", "prüfungsform", 101);
	}
	
	
	/**
	 * Konstruktor testen
	 * Gewichtung negativ - Fehler
	 */
	@Test(expected = InvalidParameterException.class)
	public void testConstructorNegativeGewichtung() {
		new Unit("name", "prüfungsform", -1);
	}
	
	
	/*
	 * Tests der Setter und Getter mit zusätzlichen Aktionen
	 */
	
	/**
	 * Gewichtung testen
	 * Normalfall 50
	 * Grenzfälle 100 und 0
	 */
	@Test
	public void testGewichtung() {
		Unit unit;
		
		unit = new Unit("name");
		unit.setGewichtung(50);
		assertEquals(50, unit.getGewichtung());
		
		// Grenzfälle
		unit = new Unit("name");
		unit.setGewichtung(100);
		assertEquals(100, unit.getGewichtung());
		
		unit = new Unit("name");
		unit.setGewichtung(0);
		assertEquals(0, unit.getGewichtung());
	}
	
	
	/**
	 * Gewichtung testen
	 * über 100 - Fehler
	 */
	@Test(expected = InvalidParameterException.class)
	public void testGewichtungInvalid() {
		Unit unit = new Unit("name");
		unit.setGewichtung(101);
	}
	
	
	/**
	 * Gewichtung testen
	 * negativ - Fehler
	 */
	@Test(expected = InvalidParameterException.class)
	public void testGewichtungNegative() {
		Unit unit = new Unit("name");
		unit.setGewichtung(-1);
	}
	
	// Note
	
	/**
	 * Note eintragen
	 * Normalfall: 2.5, 4.5
	 * Grenzfälle: 1, 4, 4.1 und 5
	 * 
	 */
	@Test
	public void testNote() {
		Unit unit;
		
		unit = new Unit("name");
		unit.setNote(2.5);
		assertEquals(2.5, unit.getNote(), 0.01);
		assertTrue(unit.isBestanden());
		
		// bei 4 oder schlechter ist die Unit nicht bestanden
		unit = new Unit("name");
		unit.setNote(4.5);
		assertEquals(4.5, unit.getNote(), 0.01);
		assertFalse(unit.isBestanden());
		
		// Grenzfälle: 1, 4, 4.5 und 5
		unit = new Unit("name");
		unit.setNote(1);
		assertEquals(1.0, unit.getNote(), 0.01);
		assertTrue(unit.isBestanden());
		
		unit = new Unit("name");
		unit.setNote(4);
		assertEquals(4.0, unit.getNote(), 0.01);
		assertTrue(unit.isBestanden());
		
		unit = new Unit("name");
		unit.setNote(4.1);
		assertEquals(4.1, unit.getNote(), 0.01);
		assertFalse(unit.isBestanden());
		
		unit = new Unit("name");
		unit.setNote(5);
		assertEquals(5.0, unit.getNote(), 0.01);
		assertFalse(unit.isBestanden());
	}
	
	/**
	 * Note eintragen
	 * größer als 5 - Fehler
	 */
	@Test(expected = InvalidParameterException.class)
	public void testNoteTooHigh() {
		Unit unit = new Unit("name");
		unit.setNote(5.1);
	}
	
	/**
	 * Note eintragen
	 * kleiner als 1 - Fehler
	 */
	@Test(expected = InvalidParameterException.class)
	public void testNoteTooLow() {
		Unit unit = new Unit("name");
		unit.setNote(0.9);
	}
	
	/**
	 * Bestanden-Funktionalität testen
	 */
	@Test
	public void testBestanden() {
		Unit unit;
		
		// unbenotete Units werden beim Bestehen / Nichtbestehen auf 1 bzw. 5 gesetzt
		
		unit = new Unit("name", "prüfungsform", 0);
		unit.setBestanden(true);
		assertEquals(1, unit.getNote(), 0.01);
		
		unit = new Unit("name", "prüfungsform", 0);
		unit.setBestanden(false);
		assertEquals(5, unit.getNote(), 0.01);
	}
	
	
	/*
	 * andere Funktionen
	 */
	
	/**
	 * Handelt es sich um eine benotete Unit?
	 */
	@Test
	public void testIsBenotet() {
		Unit unit;
		
		unit = new Unit("name", "prüfungsform", 0);
		assertFalse(unit.isBenotet());
		
		unit.setGewichtung(1);
		assertTrue(unit.isBenotet());
		
		unit.setGewichtung(100);
		assertTrue(unit.isBenotet());
	}
	
}
