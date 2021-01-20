package net.runelite.client.plugins.averagekilltime;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AverageBoss
{
    private String boss;
    private int time;
    private int killCount;

    public void addTime(int time)
    {
        this.time += time;
    }

    public void addKill()
    {
        killCount++;
    }

    public String averageTime()
    {
        //TODO: Debug this
        //String averageTime = String.format("%02d:%02d", time/60, time%60);
        return String.format("%02d:%02d", time/60, time%60);
    }
}
