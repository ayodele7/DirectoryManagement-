package com.example.DirectoryApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import com.example.DirectoryApp.Service.DirectoryManager;

@RestController
@RequestMapping("/directory")
public class DirectoryController {

    private final DirectoryManager directoryManager;

    @Autowired
    public DirectoryController(DirectoryManager directoryManager) {
        this.directoryManager = directoryManager;
    }

    @PostMapping("/create")
    public Map<String, String> create(@RequestBody String path) {
        return Map.of("message", directoryManager.create(path));
    }

    @GetMapping("/list")
    public Map<String, String> list() {
        return Map.of("structure", directoryManager.list());
    }

    @PostMapping("/move")
    public Map<String, String> move(@RequestBody String source, @RequestBody String destination) {
        return Map.of("message", directoryManager.move(source, destination));
    }

    @DeleteMapping("/delete")
    public Map<String, String> delete(@RequestBody String path) {
        return Map.of("message", directoryManager.delete(path));
    }
}
