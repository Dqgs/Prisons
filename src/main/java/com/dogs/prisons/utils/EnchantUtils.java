package com.dogs.prisons.utils;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnchantUtils {
    public static String intToRomanNumeral(int i) {
        switch (i) {
            case 1:
                return "I";
            case 2:
                return "II";
            case 3:
                return "III";
            case 4:
                return "IV";
            case 5:
                return "V";
            case 6:
                return "VI";
            case 7:
                return "VII";
            case 8:
                return "VIII";
            case 9:
                return "IX";
            case 10:
                return "X";
        }
        return Integer.toString(i);
    }

    public static int romanNumeralToInt(String i) {
        switch (i.toUpperCase()) {
            case "I":
                return 1;
            case "II":
                return 2;
            case "III":
                return 3;
            case "IV":
                return 4;
            case "V":
                return 5;
            case "VI":
                return 6;
            case "VII":
                return 7;
            case "VIII":
                return 8;
            case "IX":
                return 9;
            case "X":
                return 10;
        }
        return 0;
    }
    public static List<String> loreLineFormat(String string, ChatColor color) {
        List<String> newString = new ArrayList<>();
        while(string.contains(" ")) {
            StringBuilder sb = new StringBuilder();
            for(String a :string.split(" ", 4)) {
                sb.append(color + a + " ");
            }
            newString.add(sb.toString().trim());
            string = string.split(" ", 5)[string.split(" ", 5).length-1];
        }

        return newString;
    }
    public static String camelCase(String string) {
        Character beginning = string.charAt(0);
        return beginning.toString().toUpperCase() + string.split(beginning.toString(), 2)[1].toLowerCase();
    }

    public static Object pickRandom(Object... objects) {
        if(objects.length == 0) return null;
        return objects[new Random().nextInt(objects.length)];
    }
}
