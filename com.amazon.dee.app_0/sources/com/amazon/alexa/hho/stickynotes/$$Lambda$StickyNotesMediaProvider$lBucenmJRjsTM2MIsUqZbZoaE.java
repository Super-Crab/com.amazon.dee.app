package com.amazon.alexa.hho.stickynotes;

import android.util.Log;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.hho.stickynotes.-$$Lambda$StickyNotesMediaProvider$lBuc-enmJRjsTM2MIsUq-ZbZoaE  reason: invalid class name */
/* loaded from: classes8.dex */
public final /* synthetic */ class $$Lambda$StickyNotesMediaProvider$lBucenmJRjsTM2MIsUqZbZoaE implements Consumer {
    public static final /* synthetic */ $$Lambda$StickyNotesMediaProvider$lBucenmJRjsTM2MIsUqZbZoaE INSTANCE = new $$Lambda$StickyNotesMediaProvider$lBucenmJRjsTM2MIsUqZbZoaE();

    private /* synthetic */ $$Lambda$StickyNotesMediaProvider$lBucenmJRjsTM2MIsUqZbZoaE() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Log.e(StickyNotesMediaProvider.TAG, "Failed to delete file ", (Throwable) obj);
    }
}
