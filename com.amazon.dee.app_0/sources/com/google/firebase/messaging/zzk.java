package com.google.firebase.messaging;

import com.google.android.datatransport.Transformer;
/* compiled from: com.google.firebase:firebase-messaging@@20.1.0 */
/* loaded from: classes3.dex */
final /* synthetic */ class zzk implements Transformer {
    static final Transformer zza = new zzk();

    private zzk() {
    }

    @Override // com.google.android.datatransport.Transformer
    public final Object apply(Object obj) {
        return ((String) obj).getBytes();
    }
}
