package de.dhbw.swe.notenverwaltung;
/**
 * Diese Klasse enthält alle Treiberfunktionen, die die Benotung der Studenten betrifft.
 * Dazu zählen auch die Berechnungen der Endnoten.
 * 
 * Funktionen: Noten eintragen, bearbeiten, abfragen, archivieren, löschen, Bachelornote berechnen
 * 
 * @author Hanne Nobis
 */
public class Notenverwaltung {
	
	
	
	/**
	 * Diese Funktion kann Noten komplett neu eintragen und auch überschreiben.
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
		
		if (student == null){
			System.out.println("Student konnte im System nicht gefunden werden. Fehlercode: NV028");			
		}else{
			Unit unit = student.getStudienplan().findUnitByName(unitname);
			
			if(unit == null){
				System.out.println("Unit konnte im System nicht gefunden werden. Fehlercode: NV033");
				
			}else{
				unit.setNote(note);	
			}
		}
	}
	
	/**
	 * Zur Ausgabe der Noten eines ganzen Kurses.
	 * Die Ausgabe geschieht durch die Funktion "notenAusgabe".
	 * (Mangels einer grafischen Oberfläche, geschieht die Ausgabe in der Konsole.)
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
	 * Notenausgabe eines einzelnen Studenten. Dieser wird durch die Matrikelnummer eindeutig ausgewählt.
	 * Die Funktion "notenAusgabe" übernimmt die Ausgabe.
	 * (Mangels einer grafischen Oberfläche, geschieht die Ausgabe in der Konsole.)
	 * 
	 * @param matrikelnummer
	 * @param unitname
	 * 
	 * @author Hanne Nobis
	 */
	public void notenAbfragen(int matrikelnummer, String unitname){
		DHBW dhbw = DHBW.getDHBW();
		Student student = dhbw.findStudentByMatrikelnummer(matrikelnummer);
		if (student == null){
			System.out.println("Student konnte im System nicht gefunden werden. Fehlercode: NV063");			
		}else{
			notenAusgabe(student, unitname);		
		}
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
		System.out.print("Student: " + student.getVorname() + student.getNachname() + ", " + student.getMatrikelnummer());
		
		if(student.getStudienplan().findUnitByName(unitname) == null){					//Unit existiert nicht
			System.out.println("Unit konnte im System nicht gefunden werden. Fehlercode: NV080");		
			
		}else if(student.getStudienplan().findUnitByName(unitname).getNote() == 0){		//Note noch nicht erfasst
			System.out.println("Zu dieser Unit wurde noch keine Note eingetragen. Fehlercode: NV082");	
			
		}else { //Note kann ausgegeben werden, Unterscheidung zwischen benotet und bestanden/nicht bestanden notwendig
			
			if(student.getStudienplan().findUnitByName(unitname).isBenotet()){ //Note wird so interpretiert, wie sie eingetragen wurde
				System.out.println("Unit: " + unitname + "Benotung: " + student.getStudienplan().findUnitByName(unitname).getNote());
			
			}else{//Note wird als bestanden/nicht bestanden interpretiert: 1.0 bestanden, 5.0 nicht bestanden
				
				if(student.getStudienplan().findUnitByName(unitname).isBestanden()){
					System.out.println("Unit: " + unitname + "Benotung: bestanden");
									
				}else{
					System.out.println("Unit: " + unitname + "Benotung: nicht bestanden");
				}
			}
		}
	}
	
	
	
	
	
	
	public void notenArchivieren(){
		//TODO: Frage
		
	}
	
	public void notenLoeschen(int matrikelnummer){
		//TODO: Frage
		DHBW dhbw = DHBW.getDHBW();
		Student student = dhbw.findStudentByMatrikelnummer(matrikelnummer);
		if(student.isImmatrikuliert() == false){
			
		}
	}
	/**
	 * Sofern das Studium beendet ist, soll die Bachelornote berechnet werden.
	 * Dazu muss der Student immatrikuliert sein und alle Noten müssen im System eingetragen sein.
	 * Wenn alle Noten im System eingetragen sind, ist die Variable "Studium abgeschlossen" true.
	 * 
	 * @param kurs
	 * 
	 * @author Hanne Nobis
	 */
	public void bachelornotenBerechnen(Kurs kurs){
		for(Student student : kurs.getStudenten()){
			if(student.isStudiumAbgeschlossen() && student.isImmatrikuliert()){
				System.out.print("Student: " + student.getVorname() + student.getNachname() +", " + student.getMatrikelnummer());
				System.out.println(" Bachelornote:" + student.getBachelornote());				
			}
			else{
				System.out.println("Bachelornote wurde nicht berechnet.");
			}
		}		
	}
	
	

}
