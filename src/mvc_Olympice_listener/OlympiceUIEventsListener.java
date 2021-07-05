package mvc_Olympice_listener;



import java.util.List;
import java.util.Vector;

import mvc_Olympics_model.CompetitionDomains;
import mvc_Olympics_model.CompetitionType;
import mvc_Olympics_model.Referee;
import mvc_Olympics_model.Stadium;

public interface OlympiceUIEventsListener {
	public void addCompetitionToModel(CompetitionType typeCompetition, CompetitionDomains typeDomain, Referee referee,
			Stadium stadium , String firstPlace ,String secondplace, String thirdPlace);

	public void addTeamToModel(String country, CompetitionDomains domain , String gender);

	public void addPersonalAthleteToModel(String name, String countryName, Vector<CompetitionDomains> domain);

	public void addStadiumToModel(int numberOfSeats, String nameStadium, String location);

	public void addRefereeToModel(String name, String country, CompetitionDomains domain);

	public void addCountryToModel(String countryName);

	public void finishAndWinningCalculation();
	
	public List<Referee> getAllReferees();
	
	public List<Stadium> getAllStadiums();

	public List<String> getAllCountries();

	public List<String> getAllSingleCompetitors();

	public List<String> getAllTeamCompetitors();

	public List<String> getAllStadiumsNames();

	public List<String> getAllRefereesNames();

}
