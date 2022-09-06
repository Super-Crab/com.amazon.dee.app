package com.amazon.comms.ringservice;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.comms.calling.service.MediaStateChangeTrigger;
import com.amazon.comms.calling.service.VideoConstraints;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.ringservice.util.SrtpCryptoType;
import com.amazon.comms.ringservice.util.VideoConstraintsManager;
import com.amazon.comms.ringservice.webrtc.DataChannelProperties;
import com.amazon.comms.util.DeviceProperties;
import com.amazon.comms.util.Size;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.webrtc.IceCandidate;
/* loaded from: classes12.dex */
public class Sdp {
    public static final String ATTRIBUTE_CANDIDATE = "candidate";
    public static final String ATTRIBUTE_END_OF_CANDIDATES = "end-of-candidates";
    private static final String ATTRIBUTE_ICE_OPTIONS = "ice-options";
    public static final String CHANNEL_CONTROL_PARAM = "x-amzn-ctrl";
    public static final String CUSTOM_MEDIA_CAPABILITY = "x-amzn-media-capability";
    public static final String CUSTOM_SCALING = "x-amzn-scale";
    private static final int DATACHANNEL_MAX_ID = 65534;
    private static final String DEFAULT_UNALLOCATED_PORT = "9";
    private static final String DISABLED_MEDIA_PORT = "0";
    private static final String DTLS_ATTRIBUTE = "a=fingerprint";
    private static final String DTLS_ROLE = "a=setup";
    public static final String G729_CODEC = "G729";
    public static final String H264_CODEC = "H264";
    private static final String ICE_OPTION_TRICKLE = "trickle";
    public static final String MEDIA_APPLICATION = "application";
    public static final String MEDIA_AUDIO = "audio";
    public static final String MEDIA_CAPABILITY_RECVONLY = "recvonly";
    public static final String MEDIA_CAPABILITY_SENDONLY = "sendonly";
    public static final String MEDIA_CAPABILITY_SENDRECV = "sendrecv";
    public static final String MEDIA_DIRECTION_INACTIVE = "inactive";
    public static final String MEDIA_DIRECTION_RECVONLY = "recvonly";
    public static final String MEDIA_DIRECTION_SENDONLY = "sendonly";
    public static final String MEDIA_DIRECTION_SENDRECV = "sendrecv";
    public static final String MEDIA_VIDEO = "video";
    private static final int PIXELS_PER_MACROBLOCK = 256;
    public static final String SCREEN_ATTRIBUTE = "x-amzn-screen";
    public static final String SCREEN_ORIENTATION = "x-amzn-screen-orientation";
    private static final String SDES_ATTRIBUTE = "a=crypto";
    private static final int SEARCH_LINE_NOT_FOUND = -1;
    public static final String SUSPEND_LOCAL = "ignoringRemoteVideo";
    public static final String SUSPEND_TRIGGER = "trigger";
    private static final CommsLogger log = CommsLogger.getLogger(Sdp.class);
    private ArrayList<String> lines;
    private final Pattern scalingValuePattern = Pattern.compile("a=x-amzn-scale:(\\d+)x(\\d+)@(\\d+).*");
    private final Pattern maxMbpsPattern = Pattern.compile("max-mbps=(\\d+)");
    private final Pattern dataChannelIdPattern = Pattern.compile("dcmap:(\\d+)");
    private final Pattern dataChannelLabelPattern = Pattern.compile("label=\"(.*?)\"");
    private final Pattern dataChannelProtocolPattern = Pattern.compile("subprotocol=\"(.*?)\"");
    private final ArrayList<String> UNSUPPORTED_AUDIO_CODEC_LIST = new ArrayList<String>() { // from class: com.amazon.comms.ringservice.Sdp.1
        {
            add(Sdp.G729_CODEC);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class CodecRtpMapLocation {
        public String codecRtpMapNumber;
        public int lineLoc;

        public CodecRtpMapLocation(int i, String str) {
            this.lineLoc = i;
            this.codecRtpMapNumber = str;
        }
    }

    /* loaded from: classes12.dex */
    public enum Type {
        OFFER,
        PRANSWER,
        ANSWER
    }

    public Sdp(String str) {
        this.lines = new ArrayList<>(Splitter.on("\r\n").trimResults().splitToList(str));
    }

    private boolean addLastAttribute(int i, String str) {
        int findLastAttribute = findLastAttribute(i);
        if (findLastAttribute == -1) {
            return false;
        }
        String outline72 = GeneratedOutlineSupport1.outline72("a=", str);
        int i2 = findLastAttribute + 1;
        if (i2 >= this.lines.size()) {
            this.lines.add(outline72);
        } else {
            this.lines.add(i2, outline72);
        }
        return true;
    }

    @Nonnull
    private String buildChannelControlParamValue(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append(Config.Compare.EQUAL_TO);
            sb.append(entry.getValue());
            sb.append("; ");
        }
        return sb.toString();
    }

    private String buildDataChannelMapParameters(DataChannelProperties dataChannelProperties) {
        int id = dataChannelProperties.getId();
        String label = dataChannelProperties.getLabel();
        String protocol = dataChannelProperties.getProtocol();
        String num = Integer.toString(id);
        ArrayList arrayList = new ArrayList();
        if (label != null && !label.isEmpty()) {
            arrayList.add("label=\"" + label + EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
        }
        if (protocol != null && !protocol.isEmpty()) {
            arrayList.add("subprotocol=\"" + protocol + EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
        }
        return !arrayList.isEmpty() ? GeneratedOutlineSupport1.outline75(num, " ", Joiner.on(";").join(arrayList)) : num;
    }

    private String buildScreenOrientation(Size size, int i) {
        if (size.getWidth() < size.getHeight()) {
            return DeviceProperties.ScreenOrientation.PORTRAIT.getOrientationString() + "@" + i;
        }
        return DeviceProperties.ScreenOrientation.LANDSCAPE.getOrientationString() + "@" + i;
    }

    private String buildScreenParameters(Size size, String str) {
        return size.toString() + "@" + str;
    }

    private String buildVideoScalingValue(VideoConstraints videoConstraints) {
        return videoConstraints.getVideoWidth() + ReactProperties.HereMapMarker.X + videoConstraints.getVideoHeight() + "@" + videoConstraints.getVideoFps();
    }

    private Sdp changeMediaDirectionImpl(String str, String str2) {
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        while (i < this.lines.size() && !z) {
            String str3 = this.lines.get(i);
            if (isMediaDescription(str3)) {
                if (z2) {
                    break;
                }
                String[] splitMediaDescription = splitMediaDescription(str3);
                if (!isDeleted(splitMediaDescription) && str.equalsIgnoreCase(getMediaType(splitMediaDescription))) {
                    z2 = true;
                }
            } else if (z2) {
                if (str3.contains("recvonly")) {
                    this.lines.set(i, str3.replace("recvonly", str2));
                } else if (str3.contains("sendonly")) {
                    this.lines.set(i, str3.replace("sendonly", str2));
                } else if (str3.contains("sendrecv")) {
                    this.lines.set(i, str3.replace("sendrecv", str2));
                } else if (str3.contains("inactive")) {
                    this.lines.set(i, str3.replace("inactive", str2));
                }
                z = true;
            }
            i++;
        }
        if (z2 && !z) {
            if (i >= this.lines.size()) {
                this.lines.add("a=" + str2);
            } else {
                this.lines.add(i, "a=" + str2);
            }
        }
        return this;
    }

    private int findAttribute(int i, String str) {
        if (i < 0) {
            return -1;
        }
        while (i < this.lines.size()) {
            String str2 = this.lines.get(i);
            if (isMediaDescription(str2)) {
                break;
            } else if (isAttribute(str2) && str.equalsIgnoreCase(stripSdpType(str2.split(":")[0]))) {
                return i;
            } else {
                i++;
            }
        }
        return -1;
    }

    private int findCodecFmtpLocation(String str, String str2) {
        Pattern compile = Pattern.compile("^a=fmtp:" + str + " [\\w-]+=\\d+.*[\r]?$");
        for (int i = 0; i < this.lines.size(); i++) {
            String str3 = this.lines.get(i);
            if (str3.startsWith(str2) && compile.matcher(str3).matches()) {
                return i;
            }
        }
        return -1;
    }

    private CodecRtpMapLocation findCodecRtpMap(String str) {
        String str2;
        Pattern compile = Pattern.compile("^a=rtpmap:(\\d+) " + str + "(/\\d+)+[\r]?$");
        int i = 0;
        while (true) {
            if (i >= this.lines.size()) {
                i = -1;
                str2 = null;
                break;
            }
            String str3 = this.lines.get(i);
            if (str3.startsWith("a=rtpmap:")) {
                Matcher matcher = compile.matcher(str3);
                if (matcher.matches()) {
                    str2 = matcher.group(1);
                    break;
                }
            }
            i++;
        }
        return new CodecRtpMapLocation(i, str2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001b, code lost:
        r1 = r3.lines.get(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0027, code lost:
        if (isAttribute(r1) == false) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0029, code lost:
        return r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002e, code lost:
        if (isMediaDescription(r1) == false) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0031, code lost:
        r4 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0034, code lost:
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0010, code lost:
        if (isMediaDescription(r3.lines.get(r4)) != false) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0019, code lost:
        if (r4 >= r3.lines.size()) goto L14;
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0031 -> B:8:0x0013). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int findFirstAttribute(int r4) {
        /*
            r3 = this;
            r0 = -1
            if (r4 >= 0) goto L4
            return r0
        L4:
            java.util.ArrayList<java.lang.String> r1 = r3.lines
            java.lang.Object r1 = r1.get(r4)
            java.lang.String r1 = (java.lang.String) r1
            boolean r1 = r3.isMediaDescription(r1)
            if (r1 == 0) goto L13
            goto L31
        L13:
            java.util.ArrayList<java.lang.String> r1 = r3.lines
            int r1 = r1.size()
            if (r4 >= r1) goto L34
            java.util.ArrayList<java.lang.String> r1 = r3.lines
            java.lang.Object r1 = r1.get(r4)
            java.lang.String r1 = (java.lang.String) r1
            boolean r2 = r3.isAttribute(r1)
            if (r2 == 0) goto L2a
            return r4
        L2a:
            boolean r1 = r3.isMediaDescription(r1)
            if (r1 == 0) goto L31
            goto L34
        L31:
            int r4 = r4 + 1
            goto L13
        L34:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.comms.ringservice.Sdp.findFirstAttribute(int):int");
    }

    private int findLastAttribute(int i) {
        int i2 = -1;
        if (i < 0) {
            return -1;
        }
        while (i < this.lines.size()) {
            String str = this.lines.get(i);
            if (isAttribute(str)) {
                i2 = i;
            } else if (isMediaDescription(str)) {
                break;
            }
            i++;
        }
        return i2;
    }

    private Sdp forceMediaDirectionInactive(String str) {
        return changeMediaDirectionImpl(str, "inactive");
    }

    private List<Integer> getAllMediaDescriptionLocations() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.lines.size(); i++) {
            if (isMediaDescription(this.lines.get(i))) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        return arrayList;
    }

    private Map<Integer, List<String>> getAllMediaDescriptions() {
        HashMap hashMap = new HashMap();
        int i = 0;
        while (i < this.lines.size() && !isMediaDescription(this.lines.get(i))) {
            i++;
        }
        if (i == this.lines.size()) {
            log.e("No media descriptions found");
            return hashMap;
        }
        LinkedList linkedList = new LinkedList();
        linkedList.add(this.lines.get(i));
        for (int i2 = i + 1; i2 < this.lines.size(); i2++) {
            String str = this.lines.get(i2);
            if (isMediaDescription(str)) {
                hashMap.put(Integer.valueOf(i), linkedList);
                LinkedList linkedList2 = new LinkedList();
                linkedList2.add(str);
                linkedList = linkedList2;
                i = i2;
            } else {
                linkedList.add(str);
            }
        }
        hashMap.put(Integer.valueOf(i), linkedList);
        return hashMap;
    }

    private Map<String, String> getChannelControlParams(String str) {
        HashMap hashMap = new HashMap();
        int findAttribute = findAttribute(str, CHANNEL_CONTROL_PARAM);
        if (findAttribute != -1) {
            for (String str2 : new ArrayList(Splitter.on(";").trimResults().omitEmptyStrings().splitToList(this.lines.get(findAttribute).replaceFirst(".*:", "")))) {
                String[] split = str2.split(Config.Compare.EQUAL_TO);
                if (split.length == 2) {
                    try {
                        hashMap.put(split[0].trim(), split[1].trim());
                    } catch (NumberFormatException unused) {
                        CommsLogger commsLogger = log;
                        commsLogger.w("Caught NumberFormatException. Omitting malformed sdp control param: " + str2);
                    }
                }
            }
        }
        return hashMap;
    }

    private String getDesiredMediaDirection(String str, boolean z) {
        String mediaDirection = getMediaDirection(str);
        if (mediaDirection == null) {
            GeneratedOutlineSupport1.outline159("Cannot get desired media direction as type is deleted or not present. type= ", str, log);
            return null;
        }
        log.d(String.format("Media direction in the SDP is set to %s for %s and now will be enabled = %s", mediaDirection, str, Boolean.valueOf(z)));
        return !z ? "sendrecv".equalsIgnoreCase(mediaDirection) ? "recvonly" : "sendonly".equalsIgnoreCase(mediaDirection) ? "inactive" : mediaDirection : mediaDirection;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getMediaType(String[] strArr) {
        return stripSdpType(strArr[0]);
    }

    private boolean isApplicationMediaType(String[] strArr) {
        return "application".equalsIgnoreCase(getMediaType(strArr));
    }

    private boolean isAttribute(String str) {
        return str.startsWith("a=");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDeleted(String[] strArr) {
        return strArr[1].equals(DISABLED_MEDIA_PORT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isMediaDescription(String str) {
        return str.startsWith("m=");
    }

    private boolean noSupportedAudioCodecsPresentUtil(int i, int i2) {
        while (i <= i2) {
            String str = this.lines.get(i);
            for (int i3 = 0; i3 < this.UNSUPPORTED_AUDIO_CODEC_LIST.size(); i3++) {
                if (str.startsWith("a=rtpmap:") && !str.toLowerCase().contains(this.UNSUPPORTED_AUDIO_CODEC_LIST.get(i3).toLowerCase())) {
                    return false;
                }
            }
            i++;
        }
        log.e("No supported Audio codec present.");
        return true;
    }

    private String replaceApplicationMediaDescription(String[] strArr, String str) {
        String str2 = strArr[3];
        CommsLogger commsLogger = log;
        commsLogger.i("Original media description:" + str);
        String replace = str.replace(str2, "webrtc-datachannel");
        CommsLogger commsLogger2 = log;
        commsLogger2.i("Modified media description to remove sctp-port number:" + replace);
        return replace;
    }

    private void setAttribute(String str, String str2, String str3) {
        int findFirstAttribute = findFirstAttribute(str);
        if (findFirstAttribute != -1) {
            setAttribute(findFirstAttribute, str2, str3);
            return;
        }
        CommsLogger commsLogger = log;
        commsLogger.i("No mediaType: " + str + " exists in SDP. Omitting attribute: " + str2);
    }

    private void setChannelControlParams(String str, Map<String, String> map) {
        if (map.isEmpty()) {
            map.put(SUSPEND_LOCAL, DISABLED_MEDIA_PORT);
            map.put(SUSPEND_TRIGGER, MediaStateChangeTrigger.REMOTE_USER_REQUEST.name());
        }
        setAttribute(str, CHANNEL_CONTROL_PARAM, buildChannelControlParamValue(map));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String[] splitMediaDescription(String str) {
        return str.split(" ");
    }

    private String stripAttributeValue(String str, String str2) {
        int indexOf = str.indexOf(":") + 1;
        String[] split = str.substring(indexOf).split(" ");
        StringBuilder sb = new StringBuilder(str.length());
        sb.append(str.substring(0, indexOf));
        for (String str3 : split) {
            if (!str3.equalsIgnoreCase(str2) && !str3.isEmpty()) {
                sb.append(str3);
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }

    private void stripSdes() {
        ArrayList<String> arrayList = new ArrayList<>(this.lines.size());
        Iterator<String> it2 = this.lines.iterator();
        while (it2.hasNext()) {
            String next = it2.next();
            if (!next.startsWith(SDES_ATTRIBUTE)) {
                arrayList.add(next);
            }
        }
        this.lines = arrayList;
    }

    private String stripSdpType(String str) {
        return str.substring(2);
    }

    public synchronized boolean addIceCandidate(IceCandidate iceCandidate) {
        log.i(String.format(Locale.US, "addIceCandidate mediaIndex = %s", Integer.valueOf(iceCandidate.sdpMLineIndex)));
        int findMediaSection = findMediaSection(iceCandidate.sdpMLineIndex);
        if (findMediaSection == -1) {
            log.w(String.format(Locale.US, "addIceCandidate: media section not found for index = %s", Integer.valueOf(iceCandidate.sdpMLineIndex)));
            return false;
        }
        int i = findMediaSection + 1;
        setAttribute(i, "mid", iceCandidate.sdpMid);
        boolean addLastAttribute = addLastAttribute(i, iceCandidate.sdp);
        if (!addLastAttribute) {
            log.e("addIceCandidate: adding candidate attribute failed.");
        }
        return addLastAttribute;
    }

    public synchronized void appendMidIfSessionDeleted(String str, String str2) {
        int findMediaSection = findMediaSection(str);
        if (findMediaSection != -1 && isDeleted(splitMediaDescription(this.lines.get(findMediaSection)))) {
            if (findAttribute(str, "mid") == -1) {
                ArrayList<String> arrayList = this.lines;
                int i = findMediaSection + 1;
                arrayList.add(i, "a=mid:" + str2);
                CommsLogger commsLogger = log;
                commsLogger.i("Adding mid value: " + this.lines.get(i));
            }
        }
    }

    public synchronized void appendVideoConstraints(VideoConstraints videoConstraints) {
        int findMediaSection = findMediaSection("video");
        String str = null;
        Locale locale = Locale.US;
        if (findMediaSection == -1) {
            return;
        }
        Pattern compile = Pattern.compile("^a=rtpmap:(\\d+) H264(/\\d+)+[\r]?$");
        int i = findMediaSection;
        while (true) {
            if (i >= this.lines.size()) {
                i = -1;
                break;
            }
            String str2 = this.lines.get(i);
            if (str2.startsWith("a=rtpmap:")) {
                Matcher matcher = compile.matcher(str2);
                if (matcher.matches()) {
                    str = matcher.group(1);
                    break;
                }
            }
            i++;
        }
        if (str == null) {
            log.w("No rtpmap for H264 codec");
            return;
        }
        log.d("Found H264 rtpmap " + str + " at " + this.lines.get(i));
        StringBuilder sb = new StringBuilder();
        sb.append("a=fmtp:");
        sb.append(str);
        String sb2 = sb.toString();
        String str3 = "";
        while (true) {
            if (findMediaSection >= this.lines.size()) {
                findMediaSection = -1;
                break;
            }
            str3 = this.lines.get(findMediaSection);
            if (str3.contains(sb2)) {
                log.i("Found fmtp line for codec: H264 in media section: " + str3);
                break;
            }
            findMediaSection++;
        }
        Integer valueOf = Integer.valueOf(((videoConstraints.getVideoWidth() * videoConstraints.getVideoHeight()) * videoConstraints.getVideoFps()) / 256);
        int intValue = valueOf.intValue() / videoConstraints.getVideoFps();
        if (findMediaSection == -1) {
            String format = String.format(locale, "a=fmtp:%s %s=%d;%s=%d", str, "max-mbps", valueOf, "max-fs", Integer.valueOf(intValue));
            log.d("Add SDP line: " + format);
            this.lines.add(i + 1, format);
        } else {
            String str4 = (str3 + String.format(locale, ";%s=%d", "max-mbps", valueOf)) + String.format(locale, ";%s=%d", "max-fs", Integer.valueOf(intValue));
            log.d("Update SDP line: " + str4);
            this.lines.set(findMediaSection, str4);
        }
    }

    public synchronized Sdp changeMediaDirection(String str, boolean z) {
        return changeMediaDirectionImpl(str, getDesiredMediaDirection(str, z));
    }

    @VisibleForTesting
    int findMediaSection(String str) {
        if (str == null) {
            return 0;
        }
        String outline72 = GeneratedOutlineSupport1.outline72("m=", str);
        for (int i = 0; i < this.lines.size(); i++) {
            if (this.lines.get(i).startsWith(outline72)) {
                return i;
            }
        }
        return -1;
    }

    public List<DataChannelProperties> getActiveDataChannels() {
        int parseInt;
        ArrayList arrayList = new ArrayList();
        for (String str : getAllAttributeMatchesInSection("application", "dcmap")) {
            GeneratedOutlineSupport1.outline159("SDP line: ", str, log);
            Matcher matcher = this.dataChannelIdPattern.matcher(str);
            Matcher matcher2 = this.dataChannelLabelPattern.matcher(str);
            Matcher matcher3 = this.dataChannelProtocolPattern.matcher(str);
            if (!matcher.find()) {
                log.w("Parsing error for sdp line: a=dcmap with invalid id");
                return arrayList;
            }
            try {
                parseInt = Integer.parseInt(matcher.group(1));
            } catch (NumberFormatException e) {
                CommsLogger commsLogger = log;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Parsing error for sdp line: a=dcmap with invalid id");
                outline107.append(e.getMessage());
                commsLogger.w(outline107.toString());
            }
            if (parseInt >= 0 && parseInt <= 65534) {
                String str2 = "";
                String group = matcher2.find() ? matcher2.group(1) : str2;
                if (matcher3.find()) {
                    str2 = matcher3.group(1);
                }
                arrayList.add(DataChannelProperties.builder().id(parseInt).label(group).protocol(str2).build());
            }
            log.w("Parsing error for sdp line: a=dcmap with invalid id range");
            return arrayList;
        }
        return arrayList;
    }

    public Set<String> getActiveMediaTypes() {
        Map<String, String> mediaDirections = getMediaDirections();
        HashSet hashSet = new HashSet();
        for (Map.Entry<String, String> entry : mediaDirections.entrySet()) {
            if ("sendonly".equalsIgnoreCase(entry.getValue()) || "sendrecv".equalsIgnoreCase(entry.getValue())) {
                hashSet.add(entry.getKey());
            }
        }
        return hashSet;
    }

    public synchronized List<String> getAllAttributeMatchesInSection(String str, String str2) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        int findMediaSection = findMediaSection(str);
        while (findMediaSection != -1) {
            findMediaSection = findAttribute(findMediaSection + 1, str2);
            if (findMediaSection != -1) {
                arrayList.add(stripSdpType(this.lines.get(findMediaSection)));
            }
        }
        return arrayList;
    }

    public synchronized String getDTLSRole() {
        Iterator<String> it2 = this.lines.iterator();
        while (it2.hasNext()) {
            String next = it2.next();
            if (next.startsWith(DTLS_ROLE)) {
                return next.substring(8);
            }
        }
        return null;
    }

    public synchronized VideoConstraints getDynamicVideoConstraints(Map<Integer, VideoConstraints> map) {
        int findMediaSection = findMediaSection("video");
        VideoConstraints videoConstraints = null;
        if (findMediaSection == -1) {
            return null;
        }
        String str = "";
        while (findMediaSection < this.lines.size()) {
            str = this.lines.get(findMediaSection);
            if (!str.contains("max-mbps") && !str.contains("max-fs") && !str.contains("max-fps") && !str.contains("max-br")) {
                findMediaSection++;
            }
            log.i("Found dynamic video constraint line:" + str);
        }
        findMediaSection = -1;
        if (findMediaSection == -1) {
            return null;
        }
        Matcher matcher = this.maxMbpsPattern.matcher(str);
        if (!matcher.find()) {
            log.w("Unable to find mbps parameter in sdp line:" + str);
        } else {
            try {
                videoConstraints = VideoConstraintsManager.getVideoConstraints(map, Integer.parseInt(matcher.group(1)));
            } catch (NumberFormatException unused) {
                log.w("Parsing error for sdp line:  " + str);
                return null;
            }
        }
        int indexOf = str.indexOf(" ", str.indexOf("fmtp:")) + 1;
        String[] split = str.substring(indexOf).split(";");
        StringBuilder sb = new StringBuilder(str.length());
        sb.append(str.substring(0, indexOf));
        for (String str2 : split) {
            if (!str2.isEmpty() && !str2.contains("max-mbps") && !str2.contains("max-fs") && !str2.contains("max-fps") && !str2.contains("max-br")) {
                sb.append(str2);
                sb.append(";");
            }
        }
        String sb2 = sb.toString();
        this.lines.set(findMediaSection, sb2.substring(0, sb2.length() - 1));
        return videoConstraints;
    }

    public synchronized List<IceCandidate> getIceCandidates() {
        Vector vector;
        vector = new Vector();
        String str = "";
        int i = -1;
        for (int i2 = 0; i2 < this.lines.size(); i2++) {
            String str2 = this.lines.get(i2);
            if (str2.startsWith("m=")) {
                i++;
                str = getMediaType(str2.split(" "));
                List<String> allAttributeMatchesInSection = getAllAttributeMatchesInSection(str, "mid");
                if (allAttributeMatchesInSection.size() != 0) {
                    str = allAttributeMatchesInSection.get(0).replace("mid:", "");
                }
            } else if (str2.startsWith("a=candidate") && !str.isEmpty()) {
                vector.add(new IceCandidate(str, i, str2));
            }
        }
        log.i("getIceCandidates candidateList size = " + vector.size());
        return vector;
    }

    public synchronized String getMediaCapability(String str) {
        int findAttribute = findAttribute(str, CUSTOM_MEDIA_CAPABILITY);
        if (findAttribute != -1) {
            String str2 = this.lines.get(findAttribute);
            if (str2.contains("recvonly")) {
                return "recvonly";
            }
            if (str2.contains("sendonly")) {
                return "sendonly";
            }
        }
        return "sendrecv";
    }

    protected int getMediaDescriptionCount(boolean z) {
        return Iterables.size(getMediaDescriptions(z));
    }

    protected Iterable<String> getMediaDescriptionTypes(boolean z) {
        return Iterables.transform(getMediaDescriptions(z), new Function<String, String>() { // from class: com.amazon.comms.ringservice.Sdp.2
            @Override // com.google.common.base.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public String mo8172apply(String str) {
                Sdp sdp = Sdp.this;
                return sdp.getMediaType(sdp.splitMediaDescription(str));
            }
        });
    }

    public synchronized Iterable<String> getMediaDescriptions() {
        return getMediaDescriptions(false);
    }

    public synchronized String getMediaDirection(String str) {
        boolean z = false;
        Iterator<String> it2 = this.lines.iterator();
        while (it2.hasNext()) {
            String next = it2.next();
            if (isMediaDescription(next)) {
                if (z) {
                    break;
                }
                String[] splitMediaDescription = splitMediaDescription(next);
                if (!isDeleted(splitMediaDescription) && str.equalsIgnoreCase(getMediaType(splitMediaDescription))) {
                    z = true;
                }
            } else if (!z) {
                continue;
            } else if (next.contains("recvonly")) {
                return "recvonly";
            } else {
                if (next.contains("sendonly")) {
                    return "sendonly";
                }
                if (next.contains("inactive")) {
                    return "inactive";
                }
                if (next.contains("sendrecv")) {
                    return "sendrecv";
                }
            }
        }
        return z ? "sendrecv" : null;
    }

    public synchronized Map<String, String> getMediaDirections() {
        HashMap hashMap;
        hashMap = new HashMap();
        Iterator<String> it2 = this.lines.iterator();
        String str = null;
        while (it2.hasNext()) {
            String next = it2.next();
            if (isMediaDescription(next)) {
                String[] splitMediaDescription = splitMediaDescription(next);
                String mediaType = getMediaType(splitMediaDescription);
                if (isDeleted(splitMediaDescription)) {
                    mediaType = null;
                } else {
                    hashMap.put(mediaType, "sendrecv");
                }
                str = mediaType;
            } else if (!Strings.isNullOrEmpty(str)) {
                if (next.contains("recvonly")) {
                    hashMap.put(str, "recvonly");
                } else if (next.contains("sendonly")) {
                    hashMap.put(str, "sendonly");
                } else if (next.contains("inactive")) {
                    hashMap.put(str, "inactive");
                } else if (next.contains("sendrecv")) {
                    hashMap.put(str, "sendrecv");
                }
            }
        }
        return hashMap;
    }

    public synchronized String getMid(String str) {
        int findAttribute = findAttribute(str, "mid");
        if (findAttribute == -1) {
            return null;
        }
        return this.lines.get(findAttribute).substring(6);
    }

    public synchronized Sdp getPseudoSdpForIceTrickling() {
        ArrayList arrayList;
        log.i("getPseudoSdpForIceTrickling");
        arrayList = new ArrayList();
        Iterator<String> it2 = this.lines.iterator();
        while (it2.hasNext()) {
            String next = it2.next();
            if (next.startsWith("m=") || next.startsWith("a=mid") || next.startsWith("a=ice-ufrag") || next.startsWith("a=ice-pwd")) {
                arrayList.add(next);
            }
        }
        return new Sdp(Joiner.on("\r\n").join(arrayList));
    }

    public synchronized String getSessionName() {
        Iterator<String> it2 = this.lines.iterator();
        while (it2.hasNext()) {
            String next = it2.next();
            if (next.startsWith("s=")) {
                return next.substring(2).trim();
            }
        }
        return "";
    }

    public synchronized String getSrtpKeying() {
        boolean z = false;
        Iterator<String> it2 = this.lines.iterator();
        while (true) {
            if (it2.hasNext()) {
                if (it2.next().startsWith(DTLS_ATTRIBUTE)) {
                    z = true;
                    break;
                }
            } else {
                break;
            }
        }
        if (z) {
            stripSdes();
            return SrtpCryptoType.DTLS.toString();
        }
        return SrtpCryptoType.SDES.toString();
    }

    public synchronized VideoConstraints getVideoConstraints() {
        VideoConstraints videoConstraints;
        int findAttribute = findAttribute("video", CUSTOM_SCALING);
        videoConstraints = null;
        if (findAttribute != -1) {
            Matcher matcher = this.scalingValuePattern.matcher(this.lines.get(findAttribute));
            if (!matcher.matches()) {
                log.w("Parsing error for sdp line: a=x-amzn-scale discarding remote video constraints");
            } else {
                try {
                    VideoConstraints videoConstraints2 = new VideoConstraints(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)));
                    try {
                        if (videoConstraints2.compareTo(new VideoConstraints(0, 0, 0)) > 0) {
                            videoConstraints = videoConstraints2;
                        }
                    } catch (NumberFormatException unused) {
                        videoConstraints = videoConstraints2;
                        log.w("Parsing error for sdp line: a=x-amzn-scale discarding remote video constraints");
                        return videoConstraints;
                    }
                } catch (NumberFormatException unused2) {
                }
            }
        }
        return videoConstraints;
    }

    public synchronized Map.Entry<Boolean, MediaStateChangeTrigger> getVideoSuspendedData() {
        Map<String, String> channelControlParams;
        MediaStateChangeTrigger mediaStateChangeTrigger = MediaStateChangeTrigger.REMOTE_USER_REQUEST;
        boolean z = false;
        try {
            channelControlParams = getChannelControlParams("video");
        } catch (IllegalArgumentException | NullPointerException e) {
            log.w("ignoring trigger event due to exception: " + e.getMessage());
        }
        if (channelControlParams != null && !channelControlParams.isEmpty()) {
            Integer valueOf = Integer.valueOf(Integer.parseInt(channelControlParams.get(SUSPEND_LOCAL)));
            boolean z2 = true;
            if (valueOf == null || valueOf.intValue() != 1) {
                z2 = false;
            }
            String str = channelControlParams.get(SUSPEND_TRIGGER);
            if (str != null) {
                mediaStateChangeTrigger = MediaStateChangeTrigger.valueOf(str);
            }
            z = z2;
            return new AbstractMap.SimpleImmutableEntry(Boolean.valueOf(z), mediaStateChangeTrigger);
        }
        log.d("x-amzn-ctrl params null or empty");
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0024, code lost:
        r1[2] = java.lang.Integer.toString(java.lang.Integer.parseInt(r1[2]) + 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0033, code lost:
        r4.lines.set(r0, com.google.common.base.Joiner.on((char) org.apache.logging.log4j.util.Chars.SPACE).join(r1));
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001a, code lost:
        r1 = r1.split(" ");
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0022, code lost:
        if (r1.length != 6) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized com.amazon.comms.ringservice.Sdp incrementVersionNumber() {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = 0
        L2:
            java.util.ArrayList<java.lang.String> r1 = r4.lines     // Catch: java.lang.Throwable -> L48
            int r1 = r1.size()     // Catch: java.lang.Throwable -> L48
            if (r0 >= r1) goto L46
            java.util.ArrayList<java.lang.String> r1 = r4.lines     // Catch: java.lang.Throwable -> L48
            java.lang.Object r1 = r1.get(r0)     // Catch: java.lang.Throwable -> L48
            java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.Throwable -> L48
            java.lang.String r2 = "o="
            boolean r2 = r1.startsWith(r2)     // Catch: java.lang.Throwable -> L48
            if (r2 == 0) goto L43
            java.lang.String r2 = " "
            java.lang.String[] r1 = r1.split(r2)     // Catch: java.lang.Throwable -> L48
            int r2 = r1.length     // Catch: java.lang.Throwable -> L48
            r3 = 6
            if (r2 != r3) goto L33
            r2 = 2
            r3 = r1[r2]     // Catch: java.lang.Throwable -> L48
            int r3 = java.lang.Integer.parseInt(r3)     // Catch: java.lang.Throwable -> L48
            int r3 = r3 + 1
            java.lang.String r3 = java.lang.Integer.toString(r3)     // Catch: java.lang.Throwable -> L48
            r1[r2] = r3     // Catch: java.lang.Throwable -> L48
        L33:
            r2 = 32
            com.google.common.base.Joiner r2 = com.google.common.base.Joiner.on(r2)     // Catch: java.lang.Throwable -> L48
            java.lang.String r1 = r2.join(r1)     // Catch: java.lang.Throwable -> L48
            java.util.ArrayList<java.lang.String> r2 = r4.lines     // Catch: java.lang.Throwable -> L48
            r2.set(r0, r1)     // Catch: java.lang.Throwable -> L48
            goto L46
        L43:
            int r0 = r0 + 1
            goto L2
        L46:
            monitor-exit(r4)
            return r4
        L48:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.comms.ringservice.Sdp.incrementVersionNumber():com.amazon.comms.ringservice.Sdp");
    }

    public synchronized boolean isAttributePresent(String str) {
        log.d(String.format(Locale.US, "isAttributePresent attributeName = %s", str));
        for (int i = 0; i < this.lines.size(); i++) {
            String str2 = this.lines.get(i);
            if (isAttribute(str2) && str.equalsIgnoreCase(stripSdpType(str2.split(":")[0]))) {
                log.d("Attribute present");
                return true;
            }
        }
        log.d("Attribute not present");
        return false;
    }

    public synchronized boolean isCodecPresent(String str) {
        Pattern compile = Pattern.compile("^a=rtpmap:(\\d+) " + str + "(/\\d+)+[\r]?$");
        Iterator<String> it2 = this.lines.iterator();
        while (it2.hasNext()) {
            String next = it2.next();
            if (next.startsWith("a=rtpmap") && compile.matcher(next).matches()) {
                CommsLogger commsLogger = log;
                commsLogger.d("found rtp codec match for " + str);
                return true;
            }
        }
        CommsLogger commsLogger2 = log;
        commsLogger2.i("No rtpmap found for codec " + str);
        return false;
    }

    public synchronized boolean isMediaPresent(String str) {
        return isMediaPresent(str, false);
    }

    public boolean isMediaSendCapable(String str) {
        return getMediaCapability(str).equals("sendrecv") || getMediaCapability(str).equals("sendonly");
    }

    public synchronized boolean isTrickleIceEnabled() {
        for (int i = 0; i < this.lines.size(); i++) {
            String str = this.lines.get(i);
            if (isAttribute(str)) {
                String[] split = str.split(":");
                if (!ATTRIBUTE_ICE_OPTIONS.equalsIgnoreCase(stripSdpType(split[0]))) {
                    continue;
                } else if (split.length < 2) {
                    log.w("isTrickleIceEnabled: ice-options with no options listed");
                } else {
                    for (String str2 : split[1].split(" ")) {
                        if (ICE_OPTION_TRICKLE.equalsIgnoreCase(str2)) {
                            log.i("isTrickleIceEnabled: trickle enaled");
                            return true;
                        }
                    }
                    continue;
                }
            }
        }
        log.i("isTrickleIceEnabled: trickle disabled");
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0026, code lost:
        r2[1] = com.amazon.comms.ringservice.Sdp.DISABLED_MEDIA_PORT;
        r4.lines.set(r0, com.google.common.base.Joiner.on(" ").join(r2));
        r5 = com.amazon.comms.ringservice.Sdp.log;
        r5.i("Found media, setting port to zero: " + r1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized com.amazon.comms.ringservice.Sdp markMediaDeleted(java.lang.String r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = 0
        L2:
            java.util.ArrayList<java.lang.String> r1 = r4.lines     // Catch: java.lang.Throwable -> L56
            int r1 = r1.size()     // Catch: java.lang.Throwable -> L56
            if (r0 >= r1) goto L54
            java.util.ArrayList<java.lang.String> r1 = r4.lines     // Catch: java.lang.Throwable -> L56
            java.lang.Object r1 = r1.get(r0)     // Catch: java.lang.Throwable -> L56
            java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.Throwable -> L56
            boolean r2 = r4.isMediaDescription(r1)     // Catch: java.lang.Throwable -> L56
            if (r2 == 0) goto L51
            java.lang.String[] r2 = r4.splitMediaDescription(r1)     // Catch: java.lang.Throwable -> L56
            java.lang.String r3 = r4.getMediaType(r2)     // Catch: java.lang.Throwable -> L56
            boolean r3 = r5.equalsIgnoreCase(r3)     // Catch: java.lang.Throwable -> L56
            if (r3 == 0) goto L51
            java.lang.String r5 = "0"
            r3 = 1
            r2[r3] = r5     // Catch: java.lang.Throwable -> L56
            java.util.ArrayList<java.lang.String> r5 = r4.lines     // Catch: java.lang.Throwable -> L56
            java.lang.String r3 = " "
            com.google.common.base.Joiner r3 = com.google.common.base.Joiner.on(r3)     // Catch: java.lang.Throwable -> L56
            java.lang.String r2 = r3.join(r2)     // Catch: java.lang.Throwable -> L56
            r5.set(r0, r2)     // Catch: java.lang.Throwable -> L56
            com.amazon.comms.log.CommsLogger r5 = com.amazon.comms.ringservice.Sdp.log     // Catch: java.lang.Throwable -> L56
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L56
            r0.<init>()     // Catch: java.lang.Throwable -> L56
            java.lang.String r2 = "Found media, setting port to zero: "
            r0.append(r2)     // Catch: java.lang.Throwable -> L56
            r0.append(r1)     // Catch: java.lang.Throwable -> L56
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L56
            r5.i(r0)     // Catch: java.lang.Throwable -> L56
            goto L54
        L51:
            int r0 = r0 + 1
            goto L2
        L54:
            monitor-exit(r4)
            return r4
        L56:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.comms.ringservice.Sdp.markMediaDeleted(java.lang.String):com.amazon.comms.ringservice.Sdp");
    }

    public synchronized Sdp matchMediaPortsIfBundled() {
        int findAttribute = findAttribute(0, "group");
        if (findAttribute != -1 && this.lines.get(findAttribute).contains("BUNDLE")) {
            CommsLogger commsLogger = log;
            commsLogger.i("Found BUNDLE line: " + this.lines.get(findAttribute));
            List asList = Arrays.asList(this.lines.get(findAttribute).split(" "));
            Map<Integer, List<String>> allMediaDescriptions = getAllMediaDescriptions();
            if (allMediaDescriptions.isEmpty()) {
                return this;
            }
            String str = "";
            HashMap hashMap = new HashMap();
            for (Map.Entry<Integer, List<String>> entry : allMediaDescriptions.entrySet()) {
                Iterator<String> it2 = entry.getValue().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        String next = it2.next();
                        if (next.contains("mid") && asList.contains(next.substring(6))) {
                            hashMap.put(entry.getKey(), entry.getValue());
                            String[] split = entry.getValue().get(0).split(" ");
                            if (!DISABLED_MEDIA_PORT.equals(split[1]) && !DEFAULT_UNALLOCATED_PORT.equals(split[1])) {
                                str = split[1];
                                CommsLogger commsLogger2 = log;
                                commsLogger2.i("Found desired media port: " + str);
                            }
                        }
                    }
                }
            }
            if (str.isEmpty()) {
                log.w("Could not find media port.");
                return this;
            }
            for (Integer num : hashMap.keySet()) {
                String str2 = this.lines.get(num.intValue());
                String[] split2 = str2.split(" ");
                if (DISABLED_MEDIA_PORT.equals(split2[1])) {
                    CommsLogger commsLogger3 = log;
                    commsLogger3.i("Skipping media line because port is disabled: " + str2);
                } else {
                    split2[1] = str;
                    String join = Joiner.on(" ").join(split2);
                    this.lines.set(num.intValue(), join);
                    CommsLogger commsLogger4 = log;
                    commsLogger4.i("Changing port:" + join);
                }
            }
            return this;
        }
        log.w("No BUNDLE found");
        return this;
    }

    public synchronized Sdp modifyApplicationMediaDescription() {
        ListIterator<String> listIterator = this.lines.listIterator();
        while (listIterator.hasNext()) {
            String next = listIterator.next();
            if (isMediaDescription(next)) {
                String[] splitMediaDescription = splitMediaDescription(next);
                if (isApplicationMediaType(splitMediaDescription)) {
                    listIterator.set(replaceApplicationMediaDescription(splitMediaDescription, next));
                    return this;
                }
            }
        }
        return this;
    }

    public synchronized void modifyDTLSRole(String str) {
        ArrayList<String> arrayList = new ArrayList<>(this.lines.size());
        Iterator<String> it2 = this.lines.iterator();
        while (it2.hasNext()) {
            String next = it2.next();
            if (next.startsWith(DTLS_ROLE)) {
                arrayList.add("a=setup:" + str);
            } else {
                arrayList.add(next);
            }
        }
        this.lines = arrayList;
    }

    public synchronized boolean noSupportedAudioCodecsPresent() {
        int findMediaSection;
        int findMediaSection2;
        findMediaSection = findMediaSection("audio");
        findMediaSection2 = findMediaSection("video");
        if (findMediaSection2 == -1 || findMediaSection2 <= findMediaSection) {
            findMediaSection2 = this.lines.size();
        }
        return noSupportedAudioCodecsPresentUtil(findMediaSection, findMediaSection2 - 1);
    }

    public synchronized Sdp preferCodec(String str, boolean z) {
        Pattern compile = Pattern.compile("^a=rtpmap:(\\d+) " + str + "(/\\d+)+[\r]?$");
        String str2 = z ? "m=audio " : "m=video ";
        final String str3 = null;
        int i = -1;
        for (int i2 = 0; i2 < this.lines.size() && (i == -1 || str3 == null); i2++) {
            String str4 = this.lines.get(i2);
            if (str4.startsWith(str2)) {
                i = i2;
            } else if (str4.startsWith("a=rtpmap:")) {
                Matcher matcher = compile.matcher(str4);
                if (matcher.matches()) {
                    str3 = matcher.group(1);
                }
            }
        }
        if (i == -1) {
            log.w("No " + str2 + " line, so can't prefer " + str);
        } else if (str3 == null) {
            log.w("No rtpmap for " + str);
        } else {
            log.d("Found " + str + " rtpmap " + str3 + ", prefer at " + this.lines.get(i));
            ArrayList arrayList = new ArrayList(Arrays.asList(this.lines.get(i).split(" ")));
            if (arrayList.size() >= 3) {
                ArrayList arrayList2 = new ArrayList(Collections2.filter(arrayList.subList(3, arrayList.size()), new Predicate<String>() { // from class: com.amazon.comms.ringservice.Sdp.4
                    @Override // com.google.common.base.Predicate
                    public boolean apply(String str5) {
                        return !str5.equals(str3);
                    }
                }));
                arrayList2.add(0, str3);
                List subList = arrayList.subList(0, 3);
                subList.addAll(arrayList2);
                this.lines.set(i, Joiner.on(" ").join(subList));
                log.d("Change media description: " + this.lines.get(i));
            } else {
                log.e("Wrong SDP media description format: " + this.lines.get(i));
            }
        }
        return this;
    }

    @VisibleForTesting
    boolean rawSdpLineMatchesString(int i, String str) {
        return this.lines.get(i).equals(str);
    }

    public synchronized void removeTrickleIce() {
        int findAttribute;
        for (String str : getMediaDescriptionTypes(true)) {
            int findMediaSection = findMediaSection(str);
            if (findMediaSection > 0 && (findAttribute = findAttribute(findMediaSection + 1, ATTRIBUTE_ICE_OPTIONS)) != -1) {
                this.lines.set(findAttribute, stripAttributeValue(this.lines.get(findAttribute), ICE_OPTION_TRICKLE));
            }
        }
    }

    public synchronized Sdp replaceRTPProfile(String str) {
        for (int i = 0; i < this.lines.size(); i++) {
            String str2 = this.lines.get(i);
            if (isMediaDescription(str2)) {
                String[] split = str2.split(" ");
                String[] split2 = split[2].split("/");
                if (!new HashSet(Arrays.asList(split2)).contains("RTP")) {
                    log.e("No RTP found in media description");
                    return this;
                }
                split2[split2.length - 1] = str;
                split[2] = Joiner.on("/").join(split2);
                this.lines.set(i, Joiner.on(" ").join(split));
            }
        }
        return this;
    }

    public synchronized Sdp setBitrate(String str, boolean z, String str2, int i, boolean z2) {
        boolean z3;
        if (!z) {
            i *= 1000;
        }
        int i2 = -1;
        String str3 = null;
        Pattern compile = Pattern.compile("^a=rtpmap:(\\d+) " + str + "(/\\d+)+[\r]?$");
        int i3 = 0;
        while (true) {
            if (i3 >= this.lines.size()) {
                break;
            }
            String str4 = this.lines.get(i3);
            if (str4.startsWith("a=rtpmap:")) {
                Matcher matcher = compile.matcher(str4);
                if (matcher.matches()) {
                    str3 = matcher.group(1);
                    i2 = i3;
                    break;
                }
            }
            i3++;
        }
        if (str3 == null) {
            log.w("No rtpmap for " + str + " codec");
            return this;
        }
        log.d("Found " + str + " rtpmap " + str3 + " at " + this.lines.get(i2));
        StringBuilder sb = new StringBuilder();
        sb.append("a=fmtp:");
        sb.append(str3);
        String sb2 = sb.toString();
        Locale locale = Locale.US;
        Pattern compile2 = Pattern.compile("^a=fmtp:" + str3 + " [\\w-]+=\\d+.*[\r]?$");
        for (int i4 = 0; i4 < this.lines.size(); i4++) {
            String str5 = this.lines.get(i4);
            if (str5.startsWith(sb2) && compile2.matcher(str5).matches()) {
                log.i("SDP line: " + str5);
                ArrayList arrayList = new ArrayList(Splitter.on(";").trimResults().splitToList(str5.substring(sb2.length())));
                int i5 = 0;
                while (true) {
                    if (i5 >= arrayList.size()) {
                        z3 = false;
                        break;
                    }
                    String str6 = (String) arrayList.get(i5);
                    if (str6.startsWith(str2)) {
                        int parseInt = Integer.parseInt(str6.split(Config.Compare.EQUAL_TO)[1]);
                        if (parseInt < i && !z2) {
                            i = parseInt;
                        }
                        arrayList.set(i5, String.format(locale, "%s=%d", str2, Integer.valueOf(i)));
                        z3 = true;
                    } else {
                        i5++;
                    }
                }
                String str7 = z3 ? sb2 + " " + Joiner.on(";").join(arrayList) : str5 + String.format(locale, ";%s=%d", str2, Integer.valueOf(i));
                this.lines.set(i4, str7);
                log.d("Updated SDP line: " + str7);
                return this;
            }
        }
        String format = String.format(locale, "a=fmtp:%s %s=%d", str3, str2, Integer.valueOf(i));
        log.d("Add SDP line: " + format);
        this.lines.add(i2 + 1, format);
        return this;
    }

    public synchronized void setDataChannelMapParameters(List<DataChannelProperties> list) {
        if (list == null) {
            log.w("activeDataChannels is null");
            return;
        }
        Iterator<DataChannelProperties> it2 = list.iterator();
        int findFirstAttribute = findFirstAttribute("application");
        if (findFirstAttribute != -1) {
            int i = 1;
            while (it2.hasNext()) {
                this.lines.add(findFirstAttribute + i, "a=dcmap:" + buildDataChannelMapParameters(it2.next()));
                i++;
            }
        } else {
            log.w("Media type application not found");
        }
    }

    public synchronized void setEndOfCandidates() {
        List<Integer> allMediaDescriptionLocations = getAllMediaDescriptionLocations();
        if (allMediaDescriptionLocations.size() <= 0) {
            log.w("addIceCandidate: adding candidate attribute failed.");
            return;
        }
        for (int size = allMediaDescriptionLocations.size() - 1; size >= 0; size--) {
            int intValue = allMediaDescriptionLocations.get(size).intValue() + 1;
            if (findAttribute(intValue, ATTRIBUTE_CANDIDATE) != -1 && !addLastAttribute(intValue, ATTRIBUTE_END_OF_CANDIDATES, null)) {
                log.e("setEndOfCandidates: adding end-of-candidate attribute failed.");
            }
        }
    }

    public synchronized void setMediaCapability(String str, String str2) {
        setAttribute(str, CUSTOM_MEDIA_CAPABILITY, str2);
    }

    public synchronized void setScreenOrientation(Size size, int i) {
        if (size.getWidth() != 0 && size.getHeight() != 0 && i != -1) {
            setAttribute("video", SCREEN_ORIENTATION, buildScreenOrientation(size, i));
        }
    }

    public synchronized void setScreenParameters(Size size, String str) {
        if (size.getWidth() != 0 && size.getHeight() != 0) {
            setAttribute("video", SCREEN_ATTRIBUTE, buildScreenParameters(size, str));
        }
    }

    public synchronized void setVideoConstraintParameters(VideoConstraints videoConstraints) {
        setAttribute("video", CUSTOM_SCALING, buildVideoScalingValue(videoConstraints));
    }

    public synchronized Sdp setVideoSuspendedFlags(boolean z, MediaStateChangeTrigger mediaStateChangeTrigger) {
        CommsLogger commsLogger = log;
        commsLogger.i("setVideoSuspended: suspended=" + z + ", trigger=" + mediaStateChangeTrigger.name());
        if (z) {
            forceMediaDirectionInactive("video");
        }
        TreeMap treeMap = new TreeMap();
        treeMap.put(SUSPEND_LOCAL, z ? "1" : DISABLED_MEDIA_PORT);
        treeMap.put(SUSPEND_TRIGGER, mediaStateChangeTrigger.name());
        setChannelControlParams("video", treeMap);
        return this;
    }

    public synchronized int stripAndReturnBitrateFromVideoCodecFmtp(String str, String str2, int i) {
        String str3;
        boolean z;
        CodecRtpMapLocation findCodecRtpMap = findCodecRtpMap(str);
        if (findCodecRtpMap != null && (str3 = findCodecRtpMap.codecRtpMapNumber) != null && findCodecRtpMap.lineLoc != -1) {
            String str4 = "a=fmtp:" + str3;
            int findCodecFmtpLocation = findCodecFmtpLocation(str3, str4);
            if (findCodecFmtpLocation == -1) {
                log.w("No fmtp found for " + str + " codec");
                return i;
            }
            String str5 = this.lines.get(findCodecFmtpLocation);
            ArrayList arrayList = new ArrayList(Splitter.on(";").trimResults().splitToList(str5.substring(str4.length())));
            boolean z2 = false;
            int i2 = 0;
            while (true) {
                if (i2 >= arrayList.size()) {
                    z = false;
                    break;
                }
                String str6 = (String) arrayList.get(i2);
                if (str6.startsWith(str2)) {
                    if (arrayList.size() == 1) {
                        z = false;
                        z2 = true;
                    } else {
                        z = true;
                    }
                    i = Integer.parseInt(str6.split(Config.Compare.EQUAL_TO)[1]);
                } else {
                    i2++;
                }
            }
            if (z2) {
                log.i("Removing SDP line: " + str5);
                this.lines.remove(findCodecFmtpLocation);
            } else if (z) {
                arrayList.remove(i2);
                String str7 = str4 + " " + Joiner.on(";").join(arrayList);
                log.i("Modified SDP line: " + str7);
                this.lines.set(findCodecFmtpLocation, str7);
            }
            return i;
        }
        log.w("No rtpmap for " + str + " codec");
        return i;
    }

    public synchronized Sdp stripIceCandidates() {
        ArrayList<String> arrayList = new ArrayList<>(this.lines.size());
        Iterator<String> it2 = this.lines.iterator();
        while (it2.hasNext()) {
            String next = it2.next();
            if (!next.startsWith("a=candidate")) {
                arrayList.add(next);
            }
        }
        this.lines = arrayList;
        return this;
    }

    public synchronized String toString() {
        return Joiner.on("\r\n").join(this.lines);
    }

    protected Iterable<String> getMediaDescriptions(final boolean z) {
        return Iterables.filter(this.lines, new Predicate<String>() { // from class: com.amazon.comms.ringservice.Sdp.3
            @Override // com.google.common.base.Predicate
            public boolean apply(@Nullable String str) {
                if (Sdp.this.isMediaDescription(str)) {
                    Sdp sdp = Sdp.this;
                    return !sdp.isDeleted(sdp.splitMediaDescription(str)) || z;
                }
                return false;
            }
        });
    }

    protected boolean isMediaPresent(String str, boolean z) {
        String outline72 = GeneratedOutlineSupport1.outline72("m=", str);
        for (String str2 : getMediaDescriptions(z)) {
            if (str2.startsWith(outline72)) {
                return true;
            }
        }
        return false;
    }

    private void setAttribute(int i, String str, String str2) {
        int findAttribute = findAttribute(i, str);
        String outline72 = GeneratedOutlineSupport1.outline72("a=", str);
        if (str2 != null) {
            outline72 = GeneratedOutlineSupport1.outline75(outline72, ":", str2);
        }
        if (findAttribute == -1) {
            this.lines.add(i + 1, outline72);
        } else {
            this.lines.set(findAttribute, outline72);
        }
    }

    private int findMediaSection(int i) {
        int i2 = -1;
        for (int i3 = 0; i3 < this.lines.size(); i3++) {
            if (this.lines.get(i3).startsWith("m=") && (i2 = i2 + 1) == i) {
                return i3;
            }
        }
        return -1;
    }

    private boolean addLastAttribute(int i, String str, String str2) {
        if (str2 != null) {
            str = GeneratedOutlineSupport1.outline75(str, ":", str2);
        }
        return addLastAttribute(i, str);
    }

    @VisibleForTesting
    int findFirstAttribute(String str) {
        return findFirstAttribute(findMediaSection(str));
    }

    @VisibleForTesting
    int findAttribute(String str, String str2) {
        int findMediaSection = findMediaSection(str);
        if (findMediaSection < 0) {
            return -1;
        }
        return findAttribute(findMediaSection + 1, str2);
    }
}
