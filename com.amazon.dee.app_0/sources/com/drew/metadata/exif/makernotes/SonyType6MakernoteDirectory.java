package com.drew.metadata.exif.makernotes;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import java.util.HashMap;
/* loaded from: classes2.dex */
public class SonyType6MakernoteDirectory extends Directory {
    public static final int TAG_MAKERNOTE_THUMB_LENGTH = 1300;
    public static final int TAG_MAKERNOTE_THUMB_OFFSET = 1299;
    public static final int TAG_MAKERNOTE_THUMB_VERSION = 8192;
    @NotNull
    protected static final HashMap<Integer, String> _tagNameMap = new HashMap<>();

    static {
        _tagNameMap.put(Integer.valueOf((int) TAG_MAKERNOTE_THUMB_OFFSET), "Makernote Thumb Offset");
        _tagNameMap.put(Integer.valueOf((int) TAG_MAKERNOTE_THUMB_LENGTH), "Makernote Thumb Length");
        _tagNameMap.put(8192, "Makernote Thumb Version");
    }

    public SonyType6MakernoteDirectory() {
        setDescriptor(new SonyType6MakernoteDescriptor(this));
    }

    @Override // com.drew.metadata.Directory
    @NotNull
    public String getName() {
        return "Sony Makernote";
    }

    @Override // com.drew.metadata.Directory
    @NotNull
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }
}
