package com.amazon.deecomms.calling.telecom.audiomanagement;

import android.media.AudioManager;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public final class TelecomAudioUtils {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, TelecomAudioUtils.class);

    private TelecomAudioUtils() {
    }

    public static void verifyRouteSelected(@NonNull AudioManager audioManager, @NonNull String str) {
        if (str.equalsIgnoreCase("earpiece")) {
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Earpiece requested. Audio has been routed via earpiece is ");
            outline1.append(!audioManager.isSpeakerphoneOn());
            commsLogger.i(outline1.toString());
            if (!audioManager.isSpeakerphoneOn()) {
                return;
            }
            LOG.i("Telecom audio routing to earpiece failed. Retrying using traditional approach");
            audioManager.setSpeakerphoneOn(false);
            GeneratedOutlineSupport1.outline184(GeneratedOutlineSupport.outline1("Earpiece requested. Audio has been routed via earpiece is "), !audioManager.isSpeakerphoneOn(), LOG);
        } else if (!str.equalsIgnoreCase("speaker")) {
        } else {
            CommsLogger commsLogger2 = LOG;
            StringBuilder outline12 = GeneratedOutlineSupport.outline1("Speaker requested. Audio has been routed via speaker is ");
            outline12.append(audioManager.isSpeakerphoneOn());
            commsLogger2.i(outline12.toString());
            if (audioManager.isSpeakerphoneOn()) {
                return;
            }
            LOG.i("Telecom audio routing to speaker failed. Retrying using traditional approach");
            audioManager.setSpeakerphoneOn(true);
            CommsLogger commsLogger3 = LOG;
            StringBuilder outline13 = GeneratedOutlineSupport.outline1("Speaker requested. Audio has been routed via speaker is ");
            outline13.append(audioManager.isSpeakerphoneOn());
            commsLogger3.i(outline13.toString());
        }
    }
}
