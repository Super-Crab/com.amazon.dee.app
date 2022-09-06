package com.amazon.alexa.accessory.speech;

import com.amazon.alexa.accessory.Accessories;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessorySessionListener;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.speechapi.AlexaConnection;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes6.dex */
public final class AlexaConnectionAdvocate {
    private final Accessories accessories;
    private final AlexaConnection connection;
    private final SessionListener sessionListener;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public final class SessionListener extends AccessorySessionListener {
        private final Set<Accessory> connectedAccessories = new HashSet();

        SessionListener() {
        }

        @Override // com.amazon.alexa.accessory.AccessorySessionListener
        public void onAccessorySessionCreated(Accessory accessory) {
            if (this.connectedAccessories.isEmpty()) {
                Logger.d("AccessoryAlexaConnectionAdvocate is requesting to connect.");
                AlexaConnectionAdvocate.this.connection.registerConnectionListener(new RetryingAlexaServicesConnectionListener(AlexaConnectionAdvocate.this.connection, 3));
                AlexaConnectionAdvocate.this.connection.connect();
            }
            this.connectedAccessories.add(accessory);
        }

        @Override // com.amazon.alexa.accessory.AccessorySessionListener
        public void onAccessorySessionReleased(Accessory accessory) {
            this.connectedAccessories.remove(accessory);
            if (this.connectedAccessories.isEmpty()) {
                Logger.d("AccessoryAlexaConnectionAdvocate is requesting to disconnect.");
                AlexaConnectionAdvocate.this.connection.disconnect();
            }
        }
    }

    public AlexaConnectionAdvocate(AlexaConnection alexaConnection, Accessories accessories) {
        Preconditions.notNull(alexaConnection, "connection");
        Preconditions.notNull(accessories, "accessories");
        this.connection = alexaConnection;
        this.accessories = accessories;
        this.sessionListener = new SessionListener();
    }

    public void activate() {
        this.accessories.addSessionListener(this.sessionListener);
    }
}
