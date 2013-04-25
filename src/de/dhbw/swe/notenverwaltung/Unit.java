package de.dhbw.swe.notenverwaltung;

/**
 * Units sind Modulen zugeordnet. Alle Noten des Studenten bis auf die der Bachelorarbeit werden
 * in den Units gespeichert.
 * 
 * @author Michael Strobel
 *
 */
public class Unit {
	
	private String name;
	private String pruefungsform;
	private double note;
	
	/**
	 * Gewichtung der Unit in Prozent
	 * Unbenotete Units haben eine Gewichtung von 0
	 */
	private int gewichtung;
	
	/**
	 * Gibt an, ob der Student eine Unit bestanden hat
	 * Wird beim setzen einer Note mit 4 oder besser automatisch gesetzt
	 */
	private boolean bestanden;
	
	/**
	 * Das Modul, zu welchem die Unit geh�rt
	 * Wird automatisch beim Hinzuf�gen zur Unit-Liste des Moduls gesetzt
	 */
	private Modul modul;
	
	
	
	
	/**
	 * Unit anlegen; Die Gewichtung wird automatisch auf 100 gesetzt
	 * @param name
	 */
	public Unit(String name) {
		this.name = name;
		this.gewichtung = 100;
	}
	
	/**
	 * Unit anlegen
	 * @param name
	 * @param pruefungsform
	 * @param gewichtung 0 bei unbenoteten Units
	 */
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
	
	/**
	 * Setzt die Note einer Unit
	 * Ist sie 4 oder besser, wird die Unit als bestanden markiert und die Berechnung
	 * der Gesamtnote des Moduls angesto�en, zu welchem die Unit geh�rt
	 * @param note
	 */
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
	
	/**
	 * Markiert eine Unit als bestanden
	 * St��t die Berechnung der dazugeh�rigen Modulnote an
	 * @param bestanden
	 */
	public void setBestanden(boolean bestanden) {
		this.bestanden = bestanden;
		
		if(this.modul != null) {
			this.modul.modulnoteBerechnen();
		}
	}
	
	/**
	 * Eine Unit gilt als benotete Pr�fungsleistung, wenn eine Gewichtung gr��er 0 eingetragen wurde
	 * @return
	 */
	public boolean isBenotet() {
		return (gewichtung != 0);
	}
	
}
