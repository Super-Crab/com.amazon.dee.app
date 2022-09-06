package com.amazon.alexa.api.compat;

import java.util.ArrayList;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AlexaUserSpeechProviderMetadata.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0016\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\nJ\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaUserSpeechProviderMetadata;", "", "apiMetadata", "Lcom/amazon/alexa/api/AlexaUserSpeechProviderMetadata;", "(Lcom/amazon/alexa/api/AlexaUserSpeechProviderMetadata;)V", "getProviderScope", "Lcom/amazon/alexa/api/compat/AlexaUserSpeechProviderScope;", "getSoftwareVersion", "", "getSupportedWakeWords", "", "supportsServerInitiatedDialogs", "", "supportsWakeWord", "Companion", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public class AlexaUserSpeechProviderMetadata {
    public static final Companion Companion = new Companion(null);
    private static final String EMPTY_STRING = "";
    private final com.amazon.alexa.api.AlexaUserSpeechProviderMetadata apiMetadata;

    /* compiled from: AlexaUserSpeechProviderMetadata.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0001¢\u0006\u0002\b\tJ2\u0010\n\u001a\u00020\b2\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0007J<\u0010\n\u001a\u00020\b2\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004H\u0007J\n\u0010\u0012\u001a\u0004\u0018\u00010\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaUserSpeechProviderMetadata$Companion;", "", "()V", "EMPTY_STRING", "", "convertFromCompat", "Lcom/amazon/alexa/api/AlexaUserSpeechProviderMetadata;", "compatMetadata", "Lcom/amazon/alexa/api/compat/AlexaUserSpeechProviderMetadata;", "convertFromCompat$AlexaMobileAndroidVoiceSDKApiCompat_release", "create", "supportedInitiationTypes", "", "Lcom/amazon/alexa/api/compat/SupportedInitiationType;", "supportedWakeWords", "providerScope", "Lcom/amazon/alexa/api/compat/AlexaUserSpeechProviderScope;", "softwareVersion", "createLegacy", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final com.amazon.alexa.api.AlexaUserSpeechProviderMetadata convertFromCompat$AlexaMobileAndroidVoiceSDKApiCompat_release(@NotNull AlexaUserSpeechProviderMetadata compatMetadata) {
            Intrinsics.checkParameterIsNotNull(compatMetadata, "compatMetadata");
            return compatMetadata.apiMetadata;
        }

        @JvmStatic
        @NotNull
        public final AlexaUserSpeechProviderMetadata create(@Nullable Set<? extends SupportedInitiationType> set, @Nullable Set<String> set2, @Nullable AlexaUserSpeechProviderScope alexaUserSpeechProviderScope) {
            return create(set, set2, alexaUserSpeechProviderScope, "");
        }

        @JvmStatic
        @Nullable
        public final AlexaUserSpeechProviderMetadata createLegacy() {
            return new AlexaUserSpeechProviderMetadata(new com.amazon.alexa.api.AlexaUserSpeechProviderMetadata(true), null);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final AlexaUserSpeechProviderMetadata create(@Nullable Set<? extends SupportedInitiationType> set, @Nullable Set<String> set2, @Nullable AlexaUserSpeechProviderScope alexaUserSpeechProviderScope, @Nullable String str) {
            Set set3;
            int collectionSizeOrDefault;
            if (set != null) {
                collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(set, 10);
                ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
                for (SupportedInitiationType supportedInitiationType : set) {
                    arrayList.add(SupportedInitiationType.Companion.convertFromCompat(supportedInitiationType));
                }
                set3 = CollectionsKt___CollectionsKt.toSet(arrayList);
            } else {
                set3 = null;
            }
            com.amazon.alexa.api.AlexaUserSpeechProviderMetadata apiMetadata = com.amazon.alexa.api.AlexaUserSpeechProviderMetadata.create(set3, set2, AlexaUserSpeechProviderScope.Companion.convertFromCompat(alexaUserSpeechProviderScope), str);
            Intrinsics.checkExpressionValueIsNotNull(apiMetadata, "apiMetadata");
            return new AlexaUserSpeechProviderMetadata(apiMetadata, null);
        }
    }

    private AlexaUserSpeechProviderMetadata(com.amazon.alexa.api.AlexaUserSpeechProviderMetadata alexaUserSpeechProviderMetadata) {
        this.apiMetadata = alexaUserSpeechProviderMetadata;
    }

    @JvmStatic
    @NotNull
    public static final AlexaUserSpeechProviderMetadata create(@Nullable Set<? extends SupportedInitiationType> set, @Nullable Set<String> set2, @Nullable AlexaUserSpeechProviderScope alexaUserSpeechProviderScope) {
        return Companion.create(set, set2, alexaUserSpeechProviderScope);
    }

    @JvmStatic
    @NotNull
    public static final AlexaUserSpeechProviderMetadata create(@Nullable Set<? extends SupportedInitiationType> set, @Nullable Set<String> set2, @Nullable AlexaUserSpeechProviderScope alexaUserSpeechProviderScope, @Nullable String str) {
        return Companion.create(set, set2, alexaUserSpeechProviderScope, str);
    }

    @JvmStatic
    @Nullable
    public static final AlexaUserSpeechProviderMetadata createLegacy() {
        return Companion.createLegacy();
    }

    @Nullable
    public final AlexaUserSpeechProviderScope getProviderScope() {
        return AlexaUserSpeechProviderScope.Companion.convertToCompat(this.apiMetadata.getProviderScope());
    }

    @Nullable
    public final String getSoftwareVersion() {
        return this.apiMetadata.getSoftwareVersion();
    }

    @Nullable
    public final Set<String> getSupportedWakeWords() {
        return this.apiMetadata.getSupportedWakeWords();
    }

    public final boolean supportsServerInitiatedDialogs() {
        return this.apiMetadata.supportsServerInitiatedDialogs();
    }

    public final boolean supportsWakeWord() {
        return this.apiMetadata.supportsWakeWord();
    }

    public /* synthetic */ AlexaUserSpeechProviderMetadata(com.amazon.alexa.api.AlexaUserSpeechProviderMetadata alexaUserSpeechProviderMetadata, DefaultConstructorMarker defaultConstructorMarker) {
        this(alexaUserSpeechProviderMetadata);
    }
}
