package exeptions;

public class InvalidGridUrl extends RuntimeException {

    final String message;

    public InvalidGridUrl(String url) {

        this.message = String.format("""
                \"%s\" is not in a valid url format
                to use as selenium grid url
                please check the URL definition
                in config.properties under the project resource
                """,url);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
