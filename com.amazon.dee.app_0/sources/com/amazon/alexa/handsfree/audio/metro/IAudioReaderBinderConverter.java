package com.amazon.alexa.handsfree.audio.metro;

import android.os.IBinder;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.magiear.handsfree.util.IAudioReader;
/* loaded from: classes8.dex */
public class IAudioReaderBinderConverter {
    @Nullable
    public IAudioReader convert(@NonNull IBinder iBinder) {
        return IAudioReader.Stub.asInterface(iBinder);
    }
}
