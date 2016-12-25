package action;

import java.io.Serializable;

public class MenuItem implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String url;
	private String name;

	public MenuItem(String url, String name) {
		this.url = url;
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public String getName() {
		return name;
	}
}
