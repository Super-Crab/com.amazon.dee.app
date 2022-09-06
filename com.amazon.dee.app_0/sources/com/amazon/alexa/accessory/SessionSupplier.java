package com.amazon.alexa.accessory;

import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.AccessorySession;
import java.util.List;
/* loaded from: classes.dex */
public interface SessionSupplier {
    void addSessionListener(AccessorySessionListener accessorySessionListener);

    AccessorySession createSession(Accessory accessory, AccessorySession.Factory factory);

    AccessorySession createSession(Accessory accessory, AccessorySession.Factory factory, SessionListener sessionListener);

    AccessorySession createSession(Accessory accessory, AccessorySession.Factory factory, SessionListener sessionListener, AccessorySessionOptions accessorySessionOptions);

    @NonNull
    List<AccessorySession> getActiveSessions();

    AccessorySession getSession(Accessory accessory);

    boolean hasActiveSession(Accessory accessory);

    boolean hasActiveSessions();

    void removeSession(Accessory accessory);

    void removeSessionListener(AccessorySessionListener accessorySessionListener);
}
