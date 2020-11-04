package edu.brown.cs;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import java.util.List;
import java.util.ArrayList;

/**
 * Unit tests for text to commands
 */
public class TextToCommandsTest
{

    @Test
    public void testFormatting()
    {
        TextToCommands t = new TextToCommands();
        List<String> s = new ArrayList<String>();

        assertEquals(t.getVariableString(s), "");
        assertEquals(t.getAllCapsString(s), "");
        assertEquals(t.getNormalCase(s), "");

        s.add("this");

        assertEquals(t.getVariableString(s), "this");
        assertEquals(t.getAllCapsString(s), "THIS");
        assertEquals(t.getNormalCase(s), "This");

        s.add("is");
        s.add("a");
        s.add("var");

        assertEquals(t.getVariableString(s), "thisIsAVar");
        assertEquals(t.getAllCapsString(s), "THIS_IS_A_VAR");
        assertEquals(t.getNormalCase(s), "ThisIsAVar");
    }

    @Test
    public void testCommandMatching()
    {
        TextToCommands t = new TextToCommands();
        List<String> s1 = new ArrayList<String>();
        List<String> s2 = new ArrayList<String>();
        s1.add("next");
        s1.add("line");
        s1.add("something");

        List<String> command = t.matchCommand(s1);
        assertTrue(command != null);
        for (String s : command) {
          System.out.println(s);
        }
      }

}
