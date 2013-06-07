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
	
	
	/**
	 * Vorbereitung, die für alle Testfälle getroffen wird:
	 * Die Konsolenausgabe wird abgefangen und mit den erwarteten Ausgaben verglichen.
	 * 
	 * @author Hanne Nobis
	 */
	@Before
	public void initialization(){
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}
	
	
	/**
	 * kontrolliert die Funktionsweise der Methode notenEintragen
	 * und die Ausgaben
	 * 
	 * @author Hanne Nobis
	 */
	@Test
	public void notenEintragenTest() {
		Date geburtsdatum = new Date();
		Student student1 = new Student(1111, "Micky", "Mouse", geburtsdatum, "Entenhausen", "heimadresse", 1.5);
		Kurs kurs1 = new Kurs("kurs1", 2011, "Angewandte Informatik");
		DHBW.getDHBW().addKurs(kurs1);
		kurs1.addStudent(student1);
		
	//TrueTrueTrue -> TTT (bezieht sich auf die Richtigkeit der Eingabewerte)
		notenverwaltung.notenEintragen(1111,"Statistik", 4.0);
		assertEquals(4.0, student1.getStudienplan().findUnitByName("Statistik").getNote(), 0.01);
		notenverwaltung.notenEintragen(1111,"Statistik", 3.5);
		assertEquals(3.5, student1.getStudienplan().findUnitByName("Statistik").getNote(), 0.01);
	//TrueTrueFalse
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		notenverwaltung.notenEintragen(1111,"Statistik", 5.1);
		assertEquals("Die Noten liegen nicht im gültigen Bereich. FC: NV046\r\nBitte versuchen Sie es erneut.\r\n", 
				outContent.toString());
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
		assertEquals("Der Student konnte im System nicht gefunden werden. Möglicherweise ist die Matrikelnummer "+
				"nicht korrekt. FC: NV032\r\n", outContent.toString());
	//FTF
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		notenverwaltung.notenEintragen(99999999,"Statistik", 5.1);
		assertEquals("Der Student konnte im System nicht gefunden werden. Möglicherweise ist die Matrikelnummer "+
				"nicht korrekt. FC: NV032\r\n", outContent.toString());
	//FFT
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		notenverwaltung.notenEintragen(99999999,"XYZ", 4.0);
		assertEquals("Der Student konnte im System nicht gefunden werden. Möglicherweise ist die Matrikelnummer "+
				"nicht korrekt. FC: NV032\r\n", outContent.toString());
	//FFF
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		notenverwaltung.notenEintragen(99999999,"XYZ", 6.7);
		assertEquals("Der Student konnte im System nicht gefunden werden. Möglicherweise ist die Matrikelnummer "+
				"nicht korrekt. FC: NV032\r\n", outContent.toString());
		
	}

	
	/**
	 * 
	 * 
	 * @author Hanne Nobis
	 */
