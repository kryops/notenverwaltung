package de.dhbw.swe.notenverwaltung;

public class StudienplanGenerator {
	
	public static Studienplan createStudienplanAI() {
		
		Modul modul;
		
		Studienplan studienplan = new Studienplan("Angewandte Informatik", "B. Sc.");
		
		// Mathematik I
		modul = new Modul("Mathematik I");
		modul.setSemester(1);
		modul.setCredits(8);
		modul.setPraesenz(96);
		modul.setEigenstudium(144);
		
		modul.addUnit(new Unit("Analysis", "Klausur", 50));
		modul.addUnit(new Unit("Lineare Algebra", "Klausur", 50));
		
		studienplan.addModul(modul);
		
		// Theoretische Informatik I
		modul = new Modul("Theoretische Informatik I");
		modul.setSemester(1);
		modul.setCredits(5);
		modul.setPraesenz(60);
		modul.setEigenstudium(90);
		
		modul.addUnit(new Unit("Grundlagen und Logik", "Klausur", 100));
		
		studienplan.addModul(modul);
		
		// Theoretische Informatik II
		modul = new Modul("Theoretische Informatik II");
		modul.setSemester(2);
		modul.setCredits(5);
		modul.setPraesenz(48);
		modul.setEigenstudium(102);
		
		modul.addUnit(new Unit("Algorithmen und Komplexität", "Klausur", 100));
		
		studienplan.addModul(modul);
		
		// Programmieren
		modul = new Modul("Programmieren");
		modul.setSemester(1);
		modul.setCredits(9);
		modul.setPraesenz(96);
		modul.setEigenstudium(174);
		
		modul.addUnit(new Unit("Programmieren 1", "Klausur", 50));
		modul.addUnit(new Unit("Programmieren 2", "Klausur", 50));
		
		studienplan.addModul(modul);
		
		// Schlüsselqualifikationen I
		modul = new Modul("Schlüsselqualifikationen I");
		modul.setSemester(1);
		modul.setCredits(5);
		modul.setPraesenz(84);
		modul.setEigenstudium(66);
		
		modul.addUnit(new Unit("Betriebswirtschaftslehre", "Klausur", 100));
		modul.addUnit(new Unit("Marketing 1", "", 0));
		modul.addUnit(new Unit("Marketing 2", "", 0));
		
		studienplan.addModul(modul);
		
		// Technische Informatik I
		modul = new Modul("Technische Informatik I");
		modul.setSemester(1);
		modul.setCredits(5);
		modul.setPraesenz(48);
		modul.setEigenstudium(102);
		
		modul.addUnit(new Unit("Digitaltechnik", "Klausur", 100));
		
		studienplan.addModul(modul);
		
		// Webengineering I
		modul = new Modul("Webengineering I");
		modul.setSemester(1);
		modul.setCredits(3);
		modul.setPraesenz(48);
		modul.setEigenstudium(42);
		
		modul.addUnit(new Unit("Webengineering I", "Klausur, Programmentwurf", 100));
		modul.addUnit(new Unit("Labor Webengineering I", "", 0));
		
		studienplan.addModul(modul);
		
		// Projekt AI
		modul = new Modul("Projekt AI");
		modul.setSemester(1);
		modul.setCredits(5);
		modul.setPraesenz(84);
		modul.setEigenstudium(66);
		
		modul.addUnit(new Unit("Projektmanagement I", "Klausur", 50));
		modul.addUnit(new Unit("Projektmanagement II", "Konstruktions-Entwurf", 50));
		modul.addUnit(new Unit("Labor AI", "", 0));
		
		studienplan.addModul(modul);
		
		// Schlüsselqualifikationen II
		modul = new Modul("Schlüsselqualifikationen II");
		modul.setSemester(1);
		modul.setCredits(5);
		modul.setPraesenz(84);
		modul.setEigenstudium(66);
		
		modul.addUnit(new Unit("Intercultural Communication 1", "Klausur", 50));
		modul.addUnit(new Unit("Intercultural Communication 2", "Referat", 50));
		modul.addUnit(new Unit("Vortrags-, Lern- und Arbeitstechniken", "", 0));
		modul.addUnit(new Unit("Übung Schlüsselqualifikationen", "", 0));
		
		studienplan.addModul(modul);
		
		// Praxis I
		modul = new Modul("Praxis I");
		modul.setSemester(1);
		modul.setCredits(20);
		modul.setPraesenz(4);
		modul.setEigenstudium(596);
		modul.setBenotet(false);
		
		modul.addUnit(new Unit("Projektarbeit I", "Ablauf- und Reflexionsbericht, Projektarbeit", 100));
		modul.addUnit(new Unit("Wissenschaftliches Arbeiten", "", 0));
		
		studienplan.addModul(modul);
				
		// Mathematik II
		modul = new Modul("Mathematik II");
		modul.setSemester(3);
		modul.setCredits(6);
		modul.setPraesenz(72);
		modul.setEigenstudium(108);
		
		modul.addUnit(new Unit("Angewandte Mathematik", "Klausur", 50));
		modul.addUnit(new Unit("Statistik", "Klausur", 50));
		
		studienplan.addModul(modul);
		
		// Theoretische Informatik III
		modul = new Modul("Theoretische Informatik III");
		modul.setSemester(3);
		modul.setCredits(6);
		modul.setPraesenz(72);
		modul.setEigenstudium(108);
		
		modul.addUnit(new Unit("Compilerbauwerkzeuge", "", 0));
		modul.addUnit(new Unit("Einführung Compilerbau", "", 0));
		modul.addUnit(new Unit("Formale Sprachen und Automaten 1", "Klausur", 100));
		modul.addUnit(new Unit("Formale Sprachen und Automaten 2", "", 0));
		
		studienplan.addModul(modul);
		
		// Software Engineering I
		modul = new Modul("Software Engineering I");
		modul.setSemester(3);
		modul.setCredits(9);
		modul.setPraesenz(96);
		modul.setEigenstudium(174);
		
		modul.addUnit(new Unit("Grundlagen des Software-Engineering", "Klausur, Programmentwurf", 100));
		
		studienplan.addModul(modul);
		
		// Datenbanken I
		modul = new Modul("Datenbanken I");
		modul.setSemester(3);
		modul.setCredits(6);
		modul.setPraesenz(72);
		modul.setEigenstudium(108);
		
		modul.addUnit(new Unit("Grundlagen der Datenbanken", "Klausur", 100));
		
		studienplan.addModul(modul);
		
		// Technische Informatik II
		modul = new Modul("Technische Informatik II");
		modul.setSemester(3);
		modul.setCredits(8);
		modul.setPraesenz(96);
		modul.setEigenstudium(144);
		
		modul.addUnit(new Unit("Betriebssysteme", "Klausur", 50));
		modul.addUnit(new Unit("Rechnerarchitekturen 1", "Klausur", 50));
		modul.addUnit(new Unit("Systemnahe Programmierung 1", "", 0));
		
		studienplan.addModul(modul);
		
		// Kommunikations- und Netztechnik I
		modul = new Modul("Kommunikations- und Netztechnik I");
		modul.setSemester(3);
		modul.setCredits(5);
		modul.setPraesenz(48);
		modul.setEigenstudium(102);
		
		modul.addUnit(new Unit("Netztechnik 1", "Klausur", 100));
		modul.addUnit(new Unit("Labor Netztechnik", "", 0));
		
		studienplan.addModul(modul);
		
		// Techniken der Informatik
		modul = new Modul("Techniken der Informatik");
		modul.setSemester(3);
		modul.setCredits(5);
		modul.setPraesenz(72);
		modul.setEigenstudium(78);
		
		modul.addUnit(new Unit("Compilerbau", "Klausur", 50));
		modul.addUnit(new Unit("Webengineering 2", "Projekt", 50));
		
		studienplan.addModul(modul);
		
		// Wahlmodul Angewandte Informatik (STG Jahr 2)
		modul = new Modul("Wahlmodul Angewandte Informatik (STG Jahr 2)");
		modul.setSemester(4);
		modul.setCredits(5);
		modul.setPraesenz(72);
		modul.setEigenstudium(78);
		
		modul.addUnit(new Unit("Wahlfach 2. Studienjahr", "Klausur", 100));
		
		studienplan.addModul(modul);
		
		// Praxis II
		modul = new Modul("Praxis II");
		modul.setSemester(4);
		modul.setCredits(20);
		modul.setPraesenz(5);
		modul.setEigenstudium(595);
		
		modul.addUnit(new Unit("Mündliche Prüfung", "Mündliche Prüfung", 50));
		modul.addUnit(new Unit("Projektarbeit II", "Projektarbeit", 50));
		modul.addUnit(new Unit("Wissenschaftliches Arbeiten", "", 0));
		
		studienplan.addModul(modul);
		
		// Software Engineering II
		modul = new Modul("Software Engineering II");
		modul.setSemester(4);
		modul.setCredits(10);
		modul.setPraesenz(96);
		modul.setEigenstudium(204);
		
		modul.addUnit(new Unit("Advanced Software Engineering", "Klausur, Programmentwurf", 100));
		modul.addUnit(new Unit("Softwarequalität", "", 0));
		
		studienplan.addModul(modul);
		
		// Große Studienarbeit
		modul = new Modul("Große Studienarbeit");
		modul.setSemester(5);
		modul.setCredits(10);
		modul.setPraesenz(24);
		modul.setEigenstudium(276);
		
		modul.addUnit(new Unit("Große Studienarbeit", "Studienarbeit", 100));
		
		studienplan.addModul(modul);
		
		// Kommunikations- und Netztechnik II
		modul = new Modul("Kommunikations- und Netztechnik II");
		modul.setSemester(5);
		modul.setCredits(5);
		modul.setPraesenz(72);
		modul.setEigenstudium(78);
		
		modul.addUnit(new Unit("IT-Sicherheit", "Klausur, Programmentwurf", 50));
		modul.addUnit(new Unit("Verteilte Systeme", "Klausur, Programmentwurf", 50));
		
		studienplan.addModul(modul);
		
		// Datenbanken II
		modul = new Modul("Datenbanken II");
		modul.setSemester(5);
		modul.setCredits(5);
		modul.setPraesenz(72);
		modul.setEigenstudium(78);
		
		modul.addUnit(new Unit("DB-Implementierungen", "Klausur", 50));
		modul.addUnit(new Unit("Data Warehouse", "Klausur", 50));
		
		studienplan.addModul(modul);
		
		// Architekturen
		modul = new Modul("Architekturen");
		modul.setSemester(5);
		modul.setCredits(5);
		modul.setPraesenz(72);
		modul.setEigenstudium(78);
		
		modul.addUnit(new Unit("Architekturen von Businesssystemen", "Klausur", 50));
		modul.addUnit(new Unit("Architekturen von Rechnersystemen", "Klausur", 50));
		
		studienplan.addModul(modul);
		
		// Consulting, technischer Vertrieb und Recht
		modul = new Modul("Consulting, technischer Vertrieb und Recht");
		modul.setSemester(5);
		modul.setCredits(5);
		modul.setPraesenz(72);
		modul.setEigenstudium(78);
		
		modul.addUnit(new Unit("Consulting und technischer Vertrieb", "Klausur", 50));
		modul.addUnit(new Unit("Recht", "Klausur", 50));
		
		studienplan.addModul(modul);
		
		// Wissensbasierte und intelligente Systeme
		modul = new Modul("Wissensbasierte und intelligente Systeme");
		modul.setSemester(5);
		modul.setCredits(5);
		modul.setPraesenz(72);
		modul.setEigenstudium(78);
		
		modul.addUnit(new Unit("Interaktive Systeme", "Klausur, Programmentwurf", 50));
		modul.addUnit(new Unit("Wissensbasierte Systeme", "Klausur, Programmentwurf", 50));
		
		studienplan.addModul(modul);
		
		// Wahlmodul Angewandte Informatik (STG Jahr 2)
		modul = new Modul("Wahlmodul Angewandte Informatik (STG Jahr 3)");
		modul.setSemester(6);
		modul.setCredits(5);
		modul.setPraesenz(72);
		modul.setEigenstudium(78);
		
		modul.addUnit(new Unit("Wahlfach 3. Studienjahr", "Klausur", 100));
		
		studienplan.addModul(modul);
		
		// Praxis III
		modul = new Modul("Praxis III");
		modul.setSemester(6);
		modul.setCredits(8);
		modul.setPraesenz(4);
		modul.setEigenstudium(236);
		
		modul.addUnit(new Unit("Projektarbeit III", "Ablauf- und Reflexionsbericht, Projektarbeit", 100));
		modul.addUnit(new Unit("Wissenschaftliches Arbeiten", "", 0));
		
		studienplan.addModul(modul);
		
		// Bachelorarbeit
		modul = new Modul("Bachelorarbeit");
		modul.setSemester(6);
		modul.setCredits(12);
		modul.setPraesenz(6);
		modul.setEigenstudium(354);
		modul.setBenotet(false);
		
		modul.addUnit(new Unit("Bachelorarbeit", "Bachelorarbeit", 100));
		
		studienplan.addModul(modul);
		
		
		return studienplan;
		
	}
	
}
