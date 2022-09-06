package com.google.android.exoplayer2.transformer;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class Transformation {
    public final boolean flattenForSlowMotion;
    public final String outputMimeType;
    public final boolean removeAudio;
    public final boolean removeVideo;

    public Transformation(boolean z, boolean z2, boolean z3, String str) {
        this.removeAudio = z;
        this.removeVideo = z2;
        this.flattenForSlowMotion = z3;
        this.outputMimeType = str;
    }
}
