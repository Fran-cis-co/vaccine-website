package cs3220.model;

public class PatientEntry {

	private int id;
	private String name;
	private String vaccine;
	private String firstDose;
	private String secondDose;
	
	public PatientEntry() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVaccine() {
		return vaccine;
	}

	public void setVaccine(String vaccine) {
		this.vaccine = vaccine;
	}

	public String getFirstDose() {
		return firstDose;
	}

	public void setFirstDose(String firstDose) {
		this.firstDose = firstDose;
	}

	public String getSecondDose() {
		return secondDose;
	}

	public void setSecondDose(String secondDose) {
		this.secondDose = secondDose;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
