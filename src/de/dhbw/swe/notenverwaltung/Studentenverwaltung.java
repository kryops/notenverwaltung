package de.dhbw.swe.notenverwaltung;

import java.util.Date;

/**
 * Diese Klasse enthält alle Treiberfunktionen, die die Verwaltung der Studenten betrifft.
 * Dazu gehört auch die Zuweisung der Kurse.
 * 
 * Funktionen: Kurse einteilen, Studenten immatrikulieren, bearbeiten, anzeigen, exmatrikulieren
 * 
 * @author Hanne Nobis
 */
public class Studentenverwaltung {
	//TODO: Kurse einteilen
	//TODO: Studenten immatrikulieren (anlegen)
	//TODO: Studenten verwalten: bearbeiten anzeigen
	//TODO: Studenten exmatrikulieren
	
	
	public void kurseEinteilen(){
		
		
	}
	
	
	/**
	 * F40 :
	 * Der Student kann hier erstmalig angelegt werden. Er wird automatisch immatrikuliert, 
	 * sofern die notwendigen Daten vollstänidg sind.
	 * Notwendige Daten (für Immatrikulation): 
	 * @param matrikelnummer
	 * @param vorname
	 * @param nachname
	 * @param geburtsdatum
	 * @param geburtsort
	 * @param heimadresse
	 * @param abiturnote
	 * 
	 * Die folgenden Daten sind optional, bei nicht verwendeten int sollte eine '0' eingetragen werden,
	 * bei Strings ein 'null'.
	 * @param email
	 * @param telefonnummer
	 * @param abiturjahrgang
	 * @param abiturort
	 * @param studentenadresse
	 * @param firmenname
	 * @param ausbildungsleiter
	 * @param studiengang
	 * @param jahrgang
	 * 
	 * @author Hanne Nobis
	 */
	public void studentAnlegen(int matrikelnummer, String vorname, String nachname, Date geburtsdatum, String geburtsort,
			String heimadresse, double abiturnote, String email, String telefonnummer, int abiturjahrgang, String abiturort,
			String studentenadresse, String firmenname, String ausbildungsleiter, String studiengang, int jahrgang){
		
		if((matrikelnummer != 0) && (vorname != null) && (nachname != null) && (geburtsdatum != null) && (geburtsort != null)
				&& (heimadresse != null) && (abiturnote != 0)){
			Student student = new Student(matrikelnummer, vorname, nachname, geburtsdatum, geburtsort, heimadresse, abiturnote);
			zusatzDatenEinpflegen(matrikelnummer, email, telefonnummer, abiturjahrgang, abiturort, studentenadresse, firmenname, ausbildungsleiter, 
					studiengang, jahrgang);
		}else {
			System.out.println("Der Student konnte nicht immatrikuliert werden, da die Daten nicht vollständig sind.");
		}
		
		
	}
	
	
	
	
	public void studentBearbeiten(int matrikelnummer, String vorname, String nachname, Date geburtsdatum, String geburtsort, 
			String heimadresse, double abiturnote, String email, String telefonnummer, int abiturjahrgang, 
			String abiturort, String studentenadresse, String firmenname, String ausbildungsleiter, 
			String studiengang, int jahrgang){
		
		
		
	}
	
	public void studentAnzeigen(){
		
		
	}
	
	public void studentExmatrikulieren(){
		
		
	}
	
	
	
	public void zusatzDatenEinpflegen(int matrikelnummer, String email, String telefonnummer, int abiturjahrgang, String abiturort, 
			String studentenadresse, String firmenname, String ausbildungsleiter, String studiengang, int jahrgang){
		
		DHBW dhbw = DHBW.getDHBW();
		Student student = dhbw.findStudentByMatrikelnummer(matrikelnummer);
		
		if(email != null){
			student.setEmail(email);
		}
		
		if(telefonnummer != null){
			student.setTelefonnummer(telefonnummer);
		}
		
		if(abiturjahrgang != 0){
			student.setAbiturjahrgang(abiturjahrgang);
		}
		
		if(abiturort != null){
			student.setAbiturort(abiturort);
		}
		
		if(studentenadresse != null){
			student.setStudentenadresse(studentenadresse);
		}
		
		if(firmenname != null){
			student.setFirmenname(firmenname);	
		}
		
		if(ausbildungsleiter != null){
			student.setAusbildungsleiter(ausbildungsleiter);
		}
		
		if(studiengang != null){	
			student.setStudiengang(studiengang);			
		}
		
		if(jahrgang != 0){
			student.setJahrgang(jahrgang);
		}
		
		
	}
	
	
	
	

}
