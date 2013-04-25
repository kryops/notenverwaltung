package de.dhbw.swe.notenverwaltung;

public class Notenverwaltung {
	
	
	

	public void notenEintragen(int matrikelnummer, String unitname, double note){
		DHBW dhbw = DHBW.getDHBW();
		Student student = dhbw.findStudentByMatrikelnummer(matrikelnummer);
		Unit unit = student.getStudienplan().findUnitByName(unitname);
		unit.setNote(note);
		
		
	}
	
	public void notenBearbeiten(int matrikelnummer, String unitname){
		
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
		//TODO:löschen
		
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
