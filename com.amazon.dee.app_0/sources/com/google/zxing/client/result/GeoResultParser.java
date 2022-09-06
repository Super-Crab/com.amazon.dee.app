package com.google.zxing.client.result;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.google.zxing.Result;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes3.dex */
public final class GeoResultParser extends ResultParser {
    private static final Pattern GEO_URL_PATTERN = Pattern.compile("geo:([\\-0-9.]+),([\\-0-9.]+)(?:,([\\-0-9.]+))?(?:\\?(.*))?", 2);

    @Override // com.google.zxing.client.result.ResultParser
    /* renamed from: parse */
    public GeoParsedResult mo10122parse(Result result) {
        Matcher matcher = GEO_URL_PATTERN.matcher(ResultParser.getMassagedText(result));
        if (!matcher.matches()) {
            return null;
        }
        String group = matcher.group(4);
        try {
            double parseDouble = Double.parseDouble(matcher.group(1));
            if (parseDouble <= 90.0d && parseDouble >= -90.0d) {
                double parseDouble2 = Double.parseDouble(matcher.group(2));
                if (parseDouble2 <= 180.0d && parseDouble2 >= -180.0d) {
                    String group2 = matcher.group(3);
                    double d = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
                    if (group2 != null) {
                        double parseDouble3 = Double.parseDouble(matcher.group(3));
                        if (parseDouble3 < FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
                            return null;
                        }
                        d = parseDouble3;
                    }
                    return new GeoParsedResult(parseDouble, parseDouble2, d, group);
                }
            }
        } catch (NumberFormatException unused) {
        }
        return null;
    }
}
