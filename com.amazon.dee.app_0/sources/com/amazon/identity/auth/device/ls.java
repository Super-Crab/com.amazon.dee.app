package com.amazon.identity.auth.device;

import java.net.URL;
import java.security.SecureRandom;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ls {
    private static final long ux = TimeUnit.SECONDS.toMillis(1);
    private static final long uy = TimeUnit.SECONDS.toMillis(60);
    private static final long uz = TimeUnit.SECONDS.toMillis(78);
    private int jF;
    private final URL mURL;
    private final long uA;
    private final long uB;
    private final SecureRandom uC;

    public ls(URL url) {
        this(url, ux);
    }

    public ls d(URL url) {
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = currentTimeMillis < this.uB;
        boolean z2 = this.uB - currentTimeMillis < uz;
        if (!z || !z2) {
            io.i("BackoffInfo", String.format(Locale.ENGLISH, "Last backoff interval is %d ms, updating backoff info...", Long.valueOf(this.uA)));
            int i = this.jF + 1;
            this.jF = i;
            return new ls(i, url, lt.a((int) Math.min(this.uA * 2, uy), 30, this.uC));
        }
        return this;
    }

    public long io() {
        return this.uB;
    }

    public int ip() {
        return this.jF;
    }

    public boolean iq() {
        return ir() > 0;
    }

    public long ir() {
        long currentTimeMillis = this.uB - System.currentTimeMillis();
        if (currentTimeMillis > uz) {
            io.i("BackoffInfo", "System clock is set to past, correcting backoff info...");
            long j = uz;
            lt.e(this.mURL);
            return j;
        }
        return currentTimeMillis;
    }

    public ls(URL url, long j) {
        this(1, url, j);
    }

    public ls(int i, URL url, long j) {
        long min;
        this.jF = 0;
        this.mURL = url;
        this.uC = new SecureRandom();
        if (j <= ux) {
            io.i("BackoffInfo", String.format(Locale.ENGLISH, "Backoff interval cannot be less than %d ms, set interval to %d ms", Long.valueOf(ux), Long.valueOf(ux)));
            min = lt.a(j, 30, this.uC);
        } else {
            min = Math.min(j, uz);
        }
        this.uA = min;
        this.uB = System.currentTimeMillis() + this.uA;
        this.jF = i;
    }
}
