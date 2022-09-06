package com.drew.metadata.photoshop;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.identity.auth.map.device.token.MAPCookie;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;
/* loaded from: classes2.dex */
public class DuckyDirectory extends Directory {
    public static final int TAG_COMMENT = 2;
    public static final int TAG_COPYRIGHT = 3;
    public static final int TAG_QUALITY = 1;
    @NotNull
    protected static final HashMap<Integer, String> _tagNameMap = new HashMap<>();

    static {
        _tagNameMap.put(1, "Quality");
        _tagNameMap.put(2, MAPCookie.KEY_COMMENT);
        _tagNameMap.put(3, ExifInterface.TAG_COPYRIGHT);
    }

    public DuckyDirectory() {
        setDescriptor(new TagDescriptor(this));
    }

    @Override // com.drew.metadata.Directory
    @NotNull
    public String getName() {
        return "Ducky";
    }

    @Override // com.drew.metadata.Directory
    @NotNull
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }
}
