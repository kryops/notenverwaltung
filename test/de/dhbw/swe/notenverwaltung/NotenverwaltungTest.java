package de.dhbw.swe.notenverwaltung;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NotenverwaltungTest{
	Notenverwaltung notenverwaltung = new Notenverwaltung();
	private  ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private  ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	
	@Before
	public void initialization(){
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}
	
	
	@Test
	public void notenEintragenTest() {
		Date geburtsdatum = new Date();
		Student student1 = new Student(1111, "micky", "mouse", geburtsdatum, "entenhausen", "heimadresse", 1.5);
		Kurs kurs1 = new Kurs("kurs1", 2011, "Angewandte Informatik");
		DHBW.getDHBW().addKurs(kurs1);
		kurs1.addStudent(student1);
		
		//TrueTrueTrue -> TTT (bezieht sich auf die Richtigkeit der Eingabewerte)
		notenverwaltung.notenEintragen(1111,"Statistik", 4.0);
		assertEquals(4.0, student1.getStudienplan().findUnitByName("Statistik").getNote(), 0.01);
		notenverwaltung.notenEintragen(1111,"Statistik", 3.5);
		assertEquals(3.5, student1.getStudienplan().findUnitByName("Statistik").getNote(), 0.01);
		
		//sollte bei allen weiteren Fehlschlagen
		//TTF
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			notenverwaltung.notenEintragen(1111,"Statistik", 5.1);
			assertEquals("Die Noten liegen nicht im gültigen Bereich. FC: NV046\r\nBitte versuchen Sie es erneut.\r\n", outContent.toString());
		//TFT		
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			notenverwaltung.notenEintragen(1111,"XYZ", 4.0);
			assertEquals("Die Unit konnte im System nicht gefunden werden. FC: NV038\r\n", outContent.toString());
		//TFF
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			notenverwaltung.notenEintragen(1111,"XYZ", 0.1);
			assertEquals("Die Unit konnte im System nicht gefunden werden. FC: NV038\r\n", outContent.toString());
		//FTT
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			notenverwaltung.notenEintragen(99999999,"Statistik", 4.0);
			assertEquals("Der Student konnte im System nicht gefunden werden. Möglicherweise ist die Matrikelnummer nicht korrekt. FC: NV032\r\n", outContent.toString());
		//FTF
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			notenverwaltung.notenEintragen(99999999,"Statistik", 5.1);
			assertEquals("Der Student konnte im System nicht gefunden werden. Möglicherweise ist die Matrikelnummer nicht korrekt. FC: NV032\r\n", outContent.toString());
		//FFT
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			notenverwaltung.notenEintragen(99999999,"XYZ", 4.0);
			assertEquals("Der Student konnte im System nicht gefunden werden. Möglicherweise ist die Matrikelnummer nicht korrekt. FC: NV032\r\n", outContent.toString());
		//FFF
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			notenverwaltung.notenEintragen(99999999,"XYZ", 6.7);
			assertEquals("Der Student konnte im System nicht gefunden werden. Möglicherweise ist die Matrikelnummer nicht korrekt. FC: NV032\r\n", outContent.toString());
		
	}
	
	
	@Test
	public void notenAbfragen1Test(){
		//Input kursname, Unitname (ganzer Kurs)
//		notenAbfragen(1):
//			- gültige Werte
//			- g-ug
//			- ug-g
//			- ug-ug
		//Kurs mit Noten versehen
		Date geburtsdatumA = new Date();
		Date geburtsdatumB = new Date();
		Date geburtsdatumC = new Date();
		Student studentA = new Student(1112, "tick", "duck", geburtsdatumA, "entenhausen", "heimadresse", 3.6);
		Student studentB = new Student(1113, "trick", "duck", geburtsdatumB, "entenhausen", "heimadresse", 3.7);
		Student studentC = new Student(1114, "track", "duck", geburtsdatumC, "entenhausen", "heimadresse", 3.8);
		
		Kurs kursVoll = new Kurs("kursvoll", 2011, "WI");
		kursVoll.setRaum("2.034");
		kursVoll.setStudiengangsleiter("XYZ");
		kursVoll.addStudent(studentA);
		kursVoll.addStudent(studentB);
		kursVoll.addStudent(studentC);
		
		DHBW.getDHBW().addKurs(kursVoll);
		
		notenverwaltung.notenEintragen(1112,"Statistik", 2.0);
		notenverwaltung.notenEintragen(1113,"Statistik", 3.0);
		notenverwaltung.notenEintragen(1114,"Statistik", 4.0);
		
		//TrueTrue -> TT (bezieht sich auf die Richtigkeit der Eingabewerte)
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			notenverwaltung.notenAbfragen("kursvoll", "Datenbanken");
			assertEquals("\r\n", outContent.toString());
		
		
		
		
		
		
		
		
		
			
		
		
		
		
		
		
	}
	
	@Test
	public void notenAbfragen2Test(){
		//Input matrikelnummer, unitname (einzelner Student)
		Date geburtsdatum = new Date();
		Student student = new Student(1234, "vorname", "nachname", geburtsdatum, "geburtsort", "heimadresse", 1.5);
		
		student.getStudienplan().findUnitByName("Statistik").setNote(2.7);
		
		notenverwaltung.notenAbfragen(1234, "Statistik");
		
		
		
	}
	
	
	
	@Test
	public void bachelorNotenBerechnenTest(){
		
		
	}
	
	
	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}
	
	
	
	

}
