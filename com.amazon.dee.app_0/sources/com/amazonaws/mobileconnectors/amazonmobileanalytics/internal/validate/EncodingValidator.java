package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.validate;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.UnsupportedEncodingException;
@Deprecated
/* loaded from: classes13.dex */
public class EncodingValidator {
    private final String encoding;

    public EncodingValidator(String str) {
        this.encoding = str;
    }

    public void validate() {
        try {
            "!\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~".getBytes(this.encoding);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.encoding, " encoding is not supported"), e);
        }
    }
}
