package com.drew.metadata;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
/* loaded from: classes2.dex */
public class Tag {
    @NotNull
    private final Directory _directory;
    private final int _tagType;

    public Tag(int i, @NotNull Directory directory) {
        this._tagType = i;
        this._directory = directory;
    }

    @Nullable
    public String getDescription() {
        return this._directory.getDescription(this._tagType);
    }

    @NotNull
    public String getDirectoryName() {
        return this._directory.getName();
    }

    @NotNull
    public String getTagName() {
        return this._directory.getTagName(this._tagType);
    }

    public int getTagType() {
        return this._tagType;
    }

    @NotNull
    public String getTagTypeHex() {
        return String.format("0x%04x", Integer.valueOf(this._tagType));
    }

    public boolean hasTagName() {
        return this._directory.hasTagName(this._tagType);
    }

    @NotNull
    public String toString() {
        String description = getDescription();
        if (description == null) {
            description = this._directory.getString(getTagType()) + " (unable to formulate description)";
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[");
        outline107.append(this._directory.getName());
        outline107.append("] ");
        outline107.append(getTagName());
        outline107.append(" - ");
        outline107.append(description);
        return outline107.toString();
    }
}
