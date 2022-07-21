import java.io.File;
import java.net.URL;

public class App {
    public static void main(String[] args) throws Exception {
        var apiUrlAndContentExtractor = ApiUrlAndContentExtractor.get(ApiProvider.NASA);

        var client = new ClientHttp();
        var responseDataJson = client.getData(apiUrlAndContentExtractor.url());
        ContentExtractor contentExtractor = apiUrlAndContentExtractor.extractor();
        var contentsList = contentExtractor.extractContent(responseDataJson);
        var generator = new StickerGenerator();

        for (Content content : contentsList) {
            try {
                var title = content.title();
                var imageUrl = content.urlImage();
                var imageStream = new URL(imageUrl).openStream();
                var imageOutput = new File("images/outputs/topMovies" + title + ".png");

                generator.generate(imageStream, imageOutput);
            } catch (Exception err) {
                System.out.println(err.toString());
            }
        }
    }
}
