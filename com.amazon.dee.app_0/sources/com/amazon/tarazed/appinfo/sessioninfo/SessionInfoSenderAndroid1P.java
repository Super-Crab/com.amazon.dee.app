package com.amazon.tarazed.appinfo.sessioninfo;

import com.amazon.tarazed.core.appInfo.sessioninfo.SessionInfoSender;
import com.amazon.tarazed.core.signaling.TarazedEventDispatcher;
import com.amazon.tarazed.core.signaling.TarazedIoTManager;
import com.amazon.tarazed.core.signaling.events.TarazedEvent;
import com.amazon.tarazed.core.type.ProfileType;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;
/* compiled from: SessionInfoSenderAndroid1P.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0010\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/tarazed/appinfo/sessioninfo/SessionInfoSenderAndroid1P;", "Lcom/amazon/tarazed/core/appInfo/sessioninfo/SessionInfoSender;", "eventDispatcher", "Lcom/amazon/tarazed/core/signaling/TarazedEventDispatcher;", "iotManager", "Lcom/amazon/tarazed/core/signaling/TarazedIoTManager;", "deviceInfo", "Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "(Lcom/amazon/tarazed/core/signaling/TarazedEventDispatcher;Lcom/amazon/tarazed/core/signaling/TarazedIoTManager;Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;)V", "sendSessionInfo", "", "voiceViewState", "", "remoteType", "", "activeProfileType", "Lcom/amazon/tarazed/core/type/ProfileType;", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public class SessionInfoSenderAndroid1P implements SessionInfoSender {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String SESSION_INFO_EVENT = "sessionInfo";
    private final DeviceInfoUtility deviceInfo;
    private final TarazedEventDispatcher eventDispatcher;
    private final TarazedIoTManager iotManager;

    /* compiled from: SessionInfoSenderAndroid1P.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/tarazed/appinfo/sessioninfo/SessionInfoSenderAndroid1P$Companion;", "", "()V", "SESSION_INFO_EVENT", "", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SessionInfoSenderAndroid1P(@NotNull TarazedEventDispatcher eventDispatcher, @NotNull TarazedIoTManager iotManager, @NotNull DeviceInfoUtility deviceInfo) {
        Intrinsics.checkParameterIsNotNull(eventDispatcher, "eventDispatcher");
        Intrinsics.checkParameterIsNotNull(iotManager, "iotManager");
        Intrinsics.checkParameterIsNotNull(deviceInfo, "deviceInfo");
        this.eventDispatcher = eventDispatcher;
        this.iotManager = iotManager;
        this.deviceInfo = deviceInfo;
    }

    @Override // com.amazon.tarazed.core.appInfo.sessioninfo.SessionInfoSender
    public void sendSessionInfo(boolean z, @NotNull String remoteType, @NotNull ProfileType activeProfileType) {
        Intrinsics.checkParameterIsNotNull(remoteType, "remoteType");
        Intrinsics.checkParameterIsNotNull(activeProfileType, "activeProfileType");
        TarazedIoTManager.sendEvent$default(this.iotManager, Json.Default.stringify(TarazedEvent.Companion.serializer(SessionInfo1P.Companion.serializer()), new TarazedEvent(SESSION_INFO_EVENT, new SessionInfo1P(this.deviceInfo.getOperatingSystemName(), this.deviceInfo.getOperatingSystemVersion(), this.deviceInfo.getApp(), this.deviceInfo.getTarazedVersion(), this.eventDispatcher.getRegisteredHandlersNames(), this.deviceInfo.getDeviceLanguage(), z, this.deviceInfo.isFireTV(), activeProfileType))), null, 2, null);
    }
}
