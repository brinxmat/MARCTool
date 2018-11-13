package no.greenall.marctool;

import picocli.CommandLine;

@CommandLine.Command(name = "App", subcommands = {Marcxml2ISO2709.class})
public class App implements Runnable {

    private static final String DISPLAY_HELP_MESSAGE = "Display help message";

    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = DISPLAY_HELP_MESSAGE)
    boolean getHelp = false;

    public static void main(String[] args) {
        if (args.length == 0) {
            // a small hack to show help on empty args
            args = new String[]{"-h"};
        }

        CommandLine.run(new App(), args);
    }

    @Override
    public void run() {
        // NO-OP
    }
}
