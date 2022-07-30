package cybersoft.javabackend.java18.gamedoanso.model;

import java.util.List;

public class NguoiChoi {
	private String username;
	private String password;
	private String name;
	private List<ManChoi> manChoi;
	
	public NguoiChoi(String username, String password, String name) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public List<ManChoi> getManChoi() {
		return manChoi;
	}
}
