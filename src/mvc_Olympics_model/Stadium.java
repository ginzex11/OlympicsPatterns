package mvc_Olympics_model;

public class Stadium {
	private int numberOfSeats;
	private String nameStadium;
	private String location;
	private StadiumState state;

	private Stadium (StadiumBuilder builder , StadiumState state ) {
		this.numberOfSeats = builder.numberOfSeats;
		this.nameStadium = builder.nameStadium;
		this.location = builder.location;
		this.state = state;
	}
	
	public StadiumState getState() {
		return state;
	}
	
	public void setState(StadiumState state) {
		this.state = state;
	}
	
	public int getNumberOfSeats() {
		return numberOfSeats;
	}
	
	public String getNameStadium() {
		return nameStadium;
	}
	public String getLocation() {
		return location;
	}
	

	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public void setNameStadium(String nameStadium) {
		this.nameStadium = nameStadium;
	}
	public void setNumberOfSeatsn(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
	
	public MementoStadium getMemento()
    {
        return new MementoStadium(getNameStadium(), getState() ,getLocation(),getNumberOfSeats());
    }

    public void setMemento(MementoStadium memento)
    {
    	setState(memento.getState());
        setLocation(memento.getLocation());
        setNameStadium(memento.getNameStadium());
        setNumberOfSeatsn(memento.getNumberOfSeats());
    }
    
public static class StadiumBuilder {
		
		private int numberOfSeats;
		private String nameStadium;
		private String location;

		public int addNumberOfSeats(final int numberOfSeats) {
			this.numberOfSeats= numberOfSeats;
			return numberOfSeats;
		}

		public String addNameStadium(String nameStadium) {
			this.nameStadium = nameStadium;
			return nameStadium;
		}

		public String addLocation(String location) {
			this.location = location;
			return location;
		}

		public void builder() {
			// TODO Auto-generated method stub
			
		}
	}
	
}
