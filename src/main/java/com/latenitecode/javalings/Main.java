package com.latenitecode.javalings;

/** The Javalings executable. */
public class Main {

  public static void main(String[] args) {
    Args cli = Args.parse("v20230522", args);
    if (cli.empty()) {
      System.out.println(
          String.join(
            "\n", 
            "",
            "Hello and",
            "   welcome to...             ",
            "    _                   _ _                  ",
            "   (_) __ _ _   _  __ _| (_)_ __   __ _ ___  ",
            "   | |/ _` | | | |/ _` | | | '_ \\ / _` / __| ",
            "   | | (_| | |_| | (_| | | | | | | (_| \\__ \\ ",
            " __| |\\__,_|\\___/ \\__,_|_|_|_| |_|\\__, |___/ ",
            "|___/                             |___/      ",
            "",
            "Thanks for installing Javalings!",
            "",
            "Is this your first time? Don't worry, Javalings was made for beginners! We are",
            "going to teach you a lot of things about Java, but before we can get started,",
            "here's a couple of notes about how Javalings operates:",
            "",
            "1. The central concept behind Javalings is that you solve exercises. These",
            "   exercises usually have some sort of syntax error in them, which will cause",
            "   them to fail compilation or testing. Sometimes there's a logic error instead",
            "   of a syntax error. No matter what error, it's your job to find it and fix it!",
            "   You'll know when you fixed it because then, the exercise will compile and",
            "   Javalings will be able to move on to the next exercise.",
            "",
            "2. If you run Javalings in watch mode (which we recommend), it'll automatically",
            "   start with the first exercise. Don't get confused by an error message popping",
            "   up as soon as you run Javalings! This is part of the exercise that you're",
            "   supposed to solve, so open the exercise file in an editor and start your",
            "   detective work!",
            "",
            "3. If you're stuck on an exercise, there is a helpful hint you can view by",
            "   running `javalings hint exercise_name`, or by using `hint` in watch mode.",
            "",
            "4. If an exercise doesn't make sense to you, feel free to open an issue on",
            "   GitHub! (https://github.com/latenitecode/javalings/issues/new). We look at",
            "   every issue, and sometimes, other learners do too so you can help each",
            "   other out!",
            "",
            "Got all that? Great! To get started, run `javalings watch` in order to get the",
            "first exercise. Make sure to have your editor open!"
          )
        );
      System.exit(0);
    }
    if (cli.help()) {
      System.out.println(cli);
      System.exit(0);
    }
    if (cli.version()) {
      System.out.println(cli.getVersion());
      System.exit(0);
    }
    Javalings.Result res = null;
    if (cli.hint()) {
      res = Javalings.hint(cli.getArgs().iterator().next());
      System.out.printf("\n%s", res);
    }
    if (cli.list()) {
      res = Javalings
          .list(
            cli.hasLongOrShortArg("names") || !cli.hasLongOrShortArg("paths"),
            cli.hasLongOrShortArg("paths") || !cli.hasLongOrShortArg("names"),
            (cli.hasLongOrShortArg("unsolved"))
              ? 'u'
              : (cli.hasLongOrShortArg("solved"))
                ? 's'
                : 'a'
          );
      System.out.println(res);
    }
    if (cli.run()) {
      res = Javalings.run(cli.getArgs().iterator().next());
      System.out.println(res);
    }
    if (cli.verify()) {
      res = Javalings.verify(true);
      System.out.printf("\n%s\n", res);
    }
    if (cli.watch()) {
      res = Javalings.watch();
      System.out.println(res);
    }
    System.exit((res != null && res.ok()) ? 0 : -1);
  }
}
