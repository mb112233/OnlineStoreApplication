package EntititesLayer;

public class Client {
	
	private int id;
    private String name;
    private String address;
    private String email;
    private int age;
    
	public Client( int id,String name, String address, String email, int age) {
		this.id=id;
		this.name = name;
		this.address = address;
		this.email = email;
		this.age = age;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String adress) {
		this.address = adress;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String toString() {
		return "Client [id="+this.id+" name="+this.name+" address="+this.address+" email="+this.email+" age="+this.age+"]";
	}
}
