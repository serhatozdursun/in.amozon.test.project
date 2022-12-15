package browsers;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.URL;

public class Safari implements BrowserSelectable {

    @Override
    public MutableCapabilities getCapabilities() {
        SafariOptions options = new SafariOptions();
        options.setCapability("browserstack.local", "false");
        return options;
    }

    @Override
    public RemoteWebDriver getBrowser(URL remoteAddress) {
        return new RemoteWebDriver(remoteAddress, getCapabilities());
    }
}
