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
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj != null && obj.getClass() == this.getClass()){			
			Player p = (Player)obj;
			if(this.number == p.getNumber()
			&& this.colorMode.equals(p.getColorMode())){
				result = true;
			}
			
		}		
		return result;
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
