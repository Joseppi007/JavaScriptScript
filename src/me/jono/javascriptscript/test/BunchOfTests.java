package me.jono.javascriptscript.test;

import me.jono.javascriptscript.ProgramGraph;

/**
 * @author jono
 * I wanted to just test a bunch of stuff, so I made this thing.
 */
public class BunchOfTests {
    public static void main(String[] args) {
        doesProgramGraphMakeRandomStringsWell(args);
    }

    public static void doesProgramGraphMakeRandomStringsWell(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(ProgramGraph.randomString(10));
        }
    }
}
