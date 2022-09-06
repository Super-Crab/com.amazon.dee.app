package com.amazon.comms.ringservice.webrtc;

import com.amazon.comms.calling.service.MediaStats;
import com.amazon.comms.calling.sipclient.CallQualityMetrics;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.annotations.VisibleForTesting;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import lombok.NonNull;
import org.webrtc.StatsReport;
/* loaded from: classes12.dex */
public class StatsReportParser {
    public static final String CODEC_NOT_PRESENT = "notPresent";
    public static final String LABEL_ACTIVE_CONNECTION = "googActiveConnection";
    public static final String LABEL_CANDIDATE_PAIR = "googCandidatePair";
    public static final String LABEL_MEDIATYPE = "mediaType";
    public static final String LABEL_TRACK_ID = "googTrackId";
    private static final CommsLogger log = CommsLogger.getLogger(StatsReportParser.class);
    private static final HashMap<String, StatsReport.Value> sensitiveStatsValueMap = new HashMap<>();
    private List<Map<String, String>> activeCandidatePairs;
    private Map<String, String> audioRecvSsrc;
    private Map<String, String> audioSendSsrc;
    private boolean canLogStats;
    private long lastFramesDecoded;
    private long lastFramesEncoded;
    private long lastRxQpSum;
    private long lastTxQpSum;
    private MediaStats mediaStats;
    private CallQualityMetrics metrics;
    private Map<String, String> videoBandwidthReport;
    private Map<String, String> videoRecvSsrc;
    private Map<String, String> videoSendSsrc;
    private String webRTCReports;

    static {
        sensitiveStatsValueMap.put("googLocalAddress", new StatsReport.Value("googLocalAddress", "x.x.x.x"));
        sensitiveStatsValueMap.put("googRemoteAddress", new StatsReport.Value("googRemoteAddress", "x.x.x.x"));
        sensitiveStatsValueMap.put("ipAddress", new StatsReport.Value("ipAddress", "x.x.x.x"));
        sensitiveStatsValueMap.put("localCertificateId", new StatsReport.Value("localCertificateId", "googCertificate_redacted"));
        sensitiveStatsValueMap.put("remoteCertificateId", new StatsReport.Value("remoteCertificateId", "googCertificate_redacted"));
        sensitiveStatsValueMap.put("googDerBase64", new StatsReport.Value("googDerBase64", "redacted"));
        sensitiveStatsValueMap.put("googFingerprint", new StatsReport.Value("googFingerprint", "redacted"));
    }

    @VisibleForTesting
    StatsReportParser() {
        this.lastFramesDecoded = 0L;
        this.lastFramesEncoded = 0L;
        this.lastRxQpSum = 0L;
        this.lastTxQpSum = 0L;
        this.canLogStats = true;
    }

    public static StatsReportParser createStatsReportParser(StatsReport[] statsReportArr, boolean z) {
        StatsReportParser statsReportParser = new StatsReportParser(z);
        statsReportParser.parseWebrtcStatsReports(statsReportArr);
        return statsReportParser;
    }

    private String excludeInactiveIce(List<HashMap<String, String>> list) {
        String str;
        ArrayList arrayList = new ArrayList();
        for (HashMap<String, String> hashMap : list) {
            String str2 = hashMap.get("type");
            if (str2 == null || !str2.equalsIgnoreCase(LABEL_CANDIDATE_PAIR) || (str = hashMap.get(LABEL_ACTIVE_CONNECTION)) == null || !str.equals(PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE)) {
                arrayList.add(hashMap);
            }
        }
        return new Gson().toJson(arrayList);
    }

    @NonNull
    public static List<Map<String, String>> findActiveCandidatePairs(List<Map<String, String>> list) {
        ArrayList arrayList = new ArrayList();
        for (Map<String, String> map : list) {
            if (map.containsKey(LABEL_ACTIVE_CONNECTION)) {
                String str = map.get("googWritable");
                boolean booleanValue = str != null ? Boolean.valueOf(str).booleanValue() : false;
                if (Boolean.valueOf(map.get(LABEL_ACTIVE_CONNECTION)).booleanValue() && booleanValue) {
                    arrayList.add(map);
                }
            }
        }
        if (arrayList.isEmpty()) {
            log.w("No active candidate pair found.");
        }
        return arrayList;
    }

    protected static CommsLogger getLog() {
        return log;
    }

    private StatsReport.Value getSensitiveStatsValue(StatsReport.Value value) {
        StatsReport.Value value2;
        return (!CommsLogger.isDebugBuild() && (value2 = sensitiveStatsValueMap.get(value.name)) != null) ? value2 : value;
    }

