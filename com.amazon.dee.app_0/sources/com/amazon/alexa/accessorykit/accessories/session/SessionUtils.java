package com.amazon.alexa.accessorykit.accessories.session;

import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessorykit.internal.SessionNotFoundException;
import java.util.Objects;
/* loaded from: classes6.dex */
public final class SessionUtils {
    private SessionUtils() {
        throw new RuntimeException("No instances!");
    }

    public static AccessorySession getSession(SessionSupplier sessionSupplier, String str) throws SessionNotFoundException {
        for (AccessorySession accessorySession : sessionSupplier.getActiveSessions()) {
            if (Objects.equals(str, accessorySession.getAddress())) {
                return accessorySession;
            }
        }
        throw new SessionNotFoundException();
    }
}
