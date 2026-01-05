package com.valora.test.repository;

import com.valora.test.entity.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageRepository extends JpaRepository<LanguageEntity, String> {
    List<LanguageEntity> findByLocale(String locale);
    LanguageEntity findByMsgKeyAndLocale(String msgKey, String locale);
}