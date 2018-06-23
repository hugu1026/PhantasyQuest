package hugu1026.com.github.phantasyquest.quest;

import hugu1026.com.github.phantasyquest.PhantasyQuest;
import hugu1026.com.github.phantasyquest.quest.conversation.Conversation;
import hugu1026.com.github.phantasyquest.util.QuestYAMLReaderUtil;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class Quest {
    private String questName, NPCName, questFileName;
    private Player player;
    private NPC npc;
    private List<String> conversations, events, conditions, objectives, journals;
    private int NPCId;

    public Quest(String questFileName, Player player, NPC npc) {
        this.questFileName = questFileName;
        this.questName = QuestYAMLReaderUtil.getQuestName(questFileName);
        this.conversations = QuestYAMLReaderUtil.getConversations(questFileName);
        this.events = QuestYAMLReaderUtil.getEvents(questFileName);
        this.conditions = QuestYAMLReaderUtil.getConditions(questFileName);
        this.objectives = QuestYAMLReaderUtil.getObjectives(questFileName);
        this.journals = QuestYAMLReaderUtil.getJournals(questFileName);
        this.player = player;
        this.npc = npc;
        this.NPCId = npc.getId();
        this.NPCName = npc.getName();
    }

    public void startQuest(int startPoint) {
        final int[] point = {startPoint};
        new BukkitRunnable() {
            @Override
            public void run() {
                Conversation conversation = new Conversation(conversations.get(point[0] - 1), questFileName);
                player.sendMessage(ChatColor.GREEN + "[Quest] " + ChatColor.RED + NPCName + ": " + ChatColor.GOLD + conversation.getText());
                if (conversations.size() == point[0]) {
                    cancel();
                } else {
                    point[0] = point[0] + 1;
                }
            }
        }.runTaskTimer(PhantasyQuest.getPlugin(PhantasyQuest.class), 20L, 40L);
    }

    public String getQuestName() {
        return this.questName;
    }

    public String getQuestFileName() {
        return this.questFileName;
    }

    public List<String> getConversations() {
        return this.conversations;
    }

    public List<String> getEvents() {
        return this.events;
    }

    public List<String> getConditions() {
        return this.conditions;
    }

    public int getNPCId() {
        return this.NPCId;
    }

    public List<String> getJournals() {
        return this.journals;
    }

    public List<String> getObjectives() {
        return this.objectives;
    }

    public NPC getNpc() {
        return this.npc;
    }

    public Player getPlayer() {
        return this.player;
    }

    public String getNPCName() {
        return this.NPCName;
    }
}