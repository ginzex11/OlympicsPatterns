package mvc_Olympics_model;

public interface Clothing {
	public int getPrice ();
}

class Shirt implements Clothing {	
	private boolean hasButtons;
	private int size;
	private int length;
	
	public Shirt (int size , boolean hasButtons , int length) {
		this.size = size;
		this.hasButtons = hasButtons;
		this.length = length;
	}
	
	public boolean getHasButtons () {
		return hasButtons;
	}
	
	@Override
	public int getPrice () {
		return length * size;
	}
}

class pants implements Clothing {
	private boolean hasPockets;
	private int size;
	private int width;
	
	public pants (int size , boolean hasPockets , int width) {
		this.size = size;
		this.hasPockets = hasPockets;
		this.width = width;
	}
	
	public boolean getHasPockets () {
		return hasPockets;
	}
	
	@Override
	public int getPrice () {
		return width * size;
	}
}

class ColorClothing implements Clothing {
	private Clothing clothing;
	private String Color;
	
	
	public ColorClothing (Clothing clothing, String Color) {
		this.Color = Color;
		this.clothing = clothing;
	}
	
	public String getColor () {
		return Color;
	}

	@Override
	public int getPrice() {
		return 10 + clothing.getPrice();
	}	
}


