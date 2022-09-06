package com.amazon.alexa;
/* compiled from: DavsOfflinePromptsDownloadManager.java */
/* loaded from: classes.dex */
public class Wnn implements Runnable {
    public final /* synthetic */ C0217lcZ zZm;

    public Wnn(C0217lcZ c0217lcZ) {
        this.zZm = c0217lcZ;
    }

    @Override // java.lang.Runnable
    public void run() {
        r0.zZm(this.zZm.LPk.getLocale().toLanguageTag());
    }
}
