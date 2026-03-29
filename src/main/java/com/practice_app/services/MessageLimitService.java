package com.practice_app.services;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class MessageLimitService {

    private final Map<Long, Integer> messageCount = new HashMap<>();
    private final Map<Long, LocalDate> lastReset = new HashMap<>();

    public boolean canSend(Long userId, boolean isMentor) {

        if (isMentor) return true;

        LocalDate today = LocalDate.now();

        // 🔥 reset daily
        if (!today.equals(lastReset.get(userId))) {
            messageCount.put(userId, 0);
            lastReset.put(userId, today);
        }

        return messageCount.getOrDefault(userId, 0) < 3;
    }

    public void increment(Long userId) {
        messageCount.put(userId, messageCount.getOrDefault(userId, 0) + 1);
    }
}