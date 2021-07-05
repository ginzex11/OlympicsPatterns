package mvc_Olympics_model;


import java.util.Vector;

public interface Competitors {
	public Vector<CompetitionDomains> getTypeDomain();
	public CompetitionType getCompetitionEligible();
}
