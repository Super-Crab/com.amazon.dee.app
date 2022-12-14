package com.google.android.exoplayer2.util;

import android.os.Looper;
import android.os.Message;
import androidx.annotation.Nullable;
/* loaded from: classes2.dex */
public interface HandlerWrapper {
    Looper getLooper();

    boolean hasMessages(int i);

    Message obtainMessage(int i);

    Message obtainMessage(int i, int i2, int i3);

    Message obtainMessage(int i, int i2, int i3, @Nullable Object obj);

    Message obtainMessage(int i, @Nullable Object obj);

    boolean post(Runnable runnable);

    boolean postDelayed(Runnable runnable, long j);

    void removeCallbacksAndMessages(@Nullable Object obj);

    void removeMessages(int i);

    boolean sendEmptyMessage(int i);

    boolean sendEmptyMessageAtTime(int i, long j);

    boolean sendEmptyMessageDelayed(int i, int i2);
}
