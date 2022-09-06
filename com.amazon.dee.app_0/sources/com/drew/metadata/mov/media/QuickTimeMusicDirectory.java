package com.drew.metadata.mov.media;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.mov.QuickTimeDirectory;
import java.util.HashMap;
/* loaded from: classes2.dex */
public class QuickTimeMusicDirectory extends QuickTimeDirectory {
    @NotNull
    protected static final HashMap<Integer, String> _tagNameMap = new HashMap<>();

    static {
        QuickTimeMediaDirectory.addQuickTimeMediaTags(_tagNameMap);
    }

    public QuickTimeMusicDirectory() {
        setDescriptor(new QuickTimeMusicDescriptor(this));
    }

    @Override // com.drew.metadata.mov.QuickTimeDirectory, com.drew.metadata.Directory
    @NotNull
    public String getName() {
        return "QuickTime Music";
    }

    @Override // com.drew.metadata.mov.QuickTimeDirectory, com.drew.metadata.Directory
    @NotNull
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }
}
