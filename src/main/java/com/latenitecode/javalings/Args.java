package com.latenitecode.javalings;

/** Javalings CLI tool */
public class Args {

    private static void assertNoCommand(Command command) {
        if (command != Command.None) {
            throw new RuntimeException("Cannot have two commands");
        }
    }

    private static void assertNoOption(Option option) {
        if (option != Option.None) {
            throw new RuntimeException("Cannot have two options");
        }
    }

    public static Args parse(String version, String[] args) {
        Command command = Command.None;
        Option option = Option.None;
        for (String arg : args) {
            if (arg.equals("list")) {
                assertNoCommand(command);
                command = Command.List;
                continue;
            }
            if (arg.equals("-h") || arg.equals("-help") || arg.equals("--help")) {
                assertNoOption(option);
                option = Option.Help;
                continue;
            }
            if (arg.equals("-v") || arg.equals("--version")) {
                assertNoOption(option);
                option = Option.Version;
                continue;
            }
        }
        return new Args(version, command, option);
    }

    private Command command;
    private Option option;
    private String version;

    private Args(String version, Command command, Option option) {
        this.version = version;
        this.command = command;
        this.option = option;
    }

    public String getVersion() {
        return this.version;
    }

    public boolean empty() {
        return this.command == Command.None && this.option == Option.None;
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

    public static enum Command {
        None,
        List
    }

    public static enum Option {
        Help,
        None,
        Version
    }
}
