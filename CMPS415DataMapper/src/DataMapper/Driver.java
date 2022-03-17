package DataMapper;

// Contributors:
// Timothy McReynolds
// Kenneth Cole
// Kevin Martin(Jr. Jr.)

public class Driver {
	
	public static void main(String[] args) throws Exception {
		StudentFileMapper map = StudentFileMapper.getMapper();
		Student s = map.find(957632);
		s.setFirstName("Bobby");
		s.setLastName("Jones");
		s.setGPA(4.0);
		s.setMajor("Comp Sci");
		Student mark = new Student("Mark", "Wahlberg", 0700142, "Acting", 1.0);
		map.insert(mark);
		map.printDB();		
		map.delete(mark);
		map.printDB();
	}
}
