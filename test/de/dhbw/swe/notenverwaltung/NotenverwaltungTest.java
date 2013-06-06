package de.dhbw.swe.notenverwaltung;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class NotenverwaltungTest extends TestObjekte{
	Notenverwaltung notenverwaltung = new Notenverwaltung();
	
	@Before
	public void testObjekteAnlegen(){
		kurseAnlegen();
	}
	
	
	
	
	@Test
	public void notenNeuEintragenTest() {
		Date geburtsdatum = new Date();
		Student student1 = new Student(1, "micky", "mouse", geburtsdatum, "entenhausen", "heimadresse", 1.5);
		Kurs kurs1 = new Kurs("kurs1", 2011, "Angewandte Informatik");
		kurs1.addStudent(student1);
		
		//sollte funktionieren
		notenverwaltung.notenEintragen(1,"Statistik", 4.0);
		assertEquals(4.0, student1.getStudienplan().findUnitByName("Statistik").getNote(), 0.01);
		
		//sollte bei allein weiteren Fehlschlagen
		
	}
	
//	@Test
//	public void notenBearbeitenTest() {
//		Date geburtsdatum = new Date();
//		Student student91011 = new Student(91011, "vorname", "nachname", geburtsdatum, "geburtsort", "heimadresse", 1.5);
//		
//		//sollte funktionieren
//		notenverwaltung.notenEintragen(91011,"Compilerbauwerkzeuge", 3.0);
//		notenverwaltung.notenEintragen(91011, "Compilerbauwerkzeuge", 3.5);
//		assertEquals(3.5, student91011.getStudienplan().findUnitByName("Compilerbauwerkzeuge").getNote(), 0.01);
//		
//		//sollte bei allein weiteren Fehlschlagen
//		
//		
//	}
	
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
	
	
	
	

}
