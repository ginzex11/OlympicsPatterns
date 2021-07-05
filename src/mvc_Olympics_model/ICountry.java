package mvc_Olympics_model;

import java.util.ArrayList;

public interface ICountry {

    public String getName();

    public void addPoints(int num);

    public int getPoints();

    public ArrayList<PersonalAthlete> getAthletes();
    
    public void addAthlete(PersonalAthlete athlete);
    
    public int compareTo(Country other);
}

