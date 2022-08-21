package dev.ebo2022.newworld.core;


import gg.moonflower.pollen.api.config.PollinatedConfigBuilder;

public class NewWorldConfig {

    public static class Server {

        public final PollinatedConfigBuilder.ConfigValue<Boolean> firsInTaiga;
        public final PollinatedConfigBuilder.ConfigValue<Boolean> firsInMeadow;

        public Server(PollinatedConfigBuilder builder) {
            builder.push("Generation");
            this.firsInMeadow = builder.comment("Whether fir trees should rarely generate in regular meadows.").define("Firs in Meadows", true);
            this.firsInTaiga = builder.comment("Whether fir trees should generate semi-commonly in Taiga biomes.").define("Firs in Taigas", true);
            builder.pop();
        }
    }
}
