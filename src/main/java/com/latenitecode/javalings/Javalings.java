package com.latenitecode.javalings;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

    public static String list() {
        return Javalings.list(true, true, 'a');
    }

    public static String list(boolean useName, boolean usePath, char status) {
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
        output.append(
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
                            String name = path
                                .substring(
                                    path.lastIndexOf('/') + 1,
                                    path.lastIndexOf('.')
                                );
                            builder.append(String.format("%-22s", name));
                        }
                        if (usePath) {
                            builder.append(String.format("%-44s", path));
                        }
                        builder
                            .append(
                                String.format(
                                    "%-8s",
                                    (progress.contains(path)) ? "Done" : "Not Done"
                                )
                            );
                        return builder.toString();
                    })
                    .collect(Collectors.joining("\n"))
            );
        return output.toString();
    }
}
