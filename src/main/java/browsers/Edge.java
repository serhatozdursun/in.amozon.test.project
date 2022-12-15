package browsers;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;

public class Edge implements BrowserSelectable {

    @Override
    public MutableCapabilities getCapabilities() {
        EdgeOptions options = new EdgeOptions();
        var prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setCapability("prefs",prefs);
        return options;
    }

    @Override
    public RemoteWebDriver getBrowser(URL remoteAddress) {
        return new RemoteWebDriver(remoteAddress, getCapabilities());
    }
}

