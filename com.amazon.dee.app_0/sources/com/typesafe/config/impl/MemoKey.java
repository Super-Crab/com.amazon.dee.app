package com.typesafe.config.impl;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class MemoKey {
    private final Path restrictToChildOrNull;
    private final AbstractConfigValue value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MemoKey(AbstractConfigValue abstractConfigValue, Path path) {
        this.value = abstractConfigValue;
        this.restrictToChildOrNull = path;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof MemoKey) {
            MemoKey memoKey = (MemoKey) obj;
            if (memoKey.value != this.value) {
                return false;
            }
            Path path = memoKey.restrictToChildOrNull;
            Path path2 = this.restrictToChildOrNull;
            if (path == path2) {
                return true;
            }
            if (path != null && path2 != null) {
                return path.equals(path2);
            }
        }
        return false;
    }

    public final int hashCode() {
        int identityHashCode = System.identityHashCode(this.value);
        Path path = this.restrictToChildOrNull;
        return path != null ? ((path.hashCode() + 41) * 41) + identityHashCode : identityHashCode;
    }

    public final String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MemoKey(");
        outline107.append(this.value);
        outline107.append("@");
        outline107.append(System.identityHashCode(this.value));
        outline107.append(",");
        outline107.append(this.restrictToChildOrNull);
        outline107.append(")");
        return outline107.toString();
    }
}
