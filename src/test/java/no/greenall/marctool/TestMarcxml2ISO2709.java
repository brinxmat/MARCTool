package no.greenall.marctool;

import no.greenall.marctool.utils.ResourceUtils;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class TestMarcxml2ISO2709 {

    private static final String EOL = System.lineSeparator();

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Rule
    public TestResources testResources = TestResources.getDefaultTestResource();

    @Ignore
    @Test
    public void testItComplainsAboutLackingInputOption() {

        String expected = "Missing required option '--input=FILE'\n"
                + "Usage: marcxml2iso2709 [-h] -i=FILE [-o=FILE]\n"
                + "  -h, --help          Display help for command\n"
                + "  -i, --input=FILE    Path to input file\n"
                + "  -o, --output=FILE   Path to output file\n".replace("\n", EOL);

        String[] args = {};
        Marcxml2ISO2709.main(args);
        assertEquals(expected, testResources.getStandardErrorString());
    }

    @Ignore
    @Test
    public void testItCanConvertMarcxmlToIso2709() throws IOException {
        String inputFile = ResourceUtils.getFile( "single_record.marcxml").getAbsolutePath();
        String expected = ResourceUtils.readString("single_record.mrc", true);
        String[] args = {"-i=" + inputFile};
        Marcxml2ISO2709.main(args);
        assertEquals(expected, testResources.getStandardOutString());
    }

    @Ignore
    @Test
    public void testItCanConvertMarcxmlToIso2709File() throws IOException {
        String inputFile = ResourceUtils.getFile( "single_record.marcxml").getAbsolutePath();
        String outputFile = testFolder.newFile().getAbsolutePath();
        String expected = ResourceUtils.readString("single_record.mrc", true);
        String[] args = {"-i=" + inputFile, "-o=" + outputFile};
        Marcxml2ISO2709.main(args);
        String[] result = new String[1];
        result[0] = "";

        Files.readAllLines(Paths.get(outputFile)).forEach(s -> result[0] = result[0] + s + EOL);

        assertEquals(expected, result[0]);
    }
}