package com.leathergadgets.LEATHERGADGETSBACKEND;

import lombok.Data;

import java.util.List;

@Data
public class PreferenceRequest {
    private Long userId;
    private List<String> preferences;

    public PreferenceRequest(Long userId, List<String> preferences) {
        this.userId = userId;
        this.preferences = preferences;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<String> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<String> preferences) {
        this.preferences = preferences;
    }
}