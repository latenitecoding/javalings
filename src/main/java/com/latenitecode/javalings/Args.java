package com.latenitecode.javalings;

/** Javalings CLI tool */
public class Args {

    public static Args parse(String version, String[] args) {
        Option option = Option.None;
        for (String arg : args) {
            if (option != Option.None) {
                continue;
            }
            if (arg.equals("-h") || arg.equals("-help") || arg.equals("--help")) {
                option = Option.Help;
                continue;
            }
            if (arg.equals("-v") || arg.equals("--version")) {
                option = Option.Version;
                continue;
            }
        }
        return new Args(version, option);
    }

    private Option option;
    private String version;

    private Args(String version, Option option) {
        this.version = version;
        this.option = option;
    }

    public String getVersion() {
        return this.version;
    }

    public boolean empty() {
        return this.option == Option.None;
    }

    public boolean help() {
        return this.option == Option.Help;
    }

    public String toString() {
        return String.join(
                "\n",
                "Usage: javalings [-v] [<command>] [<args>]",
                "",
                "Javalings is a collection of small exercises to get you used to writing and reading Java code",
                "",
                "Options:",
                String.format("%-20s %s", "  -h, -help, --help", "display usage information")
            );
    }

    public boolean version() {
        return this.option == Option.Version;
    }
}
