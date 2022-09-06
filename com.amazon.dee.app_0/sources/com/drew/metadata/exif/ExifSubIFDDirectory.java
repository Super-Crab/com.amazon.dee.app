package com.drew.metadata.exif;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;
/* loaded from: classes2.dex */
public class ExifSubIFDDirectory extends ExifDirectoryBase {
    public static final int TAG_INTEROP_OFFSET = 40965;
    @NotNull
    protected static final HashMap<Integer, String> _tagNameMap = new HashMap<>();

    static {
        ExifDirectoryBase.addExifTagNames(_tagNameMap);
    }

    public ExifSubIFDDirectory() {
        setDescriptor(new ExifSubIFDDescriptor(this));
    }

    @Nullable
    public Date getDateDigitized() {
        return getDateDigitized(null);
    }

    @Nullable
    public Date getDateDigitized(@Nullable TimeZone timeZone) {
        return getDate(ExifDirectoryBase.TAG_DATETIME_DIGITIZED, getString(ExifDirectoryBase.TAG_SUBSECOND_TIME_DIGITIZED), timeZone);
    }

    @Nullable
    public Date getDateOriginal() {
        return getDateOriginal(null);
    }

    @Nullable
    public Date getDateOriginal(@Nullable TimeZone timeZone) {
        return getDate(ExifDirectoryBase.TAG_DATETIME_ORIGINAL, getString(ExifDirectoryBase.TAG_SUBSECOND_TIME_ORIGINAL), timeZone);
    }

    @Override // com.drew.metadata.Directory
    @NotNull
    public String getName() {
        return "Exif SubIFD";
    }

    @Override // com.drew.metadata.Directory
    @NotNull
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }
}
