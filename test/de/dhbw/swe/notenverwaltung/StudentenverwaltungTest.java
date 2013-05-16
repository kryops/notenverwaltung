package de.dhbw.swe.notenverwaltung;

import static org.junit.Assert.*;

import org.junit.Test;

public class StudentenverwaltungTest extends TestObjekte{
	DHBW dhbw = DHBW.getDHBW();
	Studentenverwaltung sv = new Studentenverwaltung();
	
	@Test
	public void testKurseErstmalsEinteilen(){
		sv.kurseErstmalsEinteilen("kursname1", 2010, "AI", "LLL", "6", studentenAnlegen(5, "zz"));
		
		assertEquals("kursname1", dhbw.findKursByName("kursname1"));
		
		
		
		
	}

}
