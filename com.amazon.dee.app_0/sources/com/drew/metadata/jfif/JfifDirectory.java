package com.drew.metadata.jfif;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.MetadataException;
import java.util.HashMap;
/* loaded from: classes2.dex */
public class JfifDirectory extends Directory {
    public static final int TAG_RESX = 8;
    public static final int TAG_RESY = 10;
    public static final int TAG_THUMB_HEIGHT = 13;
    public static final int TAG_THUMB_WIDTH = 12;
    public static final int TAG_UNITS = 7;
    public static final int TAG_VERSION = 5;
    @NotNull
    protected static final HashMap<Integer, String> _tagNameMap = new HashMap<>();

    static {
        _tagNameMap.put(5, "Version");
        _tagNameMap.put(7, "Resolution Units");
        _tagNameMap.put(10, "Y Resolution");
        _tagNameMap.put(8, "X Resolution");
        _tagNameMap.put(12, "Thumbnail Width Pixels");
        _tagNameMap.put(13, "Thumbnail Height Pixels");
    }

    public JfifDirectory() {
        setDescriptor(new JfifDescriptor(this));
    }

    @Deprecated
    public int getImageHeight() throws MetadataException {
        return getInt(8);
    }

    @Deprecated
    public int getImageWidth() throws MetadataException {
        return getInt(10);
    }

    @Override // com.drew.metadata.Directory
    @NotNull
    public String getName() {
        return JfifReader.PREAMBLE;
    }

    public int getResUnits() throws MetadataException {
        return getInt(7);
    }

    public int getResX() throws MetadataException {
        return getInt(8);
    }

    public int getResY() throws MetadataException {
        return getInt(10);
    }

    @Override // com.drew.metadata.Directory
    @NotNull
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    public int getVersion() throws MetadataException {
        return getInt(5);
    }
}
