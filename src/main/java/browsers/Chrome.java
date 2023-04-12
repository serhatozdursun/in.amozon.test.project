package browsers;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class Chrome implements BrowserSelectable {

    @Override
    public MutableCapabilities getCapabilities() {
        var options = new ChromeOptions();
        var caps = new MutableCapabilities();
        caps.setCapability(CapabilityType.BROWSER_NAME,"chrome");
        options.addArguments("--kiosk");
        options.addArguments("--log-level=3");
        options.addArguments("--disable-notifications");
        options.addArguments("--start-fullscreen");
        options.addArguments("--disable-logging");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        //options.addArguments("--headless");
        caps.setCapability(ChromeOptions.CAPABILITY, options);
        return options;
    }

    @Override
    public RemoteWebDriver getBrowser(URL remoteAddress) {
        return new RemoteWebDriver(remoteAddress, getCapabilities());
    }
}
