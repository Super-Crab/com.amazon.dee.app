package com.amazon.comms.ringservice.webrtc.utils;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.calling.service.MediaStats;
import com.amazon.comms.ringservice.webrtc.StatsReportParser;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
/* loaded from: classes12.dex */
public class WebRTCMediaStatsUtils {
    private static final String LABEL_LOCAL_CANDIDATE_TYPE = "googLocalCandidateType";
    private static final String LABEL_REMOTE_CANDIDATE_TYPE = "googRemoteCandidateType";
    private static final String SENSITIVE_MESSAGE_REPLACEMENT = "*****";
    private static final Pattern IP4 = Pattern.compile("[0-9]+\\.[0-9]+\\.[0-9]+\\.[0-9]+");
    private static final Pattern IP6 = Pattern.compile("(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))");
    private static final Pattern[] SENSITIVE_PATTERNS = {IP4, IP6};

    public static String filterSensitiveMessage(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        for (Pattern pattern : SENSITIVE_PATTERNS) {
            str = pattern.matcher(str).replaceAll(SENSITIVE_MESSAGE_REPLACEMENT);
        }
        return str;
    }

    @Nullable
    public static String getLocalConnectionType(@NonNull MediaStats mediaStats) {
        List<Map<String, String>> findActiveCandidatePairs = StatsReportParser.findActiveCandidatePairs(mediaStats.getMediaStats());
        if (findActiveCandidatePairs.isEmpty()) {
            return null;
        }
        return getLocalConnectionType(findActiveCandidatePairs.get(0));
    }

    @Nullable
    public static String getRemoteConnectionType(@NonNull MediaStats mediaStats) {
        List<Map<String, String>> findActiveCandidatePairs = StatsReportParser.findActiveCandidatePairs(mediaStats.getMediaStats());
        if (findActiveCandidatePairs.isEmpty()) {
            return null;
        }
        return getRemoteConnectionType(findActiveCandidatePairs.get(0));
    }

    @Nullable
    public static String getLocalConnectionType(@NonNull Map<String, String> map) {
        return map.get(LABEL_LOCAL_CANDIDATE_TYPE);
    }

    @Nullable
    public static String getRemoteConnectionType(@NonNull Map<String, String> map) {
        return map.get(LABEL_REMOTE_CANDIDATE_TYPE);
    }
}
