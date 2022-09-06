package com.drew.metadata.jfxx;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
/* loaded from: classes2.dex */
public class JfxxDescriptor extends TagDescriptor<JfxxDirectory> {
    public JfxxDescriptor(@NotNull JfxxDirectory jfxxDirectory) {
        super(jfxxDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    @Nullable
    public String getDescription(int i) {
        return i != 5 ? super.getDescription(i) : getExtensionCodeDescription();
    }

    @Nullable
    public String getExtensionCodeDescription() {
        Integer integer = ((JfxxDirectory) this._directory).getInteger(5);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        if (intValue == 16) {
            return "Thumbnail coded using JPEG";
        }
        if (intValue == 17) {
            return "Thumbnail stored using 1 byte/pixel";
        }
        if (intValue == 19) {
            return "Thumbnail stored using 3 bytes/pixel";
        }
        return "Unknown extension code " + integer;
    }
}
