package com.drew.metadata;

import com.drew.lang.CompoundException;
import com.drew.lang.annotations.Nullable;
/* loaded from: classes2.dex */
public class MetadataException extends CompoundException {
    private static final long serialVersionUID = 8612756143363919682L;

    public MetadataException(@Nullable String str) {
        super(str);
    }

    public MetadataException(@Nullable String str, @Nullable Throwable th) {
        super(str, th);
    }

    public MetadataException(@Nullable Throwable th) {
        super(th);
    }
}
