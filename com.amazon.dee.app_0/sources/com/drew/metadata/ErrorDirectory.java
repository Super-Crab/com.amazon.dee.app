package com.drew.metadata;

import com.drew.lang.annotations.NotNull;
import java.util.HashMap;
/* loaded from: classes2.dex */
public final class ErrorDirectory extends Directory {
    public ErrorDirectory() {
    }

    public ErrorDirectory(String str) {
        super.addError(str);
    }

    @Override // com.drew.metadata.Directory
    @NotNull
    public String getName() {
        return "Error";
    }

    @Override // com.drew.metadata.Directory
    @NotNull
    public String getTagName(int i) {
        return "";
    }

    @Override // com.drew.metadata.Directory
    @NotNull
    protected HashMap<Integer, String> getTagNameMap() {
        return new HashMap<>();
    }

    @Override // com.drew.metadata.Directory
    public boolean hasTagName(int i) {
        return false;
    }

    @Override // com.drew.metadata.Directory
    public void setObject(int i, @NotNull Object obj) {
        throw new UnsupportedOperationException(String.format("Cannot add value to %s.", ErrorDirectory.class.getName()));
    }
}
