package com.amazon.alexa.fitness.service.hds.model;

import com.amazon.alexa.routing.api.RouteParameter;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: GraphQLResponse.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0004¢\u0006\u0002\u0010\bJ\u0011\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0004HÆ\u0003J7\u0010\u0011\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0004HÖ\u0001R\u0019\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0018"}, d2 = {"Lcom/amazon/alexa/fitness/service/hds/model/Error;", "", RouteParameter.PATH, "", "", "locations", "Lcom/amazon/alexa/fitness/service/hds/model/ErrorLocation;", "message", "(Ljava/util/List;Ljava/util/List;Ljava/lang/String;)V", "getLocations", "()Ljava/util/List;", "getMessage", "()Ljava/lang/String;", "getPath", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class Error {
    @Nullable
    private final List<ErrorLocation> locations;
    @NotNull
    private final String message;
    @Nullable
    private final List<String> path;

    public Error(@Nullable List<String> list, @Nullable List<ErrorLocation> list2, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        this.path = list;
        this.locations = list2;
        this.message = message;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Error copy$default(Error error, List list, List list2, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            list = error.path;
        }
        if ((i & 2) != 0) {
            list2 = error.locations;
        }
        if ((i & 4) != 0) {
            str = error.message;
        }
        return error.copy(list, list2, str);
    }

    @Nullable
    public final List<String> component1() {
        return this.path;
    }

    @Nullable
    public final List<ErrorLocation> component2() {
        return this.locations;
    }

    @NotNull
    public final String component3() {
        return this.message;
    }

    @NotNull
    public final Error copy(@Nullable List<String> list, @Nullable List<ErrorLocation> list2, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        return new Error(list, list2, message);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof Error)) {
                return false;
            }
            Error error = (Error) obj;
            return Intrinsics.areEqual(this.path, error.path) && Intrinsics.areEqual(this.locations, error.locations) && Intrinsics.areEqual(this.message, error.message);
        }
        return true;
    }

    @Nullable
    public final List<ErrorLocation> getLocations() {
        return this.locations;
    }

    @NotNull
    public final String getMessage() {
        return this.message;
    }

    @Nullable
    public final List<String> getPath() {
        return this.path;
    }

    public int hashCode() {
        List<String> list = this.path;
        int i = 0;
        int hashCode = (list != null ? list.hashCode() : 0) * 31;
        List<ErrorLocation> list2 = this.locations;
        int hashCode2 = (hashCode + (list2 != null ? list2.hashCode() : 0)) * 31;
        String str = this.message;
        if (str != null) {
            i = str.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error(path=");
        outline107.append(this.path);
        outline107.append(", locations=");
        outline107.append(this.locations);
        outline107.append(", message=");
        return GeneratedOutlineSupport1.outline91(outline107, this.message, ")");
    }
}
