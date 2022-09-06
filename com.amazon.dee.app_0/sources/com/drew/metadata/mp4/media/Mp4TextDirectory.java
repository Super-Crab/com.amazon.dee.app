package com.drew.metadata.mp4.media;

import com.drew.lang.annotations.NotNull;
import java.util.HashMap;
/* loaded from: classes2.dex */
public class Mp4TextDirectory extends Mp4MediaDirectory {
    @NotNull
    protected static final HashMap<Integer, String> _tagNameMap = new HashMap<>();

    static {
        Mp4MediaDirectory.addMp4MediaTags(_tagNameMap);
    }

    public Mp4TextDirectory() {
        setDescriptor(new Mp4TextDescriptor(this));
    }

    @Override // com.drew.metadata.mp4.Mp4Directory, com.drew.metadata.Directory
    @NotNull
    public String getName() {
        return "MP4 Text";
    }

    @Override // com.drew.metadata.mp4.Mp4Directory, com.drew.metadata.Directory
    @NotNull
    protected HashMap<Integer, String> getTagNameMap() {
        return null;
    }
}
