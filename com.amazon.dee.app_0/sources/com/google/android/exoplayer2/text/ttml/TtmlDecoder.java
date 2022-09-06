package com.google.android.exoplayer2.text.ttml;

import android.text.Layout;
import androidx.annotation.Nullable;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttTopic;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ColorParser;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.util.XmlPullParserUtil;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
/* loaded from: classes2.dex */
public final class TtmlDecoder extends SimpleSubtitleDecoder {
    private static final String ATTR_BEGIN = "begin";
    private static final String ATTR_DURATION = "dur";
    private static final String ATTR_END = "end";
    private static final String ATTR_IMAGE = "backgroundImage";
    private static final String ATTR_REGION = "region";
    private static final String ATTR_STYLE = "style";
    private static final int DEFAULT_FRAME_RATE = 30;
    private static final String TAG = "TtmlDecoder";
    private static final String TTP = "http://www.w3.org/ns/ttml#parameter";
    private final XmlPullParserFactory xmlParserFactory;
    private static final Pattern CLOCK_TIME = Pattern.compile("^([0-9][0-9]+):([0-9][0-9]):([0-9][0-9])(?:(\\.[0-9]+)|:([0-9][0-9])(?:\\.([0-9]+))?)?$");
    private static final Pattern OFFSET_TIME = Pattern.compile("^([0-9]+(?:\\.[0-9]+)?)(h|m|s|ms|f|t)$");
    private static final Pattern FONT_SIZE = Pattern.compile("^(([0-9]*.)?[0-9]+)(px|em|%)$");
    static final Pattern SIGNED_PERCENTAGE = Pattern.compile("^([-+]?\\d+\\.?\\d*?)%$");
    static final Pattern PERCENTAGE_COORDINATES = Pattern.compile("^(\\d+\\.?\\d*?)% (\\d+\\.?\\d*?)%$");
    private static final Pattern PIXEL_COORDINATES = Pattern.compile("^(\\d+\\.?\\d*?)px (\\d+\\.?\\d*?)px$");
    private static final Pattern CELL_RESOLUTION = Pattern.compile("^(\\d+) (\\d+)$");
    private static final FrameAndTickRate DEFAULT_FRAME_AND_TICK_RATE = new FrameAndTickRate(30.0f, 1, 1);
    private static final CellResolution DEFAULT_CELL_RESOLUTION = new CellResolution(32, 15);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class CellResolution {
        final int columns;
        final int rows;

        CellResolution(int i, int i2) {
            this.columns = i;
            this.rows = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class FrameAndTickRate {
        final float effectiveFrameRate;
        final int subFrameRate;
        final int tickRate;

        FrameAndTickRate(float f, int i, int i2) {
            this.effectiveFrameRate = f;
            this.subFrameRate = i;
            this.tickRate = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class TtsExtent {
        final int height;
        final int width;

        TtsExtent(int i, int i2) {
            this.width = i;
            this.height = i2;
        }
    }

    public TtmlDecoder() {
        super(TAG);
        try {
            this.xmlParserFactory = XmlPullParserFactory.newInstance();
            this.xmlParserFactory.setNamespaceAware(true);
        } catch (XmlPullParserException e) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e);
        }
    }

    private static TtmlStyle createIfNull(@Nullable TtmlStyle ttmlStyle) {
        return ttmlStyle == null ? new TtmlStyle() : ttmlStyle;
    }

    private static boolean isSupportedTag(String str) {
        return str.equals(TtmlNode.TAG_TT) || str.equals(TtmlNode.TAG_HEAD) || str.equals("body") || str.equals(TtmlNode.TAG_DIV) || str.equals("p") || str.equals(TtmlNode.TAG_SPAN) || str.equals(TtmlNode.TAG_BR) || str.equals("style") || str.equals(TtmlNode.TAG_STYLING) || str.equals(TtmlNode.TAG_LAYOUT) || str.equals("region") || str.equals("metadata") || str.equals("image") || str.equals("data") || str.equals(TtmlNode.TAG_INFORMATION);
    }

    private static CellResolution parseCellResolution(XmlPullParser xmlPullParser, CellResolution cellResolution) throws SubtitleDecoderException {
        String attributeValue = xmlPullParser.getAttributeValue(TTP, "cellResolution");
        if (attributeValue == null) {
            return cellResolution;
        }
        Matcher matcher = CELL_RESOLUTION.matcher(attributeValue);
        if (!matcher.matches()) {
            GeneratedOutlineSupport1.outline168("Ignoring malformed cell resolution: ", attributeValue, TAG);
            return cellResolution;
        }
        try {
            int parseInt = Integer.parseInt((String) Assertions.checkNotNull(matcher.group(1)));
            int parseInt2 = Integer.parseInt((String) Assertions.checkNotNull(matcher.group(2)));
            if (parseInt != 0 && parseInt2 != 0) {
                return new CellResolution(parseInt, parseInt2);
            }
            throw new SubtitleDecoderException("Invalid cell resolution " + parseInt + " " + parseInt2);
        } catch (NumberFormatException unused) {
            GeneratedOutlineSupport1.outline168("Ignoring malformed cell resolution: ", attributeValue, TAG);
            return cellResolution;
        }
    }

    private static void parseFontSize(String str, TtmlStyle ttmlStyle) throws SubtitleDecoderException {
        Matcher matcher;
        String[] split = Util.split(str, "\\s+");
        if (split.length == 1) {
            matcher = FONT_SIZE.matcher(str);
        } else if (split.length == 2) {
            matcher = FONT_SIZE.matcher(split[1]);
            Log.w(TAG, "Multiple values in fontSize attribute. Picking the second value for vertical font size and ignoring the first.");
        } else {
            throw new SubtitleDecoderException(GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline107("Invalid number of entries for fontSize: "), split.length, "."));
        }
        if (matcher.matches()) {
            String str2 = (String) Assertions.checkNotNull(matcher.group(3));
            char c = 65535;
            int hashCode = str2.hashCode();
            if (hashCode != 37) {
                if (hashCode != 3240) {
                    if (hashCode == 3592 && str2.equals("px")) {
                        c = 0;
                    }
                } else if (str2.equals("em")) {
                    c = 1;
                }
            } else if (str2.equals("%")) {
                c = 2;
            }
            if (c == 0) {
                ttmlStyle.setFontSizeUnit(1);
            } else if (c == 1) {
                ttmlStyle.setFontSizeUnit(2);
            } else if (c == 2) {
                ttmlStyle.setFontSizeUnit(3);
            } else {
                throw new SubtitleDecoderException(GeneratedOutlineSupport1.outline75("Invalid unit for fontSize: '", str2, "'."));
            }
            ttmlStyle.setFontSize(Float.parseFloat((String) Assertions.checkNotNull(matcher.group(1))));
            return;
        }
        throw new SubtitleDecoderException(GeneratedOutlineSupport1.outline75("Invalid expression for fontSize: '", str, "'."));
    }

    private static FrameAndTickRate parseFrameAndTickRates(XmlPullParser xmlPullParser) throws SubtitleDecoderException {
        String attributeValue = xmlPullParser.getAttributeValue(TTP, "frameRate");
        int parseInt = attributeValue != null ? Integer.parseInt(attributeValue) : 30;
        float f = 1.0f;
        String attributeValue2 = xmlPullParser.getAttributeValue(TTP, "frameRateMultiplier");
        if (attributeValue2 != null) {
            String[] split = Util.split(attributeValue2, " ");
            if (split.length == 2) {
                f = Integer.parseInt(split[0]) / Integer.parseInt(split[1]);
            } else {
                throw new SubtitleDecoderException("frameRateMultiplier doesn't have 2 parts");
            }
        }
        int i = DEFAULT_FRAME_AND_TICK_RATE.subFrameRate;
        String attributeValue3 = xmlPullParser.getAttributeValue(TTP, "subFrameRate");
        if (attributeValue3 != null) {
            i = Integer.parseInt(attributeValue3);
        }
        int i2 = DEFAULT_FRAME_AND_TICK_RATE.tickRate;
        String attributeValue4 = xmlPullParser.getAttributeValue(TTP, "tickRate");
        if (attributeValue4 != null) {
            i2 = Integer.parseInt(attributeValue4);
        }
        return new FrameAndTickRate(parseInt * f, i, i2);
    }

    private static Map<String, TtmlStyle> parseHeader(XmlPullParser xmlPullParser, Map<String, TtmlStyle> map, CellResolution cellResolution, @Nullable TtsExtent ttsExtent, Map<String, TtmlRegion> map2, Map<String, String> map3) throws IOException, XmlPullParserException {
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "style")) {
                String attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser, "style");
                TtmlStyle parseStyleAttributes = parseStyleAttributes(xmlPullParser, new TtmlStyle());
                if (attributeValue != null) {
                    for (String str : parseStyleIds(attributeValue)) {
                        parseStyleAttributes.chain(map.get(str));
                    }
                }
                String id = parseStyleAttributes.getId();
                if (id != null) {
                    map.put(id, parseStyleAttributes);
                }
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "region")) {
                TtmlRegion parseRegionAttributes = parseRegionAttributes(xmlPullParser, cellResolution, ttsExtent);
                if (parseRegionAttributes != null) {
                    map2.put(parseRegionAttributes.id, parseRegionAttributes);
                }
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "metadata")) {
                parseMetadata(xmlPullParser, map3);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, TtmlNode.TAG_HEAD));
        return map;
    }

    private static void parseMetadata(XmlPullParser xmlPullParser, Map<String, String> map) throws IOException, XmlPullParserException {
        String attributeValue;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "image") && (attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser, "id")) != null) {
                map.put(attributeValue, xmlPullParser.nextText());
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, "metadata"));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static TtmlNode parseNode(XmlPullParser xmlPullParser, @Nullable TtmlNode ttmlNode, Map<String, TtmlRegion> map, FrameAndTickRate frameAndTickRate) throws SubtitleDecoderException {
        long j;
        long j2;
        char c;
        int attributeCount = xmlPullParser.getAttributeCount();
        TtmlStyle parseStyleAttributes = parseStyleAttributes(xmlPullParser, null);
        String str = "";
        String str2 = null;
        String[] strArr = null;
        long j3 = C.TIME_UNSET;
        long j4 = C.TIME_UNSET;
        long j5 = C.TIME_UNSET;
        for (int i = 0; i < attributeCount; i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            String attributeValue = xmlPullParser.getAttributeValue(i);
            switch (attributeName.hashCode()) {
                case -934795532:
                    if (attributeName.equals("region")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 99841:
                    if (attributeName.equals(ATTR_DURATION)) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 100571:
                    if (attributeName.equals("end")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 93616297:
                    if (attributeName.equals(ATTR_BEGIN)) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 109780401:
                    if (attributeName.equals("style")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 1292595405:
                    if (attributeName.equals(ATTR_IMAGE)) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            if (c == 0) {
                j3 = parseTimeExpression(attributeValue, frameAndTickRate);
            } else if (c == 1) {
                j4 = parseTimeExpression(attributeValue, frameAndTickRate);
            } else if (c == 2) {
                j5 = parseTimeExpression(attributeValue, frameAndTickRate);
            } else if (c == 3) {
                String[] parseStyleIds = parseStyleIds(attributeValue);
                if (parseStyleIds.length > 0) {
                    strArr = parseStyleIds;
                }
            } else if (c != 4) {
                if (c == 5 && attributeValue.startsWith(MqttTopic.MULTI_LEVEL_WILDCARD)) {
                    str2 = attributeValue.substring(1);
                }
            } else if (map.containsKey(attributeValue)) {
                str = attributeValue;
            }
        }
        if (ttmlNode != null) {
            long j6 = ttmlNode.startTimeUs;
            j = C.TIME_UNSET;
            if (j6 != C.TIME_UNSET) {
                if (j3 != C.TIME_UNSET) {
                    j3 += j6;
                }
                if (j4 != C.TIME_UNSET) {
                    j4 += ttmlNode.startTimeUs;
                }
            }
        } else {
            j = C.TIME_UNSET;
        }
        long j7 = j3;
        if (j4 == j) {
            if (j5 != j) {
                j2 = j5 + j7;
            } else if (ttmlNode != null) {
                long j8 = ttmlNode.endTimeUs;
                if (j8 != j) {
                    j2 = j8;
                }
            }
            return TtmlNode.buildNode(xmlPullParser.getName(), j7, j2, parseStyleAttributes, strArr, str, str2, ttmlNode);
        }
        j2 = j4;
        return TtmlNode.buildNode(xmlPullParser.getName(), j7, j2, parseStyleAttributes, strArr, str, str2, ttmlNode);
    }

    /* JADX WARN: Code restructure failed: missing block: B:75:0x0191, code lost:
        if (r2 != 2) goto L64;
     */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0156  */
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static com.google.android.exoplayer2.text.ttml.TtmlRegion parseRegionAttributes(org.xmlpull.v1.XmlPullParser r17, com.google.android.exoplayer2.text.ttml.TtmlDecoder.CellResolution r18, @androidx.annotation.Nullable com.google.android.exoplayer2.text.ttml.TtmlDecoder.TtsExtent r19) {
        /*
            Method dump skipped, instructions count: 451
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.ttml.TtmlDecoder.parseRegionAttributes(org.xmlpull.v1.XmlPullParser, com.google.android.exoplayer2.text.ttml.TtmlDecoder$CellResolution, com.google.android.exoplayer2.text.ttml.TtmlDecoder$TtsExtent):com.google.android.exoplayer2.text.ttml.TtmlRegion");
    }

    private static float parseShear(String str) {
        Matcher matcher = SIGNED_PERCENTAGE.matcher(str);
        if (!matcher.matches()) {
            GeneratedOutlineSupport1.outline168("Invalid value for shear: ", str, TAG);
            return Float.MAX_VALUE;
        }
        try {
            return Math.min(100.0f, Math.max(-100.0f, Float.parseFloat((String) Assertions.checkNotNull(matcher.group(1)))));
        } catch (NumberFormatException e) {
            Log.w(TAG, "Failed to parse shear: " + str, e);
            return Float.MAX_VALUE;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static TtmlStyle parseStyleAttributes(XmlPullParser xmlPullParser, TtmlStyle ttmlStyle) {
        char c;
        int attributeCount = xmlPullParser.getAttributeCount();
        TtmlStyle ttmlStyle2 = ttmlStyle;
        for (int i = 0; i < attributeCount; i++) {
            String attributeValue = xmlPullParser.getAttributeValue(i);
            String attributeName = xmlPullParser.getAttributeName(i);
            char c2 = 65535;
            switch (attributeName.hashCode()) {
                case -1550943582:
                    if (attributeName.equals("fontStyle")) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case -1224696685:
                    if (attributeName.equals("fontFamily")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case -1065511464:
                    if (attributeName.equals("textAlign")) {
                        c = 7;
                        break;
                    }
                    c = 65535;
                    break;
                case -879295043:
                    if (attributeName.equals(TtmlNode.ATTR_TTS_TEXT_DECORATION)) {
                        c = 11;
                        break;
                    }
                    c = 65535;
                    break;
                case -734428249:
                    if (attributeName.equals("fontWeight")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case 3355:
                    if (attributeName.equals("id")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 3511770:
                    if (attributeName.equals(TtmlNode.ATTR_TTS_RUBY)) {
                        c = '\t';
                        break;
                    }
                    c = 65535;
                    break;
                case 94842723:
                    if (attributeName.equals("color")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 109403361:
                    if (attributeName.equals(TtmlNode.ATTR_TTS_SHEAR)) {
                        c = '\r';
                        break;
                    }
                    c = 65535;
                    break;
                case 110138194:
                    if (attributeName.equals(TtmlNode.ATTR_TTS_TEXT_COMBINE)) {
                        c = '\b';
                        break;
                    }
                    c = 65535;
                    break;
                case 365601008:
                    if (attributeName.equals("fontSize")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 921125321:
                    if (attributeName.equals(TtmlNode.ATTR_TTS_TEXT_EMPHASIS)) {
                        c = '\f';
                        break;
                    }
                    c = 65535;
                    break;
                case 1115953443:
                    if (attributeName.equals(TtmlNode.ATTR_TTS_RUBY_POSITION)) {
                        c = '\n';
                        break;
                    }
                    c = 65535;
                    break;
                case 1287124693:
                    if (attributeName.equals("backgroundColor")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    if ("style".equals(xmlPullParser.getName())) {
                        ttmlStyle2 = createIfNull(ttmlStyle2).setId(attributeValue);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    ttmlStyle2 = createIfNull(ttmlStyle2);
                    try {
                        ttmlStyle2.setBackgroundColor(ColorParser.parseTtmlColor(attributeValue));
                        break;
                    } catch (IllegalArgumentException unused) {
                        GeneratedOutlineSupport1.outline168("Failed parsing background value: ", attributeValue, TAG);
                        break;
                    }
                case 2:
                    ttmlStyle2 = createIfNull(ttmlStyle2);
                    try {
                        ttmlStyle2.setFontColor(ColorParser.parseTtmlColor(attributeValue));
                        break;
                    } catch (IllegalArgumentException unused2) {
                        GeneratedOutlineSupport1.outline168("Failed parsing color value: ", attributeValue, TAG);
                        break;
                    }
                case 3:
                    ttmlStyle2 = createIfNull(ttmlStyle2).setFontFamily(attributeValue);
                    break;
                case 4:
                    try {
                        ttmlStyle2 = createIfNull(ttmlStyle2);
                        parseFontSize(attributeValue, ttmlStyle2);
                        break;
                    } catch (SubtitleDecoderException unused3) {
                        GeneratedOutlineSupport1.outline168("Failed parsing fontSize value: ", attributeValue, TAG);
                        break;
                    }
                case 5:
                    ttmlStyle2 = createIfNull(ttmlStyle2).setBold(TtmlNode.BOLD.equalsIgnoreCase(attributeValue));
                    break;
                case 6:
                    ttmlStyle2 = createIfNull(ttmlStyle2).setItalic(TtmlNode.ITALIC.equalsIgnoreCase(attributeValue));
                    break;
                case 7:
                    String lowerInvariant = Util.toLowerInvariant(attributeValue);
                    switch (lowerInvariant.hashCode()) {
                        case -1364013995:
                            if (lowerInvariant.equals("center")) {
                                c2 = 4;
                                break;
                            }
                            break;
                        case 100571:
                            if (lowerInvariant.equals("end")) {
                                c2 = 3;
                                break;
                            }
                            break;
                        case 3317767:
                            if (lowerInvariant.equals("left")) {
                                c2 = 0;
                                break;
                            }
                            break;
                        case 108511772:
                            if (lowerInvariant.equals("right")) {
                                c2 = 2;
                                break;
                            }
                            break;
                        case 109757538:
                            if (lowerInvariant.equals("start")) {
                                c2 = 1;
                                break;
                            }
                            break;
                    }
                    if (c2 != 0 && c2 != 1) {
                        if (c2 != 2 && c2 != 3) {
                            if (c2 != 4) {
                                break;
                            } else {
                                ttmlStyle2 = createIfNull(ttmlStyle2).setTextAlign(Layout.Alignment.ALIGN_CENTER);
                                break;
                            }
                        } else {
                            ttmlStyle2 = createIfNull(ttmlStyle2).setTextAlign(Layout.Alignment.ALIGN_OPPOSITE);
                            break;
                        }
                    } else {
                        ttmlStyle2 = createIfNull(ttmlStyle2).setTextAlign(Layout.Alignment.ALIGN_NORMAL);
                        break;
                    }
                case '\b':
                    String lowerInvariant2 = Util.toLowerInvariant(attributeValue);
                    int hashCode = lowerInvariant2.hashCode();
                    if (hashCode != 96673) {
                        if (hashCode == 3387192 && lowerInvariant2.equals("none")) {
                            c2 = 0;
                        }
                    } else if (lowerInvariant2.equals("all")) {
                        c2 = 1;
                    }
                    if (c2 != 0) {
                        if (c2 != 1) {
                            break;
                        } else {
                            ttmlStyle2 = createIfNull(ttmlStyle2).setTextCombine(true);
                            break;
                        }
                    } else {
                        ttmlStyle2 = createIfNull(ttmlStyle2).setTextCombine(false);
                        break;
                    }
                case '\t':
                    String lowerInvariant3 = Util.toLowerInvariant(attributeValue);
                    switch (lowerInvariant3.hashCode()) {
                        case -618561360:
                            if (lowerInvariant3.equals(TtmlNode.RUBY_BASE_CONTAINER)) {
                                c2 = 2;
                                break;
                            }
                            break;
                        case -410956671:
                            if (lowerInvariant3.equals(TtmlNode.RUBY_CONTAINER)) {
                                c2 = 0;
                                break;
                            }
                            break;
                        case -250518009:
                            if (lowerInvariant3.equals(TtmlNode.RUBY_DELIMITER)) {
                                c2 = 5;
                                break;
                            }
                            break;
                        case -136074796:
                            if (lowerInvariant3.equals(TtmlNode.RUBY_TEXT_CONTAINER)) {
                                c2 = 4;
                                break;
                            }
                            break;
                        case 3016401:
                            if (lowerInvariant3.equals(TtmlNode.RUBY_BASE)) {
                                c2 = 1;
                                break;
                            }
                            break;
                        case 3556653:
                            if (lowerInvariant3.equals("text")) {
                                c2 = 3;
                                break;
                            }
                            break;
                    }
                    if (c2 != 0) {
                        if (c2 != 1 && c2 != 2) {
                            if (c2 != 3 && c2 != 4) {
                                if (c2 != 5) {
                                    break;
                                } else {
                                    ttmlStyle2 = createIfNull(ttmlStyle2).setRubyType(4);
                                    break;
                                }
                            } else {
                                ttmlStyle2 = createIfNull(ttmlStyle2).setRubyType(3);
                                break;
                            }
                        } else {
                            ttmlStyle2 = createIfNull(ttmlStyle2).setRubyType(2);
                            break;
                        }
                    } else {
                        ttmlStyle2 = createIfNull(ttmlStyle2).setRubyType(1);
                        break;
                    }
                case '\n':
                    String lowerInvariant4 = Util.toLowerInvariant(attributeValue);
                    int hashCode2 = lowerInvariant4.hashCode();
                    if (hashCode2 != -1392885889) {
                        if (hashCode2 == 92734940 && lowerInvariant4.equals(TtmlNode.ANNOTATION_POSITION_AFTER)) {
                            c2 = 1;
                        }
                    } else if (lowerInvariant4.equals(TtmlNode.ANNOTATION_POSITION_BEFORE)) {
                        c2 = 0;
                    }
                    if (c2 != 0) {
                        if (c2 != 1) {
                            break;
                        } else {
                            ttmlStyle2 = createIfNull(ttmlStyle2).setRubyPosition(2);
                            break;
                        }
                    } else {
                        ttmlStyle2 = createIfNull(ttmlStyle2).setRubyPosition(1);
                        break;
                    }
                case 11:
                    String lowerInvariant5 = Util.toLowerInvariant(attributeValue);
                    switch (lowerInvariant5.hashCode()) {
                        case -1461280213:
                            if (lowerInvariant5.equals(TtmlNode.NO_UNDERLINE)) {
                                c2 = 3;
                                break;
                            }
                            break;
                        case -1026963764:
                            if (lowerInvariant5.equals(TtmlNode.UNDERLINE)) {
                                c2 = 2;
                                break;
                            }
                            break;
                        case 913457136:
                            if (lowerInvariant5.equals(TtmlNode.NO_LINETHROUGH)) {
                                c2 = 1;
                                break;
                            }
                            break;
                        case 1679736913:
                            if (lowerInvariant5.equals(TtmlNode.LINETHROUGH)) {
                                c2 = 0;
                                break;
                            }
                            break;
                    }
                    if (c2 != 0) {
                        if (c2 != 1) {
                            if (c2 != 2) {
                                if (c2 != 3) {
                                    break;
                                } else {
                                    ttmlStyle2 = createIfNull(ttmlStyle2).setUnderline(false);
                                    break;
                                }
                            } else {
                                ttmlStyle2 = createIfNull(ttmlStyle2).setUnderline(true);
                                break;
                            }
                        } else {
                            ttmlStyle2 = createIfNull(ttmlStyle2).setLinethrough(false);
                            break;
                        }
                    } else {
                        ttmlStyle2 = createIfNull(ttmlStyle2).setLinethrough(true);
                        break;
                    }
                case '\f':
                    ttmlStyle2 = createIfNull(ttmlStyle2).setTextEmphasis(TextEmphasis.parse(Util.toLowerInvariant(attributeValue)));
                    break;
                case '\r':
                    ttmlStyle2 = createIfNull(ttmlStyle2).setShearPercentage(parseShear(attributeValue));
                    break;
            }
        }
        return ttmlStyle2;
    }

    private static String[] parseStyleIds(String str) {
        String trim = str.trim();
        return trim.isEmpty() ? new String[0] : Util.split(trim, "\\s+");
    }

    private static long parseTimeExpression(String str, FrameAndTickRate frameAndTickRate) throws SubtitleDecoderException {
        boolean z;
        double d;
        double d2;
        String group;
        String group2;
        Matcher matcher = CLOCK_TIME.matcher(str);
        if (matcher.matches()) {
            double parseLong = (Long.parseLong((String) Assertions.checkNotNull(matcher.group(1))) * 3600) + (Long.parseLong((String) Assertions.checkNotNull(matcher.group(2))) * 60) + Long.parseLong((String) Assertions.checkNotNull(matcher.group(3)));
            String group3 = matcher.group(4);
            double d3 = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
            double parseDouble = parseLong + (group3 != null ? Double.parseDouble(group3) : 0.0d) + (matcher.group(5) != null ? ((float) Long.parseLong(group)) / frameAndTickRate.effectiveFrameRate : 0.0d);
            if (matcher.group(6) != null) {
                d3 = (Long.parseLong(group2) / frameAndTickRate.subFrameRate) / frameAndTickRate.effectiveFrameRate;
            }
            return (long) ((parseDouble + d3) * 1000000.0d);
        }
        Matcher matcher2 = OFFSET_TIME.matcher(str);
        if (matcher2.matches()) {
            double parseDouble2 = Double.parseDouble((String) Assertions.checkNotNull(matcher2.group(1)));
            String str2 = (String) Assertions.checkNotNull(matcher2.group(2));
            int hashCode = str2.hashCode();
            if (hashCode == 102) {
                if (str2.equals("f")) {
                    z = true;
                }
                z = true;
            } else if (hashCode == 104) {
                if (str2.equals("h")) {
                    z = false;
                }
                z = true;
            } else if (hashCode == 109) {
                if (str2.equals("m")) {
                    z = true;
                }
                z = true;
            } else if (hashCode == 3494) {
                if (str2.equals("ms")) {
                    z = true;
                }
                z = true;
            } else if (hashCode != 115) {
                if (hashCode == 116 && str2.equals("t")) {
                    z = true;
                }
                z = true;
            } else {
                if (str2.equals("s")) {
                    z = true;
                }
                z = true;
            }
            if (!z) {
                d = 3600.0d;
            } else if (!z) {
                if (!z) {
                    if (z) {
                        d2 = 1000.0d;
                    } else if (z) {
                        d2 = frameAndTickRate.effectiveFrameRate;
                    } else if (z) {
                        d2 = frameAndTickRate.tickRate;
                    }
                    parseDouble2 /= d2;
                }
                return (long) (parseDouble2 * 1000000.0d);
            } else {
                d = 60.0d;
            }
            parseDouble2 *= d;
            return (long) (parseDouble2 * 1000000.0d);
        }
        throw new SubtitleDecoderException(GeneratedOutlineSupport1.outline72("Malformed time expression: ", str));
    }

    @Nullable
    private static TtsExtent parseTtsExtent(XmlPullParser xmlPullParser) {
        String attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser, TtmlNode.ATTR_TTS_EXTENT);
        if (attributeValue == null) {
            return null;
        }
        Matcher matcher = PIXEL_COORDINATES.matcher(attributeValue);
        if (!matcher.matches()) {
            GeneratedOutlineSupport1.outline168("Ignoring non-pixel tts extent: ", attributeValue, TAG);
            return null;
        }
        try {
            return new TtsExtent(Integer.parseInt((String) Assertions.checkNotNull(matcher.group(1))), Integer.parseInt((String) Assertions.checkNotNull(matcher.group(2))));
        } catch (NumberFormatException unused) {
            GeneratedOutlineSupport1.outline168("Ignoring malformed tts extent: ", attributeValue, TAG);
            return null;
        }
    }

    @Override // com.google.android.exoplayer2.text.SimpleSubtitleDecoder
    protected Subtitle decode(byte[] bArr, int i, boolean z) throws SubtitleDecoderException {
        TtsExtent ttsExtent;
        FrameAndTickRate frameAndTickRate;
        CellResolution cellResolution;
        FrameAndTickRate frameAndTickRate2;
        try {
            XmlPullParser newPullParser = this.xmlParserFactory.newPullParser();
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            HashMap hashMap3 = new HashMap();
            hashMap2.put("", new TtmlRegion(""));
            TtsExtent ttsExtent2 = null;
            newPullParser.setInput(new ByteArrayInputStream(bArr, 0, i), null);
            ArrayDeque arrayDeque = new ArrayDeque();
            FrameAndTickRate frameAndTickRate3 = DEFAULT_FRAME_AND_TICK_RATE;
            CellResolution cellResolution2 = DEFAULT_CELL_RESOLUTION;
            int i2 = 0;
            TtmlSubtitle ttmlSubtitle = null;
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.getEventType()) {
                TtmlNode ttmlNode = (TtmlNode) arrayDeque.peek();
                if (i2 == 0) {
                    String name = newPullParser.getName();
                    if (eventType == 2) {
                        if (TtmlNode.TAG_TT.equals(name)) {
                            FrameAndTickRate parseFrameAndTickRates = parseFrameAndTickRates(newPullParser);
                            cellResolution = parseCellResolution(newPullParser, DEFAULT_CELL_RESOLUTION);
                            ttsExtent = parseTtsExtent(newPullParser);
                            frameAndTickRate2 = parseFrameAndTickRates;
                        } else {
                            ttsExtent = ttsExtent2;
                            frameAndTickRate2 = frameAndTickRate3;
                            cellResolution = cellResolution2;
                        }
                        if (!isSupportedTag(name)) {
                            Log.i(TAG, "Ignoring unsupported tag: " + newPullParser.getName());
                            i2++;
                            frameAndTickRate = frameAndTickRate2;
                        } else if (TtmlNode.TAG_HEAD.equals(name)) {
                            frameAndTickRate = frameAndTickRate2;
                            parseHeader(newPullParser, hashMap, cellResolution, ttsExtent, hashMap2, hashMap3);
                        } else {
                            frameAndTickRate = frameAndTickRate2;
                            try {
                                TtmlNode parseNode = parseNode(newPullParser, ttmlNode, hashMap2, frameAndTickRate);
                                arrayDeque.push(parseNode);
                                if (ttmlNode != null) {
                                    ttmlNode.addChild(parseNode);
                                }
                            } catch (SubtitleDecoderException e) {
                                Log.w(TAG, "Suppressing parser error", e);
                                i2++;
                            }
                        }
                    } else {
                        if (eventType == 4) {
                            ((TtmlNode) Assertions.checkNotNull(ttmlNode)).addChild(TtmlNode.buildTextNode(newPullParser.getText()));
                        } else if (eventType == 3) {
                            if (newPullParser.getName().equals(TtmlNode.TAG_TT)) {
                                ttmlSubtitle = new TtmlSubtitle((TtmlNode) Assertions.checkNotNull(arrayDeque.peek()), hashMap, hashMap2, hashMap3);
                            }
                            arrayDeque.pop();
                        }
                        ttsExtent = ttsExtent2;
                        frameAndTickRate = frameAndTickRate3;
                        cellResolution = cellResolution2;
                    }
                    frameAndTickRate3 = frameAndTickRate;
                    cellResolution2 = cellResolution;
                    ttsExtent2 = ttsExtent;
                } else if (eventType == 2) {
                    i2++;
                } else if (eventType == 3) {
                    i2--;
                }
                newPullParser.next();
            }
            if (ttmlSubtitle != null) {
                return ttmlSubtitle;
            }
            throw new SubtitleDecoderException("No TTML subtitles found");
        } catch (IOException e2) {
            throw new IllegalStateException("Unexpected error when reading input.", e2);
        } catch (XmlPullParserException e3) {
            throw new SubtitleDecoderException("Unable to decode source", e3);
        }
    }
}
