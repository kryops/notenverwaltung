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
		
	/**
	 * Vorbereitung, die für alle Testfälle getroffen wird:
	 * Die Konsolenausgabe wird abgefangen und mit den erwarteten Ausgaben verglichen.
	 * 
	 * @author Hanne Nobis
	 */
	@Before
	public void initialization(){
	    System.setOut(new PrintStream(contentOut));
	    System.setErr(new PrintStream(contentErr));
	}
	
	/**
	 * Testet die Funktion kurseErstmalsEinteilen
	 * Studenten erstellen und als Liste hinzufügen
	 * 
	 * @author Hanne Nobis
	 */
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
		assertEquals("Die angegebenen Informationen sind fehlerhaft. Bitte stellen Sie sicher, dass der Kursname " +
				"noch nicht vergeben wurde. FC: SV062\r\n", contentOut.toString());
		
	//Studentenzahl zu groß - Fehler
		contentOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(contentOut));
		for(int i=1; i<22; i++) {
			Student student = new Student(6666+i, "vorname" + i, "nachname" + i, new Date(), "geburtsort" + i, "heimadresse" + i, 1.5);
			studentenliste3.add(student);
		}
		sv.kurseErstmalsEinteilen("Kurs3", 2012, "WI", "Studiengangsleiter", "Raum", studentenliste3);
		assertEquals("Bitte verringern Sie die Anzahl der Studenten auf maximal 20. FC: SV063\r\n", contentOut.toString());
		
	//kein Student in Liste - kein Fehler
		contentOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(contentOut));
		sv.kurseErstmalsEinteilen("Kurs4", 2013, "AI", "Studiengangsleiter", "Raum", studentenliste4);
		assertEquals("Der Kurs wurde angelegt.\r\n", contentOut.toString());
		
	}
	
	/**
	 * Löschen und Hinzufügen von Studenten zu den Kursen
	 * Einhaltung der Kursgrößen wird kon rolliert
	 * Da es sich um eine überladene Funktion handelt, gibt es zwei Testfälle.
	 * Dieser hier testet kursBearbeiten(String kursname, List<Student> studenten, int aktion)
	 * 
	 * @author Hanne Nobis
	 */
	@Test
	public void kurseBearbeiten1Test(){
	//gültige Werte
		Student studentin = new Student(1122, "vorname", "nachname",  new Date(), "geburtsort", "heimadresse", 1.0);
		Kurs tinf = new Kurs("tinf", 2012, "AI");
		DHBW.getDHBW().addKurs(tinf);
		tinf.addStudent(studentin);
		tinf.setStudiengangsleiter("abc");
		tinf.setRaum("111");
		sv.kursBearbeiten("tinf", "defg", "222");
		assertEquals("defg", tinf.getStudiengangsleiter());
		assertEquals("222", tinf.getRaum());
		
	//Kursname falsch - Fehler
		contentOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(contentOut));
		sv.kursBearbeiten("tinf11", "defg", "222");
		assertEquals("Zu dem angegebenen Kursnamen wurde kein Kurs gefunden. FC: SV093\r\n", contentOut.toString());
		
	//Kurs ohne Daten ändern
		contentOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(contentOut));
		sv.kursBearbeiten("tinf", null, null);
		assertEquals("defg", tinf.getStudiengangsleiter());
		assertEquals("222", tinf.getRaum());
		
	}
	
	/**
	 * Löschen und Hinzufügen von Studenten zu den Kursen
	 * Einhaltung der Kursgrößen wird kon rolliert
	 * Da es sich um eine überladene Funktion handelt, gibt es zwei Testfälle.
	 * Dieser hier testet kursBearbeiten(String kursname, String studiengangsleiter, String raum)
	 * 
	 * @author Hanne Nobis
	 */
	@Test
	public void kurseBearbeiten2Test(){
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
		
	}
	
	/**
	 * Anlegen eines Studenten mit verschiedenen Eingaben wird getestet
	 * 
	 * @author Hanne Nobis	
	 */
	@Test
	public void studentAnlegenTest(){
	//gültige Daten
		Date date = new Date();
		Kurs kursXYZ = new Kurs("kursXYZ", 2009, "IT");
		DHBW.getDHBW().addKurs(kursXYZ);		
		sv.studentAnlegen(9876, "Vorname", "Nachname", date, "Geburtsort", "Heimadresse", 2.3, "Email", "Telefonnummer",
				2010, "Abiturort", "Studentenadresse", "Firmenname", "Ausbildungsleiter", "Studiengang", 2011, "kursXYZ");
		Student studentine = dhbw.findStudentByMatrikelnummer(9876);
		assertEquals("Vorname", studentine.getVorname());
		assertEquals("Nachname", studentine.getNachname());
		assertEquals(date, studentine.getGeburtsdatum());
		assertEquals("Geburtsort", studentine.getGeburtsort());
		assertEquals("Heimadresse", studentine.getHeimadresse());
		assertTrue(2.3 == studentine.getAbiturnote());
		assertEquals("Email", studentine.getEmail());
		assertEquals("Telefonnummer", studentine.getTelefonnummer());
		assertTrue(2010 == studentine.getAbiturjahrgang());
		assertEquals("Abiturort", studentine.getAbiturort());
		assertEquals("Studentenadresse", studentine.getStudentenadresse());
		assertEquals("Firmenname", studentine.getFirmenname());
		assertEquals("Ausbildungsleiter", studentine.getAusbildungsleiter());
		assertEquals("Studiengang", studentine.getStudiengang());
		assertTrue(2011 == studentine.getJahrgang());
		
	//Matrikelnummer doppelt - Fehler
		contentOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(contentOut));
		sv.studentAnlegen(9876, "Vorname", "Nachname", date, "Geburtsort", "Heimadresse", 2.3, "Email", "Telefonnummer",
				2010, "Abiturort", "Studentenadresse", "Firmenname", "Ausbildungsleiter", "Studiengang", 2011, "kursXYZ");
		assertEquals("Die Matrikelnummer wurde bereits vergeben. FC: SV208\r\nDer Student konnte nicht immatrikuliert werden, " +
				"da die Daten nicht vollständig sind. FC: SV210\r\n", contentOut.toString());
		
	//notwendige Daten nicht eingetragen
		contentOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(contentOut));
		sv.studentAnlegen(9875, null, null, date, "Geburtsort", "Heimadresse", 2.3, "Email", "Telefonnummer",
				2010, "Abiturort", "Studentenadresse", "Firmenname", "Ausbildungsleiter", "Studiengang", 2011, "kursXYZ");
		assertEquals("Der Student konnte nicht immatrikuliert werden, da die Daten nicht vollständig sind. FC: SV210\r\n", 
				contentOut.toString());
		
	}
	
	/**
	 * Bearbeiten des Studenten, inkl. Bearbeitung ohne etwas zu verändern
	 * 
	 * @author Hanne Nobis
	 */
	@Test
	public void studentBearbeitenTest(){
	//gültige Werte	
		Date date = new Date();
		Kurs kursMNO = new Kurs("kursMNO", 2009, "IT");
		DHBW.getDHBW().addKurs(kursMNO);		
		sv.studentAnlegen(9874, "Vorname", "Nachname", date, "Geburtsort", "Heimadresse", 2.3, "Email", "Telefonnummer",
				2010, "Abiturort", "Studentenadresse", "Firmenname", "Ausbildungsleiter", "Studiengang", 2011, "kursMNO");
		sv.studentBearbeiten(9874, "First name", "Last name", null, null, null, 0, null,null, 0, null, null, null, "Mr. Unknown", null, 0); 
		Student studenMNO = dhbw.findStudentByMatrikelnummer(9874);
		assertEquals("First name", studenMNO.getVorname());
		assertEquals("Last name", studenMNO.getNachname());
		assertEquals(date, studenMNO.getGeburtsdatum());
		assertEquals("Geburtsort", studenMNO.getGeburtsort());
		assertEquals("Heimadresse", studenMNO.getHeimadresse());
		assertTrue(2.3 == studenMNO.getAbiturnote());
		assertEquals("Email", studenMNO.getEmail());
		assertEquals("Telefonnummer", studenMNO.getTelefonnummer());
		assertTrue(2010 == studenMNO.getAbiturjahrgang());
		assertEquals("Abiturort", studenMNO.getAbiturort());
		assertEquals("Studentenadresse", studenMNO.getStudentenadresse());
		assertEquals("Firmenname", studenMNO.getFirmenname());
		assertEquals("Mr. Unknown", studenMNO.getAusbildungsleiter());
		assertEquals("Studiengang", studenMNO.getStudiengang());
		assertTrue(2011 == studenMNO.getJahrgang());
		
	//Matrikelnummer ungültig
		contentOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(contentOut));
		sv.studentBearbeiten(9873, "First name", "Last name", null, null, null, 0, null,null, 0, null, null, null, null, null, 0); 
		assertEquals("Entweder der angegebenen Matrikelnummer konnte kein Student zugeordnet werden oder der Student ist nicht " + 
				"immatrikuliert. FC: SV287\r\n", contentOut.toString());
			
	//keine Veränderung vornehmen, alles null oder 0
		sv.studentBearbeiten(9874, null, null, null, null, null, 0, null,null, 0, null, null, null, null, null, 0); 
		assertEquals("First name", studenMNO.getVorname());
		assertEquals("Last name", studenMNO.getNachname());
		assertEquals(date, studenMNO.getGeburtsdatum());
		assertEquals("Geburtsort", studenMNO.getGeburtsort());
		assertEquals("Heimadresse", studenMNO.getHeimadresse());
		assertTrue(2.3 == studenMNO.getAbiturnote());
		assertEquals("Email", studenMNO.getEmail());
		assertEquals("Telefonnummer", studenMNO.getTelefonnummer());
		assertTrue(2010 == studenMNO.getAbiturjahrgang());
		assertEquals("Abiturort", studenMNO.getAbiturort());
		assertEquals("Studentenadresse", studenMNO.getStudentenadresse());
		assertEquals("Firmenname", studenMNO.getFirmenname());
		assertEquals("Mr. Unknown", studenMNO.getAusbildungsleiter());
		assertEquals("Studiengang", studenMNO.getStudiengang());
		assertTrue(2011 == studenMNO.getJahrgang());
				
	//Student exmatrikuliert
		Student studiPQR = new Student(9872, "vor", "nach", new Date(), "geburt", "heimat", 2.5);
		Kurs kursiPQR = new Kurs("kursiPQR", 2012, "AI");
		DHBW.getDHBW().addKurs(kursiPQR);
		kursiPQR.addStudent(studiPQR);
		sv.studentExmatrikulieren(9872);
		contentOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(contentOut));
		sv.studentBearbeiten(9872, "neuer vorname", null, null, null, null, 0, null,null, 0, null, null, null, null, null, 0);
		assertEquals("Entweder der angegebenen Matrikelnummer konnte kein Student zugeordnet werden oder der Student ist nicht " + 
				"immatrikuliert. FC: SV287\r\n", contentOut.toString());
					
	}
	
	
	/**
	 * Wird der Student bei "Anzeigen" richtig ausgegeben?
	 * 
	 * @auth
	 */
