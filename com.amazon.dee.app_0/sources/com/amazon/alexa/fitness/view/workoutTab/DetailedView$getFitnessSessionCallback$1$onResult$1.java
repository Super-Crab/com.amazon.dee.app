package com.amazon.alexa.fitness.view.workoutTab;

import com.amazon.alexa.fitness.utils.FullScreenUtil;
import kotlin.Metadata;
/* compiled from: DetailedView.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes8.dex */
final class DetailedView$getFitnessSessionCallback$1$onResult$1 implements Runnable {
    public static final DetailedView$getFitnessSessionCallback$1$onResult$1 INSTANCE = new DetailedView$getFitnessSessionCallback$1$onResult$1();

    DetailedView$getFitnessSessionCallback$1$onResult$1() {
    }

    @Override // java.lang.Runnable
    public final void run() {
        FullScreenUtil.Companion.goHistoryTab();
    }
}
