import java.util.Map;

public class ExtractImDbContent extends AbstractContentExtractor {
    @Override
    Content extract(Map<String, String> attributes) {
        var title = attributes.get("title");
        var imageUrl = attributes.get("image");
        return new Content(title, imageUrl);
    }
}
