package de.dhbw.swe.notenverwaltung;

import java.util.List;

public class DHBW {
	
	
	private List<Kurs> kurse;

	public DHBW(){
		
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
