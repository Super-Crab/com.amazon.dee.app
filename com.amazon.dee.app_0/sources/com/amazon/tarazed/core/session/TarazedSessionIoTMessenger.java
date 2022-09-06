package com.amazon.tarazed.core.session;

import amazon.speech.simclient.settings.Settings;
import com.amazon.alexa.fitness.metrics.Metrics;
import com.amazon.alexa.mode.debug.EmulateConnection;
import com.amazon.tarazed.core.session.sessionEvents.SessionEvents;
import com.amazon.tarazed.core.session.sessionEvents.StateChangeEvent;
import com.amazon.tarazed.core.session.sessionEvents.StateChangeEventTypes;
import com.amazon.tarazed.core.sessionclient.model.createcredentials.SignalingCredentials;
import com.amazon.tarazed.core.signaling.TarazedIoTManager;
import com.amazon.tarazed.core.signaling.events.TarazedEvent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TarazedSessionIoTMessenger.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u0006\u0010\n\u001a\u00020\u0006J\u000e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000eJ\u001a\u0010\u000f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000eJ$\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"Lcom/amazon/tarazed/core/session/TarazedSessionIoTMessenger;", "", "iotManager", "Lcom/amazon/tarazed/core/signaling/TarazedIoTManager;", "(Lcom/amazon/tarazed/core/signaling/TarazedIoTManager;)V", EmulateConnection.EXTRA_CONNECT, "", "credentials", "Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/SignalingCredentials;", "(Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/SignalingCredentials;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", Metrics.DISCONNECT, "refreshCredentials", "sendPermissionStateChange", "state", "", "sendPlaybackStateChange", Settings.ListeningSettings.EXTRA_REASON, "sendStateChange", "stateType", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedSessionIoTMessenger {
    private final TarazedIoTManager iotManager;

    public TarazedSessionIoTMessenger(@NotNull TarazedIoTManager iotManager) {
        Intrinsics.checkParameterIsNotNull(iotManager, "iotManager");
        this.iotManager = iotManager;
    }

    public static /* synthetic */ void sendPlaybackStateChange$default(TarazedSessionIoTMessenger tarazedSessionIoTMessenger, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        tarazedSessionIoTMessenger.sendPlaybackStateChange(str, str2);
    }

    private final void sendStateChange(String str, String str2, String str3) {
        TarazedIoTManager.sendEvent$default(this.iotManager, Json.Default.stringify(TarazedEvent.Companion.serializer(StateChangeEvent.Companion.serializer()), new TarazedEvent(SessionEvents.STATE_CHANGE, new StateChangeEvent(str, str2, str3))), null, 2, null);
    }

    static /* synthetic */ void sendStateChange$default(TarazedSessionIoTMessenger tarazedSessionIoTMessenger, String str, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = null;
        }
        tarazedSessionIoTMessenger.sendStateChange(str, str2, str3);
    }

    @Nullable
    public final Object connect(@NotNull SignalingCredentials signalingCredentials, @NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        Object connect$default = TarazedIoTManager.connect$default(this.iotManager, signalingCredentials, false, continuation, 2, null);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return connect$default == coroutine_suspended ? connect$default : Unit.INSTANCE;
    }

    public final void disconnect() {
        this.iotManager.disconnect();
    }

    public final void refreshCredentials(@NotNull SignalingCredentials credentials) {
        Intrinsics.checkParameterIsNotNull(credentials, "credentials");
        this.iotManager.setCredentials(credentials);
    }

    public final void sendPermissionStateChange(@NotNull String state) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        sendStateChange$default(this, "permission", state, null, 4, null);
    }

    public final void sendPlaybackStateChange(@NotNull String state, @Nullable String str) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        sendStateChange(StateChangeEventTypes.PLAYBACK_STATE_CHANGE, state, str);
    }
}
