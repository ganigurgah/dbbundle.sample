package com.valora.test.controller;

import com.valora.library.dbbundle.core.ValoraBundleControl;
import com.valora.library.dbbundle.producer.ValoraBundle;
import com.valora.test.entity.LanguageEntity;
import com.valora.test.entity.SystemConfigEntity;
import com.valora.test.repository.SystemConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/manage")
public class ManagementController {

    @ValoraBundle
    private final ValoraBundleControl control;
    @Autowired
    private final SystemConfigRepository configRepository;

    public ManagementController(ValoraBundleControl control, SystemConfigRepository configRepository) {
        this.control = control;
        this.configRepository = configRepository;
    }

    @GetMapping("/reload")
    public String reload() {
        try {
            String newLocaleTag = configRepository.findById("default_locale").map(SystemConfigEntity::getConfigValue).orElse("en");
            Locale.setDefault(Locale.forLanguageTag(newLocaleTag));
            control.reload();
            return "Sistem başarıyla yenilendi! Kullanılan dil: " + newLocaleTag;
        } catch (Exception e) {
            return "Yenileme sırasında hata oluştu: " + e.getMessage();
        }
    }

    @PostMapping("/addnew")
    public String addNew(@RequestBody LanguageEntity languageEntity) {
        control.addNewKey(languageEntity.getMsgKey(), languageEntity.getMsgValue(), languageEntity.getLocale());
        return "'" + languageEntity.getMsgKey() + "' değeri (" + languageEntity.getMsgValue() + ")" + " " + languageEntity.getLocale() + " dili için eklendi";
    }
}