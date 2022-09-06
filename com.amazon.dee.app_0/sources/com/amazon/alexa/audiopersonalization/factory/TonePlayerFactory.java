package com.amazon.alexa.audiopersonalization.factory;

import com.amazon.alexa.audiopersonalization.components.TonePlayer;
import java.util.concurrent.Executors;
/* loaded from: classes6.dex */
public class TonePlayerFactory {
    public TonePlayer createTonePlayer() {
        return new TonePlayer(Executors.newSingleThreadExecutor());
    }
}
