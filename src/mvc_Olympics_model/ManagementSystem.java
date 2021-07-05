package mvc_Olympics_model;



import java.util.ArrayList;
import java.util.Vector;

import mvc_Olympice_listener.OlympiceBLEventsListener;
import mvc_Olympics_model.Stadium.StadiumBuilder;

public class ManagementSystem {
	private Vector<OlympiceBLEventsListener> listeners;
	private ArrayList<Competition<Team>> teamCompetitions;
	private ArrayList<Competition<PersonalAthlete>> singleCompetitions;
	private ArrayList<Country> countries;
	private ArrayList<Stadium> stadiums;
	private ArrayList<Referee> referees;

	public ManagementSystem() {
		this.listeners = new Vector<OlympiceBLEventsListener>();
		this.teamCompetitions = new ArrayList<Competition<Team>>();
		this.singleCompetitions = new ArrayList<Competition<PersonalAthlete>>();
		this.countries = new ArrayList<Country>();
		this.stadiums = new ArrayList<Stadium>();
		this.referees = new ArrayList<Referee>();
	}

	public Vector<OlympiceBLEventsListener> getListeners() {
		return listeners;
	}

	public ArrayList<Competition<Team>> getGroupCompetitions() {
		return teamCompetitions;
	}

	public void addTeamCompetition(CompetitionDomains typeDomain, Referee referee, Stadium stadium, String firstPlace,
			String secondplace, String thirdPlace) {
		this.teamCompetitions.add(new Competition<Team>(typeDomain, CompetitionType.TEAM, referee, stadium, firstPlace,
				secondplace, thirdPlace));
		addingPointsWinnersCompetition(teamCompetitions.get(teamCompetitions.size() - 1).getWinnings());
	}

	public void addTeam(String countryName, CompetitionDomains domain , String gender) throws Exception{
		Country country = findCountryByName(countryName);
		if (country == null) {
			throw new Exception("This country is not in the list of countries, please enter a new country***");
		}
		Team team = new Team(countryName, domain, gender);
		country.addTeam(team);
		fireAddTeamEvent(team);
	}
	
	public void addReferee(String name, String country, CompetitionDomains domain) throws Exception{
		AchievementLogic al = new AchievementLogic();
		Referee referee = findRefereeByNameAndCountry(name, country);
		if (referee != null) {
			throw new Exception("This judge is already on the list, insert another judge***");
		}
		referee = new Referee(name, country, domain, al);
		this.referees.add(referee);
		fireAddRefereeEvent(referee);
	}
	
	public void addSingleAthlete(String name, String countryName, Vector<CompetitionDomains> domain) throws Exception{
		AchievementLogic al = new AchievementLogic();
		Country country = findCountryByName(countryName);
		if (country == null) {
			throw new Exception("This country is not in the list of countries, please enter a new country***");
		}
		PersonalAthlete pa = new PersonalAthlete(name, countryName, domain , al);
		country.addAthlete(pa);
		fireAddSingleAthleteEvent(pa);
	}
	
	private void fireAddSingleAthleteEvent(PersonalAthlete pa) {
		for (OlympiceBLEventsListener l : this.listeners) {
			l.addCompetitorToView(pa.getName() , pa.getAchievements());
		}
	}
	
	private void fireAddRefereeEvent(Referee referee) {
		for (OlympiceBLEventsListener l : this.listeners) {
			l.addAddRefereeToView(referee.getName() , referee.getAchievements());
		}
	}


	private void fireAddTeamEvent(Team team) {
		for (OlympiceBLEventsListener l : this.listeners) {
			l.addCompetitorToView(team.getCountry() , team.getAchievements());
			;
		}
	}
	
	public boolean findPersonal (String name , Country country) {
		CountryAdapter CountryToteam = new CountryAdapter(country);
		if (CountryToteam.findAthlete(name)) {
			return true;
		}		
		return false;
	}
	public Country findCountryByName(String name) {
		for (Country country : this.countries) {
			if (country.getName().equals(name)) {
				return country;
			}
		}
		return null;
	}

