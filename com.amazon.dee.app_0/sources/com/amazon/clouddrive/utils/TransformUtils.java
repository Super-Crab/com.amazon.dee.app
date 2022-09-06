package com.amazon.clouddrive.utils;

import java.util.HashMap;
import java.util.Map;
/* loaded from: classes11.dex */
public final class TransformUtils {
    private static final Map<String, String> TRANSFORM_TO_OPERATION_MAP = new HashMap<String, String>() { // from class: com.amazon.clouddrive.utils.TransformUtils.1
        {
            put("DOCUMENT_CONVERSION", "/alt/pdf");
            put("PDF", "/alt/pdf");
            put("IMAGE_THUMBNAIL", "/alt/thumb");
            put("VIDEO_TRANSCODE", "/alt/video_transcode");
        }
    };

    private TransformUtils() {
        throw new UnsupportedOperationException("Do not instantiate.");
    }

    public static Optional<String> getTransformPathSuffix(String str) {
        return Optional.fromNullable(TRANSFORM_TO_OPERATION_MAP.get(str));
    }
}
