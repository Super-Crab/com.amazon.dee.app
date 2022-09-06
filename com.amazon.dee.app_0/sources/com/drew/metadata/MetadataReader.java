package com.drew.metadata;

import com.drew.lang.RandomAccessReader;
import com.drew.lang.annotations.NotNull;
/* loaded from: classes2.dex */
public interface MetadataReader {
    void extract(@NotNull RandomAccessReader randomAccessReader, @NotNull Metadata metadata);
}
