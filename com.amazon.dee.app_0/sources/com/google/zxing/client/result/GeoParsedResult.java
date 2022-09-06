package com.google.zxing.client.result;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes3.dex */
public final class GeoParsedResult extends ParsedResult {
    private final double altitude;
    private final double latitude;
    private final double longitude;
    private final String query;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GeoParsedResult(double d, double d2, double d3, String str) {
        super(ParsedResultType.GEO);
        this.latitude = d;
        this.longitude = d2;
        this.altitude = d3;
        this.query = str;
    }

    public double getAltitude() {
        return this.altitude;
    }

    @Override // com.google.zxing.client.result.ParsedResult
    public String getDisplayResult() {
        StringBuilder sb = new StringBuilder(20);
        sb.append(this.latitude);
        sb.append(", ");
        sb.append(this.longitude);
        if (this.altitude > FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
            sb.append(", ");
            sb.append(this.altitude);
            sb.append('m');
        }
        if (this.query != null) {
            sb.append(" (");
            sb.append(this.query);
            sb.append(')');
        }
        return sb.toString();
    }

    public String getGeoURI() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("geo:");
        outline107.append(this.latitude);
        outline107.append(JsonReaderKt.COMMA);
        outline107.append(this.longitude);
        if (this.altitude > FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
            outline107.append(JsonReaderKt.COMMA);
            outline107.append(this.altitude);
        }
        if (this.query != null) {
            outline107.append(Constants.DEFAULT_IMAGE_CHAR);
            outline107.append(this.query);
        }
        return outline107.toString();
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public String getQuery() {
        return this.query;
    }
}
