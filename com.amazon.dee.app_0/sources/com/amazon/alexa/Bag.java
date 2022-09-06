package com.amazon.alexa;

import android.util.Log;
/* compiled from: DownchannelCleanup.java */
/* loaded from: classes.dex */
public class Bag implements Runnable {
    public static final String zZm = "Bag";
    public final fau BIo;

    public Bag(fau fauVar) {
        this.BIo = fauVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        Log.i(zZm, "Cleaning up downchannel.");
        this.BIo.cancel(true);
    }
}
