package org.irods.http;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

class InformationEndpoint {

	@Test
	void testGetGeneralServerInformation() {
		final var ctx = ExecutionContext.createDefault("http://localhost:9000/irods-http-api/0.1.0");
        final var resp = assertDoesNotThrow(() -> Information.info(ctx));
        System.out.println("info: " + resp.body());
        assertFalse(resp.body().isEmpty());
	}

}
