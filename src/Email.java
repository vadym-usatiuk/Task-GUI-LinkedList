public class Email {
	
	public static final String EMAIL_EM = "@email.com";
	private String name;
	private String id;

	public Email() {
		name = "";
		id = "";
	}

	public Email(String name, String id) throws EmailException {
		id = id.trim();
		name = name.trim();

		if (name.length() == 0) {
			throw new EmailException("Error: name cannot be blank.");
		}

		else if (id.length() == 0) {
			throw new EmailException("Error: Id cannot be blank.");
		}

		else {
			this.id = id;
			this.name = name;

		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return id + "\t" + name + "\t" + id + EMAIL_EM;
	}
}