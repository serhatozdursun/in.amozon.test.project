package browsers;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;


public interface BrowserSelectable {
    MutableCapabilities getCapabilities();

    RemoteWebDriver getBrowser(URL remoteAddress);
}
