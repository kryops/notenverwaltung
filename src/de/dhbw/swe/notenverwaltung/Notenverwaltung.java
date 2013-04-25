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
		//DONE Hanne
		DHBW dhbw = DHBW.getDHBW();
		Student student = dhbw.findStudentByMatrikelnummer(matrikelnummer);
		Unit unit = student.getStudienplan().findUnitByName(unitname);
		unit.setNote(note);	
	}
	
	
	public void notenAbfragen(Kurs kurs, String unitname){
		//Unit anhand des Namens finden	
	}
	public void notenAbfragen(Student student, String unitname){
		//Unit anhand des Namens finden	
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
