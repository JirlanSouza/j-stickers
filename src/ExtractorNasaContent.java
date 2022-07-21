import java.util.Map;

public class ExtractorNasaContent extends AbstractContentExtractor {
    @Override
    Content extract(Map<String, String> attributes) {
        var title = attributes.get("title");
        var imageUrl = attributes.get("url");
        return new Content(title, imageUrl);
    }
}
