package mvc_Olympics_model;

import java.util.ArrayList;

public class StadiumSetupController {

	ArrayList<MementoStadium> savedArticles = new ArrayList<MementoStadium>();

	public void addMemento(MementoStadium m) {
		savedArticles.add(m);
	}

	public MementoStadium getMemento(int index) {
		return savedArticles.get(index);
	}
}
