package net.sqlcipher;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes4.dex */
public class CursorIndexOutOfBoundsException extends IndexOutOfBoundsException {
    public CursorIndexOutOfBoundsException(int i, int i2) {
        super(GeneratedOutlineSupport1.outline53("Index ", i, " requested, with a size of ", i2));
    }

    public CursorIndexOutOfBoundsException(String str) {
        super(str);
    }
}
