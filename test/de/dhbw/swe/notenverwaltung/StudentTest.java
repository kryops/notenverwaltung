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
		Student student = new Student(1234, "vorname", "nachname", geburtsdatum, "geburtsort", "heimadresse", 1.5);
		
		assertEquals(1234, student.getMatrikelnummer());
		assertEquals("vorname", student.getVorname());
		assertEquals("nachname", student.getNachname());
		assertEquals(geburtsdatum, student.getGeburtsdatum());
		assertEquals("geburtsort", student.getGeburtsort());
		assertEquals("heimadresse", student.getHeimadresse());
		assertEquals(1.5, student.getAbiturnote(), 0.01);
		assertTrue((student.getStudienplan() instanceof Studienplan));
		assertTrue(student.isImmatrikuliert());
	}
	
	
	/**
	 * Unit-Noten in einen AI-Studienplan eintragen
	 * alle Module außer dem Bachelorarbeit-Modul werden als bestanden eingetragen
	 * @param studienplan
	 */
	private void notenEintragen(Studienplan studienplan) {
		// Mathematik I - 8 Credits 50:50 --> Modulnote 2.75
		studienplan.findUnitByName("Analysis").setNote(2.5);
		studienplan.findUnitByName("Lineare Algebra").setNote(3);
		
		// Theoretische Informatik I - 5 Credits --> Modulnote 2
		studienplan.findUnitByName("Grundlagen und Logik").setNote(2);
		
		// Theoretische Informatik II - 5 Credits --> Modulnote 1.5
		studienplan.findUnitByName("Algorithmen und Komplexität").setNote(1.5);
		
		// Programmieren - 9 Credits 50:50 --> Modulnote 3
		studienplan.findUnitByName("Programmieren 1").setNote(2.5);
		studienplan.findUnitByName("Programmieren 2").setNote(3.5);
		
		// Schlüsselqualifikationen I - 5 Credits --> Modulnote 3.4
		studienplan.findUnitByName("Betriebswirtschaftslehre").setNote(3.4);
		studienplan.findUnitByName("Marketing 1").setBestanden(true);
		studienplan.findUnitByName("Marketing 2").setBestanden(true);
		
		// Technische Informatik I - 5 Credits --> Modulnote 1.3
		studienplan.findUnitByName("Digitaltechnik").setNote(1.3);
		
		// Webengineering I - 3 Credits --> Modulnote 2.0
		studienplan.findUnitByName("Webengineering I").setNote(2);
		studienplan.findUnitByName("Labor Webengineering I").setBestanden(true);
		
		// Projekt AI - 5 Credits --> Modulnote 2.25
		studienplan.findUnitByName("Projektmanagement I").setNote(2);
		studienplan.findUnitByName("Projektmanagement II").setNote(2.5);
		studienplan.findUnitByName("Labor AI").setBestanden(true);
		
		// Schlüsselqualifikationen II - 5 Credits --> Modulnote 1.6 
		studienplan.findUnitByName("Intercultural Communication 1").setNote(1.5);
		studienplan.findUnitByName("Intercultural Communication 2").setNote(1.7);
		studienplan.findUnitByName("Vortrags-, Lern- und Arbeitstechniken").setBestanden(true);
		studienplan.findUnitByName("Übung Schlüsselqualifikationen").setBestanden(true);
		
		// Praxis I - 20 Credits (nicht mitgezählt)
		studienplan.findUnitByName("Projektarbeit I").setBestanden(true);
		studienplan.findUnitByName("Wissenschaftliches Arbeiten").setBestanden(true);
		
		// Mathematik II - 6 Credits --> Modulnote 3.2
		studienplan.findUnitByName("Angewandte Mathematik").setNote(2.9);
		studienplan.findUnitByName("Statistik").setNote(3.5);
		
		// Theoretische Informatik III - 6 Credits --> Modulnote 2.4 
		studienplan.findUnitByName("Compilerbauwerkzeuge").setBestanden(true);
		studienplan.findUnitByName("Einführung Compilerbau").setBestanden(true);
		studienplan.findUnitByName("Formale Sprachen und Automaten 1").setNote(2.4);
		studienplan.findUnitByName("Formale Sprachen und Automaten 2").setBestanden(true);
		
		// Software Engineering I - 9 Credits --> Modulnote 1.2
		studienplan.findUnitByName("Grundlagen des Software-Engineering").setNote(1.2);
		
		// Datenbanken I - 6 Credits --> Modulnote 1.8
		studienplan.findUnitByName("Grundlagen der Datenbanken").setNote(1.8);
		
		// Technische Informatik II - 8 Credits --> Modulnote 2.6
		studienplan.findUnitByName("Betriebssysteme").setNote(2);
		studienplan.findUnitByName("Rechnerarchitekturen 1").setNote(3);
		studienplan.findUnitByName("Systemnahe Programmierung 1").setBestanden(true);
		
		// Kommunikations- und Netztechnik I - 5 Credits --> Modulnote 1.4
		studienplan.findUnitByName("Netztechnik 1").setNote(1.4);
		studienplan.findUnitByName("Labor Netztechnik").setBestanden(true);
		
		// Techniken der Informatik - 5 Credits --> Modulnote 3.1
		studienplan.findUnitByName("Compilerbau").setNote(4);
		studienplan.findUnitByName("Webengineering 2").setNote(2.2);
		
		// Wahlmodul AI Jahr 2 - 5 Credits --> Modulnote 2.2
		studienplan.findUnitByName("Wahlfach 2. Studienjahr").setNote(2.2);
		
		// Praxis II - 20 Credits --> Modulnote 1.9
		studienplan.findUnitByName("Mündliche Prüfung").setNote(1.9);
		studienplan.findUnitByName("Projektarbeit II").setNote(1.9);
		studienplan.findUnitByName("Wissenschaftliches Arbeiten 2").setBestanden(true);
		
		// Software Engineering II - 10 Credits --> Modulnote 1.5
		studienplan.findUnitByName("Advanced Software Engineering").setNote(1.5);
		studienplan.findUnitByName("Softwarequalität").setBestanden(true);
		
		// Große Studienarbeit - 10 Credits --> Modulnote 2.8
		studienplan.findUnitByName("Große Studienarbeit").setNote(2.8);
		
		// Kommunikations- und Netztechnik II - 5 Credits --> Modulnote 2.5
		studienplan.findUnitByName("IT-Sicherheit").setNote(2);
		studienplan.findUnitByName("Verteilte Systeme").setNote(3);
		
		// Datenbanken II - 5 Credits --> Modulnote 2.0
		studienplan.findUnitByName("DB-Implementierungen").setNote(1);
		studienplan.findUnitByName("Data Warehouse").setNote(3);
		
		// Architekturen - 5 Credits --> Modulnote 1.6
		studienplan.findUnitByName("Architekturen von Businesssystemen").setNote(1.8);
		studienplan.findUnitByName("Architekturen von Rechnersystemen").setNote(1.4);
		
		// Consulting, technischer Vertrieb und Recht - 5 Credits --> Modulnote 3.2
		studienplan.findUnitByName("Consulting und technischer Vertrieb").setNote(3.3);
		studienplan.findUnitByName("Recht").setNote(3.1);
		
		// Wissensbasierte und intelligente Systeme - 5 Credits --> Modulnote 3.6
		studienplan.findUnitByName("Interaktive Systeme").setNote(3.2);
		studienplan.findUnitByName("Wissensbasierte Systeme").setNote(4);
		
		// Wahlmodul AI Jahr 3 - 5 Credits --> Modulnote 2.1
		studienplan.findUnitByName("Wahlfach 3. Studienjahr").setNote(2.1);
		
		// Praxis III - 8 Credits --> Modulnote 1.5
		studienplan.findUnitByName("Projektarbeit III").setNote(1.5);
	}
	
	
	/**
	 * Bachelornote berechnen und auf Studienabschluss prüfen
	 * bestanden
	 */
	@Test
	public void testStudiumAbgeschlossenBestanden() {
		Student student = new Student(1234, "vorname", "nachname", new Date(), "geburtsort", "heimadresse", 1.5);
		Studienplan studienplan = student.getStudienplan();
		
		// Alle Units und Module mit Noten bzw. bestanden versehen
		notenEintragen(studienplan);
		
		// Bachelorarbeit (Modul) --> 12 Credits (nicht mitgezählt) 
		studienplan.findUnitByName("Bachelorarbeit").setBestanden(true);
		studienplan.findUnitByName("Wissenschaftliches Arbeiten 3").setBestanden(true);
		
		/*
		 * Summe(Credits): 178
		 * Summe(Modulnoten * Modul-Credits): 394.25
		 */
		
		// Bachelorarbeit (Note) --> 2
		student.setBachelorArbeitNote(2);
		
		/*
		 * Endnote:
		 * (Summe(Modulnoten * Modul-Credits) / Summe(Credits) * 0.8) + (BachelorArbeitNote * 0.2)
		 * = 2.17191
		 */
		
		assertTrue(student.isStudiumAbgeschlossen());
		assertEquals(2.17, student.getBachelornote(), 0.01);
	}
	
	
	/**
	 * Bachelornote berechnen und auf Studienabschluss prüfen
	 * durchgefallen, weil nicht alle Module abgeschlossen wurden
	 */
	@Test
	public void testStudiumAbgeschlossenDurchgefallenModule() {
		Student student = new Student(1234, "vorname", "nachname", new Date(), "geburtsort", "heimadresse", 1.5);
		Studienplan studienplan = student.getStudienplan();
		
		// Alle Units und Module mit Noten bzw. bestanden versehen
		// (außer letztes Modul Bachelorarbeit)
		notenEintragen(studienplan);
		
		// Bachelorarbeit (Note) --> 2
		student.setBachelorArbeitNote(2);
		
		assertFalse(student.isStudiumAbgeschlossen());
	}
	
	
	/**
	 * Bachelornote berechnen und auf Studienabschluss prüfen
	 * durchgefallen, weil die Bachelorarbeit nicht bestanden wurde
	 */
	@Test
	public void testStudiumAbgeschlossenDurchgefallenbachelorarbeit() {
		Student student = new Student(1234, "vorname", "nachname", new Date(), "geburtsort", "heimadresse", 1.5);
		Studienplan studienplan = student.getStudienplan();
		
		// Alle Units und Module mit Noten bzw. bestanden versehen
		// (außer letztes Modul Bachelorarbeit)
		notenEintragen(studienplan);
		
		// Bachelorarbeit (Modul) --> 12 Credits (nicht mitgezählt) 
		studienplan.findUnitByName("Bachelorarbeit").setBestanden(true);
		studienplan.findUnitByName("Wissenschaftliches Arbeiten 3").setBestanden(true);
		
		assertFalse(student.isStudiumAbgeschlossen());
	}
	
	
	/**
	 * Note der Bachelorarbeit eintragen
	 * Normalfall: 2.5, 4.5
	 * Grenzfälle: 1, 4, 4.1 und 5
	 */
	@Test
	public void testBachelorArbeitNote() {
		Student student = new Student(1234, "vorname", "nachname", new Date(), "geburtsort", "heimadresse", 1.5);
		
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
		Student student = new Student(1234, "vorname", "nachname", new Date(), "geburtsort", "heimadresse", 1.5);
		student.setBachelorArbeitNote(5.1);
	}
	
	
	/**
	 * Note der Bachelorarbeit eintragen
	 * kleiner als 1 - Fehler
	 */
	@Test(expected = InvalidParameterException.class)
	public void testBachelorArbeitNoteTooLow() {
		Student student = new Student(1234, "vorname", "nachname", new Date(), "geburtsort", "heimadresse", 1.5);
		student.setBachelorArbeitNote(0.9);
	}
	
}
