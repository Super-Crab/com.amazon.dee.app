package org.apache.commons.lang;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes4.dex */
public class NullArgumentException extends IllegalArgumentException {
    private static final long serialVersionUID = 1174360235354917591L;

    public NullArgumentException(String str) {
        super(GeneratedOutlineSupport1.outline83(new StringBuffer(), str == null ? "Argument" : str, " must not be null."));
    }
}
