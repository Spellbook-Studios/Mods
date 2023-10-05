package dk.sebsa.updateme.client;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateMEClient implements ClientModInitializer {
    public static Logger LOGGER = LoggerFactory.getLogger("update-me");

    @Override
    public void onInitializeClient() {
        LOGGER.info("Update ME! Running");
    }
}