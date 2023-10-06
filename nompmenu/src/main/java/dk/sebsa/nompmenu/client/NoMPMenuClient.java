package dk.sebsa.nompmenu.client;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoMPMenuClient implements ClientModInitializer {
    public static Logger LOGGER = LoggerFactory.getLogger("nompmenu");

    @Override
    public void onInitializeClient() {
        LOGGER.info("NoMPMenuClient Running");
    }
}
