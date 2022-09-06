package com.amazon.alexa.logupload;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.comms.calling.sipclient.SipHeaders;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.UUID;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
/* loaded from: classes9.dex */
public class LogReportDET implements LogReport {
    private static final String DEFAULT_DEVICE_SERIAL_NUMBER = "DSN";
    private static final String DEFAULT_DEVICE_TYPE = "A2TF17PFR55MTB";
    private static final String DEVICE_EVENT_TRACKER_URL = "https://det-ta-g7g.amazon.com/DETLogServlet";
    private static final String TAG = "LogReportDET";
    private final String dtsSessionId;
    private final String reportContent;
    private final DeviceInformation deviceInformation = (DeviceInformation) GeneratedOutlineSupport1.outline20(DeviceInformation.class);
    private final EnvironmentService environmentService = (EnvironmentService) GeneratedOutlineSupport1.outline20(EnvironmentService.class);

    public LogReportDET(String str, String str2) {
        this.dtsSessionId = str;
        this.reportContent = str2;
    }

    private RequestBody createBody() throws IOException {
        byte[] decode = Base64.decode(this.reportContent, 2);
        byte[] bytes = (String.format("MFBS/1.0 1%n", new Object[0]) + String.format("Content-Encoding: %s%n", "zip") + String.format(Locale.US, "Content-Length: %d%n%n", Integer.valueOf(decode.length))).getBytes(StandardCharsets.UTF_8);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(bytes);
        byteArrayOutputStream.write(decode);
        return RequestBody.create(MediaType.parse("application/zip"), byteArrayOutputStream.toByteArray());
    }

    private String getDeviceSerialNumber() {
        String str;
        try {
            str = this.deviceInformation.getDeviceSerialNumber();
        } catch (Exception e) {
            Log.e(TAG, "Unable to get device serial number", e);
            str = null;
        }
        return TextUtils.isEmpty(str) ? DEFAULT_DEVICE_SERIAL_NUMBER : str;
    }

    private String getDeviceType() {
        String str;
        try {
            str = this.deviceInformation.getDeviceType();
        } catch (Exception e) {
            Log.e(TAG, "Unable to get device type", e);
            str = null;
        }
        return TextUtils.isEmpty(str) ? "A2TF17PFR55MTB" : str;
    }

    public String getDtsSessionId() {
        return this.dtsSessionId;
    }

    @Override // com.amazon.alexa.logupload.LogReport
    public Request getNetworkRequest() throws LogUploadException {
        try {
            return new Request.Builder().url(HttpUrl.parse(DEVICE_EVENT_TRACKER_URL).newBuilder().addQueryParameter("key", UUID.randomUUID().toString()).build()).header("X-DSN", getDeviceSerialNumber()).header(SipHeaders.SIP_HEADER_DEVICE_TYPE, getDeviceType()).header("X-DeviceFirmwareVersion", this.environmentService.getVersionName()).header("X-Content-Type", "NoOpParser").header("X-Upload-Tag", this.dtsSessionId).header("Content-Type", "Application/Raw").post(createBody()).build();
        } catch (Exception e) {
            throw new LogUploadException("Unable to get network request", e);
        }
    }

    public String getReportContent() {
        return this.reportContent;
    }
}
