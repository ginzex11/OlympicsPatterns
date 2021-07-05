package mvc_Olympics_model;

import java.util.Vector;

public abstract class Sportsman extends ParticipantOlympics implements Competitors {
		
	private Vector<CompetitionDomains> typeDomains;
	private CompetitionType type;
	
	public Sportsman(String name, String country, Vector<CompetitionDomains> typeDomain, CompetitionType type) {
		super(name, country);
		this.typeDomains = typeDomain;
		this.type = type;
		p = new Unpaid();
	}
	
	public int CalculationOfEquipment () {
		int numOfEquipment = 0;
		if (typeDomains.get(0) == CompetitionDomains.RUNNER ) {
			numOfEquipment = numOfEquipment + 1;
		}
		
		else if (typeDomains.get(0) == CompetitionDomains.HIGH_JUMP ){
			numOfEquipment = numOfEquipment + 3;
		}
	    else if (typeDomains.size() == 2){
	    	numOfEquipment = numOfEquipment + 4;
		}
		return numOfEquipment;
	}
	
	@Override
	public Vector<CompetitionDomains> getTypeDomain() {
		return this.typeDomains;
	}
	
	@Override
	public CompetitionType getCompetitionEligible() {
		return this.type;
	}

	public void addTypeDomain(CompetitionDomains typeDomain) {
		if (!this.typeDomains.contains(typeDomain))
			this.typeDomains.add(typeDomain);
	}
}
