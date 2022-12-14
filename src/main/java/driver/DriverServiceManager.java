package driver;

import exeptions.DriverServiceNotStarted;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.service.DriverService;
import org.openqa.selenium.safari.SafariDriverService;

import java.io.IOException;

import static enums.Browsers.*;

public class DriverServiceManager {
    private static DriverServiceManager instances = null;

    private DriverService service;
    private DriverServiceManager() {

    }

    public static DriverServiceManager getInstances() {
        if (instances == null) {
            instances = new DriverServiceManager();
        }
        return instances;
    }

    public DriverService getService() {
        switch (DriverManager.instance().getBrowsersType()) {
            case FIREFOX -> {
                service = new GeckoDriverService.Builder()
                        .usingAnyFreePort()
                        .build();
                try {
                    service.start();
                } catch (IOException e) {
                    throw new DriverServiceNotStarted(FIREFOX.value(), e.getMessage());
                }
                return service;
            }
            case EDGE -> {
                service = new EdgeDriverService.Builder()
                        .usingAnyFreePort()
                        .build();
                try {
                    service.start();
                } catch (IOException e) {
                    throw new DriverServiceNotStarted(EDGE.value(), e.getMessage());
                }
                return service;
            }
            case SAFARI -> {
                service = new SafariDriverService.Builder()
                        .usingAnyFreePort()
                        .build();
                try {
                    service.start();
                } catch (IOException e) {
                    throw new DriverServiceNotStarted(SAFARI.value(), e.getMessage());
                }
                return service;
            }
            default -> {
                {
                    service = new ChromeDriverService.Builder()
                            .usingAnyFreePort()
                            .build();
                    try {
                        service.start();
                    } catch (IOException e) {
                        throw new DriverServiceNotStarted(CHROME.value(), e.getMessage());
                    }
                    return service;
                }
            }
        }
    }

    public void stopService(){
        service.stop();
    }
}
