package enums;

public enum Browsers {
    CHROME("chrome"),
    FIREFOX("firefox"),
    SAFARI("safari"),
    EDGE("edge");
    private final String browser;

    Browsers(String browser) {
        this.browser = browser;
    }

    public String value() {
        return this.browser;
    }
}

