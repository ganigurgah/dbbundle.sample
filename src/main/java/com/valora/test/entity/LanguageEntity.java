package com.valora.test.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "translations")
public class LanguageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "msg_key")
    private String msgKey;

    private String locale;

    @Column(name = "msg_value", length = 2000)
    private String msgValue;

    public LanguageEntity() {
    }

    public LanguageEntity(String msgKey, String locale, String msgValue) {
        this.msgKey = msgKey;
        this.locale = locale;
        this.msgValue = msgValue;
    }

    public String getMsgKey() {
        return msgKey;
    }

    public String getMsgValue() {
        return msgValue;
    }

    public void setMsgKey(String msgKey) {
        this.msgKey = msgKey;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void setMsgValue(String msgValue) {
        this.msgValue = msgValue;
    }
}