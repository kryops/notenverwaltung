package de.dhbw.swe.notenverwaltung;

import java.util.ArrayList;
import java.util.List;

public class Modul {
	
	private String name;
	private int credits;
	private int semester;
	
	// TODO Fragen, was sie genau darunter versteht und wo wir das herbekommen
	private int stunden;
	private int workload;
	
	private int praesenz;
	private int eigenstudium;
	
	private boolean benotet = true;
	private double modulnote;
	private boolean bestanden;
	
	private Studienplan studienplan;
	private List<Unit> units;
	
	
	public Modul(String name) {
		this.name = name;
		this.units = new ArrayList<Unit>();
	}
	
	
	public void addUnit(Unit unit) {
		units.add(unit);
		unit.setModul(this);
	}
	
	
	public void removeUnit(Unit unit) {
		units.remove(unit);
	}
	
	
	public void modulnoteBerechnen() {
		
		// benotetes Modul: Gewichteten Schnitt aus den Unitnoten bilden
		if(isBenotet()) {
		
			double note = 0;
			
			for(Unit u : units) {
				
				// Note nicht berechnen, wenn eine Unit nicht bestanden wurde
				if(!u.isBestanden()) {
					return;
				}
				
				if(u.getGewichtung() > 0) {
					note += u.getNote() * u.getGewichtung() / 100;
				}
				
			}
			
			modulnote = note;
			
			if(modulnote <= 4) {
				bestanden = true;
			}
		}
		
		// unbenotetes Modul: Units m�ssen nur bestanden sein
		else {
			for(Unit u : units) {
				if(!u.isBestanden()) {
					return;
				}
			}
			
			bestanden = true;
		}
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getCredits() {
		return credits;
	}


	public void setCredits(int credits) {
		this.credits = credits;
	}


	public int getSemester() {
		return semester;
	}


	public void setSemester(int semester) {
		this.semester = semester;
	}


	public int getStunden() {
		return stunden;
	}


	public void setStunden(int stunden) {
		this.stunden = stunden;
	}


	public int getWorkload() {
		return workload;
	}


	public void setWorkload(int workload) {
		this.workload = workload;
	}


	public int getPraesenz() {
		return praesenz;
	}


	public void setPraesenz(int praesenz) {
		this.praesenz = praesenz;
	}


	public int getEigenstudium() {
		return eigenstudium;
	}


	public void setEigenstudium(int eigenstudium) {
		this.eigenstudium = eigenstudium;
	}


	public Studienplan getStudienplan() {
		return studienplan;
	}


	public void setStudienplan(Studienplan studienplan) {
		this.studienplan = studienplan;
	}


	public List<Unit> getUnits() {
		return units;
	}


	public void setUnits(List<Unit> units) {
		this.units = units;
	}
	
	public boolean isBestanden() {
		return bestanden;
	}


	public boolean isBenotet() {
		return benotet;
	}


	public void setBenotet(boolean benotet) {
		this.benotet = benotet;
	}
	
	
	public double getModulnote() {
		return modulnote;
	}
	
}
