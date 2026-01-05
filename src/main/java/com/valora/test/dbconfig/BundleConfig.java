package com.valora.test.dbconfig;


import com.valora.library.dbbundle.core.ValoraBundleControl;
import com.valora.library.dbbundle.spi.TranslationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class BundleConfig {

    @Bean
    public ValoraBundleControl valoraBundleControl(TranslationProvider provider) {
        return new ValoraBundleControl(provider, 60);
    }
}
