package org.irods.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Path;

public class Collections {

	public static HttpResponse<String> create(ExecutionContext ctx, String bearerToken, Path logicalPath)
			throws IOException, InterruptedException {
		final var args = String.format("op=create&lpath=%s", logicalPath);

		final var request = HttpRequest.newBuilder().version(Version.HTTP_1_1)
				.uri(URI.create(ctx.httpApiURL + "/collections")).POST(BodyPublishers.ofString(args))
				.header("Authorization", "Bearer " + bearerToken).build();

		return ctx.httpClient.send(request, BodyHandlers.ofString());
	}

	public static HttpResponse<String> remove(ExecutionContext ctx, String bearerToken, Path logicalPath)
			throws IOException, InterruptedException {
		final var args = String.format("op=remove&lpath=%s", logicalPath);

		final var request = HttpRequest.newBuilder().version(Version.HTTP_1_1)
				.uri(URI.create(ctx.httpApiURL + "/collections")).POST(BodyPublishers.ofString(args))
				.header("Authorization", "Bearer " + bearerToken).build();

		return ctx.httpClient.send(request, BodyHandlers.ofString());
	}

	public static HttpResponse<String> rename(ExecutionContext ctx, String bearerToken, Path fromLogicalPath, Path toLogicalPath)
			throws IOException, InterruptedException {
		final var args = String.format("op=rename&old-lpath=%s&new-lpath=%s", fromLogicalPath, toLogicalPath);

		final var request = HttpRequest.newBuilder().version(Version.HTTP_1_1)
				.uri(URI.create(ctx.httpApiURL + "/collections")).POST(BodyPublishers.ofString(args))
				.header("Authorization", "Bearer " + bearerToken).build();

		return ctx.httpClient.send(request, BodyHandlers.ofString());
	}

	public static HttpResponse<String> stat(ExecutionContext ctx, String bearerToken, Path logicalPath)
			throws IOException, InterruptedException {
		final var url = String.format("%s/collections?op=stat&lpath=%s", ctx.httpApiURL, logicalPath);

		final var request = HttpRequest.newBuilder().version(Version.HTTP_1_1).uri(URI.create(url)).GET()
				.header("Authorization", "Bearer " + bearerToken).build();

		return ctx.httpClient.send(request, BodyHandlers.ofString());
	}

	public static HttpResponse<String> list(ExecutionContext ctx, String bearerToken, Path logicalPath, boolean recurse)
			throws IOException, InterruptedException {
		final var url = String.format("%s/collections?op=list&lpath=%s&recurse=%d", ctx.httpApiURL, logicalPath,
				(recurse ? 1 : 0));

		final var request = HttpRequest.newBuilder().version(Version.HTTP_1_1).uri(URI.create(url)).GET()
				.header("Authorization", "Bearer " + bearerToken).build();

		return ctx.httpClient.send(request, BodyHandlers.ofString());
	}

	public static HttpResponse<String> setPermission(ExecutionContext ctx, String bearerToken, Path logicalPath, String entityName,
			Permission perm) throws IOException, InterruptedException {
		final var args = String.format("op=set_permission&lpath=%s&entity-name=%s&permission=%s", logicalPath,
				entityName, perm.toString());

		final var request = HttpRequest.newBuilder().version(Version.HTTP_1_1)
				.uri(URI.create(ctx.httpApiURL + "/collections")).POST(BodyPublishers.ofString(args))
				.header("Authorization", "Bearer " + bearerToken).build();

		return ctx.httpClient.send(request, BodyHandlers.ofString());
	}

}
