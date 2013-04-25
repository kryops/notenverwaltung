package de.dhbw.swe.notenverwaltung;

import java.util.ArrayList;
import java.util.List;

public class Studienplan {
	
	private String name;
	private String abschluss;
	
	private Student student;
	private List<Modul> module;
	
	
	public Studienplan(String name, String abschluss) {
		this.name = name;
		this.abschluss = abschluss;
		this.module = new ArrayList<Modul>();
	}
	
	public void addModul(Modul modul) {
		module.add(modul);
		modul.setStudienplan(this);
	}
	
	public void removeModul(Modul modul) {
		module.remove(modul);
	}
	
	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAbschluss() {
		return abschluss;
	}


	public void setAbschluss(String abschluss) {
		this.abschluss = abschluss;
	}


	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	

	public List<Modul> getModule() {
		return module;
	}
	
	
	public void setModule(List<Modul> module) {
		this.module = module;
	}

	
}
