package com.amazon.alexa.accessory.speechapi.speech;

import com.amazon.alexa.accessory.speechapi.speech.UserSpeechProvider;
import java.util.Collections;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: UserSpeechProviderMetadata.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B5\b\u0002\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\nJ\u0006\u0010\u000b\u001a\u00020\bJ\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003J\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u000fR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/speech/UserSpeechProviderMetadata;", "", "supportedInitiationTypes", "", "Lcom/amazon/alexa/accessory/speechapi/speech/UserSpeechProvider$SupportedInitiationType;", "supportedWakeWords", "", "providerScope", "Lcom/amazon/alexa/accessory/speechapi/speech/UserSpeechProvider$Scope;", "softwareVersion", "(Ljava/util/Set;Ljava/util/Set;Lcom/amazon/alexa/accessory/speechapi/speech/UserSpeechProvider$Scope;Ljava/lang/String;)V", "getProviderScope", "getSupportedInitiationTypes", "getSupportedWakeWords", "supportsServerInitiatedDialogs", "", "supportsWakeWord", "Companion", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class UserSpeechProviderMetadata {
    @NotNull
    public static final String ALEXA_WAKE_WORD = "alexa";
    public static final Companion Companion = new Companion(null);
    private static final String EMPTY_STRING = "";
    private final UserSpeechProvider.Scope providerScope;
    private final String softwareVersion;
    private final Set<UserSpeechProvider.SupportedInitiationType> supportedInitiationTypes;
    private final Set<String> supportedWakeWords;

    /* compiled from: UserSpeechProviderMetadata.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J8\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\t2\u0006\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/speech/UserSpeechProviderMetadata$Companion;", "", "()V", "ALEXA_WAKE_WORD", "", "EMPTY_STRING", "create", "Lcom/amazon/alexa/accessory/speechapi/speech/UserSpeechProviderMetadata;", "supportedInitiationTypes", "", "Lcom/amazon/alexa/accessory/speechapi/speech/UserSpeechProvider$SupportedInitiationType;", "supportedWakeWords", "providerScope", "Lcom/amazon/alexa/accessory/speechapi/speech/UserSpeechProvider$Scope;", "softwareVersion", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public static /* synthetic */ UserSpeechProviderMetadata create$default(Companion companion, Set set, Set set2, UserSpeechProvider.Scope scope, String str, int i, Object obj) {
            if ((i & 8) != 0) {
                str = "";
            }
            return companion.create(set, set2, scope, str);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final UserSpeechProviderMetadata create(@NotNull Set<? extends UserSpeechProvider.SupportedInitiationType> set, @NotNull Set<String> set2, @NotNull UserSpeechProvider.Scope scope) {
            return create$default(this, set, set2, scope, null, 8, null);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final UserSpeechProviderMetadata create(@NotNull Set<? extends UserSpeechProvider.SupportedInitiationType> supportedInitiationTypes, @NotNull Set<String> supportedWakeWords, @NotNull UserSpeechProvider.Scope providerScope, @Nullable String str) {
            Intrinsics.checkParameterIsNotNull(supportedInitiationTypes, "supportedInitiationTypes");
            Intrinsics.checkParameterIsNotNull(supportedWakeWords, "supportedWakeWords");
            Intrinsics.checkParameterIsNotNull(providerScope, "providerScope");
            return new UserSpeechProviderMetadata(supportedInitiationTypes, supportedWakeWords, providerScope, str, null);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private UserSpeechProviderMetadata(Set<? extends UserSpeechProvider.SupportedInitiationType> set, Set<String> set2, UserSpeechProvider.Scope scope, String str) {
        if (set != 0) {
            if (set2 == null) {
                throw new IllegalStateException("wake word list can't be null".toString());
            }
            if (scope != null) {
                this.providerScope = scope;
                this.supportedInitiationTypes = set;
                this.supportedWakeWords = set2;
                this.softwareVersion = str;
                return;
            }
            throw new IllegalStateException("provider type can't be null".toString());
        }
        throw new IllegalStateException("initiation types can't be null".toString());
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final UserSpeechProviderMetadata create(@NotNull Set<? extends UserSpeechProvider.SupportedInitiationType> set, @NotNull Set<String> set2, @NotNull UserSpeechProvider.Scope scope) {
        return Companion.create$default(Companion, set, set2, scope, null, 8, null);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final UserSpeechProviderMetadata create(@NotNull Set<? extends UserSpeechProvider.SupportedInitiationType> set, @NotNull Set<String> set2, @NotNull UserSpeechProvider.Scope scope, @Nullable String str) {
        return Companion.create(set, set2, scope, str);
    }

    @NotNull
    public final UserSpeechProvider.Scope getProviderScope() {
        return this.providerScope;
    }

    @NotNull
    public final Set<UserSpeechProvider.SupportedInitiationType> getSupportedInitiationTypes() {
        Set<UserSpeechProvider.SupportedInitiationType> unmodifiableSet = Collections.unmodifiableSet(this.supportedInitiationTypes);
        Intrinsics.checkExpressionValueIsNotNull(unmodifiableSet, "Collections.unmodifiable…supportedInitiationTypes)");
        return unmodifiableSet;
    }

    @NotNull
    public final Set<String> getSupportedWakeWords() {
        Set<String> unmodifiableSet = Collections.unmodifiableSet(this.supportedWakeWords);
        Intrinsics.checkExpressionValueIsNotNull(unmodifiableSet, "Collections.unmodifiable…ring>(supportedWakeWords)");
        return unmodifiableSet;
    }

    public final boolean supportsServerInitiatedDialogs() {
        return this.supportedInitiationTypes.contains(UserSpeechProvider.SupportedInitiationType.SERVER);
    }

    public final boolean supportsWakeWord() {
        return this.supportedInitiationTypes.contains(UserSpeechProvider.SupportedInitiationType.WAKE_WORD);
    }

    public /* synthetic */ UserSpeechProviderMetadata(Set set, Set set2, UserSpeechProvider.Scope scope, String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(set, set2, scope, str);
    }
}
