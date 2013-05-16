package de.dhbw.swe.notenverwaltung;

import java.util.Date;
import java.util.List;

/**
 * Diese Klasse enthält alle Treiberfunktionen, die die Verwaltung der Studenten betrifft.
 * Dazu gehört auch die Zuweisung der Kurse.
 * 
 * Funktionen: Kurse einteilen, Studenten immatrikulieren, bearbeiten, anzeigen, exmatrikulieren
 * 
 * @author Hanne Nobis
 */
public class Studentenverwaltung {
	

	/**
	 * F30:
	 * Bei der erstmaligen Erstellung eines Kurses können direkt die Studenten zugeordnet werden.
	 * 
	 * @param kursname
	 * @param jahrgang
	 * @param studiengang
	 * @param studiengangsleiter
	 * @param raum
	 * @param studenten
	 * 
	 * @author Hanne Nobis
	 */
	public void kurseErstmalsEinteilen(String kursname, int jahrgang, String studiengang, String studiengangsleiter, String raum, 
			List<Student> studenten){
		int kursgroesse = 0;
		
		if(studenten != null){
			kursgroesse = studenten.size();
		}
		
		if(kursgroesse < 21){
			DHBW dhbw = DHBW.getDHBW();
			
			if((kursname != null) && (dhbw.findKursByName(kursname) == null) && (jahrgang != 0) && (studiengang != null)){
				Kurs kurs = new Kurs(kursname, jahrgang, studiengang);
				if(studiengangsleiter != null){
					kurs.setStudiengangsleiter(studiengangsleiter);			
				}
				if(raum != null){
					kurs.setRaum(raum);
				}
				
				if(studenten != null){
					for(Student student : studenten ){
						if(student.isImmatrikuliert()){
							kurs.addStudent(student);
						}else{
							System.out.println("Der Student " + student.getMatrikelnummer() + " ist nicht immatrikuliert und kann" + 
									"daher dem Kurs " + kurs.getKursname() + "nicht hinzugefügt werden. FC: SV056");
						}			
					}	
				}
				System.out.println("Der Kurs wurde angelegt.");
			}
		}else{
			System.out.println("Bitte verringern Sie die Anzahl der Studenten auf maximal 20. FC: SV063");
			
		}
	}
	
	
	/**
	 * F30:
	 * Zum Bearbeiten der Kursdaten wird diese Funktion verwendet,
	 * allerdings kann weder der Kursname noch der Jahrgang oder der Studiengang geändert werden.
	 * Die Änderung der zugehörigen Studenten erfolgt in der Parallelfunktion (Überladung).
	 * 
	 * @param kursname
	 * @param studiengangsleiter
	 * @param raum
	 * 
	 * @author Hanne Nobis
	 */
	public void kursBearbeiten(String kursname, String studiengangsleiter, String raum){
		DHBW dhbw = DHBW.getDHBW();
		Kurs kurs = dhbw.findKursByName(kursname);
		
		if(kurs != null){
			if(studiengangsleiter != null){
				kurs.setStudiengangsleiter(studiengangsleiter);
			}
			if(raum != null){
				kurs.setRaum(raum);
			}
		}else{
			System.out.println("Zu dem angegebenen Kursnamen wurde kein Kurs gefunden. FC: SV093");	
		}
	}
	

