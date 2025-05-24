package com.leathergadgets.LEATHERGADGETSBACKEND;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
//@RequiredArgsConstructor
public class AboutYouService {

    private final AboutYouRepository aboutYouRepository;

    public AboutYouService(AboutYouRepository aboutYouRepository) {
        this.aboutYouRepository = aboutYouRepository;
    }

    public aboutyou saveAboutYou(aboutyou info) {
        return aboutYouRepository.save(info);
    }

    public aboutyou getAboutYou(Long id) {
        return aboutYouRepository.findById(id).orElseThrow(() -> new RuntimeException("Information not found"));
    }

    public List<aboutyou> getAllAboutYou() {
        return aboutYouRepository.findAll();
    }

    public void deleteAboutYou(Long id) {
        aboutYouRepository.deleteById(id);
    }
}
