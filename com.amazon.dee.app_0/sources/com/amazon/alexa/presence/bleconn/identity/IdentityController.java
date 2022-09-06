package com.amazon.alexa.presence.bleconn.identity;

import com.amazon.alexa.presence.bleconn.helpers.ByteHelper;
import com.amazon.alexa.presence.bleconn.identity.encryption.DecryptionModule;
import com.amazon.alexa.presence.bleconn.identity.encryption.EncryptionModuleFactory;
import com.amazon.alexa.presence.bleconn.identity.model.BleIdentityCore;
import com.amazon.alexa.presence.bleconn.identity.model.BleIdentityPacket;
import com.amazon.alexa.presence.bleconn.identity.model.IdentityRepo;
import com.amazon.alexa.presence.bleconn.identity.model.Key;
import com.amazon.alexa.presence.bleconn.service.protocols.relationships.DecryptedRequesterRelationshipV1;
import com.amazon.alexa.presence.bleconn.service.protocols.relationships.EncryptedRelationshipTokenV1;
import com.amazon.alexa.presence.bleconn.service.protocols.relationships.RequesterRelationshipsPacketV1;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes9.dex */
public class IdentityController {
    private static final Logger LOG = LoggerFactory.getLogger(IdentityController.class);
    private final IdentityClient identityClient;
    private final IdentityRepo identityRepo;
    private final PlatformSupport platformSupport;
    private final EncryptionModuleFactory relationshipCipherFactory;
    private final EncryptionModuleFactory tokenCipherFactory;

    public IdentityController(IdentityRepo identityRepo, IdentityClient identityClient, EncryptionModuleFactory encryptionModuleFactory, EncryptionModuleFactory encryptionModuleFactory2, PlatformSupport platformSupport) {
        this.identityRepo = (IdentityRepo) Objects.requireNonNull(identityRepo, "identityRepo must not be null");
        this.identityClient = (IdentityClient) Objects.requireNonNull(identityClient, "identityClient must not be null");
        this.tokenCipherFactory = (EncryptionModuleFactory) Objects.requireNonNull(encryptionModuleFactory, "tokenEncryptionCipherFactory must not be null");
        this.relationshipCipherFactory = (EncryptionModuleFactory) Objects.requireNonNull(encryptionModuleFactory2, "relationshipCipherFactory must not be null");
        this.platformSupport = (PlatformSupport) Objects.requireNonNull(platformSupport, "cipherFactory must not be null");
    }

    private byte[] encryptedTokenFrom(BleIdentityPacket bleIdentityPacket) {
        try {
            LOG.info("Requested to vend identity token");
            String currentEncryptionKey = bleIdentityPacket.getCurrentEncryptionKey();
            if (currentEncryptionKey == null) {
                LOG.info("Unable to produce encrypted token. No valid encryption keyfound.");
                return null;
            }
            String currentToken = bleIdentityPacket.getCurrentToken();
            if (currentToken == null) {
                LOG.info("Unable to produce encrypted token. No valid token found.");
                return null;
            }
            LOG.debug("Encrypting identity token");
            LOG.debug("Key: %s", currentEncryptionKey);
            LOG.debug("Token: %s", currentToken);
            byte[] base64Decode = this.platformSupport.base64Decode(currentEncryptionKey);
            byte[] base64Decode2 = this.platformSupport.base64Decode(currentToken);
            LOG.debug("Token Size: %s bytes", Integer.valueOf(base64Decode2.length));
            return this.tokenCipherFactory.buildEncryptionModuleForKey(base64Decode).encrypt(base64Decode2);
        } catch (Throwable th) {
            throw new RuntimeException(th);
        }
    }

    private boolean shouldRefreshIdentityData(BleIdentityPacket bleIdentityPacket) {
        return bleIdentityPacket == null || bleIdentityPacket.isStale() || bleIdentityPacket.getCurrentToken() == null || bleIdentityPacket.getCurrentEncryptionKey() == null;
    }

