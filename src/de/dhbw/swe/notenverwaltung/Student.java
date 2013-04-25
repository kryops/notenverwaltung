package de.dhbw.swe.notenverwaltung;

import java.util.Date;
/**
 * Beschreibt das Objekt "Student".
 * In der Hierarchie liegt der Student direkt unter dem Kurs.
 * Er hat einen persönlichen Studienplan, in dem nicht nur die Modul-, sondern auch die Noten für Units eingetragen werden.
 * 
 * @author Hanne Nobis
 */
public class Student {
	
	//Stammdaten
	private String vorname;
	private String nachname;
	private Date geburtsdatum;
	private String geburtsort;
	private String email;
	private String telefonnummer;
	private int abiturjahrgang;
	private String abiturort;
	private double abiturnote;
	
	//Heimadresse
	private String heimstrasse;
	private int heimhausnummer;
	private int heimpostleitzahl;
	private String heimort;
	//Studentenadresse
	private String studentenstrasse;
	private int studentenhausnummer;
	private int studentenpostleitzahl;
	private String studentenort;
	
	//Studiendaten
	private int matrikelnummer;
	private String firmenname;
	private String ausbildungsleiter;
	private String studiengang;
	private int jahrgang;
	private boolean immatrikuliert;
	private boolean bachelorArbeit = false;
	private double bachelorArbeitNote;
	private double bachelornote;
	private Studienplan studienplan;
	private Kurs kurs;
	private boolean studiumAbgeschlossen;
	
	
	
	public Student(String vorname, String nachname, Date geburtsdatum, String geburtsort) {
		this.vorname = vorname;
		this.geburtsdatum = geburtsdatum;
		this.geburtsort = geburtsort;
	}
	
	private void bachelornoteBerechnen() {
		
		if(studienplan == null) {
			return;
		}
		
		if(!bachelorArbeit) {
			return;
		}
		
		// Module durchgehen und Schnitt ausrechnen
		int creditSumme = 0;
		double note = 0;
		
		for(Modul m : studienplan.getModule()) {
			
			if(!m.isBestanden()) {
				return;
			}
			
			if(m.isBenotet()) {
				creditSumme += m.getCredits();
				note += m.getModulnote() * m.getCredits();
			}
			
		}
		
		// Module zählen 80%, Bachelorarbeit zählt 20%
		note = note / creditSumme * 0.8;
		note = note + bachelorArbeitNote * 0.2;
		
		bachelornote = note;
		studiumAbgeschlossen = true;
	}
	
	
	
	//Getter und Setter
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	
	public Date getGeburtsdatum() {
		return geburtsdatum;
	}
	public void setGeburtsdatum(Date geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}
	
	public String getGeburtsort() {
		return geburtsort;
	}
	public void setGeburtsort(String geburtsort) {
		this.geburtsort = geburtsort;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefonnummer() {
		return telefonnummer;
	}
	
	public void setTelefonnummer(String telefonnummer) {
		this.telefonnummer = telefonnummer;
	}
	public int getAbiturjahrgang() {
		return abiturjahrgang;
	}
	
	public void setAbiturjahrgang(int abiturjahrgang) {
		this.abiturjahrgang = abiturjahrgang;
	}
	public String getAbiturort() {
		return abiturort;
	}
	
	public void setAbiturort(String abiturort) {
		this.abiturort = abiturort;
	}
	public double getAbiturnote() {
		return abiturnote;
	}
	
	public void setAbiturnote(double abiturnote) {
		this.abiturnote = abiturnote;
	}
	public String getHeimstrasse() {
		return heimstrasse;
	}
	
	public void setHeimstrasse(String heimstrasse) {
		this.heimstrasse = heimstrasse;
	}
	public int getHeimhausnummer() {
		return heimhausnummer;
	}
	
	public void setHeimhausnummer(int heimhausnummer) {
		this.heimhausnummer = heimhausnummer;
	}
	public int getHeimpostleitzahl() {
		return heimpostleitzahl;
	}
	
	public void setHeimpostleitzahl(int heimpostleitzahl) {
		this.heimpostleitzahl = heimpostleitzahl;
	}
	public String getHeimort() {
		return heimort;
	}
	
	public void setHeimort(String heimort) {
		this.heimort = heimort;
	}
	public String getStudentenstrasse() {
		return studentenstrasse;
	}
	
	public void setStudentenstrasse(String studentenstrasse) {
		this.studentenstrasse = studentenstrasse;
	}
	public int getStudentenhausnummer() {
		return studentenhausnummer;
	}
	
	public void setStudentenhausnummer(int studentenhausnummer) {
		this.studentenhausnummer = studentenhausnummer;
	}
	public int getStudentenpostleitzahl() {
		return studentenpostleitzahl;
	}
	public void setStudentenpostleitzahl(int studentenpostleitzahl) {
		this.studentenpostleitzahl = studentenpostleitzahl;
	}
	public String getStudentenort() {
		return studentenort;
	}
	public void setStudentenort(String studentenort) {
		this.studentenort = studentenort;
	}
	public int getMatrikelnummer() {
		return matrikelnummer;
	}
	public void setMatrikelnummer(int matrikelnummer) {
		this.matrikelnummer = matrikelnummer;
	}
	public String getFirmenname() {
		return firmenname;
	}
	public void setFirmenname(String firmenname) {
		this.firmenname = firmenname;
	}
	public String getAusbildungsleiter() {
		return ausbildungsleiter;
	}
	public void setAusbildungsleiter(String ausbildungsleiter) {
		this.ausbildungsleiter = ausbildungsleiter;
	}
	public String getStudiengang() {
		return studiengang;
	}
	public void setStudiengang(String studiengang) {
		this.studiengang = studiengang;
	}
	public int getJahrgang() {
		return jahrgang;
	}
	public void setJahrgang(int jahrgang) {
		this.jahrgang = jahrgang;
	}
	public boolean isImmatrikuliert() {
		return immatrikuliert;
	}
	public void setImmatrikuliert(boolean immatrikuliert) {
		this.immatrikuliert = immatrikuliert;
	}
	public double getBachelornote() {
		return bachelornote;
	}
	public Studienplan getStudienplan() {
		return studienplan;
	}
	public void setStudienplan(Studienplan studienplan) {
		this.studienplan = studienplan;
		studienplan.setStudent(this);
	}
	public Kurs getKurs() {
		return kurs;
	}
	public void setKurs(Kurs kurs) {
		this.kurs = kurs;
	}

	public boolean isStudiumAbgeschlossen() {
		
		if(!studiumAbgeschlossen) {
			bachelornoteBerechnen();
		}
		
		return studiumAbgeschlossen;
	}
	
	public double getBachelorArbeitNote() {
		return bachelorArbeitNote;
	}

	public void setBachelorArbeitNote(double bachelorArbeitNote) {
		this.bachelorArbeitNote = bachelorArbeitNote;
		
		if(bachelorArbeitNote <= 4) {
			bachelorArbeit = true;
		}
	}
	
}
