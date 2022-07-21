import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientHttp {
    public String getData(String url) throws RuntimeException {
        try {
            var apiUrl = URI.create(url);
            var httpClient = java.net.http.HttpClient.newHttpClient();
            var request = HttpRequest.newBuilder(apiUrl).GET().build();
            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();
        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }

    }
}
