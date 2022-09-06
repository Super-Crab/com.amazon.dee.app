package com.amazon.alexa.accessory;

import com.amazon.alexa.accessory.io.SizedSource;
/* loaded from: classes.dex */
public interface AccessoryStream {
    public static final int STREAM_ALEXA_API = 6;
    public static final int STREAM_BULKDATA = 5;
    public static final int STREAM_CONTROL = 0;
    public static final int STREAM_DIAGNOSTICS = 3;
    public static final int STREAM_FIRMWARE = 2;
    public static final int STREAM_FITNESS = 4;
    public static final int STREAM_SIDEWALK = 7;
    public static final int STREAM_VOICE = 1;

    int getId();

    boolean handleData(SizedSource sizedSource) throws Exception;
}
