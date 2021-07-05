package mvc_Olympics_model;

import java.util.ArrayList;
import java.util.Vector;

public class Team  implements Comparable<Team>, Competitors , InAthlete {

	private ArrayList<Sportsman> athletes;
	private CompetitionDomains typeDomain;
	private CompetitionType type;
	private String country;
	private ArrayList<Team> teams;
	private String gender;
	private int achievements;
	
	public Team(String country, CompetitionDomains domain , String gender) {
		super();
		this.country = country;
		this.typeDomain = domain;
		this.athletes = new ArrayList<Sportsman>();
        teams = new ArrayList<Team>();
		this.achievements = 20000;
		this.type = CompetitionType.TEAM;
		this.gender = gender;
	}
	
	@Override
	public boolean findAthlete(String name) {
		for (Sportsman s : athletes) {
			if (name == s.name) {
				return true;
			}
		}
		return false;
	}
	
	public void addSmallTeam (Team team) {
		teams.add(team);
	}

	public int getAchievements() {
		return achievements;
	}

	@Override
	public Vector<CompetitionDomains> getTypeDomain() {
		Vector<CompetitionDomains> domains = new Vector<CompetitionDomains>();
		domains.add(this.typeDomain);
		return domains;
	}

	@Override
	public CompetitionType getCompetitionEligible() {
		return this.type;
	}

	public void addAchievements(int achievements) {
		this.achievements += achievements;
	}

	public ArrayList<Sportsman> getAthletes() {
		return athletes;
	}

	public void addAthlete(Sportsman athlete) {
		if (!this.athletes.contains(athlete) && athlete.getTypeDomain().contains(this.typeDomain))
			this.athletes.add(athlete);
	}

	public String getCountry() {
		return country;
	}
	
	public String getGender() {
		return gender;
	}

	@Override
	public int compareTo(Team other) {
		return Integer.compare(this.getAchievements(), other.getAchievements());
	}	
}
class WomenTeam extends Team {
	public WomenTeam(String country, CompetitionDomains domain ,String ShirtColor) {
		super(country , domain ,"women");
	}
}

class MenTeam extends Team {
	public MenTeam(String country, CompetitionDomains domain ,String ShirtColor) {
		super(country , domain ,"Men");
	}
}

