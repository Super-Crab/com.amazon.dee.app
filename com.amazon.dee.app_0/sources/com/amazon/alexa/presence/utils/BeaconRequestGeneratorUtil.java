package com.amazon.alexa.presence.utils;

import com.amazon.alexa.presence.Presence;
import com.amazon.alexa.presence.detection.BeaconDetection;
import com.amazon.alexa.presence.detection.BeaconFormatLogic;
import com.amazon.alexa.presence.models.DetectedBeaconDetails;
import com.amazon.alexa.presence.models.ResolveBeaconsRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes9.dex */
public final class BeaconRequestGeneratorUtil {
    private static final boolean DETAILED_RESPONSE_API_FIELD = false;
    private static final String TAG = Presence.tag();

    private BeaconRequestGeneratorUtil() {
    }

    public static ResolveBeaconsRequest generateResolveBeaconsRequest(BeaconFormatLogic beaconFormatLogic, List<BeaconDetection> list, String str) {
        String epochToIso8601 = beaconFormatLogic.epochToIso8601(System.currentTimeMillis());
        List emptyList = Collections.emptyList();
        ArrayList arrayList = new ArrayList(list.size());
        for (BeaconDetection beaconDetection : list) {
            arrayList.add(new DetectedBeaconDetails(beaconDetection.getDetectionTime(), beaconDetection.getBeaconPayload(), beaconDetection.getRelativeReceivedSignalStrength()));
        }
        return new ResolveBeaconsRequest(Collections.unmodifiableList(arrayList), emptyList, epochToIso8601, false, str);
    }
}
