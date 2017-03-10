package gameElements;

public class Player {
	private int number;
	private String colorMode;

	public Player(int number) {
		this.number = number;
		this.colorMode = "bw";
	}
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getColorMode() {
		return colorMode;
	}

	public void setColorMode(String colorMode) {
		this.colorMode = colorMode;
	}
	
	public String toString(){
		String s = "";
		if(number == 0){
			if(colorMode == "og"){
				s = "green";
			} 
			else if(colorMode == "bw") {				
				s = "white";
			}
		}
		else {
			if(colorMode == "og"){
				s = "orange";
			} 
			else if(colorMode == "bw") {				
				s = "black";
			}			
		}
		return s;		
	}
		
}
