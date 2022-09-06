package com.amazon.alexa.alertsca.events;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class AutoValue_NetworkConnectivityEvent extends NetworkConnectivityEvent {
    private final boolean connected;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_NetworkConnectivityEvent(boolean z) {
        this.connected = z;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof NetworkConnectivityEvent) && this.connected == ((NetworkConnectivityEvent) obj).isConnected();
    }

    public int hashCode() {
        return (this.connected ? 1231 : 1237) ^ 1000003;
    }

    @Override // com.amazon.alexa.alertsca.events.NetworkConnectivityEvent
    public boolean isConnected() {
        return this.connected;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline97(GeneratedOutlineSupport1.outline107("NetworkConnectivityEvent{connected="), this.connected, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
