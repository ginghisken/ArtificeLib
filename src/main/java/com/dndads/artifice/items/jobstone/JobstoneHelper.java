package com.dndads.artifice.items.jobstone;

import java.util.HashMap;
import java.util.Map;

public class JobstoneHelper {
    private final static Map<String, String[]> compendium = new HashMap<String, String[]>() {{
//        Use this to add the Jobstone text for items

//        put(" job_name ", new String[] {
//          "Job: Job\nFlavor text",
//          "Text that is revealed when you hold SHIFT"
//        });

        put("tester", new String[] {
            "\nJob: Tester",
                "Basically useless. Nice."
        });

        put("scout", new String[] {
                "\nJob: Scout\n" +
                        "Dodge faster, dodge better.",
                "Enhances feather regeneration speed.\n" +
                        "Upclasses into nothing."
        });
        put("defender", new String[] {
                "\nJob: Defender\n" +
                        "Stand tall, stand firm.",
                "Enhances defensive capability.\n" +
                        "Upclasses into Knight and Paladin."
        });
//        put("", new String[] {
//
//        });
//        put("", new String[] {
//
//        });
//        put("", new String[] {
//
//        });
    }};

    public static String[] getText(String name) { return compendium.get(name); }
}
