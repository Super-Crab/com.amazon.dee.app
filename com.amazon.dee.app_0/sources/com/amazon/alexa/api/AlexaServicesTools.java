package com.amazon.alexa.api;

import com.amazon.alexa.utils.validation.Assertions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class AlexaServicesTools {
    public static final String MESSAGING_ERROR = "Unable to communicate with the service";
    private static final String NOT_CONNECTED_ERROR = "Connection object is not connected";

    private AlexaServicesTools() {
        throw new UnsupportedOperationException();
    }

    public static void checkAlexaBound(ManagedServiceConnection managedServiceConnection) {
        Assertions.isFalse(!managedServiceConnection.isBoundToService(), NOT_CONNECTED_ERROR);
    }

    public static void checkAlexaConnection(ManagedServiceConnection managedServiceConnection) {
        Assertions.isFalse(!managedServiceConnection.isConnected(), NOT_CONNECTED_ERROR);
    }

    public static AlexaServicesMessageSender getMessageSender(AlexaServicesConnection alexaServicesConnection) {
        AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
        Assertions.notNull(alexaServicesMessageSender, MESSAGING_ERROR);
        return alexaServicesMessageSender;
    }
}