    private boolean isAudioSsrc(Map<String, String> map) {
        String str = map.get("mediaType");
        return (str != null && "audio".equalsIgnoreCase(str)) || map.containsKey("audioInputLevel") || map.containsKey("audioOutputLevel");
    }

    private boolean isVideoSsrc(Map<String, String> map) {
        String str = map.get("mediaType");
        return (str != null && "video".equalsIgnoreCase(str)) || map.containsKey("googFrameHeightSent") || map.containsKey("googFrameHeightReceived");
    }

    private CallQualityMetrics.BaseQualityMetrics parseBaseMetrics(CallQualityMetrics.BaseQualityMetrics baseQualityMetrics, Map<String, String> map, Map<String, String> map2) {
        if (map != null) {
            baseQualityMetrics.setCallRxTotalPackets(safeGetLong(map, "packetsReceived"));
            baseQualityMetrics.setCallRxTotalBytes(safeGetLong(map, "bytesReceived"));
            baseQualityMetrics.setCallRxPacketLoss(safeGetLong(map, "packetsLost"));
            baseQualityMetrics.setCallRxAvgJitterUsec(safeGetLong(map, "googJitterReceived") * 1000);
            baseQualityMetrics.setCallRxJitterBufferMs(safeGetLong(map, "googJitterBufferMs"));
            baseQualityMetrics.setCallRxEncodedAudioToFrameDelayMs(safeGetLong(map, "googCurrentDelayMs"));
            baseQualityMetrics.setCallRxAudioPacketBufferMs(safeGetLong(map, "googAudioPacketBufferMs"));
            baseQualityMetrics.setCallRxAudioPlayoutBufferMs(safeGetLong(map, "googAudioPlayoutBufferMs"));
            baseQualityMetrics.setCallRxAudioDepacketizationDelayUsec(safeGetLong(map, "googAudioDepacketizationDelayUs"));
            baseQualityMetrics.setCallRxAudioDecodeDelayUsec(safeGetLong(map, "googAudioDecodeDelayUs"));
            baseQualityMetrics.setCallRxAudioOutputLevel(safeGetLong(map, "audioOutputLevel"));
        } else {
            log.d("rxStats is null. Skipping");
        }
        if (map2 != null) {
            baseQualityMetrics.setCallTxTotalPackets(safeGetLong(map2, "packetsSent"));
            baseQualityMetrics.setCallTxTotalBytes(safeGetLong(map2, "bytesSent"));
            baseQualityMetrics.setCallTxPacketLoss(safeGetLong(map2, "packetsLost"));
            baseQualityMetrics.setCallTxAvgJitterUsec(safeGetLong(map2, "googJitterReceived") * 1000);
            baseQualityMetrics.setCallTxAudioFrameToPacketDelayMs(safeGetLong(map2, "googVoiceCaptureToPacketDelayMs"));
            baseQualityMetrics.setCallRoundTripDelayUsec(safeGetLong(map2, "googRtt") * 1000);
            baseQualityMetrics.setCallTxAudioEncodePreprocessingDelayUsec(safeGetLong(map2, "googAudioEncodePreprocessingLatencyUs"));
            baseQualityMetrics.setCallTxAudioEncodeDelayUsec(safeGetLong(map2, "googAudioEncodeLatencyUs"));
            baseQualityMetrics.setCallTxAudioPacketizationDelayUsec(safeGetLong(map2, "googAudioPacketizationLatencyUs"));
            baseQualityMetrics.setCallTxAudioInputLevel(safeGetLong(map2, "audioInputLevel"));
        } else {
            log.d("txStats is null. Skipping");
        }
        return baseQualityMetrics;
    }

