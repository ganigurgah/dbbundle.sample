package com.valora.test.init;

import com.valora.test.entity.LanguageEntity;
import com.valora.test.entity.SystemConfigEntity;
import com.valora.test.repository.LanguageRepository;
import com.valora.test.repository.SystemConfigRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final LanguageRepository repository;
    private final SystemConfigRepository configRepository;

    public DataInitializer(LanguageRepository repository, SystemConfigRepository configRepository) {
        this.repository = repository;
        this.configRepository = configRepository;
    }

    @Override
    public void run(String... args) {
        SystemConfigEntity configEntity = configRepository.findByConfigKey("default_locale");
        if (configEntity == null) {
            configRepository.save(new SystemConfigEntity("default_locale", "en"));
            if (repository.findByMsgKeyAndLocale("welcome", "tr") == null) {
                repository.deleteAll();
                repository.save(new LanguageEntity("welcome", "tr", "Hoş Geldiniz!"));
                repository.save(new LanguageEntity("welcome", "en", "Welcome!"));
                repository.save(new LanguageEntity("app.name", "tr", "Valora Test Sistemi"));
                System.out.println("--- Test verileri H2 veritabanına yüklendi ---");
            }
        }
    }
}