    private BleIdentityPacket tryRefreshIdentityData() {
        LOG.info("Requesting new identity packet");
        BleIdentityCore v2BeaconIdentityPacket = this.identityClient.getV2BeaconIdentityPacket();
        if (v2BeaconIdentityPacket == null) {
            LOG.warn("Unable to retrieve a new identity packet");
            return null;
        }
        LOG.debug("Identity packet received. Saving locally.");
        BleIdentityPacket bleIdentityPacket = new BleIdentityPacket(v2BeaconIdentityPacket);
        this.identityRepo.store(bleIdentityPacket);
        return bleIdentityPacket;
    }

    public String clearTextToken() {
        LOG.debug("Requested to vend identity token");
        BleIdentityPacket retrieveIdentityData = this.identityRepo.retrieveIdentityData();
        if (retrieveIdentityData == null) {
            return null;
        }
        return retrieveIdentityData.getCurrentToken();
    }

    public MatchingRelationshipData getFirstMatchingRelationship(RequesterRelationshipsPacketV1 requesterRelationshipsPacketV1) {
        if (requesterRelationshipsPacketV1 == null) {
            LOG.info("Requester data not provided, no relationship matches.");
            return null;
        }
        List<Key> relationshipKeys = this.identityRepo.retrieveIdentityData().getRelationshipKeys();
        LOG.info("Checking for a match with one of %s loaded relationships", Integer.valueOf(relationshipKeys.size()));
        HashMap hashMap = new HashMap(relationshipKeys.size());
        for (Key key : relationshipKeys) {
            LOG.debug("Loading key %s", key.getKey());
            hashMap.put(key, this.relationshipCipherFactory.buildDecryptionModuleForKey(this.platformSupport.base64Decode(key.getKey())));
        }
        for (EncryptedRelationshipTokenV1 encryptedRelationshipTokenV1 : requesterRelationshipsPacketV1.relationships()) {
            byte[] encryptedRelationshipData = encryptedRelationshipTokenV1.getEncryptedRelationshipData();
            for (Map.Entry entry : hashMap.entrySet()) {
                DecryptedRequesterRelationshipV1 tryParse = DecryptedRequesterRelationshipV1.tryParse(((DecryptionModule) entry.getValue()).decrypt(encryptedRelationshipData));
                if (tryParse != null && tryParse.targetDataValidates()) {
                    LOG.info("Matching relationship with requester found.");
                    LOG.debug("Key: %s", ((Key) entry.getKey()).getKey());
                    LOG.debug("Matching relationship proof: (string) %s", new String(encryptedRelationshipTokenV1.getBytes(), Charset.defaultCharset()));
                    LOG.debug("Matching relationship proof: (hex) %s", ByteHelper.asHex(tryParse.getBytes()));
                    return new MatchingRelationshipData((Key) entry.getKey(), tryParse.getRequesterValidationData());
                }
            }
        }
        LOG.info("No relationship determined to match with requester.");
        return null;
    }

    public boolean refresh(boolean z) {
        try {
            BleIdentityPacket retrieveIdentityData = this.identityRepo.retrieveIdentityData();
            if (z || shouldRefreshIdentityData(retrieveIdentityData)) {
                LOG.debug("Refreshing saved identity data store.");
                retrieveIdentityData = tryRefreshIdentityData();
            }
            if (retrieveIdentityData == null) {
                return false;
            }
            return !retrieveIdentityData.isStale();
        } catch (Throwable th) {
            LOG.warn("Exception encountered while refreshing identity data", th);
            return false;
        }
    }

    public byte[] token() {
        LOG.debug("Requested to vend identity token");
        BleIdentityPacket retrieveIdentityData = this.identityRepo.retrieveIdentityData();
        if (retrieveIdentityData == null) {
            return null;
        }
        return encryptedTokenFrom(retrieveIdentityData);
    }
}
