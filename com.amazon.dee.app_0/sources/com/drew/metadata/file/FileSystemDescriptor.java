package com.drew.metadata.file;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
/* loaded from: classes2.dex */
public class FileSystemDescriptor extends TagDescriptor<FileSystemDirectory> {
    public FileSystemDescriptor(@NotNull FileSystemDirectory fileSystemDirectory) {
        super(fileSystemDirectory);
    }

    @Nullable
    private String getFileSizeDescription() {
        Long longObject = ((FileSystemDirectory) this._directory).getLongObject(2);
        if (longObject == null) {
            return null;
        }
        return Long.toString(longObject.longValue()) + " bytes";
    }

    @Override // com.drew.metadata.TagDescriptor
    @Nullable
    public String getDescription(int i) {
        return i != 2 ? super.getDescription(i) : getFileSizeDescription();
    }
}
