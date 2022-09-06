package com.amazon.alexa.presence.bleconn.identity.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes9.dex */
public class BleIdentityCore {
    private static final Logger LOG = LoggerFactory.getLogger(BleIdentityCore.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private List<IdentityToken> tokens = new ArrayList();
    private List<Key> encryptionKeys = new ArrayList();
    private List<Key> relationshipVerificationKeys = new ArrayList();

    static {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    public BleIdentityCore() {
    }

    public static BleIdentityCore deserialize(String str) {
        try {
            return (BleIdentityCore) MAPPER.readValue(str, BleIdentityCore.class);
        } catch (Throwable th) {
            LOG.warn("Unable to deserialize packet", th);
            return null;
        }
    }

    public static String serialize(BleIdentityCore bleIdentityCore) {
        Objects.requireNonNull(bleIdentityCore);
        try {
            return MAPPER.writeValueAsString(bleIdentityCore);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Key> getEncryptionKeys() {
        return this.encryptionKeys;
    }

    public List<Key> getRelationshipVerificationKeys() {
        return this.relationshipVerificationKeys;
    }

    public List<IdentityToken> getTokens() {
        return this.tokens;
    }

    public void setEncryptionKeys(List<Key> list) {
        this.encryptionKeys = list;
    }

    public void setRelationshipVerificationKeys(List<Key> list) {
        this.relationshipVerificationKeys = list;
    }

    public void setTokens(List<IdentityToken> list) {
        this.tokens = list;
    }

    public BleIdentityCore(List<IdentityToken> list, List<Key> list2, List<Key> list3) {
        this.tokens.addAll(list);
        this.encryptionKeys.addAll(list2);
        this.relationshipVerificationKeys.addAll(list3);
    }
}
