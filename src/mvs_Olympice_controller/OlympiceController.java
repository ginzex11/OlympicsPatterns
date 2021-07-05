package mvs_Olympice_controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import mvc_Olympice_listener.OlympiceBLEventsListener;
import mvc_Olympice_listener.OlympiceUIEventsListener;
import mvc_Olympice_view.OlympiceJavaFx;
import mvc_Olympics_model.CompetitionDomains;
import mvc_Olympics_model.CompetitionType;
import mvc_Olympics_model.Country;
import mvc_Olympics_model.ManagementSystem;
import mvc_Olympics_model.PersonalAthlete;
import mvc_Olympics_model.Referee;
import mvc_Olympics_model.Stadium;
import mvc_Olympics_model.Team;;

public class OlympiceController implements OlympiceBLEventsListener, OlympiceUIEventsListener {

	private OlympiceJavaFx olympiceView;
	private ManagementSystem olympiceModel;

	public OlympiceController(OlympiceJavaFx view, ManagementSystem model) {
		olympiceView = view;
		olympiceModel = model;

		olympiceView.registerListener(this);
		olympiceModel.registerListener(this);
	}

	@Override
	public void addCompetitionToModel(CompetitionType typeCompetition, CompetitionDomains typeDomain, Referee referee,
			Stadium stadium, String firstPlace, String secondplace, String thirdPlace) {
		if (CompetitionType.SINGLE == typeCompetition) {
			olympiceModel.addSingleCompetition(typeDomain, referee, stadium, firstPlace, secondplace, thirdPlace);
		} else if (CompetitionType.TEAM == typeCompetition) {
			olympiceModel.addTeamCompetition(typeDomain, referee, stadium, firstPlace, secondplace, thirdPlace);
		}
	}

	@Override
	public void addCompetitorToView(String competitor , int achievements) {
		olympiceView.addCompetitorToView(competitor , achievements);
	}

	@Override
	public void addTeamToModel(String country, CompetitionDomains domain , String gender) {
		try {
		olympiceModel.addTeam(country, domain ,gender);
		}catch (Exception e) {
			olympiceView.enterNewCountry(e.getMessage());
		}
	}

	@Override
	public void addPersonalAthleteToModel(String name, String countryName, Vector<CompetitionDomains> domain) {
		try {
		olympiceModel.addSingleAthlete(name, countryName, domain);
		}catch (Exception e) {
			olympiceView.enterNewCountry(e.getMessage());
		}
	}

	@Override
	public void addStadiumToModel(int numberOfSeats, String nameStadium, String location) {
		try {
		olympiceModel.addStadium(numberOfSeats, nameStadium, location);
		}catch (Exception e) {
			olympiceView.thisStadiumIsAlreadyOnTheList (e.getMessage());
		}
	}


	public void addStadiumToView(String nameStadium , int numOfSpectators) {
		olympiceView.addStadiumToView(nameStadium , numOfSpectators);
	}

	@Override
	public void addRefereeToModel(String name, String country, CompetitionDomains domain) {
		try {
		olympiceModel.addReferee(name, country, domain);
		}catch (Exception e) {
			olympiceView.theJudgeIsOnTheList(e.getMessage());
		}
	}

	@Override
	public void addAddRefereeToView(String name , int achievements) {
		olympiceView.addRefereeToView(name , achievements);
	}

	@Override
	public void addCountryToModel(String countryName) {
		try {
		olympiceModel.addCountry(countryName);
		} catch (Exception e) {
			olympiceView.countryIsAlreadyOnTheList (e.getMessage());
		}
	}

	
	@Override
	public void addCountryToView(String country) {
		olympiceView.addCosuntryToView(country);
	}

	@Override
	public List<Referee> getAllReferees() {
		return olympiceModel.getReferees();
	}

	@Override
	public List<Stadium> getAllStadiums() {
		return olympiceModel.getStadiums();
	}

	@Override
	public List<String> getAllCountries() {
		ArrayList<String> countriesNames = new ArrayList<String>();
		List<Country> countries = olympiceModel.getCountries();
		for (Country c : countries) {
			countriesNames.add(c.getName());
		}
		return countriesNames;
	}

	@Override
	public List<String> getAllSingleCompetitors() {
		ArrayList<String> athletesNames = new ArrayList<String>();
		List<Country> countries = olympiceModel.getCountries();
		for (Country c : countries) {
			for (PersonalAthlete pa : c.getAthletes()) {
				athletesNames.add(pa.getName());
			}
		}
		return athletesNames;
	}

	@Override
	public List<String> getAllTeamCompetitors() {
		ArrayList<String> athletesNames = new ArrayList<String>();
		List<Country> countries = olympiceModel.getCountries();
		for (Country c : countries) {
			for (Team team : c.getTeams()) {
				athletesNames.add(team.getCountry());
			}
		}
		return athletesNames;
	}

	@Override
	public List<String> getAllStadiumsNames() {
		ArrayList<String> stadiumNames = new ArrayList<String>();
		List<Stadium> stadiums = getAllStadiums();
		for (Stadium s : stadiums) {
			stadiumNames.add(s.getNameStadium());
		}
		return stadiumNames;
	}

	@Override
	public List<String> getAllRefereesNames() {
		ArrayList<String> refereesNames = new ArrayList<String>();
		List<Referee> referees = getAllReferees();
		for (Referee r : referees) {
			refereesNames.add(r.getName());
		}
		return refereesNames;
	}
	
    @Override	
	public void finishAndWinningCalculation() {
    	olympiceModel.winnersOfTheOlympics();
	}
    
    @Override
    public void addWinnersOfTheCompetitionToView(String FirstPlaceInTheOlympics, String SecondPlaceInTheOlympics,
			String ThirdPlaceInTheOlympics) {
    	olympiceView.addWinnersOfTheCompetitionToView(FirstPlaceInTheOlympics,SecondPlaceInTheOlympics,
				ThirdPlaceInTheOlympics);
    }

}