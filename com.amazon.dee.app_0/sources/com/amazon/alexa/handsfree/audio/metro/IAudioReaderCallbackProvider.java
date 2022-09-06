package com.amazon.alexa.handsfree.audio.metro;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.audio.api.AudioReader;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.magiear.handsfree.util.IAudioReaderCallback;
import com.magiear.handsfree.util.ParamDefinition;
import java.util.Objects;
/* loaded from: classes8.dex */
public class IAudioReaderCallbackProvider {
    private static final String TAG = "IAudioReaderCbProvider";
    private final IAudioReaderCallback mAudioReaderCallback;

    public IAudioReaderCallbackProvider(@NonNull final AudioReader audioReader) {
        this(new IAudioReaderCallback.Stub() { // from class: com.amazon.alexa.handsfree.audio.metro.IAudioReaderCallbackProvider.1
            @Override // com.magiear.handsfree.util.IAudioReaderCallback
            public void onAudioEnd(@Nullable Bundle bundle) {
                Log.d(IAudioReaderCallbackProvider.TAG, "onAudioEnd: audio buffer from metro transferred completed!");
                AudioReader.this.onTransferFinished();
            }

            @Override // com.magiear.handsfree.util.IAudioReaderCallback
            public void onBuffer(@Nullable Bundle bundle) {
                if (bundle == null) {
                    Log.e(IAudioReaderCallbackProvider.TAG, "onBuffer: No audio bundle.");
                } else if (!bundle.containsKey(ParamDefinition.KEY_AUDIO_CALLBACK_DATA)) {
                    Log.e(IAudioReaderCallbackProvider.TAG, "onBuffer: No audio included in the bundle!");
                } else {
                    Log.d(IAudioReaderCallbackProvider.TAG, "onBuffer: audio buffer from metro");
                    AudioReader.this.onAudioReceived((byte[]) Objects.requireNonNull(bundle.getByteArray(ParamDefinition.KEY_AUDIO_CALLBACK_DATA)));
                }
            }
        });
    }

    @NonNull
    public IAudioReaderCallback getIAudioReaderCallback() {
        return this.mAudioReaderCallback;
    }

    @VisibleForTesting
    IAudioReaderCallbackProvider(@NonNull IAudioReaderCallback iAudioReaderCallback) {
        this.mAudioReaderCallback = iAudioReaderCallback;
    }
}
