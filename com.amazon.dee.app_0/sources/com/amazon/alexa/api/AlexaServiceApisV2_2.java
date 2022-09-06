package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Messenger;
import androidx.annotation.Nullable;
import com.amazon.alexa.CGv;
import com.amazon.alexa.Mlj;
import com.amazon.alexa.UYN;
import java.util.HashSet;
/* loaded from: classes6.dex */
public class AlexaServiceApisV2_2 extends AlexaServicesApisV2_1 {
    public final Mlj contextsProviderFactory;

    /* renamed from: com.amazon.alexa.api.AlexaServiceApisV2_2$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType = new int[AlexaServicesMessageType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.REGISTER_CONTEXTS_PROVIDER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.DEREGISTER_CONTEXTS_PROVIDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.CACHE_CONTEXTS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.CLEAR_CONTEXT_CACHE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.SET_CONTEXT_CACHING_ENABLED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public AlexaServiceApisV2_2(AlexaClient alexaClient, UYN uyn, CGv cGv, Mlj mlj) {
        super(alexaClient, uyn, cGv);
        this.contextsProviderFactory = mlj;
    }

    private void cacheContexts(Bundle bundle) {
        this.alexaClient.cacheContexts(getClient(bundle), new HashSet(BundleTransformer.getDefaultInstance().getCollectionFromBundle(Bundles.getBundle(bundle, AlexaServicesArgumentKey.ALEXA_CONTEXTS), AlexaContext.class)));
    }

    private void clearContextCache(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        Bundle optionalBundle = Bundles.getOptionalBundle(bundle, AlexaServicesArgumentKey.NAMESPACES);
        if (optionalBundle != null) {
            this.alexaClient.clearContextCache(client, new HashSet(BundleTransformer.getDefaultInstance().getCollectionFromBundle(optionalBundle, String.class)));
            return;
        }
        this.alexaClient.clearContextCache(client);
    }

    private void deregisterContextsProvider(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.deregisterContextsProvider(client, this.contextsProviderFactory.zZm(Bundles.getBinder(bundle, AlexaServicesArgumentKey.CONTEXTS_PROVIDER), client));
    }

    private boolean isClientVersionSupported(Bundle bundle) {
        return Versions.isPayloadSupportedByVersion(bundle, Versions.V2_2_0);
    }

    private void registerContextsProvider(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.registerContextsProvider(client, this.contextsProviderFactory.zZm(Bundles.getBinder(bundle, AlexaServicesArgumentKey.CONTEXTS_PROVIDER), client));
    }

    private void setContextCacheEnabled(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        boolean z = Bundles.getBoolean(bundle, AlexaServicesArgumentKey.ENABLE_CONTEXT_CACHING);
        Bundle optionalBundle = Bundles.getOptionalBundle(bundle, AlexaServicesArgumentKey.NAMESPACES);
        if (optionalBundle != null) {
            this.alexaClient.setContextCachingEnabled(client, new HashSet(BundleTransformer.getDefaultInstance().getCollectionFromBundle(optionalBundle, String.class)), z);
            return;
        }
        this.alexaClient.setContextCachingEnabled(client, z);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.api.AlexaServicesApisV2_1, com.amazon.alexa.api.AlexaServicesApisV1, com.amazon.alexa.api.AlexaServicesV2, com.amazon.alexa.api.AlexaServicesV1, com.amazon.alexa.api.messages.MessageProcessor
    public void processMessage(AlexaServicesMessageType alexaServicesMessageType, Bundle bundle, @Nullable Messenger messenger) {
        if (!isClientVersionSupported(bundle)) {
            super.processMessage(alexaServicesMessageType, bundle, messenger);
            return;
        }
        switch (alexaServicesMessageType.ordinal()) {
            case 56:
                registerContextsProvider(bundle);
                return;
            case 57:
                deregisterContextsProvider(bundle);
                return;
            case 58:
                cacheContexts(bundle);
                return;
            case 59:
                clearContextCache(bundle);
                return;
            case 60:
                setContextCacheEnabled(bundle);
                return;
            default:
                super.processMessage(alexaServicesMessageType, bundle, messenger);
                return;
        }
    }
}
