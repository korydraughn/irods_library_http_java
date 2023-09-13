package org.irods.http;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

class AuthenticationEndpoint {

	@Test
	void testBasicAuthentication() {
		final var ctx = ExecutionContext.createDefault("http://localhost:9000/irods-http-api/0.1.0");
        final var resp = assertDoesNotThrow(() -> BasicAuthentication.authenticate(ctx, "rods", "rods"));
        assertFalse(resp.body().isEmpty());
	}

}
