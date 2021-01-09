package net.runelite.client.plugins.averagekilltime;

import net.runelite.api.ChatMessageType;
import net.runelite.api.events.ChatMessage;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.util.Text;

import javax.inject.Inject;

@PluginDescriptor(
        name = "Average Kill Time",
        description = "Shows the average kill time of a boss",
        enabledByDefault = true,
        tags = {"average","kill","boss"}
)

public class AverageKillPlugin extends Plugin
{
    @Inject
    public void onChatMessage(ChatMessage event)
    {
        if (event.getType() != ChatMessageType.GAMEMESSAGE)
        {
            return;
        }
        String message = Text.removeTags(event.getMessage());
    }
}
