package entity;

public class Employee {
	private String id;
	private String name;
	private byte sex_id;
	private int age;
	private int department_id;
	private int post_id;
	private String address;
	private String phone;
	private String status;
	private String depart_name;
	private String post_name;

	public Employee() {
	}

	public Employee(String id, String name, byte sex_id, int age, int department_id, int post_id, String address,
			String phone, String status) {
		super();
		this.id = id;
		this.name = name;
		this.sex_id = sex_id;
		this.age = age;
		this.department_id = department_id;
		this.post_id = post_id;
		this.address = address;
		this.phone = phone;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getSex_id() {
		return sex_id;
	}

	public void setSex_id(byte sex_id) {
		this.sex_id = sex_id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDepart_name() {
		return depart_name;
	}

	public void setDepart_name(String depart_name) {
		this.depart_name = depart_name;
	}

	public String getPost_name() {
		return post_name;
	}

	public void setPost_name(String post_name) {
		this.post_name = post_name;
	}
}
