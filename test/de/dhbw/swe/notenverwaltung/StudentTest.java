package de.dhbw.swe.notenverwaltung;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;
import java.util.Date;

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
		Date geburtsdatum = new Date();
		Student student = new Student(1234, "vorname", "nachname", geburtsdatum, "geburtsort");
		
		assertEquals(1234, student.getMatrikelnummer());
		assertEquals("vorname", student.getVorname());
		assertEquals("nachname", student.getNachname());
		assertEquals(geburtsdatum, student.getGeburtsdatum());
		assertEquals("geburtsort", student.getGeburtsort());
		assertTrue((student.getStudienplan() instanceof Studienplan));
		assertTrue(student.isImmatrikuliert());
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
		Student student = new Student(1234, "vorname", "nachname", new Date(), "geburtsort");
		
		// Normalfälle: 2.5 (bestanden), 4.5 (nicht bestanden)
		student.setBachelorArbeitNote(2.5);
		assertEquals(2.5, student.getBachelorArbeitNote(), 0.01);
		assertTrue(student.isBachelorArbeitBestanden());
		
		student.setBachelorArbeitNote(4.5);
		assertEquals(4.5, student.getBachelorArbeitNote(), 0.01);
		assertFalse(student.isBachelorArbeitBestanden());
		
		// Grenzfälle: 1, 4 (bestanden), 4.1 und 5 (nicht bestanden)
		student.setBachelorArbeitNote(4);
		assertEquals(4, student.getBachelorArbeitNote(), 0.01);
		assertTrue(student.isBachelorArbeitBestanden());
		
		student.setBachelorArbeitNote(4.1);
		assertEquals(4.1, student.getBachelorArbeitNote(), 0.01);
		assertFalse(student.isBachelorArbeitBestanden());
		
		student.setBachelorArbeitNote(5);
		assertEquals(5, student.getBachelorArbeitNote(), 0.01);
		assertFalse(student.isBachelorArbeitBestanden());
	}
	
	
	/**
	 * Note der Bachelorarbeit eintragen
	 * größer als 5 - Fehler
	 */
	@Test(expected = InvalidParameterException.class)
	public void testBachelorArbeitNoteTooHigh() {
		Student student = new Student(1234, "vorname", "nachname", new Date(), "geburtsort");
		student.setBachelorArbeitNote(5.1);
	}
	
	
	/**
	 * Note der Bachelorarbeit eintragen
	 * kleiner als 1 - Fehler
	 */
	@Test(expected = InvalidParameterException.class)
	public void testBachelorArbeitNoteTooLow() {
		Student student = new Student(1234, "vorname", "nachname", new Date(), "geburtsort");
		student.setBachelorArbeitNote(0.9);
	}
	
}
