package com.amazon.alexa.fitness.view.homecard;

import android.content.Context;
import android.util.Log;
import com.amazon.alexa.fitness.api.afx.FitnessRoutesKt;
import com.amazon.alexa.fitness.view.message.CustomToast;
import com.amazon.alexa.viewprovider.api.event.EventCapture;
import com.amazon.alexa.viewprovider.api.provider.ViewProvider;
import com.amazon.alexa.viewprovider.api.view.ViewModule;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
/* compiled from: FitnessHomeViewProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/fitness/view/homecard/FitnessViewProvider;", "Lcom/amazon/alexa/viewprovider/api/provider/ViewProvider;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "createView", "Lcom/amazon/alexa/viewprovider/api/view/ViewModule;", "payload", "Lorg/json/JSONObject;", "eventCapture", "Lcom/amazon/alexa/viewprovider/api/event/EventCapture;", "isSupported", "", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FitnessViewProvider implements ViewProvider {
    private final Context context;

    public FitnessViewProvider(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
    }

    @Override // com.amazon.alexa.viewprovider.api.provider.ViewProvider
    @Nullable
    public ViewModule createView(@Nullable JSONObject jSONObject, @Nullable EventCapture eventCapture) {
        CustomToast.Companion.setContext(this.context);
        if (!isSupported(jSONObject)) {
            return null;
        }
        Log.i("FitnessViewProvider", "returning view module");
        return new FitnessHomeViewModule(this.context);
    }

    @Override // com.amazon.alexa.viewprovider.api.provider.ViewProvider
    public boolean isSupported(@Nullable JSONObject jSONObject) {
        String optString;
        if (jSONObject == null || (optString = jSONObject.optString("customTemplateRoute", "")) == null) {
            return false;
        }
        return optString.equals(FitnessRoutesKt.FITNESS_HOME_CARD_ROUTE);
    }
}
