package net.craftingstore.sponge;

import com.google.inject.Inject;
import net.craftingstore.core.CraftingStorePlugin;
import net.craftingstore.core.models.donation.Donation;
import net.craftingstore.sponge.config.Config;
import net.craftingstore.sponge.events.DonationReceivedEvent;
import org.spongepowered.api.Game;
import org.spongepowered.api.Platform;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.plugin.PluginContainer;

import java.util.concurrent.TimeUnit;

public class CraftingStoreSpongeImpl implements CraftingStorePlugin {

    @Inject
    private CraftingStoreSponge spongePlugin;

    @Inject
    private Game game;

    @Inject
    private Config config;

    @Inject
    private PluginContainer pluginContainer;

    public boolean executeDonation(Donation donation) {
        if (donation.getPlayer().isRequiredOnline()) {
            Player player = game.getServer().getPlayer(donation.getPlayer().getUsername()).orElse(null);
            if (player == null || !player.isOnline()) {
                return false;
            }
        }

        DonationReceivedEvent event = new DonationReceivedEvent(donation);
        game.getEventManager().post(event);
        if (event.isCancelled()) {
            return false;
        }

        game.getScheduler().createTaskBuilder()
                .execute(() -> game.getCommandManager().process(game.getServer().getConsole(), donation.getCommand()))
                .submit(spongePlugin);
        return true;
    }

    public java.util.logging.Logger getLogger() {
        return java.util.logging.Logger.getLogger("CraftingStore");
    }

    public void disable() {
        // Not possible in Sponge
    }

    public void registerRunnable(Runnable runnable, int delay, int interval) {
        game.getScheduler().createTaskBuilder()
                .execute(runnable)
                .async()
                .delay(delay, TimeUnit.SECONDS)
                .interval(interval, TimeUnit.SECONDS)
                .submit(spongePlugin);
    }

    public String getToken() {
        return config.getConfig().getNode("api-key").getString();
    }

    public String getVersion() {
        return pluginContainer.getVersion().orElse("unknown-version");
    }

    public String getPlatform() {
        return "sponge/"
                + game.getPlatform().getContainer(Platform.Component.API).getVersion().orElse("unknown")
                + "/" + game.getPlatform().getContainer(Platform.Component.IMPLEMENTATION).getVersion().orElse("unknown");
    }
}
