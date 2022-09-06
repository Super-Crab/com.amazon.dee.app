package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.http;

import android.util.Log;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.devicesetupservice.smarthome.ProtocolType;
import com.amazonaws.Request;
import com.amazonaws.Response;
import com.amazonaws.handlers.RequestHandler2;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.AnalyticsEvent;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.Connectivity;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.util.DateUtil;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.util.StringUtil;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.InternalEventClient;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;
@Deprecated
/* loaded from: classes13.dex */
public class RequestTimingHandler extends RequestHandler2 {
    public static final String REQUESTATTEMPTS_HEADER = "x-amzn-RequestAttempts";
    public static final String REQUESTTIME_HEADER = "x-amzn-RequestTime";
    public static final String SERVERINFO_HEADER = "x-amzn-ServerInfo";
    private static final String TAG = "RequestTimingHandler";
    private static final String TIMEZONE_FORMAT = "Z";
    private final Connectivity connectivity;
    private int contentLength;
    private DateFormat df = DateUtil.createLocaleIndependentDateFormatter(TIMEZONE_FORMAT);
    private final InternalEventClient eventClient;
    private long startTime;

    public RequestTimingHandler(Connectivity connectivity, InternalEventClient internalEventClient) {
        this.eventClient = internalEventClient;
        this.connectivity = connectivity;
    }

    private synchronized String getTimeZone() {
        return this.df.format(new Date());
    }

    @Override // com.amazonaws.handlers.RequestHandler2
    public void afterError(Request<?> request, Response<?> response, Exception exc) {
    }

    @Override // com.amazonaws.handlers.RequestHandler2
    public void afterResponse(Request<?> request, Response<?> response) {
        if (response == null) {
            return;
        }
        try {
            Map<String, String> headers = response.getHttpResponse().getHeaders();
            String str = headers.get(REQUESTTIME_HEADER);
            long currentTimeMillis = this.startTime - System.currentTimeMillis();
            if (str != null && str.trim().length() > 0) {
                try {
                    currentTimeMillis = Long.parseLong(str.trim());
                } catch (NumberFormatException unused) {
                }
            }
            double d = -1.0d;
            try {
                d = Double.valueOf(Long.toString(currentTimeMillis)).doubleValue();
            } catch (NumberFormatException e) {
                Log.e(TAG, "Couldn't convert response time to double format", e);
            }
            String str2 = headers.get(REQUESTATTEMPTS_HEADER);
            double d2 = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
            if (str2 != null && str2.trim().length() > 0) {
                try {
                    d2 = Double.parseDouble(str2.trim());
                } catch (NumberFormatException unused2) {
                }
            }
            String str3 = headers.get(SERVERINFO_HEADER);
            if (StringUtil.isNullOrEmpty(str) || this.eventClient == null) {
                return;
            }
            AnalyticsEvent mo6693withMetric = this.eventClient.createEvent("_httpRequestTiming").mo6692withAttribute("url", request.getEndpoint().toURL().toString()).mo6692withAttribute("responseCode", Integer.toString(response.getHttpResponse().getStatusCode())).mo6692withAttribute("timeZone", getTimeZone()).mo6693withMetric("attempts", Double.valueOf(d2)).mo6693withMetric("totalTime", Double.valueOf(d)).mo6693withMetric("requestSize", Double.valueOf(this.contentLength));
            String str4 = "UNKNOWN";
            if (this.connectivity != null) {
                if (this.connectivity.hasWifi()) {
                    str4 = ProtocolType.WIFI;
                } else if (this.connectivity.hasWAN()) {
                    str4 = "WAN";
                }
            }
            mo6693withMetric.mo6692withAttribute("network", str4);
            if (str3 != null) {
                mo6693withMetric.mo6692withAttribute("serverInfo", str3);
            }
            this.eventClient.recordEvent(mo6693withMetric);
        } catch (Exception e2) {
            Log.e(TAG, "Unable to record _RequestTime event", e2);
        }
    }

    @Override // com.amazonaws.handlers.RequestHandler2
    public void beforeRequest(Request<?> request) {
        this.startTime = System.currentTimeMillis();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        InputStream content = request.getContent();
        while (true) {
            try {
                int read = content.read();
                if (read != -1) {
                    byteArrayOutputStream.write(read);
                } else {
                    this.contentLength = byteArrayOutputStream.size();
                    request.setContent(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
                    return;
                }
            } catch (IOException e) {
                Log.e(TAG, "Cannot read content of request");
                throw new RuntimeException(e);
            }
        }
    }
}
