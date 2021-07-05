package mvc_Olympics_model;


import java.util.Vector;

public class PersonalAthlete extends Sportsman implements Comparable <PersonalAthlete> , VisitableOlympics{
	private int achievements;
	
	public PersonalAthlete(String name, String country, Vector<CompetitionDomains> typeDomain, AchievementLogic visitor) {
		super(name, country, typeDomain, CompetitionType.SINGLE);
		setAchievements(visitor);
	}

	public int getAchievements() {
		return achievements;
	}
		
	public void addAchievements(int achievements) {
		this.achievements += achievements;
	}

	public int compareTo(PersonalAthlete other) {
        return Integer.compare(this.getAchievements(),other.getAchievements());
	}
	
	public void setAchievements (OlympicsVisitors visitor) {
		achievements = visitor.addAchievements(this);
	}	
}
