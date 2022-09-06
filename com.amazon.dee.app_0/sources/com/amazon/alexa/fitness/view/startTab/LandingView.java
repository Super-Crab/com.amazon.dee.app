package com.amazon.alexa.fitness.view.startTab;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.amazon.alexa.fitness.ui.R;
import com.amazon.alexa.fitness.utils.FullScreenUtil;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: LandingView.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/alexa/fitness/view/startTab/LandingView;", "", "()V", "Companion", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class LandingView {
    public static final Companion Companion = new Companion(null);

    /* compiled from: LandingView.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/fitness/view/startTab/LandingView$Companion;", "", "()V", "getLandingPageView", "Landroid/view/View;", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final View getLandingPageView() {
            Resources resources;
            Configuration configuration;
            ViewGroup container = FullScreenUtil.Companion.getContainer();
            float f = (container == null || (resources = container.getResources()) == null || (configuration = resources.getConfiguration()) == null) ? 0.0f : configuration.fontScale;
            Context context = null;
            if (f > 1) {
                Log.i("AFX-LandingView", "adding landing page view with big font for " + f);
                ViewGroup container2 = FullScreenUtil.Companion.getContainer();
                if (container2 != null) {
                    context = container2.getContext();
                }
                View inflate = LayoutInflater.from(context).inflate(R.layout.landing_page_layout_big_font, FullScreenUtil.Companion.getContainer(), false);
                Intrinsics.checkExpressionValueIsNotNull(inflate, "LayoutInflater.from(Full…eenUtil.container, false)");
                return inflate;
            }
            Log.i("AFX-LandingView", "adding landing page view");
            ViewGroup container3 = FullScreenUtil.Companion.getContainer();
            if (container3 != null) {
                context = container3.getContext();
            }
            View inflate2 = LayoutInflater.from(context).inflate(R.layout.landing_page_layout, FullScreenUtil.Companion.getContainer(), false);
            Intrinsics.checkExpressionValueIsNotNull(inflate2, "LayoutInflater.from(Full…eenUtil.container, false)");
            return inflate2;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
