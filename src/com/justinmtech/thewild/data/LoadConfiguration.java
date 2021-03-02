package com.justinmtech.thewild.data;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

//Load the configuration file
public class LoadConfiguration {
    private Map<Map<String, Number>, Map<String, Number>> config;


    public LoadConfiguration() {
        loadConfigFile();
    }

    private void loadConfigFile() {
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("config.yml");
        config = yaml.load(inputStream);
    }

    public Map<Map<String, Number>, Map<String, Number>> getConfig() {
        return config;
    }

    public void setConfig(Map<Map<String, Number>, Map<String, Number>> config) {
        this.config = config;
    }
}
