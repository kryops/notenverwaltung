package de.dhbw.swe.notenverwaltung;

public class Unit {
	
	private String name;
	private String pruefungsform;
	private double note;
	private int gewichtung;
	private boolean bestanden;
	
	private Modul modul;
	
	
	public Unit(String name) {
		this.name = name;
		this.gewichtung = 100;
	}
	
	public Unit(String name, String pruefungsform, int gewichtung) {
		this.name = name;
		this.pruefungsform = pruefungsform;
		this.gewichtung = gewichtung;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPruefungsform() {
		return pruefungsform;
	}

	public void setPruefungsform(String pruefungsform) {
		this.pruefungsform = pruefungsform;
	}

	public double getNote() {
		return note;
	}

	public void setNote(double note) {
		this.note = note;
		
		if(note <= 4) {
			this.bestanden = true;
			
			if(this.modul != null) {
				this.modul.modulnoteBerechnen();
			}
		}
	}

	public int getGewichtung() {
		return gewichtung;
	}

	public void setGewichtung(int gewichtung) {
		this.gewichtung = gewichtung;
	}

	public Modul getModul() {
		return modul;
	}

	public void setModul(Modul modul) {
		this.modul = modul;
	}

	public boolean isBestanden() {
		return bestanden;
	}

	public void setBestanden(boolean bestanden) {
		this.bestanden = bestanden;
	}
	
}
