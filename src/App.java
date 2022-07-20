import java.io.File;
import java.io.PrintStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        var url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        var apiUrl = URI.create(url);
        var httpClient = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(apiUrl).GET().build();
        var response = httpClient.send(request, BodyHandlers.ofString());
        String body = response.body();

        var parser = new JsonParser();
        List<Map<String, String>> moviesList = parser.parse(body);

        var generator = new StickerGenerator();

        for (Map<String, String> movie : moviesList) {
            try {
                var imageUrl = movie.get("image");
                var movieTitle = movie.get("title");


                var imageStream = new URL(imageUrl).openStream();
                var imageOutput = new File("images/outputs/topMovies" + movieTitle + ".png");
                generator.generate(imageStream, imageOutput);
            } catch (Exception err) {
                continue;
            }


            StringBuilder rating = new StringBuilder();

            for (int i = 0; i < new Float(movie.get("imDbRating")); i++) {
                rating.append("\u2B50");
            }

            System.out.printf("%-20s%s %n", "RANK:", movie.get("rank"));
            System.out.printf("%-20s%s %n", "ID:", movie.get("id"));
            System.out.printf("%-20s\u001b[1m\u001b[30m\u001b[45;1m %s \u001b[m %n", "TITLE:", movie.get("title"));
            System.out.printf("%-20s\u001b[1m\u001b[33m%s\u001b[m %n", "RATING:", rating.toString());
            System.out.printf("---------------------------------------------------------------------------------%n");
        }
    }
}
