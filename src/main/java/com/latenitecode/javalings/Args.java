package com.latenitecode.javalings;

import java.util.HashSet;
import java.util.Set;

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
        Set<String> clargs = new HashSet<>();
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
            clargs.add(arg);
        }
        return new Args(version, command, option, clargs);
    }

    private Set<String> args;
    private Command command;
    private Option option;
    private String version;

    private Args(String version, Command command, Option option, Set<String> args) {
        this.version = version;
        this.command = command;
        this.option = option;
        this.args = args;
    }

    public String getVersion() {
        return this.version;
    }

    public boolean empty() {
        return this.command == Command.None && this.option == Option.None;
    }

    public boolean hasArg(String arg) {
        return this.args.contains(arg);
    }

    public boolean hasLongOrShortArg(String arg) {
        return this.hasArg(String.format("--%s", arg)) ||
            this.hasArg(String.format("-%s", arg.charAt(0)));
    }

    public boolean help() {
        return this.option == Option.Help;
    }

    public boolean list() {
        return this.command == Command.List;
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
