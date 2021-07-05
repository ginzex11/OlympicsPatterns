package mvc_Olympics_model;

import java.util.Vector;

public class TeamAthlete extends Sportsman{
	private int achievements;
		
	public TeamAthlete(String name, String country, Vector<CompetitionDomains> typeDomain, CompetitionType type) {
		super(name, country, typeDomain, type);
	}
	
	public int getAchievements() {
		return achievements;
	}
	
	public void addAchievements(int achievements) {
		this.achievements += achievements;
	}
	
	
}
