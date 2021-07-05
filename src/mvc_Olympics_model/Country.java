package mvc_Olympics_model;


import java.util.ArrayList;

public class Country implements Comparable<Country> {

	private ArrayList<PersonalAthlete> athletes;
	private ArrayList<Team> teams;
	private String name;
	private int points;

	public Country(String name) {
		this.name = name;
		this.athletes = new ArrayList<PersonalAthlete>();
		this.teams = new ArrayList<Team>();
		this.points = 0;
	}

	public String getName() {
		return name;
	}

	public void addPoints(int num) {
        System.out.println(name);
		System.out.println(points);
		this.points = points+ num;
		System.out.println(points);
	}

	public int getPoints() {
		return this.points;
	}

	public ArrayList<PersonalAthlete> getAthletes() {
		return this.athletes;
	}

	public void addAthlete(PersonalAthlete athlete) {
		if (!this.getAthletes().contains(athlete))
			this.getAthletes().add(athlete);
	}
	public ArrayList<Team> getTeams() {
		return this.teams;
	}

	public void addTeam(Team team) {
		if (!this.getTeams().contains(team))
			this.getTeams().add(team);
	}

	@Override
	public int compareTo(Country other) {
		return Integer.compare(this.getPoints(), other.getPoints());
	}
}
