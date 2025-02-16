package com.example.DirectoryApp.Service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class DirectoryManager {
    private final Map<String, List<String>> directoryMap = new TreeMap<>();

    public String create(String path) {
        if (directoryMap.containsKey(path)) {
            return "Directory already exists: " + path;
        }

        String parent = getParentPath(path);
        if (!parent.isEmpty() && !directoryMap.containsKey(parent)) {
            return "Cannot create " + path + " - parent does not exist";
        }

        directoryMap.put(path, new ArrayList<>());
        if (!parent.isEmpty()) {
            directoryMap.get(parent).add(path);
        }

        return "CREATE " + path;
    }

    public String list() {
        StringBuilder result = new StringBuilder("LIST\n");
        for (String dir : directoryMap.keySet()) {
            if (!dir.contains("/")) { // Top-level directories
                printHierarchy(dir, 0, result);
            }
        }
        return result.toString();
    }

    private void printHierarchy(String dir, int level, StringBuilder result) {
        result.append("  ".repeat(level)).append(dir.substring(dir.lastIndexOf("/") + 1)).append("\n");
        for (String child : directoryMap.getOrDefault(dir, new ArrayList<>())) {
            printHierarchy(child, level + 1, result);
        }
    }

    public String move(String src, String dest) {
        if (!directoryMap.containsKey(src)) {
            return "Cannot move " + src + " - does not exist";
        }
        if (!directoryMap.containsKey(dest)) {
            return "Cannot move " + src + " - destination does not exist";
        }

        String parent = getParentPath(src);
        if (!parent.isEmpty()) {
            directoryMap.get(parent).remove(src);
        }

        directoryMap.get(dest).add(src);
        return "MOVE " + src + " " + dest;
    }

    public String delete(String path) {
        if (!directoryMap.containsKey(path)) {
            return "Cannot delete " + path + " - does not exist";
        }

        deleteRecursively(path);
        return "DELETE " + path;
    }

    private void deleteRecursively(String path) {
        for (String child : new ArrayList<>(directoryMap.get(path))) {
            deleteRecursively(child);
        }
        directoryMap.remove(path);
    }

    private String getParentPath(String path) {
        int lastSlash = path.lastIndexOf("/");
        return lastSlash == -1 ? "" : path.substring(0, lastSlash);
    }
}