    private HashMap<String, String> parseStatsReport(StatsReport statsReport) {
        StatsReport.Value[] valueArr;
        HashMap<String, String> hashMap = new HashMap<>();
        for (StatsReport.Value value : statsReport.values) {
            hashMap.put(value.name, value.value);
        }
        hashMap.put("id", statsReport.id);
        hashMap.put("type", statsReport.type);
        hashMap.put("timestamp", Double.toString(statsReport.timestamp));
        return hashMap;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0173  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.amazon.comms.calling.sipclient.CallQualityMetrics.VideoQualityMetrics parseVideoMetrics(com.amazon.comms.calling.sipclient.CallQualityMetrics.VideoQualityMetrics r21, java.util.Map<java.lang.String, java.lang.String> r22, java.util.Map<java.lang.String, java.lang.String> r23, java.util.Map<java.lang.String, java.lang.String> r24) {
        /*
            Method dump skipped, instructions count: 395
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.comms.ringservice.webrtc.StatsReportParser.parseVideoMetrics(com.amazon.comms.calling.sipclient.CallQualityMetrics$VideoQualityMetrics, java.util.Map, java.util.Map, java.util.Map):com.amazon.comms.calling.sipclient.CallQualityMetrics$VideoQualityMetrics");
    }

    private void parseWebrtcStatsReports(StatsReport[] statsReportArr) {
        ArrayList arrayList = new ArrayList();
        String num = Integer.toString(statsReportArr.length);
        for (int i = 0; i < statsReportArr.length; i++) {
            arrayList.add(parseStatsReport(statsReportArr[i]));
            if (this.canLogStats) {
                log.i(String.format(Locale.US, "WebRTC StatsReport: [%d/%s]: {%s}", Integer.valueOf(i + 1), num, toSensitiveString(statsReportArr[i])));
            }
        }
        this.webRTCReports = excludeInactiveIce(arrayList);
        initStatsReportMaps(arrayList);
    }

    private boolean safeGetBool(Map<String, String> map, String str) {
        return safeGetBoolImpl(map, str, false);
    }

    private boolean safeGetBoolImpl(Map<String, String> map, String str, boolean z) {
        String str2;
        return (map == null || (str2 = map.get(str)) == null) ? z : Boolean.parseBoolean(str2);
    }

    private long safeGetLong(Map<String, String> map, String str) {
        return safeGetLongImpl(map, str, 0L);
    }

    private long safeGetLongFiltered(Map<String, String> map, String str, long j, long j2, long j3) {
        long safeGetLongImpl = safeGetLongImpl(map, str, j3);
        return (safeGetLongImpl < j || safeGetLongImpl > j2) ? j3 : safeGetLongImpl;
    }

    private long safeGetLongImpl(Map<String, String> map, String str, long j) {
        String str2;
        if (map == null || (str2 = map.get(str)) == null) {
            return j;
        }
        try {
            return Long.parseLong(str2);
        } catch (NumberFormatException unused) {
            log.i(String.format("number format exception for param: %s. Setting value to zero", str));
            return j;
        }
    }

    private String toSensitiveString(StatsReport statsReport) {
        StringBuilder sb = new StringBuilder();
        String str = statsReport.id;
        if ("googCertificate".equals(statsReport.type)) {
            str = "googCertificate_id_redacted";
        }
        GeneratedOutlineSupport1.outline180(sb, "id: ", str, ", type: ");
        sb.append(statsReport.type);
        sb.append(", timestamp: ");
        sb.append(statsReport.timestamp);
        sb.append(", values: ");
        int i = 0;
        while (true) {
            StatsReport.Value[] valueArr = statsReport.values;
            if (i < valueArr.length) {
                sb.append(getSensitiveStatsValue(valueArr[i]).toString());
                if (i != statsReport.values.length - 1) {
                    sb.append(", ");
                }
                i++;
            } else {
                return sb.toString();
            }
        }
    }

    public List<Map<String, String>> getActiveCandidates() {
        return this.activeCandidatePairs;
    }

    public Map<String, String> getAudioRecvSsrc() {
        return this.audioRecvSsrc;
    }

    public Map<String, String> getAudioSendSsrc() {
        return this.audioSendSsrc;
    }

    public CallQualityMetrics getCallQualityMetrics() {
        if (this.metrics == null) {
            this.metrics = new CallQualityMetrics();
            parseVideoMetrics();
            parseAudioMetrics();
            parseCodecData();
        }
        return this.metrics;
    }

    public MediaStats getMediaStats() {
        return this.mediaStats;
    }

    public Map<String, String> getVideoBandwidthReport() {
        return this.videoBandwidthReport;
    }

    public Map<String, String> getVideoRecvSsrc() {
        return this.videoRecvSsrc;
    }

    public Map<String, String> getVideoSendSsrc() {
        return this.videoSendSsrc;
    }

    public String getWebRTCStatsReportsWithOnlyActiveIce() {
        return this.webRTCReports;
    }

    protected void initStatsReportMaps(List<HashMap<String, String>> list) {
        this.mediaStats = new MediaStats(list);
        if (this.activeCandidatePairs == null) {
            this.activeCandidatePairs = findActiveCandidatePairs(this.mediaStats.getMediaStats());
        }
        for (Map<String, String> map : this.mediaStats.getMediaStats()) {
            String str = map.get("type");
            if ("ssrc".equals(str)) {
                boolean containsKey = map.containsKey("bytesReceived");
                if (isAudioSsrc(map)) {
                    if (containsKey) {
                        this.audioRecvSsrc = map;
                    } else {
                        this.audioSendSsrc = map;
                    }
                } else if (!isVideoSsrc(map)) {
                    CommsLogger commsLogger = log;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[initStatsReportMaps] unknown track type: ");
                    outline107.append(map.get(LABEL_TRACK_ID));
                    commsLogger.d(outline107.toString());
                } else if (containsKey) {
                    this.videoRecvSsrc = map;
                } else {
                    this.videoSendSsrc = map;
                }
            } else if ("VideoBwe".equals(str)) {
                this.videoBandwidthReport = map;
            }
        }
    }

    CallQualityMetrics.BaseQualityMetrics parseAudioMetrics() {
        if (this.metrics == null) {
            this.metrics = new CallQualityMetrics();
        }
        if (this.metrics.getAudio() == null) {
            CallQualityMetrics.BaseQualityMetrics baseQualityMetrics = new CallQualityMetrics.BaseQualityMetrics();
            parseBaseMetrics(baseQualityMetrics, this.audioRecvSsrc, this.audioSendSsrc);
            this.metrics.setAudio(baseQualityMetrics);
        }
        return this.metrics.getAudio();
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x001d, code lost:
        if (com.google.common.base.Strings.isNullOrEmpty(r0) != false) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    com.amazon.comms.calling.sipclient.CallQualityMetrics.CodecData parseCodecData() {
        /*
            r4 = this;
            com.amazon.comms.calling.sipclient.CallQualityMetrics r0 = r4.metrics
            if (r0 != 0) goto Lb
            com.amazon.comms.calling.sipclient.CallQualityMetrics r0 = new com.amazon.comms.calling.sipclient.CallQualityMetrics
            r0.<init>()
            r4.metrics = r0
        Lb:
            java.util.Map<java.lang.String, java.lang.String> r0 = r4.videoSendSsrc
            java.lang.String r1 = "googCodecName"
            java.lang.String r2 = "notPresent"
            if (r0 == 0) goto L1f
            java.lang.Object r0 = r0.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            boolean r3 = com.google.common.base.Strings.isNullOrEmpty(r0)
            if (r3 == 0) goto L20
        L1f:
            r0 = r2
        L20:
            java.util.Map<java.lang.String, java.lang.String> r3 = r4.audioSendSsrc
            if (r3 == 0) goto L32
            java.lang.Object r1 = r3.get(r1)
            java.lang.String r1 = (java.lang.String) r1
            boolean r3 = com.google.common.base.Strings.isNullOrEmpty(r1)
            if (r3 == 0) goto L31
            goto L32
        L31:
            r2 = r1
        L32:
            com.amazon.comms.calling.sipclient.CallQualityMetrics$CodecData r1 = new com.amazon.comms.calling.sipclient.CallQualityMetrics$CodecData
            r1.<init>(r0, r2)
            com.amazon.comms.calling.sipclient.CallQualityMetrics r0 = r4.metrics
            r0.setCodecData(r1)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.comms.ringservice.webrtc.StatsReportParser.parseCodecData():com.amazon.comms.calling.sipclient.CallQualityMetrics$CodecData");
    }

    @VisibleForTesting
    StatsReportParser(boolean z) {
        this.lastFramesDecoded = 0L;
        this.lastFramesEncoded = 0L;
        this.lastRxQpSum = 0L;
        this.lastTxQpSum = 0L;
        this.canLogStats = z;
    }

    CallQualityMetrics.VideoQualityMetrics parseVideoMetrics() {
        if (this.metrics == null) {
            this.metrics = new CallQualityMetrics();
        }
        if (this.metrics.getVideo() == null) {
            CallQualityMetrics.VideoQualityMetrics videoQualityMetrics = new CallQualityMetrics.VideoQualityMetrics();
            parseVideoMetrics(videoQualityMetrics, this.videoRecvSsrc, this.videoSendSsrc, this.videoBandwidthReport);
            Map<String, String> map = this.videoSendSsrc;
            if (map != null && map.get("googCodecName") != null && this.videoSendSsrc.get("googCodecName").length() > 0) {
                this.metrics.setVideo(videoQualityMetrics);
            }
        }
        return this.metrics.getVideo();
    }
}
