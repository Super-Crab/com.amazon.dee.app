package com.amazon.alexa.voice.ui.onedesign.widget.gradient;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import com.amazon.alexa.voice.ui.onedesign.widget.gradient.DefaultGradientCompositor;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.voice.ui.onedesign.widget.gradient.-$$Lambda$DefaultGradientCompositor$S4GLurg3u2AfmE0e9idPYOMrXT0  reason: invalid class name */
/* loaded from: classes11.dex */
public final /* synthetic */ class $$Lambda$DefaultGradientCompositor$S4GLurg3u2AfmE0e9idPYOMrXT0 implements DefaultGradientCompositor.CanvasFactory {
    public static final /* synthetic */ $$Lambda$DefaultGradientCompositor$S4GLurg3u2AfmE0e9idPYOMrXT0 INSTANCE = new $$Lambda$DefaultGradientCompositor$S4GLurg3u2AfmE0e9idPYOMrXT0();

    private /* synthetic */ $$Lambda$DefaultGradientCompositor$S4GLurg3u2AfmE0e9idPYOMrXT0() {
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.widget.gradient.DefaultGradientCompositor.CanvasFactory
    public final Canvas createInstance(Bitmap bitmap) {
        Canvas createCanvas;
        createCanvas = DefaultGradientCompositor.createCanvas(bitmap);
        return createCanvas;
    }
}
