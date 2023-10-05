package dk.sebsa.updateme;

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

public class UpdateMEConfig {
    public static ConfigClassHandler<UpdateMEConfig> HANDLER = ConfigClassHandler.createBuilder(UpdateMEConfig.class)
            .id(new Identifier("update-me", "update_me_config"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("update_me.json5"))
                    .setJson5(true)
                    .build())
            .build();

    @SerialEntry(comment = "The version of the modpack, this is the version that is used to compare against the newest version")
    public String versionString = "1.0.0a";

    @SerialEntry(comment = "If not empty, this used to test the modpacks version against\nIf the text is different from the current versions text, the update screen will show up, upon launching the game\nMust be a direct link e.g.\nhttps://raw.githubusercontent.com/Spellbook-Studios/Modpacks/main/Wynntills%20Ultimate/version-text/VERSION")
    public String newestVersionURL = "";
}
