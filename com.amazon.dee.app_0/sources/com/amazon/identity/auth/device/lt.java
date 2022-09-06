package com.amazon.identity.auth.device;

import com.amazon.identity.auth.device.framework.RetryLogic;
import java.net.URL;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class lt {
    private final Random uE;
    private int uF;
    private int uG;
    private int uH;
    private int uI;
    private int uJ;
    private static final HashMap<String, ls> uD = new HashMap<>();
    private static final String TAG = lt.class.getName();

    public lt(int i, int i2) {
        this(i, i2, (byte) 0);
    }

    public static int a(long j, int i, Random random) {
        int min = (int) Math.min(2147483647L, ((i * 2) * j) / 100);
        if (min != 0) {
            return (int) ((j - (min / 2)) + random.nextInt(min));
        }
        return (int) j;
    }

    public static ls e(URL url) {
        ls d;
        String g = g(url);
        synchronized (uD) {
            ls lsVar = uD.get(g);
            if (lsVar == null) {
                d = new ls(url);
            } else {
                d = lsVar.d(url);
            }
            uD.put(g, d);
        }
        return d;
    }

    public static ls f(URL url) {
        ls lsVar;
        synchronized (uD) {
            lsVar = uD.get(g(url));
        }
        return lsVar;
    }

    private static String g(URL url) {
        return url.getHost() + url.getPath();
    }

    public int is() {
        this.uH++;
        int i = this.uF;
        int i2 = this.uI;
        int i3 = i * i2;
        if (i3 * 2 <= this.uG) {
            this.uI = i2 * 2;
        }
        return a(i3, this.uJ, this.uE);
    }

    public int it() {
        return this.uH;
    }

    public lt(int i, int i2, byte b) {
        this.uE = new SecureRandom();
        this.uF = i;
        this.uG = i2;
        this.uH = 0;
        this.uI = 1;
        this.uJ = 30;
        if (this.uF <= 0) {
            this.uF = 10;
            io.b(TAG, "ExponentialBackoffHelper: was constructed with a first retry interval value less than or equal to zero. It has been set to a default value (%d ms)", Integer.valueOf(this.uF));
        }
        if (this.uJ <= 0) {
            this.uJ = 10;
            io.b(TAG, "ExponentialBackoffHelper: was constructed with a jitter percent range less than or equal to zero. It has been set to a default value (%d percent)", Integer.valueOf(this.uJ));
        }
    }

    public static ls a(int i, URL url) {
        if (RetryLogic.j(i)) {
            io.e(TAG, String.format(Locale.ENGLISH, "MAP received %d response from server, so updating the backoff info", Integer.valueOf(i)));
            return e(url);
        }
        synchronized (uD) {
            uD.remove(g(url));
        }
        return null;
    }
}
