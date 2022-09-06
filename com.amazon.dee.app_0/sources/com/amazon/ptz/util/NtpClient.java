package com.amazon.ptz.util;

import android.util.Log;
import com.amazon.ptz.util.NtpClient;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.net.InetAddress;
import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
/* loaded from: classes13.dex */
public class NtpClient {
    private static final String HOST = "time.google.com";
    private static final String TAG = LogTag.forClass(NtpClient.class);
    private static final int TIMEOUT_MILLISECONDS = 5000;
    private long offset;

    /* loaded from: classes13.dex */
    public interface NtpListener {
        void onNtpOffsetReceived(long j);
    }

    public static void getNtpOffset(final NtpListener ntpListener) {
        new Thread(new Runnable() { // from class: com.amazon.ptz.util.-$$Lambda$NtpClient$5Z9l_x3O7LkbZM-AfBRpW-62GII
            @Override // java.lang.Runnable
            public final void run() {
                NtpClient.lambda$getNtpOffset$0(NtpClient.NtpListener.this);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getNtpOffset$0(NtpListener ntpListener) {
        NtpClient ntpClient = new NtpClient();
        if (ntpClient.requestTime(HOST, 5000)) {
            ntpListener.onNtpOffsetReceived(ntpClient.offset);
        }
    }

    private boolean requestTime(String str, int i) {
        NTPUDPClient nTPUDPClient = new NTPUDPClient();
        nTPUDPClient.setDefaultTimeout(i);
        try {
            TimeInfo time = nTPUDPClient.getTime(InetAddress.getByName(str));
            time.computeDetails();
            if (time.getOffset() == null) {
                Log.e(TAG, "Unable to retrieve the NTP offset from remote NTP server");
                return false;
            }
            this.offset = time.getOffset().longValue();
            return true;
        } catch (IOException e) {
            String str2 = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Exception encountered when requesting NTP time: ");
            outline107.append(e.getMessage());
            Log.e(str2, outline107.toString());
            return false;
        }
    }
}
