package mvc_Olympics_model;

public class CountryAdapter implements InAthlete{

	public Country country;
	
	public CountryAdapter (Country country) {
		this.country = country;
	}
	
	@Override
	public boolean findAthlete (String name) {
		for(Team  t : country.getTeams()) {
			if(t.findAthlete(name)){
				return true;
			}
		}
		return false;	
	}
}
