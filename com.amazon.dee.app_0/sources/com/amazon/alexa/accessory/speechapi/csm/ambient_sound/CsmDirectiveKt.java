package com.amazon.alexa.accessory.speechapi.csm.ambient_sound;

import android.content.Intent;
import android.os.Bundle;
import com.amazon.alexa.accessory.internal.util.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: CsmDirective.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"toCsmDirective", "Lcom/amazon/alexa/accessory/speechapi/csm/ambient_sound/CsmDirective;", "Landroid/content/Intent;", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class CsmDirectiveKt {
    @NotNull
    public static final CsmDirective toCsmDirective(@NotNull Intent toCsmDirective) {
        Intrinsics.checkParameterIsNotNull(toCsmDirective, "$this$toCsmDirective");
        Logger.d("CsmDirective: Intent.toCsmDirective");
        Bundle extras = toCsmDirective.getExtras();
        if (extras != null) {
            Intrinsics.checkExpressionValueIsNotNull(extras, "this.extras ?: throw Ill…le value is not present\")");
            String string = extras.getString("name");
            if (string != null) {
                Intrinsics.checkExpressionValueIsNotNull(string, "bundle.getString(CsmDire…me value is not present\")");
                String string2 = extras.getString("namespace");
                if (string2 != null) {
                    Intrinsics.checkExpressionValueIsNotNull(string2, "bundle.getString(CsmDire…ce value is not present\")");
                    String string3 = extras.getString("payload");
                    if (string3 != null) {
                        Intrinsics.checkExpressionValueIsNotNull(string3, "bundle.getString(CsmDire…ad value is not present\")");
                        return new CsmDirective(string, string2, string3);
                    }
                    throw new IllegalArgumentException("payload value is not present");
                }
                throw new IllegalArgumentException("namespace value is not present");
            }
            throw new IllegalArgumentException("name value is not present");
        }
        throw new IllegalArgumentException("Bundle value is not present");
    }
}
