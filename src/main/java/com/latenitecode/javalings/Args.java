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

  private static String formatCommand(String command, String desc) {
    return String.format("  %-20s%s", command, desc);
  }

  private static String formatOption(String option, String desc) {
    return String.format(
        "  %-20s%s",
        String.format("-%s, --%s", option.charAt(0), option),
        desc
      );
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
      if (arg.equals("run")) {
        assertNoCommand(command);
        command = Command.Run;
        continue;
      }
      if (arg.equals("verify")) {
        assertNoCommand(command);
        command = Command.Verify;
        continue;
      }
      if (arg.equals("watch")) {
        assertNoCommand(command);
        command = Command.Watch;
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

  public Set<String> getArgs() {
    return this.args;
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
    if (this.command == Command.List) {
      return String.join(
          "\n",
          "Usage: javalings list [-p] [-n] [-u] [-s]",
          "",
          "Lists the exercises available in Javalings",
          "",
          "Options:",
          Args.formatOption("paths", "show only the paths of the exercises"),
          Args.formatOption("names", "show only the names of the exercises"),
          Args.formatOption("unsolved", "display only exercises not yet solved"),
          Args.formatOption("solved", "display only exercises that have been solved"),
          Args.formatOption("help", "display usage information")
        );
    }
    if (this.command == Command.Run) {
      return String.join(
          "\n",
          "Usage: javalings run <name>",
          "",
          "Runs/Tests a single exercise",
          "",
          "Positional arguments:",
          Args.formatCommand("name", "the name of the exercise"),
          "",
          "Options:",
          Args.formatOption("help", "display usage information")
        );
    }
    if (this.command == Command.Verify) {
      return String.join(
          "\n",
          "Usage: javalings verify",
          "",
          "Verifies all exercises according to the recommended order",
          "",
          "Options:",
          Args.formatOption("help", "display usage information")
        );
    }
    return String.join(
        "\n",
        "Usage: javalings [-v] [<command>] [<args>]",
        "",
        "Javalings is a collection of small exercises to get you used to writing and reading Java code",
        "",
        "Options:",
        Args.formatOption("version", "show the executable version"),
        Args.formatOption("help", "display usage information"),
        "",
        "Commands:",
        Args.formatCommand(
          "verify",
          "Verifies all exercises according to the recommended order"
        ),
        Args.formatCommand("run", "Runs/Tests a single exercise"),
        Args.formatCommand("list", "Lists the exercises available in Javalings")
      );
  }

  public boolean run() {
    return this.command == Command.Run;
  }

  public boolean verify() {
    return this.command == Command.Verify;
  }

  public boolean version() {
    return this.option == Option.Version;
  }

  public boolean watch() {
    return this.command == Command.Watch;
  }

  public static enum Command {
    None,
    List,
    Run,
    Verify,
    Watch
  }

  public static enum Option {
    Help,
    None,
    Version
  }
}
