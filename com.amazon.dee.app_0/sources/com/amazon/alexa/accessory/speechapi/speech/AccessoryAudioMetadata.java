package com.amazon.alexa.accessory.speechapi.speech;

import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AccessoryAudioMetadata.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \f2\u00020\u0001:\u0001\fB!\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\t\u001a\u00020\u0003J\b\u0010\n\u001a\u0004\u0018\u00010\u0007J\u0006\u0010\u000b\u001a\u00020\u0005R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/speech/AccessoryAudioMetadata;", "", "accessoryAudioProfile", "Lcom/amazon/alexa/accessory/speechapi/speech/AccessoryAudioProfile;", "audioFormat", "", "accessoryWakeWord", "Lcom/amazon/alexa/accessory/speechapi/speech/AccessoryWakeWord;", "(Lcom/amazon/alexa/accessory/speechapi/speech/AccessoryAudioProfile;Ljava/lang/String;Lcom/amazon/alexa/accessory/speechapi/speech/AccessoryWakeWord;)V", "getAccessoryAudioProfile", "getAccessoryWakeWord", "getAudioFormat", "Companion", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AccessoryAudioMetadata {
    public static final Companion Companion = new Companion(null);
    private final AccessoryAudioProfile accessoryAudioProfile;
    private final AccessoryWakeWord accessoryWakeWord;
    private final String audioFormat;

    /* compiled from: AccessoryAudioMetadata.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nH\u0007¨\u0006\u000b"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/speech/AccessoryAudioMetadata$Companion;", "", "()V", "create", "Lcom/amazon/alexa/accessory/speechapi/speech/AccessoryAudioMetadata;", "accessoryAudioProfile", "Lcom/amazon/alexa/accessory/speechapi/speech/AccessoryAudioProfile;", "audioFormat", "", "accessoryWakeWord", "Lcom/amazon/alexa/accessory/speechapi/speech/AccessoryWakeWord;", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public static /* synthetic */ AccessoryAudioMetadata create$default(Companion companion, AccessoryAudioProfile accessoryAudioProfile, String str, AccessoryWakeWord accessoryWakeWord, int i, Object obj) {
            if ((i & 4) != 0) {
                accessoryWakeWord = null;
            }
            return companion.create(accessoryAudioProfile, str, accessoryWakeWord);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final AccessoryAudioMetadata create(@NotNull AccessoryAudioProfile accessoryAudioProfile, @NotNull String str) {
            return create$default(this, accessoryAudioProfile, str, null, 4, null);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final AccessoryAudioMetadata create(@NotNull AccessoryAudioProfile accessoryAudioProfile, @NotNull String audioFormat, @Nullable AccessoryWakeWord accessoryWakeWord) {
            Intrinsics.checkParameterIsNotNull(accessoryAudioProfile, "accessoryAudioProfile");
            Intrinsics.checkParameterIsNotNull(audioFormat, "audioFormat");
            return new AccessoryAudioMetadata(accessoryAudioProfile, audioFormat, accessoryWakeWord, null);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private AccessoryAudioMetadata(AccessoryAudioProfile accessoryAudioProfile, String str, AccessoryWakeWord accessoryWakeWord) {
        this.accessoryAudioProfile = accessoryAudioProfile;
        this.audioFormat = str;
        this.accessoryWakeWord = accessoryWakeWord;
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final AccessoryAudioMetadata create(@NotNull AccessoryAudioProfile accessoryAudioProfile, @NotNull String str) {
        return Companion.create$default(Companion, accessoryAudioProfile, str, null, 4, null);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final AccessoryAudioMetadata create(@NotNull AccessoryAudioProfile accessoryAudioProfile, @NotNull String str, @Nullable AccessoryWakeWord accessoryWakeWord) {
        return Companion.create(accessoryAudioProfile, str, accessoryWakeWord);
    }

    @NotNull
    public final AccessoryAudioProfile getAccessoryAudioProfile() {
        return this.accessoryAudioProfile;
    }

    @Nullable
    public final AccessoryWakeWord getAccessoryWakeWord() {
        return this.accessoryWakeWord;
    }

    @NotNull
    public final String getAudioFormat() {
        return this.audioFormat;
    }

    public /* synthetic */ AccessoryAudioMetadata(AccessoryAudioProfile accessoryAudioProfile, String str, AccessoryWakeWord accessoryWakeWord, DefaultConstructorMarker defaultConstructorMarker) {
        this(accessoryAudioProfile, str, accessoryWakeWord);
    }
}
