package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class Transforms {
    public static final String DOCUMENT_CONVERSION = "DOCUMENT_CONVERSION";
    public static final String IMAGE_ANIMATION = "IMAGE_ANIMATION";
    public static final String IMAGE_THUMBNAIL = "IMAGE_THUMBNAIL";
    public static final String PDF = "PDF";
    public static final String PHOTO = "PHOTO";
    public static final String VIDEO = "VIDEO";
    public static final String VIDEO_TRANSCODE = "VIDEO_TRANSCODE";
    private static final String[] values = {"VIDEO_TRANSCODE", "DOCUMENT_CONVERSION", "IMAGE_THUMBNAIL", "IMAGE_ANIMATION", "PHOTO", "VIDEO", "PDF"};

    private Transforms() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
