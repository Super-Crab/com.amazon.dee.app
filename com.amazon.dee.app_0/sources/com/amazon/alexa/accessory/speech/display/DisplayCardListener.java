package com.amazon.alexa.accessory.speech.display;

import android.annotation.SuppressLint;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Cardrendering;
import com.amazon.alexa.accessory.repositories.display.DisplayContentRepository;
import com.amazon.alexa.accessory.speechapi.listeners.CardExtras;
import com.amazon.alexa.accessory.speechapi.listeners.CardListener;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes6.dex */
public class DisplayCardListener implements CardListener {
    private static final String TAG = "speech.DisplayCardListener";
    private final int chunkSize;
    private final int dialogTurnSequenceId;
    private final AtomicInteger dialogTurnSpeechId;
    private final DisplayContentRepository displayContentRepository;

    public DisplayCardListener(DisplayContentRepository displayContentRepository, int i, int i2) {
        Preconditions.notNull(displayContentRepository, "displayContentRepository");
        this.displayContentRepository = displayContentRepository;
        this.dialogTurnSequenceId = i;
        this.dialogTurnSpeechId = new AtomicInteger(0);
        this.chunkSize = i2;
    }

    @Override // com.amazon.alexa.accessory.speechapi.listeners.CardListener
    @SuppressLint({"CheckResult"})
    public void onReceivedRenderCard(String str, CardExtras cardExtras) {
        Logger.d("%s: received RenderCard Data %s", TAG, str);
        this.displayContentRepository.setDisplayContentByChunks(str, Cardrendering.DisplayContentType.TYPE_CARD, this.dialogTurnSequenceId, this.dialogTurnSpeechId.getAndIncrement(), this.chunkSize).subscribe($$Lambda$DisplayCardListener$n2rDCPhkEHZq2pnLgD9OchDWo.INSTANCE, $$Lambda$DisplayCardListener$JMHb_0vmeHFerkqMfWSgEUwhlKc.INSTANCE);
    }
}
