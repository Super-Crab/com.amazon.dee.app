package com.amazon.imageutilities;
/* loaded from: classes12.dex */
public final class JpegSegmentMarker {
    static final byte APPN_MARKER_SECOND_BYTE_LOWER_BOUND = -32;
    static final byte APPN_MARKER_SECOND_BYTE_UPPER_BOUND = -17;
    static final byte COM_MARKER_SECOND_BYTE = -2;
    static final byte DQT_MARKER_SECOND_BYTE = -37;
    static final byte DRI_MARKER_SECOND_BYTE = -35;
    static final int LENGTH = 2;
    static final byte MARKER_FIRST_BYTE = -1;
    static final byte SOFN_MARKER_SECOND_BYTE_LOWER_BOUND = -64;
    static final byte SOFN_MARKER_SECOND_BYTE_UPPER_BOUND = -49;
    static final byte[] SOI = {-1, -40};
    static final byte[] EOI = {-1, -39};

    private JpegSegmentMarker() {
    }
}
