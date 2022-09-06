package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class CaptureMode {
    public static final String BURST = "BURST";
    public static final String HDR = "HDR";
    public static final String LENTICULAR = "LENTICULAR";
    public static final String NONE = "NONE";
    public static final String PANORAMA = "PANORAMA";
    public static final String REWIND = "REWIND";
    public static final String VIDSNAP = "VIDSNAP";
    private static final String[] values = {"NONE", "REWIND", "HDR", "BURST", "PANORAMA", "LENTICULAR", "VIDSNAP"};

    private CaptureMode() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
