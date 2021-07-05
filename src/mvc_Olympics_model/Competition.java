package mvc_Olympics_model;



import java.util.ArrayList;
import java.util.Vector;

public class Competition <T extends Competitors> {
	private CompetitionDomains typeDomain;
	private CompetitionType typeCompatitions;
	private Referee referee;
	private Stadium stadium;
	private Vector<T> participants;
	private ArrayList<String> winningPlaces;

	public Competition(CompetitionDomains typeDomain, CompetitionType typeCompatitions, Referee referee,
			Stadium stadium , String firstPlace ,String secondplace, String thirdPlace) {
		super();
		this.typeDomain = typeDomain;
		this.typeCompatitions = typeCompatitions;
		this.referee = referee;
		this.stadium = stadium;
		this.participants = new Vector<T>();
		this.winningPlaces = new ArrayList<String>();
		winningPlaces.add(thirdPlace);
		winningPlaces.add(secondplace);
		winningPlaces.add(firstPlace);
	}

	public ArrayList<String> getWinnings (){
		return winningPlaces;
	}
	public CompetitionDomains getTypeDomain() {
		return typeDomain;
	}
	public CompetitionType getTypeCompatitions() {
		return typeCompatitions;
	}
	public Referee getReferee() {
		return referee;
	}
	public Stadium getStadium() {
		return stadium;
	}

	public Vector<T> getParticipants() {
		return participants;
	}

	public void addParticipant(T participant) {
		if (participant.getCompetitionEligible() == this.getTypeCompatitions()
				&& participant.getTypeDomain().contains(this.getTypeDomain())
				&& !this.participants.contains(participant)) {
			this.participants.add(participant);
		}
	}
}
