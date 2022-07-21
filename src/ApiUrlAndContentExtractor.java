public class ApiUrlAndContentExtractor {
    public static ApiUrlAndContentExtractorRecord get(ApiProvider provider) {
        switch (provider) {
            case IMDB -> {
                return new ApiUrlAndContentExtractorRecord(
                    "https://api.mocki.io/v2/549a5d8b/Top250Movies",
                    new ExtractImDbContent()
                );
            }
            case NASA -> {
                return new ApiUrlAndContentExtractorRecord(
                    "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-07-01&end_date=2022-07-16",
                    new ExtractorNasaContent()
                );
            }
        }

        throw new IllegalArgumentException();
    }
}

record ApiUrlAndContentExtractorRecord(String url, ContentExtractor extractor) {
}
