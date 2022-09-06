package net.danlew.android.joda;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.TimeZone;
import org.joda.time.DateTimeZone;
/* loaded from: classes4.dex */
public class TimeZoneChangedReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("time-zone");
        try {
            DateTimeZone.setDefault(DateTimeZone.forTimeZone(TimeZone.getDefault()));
            String str = "TIMEZONE_CHANGED received, changed default timezone to \"" + stringExtra + EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED;
        } catch (IllegalArgumentException e) {
            Log.e("joda-time-android", "Could not recognize timezone id \"" + stringExtra + EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED, e);
        }
    }
}
