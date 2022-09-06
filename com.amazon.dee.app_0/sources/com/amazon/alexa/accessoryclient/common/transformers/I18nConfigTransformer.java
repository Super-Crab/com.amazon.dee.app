package com.amazon.alexa.accessoryclient.common.transformers;

import android.os.Bundle;
import com.amazon.alexa.accessory.davs.DavsI18nConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
/* compiled from: I18nConfigTransformer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/transformers/I18nConfigTransformer;", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "Lcom/amazon/alexa/accessory/davs/DavsI18nConfig;", "()V", "I18N_CONFIG_JSON_KEY", "", "fromBundle", "bundle", "Landroid/os/Bundle;", "toBundle", "t", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class I18nConfigTransformer implements BundleTransformer<DavsI18nConfig> {
    private static final String I18N_CONFIG_JSON_KEY = "i18nConfig";
    public static final I18nConfigTransformer INSTANCE = new I18nConfigTransformer();

    private I18nConfigTransformer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
    @NotNull
    /* renamed from: fromBundle */
    public DavsI18nConfig mo568fromBundle(@NotNull Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        DavsI18nConfig.Factory factory = DavsI18nConfig.FACTORY;
        String string = bundle.getString(I18N_CONFIG_JSON_KEY);
        if (string == null) {
            Intrinsics.throwNpe();
        }
        DavsI18nConfig mo1239create = factory.mo1239create(new JSONObject(string));
        Intrinsics.checkExpressionValueIsNotNull(mo1239create, "DavsI18nConfig.FACTORY.c…IG_JSON_KEY)!!\n        ))");
        return mo1239create;
    }

    @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
    @NotNull
    public Bundle toBundle(@NotNull DavsI18nConfig t) {
        Intrinsics.checkParameterIsNotNull(t, "t");
        Bundle bundle = new Bundle();
        bundle.putString(I18N_CONFIG_JSON_KEY, t.toJsonObject().toString());
        return bundle;
    }
}
