package com.valora.test.service;

import com.valora.library.dbbundle.spi.TranslationProvider;
import com.valora.test.entity.LanguageEntity;
import com.valora.test.repository.LanguageRepository;
import com.valora.test.repository.SystemConfigRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Primary
public class DatabaseTranslationProvider implements TranslationProvider {

    private final LanguageRepository repository;
    private final SystemConfigRepository configRepository;
    private Locale systemConfigLocale;

    public DatabaseTranslationProvider(LanguageRepository repository, SystemConfigRepository configRepository) {
        this.repository = repository;
        this.configRepository = configRepository;
    }

    @Override
    public Map<String, Object> loadTranslations(Locale locale) {
        Locale targetLocale = getDefaultLocaleFromDB();
        System.out.println(">>> Yüklenen Dil: " + targetLocale.getLanguage());

        System.out.println(">>> Kütüphane DB'ye erişiyor: Locale=" + targetLocale);
        return repository.findAll().stream()
                .filter(e -> e.getLocale().equalsIgnoreCase(targetLocale.getLanguage()))
                .collect(Collectors.toMap(
                        LanguageEntity::getMsgKey,
                        LanguageEntity::getMsgValue,
                        (existing, replacement) -> existing
                ));
    }

    @Override
    public void saveTranslation(String key, String value, String locale) {
        LanguageEntity entity = new LanguageEntity();
        entity.setMsgKey(key);
        entity.setMsgValue(value);
        entity.setLocale(locale);
        repository.save(entity);
    }

    private Locale getDefaultLocaleFromDB() {
        if (systemConfigLocale == null) {
            systemConfigLocale = configRepository.findById("default_locale")
                    .map(config -> Locale.forLanguageTag(config.getConfigValue()))
                    .orElse(Locale.ENGLISH); // DB'de ayar yoksa güvenli liman olarak 'en' dön
        }
        return systemConfigLocale;
    }
}