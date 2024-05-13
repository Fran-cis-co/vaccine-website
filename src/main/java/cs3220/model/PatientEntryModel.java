package cs3220.model;

public class PatientEntryModel {
	private int id;
	private String name;
	private String vaccine;
	private String firstDose;
	private String secondDose;
	private int vaccineDosesRequired;
	private int vaccineDosesLeft;
	
	public PatientEntryModel() {
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

	public int getVaccineDosesRequired() {
		return vaccineDosesRequired;
	}

	public void setVaccineDosesRequired(int vaccineDosesRequired) {
		this.vaccineDosesRequired = vaccineDosesRequired;
	}

	public int getVaccineDosesLeft() {
		return vaccineDosesLeft;
	}

	public void setVaccineDosesLeft(int vaccineDosesLeft) {
		this.vaccineDosesLeft = vaccineDosesLeft;
	}
}
