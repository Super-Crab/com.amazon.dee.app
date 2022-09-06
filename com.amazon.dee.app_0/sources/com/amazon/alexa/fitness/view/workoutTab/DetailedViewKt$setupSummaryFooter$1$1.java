package com.amazon.alexa.fitness.view.workoutTab;

import android.view.View;
import com.amazon.alexa.fitness.utils.FullScreenUtil;
import kotlin.Metadata;
/* compiled from: DetailedView.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes8.dex */
final class DetailedViewKt$setupSummaryFooter$1$1 implements View.OnClickListener {
    public static final DetailedViewKt$setupSummaryFooter$1$1 INSTANCE = new DetailedViewKt$setupSummaryFooter$1$1();

    DetailedViewKt$setupSummaryFooter$1$1() {
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        FullScreenUtil.Companion.goHistoryTab();
    }
}
