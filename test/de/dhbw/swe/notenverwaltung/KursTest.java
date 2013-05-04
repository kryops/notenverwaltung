package de.dhbw.swe.notenverwaltung;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

/**
 * Tests der Klasse Kurs
 * 
 * @author Michael Strobel
 *
 */
public class KursTest {
	
	/**
	 * Konstruktor testen
	 */
	@Test
	public void testConstructor() {
		Kurs kurs = new Kurs("name", 2011, "studiengang");
		
		assertEquals("name", kurs.getKursname());
		assertEquals(2011, kurs.getJahrgang());
		assertEquals("studiengang", kurs.getStudiengang());
		assertEquals(new ArrayList<Student>(), kurs.getStudenten());
	}
	
	
	/**
	 * Studenten-Liste testen
	 * Studenten hinzufügen und entfernen
	 */
	@Test
	public void testStudentList() {
		Kurs kurs = new Kurs("name", 2011, "studiengang");
		
		// Studenten hinzufügen
		Student student1 = new Student(1, "vorname1", "nachname1", new Date(), "geburtsort1");
		Student student2 = new Student(2, "vorname2", "nachname2", new Date(), "geburtsort2");
		Student student3 = new Student(3, "vorname3", "nachname3", new Date(), "geburtsort3");
		
		kurs.addStudent(student1);
		assertEquals(1, kurs.getStudenten().size());
		assertEquals(student1, kurs.getStudenten().get(0));
		
		assertEquals(kurs, kurs.getStudenten().get(0).getKurs());
		
		kurs.addStudent(student2);
		assertEquals(2, kurs.getStudenten().size());
		assertTrue(kurs.getStudenten().contains(student1));
		assertTrue(kurs.getStudenten().contains(student2));
		
		// enthaltenen Studenten entfernen
		kurs.removeStudent(student1);
		assertEquals(1, kurs.getStudenten().size());
		assertEquals(student2, kurs.getStudenten().get(0));
		
		// nicht enthaltenen Studenten entfernen
		kurs.removeStudent(student3);
		assertEquals(1, kurs.getStudenten().size());
		assertEquals(student2, kurs.getStudenten().get(0));
	}
}
