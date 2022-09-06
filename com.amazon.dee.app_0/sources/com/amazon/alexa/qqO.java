package com.amazon.alexa;

import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.core.configuration.Stage;
import dagger.Lazy;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SearchUiMetricsAuthority.kt */
/* loaded from: classes.dex */
public final class qqO extends Lambda implements Function0<String> {
    public final /* synthetic */ KPv zZm;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public qqO(KPv kPv) {
        super(0);
        this.zZm = kPv;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public String mo12560invoke() {
        Lazy lazy;
        lazy = this.zZm.jiA;
        Object mo358get = lazy.mo358get();
        Intrinsics.checkExpressionValueIsNotNull(mo358get, "clientConfigurationLazy.get()");
        Stage stage = ((ClientConfiguration) mo358get).getStage();
        if (stage == null) {
            stage = Stage.PROD;
        }
        Intrinsics.checkExpressionValueIsNotNull(stage, "clientConfigurationLazy.get().stage ?: Stage.PROD");
        return "AlexaMobileAndroid_" + stage;
    }
}
