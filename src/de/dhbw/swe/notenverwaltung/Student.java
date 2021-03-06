package de.dhbw.swe.notenverwaltung;

import java.security.InvalidParameterException;
import java.util.Date;

/**
 * Beschreibt das Objekt "Student".
 * In der Hierarchie liegt der Student direkt unter dem Kurs.
 * Er hat einen pers�nlichen Studienplan, in dem nicht nur die Modul-, sondern auch die Noten f�r Units eingetragen werden.
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
	
	//Adressen
	private String heimadresse;
	private String studentenadresse;
	
	//Studiendaten
	private int matrikelnummer;
	private String firmenname;
	private String ausbildungsleiter;
	private String studiengang;
	private int jahrgang;
	private boolean immatrikuliert;
	
	/**
	 * true, wenn die Bachelorarbeit bestanden wurde
	 * Wird automatisch gesetzt, wenn eine Bachelorarbeit-Note mit 4 oder besser eingetragen wird
	 */
	private boolean bachelorArbeitBestanden;

	private double bachelorArbeitNote;
	
	/**
	 * Endnote des Bachelor-Abschlusses
	 * Wird automatisch ausgerechnet, wenn �berpr�ft wird, ob das Studium abgeschlossen wurde
	 */
	private double bachelornote;
	
	/**
	 * Studienplan des Studenten
	 * Wird beim Anlegen des Studenten automatisch generiert (AI)
	 */
	private Studienplan studienplan;
	
	/**
	 * Kurs des Studenten
	 * Wird automatisch gesetzt, wenn der Student dem Kurs zugeordnet wird
	 */
	private Kurs kurs;
	
	/**
	 * Wird automatisch beim Berechnen der Bachelornote gesetzt, wenn das
	 * Studium erfolgreich abgeschlossen wurde
	 */
	private boolean studiumAbgeschlossen;
	
	
	/**
	 * Legt einen Studenten an und erstellt dessen Studienplan (AI)
	 * @param matrikelnummer
	 * @param vorname
	 * @param nachname
	 * @param geburtsdatum
	 * @param geburtsort
	 * @param heimadresse
	 * @param abiturnote
	 */
	public Student(int matrikelnummer, String vorname, String nachname, Date geburtsdatum, String geburtsort, String heimadresse, double abiturnote) {
		this.matrikelnummer = matrikelnummer;
		this.vorname = vorname;
		this.nachname = nachname;
		this.geburtsdatum = geburtsdatum;
		this.geburtsort = geburtsort;
		this.heimadresse = heimadresse;
		this.abiturnote = abiturnote;
		this.setImmatrikuliert(true);
		
		// Studienplan generieren
		this.studienplan = StudienplanGenerator.createStudienplanAI();
	}
	
	/**
	 * Berechnet die Bachelor-Endnote
	 * Markiert das Studium als abgeschlossen, wenn alle Module und die Bachelorarbeit bestanden wurden
	 * Wird von der isStudiumAbgeschlossen() aufgerufen
	 */
	private void bachelornoteBerechnen() {
		
		if(!bachelorArbeitBestanden) {
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
		
		// Module z�hlen 80%, Bachelorarbeit z�hlt 20%
		note = (note / creditSumme) * 0.8;
		note = note + (bachelorArbeitNote * 0.2);
		
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
	
	
	public String getHeimadresse() {
		return heimadresse;
	}
	
	
	
	public String getStudentenadresse() {
		return studentenadresse;
	}
	
	
	
	
	public void setStudentenadresse(String studentenadresse) {
		this.studentenadresse = studentenadresse;
	}
	
	
	public void setHeimadresse(String heimadresse) {
		this.heimadresse = heimadresse;
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
	
	
	/**
	 * @return Bachelornote, auf eine Nachkommastelle gerundet
	 */
	public double getBachelornote() {
		return ((double)Math.round(bachelornote * 10) / 10);
	}
	
	/**
	 * @return exakt berechnete Bachelornote, nicht gerundet
	 */
	public double getExactBachelornote() {
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
	
	
	/**
	 * �berpr�ft, ob das Studium abgeschlossen wurde
	 * St��t die Berechnung der Bachelor-Endnote an
	 * @return
	 */
	public boolean isStudiumAbgeschlossen() {
		
		if(!studiumAbgeschlossen) {
			bachelornoteBerechnen();
		}
		
		return studiumAbgeschlossen;
	}
	
	public boolean isBachelorArbeitBestanden() {
		return bachelorArbeitBestanden;
	}
	
	/**
	 * Gibt die Note der Bachelorarbeit zur�ck
	 * @return Note; 0, wenn die Note noch nicht berechnet oder das Studium nicht abgeschlossen wurde
	 */
	public double getBachelorArbeitNote() {
		return bachelorArbeitNote;
	}
	
	/**
	 * Setzt die Note der Bachelorarbeit
	 * Bei 4 oder besser wird die Bachelorarbeit als bestanden markiert
	 * @param bachelorArbeitNote Note zwischen 1 und 5
	 * @throws InvalidParameterException Note au�erhalb des g�ltigen Bereichs
	 */
	public void setBachelorArbeitNote(double bachelorArbeitNote) {
		
		if(bachelorArbeitNote < 1 || bachelorArbeitNote > 5) {
			throw new InvalidParameterException("Note au�erhalb des g�ltigen Bereichs!");
		}
		
		this.bachelorArbeitNote = bachelorArbeitNote;
		
		// als bestanden / nicht bestanden markieren
		if(bachelorArbeitNote <= 4) {
			bachelorArbeitBestanden = true;
		}
		else {
			bachelorArbeitBestanden = false;
		}
	}
	
}
