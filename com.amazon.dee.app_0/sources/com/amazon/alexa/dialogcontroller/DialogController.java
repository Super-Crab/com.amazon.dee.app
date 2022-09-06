package com.amazon.alexa.dialogcontroller;

import androidx.annotation.Nullable;
import com.amazon.alexa.api.AlexaAudioSink;
import com.amazon.alexa.api.AlexaDialogExtras;
import com.amazon.alexa.api.AlexaWakeWord;
import java.io.IOException;
/* loaded from: classes7.dex */
public interface DialogController {
    void addLifecycleCallbacks(DialogLifecycleCallbacks dialogLifecycleCallbacks);

    boolean isInDialog();

    boolean isInterruptable();

    boolean isMultiturn();

    void release();

    void removeLifecycleCallbacks(DialogLifecycleCallbacks dialogLifecycleCallbacks);

    boolean startDialog(AlexaDialogExtras alexaDialogExtras) throws IOException;

    boolean startDialog(AlexaDialogExtras alexaDialogExtras, AlexaAudioSink alexaAudioSink, @Nullable AlexaWakeWord alexaWakeWord);

    void stopCurrentDialogTurn();
}
