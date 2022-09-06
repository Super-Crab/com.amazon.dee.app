package com.amazon.alexa.dialogcontroller;

import androidx.annotation.Nullable;
import com.amazon.alexa.api.AlexaAudioMetadata;
import com.amazon.alexa.api.AlexaAudioSink;
import com.amazon.alexa.api.AlexaDialogExtras;
/* loaded from: classes7.dex */
public class StartDialogData {
    private final AlexaAudioMetadata alexaAudioMetadata;
    private final AlexaAudioSink audioSink;
    private final AlexaDialogExtras dialogExtras;

    /* JADX INFO: Access modifiers changed from: package-private */
    public StartDialogData(AlexaDialogExtras alexaDialogExtras, @Nullable AlexaAudioSink alexaAudioSink, AlexaAudioMetadata alexaAudioMetadata) {
        this.dialogExtras = alexaDialogExtras;
        this.audioSink = alexaAudioSink;
        this.alexaAudioMetadata = alexaAudioMetadata;
    }

    public AlexaAudioMetadata getAlexaAudioMetadata() {
        return this.alexaAudioMetadata;
    }

    @Nullable
    public AlexaAudioSink getAudioSink() {
        return this.audioSink;
    }

    public AlexaDialogExtras getDialogExtras() {
        return this.dialogExtras;
    }
}
