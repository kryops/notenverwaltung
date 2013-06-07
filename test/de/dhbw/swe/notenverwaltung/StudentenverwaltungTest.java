package de.dhbw.swe.notenverwaltung;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StudentenverwaltungTest{
	DHBW dhbw = DHBW.getDHBW();
	Studentenverwaltung sv = new Studentenverwaltung();
	private  ByteArrayOutputStream contentOut = new ByteArrayOutputStream();
	private  ByteArrayOutputStream contentErr = new ByteArrayOutputStream();
		
	@Before
	public void initialization(){
	    System.setOut(new PrintStream(contentOut));
	    System.setErr(new PrintStream(contentErr));
	}
	
	@Test
	public void kurseErstmalsEinteilenTest(){
		//Liste von Studenten erzeugen
		Student studentV = new Student(4444, "vornameV", "nachnameV", new Date(), "geburtsortV", "heimadresseV", 1.5);
		Student studentW = new Student(5555, "vornameW", "nachnameW", new Date(), "geburtsortW", "heimadresseW", 1.5);
		Student studentX = new Student(6666, "vornameX", "nachnameX", new Date(), "geburtsortX", "heimadresseX", 1.5);
		Student studentY = new Student(7777, "vornameY", "nachnameY", new Date(), "geburtsortY", "heimadresseY", 1.5);
		List<Student> studentenliste1 = new ArrayList<Student>();
		List<Student> studentenliste2 = new ArrayList<Student>();
		List<Student> studentenliste3 = new ArrayList<Student>();
		List<Student> studentenliste4 = new ArrayList<Student>();
		studentenliste1.add(studentV);
		studentenliste1.add(studentW);
		studentenliste2.add(studentX);
		
		
		//gültige Werte	
		sv.kurseErstmalsEinteilen("KursX", 2013, "AI", "Studiengangsleiter", "Raum", studentenliste1);
		assertEquals("Der Kurs wurde angelegt.\r\n", contentOut.toString());
		
		//doppelter Kursname - Fehler
		contentOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(contentOut));
		sv.kurseErstmalsEinteilen("KursX", 2012, "WI", "Studiengangsleiter", "Raum", studentenliste2);
		assertEquals("Die angegebenen Informationen sind fehlerhaft. Bitte stellen Sie sicher, dass der Kursname noch nicht vergeben wurde. FC: SV062\r\n", contentOut.toString());
		
		//Studentenzahl zu groß - Fehler
		contentOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(contentOut));
		for(int i=1; i<22; i++) {
			Student student = new Student(6666+i, "vorname" + i, "nachname" + i, new Date(), "geburtsort" + i, "heimadresse" + i, 1.5);
			studentenliste3.add(student);
		}
		sv.kurseErstmalsEinteilen("Kurs3", 2012, "WI", "Studiengangsleiter", "Raum", studentenliste3);
		assertEquals("Bitte verringern Sie die Anzahl der Studenten auf maximal 20. FC: SV063\r\n", contentOut.toString());
		
