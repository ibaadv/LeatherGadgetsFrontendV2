package com.leathergadgets.LEATHERGADGETSBACKEND;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aboutyou")
//@RequiredArgsConstructor
public class AboutYouController {

    private final AboutYouService aboutYouService;

    public AboutYouController(AboutYouService aboutYouService) {
        this.aboutYouService = aboutYouService;
    }

    @PostMapping
    public ResponseEntity<aboutyou> saveAboutYou(@RequestBody aboutyou info) {
        aboutyou savedInfo = aboutYouService.saveAboutYou(info);
        return ResponseEntity.ok(savedInfo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<aboutyou> getAboutYou(@PathVariable Long id) {
        aboutyou info = aboutYouService.getAboutYou(id);
        return ResponseEntity.ok(info);
    }

    @GetMapping
    public ResponseEntity<List<aboutyou>> getAllAboutYou() {
        List<aboutyou> infos = aboutYouService.getAllAboutYou();
        return ResponseEntity.ok(infos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAboutYou(@PathVariable Long id) {
        aboutYouService.deleteAboutYou(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}
