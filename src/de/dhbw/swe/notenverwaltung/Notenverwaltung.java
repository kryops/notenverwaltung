package de.dhbw.swe.notenverwaltung;

public class Notenverwaltung {
	
	public void notenEintragen(Student student, Unit unit){
		unit.getModul().modulnoteBerechnen();
		
	}
	
	public void notenBearbeiten(Student student, Unit unit){
		unit.getModul().modulnoteBerechnen();
		
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
