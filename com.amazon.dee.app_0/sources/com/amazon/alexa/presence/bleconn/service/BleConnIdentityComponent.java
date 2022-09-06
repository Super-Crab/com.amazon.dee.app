package com.amazon.alexa.presence.bleconn.service;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.presence.bleconn.identity.IdentityClient;
import com.amazon.alexa.presence.bleconn.identity.IdentityController;
import com.amazon.alexa.presence.bleconn.identity.PlatformSupport;
import com.amazon.alexa.presence.bleconn.identity.clients.LocalResponseIdentityClient;
import com.amazon.alexa.presence.bleconn.identity.clients.PresenceIdentityClient;
import com.amazon.alexa.presence.bleconn.identity.encryption.EncryptionModuleFactory;
import com.amazon.alexa.presence.bleconn.identity.encryption.EncryptionModuleFactoryImpl;
import com.amazon.alexa.presence.bleconn.identity.model.IdentityRepo;
import com.amazon.alexa.presence.service.MapAuthTokenProvider;
import com.amazon.alexa.presence.service.PersistentMap;
import com.amazon.alexa.presence.service.PresenceServiceConfiguration;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes9.dex */
public class BleConnIdentityComponent {
    private static final String TAG = "com.amazon.alexa.presence.bleconn.service.BleConnIdentityComponent";
    private final PresenceServiceConfiguration config = PresenceServiceConfiguration.defaultConfiguration();
    private final IdentityController identityController;

    public BleConnIdentityComponent(Context context) {
        this.identityController = new IdentityController(new IdentityRepo(new PersistentMap(context)), newIdentityClient(), tokenEncryptionModuleFactory(), relationshipVerificationEncryptionModuleFactory(), platformSupport());
    }

    private PersonIdProvider getPersonIdProvider() {
        return (PersonIdProvider) GeneratedOutlineSupport1.outline21(PersonIdProvider.class);
    }

    private IdentityClient newIdentityClient() {
        if (this.config.shouldUseLocalShim()) {
            Log.i(TAG, "Using static identity shim");
            return new LocalResponseIdentityClient();
        }
        String identityEndpoint = this.config.getIdentityEndpoint();
        String str = TAG;
        Log.i(str, "Setting Identity Token endpoint = " + identityEndpoint);
        return new PresenceIdentityClient(identityEndpoint, new MapAuthTokenProvider(), getPersonIdProvider());
    }

    private PlatformSupport platformSupport() {
        return new PlatformSupport() { // from class: com.amazon.alexa.presence.bleconn.service.BleConnIdentityComponent.1
            @Override // com.amazon.alexa.presence.bleconn.identity.PlatformSupport
            public byte[] base64Decode(String str) {
                return Base64.decode(str, 0);
            }
        };
    }

    private EncryptionModuleFactory relationshipVerificationEncryptionModuleFactory() {
        return new EncryptionModuleFactoryImpl(EncryptionModuleFactoryImpl.EncryptionModes.RELATIONSHIP_TOKEN);
    }

    private EncryptionModuleFactory tokenEncryptionModuleFactory() {
        return new EncryptionModuleFactoryImpl(EncryptionModuleFactoryImpl.EncryptionModes.IDENTITY_TOKEN);
    }

    public IdentityController getIdentityController() {
        return this.identityController;
    }
}
