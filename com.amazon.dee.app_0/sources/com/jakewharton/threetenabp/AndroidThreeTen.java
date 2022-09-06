package com.jakewharton.threetenabp;

import android.app.Application;
import android.content.Context;
import java.util.concurrent.atomic.AtomicBoolean;
import org.threeten.bp.zone.ZoneRulesInitializer;
/* loaded from: classes3.dex */
public final class AndroidThreeTen {
    private static final AtomicBoolean initialized = new AtomicBoolean();

    private AndroidThreeTen() {
        throw new AssertionError();
    }

    public static void init(Application application) {
        init((Context) application);
    }

    public static void init(Context context) {
        init(context, "org/threeten/bp/TZDB.dat");
    }

    public static void init(Context context, String str) {
        if (!initialized.getAndSet(true)) {
            ZoneRulesInitializer.setInitializer(new AssetsZoneRulesInitializer(context, str));
        }
    }
}
