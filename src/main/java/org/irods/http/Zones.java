package org.irods.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Path;

public class Zones {

	public static HttpResponse<String> report(ExecutionContext ctx, String bearerToken, Path logicalPath)
			throws IOException, InterruptedException {
		final var request = HttpRequest.newBuilder().version(Version.HTTP_1_1)
				.uri(URI.create(ctx.httpApiURL + "/zones?op=report")).GET()
				.header("Authorization", "Bearer " + bearerToken).build();

		return ctx.httpClient.send(request, BodyHandlers.ofString());
	}

}
