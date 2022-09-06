package com.drew.metadata.wav;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
/* loaded from: classes2.dex */
public class WavDescriptor extends TagDescriptor<WavDirectory> {
    public WavDescriptor(@NotNull WavDirectory wavDirectory) {
        super(wavDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    @Nullable
    public String getDescription(int i) {
        return super.getDescription(i);
    }
}
