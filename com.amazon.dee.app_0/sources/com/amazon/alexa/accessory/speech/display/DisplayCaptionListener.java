package com.amazon.alexa.accessory.speech.display;

import android.annotation.SuppressLint;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Cardrendering;
import com.amazon.alexa.accessory.repositories.display.DisplayContentRepository;
import com.amazon.alexa.accessory.speechapi.listeners.Caption;
import com.amazon.alexa.accessory.speechapi.listeners.CaptionListener;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes6.dex */
public class DisplayCaptionListener implements CaptionListener {
    private static final String TAG = "speech.DisplayCaptionListener";
    private final int chunkSize;
    private final int dialogTurnSequenceId;
    private final AtomicInteger dialogTurnSpeechId;
    private final DisplayContentRepository displayContentRepository;

    public DisplayCaptionListener(DisplayContentRepository displayContentRepository, int i, int i2) {
        Preconditions.notNull(displayContentRepository, "displayContentRepository");
        this.displayContentRepository = displayContentRepository;
        this.dialogTurnSequenceId = i;
        this.dialogTurnSpeechId = new AtomicInteger(0);
        this.chunkSize = i2;
    }

    @Override // com.amazon.alexa.accessory.speechapi.listeners.CaptionListener
    @SuppressLint({"CheckResult"})
    public void onReceivedCaption(Caption caption) {
        Logger.d("%s: received Caption Data %s", TAG, caption.getContent());
        this.displayContentRepository.setDisplayContentByChunks(caption.getContent(), Cardrendering.DisplayContentType.TYPE_CAPTION, this.dialogTurnSequenceId, this.dialogTurnSpeechId.getAndIncrement(), this.chunkSize).subscribe($$Lambda$DisplayCaptionListener$p0DMhVYO3OiAhHsPr4bFGDUn4s.INSTANCE, $$Lambda$DisplayCaptionListener$vY7YkS3xj6ZIRLUkgho0fTAxGxo.INSTANCE);
    }
}
