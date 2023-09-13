package org.irods.http;

public enum Permission {
	OWN("own"),
	WRITE("modify_object"),
	READ("read_object"),
	NULL("null");

	private String name;

	Permission(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
