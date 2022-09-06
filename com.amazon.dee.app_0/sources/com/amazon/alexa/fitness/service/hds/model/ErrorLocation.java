package com.amazon.alexa.fitness.service.hds.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.devsupport.StackTraceHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: GraphQLResponse.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0006HÆ\u0003J)\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/amazon/alexa/fitness/service/hds/model/ErrorLocation;", "", "line", "", StackTraceHelper.COLUMN_KEY, "sourceName", "", "(IILjava/lang/String;)V", "getColumn", "()I", "getLine", "getSourceName", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class ErrorLocation {
    private final int column;
    private final int line;
    @Nullable
    private final String sourceName;

    public ErrorLocation(int i, int i2, @Nullable String str) {
        this.line = i;
        this.column = i2;
        this.sourceName = str;
    }

    public static /* synthetic */ ErrorLocation copy$default(ErrorLocation errorLocation, int i, int i2, String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = errorLocation.line;
        }
        if ((i3 & 2) != 0) {
            i2 = errorLocation.column;
        }
        if ((i3 & 4) != 0) {
            str = errorLocation.sourceName;
        }
        return errorLocation.copy(i, i2, str);
    }

    public final int component1() {
        return this.line;
    }

    public final int component2() {
        return this.column;
    }

    @Nullable
    public final String component3() {
        return this.sourceName;
    }

    @NotNull
    public final ErrorLocation copy(int i, int i2, @Nullable String str) {
        return new ErrorLocation(i, i2, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof ErrorLocation)) {
                return false;
            }
            ErrorLocation errorLocation = (ErrorLocation) obj;
            return this.line == errorLocation.line && this.column == errorLocation.column && Intrinsics.areEqual(this.sourceName, errorLocation.sourceName);
        }
        return true;
    }

    public final int getColumn() {
        return this.column;
    }

    public final int getLine() {
        return this.line;
    }

    @Nullable
    public final String getSourceName() {
        return this.sourceName;
    }

    public int hashCode() {
        int i = ((this.line * 31) + this.column) * 31;
        String str = this.sourceName;
        return i + (str != null ? str.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ErrorLocation(line=");
        outline107.append(this.line);
        outline107.append(", column=");
        outline107.append(this.column);
        outline107.append(", sourceName=");
        return GeneratedOutlineSupport1.outline91(outline107, this.sourceName, ")");
    }
}
