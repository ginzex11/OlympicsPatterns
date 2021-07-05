package mvc_Olympics_model;

public abstract class ParticipantOlympics {

	public String name;
	public String country;
	public Payment p;
	public int numOfEquipment;
	
	public abstract int CalculationOfEquipment ();
	
	public void equipmentCounter () {
		numOfEquipment = 2 + CalculationOfEquipment();
	}
	
	public ParticipantOlympics (String name, String country) {
		this.name = name;
		this.country = country;
	}
	public String getName() {
		return name;
	}

	public String getCountry() {
		return country;
	}
	
	public boolean isPayment () {
		return p.payment();
	}
	
	public void setPayment(Payment pay) {
		p = pay;
	}	
}
