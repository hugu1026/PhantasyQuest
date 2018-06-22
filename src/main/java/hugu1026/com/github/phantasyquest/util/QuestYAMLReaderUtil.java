package hugu1026.com.github.phantasyquest.util;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestYAMLReaderUtil {
    public static File getQuestFile(String questName) {
        File questData = new File(Bukkit.getServer().getPluginManager().getPlugin("PhantasyQuest").getDataFolder(), File.separator + "quests");
        return new File(questData, File.separator + questName + ".yml");
    }

    public static FileConfiguration getQuestData(String questName) {
        File questDatas = new File(Bukkit.getServer().getPluginManager().getPlugin("PhantasyQuest").getDataFolder(), File.separator + "quests");
        File questFile = new File(questDatas, File.separator + questName + ".yml");
        return YamlConfiguration.loadConfiguration(questFile);
    }

    public static List<String> getAllQuestsNames() {
        File questFile = new File(Bukkit.getServer().getPluginManager().getPlugin("PhantasyQuest").getDataFolder(), File.separator + "quests");
        List<File> questFiles = Arrays.asList(questFile.listFiles());
        List<String> questNames = new ArrayList<>();
        questFiles.forEach(file -> {
            String questName = file.getName().replace("plugins\\PhantasyQuest\\quests\\", "");
            questNames.add(questName);
        });
        return questNames;
    }
}
