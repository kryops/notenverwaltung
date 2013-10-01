package de.dhbw.swe.notenverwaltung;

import java.security.InvalidParameterException;

/**
 * Diese Klasse enthält alle Treiberfunktionen, die die Benotung der Studenten betrifft.
 * Dazu zählen auch die Berechnungen der Endnoten.
 * 
 * Funktionen: Noten eintragen, bearbeiten, abfragen, archivieren, löschen, Bachelornote berechnen
 * 
 * @author Hanne Nobis
 */
public class Notenverwaltung {
	
	
	
	/** F20 : 
	 * Diese Funktion kann Noten für einen Studenten
	 * komplett neu eintragen und auch bearbeiten.
	 *
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
			System.out.println("Der Student konnte im System nicht gefunden werden. Möglicherweise ist die Matrikelnummer nicht korrekt. FC: NV032");
			
		}else{
			Unit unit = student.getStudienplan().findUnitByName(unitname);
			
			if(unit == null){
				System.out.println("Die Unit konnte im System nicht gefunden werden. FC: NV038");
				
			}else{
				try {
					unit.setNote(note);	
					System.out.println("Die Noten wurden erfolgreich eingetragen/geändert.");
					
				}catch(InvalidParameterException ipe){
					System.out.println("Die Noten liegen nicht im gültigen Bereich. FC: NV046");
					System.out.println("Bitte versuchen Sie es erneut.");
				}				
			}
		}
	}
	
	/** F20 : 
	 * Diese Funktion kann Noten für einen Studenten
	 * komplett neu eintragen und auch bearbeiten.
	 * Hier nur für bestanden, nicht bestanden
	 * 
	 * @param matrikelnummer 
	 * @param unitname 
	 * @param bestanden 
	 * 
	 * @author Hanne Nobis
	 */
	public void notenEintragen(int matrikelnummer, String unitname, boolean bestanden){
		DHBW dhbw = DHBW.getDHBW();
		Student student = dhbw.findStudentByMatrikelnummer(matrikelnummer);
		
		if (student == null){
			System.out.println("Der Student konnte im System nicht gefunden werden. Möglicherweise ist die Matrikelnummer nicht korrekt. FC: NV070");
			
		}else{
			Unit unit = student.getStudienplan().findUnitByName(unitname);
			
			if(unit == null){
				System.out.println("Die Unit konnte im System nicht gefunden werden. FC: NV076");
				
			}else{
				unit.setGewichtung(0);	
				unit.setBestanden(bestanden);
				System.out.println("Das Ergebnis wurde erfolgreich eingetragen/geändert.");			
			}
		}
	}
	
	
	/** F20 : 
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
		
		if(kurs == null){
			System.out.println("Der Kurs konnte im System nicht gefunden werden. FC: NV068");
			
		}else{
			if(kurs.getStudenten().size() == 0){
				System.out.println("Zu diesem Kurs konnten keine Studenten gefunden werden. FC: NV072");
				
			}else{
				for(Student student : kurs.getStudenten()){
					notenAusgabe(student, unitname);
				}
			}
		}
	}
	
	/** F20 :
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
			System.out.println("Student konnte im System nicht gefunden werden. FC: NV097");
			
		}else{
			notenAusgabe(student, unitname);		
		}
	}
	
	/** F20 :
	 * Die Note einer Unit werden in der Konsole (zusammen mit dem Vornamen, Nachnamen und der Matrikelnummer eines Studenten) ausgegeben.
	 * 
	 * @param student
	 * @param unitname
	 * 
	 * @author Hanne Nobis
	 */
	public void notenAusgabe(Student student, String unitname){
		System.out.print("Student: " + student.getVorname() + " " + student.getNachname() + ", " + student.getMatrikelnummer());
		
		if(student.getStudienplan().findUnitByName(unitname) == null){					//Unit existiert nicht
			System.out.println(" | Unit konnte im System nicht gefunden werden. FC: NV116");		
			
		}else if(student.getStudienplan().findUnitByName(unitname).getNote() == 0){		//Note noch nicht erfasst
			System.out.println("Zu dieser Unit wurde noch keine Note eingetragen. FC: NV119");	
			
		}else { //Note kann ausgegeben werden, Unterscheidung zwischen benotet und bestanden/nicht bestanden notwendig
			
			if(student.getStudienplan().findUnitByName(unitname).isBenotet()){ //Note wird so interpretiert, wie sie eingetragen wurde
				System.out.println(" | Unit: " + unitname + " | Benotung: " + student.getStudienplan().findUnitByName(unitname).getNote());
			
			}else{//Note wird als bestanden/nicht bestanden interpretiert: 1.0 bestanden, 5.0 nicht bestanden
				
				if(student.getStudienplan().findUnitByName(unitname).isBestanden()){
					System.out.println(" | Unit: " + unitname + " | Benotung: bestanden");
									
				}else{
					System.out.println(" | Unit: " + unitname + " | Benotung: nicht bestanden");
				}
			}
		}
	}
	
	
	/** F22 :
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
				System.out.print("Student: " + student.getVorname() + " " + student.getNachname() +", " + student.getMatrikelnummer());
				System.out.println(" | Bachelornote: " + student.getBachelornote());				
			}
			else{
				System.out.println("Bachelornote wurde nicht berechnet. FC: NV155");
			}
		}		
	}
	
	

}
