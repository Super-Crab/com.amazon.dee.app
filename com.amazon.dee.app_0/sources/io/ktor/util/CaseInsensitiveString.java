package io.ktor.util;

import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Text.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\f\u001a\u00020\bH\u0016J\b\u0010\r\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lio/ktor/util/CaseInsensitiveString;", "", "content", "", "(Ljava/lang/String;)V", "getContent", "()Ljava/lang/String;", "hash", "", "equals", "", "other", "hashCode", "toString", "ktor-utils"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class CaseInsensitiveString {
    @NotNull
    private final String content;
    private final int hash;

    public CaseInsensitiveString(@NotNull String content) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        this.content = content;
        String str = this.content;
        if (str != null) {
            String lowerCase = str.toLowerCase();
            Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
            this.hash = lowerCase.hashCode();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    public boolean equals(@Nullable Object obj) {
        String str;
        boolean equals;
        if (!(obj instanceof CaseInsensitiveString)) {
            obj = null;
        }
        CaseInsensitiveString caseInsensitiveString = (CaseInsensitiveString) obj;
        if (caseInsensitiveString != null && (str = caseInsensitiveString.content) != null) {
            equals = StringsKt__StringsJVMKt.equals(str, this.content, true);
            if (equals) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    public final String getContent() {
        return this.content;
    }

    public int hashCode() {
        return this.hash;
    }

    @NotNull
    public String toString() {
        return this.content;
    }
}
