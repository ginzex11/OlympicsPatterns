package mvc_Olympics_model;

public class Referee extends ParticipantOlympics implements VisitableOlympics {

	private CompetitionDomains domain;
	private int achievements;
	
	public Referee(String name, String country, CompetitionDomains domain ,AchievementLogic visitor) {
		super(name, country);
		this.domain = domain;
		p = new Paid();
		setAchievements(visitor);
	}

	public int getAchievements() {
		return achievements;
	}
	public CompetitionDomains getDomain() {
		return domain;
	}
	
	public void setAchievements (OlympicsVisitors visitor) {
		achievements =  visitor.addAchievements(this);
	}
	
    public int CalculationOfEquipment () {
    	int numOfEquipment = 0;
    	return numOfEquipment = numOfEquipment + 1;		
	}	
}





