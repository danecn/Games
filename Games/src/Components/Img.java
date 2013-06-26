package Components;

public class Img {

	public String path;
	public boolean evil = false;

	public Img() {
	}

	public Img(String path, boolean evil) {
		this.path = path;
		this.evil = evil;
	}

	public void setEvil(boolean evil) {
		if(evil)
			evil = true;
		else if(!evil)
			evil = false;
	}
}