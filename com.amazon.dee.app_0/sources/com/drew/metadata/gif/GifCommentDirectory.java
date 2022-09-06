package com.drew.metadata.gif;

import com.amazon.identity.auth.map.device.token.MAPCookie;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.StringValue;
import java.util.HashMap;
/* loaded from: classes2.dex */
public class GifCommentDirectory extends Directory {
    public static final int TAG_COMMENT = 1;
    @NotNull
    protected static final HashMap<Integer, String> _tagNameMap = new HashMap<>();

    static {
        _tagNameMap.put(1, MAPCookie.KEY_COMMENT);
    }

    public GifCommentDirectory(StringValue stringValue) {
        setDescriptor(new GifCommentDescriptor(this));
        setStringValue(1, stringValue);
    }

    @Override // com.drew.metadata.Directory
    @NotNull
    public String getName() {
        return "GIF Comment";
    }

    @Override // com.drew.metadata.Directory
    @NotNull
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }
}
