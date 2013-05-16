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
	 * 
	 * @param matrikelnummer
	 * @param vorname
	 * @param nachname
	 * @param geburtsdatum
	 * @param geburtsort
	 * @param heimadresse
	 * @param abiturnote
	 * 
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
		
		DHBW dhbw = DHBW.getDHBW();
		
		if((matrikelnummer != 0) && (dhbw.findStudentByMatrikelnummer(matrikelnummer) == null) && (vorname != null) && (nachname != null) && (geburtsdatum != null) && (geburtsort != null)
				&& (heimadresse != null) && (abiturnote != 0)){
			new Student(matrikelnummer, vorname, nachname, geburtsdatum, geburtsort, heimadresse, abiturnote);
			
			if(zusatzStudentenDaten(matrikelnummer, email, telefonnummer, abiturjahrgang, abiturort, studentenadresse, firmenname, ausbildungsleiter, 
					studiengang, jahrgang)){
				System.out.println("Der Student wurde erfolgreich angelegt und immatrikuliert.");
			}else{
				System.out.println("Der Student konnte nicht immatrikuliert werden, da die Daten nicht vollständig sind.");
				
			}
			
		}else {
			if(dhbw.findStudentByMatrikelnummer(matrikelnummer) != null) {
				System.out.println("Die Matrikelnummer wurde bereits vergeben.");
			}
			System.out.println("Der Student konnte nicht immatrikuliert werden, da die Daten nicht vollständig sind.");
		}
		
	}
	
	
	
	/**
	 * F41: 
	 * Der Studentendaten können hier bearbeitet werden.
	 * Dabei ist zu beachten, dass die Matrikelnummer nicht bearbeitet werden kann, 
	 * da diese zur Identifikation des Studenten benötigt wird.
	 * Die Studentendaten, die nicht verändert werden sollen, werden bei der Funktion 
	 * einfach als 'null' (Strings) oder '0' (int) mitgegeben.
	 * 
	 * @param matrikelnummer
	 * 
	 * Die folgenden Daten können bearbeitet werden:
	 * @param vorname
	 * @param nachname
	 * @param geburtsdatum
	 * @param geburtsort
	 * @param heimadresse
	 * @param abiturnote
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
	public void studentBearbeiten(int matrikelnummer, String vorname, String nachname, Date geburtsdatum, String geburtsort, 
			String heimadresse, double abiturnote, String email, String telefonnummer, int abiturjahrgang, 
			String abiturort, String studentenadresse, String firmenname, String ausbildungsleiter, 
			String studiengang, int jahrgang){
		
		DHBW dhbw = DHBW.getDHBW();
		Student student = dhbw.findStudentByMatrikelnummer(matrikelnummer);
		
		if(student != null){
			if(vorname != null){
				student.setVorname(vorname);
			}
			
			if(nachname != null){
				student.setNachname(nachname);
			}
			
			if(geburtsdatum != null){
				student.setGeburtsdatum(geburtsdatum);
			}
			
			if(geburtsort != null){
				student.setGeburtsort(geburtsort);
			}
			
			if(heimadresse != null){
				student.setHeimadresse(heimadresse);
			}
			
			if(abiturnote != 0){
				student.setAbiturnote(abiturnote);
			}
			
			if(zusatzStudentenDaten(matrikelnummer, email, telefonnummer, abiturjahrgang, abiturort, studentenadresse, firmenname, ausbildungsleiter, 
					studiengang, jahrgang)){
				System.out.println("Der Student wurde erfolgreich bearbeitet.");
			}else{
				System.out.println("Der angegebenen Matrikelnummer konnte kein Student zugeordnet werden.");
			}
		}else{
			System.out.println("Der angegebenen Matrikelnummer konnte kein Student zugeordnet werden.");
			
		}
		
		
	}
	
	public void studentAnzeigen(){
		
		
	}
	
	public void studentExmatrikulieren(){
		
		
	}
	
	
	
	public boolean zusatzStudentenDaten(int matrikelnummer, String email, String telefonnummer, int abiturjahrgang, String abiturort, 
			String studentenadresse, String firmenname, String ausbildungsleiter, String studiengang, int jahrgang){
		
		DHBW dhbw = DHBW.getDHBW();
		Student student = dhbw.findStudentByMatrikelnummer(matrikelnummer);
		
		if(student != null){
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
			return true;
		}else{
			return false;
			
		}
		
	}
	
	
	
	

}
