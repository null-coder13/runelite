package net.runelite.client.plugins.averagekilltime;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.events.ChatMessage;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.util.Text;

import javax.inject.Inject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@PluginDescriptor(
        name = "Average Kill Time",
        description = "Shows the average kill time of a boss",
        enabledByDefault = true,
        tags = {"average","kill","boss"}
)

public class AverageKillPlugin extends Plugin
{
    private static final Pattern KILL_MESSAGE = Pattern.compile("Fight duration: <col=ff0000>(\\d+):(\\d+)</col>\\. Personal best: (\\d+):(\\d+)\\.");

    @Inject
    private Client client;

    @Inject
    private ChatMessageManager chatMessageManager;

    @Inject
    public void onChatMessage(ChatMessage event)
    {
        System.out.println(KILL_MESSAGE.toString());
        if (event.getType() != ChatMessageType.GAMEMESSAGE)
        {
            System.out.println("Not a GameMessage");
            return;
        }
        String message = event.getMessage();
        System.out.println(message);
        Matcher matcher = KILL_MESSAGE.matcher(message);

        if (matcher.matches())
        {
            int test1 = Integer.parseInt(matcher.group(1));
            System.out.println(test1);
        }
    }
}
