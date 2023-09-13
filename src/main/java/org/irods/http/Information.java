package org.irods.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class Information {

	public static HttpResponse<String> info(ExecutionContext ctx) throws IOException, InterruptedException {
		final var request = HttpRequest.newBuilder().version(Version.HTTP_1_1).uri(URI.create(ctx.httpApiURL + "/info"))
				.GET().build();

		return ctx.httpClient.send(request, BodyHandlers.ofString());
	}

}