//TODO bestanden/nicht bestanden
	@Test
	public void notenAbfragen1Test(){
		//Kurs mit Noten versehen
		Date geburtsdatumA = new Date();
		Date geburtsdatumB = new Date();
		Date geburtsdatumC = new Date();
		Student studentA = new Student(1112, "Tick", "Duck", geburtsdatumA, "Entenhausen", "heimadresse", 3.6);
		Student studentB = new Student(1113, "Trick", "Duck", geburtsdatumB, "Entenhausen", "heimadresse", 3.7);
		Student studentC = new Student(1114, "Track", "Duck", geburtsdatumC, "Entenhausen", "heimadresse", 3.8);
		
		Kurs kursVoll = new Kurs("kursvoll", 2011, "WI");
		kursVoll.setRaum("2.034");
		kursVoll.setStudiengangsleiter("XYZ");
		kursVoll.addStudent(studentA);
		kursVoll.addStudent(studentB);
		kursVoll.addStudent(studentC);
		DHBW.getDHBW().addKurs(kursVoll);
		
		notenverwaltung.notenEintragen(1112,"Grundlagen der Datenbanken", 2.0);
		notenverwaltung.notenEintragen(1113,"Grundlagen der Datenbanken", 3.0);
		notenverwaltung.notenEintragen(1114,"Grundlagen der Datenbanken", 4.0);
		
//		unbenotete Prüfungsleistungen	
//		notenverwaltung.notenEintragen(1112,"Compilerbauwerkzeuge", 2.0);
//		notenverwaltung.notenEintragen(1113,"Compilerbauwerkzeuge", 3.0);
//		notenverwaltung.notenEintragen(1114,"Compilerbauwerkzeuge", 4.0);
		
		//TrueTrue -> TT (bezieht sich auf die Richtigkeit der Eingabewerte)
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			notenverwaltung.notenAbfragen("kursvoll", "Grundlagen der Datenbanken");
			assertEquals("Student: Tick Duck, 1112 | Unit: Grundlagen der Datenbanken | Benotung: 2.0\r\n" + 
						"Student: Trick Duck, 1113 | Unit: Grundlagen der Datenbanken | Benotung: 3.0\r\n" + 
						"Student: Track Duck, 1114 | Unit: Grundlagen der Datenbanken | Benotung: 4.0\r\n", outContent.toString());	
		//TF
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			notenverwaltung.notenAbfragen("kursvoll", "XXX");
			assertEquals("Student: Tick Duck, 1112 | Unit konnte im System nicht gefunden werden. FC: NV116\r\n" +
						"Student: Trick Duck, 1113 | Unit konnte im System nicht gefunden werden. FC: NV116\r\n" +
						"Student: Track Duck, 1114 | Unit konnte im System nicht gefunden werden. FC: NV116\r\n", outContent.toString());          
		//FT
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			notenverwaltung.notenAbfragen("kurs", "Grundlagen der Datenbanken");
			assertEquals("Der Kurs konnte im System nicht gefunden werden. FC: NV068\r\n", outContent.toString());	   
		//FF
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			notenverwaltung.notenAbfragen("kurs", "XXX");
			assertEquals("Der Kurs konnte im System nicht gefunden werden. FC: NV068\r\n", outContent.toString());
			
	}
	
	
	/**
	 * 
	 * 
	 * @author Hanne Nobis
	 */
