package de.dhbw.swe.notenverwaltung;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

/**
 * Tests der Klasse DHBW
 * 
 * @author Michael Strobel
 *
 */
public class DHBWTest {
	
	/**
	 * Singleton-Funktionalität und Konstruktor testen
	 */
	@Test
	public void testConstructorSingleton() {
		DHBW dhbw = DHBW.getDHBW();
		assertSame(DHBW.getDHBW(), dhbw);
	}
	
	
	/**
	 * Kurs-Liste testen
	 * Kurse hinzufügen und entfernen
	 */
	@Test
	public void testKursList() {
		DHBW dhbw = DHBW.getDHBW();
		
		// Kurse zurücksetzen (Singleton)
		dhbw.setKurse(new ArrayList<Kurs>());
		
		Kurs kurs1 = new Kurs("name1", 2011, "studiengang1");
		Kurs kurs2 = new Kurs("name2", 2011, "studiengang2");
		Kurs kurs3 = new Kurs("name3", 2011, "studiengang3");
		
		// Kurse hinzufügen
		dhbw.addKurs(kurs1);
		
		for(Kurs k: dhbw.getKurse()) {
			System.out.println(k.getKursname());
		}
		
		
		assertEquals(1, dhbw.getKurse().size());
		assertEquals(kurs1, dhbw.getKurse().get(0));
		
		assertEquals(dhbw, dhbw.getKurse().get(0).getDhbw());
		
		dhbw.addKurs(kurs2);
		assertEquals(2, dhbw.getKurse().size());
		assertTrue(dhbw.getKurse().contains(kurs1));
		assertTrue(dhbw.getKurse().contains(kurs2));
		
		// Kurs entfernen (enthalten)
		dhbw.removeKurs(kurs1);
		assertEquals(1, dhbw.getKurse().size());
		assertEquals(kurs2, dhbw.getKurse().get(0));
		
		// Kurs entfernen (nicht enthalten)
		dhbw.removeKurs(kurs3);
		assertEquals(1, dhbw.getKurse().size());
		assertEquals(kurs2, dhbw.getKurse().get(0));
	}
	
	
	/**
	 * Student anhand der Matrikelnummer finden
	 * gesuchter Student existiert
	 */
	@Test
	public void testFindStudentByMatrikelNummerExists() {
		DHBW dhbw = DHBW.getDHBW();
		
		// Kurse zurücksetzen (Singleton)
		dhbw.setKurse(new ArrayList<Kurs>());
		
		Kurs kurs1 = new Kurs("name1", 2011, "studiengang1");
		Kurs kurs2 = new Kurs("name2", 2011, "studiengang2");
		
		Student student1 = new Student(1, "vorname1", "nachname1", new Date(), "geburtsort1");
		Student student2 = new Student(2, "vorname2", "nachname2", new Date(), "geburtsort2");
		Student student3 = new Student(3, "vorname3", "nachname3", new Date(), "geburtsort3");
		
		kurs1.addStudent(student1);
		kurs1.addStudent(student2);
		kurs2.addStudent(student3);
		
		dhbw.addKurs(kurs1);
		dhbw.addKurs(kurs2);
		
		assertEquals(student1, dhbw.findStudentByMatrikelnummer(1));
		assertEquals(student2, dhbw.findStudentByMatrikelnummer(2));
		assertEquals(student3, dhbw.findStudentByMatrikelnummer(3));
	}
	
	
	/**
	 * Student anhand der Matrikelnummer finden
	 * gesuchter Student existiert nicht
	 */
	@Test
	public void testFindStudentByMatrikelNummerExistsNot() {
		DHBW dhbw = DHBW.getDHBW();
		
		// Kurse zurücksetzen (Singleton)
		dhbw.setKurse(new ArrayList<Kurs>());
		
		Kurs kurs1 = new Kurs("name1", 2011, "studiengang1");
		Kurs kurs2 = new Kurs("name2", 2011, "studiengang2");
		
		Student student1 = new Student(1, "vorname1", "nachname1", new Date(), "geburtsort1");
		Student student2 = new Student(2, "vorname2", "nachname2", new Date(), "geburtsort2");
		Student student3 = new Student(3, "vorname3", "nachname3", new Date(), "geburtsort3");
		
		kurs1.addStudent(student1);
		kurs1.addStudent(student2);
		kurs2.addStudent(student3);
		
		dhbw.addKurs(kurs1);
		dhbw.addKurs(kurs2);
		
		assertEquals(null , dhbw.findStudentByMatrikelnummer(4));
	}
	
	
	/**
	 * Kurs anhand des Kursnamens finden
	 * gesuchter Kurs existiert
	 */
	@Test
	public void testFindKursByNameExists() {
		DHBW dhbw = DHBW.getDHBW();
		
		// Kurse zurücksetzen (Singleton)
		dhbw.setKurse(new ArrayList<Kurs>());
		
		Kurs kurs1 = new Kurs("name1", 2011, "studiengang1");
		Kurs kurs2 = new Kurs("name2", 2011, "studiengang2");
		
		dhbw.addKurs(kurs1);
		dhbw.addKurs(kurs2);
		
		assertEquals(kurs1, dhbw.findKursByName("name1"));
		assertEquals(kurs2, dhbw.findKursByName("name2"));
	}
	
	
	/**
	 * Kurs anhand des Kursnamens finden
	 * gesuchter Kurs existiert nicht
	 */
	@Test
	public void testFindKursByNameExistsNot() {
		DHBW dhbw = DHBW.getDHBW();
		
		// Kurse zurücksetzen (Singleton)
		dhbw.setKurse(new ArrayList<Kurs>());
		
		Kurs kurs1 = new Kurs("name1", 2011, "studiengang1");
		Kurs kurs2 = new Kurs("name2", 2011, "studiengang2");
		
		dhbw.addKurs(kurs1);
		dhbw.addKurs(kurs2);
		
		assertEquals(null, dhbw.findKursByName("name3"));
	}
	
}
