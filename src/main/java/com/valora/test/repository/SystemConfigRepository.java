package com.valora.test.repository;

import com.valora.test.entity.SystemConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemConfigRepository extends JpaRepository<SystemConfigEntity, String> {
    SystemConfigEntity findByConfigKey(String configKey);
}
