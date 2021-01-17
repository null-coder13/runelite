package net.runelite.client.plugins.averagekilltime;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.events.ChatMessage;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.util.Text;

import javax.inject.Inject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@PluginDescriptor(
        name = "Average Kill Time",
        description = "Shows the average kill time of a boss",
        enabledByDefault = true,
        tags = {"average","kill","boss"}
)

@Slf4j
public class AverageKillPlugin extends Plugin
{
    private static final Pattern KILL_MESSAGE = Pattern.compile("Fight duration: (\\d+):(\\d+)\\. Personal best: (\\d+):(\\d+)");

    @Inject
    private Client client;

    @Inject
    private ChatMessageManager chatMessageManager;

    @Subscribe
    public void onChatMessage(ChatMessage event)
    {
        if (event.getType() != ChatMessageType.GAMEMESSAGE)
        {
            return;
        }

        String message = Text.removeTags(event.getMessage());
        Matcher matcher = KILL_MESSAGE.matcher(message);

        if (matcher.matches())
        {
            //TODO: Figure out how to convert time maybe new method
            int test1 = Integer.parseInt(matcher.group(2));
        }
    }
}
