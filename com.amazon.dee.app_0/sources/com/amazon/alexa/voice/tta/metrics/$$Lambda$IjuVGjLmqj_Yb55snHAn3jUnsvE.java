package com.amazon.alexa.voice.tta.metrics;

import com.amazon.alexa.voice.tta.utils.AsyncWorker;
import com.amazon.alexa.voice.tta.utils.Worker;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.voice.tta.metrics.-$$Lambda$IjuVGjLmqj_Yb55snHAn3jUnsvE  reason: invalid class name */
/* loaded from: classes11.dex */
public final /* synthetic */ class $$Lambda$IjuVGjLmqj_Yb55snHAn3jUnsvE implements Worker.Spawner {
    public static final /* synthetic */ $$Lambda$IjuVGjLmqj_Yb55snHAn3jUnsvE INSTANCE = new $$Lambda$IjuVGjLmqj_Yb55snHAn3jUnsvE();

    private /* synthetic */ $$Lambda$IjuVGjLmqj_Yb55snHAn3jUnsvE() {
    }

    @Override // com.amazon.alexa.voice.tta.utils.Worker.Spawner
    public final Worker spawn() {
        return AsyncWorker.spawn();
    }
}
