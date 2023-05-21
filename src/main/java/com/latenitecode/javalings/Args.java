package com.latenitecode.javalings;

/** Javalings CLI tool */
public class Args {

    public static Args parse(String[] args) {
        boolean help = false;
        for (String arg : args) {
            if (arg.equals("-h") || arg.equals("-help") || arg.equals("--help")) {
                help = true;
            }
        }
        return new Args(help);
    }

    private boolean help;

    private Args(boolean help) {
        this.help = help;
    }

    public boolean empty() {
        return !this.help;
    }

    public boolean help() {
        return this.help;
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
}
