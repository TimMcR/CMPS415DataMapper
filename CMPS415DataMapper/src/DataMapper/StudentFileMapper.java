package DataMapper;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class StudentFileMapper {
	
	private static final String FilePath = "files/input.csv";
	private List<List<String>> records;

	private static StudentFileMapper theMapper;
	
	private StudentFileMapper() throws Exception {
		records = new ArrayList<>();
		initDB();
	}
	
	public static StudentFileMapper getMapper() throws Exception {
		if(theMapper == null) {
			theMapper = new StudentFileMapper();
		}
		return theMapper;
	}
	
	
	private void initDB() throws Exception {
		BufferedReader csvReader = new BufferedReader(new FileReader(FilePath));
		String row;
		//Adds everything included the header to the record
		while ((row = csvReader.readLine()) != null) {
		    String[] data = row.split(",");
	    	records.add(Arrays.asList(data));
		}
		csvReader.close();
	}
	
	private void loadDB() throws Exception {
		FileWriter csvWriter = new FileWriter(FilePath);
		for(List<String> rowData : records) {
			csvWriter.append(String.join(",", rowData));
		    csvWriter.append("\n");
		}
		csvWriter.flush();
		csvWriter.close();
	}
		
	private int findLocation(int id) {
		boolean header = true;
		for(int i = 0; i < records.size(); i++) {
			if(header) {
				header = false;
				continue; 
			}
			List<String> line = records.get(i);
			int wNum = Integer.parseInt(line.get(2));
			if(wNum == id) {
				return i;
			}
		}
		return 0;
	}
	
	public void printDB() {
		System.out.println(records);
	}
	
	//FUID
	public Student find(int id) {
		//Check if the wNum is equal to the given id
		//If it is then finish creating the student object
		//Otherwise forget the current student and move on
		boolean header = true;
		for(List<String> line : records) {
			if(header) {
				header = false;
				continue;
			}
			int wNum = Integer.parseInt(line.get(2));
			if(wNum == id) {
				double GPA = Double.parseDouble(line.get(4));
				String firstName = line.get(0);
				String lastName = line.get(1);
				String major = line.get(3);
				
				return new Student(firstName, lastName, wNum, major, GPA);
			}
		}
		return null;
	}
	
	public void update(Student student) throws Exception {
		int studentLocation = findLocation(student.getWNum());
		if(studentLocation != 0) {
			records.get(studentLocation).set(4, student.getGPA() + "");
			records.get(studentLocation).set(0, student.getFirstName());
			records.get(studentLocation).set(1, student.getLastName());
			records.get(studentLocation).set(3, student.getMajor());
			loadDB();
		}
	}
	
	public void insert(Student student) throws Exception {
		//If the student already exists in the database, then we do not insert it
		int index = findLocation(student.getWNum()); 
		if(index != 0)
			return;
		String[] StudentToStringArray = {
		student.getFirstName(),
		student.getLastName(),
		student.getWNum() + "",
		student.getMajor(),
		student.getGPA() + ""
		};
		records.add(Arrays.asList(StudentToStringArray));
		loadDB();
	}
	
	public void delete(Student student) throws Exception {
		//If the student does not exist in the database, then we do not need to delete it
		int index = findLocation(student.getWNum()); 
		if(index == 0)
			return;
		records.remove(index);
		loadDB();
	}
}