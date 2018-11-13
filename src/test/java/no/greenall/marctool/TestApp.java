package no.greenall.marctool;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TestApp {

    private static final CharSequence EOL = System.lineSeparator();

    @Rule
    public TestResources testResources = TestResources.getDefaultTestResource();

    @Test
    public void testExists() {
        new App();
    }

    @Test
    public void testItConvertsXmlToIso2709() {
        String[] args = {"-i=in.xml", "-o=out.mrc"};
        App.main(args);
    }

    @Test
    public void testItDisplaysHelpMessageOnNoArgs() {

        String expected = "Usage: App [-h] [COMMAND]\n"
                + "  -h, --help   Display help message\n"
                + "Commands:\n"
                + "  marcxml2iso2709"
                +"\n".replace("\n", "");

        String[] emptyArgs = {};
        App.main(emptyArgs);
        assertEquals(expected, testResources.getStandardOutString().replace(EOL, ""));
    }
}
