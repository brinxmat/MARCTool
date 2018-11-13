package no.greenall.marctool;

import org.marc4j.MarcStreamWriter;
import org.marc4j.MarcWriter;
import org.marc4j.MarcXmlReader;
import org.marc4j.marc.Record;
import picocli.CommandLine;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

@CommandLine.Command(name = "marcxml2iso2709")
public class Marcxml2ISO2709 implements Runnable {

    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "Display help for command")
    private boolean showHelp;

    @CommandLine.Option(names = {"-i", "--input"}, description = "Path to input file", paramLabel = "FILE", required = true)
    private String inputFilePath;

    @CommandLine.Option(names = {"-o", "--output"}, description = "Path to output file", paramLabel = "FILE")
    private String outputFilePath;

    public static void main(String[] args) {
        CommandLine.run(new Marcxml2ISO2709(), args);
    }

    private String convertFromMarcxml() {
        final InputStream inputStream = getFileInputStream(inputFilePath);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final MarcXmlReader marcXmlReader = new MarcXmlReader(inputStream);
        final MarcWriter marcWriter = new MarcStreamWriter(byteArrayOutputStream);
        while (marcXmlReader.hasNext()) {
            Record record = marcXmlReader.next();
            marcWriter.write(record);
        }
        return byteArrayOutputStream.toString();
    }

    private InputStream getFileInputStream(String path) {
        try {
            return new FileInputStream(new File(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        if (Objects.nonNull(outputFilePath)) {
            try {
                Files.write(Paths.get(outputFilePath), convertFromMarcxml().getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println(convertFromMarcxml());
        }
    }
}
