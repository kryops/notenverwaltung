package de.dhbw.swe.notenverwaltung;

import java.util.List;
/**
 * Die DHBW ist der Hierarchiekopf.
 * Sie besitzt nur 1 Objekt, welches mit der Funktion "getDHBW" aufgerufen werden kann und vereint alle Kurse unter sich. 
 * (Indirekt also auch alle Studenten.)
 * 
 * @author Hanne Nobis
 *
 */
public class DHBW {
	
	private static DHBW dhbw;
	private List<Kurs> kurse;

	private DHBW(){
	}
	
	public static DHBW getDHBW(){
		
		if(dhbw == null) {
			dhbw = new DHBW();
		}
		
		return dhbw;
	}
	
	
	public Student findStudentByMatrikelnummer(int matrikelnummer){
		//TODO Michael
		return student;
	}
	
	public void addKurs(Kurs kurs) {
		kurse.add(kurs);
	}
	
	public void removeKurs(Kurs kurs) {
		kurse.remove(kurs);
	}
	
	
	//Getter und Setter
	public List<Kurs> getKurse() {
		return kurse;
	}

	public void setKurse(List<Kurs> kurse) {
		this.kurse = kurse;
	}
	
}
