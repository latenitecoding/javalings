package com.latenitecode.javalings;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Javalings {

    private static TreeMap<String, String> getExercises() {
        TreeMap<String, String> exercises = new TreeMap<>();
        Stream.of(new File("exercises").listFiles())
            .filter(file -> !file.isDirectory())
            .map(File::getPath)
            .filter(path -> path.contains(".java"))
            .forEach(path -> {
                exercises.put(path.substring(path.length() - 8, path.length() - 5), path);
            });
        return exercises;
    }

    public static String list() {
        return Javalings.list(true, true, true, 'a');
    }

    public static String list(boolean useName, boolean usePath, boolean useStatus, char status) {
        TreeMap<String, String> exercises = Javalings.getExercises();
        StringBuilder output = new StringBuilder();
        if (useName) {
            output.append(String.format("%-22s", "Name"));
        }
        if (usePath) {
            output.append(String.format("%-44s", "Path"));
        }
        if (useStatus) {
            output.append(String.format("%-8s", "Status"));
        }
        output.append("\n");
        output.append(
                exercises
                    .keySet()
                    .stream()
                    .map(key -> {
                        String path = exercises.get(key);
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
                        if (useStatus) {
                            // TODO: check actual status
                            builder.append("Not Done");
                        }
                        return builder.toString();
                    })
                    .collect(Collectors.joining("\n"))
            );
        return output.toString();
    }
}
