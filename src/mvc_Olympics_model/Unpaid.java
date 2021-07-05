package mvc_Olympics_model;

public class Unpaid implements Payment{

	public boolean payment() {
		return false;
	}
}
