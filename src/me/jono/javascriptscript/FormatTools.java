package me.jono.javascriptscript;

import java.util.ArrayList;

/**
 * @author jono
 * FormatTools is a pile of abstract functions to help with formatting stuffs
 */
public class FormatTools {
    /**
     * Separates a String by spaces and tabs as long as they aren't between parentheses, brackets, or curly braces
     * @param toSplit The String to be split
     * @return The String spit into pieces
     */
    public static ArrayList<String> separateBySpacesNotInBrackets(String toSplit) {
        ArrayList<String> r = new ArrayList<>();
        r.add("");
        int parentheses = 0;
        int bracket = 0;
        int curly = 0;
        for (int i = 0; i < toSplit.length(); i++) {
            char c = toSplit.charAt(i);
            switch (c) {
                case ('(') -> {parentheses++;r.add(r.remove(r.size()-1)+c);}
                case ('[') -> {bracket++;r.add(r.remove(r.size()-1)+c);}
                case ('{') -> {curly++;r.add(r.remove(r.size()-1)+c);}
                case (')') -> {parentheses--;r.add(r.remove(r.size()-1)+c);}
                case (']') -> {bracket--;r.add(r.remove(r.size()-1)+c);}
                case ('}') -> {curly--;r.add(r.remove(r.size()-1)+c);}
                case (' '), ('\t') -> {
                    if (r.get(r.size()-1).length() > 0 && parentheses < 1 && bracket < 1 && curly < 1) {
                        r.add("");
                    }
                    else if (r.get(r.size()-1).length() > 0) {
                        r.add(r.remove(r.size()-1)+c);
                    }
                }
                default -> {r.add(r.remove(r.size()-1)+c);}
            }
        }
        return r;
    }

    /**
     * Separates a String by a character as long as it isn't between parentheses, brackets, or curly braces
     * @param toSplit The String to be split
     * @param splitChar The character to split by
     * @return The String spit into pieces
     */
    public static ArrayList<String> separateByCharNotInBrackets(String toSplit, char splitChar) {
        ArrayList<String> r = new ArrayList<>();
        r.add("");
        int parentheses = 0;
        int bracket = 0;
        int curly = 0;
        for (int i = 0; i < toSplit.length(); i++) {
            char c = toSplit.charAt(i);
            switch (c) {
                case ('(') -> {parentheses++;r.add(r.remove(r.size()-1)+c);}
                case ('[') -> {bracket++;r.add(r.remove(r.size()-1)+c);}
                case ('{') -> {curly++;r.add(r.remove(r.size()-1)+c);}
                case (')') -> {parentheses--;r.add(r.remove(r.size()-1)+c);}
                case (']') -> {bracket--;r.add(r.remove(r.size()-1)+c);}
                case ('}') -> {curly--;r.add(r.remove(r.size()-1)+c);}
                default -> {
                    if (c == splitChar) {
                        if (r.get(r.size()-1).length() > 0 && parentheses < 1 && bracket < 1 && curly < 1) {
                            r.add("");
                        }
                        else if (r.get(r.size()-1).length() > 0) {
                            r.add(r.remove(r.size()-1)+c);
                        }
                    }
                    else r.add(r.remove(r.size()-1)+c);
                }
            }
        }
        return r;
    }
}
