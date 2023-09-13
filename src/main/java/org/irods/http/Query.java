package org.irods.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Query {

	public static HttpResponse<String> executeGenQuery(ExecutionContext ctx, String bearerToken, String query,
			Optional<Integer> offset, Optional<Integer> count) throws IOException, InterruptedException {
		final var url = new StringBuilder(ctx.httpApiURL).append("/query?op=execute_genquery&query=").append(query);

		if (offset.isPresent()) {
			url.append("&offset=").append(offset.get());
		}

		if (count.isPresent()) {
			url.append("&count=").append(count.get());
		}

		final var request = HttpRequest.newBuilder().version(Version.HTTP_1_1).uri(URI.create(url.toString())).GET()
				.header("Authorization", "Bearer " + bearerToken).build();

		return ctx.httpClient.send(request, BodyHandlers.ofString());
	}

	public static HttpResponse<String> executeSpecificQuery(ExecutionContext ctx, String bearerToken, String name, List<String> args,
			Optional<String> argsDelimiter, Optional<Integer> offset, Optional<Integer> count)
			throws IOException, InterruptedException {
		final var url = new StringBuilder(ctx.httpApiURL).append("/query?op=execute_specific_query&name=").append(name);

		final var delimiter = argsDelimiter.orElse(",");

		if (!args.isEmpty()) {
			url.append("&args=");
			url.append(args.stream().collect(Collectors.joining(delimiter)));
		}

		url.append("&args-delimiter=").append(delimiter);

		if (offset.isPresent()) {
			url.append("&offset=").append(offset.get());
		}

		if (count.isPresent()) {
			url.append("&count=").append(count.get());
		}

		final var request = HttpRequest.newBuilder().version(Version.HTTP_1_1).uri(URI.create(url.toString())).GET()
				.header("Authorization", "Bearer " + bearerToken).build();

		return ctx.httpClient.send(request, BodyHandlers.ofString());
	}

}
