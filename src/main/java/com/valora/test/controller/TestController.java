package com.valora.test.controller;

import com.valora.library.dbbundle.core.ValoraBundleControl;
import com.valora.library.dbbundle.producer.ValoraBundle;
import jakarta.annotation.Nullable;
import jakarta.websocket.server.PathParam;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.ResourceBundle;

@RestController
public class TestController {

    @Autowired
    private ResourceBundle msg;


    @GetMapping("/msg/{key}")
    public String getMsg(@PathVariable String key, @Nullable @PathParam("lang") String lang) {
        try {
            return msg.getString(key);
        } catch (Exception e) {
            return "Anahtar bulunamadı: " + key + " (Hata: " + e.getMessage() + ")";
        }
    }
}