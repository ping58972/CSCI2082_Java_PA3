
public class Car <T> {
	
	private int year;
	private String make;
	public Car <T>(int year, String make) {
		super();
		this.year = year;
		this.make = make;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public <> getMake(> {
		return make;
	
		this.make = make;
	
	

	public boolean equals (Car obj) { 
		
		return (obj.make.equals(make) && obj.year == year);
	}
	@Override
	public String toString() {
		return "Car [year=" + year + ", make=" + make + "]";
	}
	
	

}
