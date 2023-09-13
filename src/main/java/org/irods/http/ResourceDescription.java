package org.irods.http;

import java.nio.file.Path;
import java.util.Optional;

public class ResourceDescription {
	
	public String name;
	public String type;
	public Optional<String> host;
	public Optional<Path> vaultPath;
	public Optional<String> context;
	
	public static ResourceDescription ofUnixfilesystemResource(String name, String host, Path vaultPath, Optional<String> context) {
		var rd = new ResourceDescription();
		rd.name = name;
		rd.type = "unixfilesystem";
		rd.host = Optional.of(host);
		rd.vaultPath = Optional.of(vaultPath);
		rd.context = context;
		return rd;
	}

	public static ResourceDescription ofReplicationResource(String name, Optional<String> context) {
		var rd = new ResourceDescription();
		rd.name = name;
		rd.type = "replication";
		rd.context = context;
		return rd;
	}

	public static ResourceDescription ofPassthruResource(String name, Optional<String> context) {
		var rd = new ResourceDescription();
		rd.name = name;
		rd.type = "passthru";
		rd.context = context;
		return rd;
	}

	public static ResourceDescription ofCompoundResource(String name, Optional<String> context) {
		var rd = new ResourceDescription();
		rd.name = name;
		rd.type = "compound";
		rd.context = context;
		return rd;
	}

	public static ResourceDescription ofS3Resource(String name, String host, Optional<String> context) {
		var rd = new ResourceDescription();
		rd.name = name;
		rd.type = "s3";
		rd.host = Optional.of(host);
		rd.context = context;
		return rd;
	}

	public static ResourceDescription ofDeferredResource(String name) {
		var rd = new ResourceDescription();
		rd.name = name;
		rd.type = "deferred";
		return rd;
	}

}