//		//Student in Liste ist nicht immatrikuliert - Fehler
//		contentOut = new ByteArrayOutputStream();
//		System.setOut(new PrintStream(contentOut));
//		sv.studentExmatrikulieren(7777);
//		studentenliste2.add(studentY);
//		sv.kurseErstmalsEinteilen("Kurs2", 2012, "WI", "Studiengangsleiter", "Raum", studentenliste2);
//		assertEquals("Der Student 7777 ist nicht immatrikuliert und kann daher dem Kurs Kurs4 nicht hinzugefügt werden. FC: SV056\r\n", contentOut.toString());
		
		//kein Student in Liste - kein Fehler
		contentOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(contentOut));
		sv.kurseErstmalsEinteilen("Kurs4", 2013, "AI", "Studiengangsleiter", "Raum", studentenliste4);
		assertEquals("Der Kurs wurde angelegt.\r\n", contentOut.toString());
		
	}
	
	@Test
	public void kurseBearbeiten1Test(){
		Student student1 = new Student(2345, "vorname1", "nachname1", new Date(), "geburtsort1", "heimadresse1", 1.5);
		Student student2 = new Student(3456, "vorname2", "nachname2", new Date(), "geburtsort2", "heimadresse2", 1.5);
		Student student3 = new Student(4567, "vorname3", "nachname3", new Date(), "geburtsort3", "heimadresse3", 1.5);
		List<Student> studentenlisteA = new ArrayList<Student>();
		studentenlisteA.add(student1);
		studentenlisteA.add(student2);
		List<Student> studentenlisteB = new ArrayList<Student>();
		studentenlisteB.add(student3);
		
		Kurs aKurs = new Kurs("aKurs", 2012, "AI");
		DHBW.getDHBW().addKurs(aKurs);
		aKurs.addStudent(student1);
		aKurs.addStudent(student2);
		
		//gültige Werte Aktion 1: Hinzufügen der Studentenliste zum Kurs
		sv.kursBearbeiten("aKurs", studentenlisteB, 1);
		assertTrue(3 == aKurs.getStudenten().size());
		//gültige werte Aktion -1: Löschen der Studenten aus Studentenliste
		sv.kursBearbeiten("aKurs", studentenlisteA, -1);
		assertTrue(1 == aKurs.getStudenten().size());
		
		
		//Kursname nicht vergeben  - Fehler
		sv.kursBearbeiten("xxx", studentenlisteA, 1);
		assertEquals("Zu dem angegebenen Kursnamen wurde kein Kurs gefunden. FC: SV153\r\n", contentOut.toString());
		
		//Aktion nicht gültig  - Fehler
		contentOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(contentOut));
		sv.kursBearbeiten("aKurs", studentenlisteA, 4);
		assertEquals("Die angegebene Aktion ist nicht zulässig. FC: SV149\r\n", contentOut.toString());
		
		//Studentenliste leer - kein Fehler
		List<Student> studentenlisteC = new ArrayList<Student>();
		int size = aKurs.getStudenten().size();
		
		sv.kursBearbeiten("aKurs", studentenlisteC, 1);
		assertTrue(size == aKurs.getStudenten().size());
		
		size = aKurs.getStudenten().size();
		sv.kursBearbeiten("aKurs", studentenlisteC, -1);
		assertTrue(size == aKurs.getStudenten().size());
		
		//Studentenliste mehr als 20 - Fehler
		List<Student> studentenlisteD = new ArrayList<Student>();
		for(int i=1; i<22; i++) {
			Student student = new Student(7777+i, "vorname" + i, "nachname" + i, new Date(), "geburtsort" + i, "heimadresse" + i, 1.5);
			studentenlisteD.add(student);
		}
		contentOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(contentOut));
		sv.kursBearbeiten("aKurs", studentenlisteD, 1);
		assertEquals("Bitte verringern Sie die Anzahl der hinzuzufügenden Studenten, so dass" + 
				" die maximale Kursgröße (20Teilnehmer) nicht überschritten wird. FC: SV132\r\n", contentOut.toString());
		
		//Studenten hinzufügen, Kursgröße überschreiten - Fehler
		List<Student> studentenlisteE = new ArrayList<Student>();
		for(int i=1; i<21; i++) {
			Student student = new Student(8888+i, "vorname" + i, "nachname" + i, new Date(), "geburtsort" + i, "heimadresse" + i, 1.5);
			studentenlisteE.add(student);
		}
		contentOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(contentOut));
		sv.kursBearbeiten("aKurs", studentenlisteE, 1);
		assertEquals("Bitte verringern Sie die Anzahl der hinzuzufügenden Studenten, so dass" + 
				" die maximale Kursgröße (20Teilnehmer) nicht überschritten wird. FC: SV132\r\n", contentOut.toString());
		
		//Studenten hinzufügen, Kursgröße genau 20 - kein Fehler
				List<Student> studentenlisteF = new ArrayList<Student>();
				for(int i=1; i<20; i++) {
					Student student = new Student(8888+i, "vorname" + i, "nachname" + i, new Date(), "geburtsort" + i, "heimadresse" + i, 1.5);
					studentenlisteF.add(student);
				}
				contentOut = new ByteArrayOutputStream();
				System.setOut(new PrintStream(contentOut));
				sv.kursBearbeiten("aKurs", studentenlisteF, 1);
				assertTrue(20 == aKurs.getStudenten().size());
			
		//Studenten löschen, die nicht im Kurs sind - kein Fehler
				sv.kursBearbeiten("aKurs", studentenlisteA, 1);
				assertTrue(20 == aKurs.getStudenten().size());
		
				
//				//Student in Liste ist nicht immatrikuliert - Fehler
//				contentOut = new ByteArrayOutputStream();
//				System.setOut(new PrintStream(contentOut));
//				sv.studentExmatrikulieren(7777);
//				studentenliste2.add(studentY);
//				sv.kurseErstmalsEinteilen("Kurs2", 2012, "WI", "Studiengangsleiter", "Raum", studentenliste2);
//				assertEquals("Der Student 7777 ist nicht immatrikuliert und kann daher dem Kurs Kurs4 nicht hinzugefügt werden. FC: SV056\r\n", contentOut.toString());
			
		
		
		
	}
	
	@Test
	public void kurseBearbeiten2Test(){
		
		
	}
	
	@Test
	public void studentAnlegenTest(){
		
		
	}
	
	@Test
	public void studentBearbeitenTest(){
		
		
	}
	
	@Test
	public void studentAnzeigenTest(){
		
		
	}
	
	@Test
	public void studentExmatrikulierenTest(){
		
		
	}
	
	@Test
	public void zusatzStudentenDatenTest(){
		
		
	}
	
	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}

}
