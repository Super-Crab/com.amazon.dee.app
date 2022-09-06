package com.amazon.alexa.accessory.capabilities.firmware;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$DefaultDAVSUpdateCandidateInformation$1jz5DtqN9pZIWQ7_ilW-bLEAYAw  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$DefaultDAVSUpdateCandidateInformation$1jz5DtqN9pZIWQ7_ilWbLEAYAw implements Consumer {
    public static final /* synthetic */ $$Lambda$DefaultDAVSUpdateCandidateInformation$1jz5DtqN9pZIWQ7_ilWbLEAYAw INSTANCE = new $$Lambda$DefaultDAVSUpdateCandidateInformation$1jz5DtqN9pZIWQ7_ilWbLEAYAw();

    private /* synthetic */ $$Lambda$DefaultDAVSUpdateCandidateInformation$1jz5DtqN9pZIWQ7_ilWbLEAYAw() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Throwable th = (Throwable) obj;
        Logger.e("Failed to receive firmware information");
    }
}
