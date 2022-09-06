package com.amazon.alexa.fitness.util;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
/* compiled from: GsonUtils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u001a\u001e\u0010\u0000\u001a\u0002H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u0003H\u0086\b¢\u0006\u0002\u0010\u0004\u001a\u0015\u0010\u0005\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0001*\u0002H\u0001¢\u0006\u0002\u0010\u0006\u001a\u0015\u0010\u0007\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0001*\u0002H\u0001¢\u0006\u0002\u0010\u0006¨\u0006\b"}, d2 = {"fromJson", ExifInterface.GPS_DIRECTION_TRUE, "", "", "(Ljava/lang/String;)Ljava/lang/Object;", "toJson", "(Ljava/lang/Object;)Ljava/lang/String;", "toJsonWithNulls", "AlexaMobileAndroidFitnessExtension_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class GsonUtilsKt {
    @NotNull
    public static final /* synthetic */ <T> T fromJson(@NotNull String fromJson) {
        boolean isBlank;
        Intrinsics.checkParameterIsNotNull(fromJson, "$this$fromJson");
        try {
            isBlank = StringsKt__StringsJVMKt.isBlank(fromJson);
            if (!(!isBlank)) {
                throw new JsonParseException("Cannot parse blank JSON String.");
            }
            Gson gson = GsonUtils.Companion.getGson();
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            T t = (T) gson.fromJson(fromJson, (Class<Object>) Object.class);
            Intrinsics.checkExpressionValueIsNotNull(t, "gson.fromJson(this, T::class.java)");
            return t;
        } catch (JsonParseException e) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline73("Failed to deserialize JSON String: '", fromJson, Chars.QUOTE), e);
        }
    }

    @NotNull
    public static final <T> String toJson(T t) {
        String json = GsonUtils.Companion.getGson().toJson(t);
        Intrinsics.checkExpressionValueIsNotNull(json, "gson.toJson(this)");
        return json;
    }

    @NotNull
    public static final <T> String toJsonWithNulls(T t) {
        String json = new GsonBuilder().serializeNulls().create().toJson(t);
        Intrinsics.checkExpressionValueIsNotNull(json, "GsonBuilder().serializeN…s().create().toJson(this)");
        return json;
    }
}