	/**
	 * F30:
	 * zum Bearbeiten der Studentenzugehörigkeit zu einem Kurs 
	 * es wird eine Liste mit Studenten mitgegeben, die entweder hinzugefügt oder entfernt werden
	 * bei einer Aktion = 1 werden die Studenten hinzugefügt
	 * bei einer Aktion = -1 werden die Studenten gelöscht
	 * 
	 * @param kursname
	 * @param studenten
	 * @param aktion
	 * 
	 * @author Hanne Nobis
	 */
	public void kursBearbeiten(String kursname, List<Student> studenten, int aktion){
		DHBW dhbw = DHBW.getDHBW();
		Kurs kurs = dhbw.findKursByName(kursname);
		
		if((kurs != null) && (studenten != null)){
			//Hinzufügen der übergebenen Studenten
			if(aktion == 1){
				if(kurs.getStudenten().size() == 20){
					System.out.println("Die maximale Teilnehmerzahl des Kurses ist bereits erreicht. FC: SV119");
					
				}else if((kurs.getStudenten().size() + studenten.size()) < 21){
					for(Student student : studenten ){
						if(student.isImmatrikuliert()){
							kurs.addStudent(student);
						}else{
							System.out.println("Der Student " + student.getMatrikelnummer() + " ist nicht immatrikuliert und kann" + 
									"daher dem Kurs " + kurs.getKursname() + "nicht hinzugefügt werden. FC: SV127");
						}	
					}
				}else{
					System.out.println("Bitte verringern Sie die Anzahl der hinzuzufügenden Studenten, so dass" + 
							" die maximale Kursgröße (20Teilnehmer) nicht überschritten wird. FC: SV132");
				}
				
				
				
			//Entfernen der übergebenen Studenten 	
			}else if(aktion == -1){
				for(Student student : studenten){
					if(student.getKurs().getKursname() == kursname){
						kurs.removeStudent(student);						
					}else{
						System.out.println("Der Student " + student.getMatrikelnummer() + " gehört nicht dem ausgewähltem Kurs an und" + 
								"wird daher nicht entfernt. FC: SV144");
					}
				}
	
			}else{
				System.out.println("Die angegebene Aktion ist nicht zulässig. FC: SV149");
			}
					
		}else{
			System.out.println("Zu dem angegebenen Kursnamen wurde kein Kurs gefunden. FC: SV153");	
		}
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
				System.out.println("Der Student konnte nicht immatrikuliert werden, da die Daten nicht vollständig sind. FC: SV202");
				
			}
			
		}else {
			if(dhbw.findStudentByMatrikelnummer(matrikelnummer) != null) {
				System.out.println("Die Matrikelnummer wurde bereits vergeben. FC: SV208");
			}
			System.out.println("Der Student konnte nicht immatrikuliert werden, da die Daten nicht vollständig sind. FC: SV210");
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
		
		if((student != null) && student.isImmatrikuliert()){
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
				System.out.println("Der angegebenen Matrikelnummer konnte kein Student zugeordnet werden. FC: SV283");
			}
		}else{
			System.out.println("Entweder der angegebenen Matrikelnummer konnte kein Student zugeordnet werden oder der Student ist" + 
					"nicht immatrikuliert. FC: SV287");
			
		}
		
		
	}
	
	/**
	 * F41:
	 * zur Ausgabe aller vorhandenen Daten in der Konsole (mangels einer grafischen Oberfläche)
	 * Identifizierung durch Übergabe der Matrikelnummer
	 * 
	 * @param matrikelnummer
	 * 
	 * @author Hanne Nobis
	 */
	public void studentAnzeigen(int matrikelnummer){
		String immatrikulationsstatus;
		DHBW dhbw = DHBW.getDHBW();
		Student student = dhbw.findStudentByMatrikelnummer(matrikelnummer);
		if(student != null){
			if(student.isImmatrikuliert()){
				immatrikulationsstatus = "immatrikuliert";
			}else{
				immatrikulationsstatus = "exmatrikuliert";
			}
			//AUSGABE
			System.out.println("Matr.-Nr.: " + matrikelnummer + "  Name: " + student.getVorname() + " " + student.getNachname());
			System.out.println("Studium  Studiengang: " + student.getStudiengang() + " Jahrgang: " + student.getJahrgang() + immatrikulationsstatus);
			System.out.println("Geburtsdaten: " + student.getGeburtsort() + " " + student.getGeburtsdatum());
			System.out.println("Kontaktdaten  Email " + student.getEmail() + " Tel." + student.getTelefonnummer());
			System.out.println("Heimatadresse: " + student.getHeimadresse());
			System.out.println("Studentnadresse: " + student.getStudentenadresse());
			System.out.println("Abitur  Abschlussnote: " + student.getAbiturnote() + " " + student.getAbiturort() + " Jahrgang: " + student.getAbiturjahrgang());
			System.out.println("Unternehmen  Firma: " + student.getFirmenname() + " Ausbildungsleiter: " + student.getAusbildungsleiter());
			
		}else{
			System.out.println("Der angegebenen Matrikelnummer konnte kein Student zugeordnet werden. FC: SV324");
			
		}
		
	}
	
	
	
	
	
	
	/**
	 * F42:
	 * der Student wird bei Aufruf dieser Funktion anhand der Matrikelnummer exmatrikuliert 
	 * 
	 * @param matrikelnummer
	 * 
	 * @author Hanne Nobis
	 */
	public void studentExmatrikulieren(int matrikelnummer){
		DHBW dhbw = DHBW.getDHBW();
		Student student = dhbw.findStudentByMatrikelnummer(matrikelnummer);
		
		if((student != null) && student.isImmatrikuliert()){
			student.setImmatrikuliert(false); 
			student.getKurs().removeStudent(student);			
			System.out.println("Der Student " + matrikelnummer + " wurde exmatrikuliert.");			
		}else{
			System.out.println("Der Student " + matrikelnummer + " konnte nicht exmatrikuliert werden. FC: SV352");
		}
		
	}
	
	
	/**
	 * Diese Funktion dient zur Unterstützung beim Anlegen eines Studenten und bei der
	 * Vervollständigung der Studentendaten.
	 * Die Matrikelnummer dient nur der Identifizierung des Studenten.
	 * 
	 * 
	 * @param matrikelnummer
	 * @param email
	 * @param telefonnummer
	 * @param abiturjahrgang
	 * @param abiturort
	 * @param studentenadresse
	 * @param firmenname
	 * @param ausbildungsleiter
	 * @param studiengang
	 * @param jahrgang
	 * @return
	 */
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
