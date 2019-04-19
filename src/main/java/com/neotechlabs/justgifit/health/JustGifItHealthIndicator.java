package com.neotechlabs.justgifit.health;

import com.justgifit.JustGifItProperties;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

import javax.inject.Inject;

public class JustGifItHealthIndicator implements HealthIndicator {

    @Inject
    private JustGifItProperties properties;

    @Override
    public Health health() {
        if (!properties.getGifLoacation().canWrite()) {
            return Health.down().build();
        }
        return Health.up().build();
    }
}
