package DataMapper;

public class Student {
	private String firstName, lastName;
	private int wNum;
	private String major;
	private double GPA;
	
	public Student(String firstName, String lastName, int wNum, String major, double GPA) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.wNum = wNum;
		this.major = major;
		this.GPA = GPA;
	}
	
	public void setFirstName(String firstName) throws Exception {
		this.firstName = firstName;
		StudentFileMapper map = StudentFileMapper.getMapper();
		map.update(this);
	}
	
	public void setLastName(String lastName) throws Exception {
		this.lastName = lastName;
		StudentFileMapper map = StudentFileMapper.getMapper();
		map.update(this);
	}
	
	public void setWNum(int wNum){
		this.wNum = wNum;
	}
	
	public void setMajor(String major) throws Exception {
		this.major = major;
		StudentFileMapper map = StudentFileMapper.getMapper();
		map.update(this);
	}
	
	public void setGPA(double GPA) throws Exception {
		this.GPA = GPA;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public int getWNum() {
		return wNum;
	}
	
	public String getMajor() {
		return major;
	}
	
	public double getGPA() {
		return GPA;
	}
	
	public boolean WNumIsPrime() {
		for(int i = 2; i <= wNum / 2; i++) {
			if(wNum % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName + " W#" + wNum + ", Major: " + major + ", GPA: " + GPA;
	}
}