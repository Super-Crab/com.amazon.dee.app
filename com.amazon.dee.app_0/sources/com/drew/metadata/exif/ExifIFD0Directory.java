package com.drew.metadata.exif;

import com.drew.lang.annotations.NotNull;
import java.util.HashMap;
/* loaded from: classes2.dex */
public class ExifIFD0Directory extends ExifDirectoryBase {
    public static final int TAG_EXIF_SUB_IFD_OFFSET = 34665;
    public static final int TAG_GPS_INFO_OFFSET = 34853;
    @NotNull
    protected static final HashMap<Integer, String> _tagNameMap = new HashMap<>();

    static {
        ExifDirectoryBase.addExifTagNames(_tagNameMap);
    }

    public ExifIFD0Directory() {
        setDescriptor(new ExifIFD0Descriptor(this));
    }

    @Override // com.drew.metadata.Directory
    @NotNull
    public String getName() {
        return "Exif IFD0";
    }

    @Override // com.drew.metadata.Directory
    @NotNull
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }
}
