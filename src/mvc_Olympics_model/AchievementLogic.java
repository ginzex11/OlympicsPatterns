package mvc_Olympics_model;

public class AchievementLogic implements OlympicsVisitors{

	@Override
	public int addAchievements(Referee r) {
        char [] c = r.getName().toCharArray();	  
        int counter = 0 ;
	    for (int i = 0 ; i < c.length ; i++) {
		     counter++;
	   }
	    return counter;
	}

	@Override
	public int addAchievements(PersonalAthlete p) {
		char [] c = p.getName().toCharArray();	  
        int counter = 0 ;
	    for (int i = 0 ; i < c.length ; i++) {
		     counter = counter + 5;
	   }
	    return counter;		
	}
}
