package com.latenitecode.javalings;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Javalings {

    public static String list() {
        HashMap<String, String> exercisesMap = new HashMap<>(100);
        Stream.of(new File("exercises").listFiles())
            .filter(file -> !file.isDirectory())
            .map(File::getPath)
            .filter(path -> path.contains(".java"))
            .forEach(path -> {
                exercisesMap.put(path.substring(path.length() - 8, path.length() - 5), path);
            });
        ArrayList<String> sortedKeys = new ArrayList<>(exercisesMap.keySet());
        Collections.sort(sortedKeys);
        return sortedKeys
            .stream()
            .map(key -> exercisesMap.get(key))
            .collect(Collectors.joining("\n"));
    }
}
