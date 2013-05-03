package de.dhbw.swe.notenverwaltung;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.junit.Test;

/**
 * Tests der Klasse Student
 * 
 * @author Michael Strobel
 *
 */
public class StudentTest {
	
	/**
	 * Konstruktor testen
	 */
	@Test
	public void testConstructor() {
		// TODO
		fail("implement me");
	}
	
	
	/**
	 * Bachelornote berechnen und auf Studienabschluss prüfen
	 * bestanden
	 */
	@Test
	public void testStudiumAbgeschlossenBestanden() {
		// TODO
		fail("implement me");
	}
	
	
	/**
	 * Bachelornote berechnen und auf Studienabschluss prüfen
	 * durchgefallen
	 */
	@Test
	public void testStudiumAbgeschlossenDurchgefallen() {
		// TODO
		fail("implement me");
	}
	
	
	/**
	 * Note der Bachelorarbeit eintragen
	 * Normalfall: 2.5, 4.5
	 * Grenzfälle: 1, 4, 4.1 und 5
	 */
	@Test
	public void testBachelorArbeitNote() {
		// TODO
		fail("implement me");
	}
	
	
	/**
	 * Note der Bachelorarbeit eintragen
	 * größer als 5 - Fehler
	 */
	@Test(expected = InvalidParameterException.class)
	public void testBachelorArbeitNoteTooHigh() {
		// TODO
		fail("implement me");
	}
	
	
	/**
	 * Note der Bachelorarbeit eintragen
	 * kleiner als 1 - Fehler
	 */
	@Test(expected = InvalidParameterException.class)
	public void testBachelorArbeitNoteTooLow() {
		// TODO
		fail("implement me");
	}
	
}
