package cs3220.model;

public class VaccineListEntry{
	private int id;
	private String vaccineName;
	private int dosesRequired;
	private int daysBetweenDoses;
	private int dosesRecieved;
	private int dosesLeft;
	
	public VaccineListEntry() {
		
	}


	public String getVaccineName() {
		return vaccineName;
	}

	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getDosesRequired() {
		return dosesRequired;
	}


	public void setDosesRequired(int dosesRequired) {
		this.dosesRequired = dosesRequired;
	}


	public int getDaysBetweenDoses() {
		return daysBetweenDoses;
	}


	public void setDaysBetweenDoses(int daysBetweenDoses) {
		this.daysBetweenDoses = daysBetweenDoses;
	}


	public int getDosesRecieved() {
		return dosesRecieved;
	}


	public void setDosesRecieved(int dosesRecieved) {
		this.dosesRecieved = dosesRecieved;
	}


	public int getDosesLeft() {
		return dosesLeft;
	}


	public void setDosesLeft(int dosesLeft) {
		this.dosesLeft = dosesLeft;
	}


	
}
