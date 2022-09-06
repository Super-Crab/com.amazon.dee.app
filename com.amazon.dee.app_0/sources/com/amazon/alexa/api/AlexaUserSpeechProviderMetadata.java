package com.amazon.alexa.api;

import android.os.Bundle;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.validation.Preconditions;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes6.dex */
public class AlexaUserSpeechProviderMetadata {
    public static final String ALEXA_WAKE_WORD = "alexa";
    private static final String EMPTY_STRING = "";
    private final AlexaUserSpeechProviderScope providerScope;
    @Nullable
    private final String softwareVersion;
    private final Set<AlexaSupportedInitiationType> supportedInitiationTypes;
    private final Set<String> supportedWakeWords;

    /* loaded from: classes6.dex */
    static class Adapter implements u<AlexaUserSpeechProviderMetadata> {
        private static final String KEY_PROVIDER_TYPE = "providerType";
        private static final String KEY_SOFTWARE_VERSION = "softwareVersion";
        private static final String KEY_SUPPORTED_INITIATION_TYPES = "supportedInitiationTypes";
        private static final String KEY_SUPPORTED_WAKE_WORDS = "supportedWakeWords";

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.api.u
        /* renamed from: createFromBundle */
        public AlexaUserSpeechProviderMetadata mo844createFromBundle(Bundle bundle) {
            Preconditions.notNull(bundle, "Bundle can't be null");
            AlexaUserSpeechProviderScope valueOf = AlexaUserSpeechProviderScope.valueOf(bundle.getString(KEY_PROVIDER_TYPE, AlexaUserSpeechProviderScope.APPLICATION.name()));
            return AlexaUserSpeechProviderMetadata.create(new HashSet(BundleTransformer.getDefaultInstance().getCollectionFromBundle(bundle.getBundle(KEY_SUPPORTED_INITIATION_TYPES), AlexaSupportedInitiationType.class)), new HashSet(BundleTransformer.getDefaultInstance().getCollectionFromBundle(bundle.getBundle(KEY_SUPPORTED_WAKE_WORDS), String.class)), valueOf, bundle.getString("softwareVersion", ""));
        }

        @Override // com.amazon.alexa.api.u
        public Bundle toBundle(AlexaUserSpeechProviderMetadata alexaUserSpeechProviderMetadata) {
            Bundle bundle = new Bundle();
            bundle.putBundle(KEY_SUPPORTED_INITIATION_TYPES, BundleTransformer.getDefaultInstance().toBundle((Collection) alexaUserSpeechProviderMetadata.supportedInitiationTypes));
            bundle.putBundle(KEY_SUPPORTED_WAKE_WORDS, BundleTransformer.getDefaultInstance().toBundle((Collection) alexaUserSpeechProviderMetadata.supportedWakeWords));
            bundle.putString(KEY_PROVIDER_TYPE, alexaUserSpeechProviderMetadata.providerScope.name());
            bundle.putString("softwareVersion", alexaUserSpeechProviderMetadata.softwareVersion);
            return bundle;
        }
    }

    private AlexaUserSpeechProviderMetadata(Set<AlexaSupportedInitiationType> set, Set<String> set2, AlexaUserSpeechProviderScope alexaUserSpeechProviderScope, @Nullable String str) {
        Preconditions.notNull(set, "initiation types can't be null");
        Preconditions.notNull(set2, "wake word list can't be null");
        Preconditions.notNull(alexaUserSpeechProviderScope, "provider type can't be null");
        this.providerScope = alexaUserSpeechProviderScope;
        this.supportedInitiationTypes = set;
        this.supportedWakeWords = set2;
        this.softwareVersion = str;
    }

    @Deprecated
    public AlexaUserSpeechProviderMetadata(boolean z) {
        this(Collections.singleton(AlexaSupportedInitiationType.WAKE_WORD), Collections.singleton("alexa"), AlexaUserSpeechProviderScope.SYSTEM, "");
    }

    public static AlexaUserSpeechProviderMetadata create(Set<AlexaSupportedInitiationType> set, Set<String> set2, AlexaUserSpeechProviderScope alexaUserSpeechProviderScope) {
        return create(set, set2, alexaUserSpeechProviderScope, "");
    }

    public static AlexaUserSpeechProviderMetadata create(Set<AlexaSupportedInitiationType> set, Set<String> set2, AlexaUserSpeechProviderScope alexaUserSpeechProviderScope, String str) {
        return new AlexaUserSpeechProviderMetadata(set, set2, alexaUserSpeechProviderScope, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AlexaUserSpeechProviderMetadata createLegacy() {
        return new AlexaUserSpeechProviderMetadata(true);
    }

    public Bundle getBundle() {
        return new Adapter().toBundle(this);
    }

    public AlexaUserSpeechProviderScope getProviderScope() {
        return this.providerScope;
    }

    @Nullable
    public String getSoftwareVersion() {
        return this.softwareVersion;
    }

    public Set<String> getSupportedWakeWords() {
        return Collections.unmodifiableSet(this.supportedWakeWords);
    }

    public boolean supportsServerInitiatedDialogs() {
        return this.supportedInitiationTypes.contains(AlexaSupportedInitiationType.SERVER);
    }

    public boolean supportsWakeWord() {
        return this.supportedInitiationTypes.contains(AlexaSupportedInitiationType.WAKE_WORD);
    }
}
