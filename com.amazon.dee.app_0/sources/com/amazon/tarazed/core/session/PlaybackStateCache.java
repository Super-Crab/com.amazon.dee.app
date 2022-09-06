package com.amazon.tarazed.core.session;

import android.content.Context;
import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.tarazed.core.Cache;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.session.sessionEvents.PlaybackStates;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.CompositeEncoder;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.MissingFieldException;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: PlaybackStateCache.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 \u00112\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u0011\u0012B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0016J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000bH\u0016R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/tarazed/core/session/PlaybackStateCache;", "Lcom/amazon/tarazed/core/Cache;", "Lcom/amazon/tarazed/core/session/PlaybackStateCache$PlaybackCached;", "context", "Landroid/content/Context;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "(Landroid/content/Context;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;)V", "playbackState", "", "getPlaybackState", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "setPlaybackState", "", "newState", "Companion", "PlaybackCached", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public class PlaybackStateCache extends Cache<PlaybackCached> {
    private static final String CACHE_DIR = "sessionPlaybackState";
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private String playbackState;

    /* compiled from: PlaybackStateCache.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/tarazed/core/session/PlaybackStateCache$Companion;", "", "()V", "CACHE_DIR", "", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: PlaybackStateCache.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0087\b\u0018\u0000 \u00142\u00020\u0001:\u0002\u0013\u0014B#\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bB\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u0013\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/amazon/tarazed/core/session/PlaybackStateCache$PlaybackCached;", "", "seen1", "", "state", "", "serializationConstructorMarker", "Lkotlinx/serialization/SerializationConstructorMarker;", "(ILjava/lang/String;Lkotlinx/serialization/SerializationConstructorMarker;)V", "(Ljava/lang/String;)V", "getState", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "$serializer", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    @Serializable
    /* loaded from: classes13.dex */
    public static final class PlaybackCached {
        public static final Companion Companion = new Companion(null);
        @NotNull
        private final String state;

        /* compiled from: PlaybackStateCache.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/amazon/tarazed/core/session/PlaybackStateCache$PlaybackCached$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/amazon/tarazed/core/session/PlaybackStateCache$PlaybackCached;", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
        /* loaded from: classes13.dex */
        public static final class Companion {
            private Companion() {
            }

            @NotNull
            public final KSerializer<PlaybackCached> serializer() {
                return PlaybackStateCache$PlaybackCached$$serializer.INSTANCE;
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ PlaybackCached(int i, @Nullable String str, @Nullable SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 1) != 0) {
                this.state = str;
                return;
            }
            throw new MissingFieldException("state");
        }

        public PlaybackCached(@NotNull String state) {
            Intrinsics.checkParameterIsNotNull(state, "state");
            this.state = state;
        }

        public static /* synthetic */ PlaybackCached copy$default(PlaybackCached playbackCached, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = playbackCached.state;
            }
            return playbackCached.copy(str);
        }

        @JvmStatic
        public static final void write$Self(@NotNull PlaybackCached self, @NotNull CompositeEncoder output, @NotNull SerialDescriptor serialDesc) {
            Intrinsics.checkParameterIsNotNull(self, "self");
            Intrinsics.checkParameterIsNotNull(output, "output");
            Intrinsics.checkParameterIsNotNull(serialDesc, "serialDesc");
            output.encodeStringElement(serialDesc, 0, self.state);
        }

        @NotNull
        public final String component1() {
            return this.state;
        }

        @NotNull
        public final PlaybackCached copy(@NotNull String state) {
            Intrinsics.checkParameterIsNotNull(state, "state");
            return new PlaybackCached(state);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                return (obj instanceof PlaybackCached) && Intrinsics.areEqual(this.state, ((PlaybackCached) obj).state);
            }
            return true;
        }

        @NotNull
        public final String getState() {
            return this.state;
        }

        public int hashCode() {
            String str = this.state;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        @NotNull
        public String toString() {
            return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("PlaybackCached(state="), this.state, ")");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlaybackStateCache(@NotNull Context context, @NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper) {
        super(context, logger, metricsHelper, PlaybackCached.Companion.serializer(), CACHE_DIR);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        this.playbackState = "inactive";
    }

    @NotNull
    public String getPlaybackState(@NotNull String sessionId) {
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        if (Intrinsics.areEqual(this.playbackState, "inactive")) {
            PlaybackCached playbackCached = get(sessionId);
            String state = playbackCached != null ? playbackCached.getState() : null;
            if (state != null) {
                this.playbackState = state;
            }
        }
        return this.playbackState;
    }

    public void setPlaybackState(@NotNull String sessionId, @NotNull String newState) {
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(newState, "newState");
        this.playbackState = newState;
        if (Intrinsics.areEqual(this.playbackState, PlaybackStates.SUSPENDED)) {
            put(sessionId, new PlaybackCached(this.playbackState));
        }
    }
}