	public ArrayList<Competition<PersonalAthlete>> getSingleCompetitions() {
		return singleCompetitions;
	}

	public void addSingleCompetition(CompetitionDomains typeDomain, Referee referee, Stadium stadium, String firstPlace,
			String secondplace, String thirdPlace) {
		this.singleCompetitions.add(new Competition<PersonalAthlete>(typeDomain, CompetitionType.SINGLE, referee,
				stadium, firstPlace, secondplace, thirdPlace));
		addingPointsWinnersCompetition(singleCompetitions.get(singleCompetitions.size() - 1).getWinnings());
	}

	public ArrayList<Country> getCountries() {
		return countries;
	}

	public void addCountry(String countryName) throws Exception{
		Country country = findCountryByName(countryName);
		if (country != null) {
			throw new Exception("This country is already on the list, please enter the name of another country***");
		}
		country = new Country(countryName);
		this.countries.add(country);
		fireAddCountryEvent(country);
	}

	private void fireAddCountryEvent(Country country) {
		for (OlympiceBLEventsListener l : this.listeners) {
			l.addCountryToView(country.getName());
		}
	}

	private void fireAddStadiumEvent(Stadium stadium) {
		for (OlympiceBLEventsListener l : this.listeners) {
			l.addStadiumToView(stadium.getNameStadium() , stadium.getState().getNumOfSeats());
		}
	}

	public ArrayList<Stadium> getStadiums() {
		return stadiums;
	}

	public void addStadium(int numberOfSeats, String nameStadium, String location) throws Exception{
		Stadium stadium = findStadiumByName(nameStadium);
		if (stadium != null) {
			throw new Exception("This stadium is on the list, please enter the details of another stadium***");
		}
		
		StadiumBuilder StadiumBuilder1 = new StadiumBuilder();
		StadiumBuilder1.addNumberOfSeats(numberOfSeats);
		StadiumBuilder1.addLocation(location);
		StadiumBuilder1.addNameStadium(nameStadium);
		StadiumBuilder1.builder();	
		
		this.stadiums.add(stadium);
		fireAddStadiumEvent(stadium);
	}
	
	public Stadium findStadiumByName(String name) {
		for (Stadium stadium : this.stadiums) {
			if (stadium.getNameStadium().equals(name)) {
				return stadium;
			}
		}
		return null;
	}

	public ArrayList<Referee> getReferees() {
		return referees;
	}

	public Referee findRefereeByNameAndCountry(String name, String countryName) {
		for (Referee referee : this.referees) {
			if (referee.getName().equals(name) && referee.getCountry().equals(countryName)) {
				return referee;
			}
		}
		return null;
	}

	public void registerListener(OlympiceBLEventsListener newListener) {
		listeners.add(newListener);
	}

	public void addingPointsWinnersCompetition(ArrayList<String> Winners) {
		for (int z = 0; z < Winners.size(); z++) {
			for (Country countries : countries) {
				if (countries.getName().equals(Winners.get(z))) {
					countries.addPoints(z + 1);
				}
			}

		}
	}

	public void winnersOfTheOlympics() {

		for (int i = countries.size() - 1; i > 0; i--) {
			for (int j = 0; j < i ; j++) {
				if (countries.get(j).getPoints() > countries.get(j + 1).getPoints()) {
					Country temp = countries.get(j);
					countries.set(j, countries.get(j + 1));
					countries.set(j + 1, temp);
				}
			}
		}

		System.out.println(countries.get(0).getName());
		fireWinnersOfTheCompetition(countries.get(countries.size() - 1).getName(), countries.get(countries.size() - 2).getName(), countries.get(countries.size() - 3).getName());
	}

	private void fireWinnersOfTheCompetition(String FirstPlaceInTheOlympics, String SecondPlaceInTheOlympics,
			String ThirdPlaceInTheOlympics) {
		for (OlympiceBLEventsListener l : this.listeners) {
			l.addWinnersOfTheCompetitionToView(FirstPlaceInTheOlympics, SecondPlaceInTheOlympics,
					ThirdPlaceInTheOlympics);
			;
		}
	}
}
