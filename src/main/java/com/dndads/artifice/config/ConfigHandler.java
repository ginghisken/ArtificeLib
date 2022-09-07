package com.dndads.artifice.config;

import net.minecraftforge.common.ForgeConfigSpec;
import java.util.ArrayList;

public class ConfigHandler {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    //public static final ForgeConfigSpec.ConfigValue<Integer> testInteger;
    //public static final ForgeConfigSpec.ConfigValue<String> testString;

    public static final ForgeConfigSpec.ConfigValue<ArrayList<String>> swordsList;
    public static final ForgeConfigSpec.ConfigValue<ArrayList<String>> axesList;

    public static ArrayList<String> swordsListDefault = new ArrayList<String>();
    public static ArrayList<String> axesListDefault = new ArrayList<String>();

    static {
        BUILDER.push("Config for Artifice Weapon Types");

        // testInteger = BUILDER.comment("This is an integer. (Default Value: 1)").define("Example Integer", 1);
        // Can be gotten anywhere via ConfigHandler.testInteger.get()

        // testString = BUILDER.comment("This is a string. (Default Value: test)").define("Example String", "test");
        // Can be gotten anywhere via ConfigHandler.testString.get()

        swordsListDefault.add("minecraft:wooden_sword");
        swordsListDefault.add("minecraft:stone_sword");
        swordsListDefault.add("minecraft:iron_sword");
        swordsListDefault.add("minecraft:gold_sword");
        swordsListDefault.add("minecraft:diamond_sword");
        swordsListDefault.add("minecraft:netherite_sword");
        swordsListDefault.add("artifice:materia_sword");

        swordsList = BUILDER.comment("This is a list of all sword names. (Default Values: " + swordsListDefault).define("Swords List", swordsListDefault);
        // Can be gotten anywhere via ConfigHandler.swordsList.get()

        axesListDefault.add("minecraft:wooden_axe");
        axesListDefault.add("minecraft:stone_axe");
        axesListDefault.add("minecraft:iron_axe");
        axesListDefault.add("minecraft:gold_axe");
        axesListDefault.add("minecraft:diamond_axe");
        axesListDefault.add("minecraft:netherite_axe");

        axesList = BUILDER.comment("This is a list of all axe names. (Default Values: " + axesListDefault).define("Axes List", axesListDefault);
        // Can be gotten anywhere via ConfigHandler.axesList.get()

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
