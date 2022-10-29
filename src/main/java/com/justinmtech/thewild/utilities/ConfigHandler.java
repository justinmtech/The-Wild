package com.justinmtech.thewild.utilities;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.Objects;

public class ConfigHandler {
    private Map<Map<String, Number>, Map<String, Number>> config;


    public ConfigHandler() {
        boolean configExists = new File("config.yml").exists();
        if (!configExists) saveConfig();
        loadConfigFile();
    }

    private void loadConfigFile() {
        Yaml yaml = new Yaml();
        try {
            InputStream inputStream = new FileInputStream("config.yml");
            config = yaml.load(inputStream);
        } catch (FileNotFoundException e) {
            System.out.println("Config file not found.");
            saveConfig();
        }
    }

    private void saveConfig() {
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("config.yml");
        try {
            Files.copy(Objects.requireNonNull(inputStream), Paths.get("config.yml"), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Config could not be saved.");
        }
    }

    public Map<Map<String, Number>, Map<String, Number>> getConfig() {
        return config;
    }

}
