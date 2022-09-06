package com.amazon.alexa.accessory.speechapi;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AmbientSoundDispatcher.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u0006J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/AmbientSoundDispatcher;", "", "dispatch", "", "algorithm", "Lcom/amazon/alexa/accessory/speechapi/AmbientSoundDispatcher$Algorithm;", "Algorithm", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface AmbientSoundDispatcher {

    /* compiled from: AmbientSoundDispatcher.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0001\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\u000b"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/AmbientSoundDispatcher$Algorithm;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "PASSTHROUGH", "ANC", "NONE", "Companion", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public enum Algorithm {
        PASSTHROUGH("PASSTHROUGH"),
        ANC("ACTIVE_NOISE_CONTROL"),
        NONE("NONE");
        
        public static final Companion Companion = new Companion(null);
        private static final Map<String, Algorithm> map;
        @NotNull
        private final String value;

        /* compiled from: AmbientSoundDispatcher.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\u0005R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/AmbientSoundDispatcher$Algorithm$Companion;", "", "()V", com.amazon.alexa.auth.BuildConfig.FLAVOR_authtype, "", "", "Lcom/amazon/alexa/accessory/speechapi/AmbientSoundDispatcher$Algorithm;", "fromString", "value", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
        /* loaded from: classes6.dex */
        public static final class Companion {
            private Companion() {
            }

            @Nullable
            public final Algorithm fromString(@NotNull String value) {
                Intrinsics.checkParameterIsNotNull(value, "value");
                return (Algorithm) Algorithm.map.get(value);
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        static {
            int mapCapacity;
            int coerceAtLeast;
            Algorithm[] values = values();
            mapCapacity = MapsKt__MapsJVMKt.mapCapacity(values.length);
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
            LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
            for (Algorithm algorithm : values) {
                linkedHashMap.put(algorithm.value, algorithm);
            }
            map = linkedHashMap;
        }

        Algorithm(String str) {
            this.value = str;
        }

        @NotNull
        public final String getValue() {
            return this.value;
        }
    }

    boolean dispatch(@NotNull Algorithm algorithm);
}
