package ru.webitmo.vkapi.service.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@UtilityClass
public class BrowserLauncher {
    public void launchWithUrl(String url) {
        String os = System.getProperty("os.name").toLowerCase();
        Runtime runtime = Runtime.getRuntime();
        try {
            if (os.contains("win")) {
                log.info("opening browser with url:{}", url);
                runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } else {
                Desktop desktop = Desktop.getDesktop();
                if (!desktop.isSupported(Desktop.Action.BROWSE)) {
                    if (os.contains("mac")) {
                        runtime.exec("open " + url);
                    } else if (os.contains("nix") || os.contains("nux")) {
                        String[] browsers = {"epiphany", "firefox", "mozilla", "konqueror", "netscape", "opera", "links", "lynx"};
                        StringBuilder cmd = new StringBuilder();
                        for (int i = 0; i < browsers.length; i++)
                            cmd.append(i == 0 ? "" : " || ").append(browsers[i]).append(" \"").append(url).append("\" ");
                        runtime.exec(new String[]{"sh", "-c", cmd.toString()});
                    }
                }
                else {
                    try {
                        desktop.browse(new URI(url));
                    } catch (IOException ex) {
                        log.error("Cannot open browser with url: {}!", url);
                    } catch (URISyntaxException e) {
                        log.error("Bad url syntax, cannot open url!");
                    }
                }
            }
        } catch (IOException e) {
            log.error("Cannot open browser on desktop!", e);
        }
    }
}
