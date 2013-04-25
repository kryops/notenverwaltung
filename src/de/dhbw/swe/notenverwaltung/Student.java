package de.dhbw.swe.notenverwaltung;

import java.util.Date;

public class Student {
	
	//Stammdaten
	private String vorname;
	private String nachname;
	private Date geburtsdatum;
	private String geburtsort;
	private String email;
	private String telefonnummer;
	private Integer abiturjahrgang;
	private String abiturort;
	private double abiturnote;
	
	//Heimadresse
	private String heimstrasse;
	private Integer heimhausnummer;
	private Integer heimpostleitzahl;
	private String heimort;
	//Studentenadresse
	private String studentenstrasse;
	private Integer studentenhausnummer;
	private Integer studentenpostleitzahl;
	private String studentenort;
	
	//Studiendaten
	private int matrikelnummer;
	private String firmenname;
	private String ausbildungsleiter;
	private String studiengang;
	private int jahrgang;
	private boolean immatrikuliert;
	private double bachelornote;
	private Studienplan studienplan;
	private Kurs kurs;
	
	
	
	public Student(String vorname, String nachname, Date geburtsdatum, String geburtsort) {
		this.vorname = vorname;
		this.geburtsdatum = geburtsdatum;
		this.geburtsort = geburtsort;
	}
	
	public double bachelornote(){
		//bachelornote = (für alle Module:(Credits*Modulnote))*0.8 +0.2*Bachelorarbeitsnote
		return bachelornote;
		
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
	public Integer getAbiturjahrgang() {
		return abiturjahrgang;
	}
	
	public void setAbiturjahrgang(Integer abiturjahrgang) {
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
	public Integer getHeimhausnummer() {
		return heimhausnummer;
	}
	
	public void setHeimhausnummer(Integer heimhausnummer) {
		this.heimhausnummer = heimhausnummer;
	}
	public Integer getHeimpostleitzahl() {
		return heimpostleitzahl;
	}
	
	public void setHeimpostleitzahl(Integer heimpostleitzahl) {
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
	public Integer getStudentenhausnummer() {
		return studentenhausnummer;
	}
	
	public void setStudentenhausnummer(Integer studentenhausnummer) {
		this.studentenhausnummer = studentenhausnummer;
	}
	public Integer getStudentenpostleitzahl() {
		return studentenpostleitzahl;
	}
	public void setStudentenpostleitzahl(Integer studentenpostleitzahl) {
		this.studentenpostleitzahl = studentenpostleitzahl;
	}
	public String getStudentenort() {
		return studentenort;
	}
	public void setStudentenort(String studentenort) {
		this.studentenort = studentenort;
	}
	public Integer getMatrikelnummer() {
		return matrikelnummer;
	}
	public void setMatrikelnummer(Integer matrikelnummer) {
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
	public Integer getJahrgang() {
		return jahrgang;
	}
	public void setJahrgang(Integer jahrgang) {
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
	public void setBachelornote(double bachelornote) {
		this.bachelornote = bachelornote;
	}
	public Studienplan getStudienplan() {
		return studienplan;
	}
	public void setStudienplan(Studienplan studienplan) {
		this.studienplan = studienplan;
	}
	public Kurs getKurs() {
		return kurs;
	}
	public void setKurs(Kurs kurs) {
		this.kurs = kurs;
	}
	
	
	
}
