package org.irods.http;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CollectionsEndpoint {
	
	static ExecutionContext ctx;
	static String bearerToken;
	
	@BeforeAll
	static void authenticate() throws IOException, InterruptedException {
		ctx = ExecutionContext.createDefault("http://localhost:9000/irods-http-api/0.1.0");
		bearerToken = BasicAuthentication.authenticate(ctx, "rods", "rods").body();
	}

	@Test
	void testCreateCollection() throws IOException, InterruptedException {
		var path = Path.of("/tempZone/home/rods/by_java");
		
        var result = Collections.create(ctx, bearerToken, path);
        assertEquals(result.statusCode(), 200);
        assertFalse(result.body().isEmpty());

        result = Collections.stat(ctx, bearerToken, path);
        assertEquals(result.statusCode(), 200);
        assertFalse(result.body().isEmpty());

        var newPath = Path.of("/tempZone/home/rods/by_java.renamed");
        result = Collections.rename(ctx, bearerToken, path, newPath);
        assertEquals(result.statusCode(), 200);
        assertFalse(result.body().isEmpty());

        result = Collections.stat(ctx, bearerToken, newPath);
        assertEquals(result.statusCode(), 200);
        assertFalse(result.body().isEmpty());

        result = Collections.remove(ctx, bearerToken, newPath);
        assertEquals(result.statusCode(), 200);
        assertFalse(result.body().isEmpty());

        result = Collections.stat(ctx, bearerToken, path);
        assertEquals(result.statusCode(), 200);
        assertFalse(result.body().isEmpty());

        result = Collections.stat(ctx, bearerToken, newPath);
        assertEquals(result.statusCode(), 200);
        assertFalse(result.body().isEmpty());
	}

}
