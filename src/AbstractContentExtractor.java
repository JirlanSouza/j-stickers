import java.util.List;
import java.util.Map;

public abstract class AbstractContentExtractor implements ContentExtractor {
    public List<Content> extractContent(String json) {
        var parser = new JsonParser();
        List<Map<String, String>> attributesList = parser.parse(json);

        return attributesList.stream().map(this::extract).toList();
    }

    abstract Content extract(Map<String, String> attributes);
}