//TODO Problem mit Datum beheben!
//	@Test
//	public void studentAnzeigenTest(){
//		Student studi = new Student(667788, "vor", "nach", new Date(), "geburt", "heimat", 2.5);
//		
//		Kurs kursi = new Kurs("kursi", 2012, "AI");
//		DHBW.getDHBW().addKurs(kursi);
//		kursi.addStudent(studi);
//		
//		sv.studentAnzeigen(667788);
//		assertEquals("Matr.-Nr.: 667788  Name: vor nach\r\n | Studium  Studiengang: null  Jahrgang: 0 immatrikuliert\r\n" +
//					" | Geburtsdaten: geburt Fri Jun 07 12:35:56 CEST 2013\r\n | Kontaktdaten  Email null  Tel. null\r\n" +
//					" | Heimatadresse: heimat\r\n | Studentenadresse: null\r\n" +
//					" | Abitur  Abschlussnote: 2.5 null  Jahrgang: 0\r\n | Unternehmen  Firma: null  Ausbildungsleiter: null", 
//					contentOut.toString());
//		
//		contentOut = new ByteArrayOutputStream();
//		System.setOut(new PrintStream(contentOut));
//		sv.studentAnzeigen(998877);
//		assertEquals("Der angegebenen Matrikelnummer konnte kein Student zugeordnet werden. FC: SV324\r\n", contentOut.toString());
//		
//	}
	
	
	/**
	 * Studenten werden exmatrikuliert, teilweise auch doppelt
	 * Welche Folgen hat das? (keine)
	 * 
	 * @author Hanne Nobis
	 */
	@Test
	public void studentExmatrikulierenTest(){
	//gültige Werte
		Student studi1 = new Student(778899, "vor", "nach", new Date(), "geburt", "heimat", 2.5);
		Kurs kursi1 = new Kurs("kursi1", 2012, "AI");
		DHBW.getDHBW().addKurs(kursi1);
		kursi1.addStudent(studi1);
		sv.studentExmatrikulieren(778899);
		assertFalse(studi1.isImmatrikuliert());
		
	//matrikelnummer ungültig
		contentOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(contentOut));
		sv.studentExmatrikulieren(778800);
		assertEquals("Der Student 778800 konnte nicht gefunden werden. Er wurde möglicherweise bereits exmatrikuliert. FC: SV362\r\n",
				contentOut.toString());
		
	//Student bereits exmatrikuliert
		contentOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(contentOut));
		sv.studentExmatrikulieren(778899);
		assertFalse(studi1.isImmatrikuliert());
		assertEquals("Der Student 778899 konnte nicht gefunden werden. Er wurde möglicherweise bereits exmatrikuliert. FC: SV362\r\n", 
				contentOut.toString());
		
	}
	
	
	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}

}
