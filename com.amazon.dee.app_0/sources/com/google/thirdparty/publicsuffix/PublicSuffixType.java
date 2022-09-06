package com.google.thirdparty.publicsuffix;

import com.amazon.deecomms.common.Constants;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import kotlinx.serialization.json.internal.JsonReaderKt;
@Beta
@GwtCompatible
/* loaded from: classes3.dex */
public enum PublicSuffixType {
    PRIVATE(JsonReaderKt.COLON, JsonReaderKt.COMMA),
    REGISTRY('!', Constants.DEFAULT_IMAGE_CHAR);
    
    private final char innerNodeCode;
    private final char leafNodeCode;

    PublicSuffixType(char c, char c2) {
        this.innerNodeCode = c;
        this.leafNodeCode = c2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PublicSuffixType fromCode(char c) {
        PublicSuffixType[] values;
        for (PublicSuffixType publicSuffixType : values()) {
            if (publicSuffixType.getInnerNodeCode() == c || publicSuffixType.getLeafNodeCode() == c) {
                return publicSuffixType;
            }
        }
        StringBuilder sb = new StringBuilder(38);
        sb.append("No enum corresponding to given code: ");
        sb.append(c);
        throw new IllegalArgumentException(sb.toString());
    }

    static PublicSuffixType fromIsPrivate(boolean z) {
        return z ? PRIVATE : REGISTRY;
    }

    char getInnerNodeCode() {
        return this.innerNodeCode;
    }

    char getLeafNodeCode() {
        return this.leafNodeCode;
    }
}
