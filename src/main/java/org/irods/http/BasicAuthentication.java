package org.irods.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Base64;

public class BasicAuthentication {

	public static HttpResponse<String> authenticate(ExecutionContext ctx, String username, String password)
			throws IOException, InterruptedException {
		final var creds = Base64.getUrlEncoder().encodeToString(String.format("%s:%s", username, password).getBytes());

		final var request = HttpRequest.newBuilder().version(Version.HTTP_1_1)
				.uri(URI.create(ctx.httpApiURL + "/authenticate")).POST(HttpRequest.BodyPublishers.noBody())
				.header("Authorization", "Basic " + creds).build();

		return ctx.httpClient.send(request, BodyHandlers.ofString());
	}

}
