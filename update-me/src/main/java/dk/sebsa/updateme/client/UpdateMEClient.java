package dk.sebsa.updateme.client;

import dk.sebsa.updateme.UpdateMEConfig;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class UpdateMEClient implements ClientModInitializer {
    public static Logger LOGGER = LoggerFactory.getLogger("update-me");
    public static String currVer, newVer;
    public static boolean needsUpdate;
    public static boolean screenShown = false;

    @Override
    public void onInitializeClient() {
        UpdateMEConfig.HANDLER.load();
        UpdateMEConfig.HANDLER.save();
        currVer = UpdateMEConfig.HANDLER.instance().versionString;
        newVer = currVer;

        LOGGER.info("Update ME! Running. Current modpack version: " + currVer);

        if(!UpdateMEConfig.HANDLER.instance().newestVersionURL.isEmpty()) {
            try {
                URL url = new URL(UpdateMEConfig.HANDLER.instance().newestVersionURL);
                Scanner s = new Scanner(url.openStream());
                // read from your scanner
                newVer = s.nextLine();
                LOGGER.info("Newest version: " + newVer);
            }
            catch(IOException ex) {
                LOGGER.error("A IOException occured getting url", ex);
                return;
            }
        }

        if(!currVer.equals(newVer)) {
            LOGGER.warn("Modpack needs an update");
            needsUpdate = true;
        } else {
            LOGGER.info("Modpack is up-to date");
            needsUpdate = false;
        }
    }
}