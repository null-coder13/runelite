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
    private static final Pattern BOSS_NAME = Pattern.compile("Your (\\w+\\s?\\w+?) kill count is: (\\d+)\\.");
    private int savedTime = 0;
    private AverageBoss garg = new AverageBoss("", 0, 0);

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
        Matcher bossMatcher = BOSS_NAME.matcher(message);

        if (bossMatcher.matches())
        {
            garg.setBoss(bossMatcher.group(1));
            garg.addTime(this.savedTime);
            garg.addKill();
            System.out.println("Boss name: " + garg.getBoss() + ", Average Time: " + garg.averageTime() + ", Kills: " + garg.getKillCount());
        }

        if (matcher.matches())
        {
            int minutes = Integer.parseInt(matcher.group(1)) * 60;
            int seconds = Integer.parseInt(matcher.group(2));
            int timeInSeconds = minutes + seconds;
            System.out.println("The timeInSeconds is: " + timeInSeconds);
        }
    }

    public int checkBoss()
    {
        //Check what boss is being killed. Maybe another plugin checks this somehow
        return 0;
    }
}
