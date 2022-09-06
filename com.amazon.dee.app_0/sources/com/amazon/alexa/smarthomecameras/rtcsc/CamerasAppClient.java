package com.amazon.alexa.smarthomecameras.rtcsc;

import android.content.Context;
import android.util.Log;
import com.amazon.rtcsc.appclient.RtcscAppClient;
/* loaded from: classes10.dex */
public class CamerasAppClient extends RtcscAppClient {
    private static final String TAG = "CamerasAppClient";

    public CamerasAppClient(Context context) {
        super(context);
    }

    @Override // com.amazon.rtcsc.appclient.RtcscAppClient
    protected void onRtcscServiceConnected() {
        Log.i(TAG, "onRtcscServiceConnected received.");
    }

    @Override // com.amazon.rtcsc.appclient.RtcscAppClient
    protected void onRtcscServiceDisconnected() {
        Log.i(TAG, "onRtcscServiceDisconnected received.");
    }
}