//TODO bestanden/nicht bestanden
	@Test
	public void notenAbfragen2Test(){
		Date geburtsdatumZ = new Date();
		Student studentZ = new Student(2222, "Minnie", "Mouse", geburtsdatumZ, "Entenhausen", "heimadresse", 1.0);
		Kurs kursZ = new Kurs("kursZ", 2012, "AI");
		DHBW.getDHBW().addKurs(kursZ);
		kursZ.addStudent(studentZ);
		notenverwaltung.notenEintragen(2222,"Digitaltechnik", 2.9);
		
		//TrueTrue -> TT (bezieht sich auf die Richtigkeit der Eingabewerte)
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			notenverwaltung.notenAbfragen(2222, "Digitaltechnik");
			assertEquals("Student: Minnie Mouse, 2222 | Unit: Digitaltechnik | Benotung: 2.9\r\n", outContent.toString());
		//TF
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			notenverwaltung.notenAbfragen(2222, "jdj");
			assertEquals("Student: Minnie Mouse, 2222 | Unit konnte im System nicht gefunden werden. FC: NV116\r\n", outContent.toString());
		//FT
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			notenverwaltung.notenAbfragen(0, "Digitaltechnik");
			assertEquals("Student konnte im System nicht gefunden werden. FC: NV097\r\n", outContent.toString());
		//FF
			outContent = new ByteArrayOutputStream();
			System.setOut(new PrintStream(outContent));
			notenverwaltung.notenAbfragen(101010101, "XX");
			assertEquals("Student konnte im System nicht gefunden werden. FC: NV097\r\n", outContent.toString());
			
	}
	
	
	/**
	 * Berechnung der Bachelornote
	 * haben Studenten das Studium überhaupt abgeschlossen?
	 * Sind alle Noten eingetragen?
	 * ...
	 * 
	 * @author Hanne Nobis
	 */
	@Test
	public void bachelorNotenBerechnenTest(){
	//Kurs mit Noten versehen
		Date Ageburtsdatum = new Date();
		Date Bgeburtsdatum = new Date();
		Date Cgeburtsdatum = new Date();
		Date Dgeburtsdatum = new Date();
		Date Egeburtsdatum = new Date();
		
		Student Astudent = new Student(3333, "Homer", "Simpson", Ageburtsdatum, "Entenhausen", "heimadresse", 3.0);
		Student Bstudent = new Student(3334, "Marge", "Simpson", Bgeburtsdatum, "Entenhausen", "heimadresse", 2.0);
		Student Cstudent = new Student(3335, "Bart", "Simpson", Cgeburtsdatum, "Entenhausen", "heimadresse", 3.0);
		Student Dstudent = new Student(3336, "Lisa", "Simpson", Dgeburtsdatum, "Entenhausen", "heimadresse", 1.0);
		Student Estudent = new Student(3337, "Meggie", "Simpson", Egeburtsdatum, "Entenhausen", "heimadresse", 2.0);
				
		Kurs kursSimpson = new Kurs("kursSimpson", 2011, "WI");
		kursSimpson.setRaum("2.034");
		kursSimpson.setStudiengangsleiter("XYZ");	
		kursSimpson.addStudent(Astudent);
		kursSimpson.addStudent(Bstudent);
		kursSimpson.addStudent(Cstudent);
		kursSimpson.addStudent(Dstudent);
		kursSimpson.addStudent(Estudent);
		DHBW.getDHBW().addKurs(kursSimpson);
		
		Studienplan studiA = Astudent.getStudienplan();		
		alleNotenEintragen(studiA);	
		Astudent.setBachelorArbeitNote(2);	
		Studienplan studiB = Bstudent.getStudienplan();		
		alleNotenEintragen(studiB);	
		Bstudent.setBachelorArbeitNote(2);
		Studienplan studiC = Cstudent.getStudienplan();		
		alleNotenEintragen(studiC);	
		Cstudent.setBachelorArbeitNote(2);
		Studienplan studiD = Dstudent.getStudienplan();		
		alleNotenEintragen(studiD);	
		Dstudent.setBachelorArbeitNote(2);
		
	//ungültiger Kurs (Student E fehlt)
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		notenverwaltung.bachelornotenBerechnen(kursSimpson);
		assertEquals("Student: Homer Simpson, 3333 | Bachelornote: 2.2\r\n" +
				"Student: Marge Simpson, 3334 | Bachelornote: 2.2\r\n" +
				"Student: Bart Simpson, 3335 | Bachelornote: 2.2\r\n" +
				"Student: Lisa Simpson, 3336 | Bachelornote: 2.2\r\n" +
				"Bachelornote wurde nicht berechnet. FC: NV155\r\n", outContent.toString());
		
	//fehlenden Studenten ergänzen	
		Studienplan studiE = Estudent.getStudienplan();		
		alleNotenEintragen(studiE);	
		Estudent.setBachelorArbeitNote(2);
		
	//gültiger Kurs
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		notenverwaltung.bachelornotenBerechnen(kursSimpson);
		assertEquals("Student: Homer Simpson, 3333 | Bachelornote: 2.2\r\n" +
				"Student: Marge Simpson, 3334 | Bachelornote: 2.2\r\n" +
				"Student: Bart Simpson, 3335 | Bachelornote: 2.2\r\n" +
				"Student: Lisa Simpson, 3336 | Bachelornote: 2.2\r\n" +
				"Student: Meggie Simpson, 3337 | Bachelornote: 2.2\r\n", outContent.toString());

	}
	
	
	/**
	 * 
	 * @param studienplan
	 * @author Michael Strobel
	 */
	private void alleNotenEintragen(Studienplan studienplan) {
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
		studienplan.findUnitByName("Wissenschaftliches Arbeiten 3").setBestanden(true);		
		studienplan.findUnitByName("Bachelorarbeit").setBestanden(true);
		
	}
	
	
	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}
	
}
