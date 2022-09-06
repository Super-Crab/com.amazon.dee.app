package com.amazon.deecomms.identity;

import com.amazon.deecomms.api.CommsIdentity;
import com.amazon.deecomms.util.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.identity.-$$Lambda$M2TuQPHN9zoCr51xMbGI9HnVJUU  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$M2TuQPHN9zoCr51xMbGI9HnVJUU implements Consumer {
    private final /* synthetic */ CommsIdentity f$0;

    @Override // com.amazon.deecomms.util.Consumer
    public final void accept(Object obj) {
        this.f$0.setHashedCommsId((String) obj);
    }
}
