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
