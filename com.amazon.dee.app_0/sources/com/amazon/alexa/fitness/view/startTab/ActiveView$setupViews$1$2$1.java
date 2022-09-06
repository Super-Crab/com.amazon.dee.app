package com.amazon.alexa.fitness.view.startTab;

import android.view.View;
import com.amazon.alexa.fitness.utils.EventType;
import com.amazon.alexa.fitness.utils.FullScreenUtil;
import com.amazon.alexa.fitness.utils.MetricHelperKt;
import com.amazon.alexa.fitness.utils.NavBarMetrics;
import kotlin.Metadata;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ActiveView.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class ActiveView$setupViews$1$2$1 implements View.OnClickListener {
    public static final ActiveView$setupViews$1$2$1 INSTANCE = new ActiveView$setupViews$1$2$1();

    ActiveView$setupViews$1$2$1() {
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        MetricHelperKt.recordUserInteractionEvent(FullScreenUtil.Companion.getMetricHelper(), NavBarMetrics.Companion.getEXIT_BUTTON(), EventType.TAP);
        FullScreenUtil.Companion.goHome();
    }
}
