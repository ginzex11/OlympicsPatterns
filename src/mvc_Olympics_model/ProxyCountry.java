package mvc_Olympics_model;

import java.util.ArrayList;

public class ProxyCountry implements ICountry {
    String countryName;
    Country oldCountry;

    public ProxyCountry(String countryName) {
        this.countryName = countryName;
    }

    public String getName() {
        if (oldCountry == null) {
            oldCountry = new Country(countryName);
        }
        return oldCountry.getName();
    }


    public void addPoints(int num) {
        if (oldCountry == null) {
            oldCountry = new Country(countryName);
        }
        oldCountry.addPoints(num);
    }


    public int getPoints() {
        if (oldCountry == null) {
            oldCountry = new Country(countryName);
        }
        return oldCountry.getPoints();
    }


    public ArrayList<PersonalAthlete> getAthletes() {
        if (oldCountry == null) {
            oldCountry = new Country(countryName);
        }
        return oldCountry.getAthletes();
    }

    public void addAthlete(PersonalAthlete athlete) {
        if (oldCountry == null) {
            oldCountry = new Country(countryName);
        }
        oldCountry.addAthlete(athlete);
    }


    public int compareTo(Country other) {
        if (oldCountry == null) {
            oldCountry = new Country(countryName);
        }
        return oldCountry.compareTo(other);
    }

}

