package net.danlew.android.joda;

import android.content.Context;
import android.content.IntentFilter;
import java.io.IOException;
import org.joda.time.DateTimeZone;
/* loaded from: classes4.dex */
public final class JodaTimeAndroid {
    private static boolean sInitCalled = false;

    private JodaTimeAndroid() {
        throw new AssertionError();
    }

    public static void init(Context context) {
        if (sInitCalled) {
            return;
        }
        sInitCalled = true;
        try {
            DateTimeZone.setProvider(new ResourceZoneInfoProvider(context));
            context.getApplicationContext().registerReceiver(new TimeZoneChangedReceiver(), new IntentFilter("android.intent.action.TIMEZONE_CHANGED"));
        } catch (IOException unused) {
            throw new RuntimeException("Could not read ZoneInfoMap");
        }
    }
}
