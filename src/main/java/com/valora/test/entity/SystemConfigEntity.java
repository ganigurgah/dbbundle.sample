package com.valora.test.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "SYSTEM_CONFIG")
public class SystemConfigEntity {
    @Id
    @Column(name="config_key")
    private String configKey;

    @Column(name = "config_value")
    private String configValue;

    public SystemConfigEntity() {
    }

    public SystemConfigEntity(String configKey, String configValue) {
        this.configKey = configKey;
        this.configValue = configValue;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }
}
