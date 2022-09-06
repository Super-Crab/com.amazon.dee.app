package com.amazon.alexa.fitness.service.hds.model;

import com.amazon.alexa.accessory.notificationpublisher.utils.NotificationConstants;
import com.amazon.alexa.fitness.util.GsonUtils;
import com.amazon.alexa.fitness.util.GsonUtilsKt;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.identity.auth.device.api.MAPWebViewEventHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.internal.LinkedTreeMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: GraphQLResponse.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\tJ\u0017\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0014\u001a\u00020\u00152\n\u0010\u0016\u001a\u0006\u0012\u0002\b\u00030\u0017J?\u0010\u0018\u001a\u00020\u00002\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0004HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\u0006\u0010\u001e\u001a\u00020\u001aJ\u0006\u0010\u001f\u001a\u00020\u001aJ\t\u0010 \u001a\u00020\u0004HÖ\u0001R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001f\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0019\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006!"}, d2 = {"Lcom/amazon/alexa/fitness/service/hds/model/GraphQLResponse;", "", "data", "Lcom/google/gson/internal/LinkedTreeMap;", "", MAPWebViewEventHelper.KEY_ERRORS, "", "Lcom/amazon/alexa/fitness/service/hds/model/Error;", AlexaMetricsConstants.EventConstants.MESSAGE, "(Lcom/google/gson/internal/LinkedTreeMap;Ljava/util/List;Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "getData", "()Lcom/google/gson/internal/LinkedTreeMap;", "getErrors", "()Ljava/util/List;", "component1", "component2", "component3", "convertToObject", NotificationConstants.REQUEST_TYPE, "Lcom/amazon/alexa/fitness/service/hds/model/RequestType;", "clazz", "Ljava/lang/Class;", "copy", "equals", "", "other", "hashCode", "", "isAuthSuccessful", "isSuccessful", "toString", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class GraphQLResponse {
    @Nullable
    private final String Message;
    @Nullable
    private final LinkedTreeMap<String, Object> data;
    @Nullable
    private final List<Error> errors;

    public GraphQLResponse(@Nullable LinkedTreeMap<String, Object> linkedTreeMap, @Nullable List<Error> list, @Nullable String str) {
        this.data = linkedTreeMap;
        this.errors = list;
        this.Message = str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ GraphQLResponse copy$default(GraphQLResponse graphQLResponse, LinkedTreeMap linkedTreeMap, List list, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            linkedTreeMap = graphQLResponse.data;
        }
        if ((i & 2) != 0) {
            list = graphQLResponse.errors;
        }
        if ((i & 4) != 0) {
            str = graphQLResponse.Message;
        }
        return graphQLResponse.copy(linkedTreeMap, list, str);
    }

    @Nullable
    public final LinkedTreeMap<String, Object> component1() {
        return this.data;
    }

    @Nullable
    public final List<Error> component2() {
        return this.errors;
    }

    @Nullable
    public final String component3() {
        return this.Message;
    }

    @Nullable
    public final Object convertToObject(@NotNull RequestType requestType, @NotNull Class<?> clazz) {
        Intrinsics.checkParameterIsNotNull(requestType, "requestType");
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        if (this.data != null) {
            return GsonUtils.Companion.getGson().fromJson(GsonUtilsKt.toJson(this.data.get(requestType.getValue())), (Class<Object>) clazz);
        }
        return null;
    }

    @NotNull
    public final GraphQLResponse copy(@Nullable LinkedTreeMap<String, Object> linkedTreeMap, @Nullable List<Error> list, @Nullable String str) {
        return new GraphQLResponse(linkedTreeMap, list, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof GraphQLResponse)) {
                return false;
            }
            GraphQLResponse graphQLResponse = (GraphQLResponse) obj;
            return Intrinsics.areEqual(this.data, graphQLResponse.data) && Intrinsics.areEqual(this.errors, graphQLResponse.errors) && Intrinsics.areEqual(this.Message, graphQLResponse.Message);
        }
        return true;
    }

    @Nullable
    public final LinkedTreeMap<String, Object> getData() {
        return this.data;
    }

    @Nullable
    public final List<Error> getErrors() {
        return this.errors;
    }

    @Nullable
    public final String getMessage() {
        return this.Message;
    }

    public int hashCode() {
        LinkedTreeMap<String, Object> linkedTreeMap = this.data;
        int i = 0;
        int hashCode = (linkedTreeMap != null ? linkedTreeMap.hashCode() : 0) * 31;
        List<Error> list = this.errors;
        int hashCode2 = (hashCode + (list != null ? list.hashCode() : 0)) * 31;
        String str = this.Message;
        if (str != null) {
            i = str.hashCode();
        }
        return hashCode2 + i;
    }

    public final boolean isAuthSuccessful() {
        return this.Message == null;
    }

    public final boolean isSuccessful() {
        List<Error> list;
        return isAuthSuccessful() && ((list = this.errors) == null || list.isEmpty());
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GraphQLResponse(data=");
        outline107.append(this.data);
        outline107.append(", errors=");
        outline107.append(this.errors);
        outline107.append(", Message=");
        return GeneratedOutlineSupport1.outline91(outline107, this.Message, ")");
    }
}
