package com.dndads.artifice.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigHandler {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> testInteger;
    public static final ForgeConfigSpec.ConfigValue<String> testString;

    static {
        BUILDER.push("Config for Artifice Weapon Types");

        testInteger = BUILDER.comment("This is an integer. (Default Value: 1)").define("Example Integer", 1);
        // Can be gotten anywhere via ConfigHandler.testInteger.get()

        testString = BUILDER.comment("This is a string. (Default Value: test)").define("Example String", "test");
        // Can be gotten anywhere via ConfigHandler.testString.get()

        BUILDER.pop();
        SPEC = BUILDER.build();
    }


}
