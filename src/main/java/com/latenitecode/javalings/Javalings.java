package com.latenitecode.javalings;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Collection;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Javalings {

  private static TreeMap<String, String> getExercises() {
    TreeMap<String, String> exercises = new TreeMap<>();
    Stream
        .of(new File("exercises").listFiles())
        .filter(file -> !file.isDirectory())
        .map(File::getPath)
        .filter(path -> path.contains(".java"))
        .forEach(path -> {
          exercises.put(path.substring(path.length() - 8, path.length() - 5), path);
        });
    return exercises;
  }

  private static TreeSet<String> getProgress() {
    try {
      return Files
          .lines(Path.of(".progress"))
          .collect(Collectors.toCollection(TreeSet::new));
    } catch (IOException e) {
      System.err.println(e);
      return new TreeSet<>();
    }
  }

  private static void report(int passing, int total) {
    StringBuilder progress = new StringBuilder();
    if (passing > 0) {
      progress.append("\033[0;32m");
    }
    for (int i = 0; i < passing - 1; i++) {
      progress.append('#');
    }
    if (passing > 0) {
      progress.append('>');
      progress.append("\033[0m");
    }
    if (passing < total) {
      progress.append("\033[0;31m");
    }
    for (int i = passing; i < total; i++) {
      progress.append('-');
    }
    if (passing < total) {
      progress.append("\033[0m");
    }
    System.out.printf(
          "Progress: [%s] %d/%d (%.1f %%)\n",
          progress.toString(),
          passing,
          total,
          (double) passing / total
        );
  }

  private static Result watch(WatchService watcher, WatchKey wk, String exercise) {
    try {
      String name = exercise.substring(exercise.lastIndexOf("/") + 1, exercise.lastIndexOf("."));

      Result run = Javalings.run(name);
      System.out.printf("\n%s\n", run);
      if (run.ok()) {
        return run;
      }

      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

      while (true) {
        final WatchKey wkEvents = watcher.poll();

        if (wkEvents != null) {
          boolean triggered = false;
          for (WatchEvent<?> event : wkEvents.pollEvents()) {
            final Path entry = (Path) event.context();
            if (!entry.endsWith(exercise)) {
              triggered = true;
            }
          }

          if (triggered) {
            run = Javalings.run(name);
            System.out.printf("\n%s\n", run);
            if (run.ok()) {
              reader.close();
              return run;
            }
          }
        }

        if (reader.ready()) {
          String cmd = reader.readLine().toLowerCase();
          if (cmd.equals("hint")) {
            System.out.println(Javalings.hint(name));
          } else if (cmd.equals("exit")) {
            reader.close();
            return new Result(false, "Watch exited");
          }
        }
      }
    } catch (IOException e) {
      System.err.println(e);
      return new Result(false, String.format("Unable to watch %s", exercise));
    }
  }

  public static Result hint(String name) {
    try {
      return new Result(true, Files.readString(Path.of(String.format(".hints/%s.hint", name))));
    } catch (IOException e) {
      System.err.println(e);
      return new Result(false, String.format("Cannot read hint for %s", name));
    }
  }

  public static Result list() {
    return Javalings.list(true, true, 'a');
  }

  public static Result list(boolean useName, boolean usePath, char status) {
    StringBuilder output = new StringBuilder();
    if (useName) {
      output.append(String.format("%-22s", "Name"));
    }
    if (usePath) {
      output.append(String.format("%-44s", "Path"));
    }
    output.append(String.format("%-8s", "Status"));
    output.append("\n");

    TreeMap<String, String> exercises = Javalings.getExercises();
    TreeSet<String> progress = Javalings.getProgress();
    output
        .append(
          exercises
              .keySet()
              .stream()
              .map(key -> exercises.get(key))
              .filter(path -> {
                return status == 'a' || status == 'u' && !progress.contains(path)
                    || status == 's' && progress.contains(path);
              })
              .map(path -> {
                StringBuilder builder = new StringBuilder();
                if (useName) {
                  String name = path.substring(path.lastIndexOf('/') + 1, path.lastIndexOf('.'));
                  builder.append(String.format("%-22s", name));
                }
                if (usePath) {
                  builder.append(String.format("%-44s", path));
                }
                builder.append(
                      String.format("%-8s", (progress.contains(path)) ? "Done" : "Not Done")
                    );
                return builder.toString();
              })
              .collect(Collectors.joining("\n"))
        );
    return new Result(true, output.toString());
  }

  public static Result run(String name) {
    try {
      boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
      String javac = String.format("javac -d target/exercises exercises/%s.java", name);
      String java = String.format("java -cp target/exercises %s", name);

      ProcessBuilder builder = new ProcessBuilder();
      if (isWindows) {
        builder.command("cmd.exe", "/c", String.format("%s ; %s", javac, java));
      } else {
        builder.command("bash", "-c", String.format("%s ; %s", javac, java));
      }

      Process proc = builder.start();
      int exitCode = proc.waitFor();
      StringBuilder output = new StringBuilder();
      BufferedReader reader;

      if (exitCode != 0) {
        reader = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
        output.append(reader.lines().collect(Collectors.joining("\n")));
        output.append("\n\033[0;31m");
        output.append(
              String.format("\u26A0 Testing of exercises/%s.java failed! Please try again.", name)
            );
        output.append("\033[0m");
        reader.close();
        return new Result(false, output.toString());
      }

      boolean flag = Files
          .lines(Path.of(String.format("exercises/%s.java", name)))
          .filter(line -> line.equals("// TODO: I AM NOT DONE"))
          .findFirst()
          .isPresent();

      if (flag) {
        output.append("\n\033[0;31m");
        output.append(
              String.format(
                    "You can keep working on this exercise `exercises/%s.java`, or jump into the\n",
                    name
                  )
            );
        output.append("the next one by removing the TODO comment:\n\n");
        output.append("\u26A0 // TODO: I AM NOT DONE\n");
        output.append("\033[0m");
        return new Result(false, output.toString());
      }

      reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
      output.append(reader.lines().collect(Collectors.joining("\n")));
      output.append("\n\033[0;32m");
      output.append(String.format("\u2705 Successfully ran exercises/%s.java", name));
      output.append("\033[0m");
      reader.close();
      return new Result(true, output.toString());
    } catch (InterruptedException | IOException e) {
      System.err.println(e);
      return new Result(false, String.format("Unable to run %s", name));
    }
  }

  public static Result verify(boolean shouldReport) {
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(".progress"));
      Collection<String> exercises = Javalings.getExercises().values();
      int passCount = 0;
      for (String exercise : exercises) {
        Result run = Javalings.run(
              exercise.substring(exercise.lastIndexOf("/") + 1, exercise.lastIndexOf("."))
            );
        if (!run.ok()) {
          writer.close();
          return run;
        }
        passCount++;
        writer.write(exercise);
        writer.flush();
        if (shouldReport) {
          Javalings.report(passCount, exercises.size());
        }
      }
      writer.close();
      return new Result(true, "Congratulations!");
    } catch (IOException e) {
      System.err.println(e);
      return new Result(false, "Unable to verify exercises");
    }
  }

  public static Result watch() {
    WatchService watcher = null;
    WatchKey wk = null;
    try {
      watcher = FileSystems.getDefault().newWatchService();
      wk = Paths.get("exercises").register(watcher, ENTRY_MODIFY);
      for (String exercise : Javalings.getExercises().values()) {
        Result res = Javalings.watch(watcher, wk, exercise);
        if (!res.ok()) {
          return res;
        }
      }
      wk.cancel();
      return new Result(true, "Congratulations");
    } catch (IOException e) {
      return new Result(false, "Unable to complete watch");
    }
  }

  public static class Result {
    
    private String msg;
    private boolean ok;

    public Result(boolean ok, String msg) {
      this.ok = ok;
      this.msg = msg;
    }

    public boolean ok() {
      return this.ok;
    }

    public String toString() {
      return this.msg;
    }
  }
}
