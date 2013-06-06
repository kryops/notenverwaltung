package de.dhbw.swe.notenverwaltung;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class NotenverwaltungTest extends TestObjekte{
	Notenverwaltung notenverwaltung = new Notenverwaltung();
	private  ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private  ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	
	@Before
	public void initialization(){
		kurseAnlegen();
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}
	
	
	
	@Test
	public void notenNeuEintragenTest() {
		Date geburtsdatum = new Date();
		Student student1 = new Student(1111, "micky", "mouse", geburtsdatum, "entenhausen", "heimadresse", 1.5);
		Kurs kurs1 = new Kurs("kurs1", 2011, "Angewandte Informatik");
		DHBW.getDHBW().addKurs(kurs1);
		kurs1.addStudent(student1);
		
		//sollte funktionieren
		notenverwaltung.notenEintragen(1111,"Statistik", 4.0);
		assertEquals(4.0, student1.getStudienplan().findUnitByName("Statistik").getNote(), 0.01);
		
		
		//sollte bei allen weiteren Fehlschlagen
		//FTT
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		notenverwaltung.notenEintragen(99999999,"Statistik", 4.0);
		assertEquals("Der Student konnte im System nicht gefunden werden. Möglicherweise ist die Matrikelnummer nicht korrekt. FC: NV032\r\n", outContent.toString());
		//TFT
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		notenverwaltung.notenEintragen(1111,"XYZ", 4.0);
		assertEquals("Die Unit konnte im System nicht gefunden werden. FC: NV038\r\n", outContent.toString());
		//TTF
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		notenverwaltung.notenEintragen(1111,"Statistik", 5.1);
		assertEquals("Die Noten liegen nicht im gültigen Bereich. FC: NV046\r\nBitte versuchen Sie es erneut.\r\n", outContent.toString());
		
		
		
		
		
	}
	
	@Test
	public void notenBearbeitenTest() {
		Date geburtsdatum = new Date();
		Student student91011 = new Student(91011, "vorname", "nachname", geburtsdatum, "geburtsort", "heimadresse", 1.5);
		Kurs kurs2 = new Kurs("kurs2", 2011, "Angewandte Informatik");
		DHBW.getDHBW().addKurs(kurs2);
		kurs2.addStudent(student91011);
		
		//sollte funktionieren
		notenverwaltung.notenEintragen(91011,"Compilerbauwerkzeuge", 3.0);
		notenverwaltung.notenEintragen(91011, "Compilerbauwerkzeuge", 3.5);
		assertEquals(3.5, student91011.getStudienplan().findUnitByName("Compilerbauwerkzeuge").getNote(), 0.01);
		
		//sollte bei allein weiteren Fehlschlagen
		
		
	}
	
	@Test
	public void notenAbfragen1Test(){
		//Input kursname, Unitname (ganzer Kurs)
		
		
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
