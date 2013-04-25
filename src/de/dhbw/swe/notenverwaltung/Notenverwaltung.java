package de.dhbw.swe.notenverwaltung;
/**
 * Diese Klasse enth�lt alle Treiberfunktionen, die die Benotung der Studenten betrifft.
 * Dazu z�hlen auch die Berechnungen der Endnoten.
 * 
 * Funktionen: Noten eintragen, bearbeiten, abfragen, archivieren, l�schen, Bachelornote berechnen
 * 
 * @author Hanne Nobis
 */
public class Notenverwaltung {
	
	
	
	/**
	 * Diese Funktion kann Noten komplett neu eintragen und auch �berschreiben.
	 * 
	 * @param matrikelnummer 
	 * @param unitname 
	 * @param note 
	 * 
	 * @author Hanne Nobis
	 */
	public void notenEintragen(int matrikelnummer, String unitname, double note){
		DHBW dhbw = DHBW.getDHBW();
		Student student = dhbw.findStudentByMatrikelnummer(matrikelnummer);
		Unit unit = student.getStudienplan().findUnitByName(unitname);
		unit.setNote(note);	
	}
	
	/**
	 * Zur Ausgabe der Noten eines ganzen Kurses.
	 * Die Ausgabe geschieht durch die Funktion "notenAusgabe".
	 * (Mangels einer grafischen Oberfl�che, geschieht die Ausgabe in der Konsole.)
	 * 
	 * @param kursname
	 * @param unitname
	 * 
	 * @author Hanne Nobis
	 */
	public void notenAbfragen(String kursname, String unitname){
		DHBW dhbw = DHBW.getDHBW();
		Kurs kurs = dhbw.findKursByName(kursname);
		
		for(Student student : kurs.getStudenten()){
			notenAusgabe(student, unitname);
		}
	}
	
	/**
	 * Notenausgabe eines einzelnen Studenten. Dieser wird durch die Matrikelnummer eindeutig ausgew�hlt.
	 * Die Funktion "notenAusgabe" �bernimmt die Ausgabe.
	 * (Mangels einer grafischen Oberfl�che, geschieht die Ausgabe in der Konsole.)
	 * 
	 * @param matrikelnummer
	 * @param unitname
	 * 
	 * @author Hanne Nobis
	 */
	public void notenAbfragen(int matrikelnummer, String unitname){
		DHBW dhbw = DHBW.getDHBW();
		Student student = dhbw.findStudentByMatrikelnummer(matrikelnummer);
		notenAusgabe(student, unitname);		
	}
	
	/**
	 * Die Note einer Unit werden in der Konsole (zusammen mit dem Vornamen, Nachnamen und der Matrikelnummer eines Studenten) ausgegeben.
	 * 
	 * @param student
	 * @param unitname
	 * 
	 * @author Hanne Nobis
	 */
	public void notenAusgabe(Student student, String unitname){
		System.out.print(student.getVorname() + student.getNachname() + student.getMatrikelnummer());
		System.out.println(student.getStudienplan().findUnitByName(unitname).getNote());		
	}
	
	public void notenArchivieren(){
		//TODO: archivieren
		
	}
	
	public void notenLoeschen(){
		//TODO:l�schen
		
	}
	
	public void bachelornotenBerechnen(Kurs kurs){
		//TODO: Bachelornote berechnen, Rechenvorgang im Student
		
	}
	
	public void modulnoteBerechnen(Modul modul){
		//TODO:Rechenvorgang im Modul
		//Info: Unterscheidung zwischen  a) bereits alle Credits eingetragen(--> Modulnote berechnen) 
		//b) noch nicht alle Credits eingetragen
		
		
	}

}
