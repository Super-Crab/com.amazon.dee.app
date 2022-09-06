package com.amazon.alexa.voice.ui.driveMode;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.ui.cards.CardCreationEventListener;
import com.amazon.alexa.voice.ui.cards.CardRendererControllerFactory;
import com.amazon.alexa.voice.ui.driveMode.local.DriveModeLocalControllerFactory;
import com.amazon.alexa.voice.ui.superbowl.ControllerFactory;
import com.amazon.regulator.ViewController;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes11.dex */
public final class DriveModeCardRendererControllerFactory extends CardRendererControllerFactory {
    public DriveModeCardRendererControllerFactory(int i, CardCreationEventListener cardCreationEventListener) {
        super(cardCreationEventListener, getControllerFactories(i));
    }

    @VisibleForTesting
    static Map<String, ControllerFactory<? extends ViewController>> getControllerFactories(int i) {
        HashMap hashMap = new HashMap();
        CardRendererControllerFactory.register(hashMap, new DriveModeLocalControllerFactory(i), "PuffinCard");
        return hashMap;
    }
}
