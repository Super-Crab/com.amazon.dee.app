package com.amazon.alexa.accessory.speech.display;

import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.capabilities.speech.SpeechSettings;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.repositories.display.DisplayContentRepository;
import com.amazon.alexa.accessory.speechapi.listeners.CaptionListener;
import com.amazon.alexa.accessory.speechapi.listeners.CardListener;
/* loaded from: classes6.dex */
public class DisplayContentListenersFactory {
    public static final int CHUNK_SIZE_IN_BYTES = 900;
    private static final String TAG = "speech.DisplayContentListenersFactory";
    private final SessionSupplier sessionSupplier;

    public DisplayContentListenersFactory(SessionSupplier sessionSupplier) {
        Preconditions.notNull(sessionSupplier, "sessionSupplier");
        this.sessionSupplier = sessionSupplier;
    }

    private DisplayContentRepository getDisplayContentRepository(SpeechSettings speechSettings) {
        String identifier = speechSettings.getAccessoryIdentifierProvider().getIdentifier();
        for (AccessorySession accessorySession : this.sessionSupplier.getActiveSessions()) {
            if (accessorySession.getAccessory().getAddress().equals(identifier)) {
                return accessorySession.getDisplayContentRepository();
            }
        }
        return null;
    }

    public CaptionListener createCaptionListener(SpeechSettings speechSettings, int i) {
        Preconditions.notNull(speechSettings, "settings");
        DisplayContentRepository displayContentRepository = getDisplayContentRepository(speechSettings);
        if (displayContentRepository == null) {
            Logger.d("%s: Return no-op caption listener with invalid DisplayContentRepository", TAG);
            return new UnavailableCaptionListener();
        }
        return new DisplayCaptionListener(displayContentRepository, i, 900);
    }

    public CardListener createCardListener(SpeechSettings speechSettings, int i) {
        Preconditions.notNull(speechSettings, "settings");
        DisplayContentRepository displayContentRepository = getDisplayContentRepository(speechSettings);
        if (displayContentRepository == null) {
            Logger.d("%s: Return no-op card listener with invalid DisplayContentRepository", TAG);
            return new UnavailableCardListener();
        }
        return new DisplayCardListener(displayContentRepository, i, 900);
    }
}
