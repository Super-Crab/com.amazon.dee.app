package com.amazon.alexa.accessory.speech.display;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.speech.display.-$$Lambda$DisplayCaptionListener$vY7YkS3xj6ZIRLUkgho0fTAxGxo  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$DisplayCaptionListener$vY7YkS3xj6ZIRLUkgho0fTAxGxo implements Consumer {
    public static final /* synthetic */ $$Lambda$DisplayCaptionListener$vY7YkS3xj6ZIRLUkgho0fTAxGxo INSTANCE = new $$Lambda$DisplayCaptionListener$vY7YkS3xj6ZIRLUkgho0fTAxGxo();

    private /* synthetic */ $$Lambda$DisplayCaptionListener$vY7YkS3xj6ZIRLUkgho0fTAxGxo() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("%s: Fail to set display content by chunks.", (Throwable) obj, DisplayCaptionListener.TAG);
    }
}
