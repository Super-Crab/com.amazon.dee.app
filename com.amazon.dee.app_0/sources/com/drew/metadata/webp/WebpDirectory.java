package com.drew.metadata.webp;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import java.util.HashMap;
/* loaded from: classes2.dex */
public class WebpDirectory extends Directory {
    public static final String CHUNK_EXIF = "EXIF";
    public static final String CHUNK_ICCP = "ICCP";
    public static final String CHUNK_VP8 = "VP8 ";
    public static final String CHUNK_VP8L = "VP8L";
    public static final String CHUNK_VP8X = "VP8X";
    public static final String CHUNK_XMP = "XMP ";
    public static final String FORMAT = "WEBP";
    public static final int TAG_HAS_ALPHA = 3;
    public static final int TAG_IMAGE_HEIGHT = 1;
    public static final int TAG_IMAGE_WIDTH = 2;
    public static final int TAG_IS_ANIMATION = 4;
    @NotNull
    protected static final HashMap<Integer, String> _tagNameMap = new HashMap<>();

    static {
        _tagNameMap.put(1, "Image Height");
        _tagNameMap.put(2, "Image Width");
        _tagNameMap.put(3, "Has Alpha");
        _tagNameMap.put(4, "Is Animation");
    }

    public WebpDirectory() {
        setDescriptor(new WebpDescriptor(this));
    }

    @Override // com.drew.metadata.Directory
    @NotNull
    public String getName() {
        return "WebP";
    }

    @Override // com.drew.metadata.Directory
    @NotNull
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }
}
