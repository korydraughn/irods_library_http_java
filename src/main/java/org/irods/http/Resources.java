package org.irods.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Path;
import java.util.Optional;

public class Resources {

	public static HttpResponse<String> create(ExecutionContext ctx, String bearerToken,
			ResourceDescription resourceDesp) throws IOException, InterruptedException {
		final var args = String.format("op=create&name=%s&type=%s&host=%s&vault-path=%s", resourceDesp.name,
				resourceDesp.type, resourceDesp.host.orElse(""), resourceDesp.vaultPath.orElse(Path.of("")));

		final var request = HttpRequest.newBuilder().version(Version.HTTP_1_1)
				.uri(URI.create(ctx.httpApiURL + "/resources")).POST(BodyPublishers.ofString(args))
				.header("Authorization", "Bearer " + bearerToken).build();

		return ctx.httpClient.send(request, BodyHandlers.ofString());
	}

	public static HttpResponse<String> remove(ExecutionContext ctx, String bearerToken, String resourceName)
			throws IOException, InterruptedException {
		final var args = String.format("op=remove&name=%s", resourceName);

		final var request = HttpRequest.newBuilder().version(Version.HTTP_1_1)
				.uri(URI.create(ctx.httpApiURL + "/resources")).POST(BodyPublishers.ofString(args))
				.header("Authorization", "Bearer " + bearerToken).build();

		return ctx.httpClient.send(request, BodyHandlers.ofString());
	}

	public static HttpResponse<String> modify(ExecutionContext ctx, String bearerToken, String name, String property,
			String newValue) throws IOException, InterruptedException {
		final var args = String.format("op=modify&name=%s&property=%s&value=%s", name, property, newValue);

		final var request = HttpRequest.newBuilder().version(Version.HTTP_1_1)
				.uri(URI.create(ctx.httpApiURL + "/resources")).POST(BodyPublishers.ofString(args))
				.header("Authorization", "Bearer " + bearerToken).build();

		return ctx.httpClient.send(request, BodyHandlers.ofString());
	}

	public static HttpResponse<String> addChild(ExecutionContext ctx, String bearerToken, String parentName,
			String childName, Optional<String> context) throws IOException, InterruptedException {
		final var args = String.format("op=add_child&parent-name=%s&child-name=%s&context=%s", parentName, childName,
				context.orElse(""));

		final var request = HttpRequest.newBuilder().version(Version.HTTP_1_1)
				.uri(URI.create(ctx.httpApiURL + "/resources")).POST(BodyPublishers.ofString(args))
				.header("Authorization", "Bearer " + bearerToken).build();

		return ctx.httpClient.send(request, BodyHandlers.ofString());
	}

	public static HttpResponse<String> removeChild(ExecutionContext ctx, String bearerToken, String parentName,
			String childName) throws IOException, InterruptedException {
		final var args = String.format("op=remove_child&parent-name=%s&child-name=%s", parentName, childName);

		final var request = HttpRequest.newBuilder().version(Version.HTTP_1_1)
				.uri(URI.create(ctx.httpApiURL + "/resources")).POST(BodyPublishers.ofString(args))
				.header("Authorization", "Bearer " + bearerToken).build();

		return ctx.httpClient.send(request, BodyHandlers.ofString());
	}

	public static HttpResponse<String> rebalance(ExecutionContext ctx, String bearerToken, String name)
			throws IOException, InterruptedException {
		final var args = String.format("op=rebalance&name=%s", name);

		final var request = HttpRequest.newBuilder().version(Version.HTTP_1_1)
				.uri(URI.create(ctx.httpApiURL + "/resources")).POST(BodyPublishers.ofString(args))
				.header("Authorization", "Bearer " + bearerToken).build();

		return ctx.httpClient.send(request, BodyHandlers.ofString());
	}

	public static HttpResponse<String> stat(ExecutionContext ctx, String bearerToken, String name)
			throws IOException, InterruptedException {
		final var url = String.format("%s/resources?op=stat&name=%s", ctx.httpApiURL, name);

		final var request = HttpRequest.newBuilder().version(Version.HTTP_1_1).uri(URI.create(url)).GET()
				.header("Authorization", "Bearer " + bearerToken).build();

		return ctx.httpClient.send(request, BodyHandlers.ofString());
	}

}
