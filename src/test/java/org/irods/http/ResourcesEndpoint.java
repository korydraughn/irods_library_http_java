package org.irods.http;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.nio.file.Path;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ResourcesEndpoint {

	static ExecutionContext ctx;
	static String bearerToken;
	
	@BeforeAll
	static void authenticate() {
		ctx = ExecutionContext.createDefault("http://localhost:9000/irods-http-api/0.1.0");
		bearerToken = assertDoesNotThrow(() -> BasicAuthentication.authenticate(ctx, "rods", "rods").body());
	}

	@Test
	void testCreateAndRemoveUnixfilesystemResource() {
		var ufsrd = ResourceDescription.ofUnixfilesystemResource("java_ufs", "localhost", Path.of("/tmp/java_ufs_vault"), Optional.empty());
		var result = assertDoesNotThrow(() -> Resources.create(ctx, bearerToken, ufsrd));
		System.out.println("created resource: " + result);

		result = assertDoesNotThrow(() -> Resources.remove(ctx, bearerToken, ufsrd.name));
		System.out.println("removed resource: " + result);
	}

	@Test
	void testStatAResource() {
		var result = assertDoesNotThrow(() -> Resources.stat(ctx, bearerToken, "demoResc"));
		System.out.println("result: " + result);
	}

}
