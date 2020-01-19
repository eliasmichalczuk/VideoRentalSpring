package com.elias.videorental;

import javax.transaction.Transactional;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import lombok.Data;

@Data

@Service
@ConfigurationProperties(prefix = "spring.flyway", ignoreUnknownFields = true, ignoreInvalidFields = true)
public class FlyWayProperties {
    private boolean enabled;
    private boolean migrate;
    private String locations;
    private String schemas;
    private boolean baselineOnMigrate;
}
