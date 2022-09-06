package com.amazon.alexa.fitness.view.homecard;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.amazon.alexa.fitness.api.afx.HomeCardViewModel;
import com.amazon.alexa.fitness.ui.R;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.viewprovider.api.view.ViewModule;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessHomeViewModule.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0015H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/amazon/alexa/fitness/view/homecard/FitnessHomeViewModule;", "Lcom/amazon/alexa/viewprovider/api/view/ViewModule;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "homeCardViewModel", "Lcom/amazon/alexa/fitness/api/afx/HomeCardViewModel;", "kotlin.jvm.PlatformType", "imageResourceObserver", "Landroidx/lifecycle/Observer;", "", "mainHandler", "Landroid/os/Handler;", "observers", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "subTitleObserver", "", "titleObserver", "cleanUp", "", "getView", "Landroid/view/View;", "prepare", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FitnessHomeViewModule implements ViewModule {
    private final Context context;
    private final HomeCardViewModel homeCardViewModel;
    private Observer<Integer> imageResourceObserver;
    private final Handler mainHandler;
    private ArrayList<Object> observers;
    private Observer<String> subTitleObserver;
    private Observer<String> titleObserver;

    public FitnessHomeViewModule(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.homeCardViewModel = (HomeCardViewModel) GeneratedOutlineSupport1.outline20(HomeCardViewModel.class);
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.observers = new ArrayList<>();
    }

    @Override // com.amazon.alexa.viewprovider.api.view.ViewModule
    public void cleanUp() {
        this.mainHandler.post(new Runnable() { // from class: com.amazon.alexa.fitness.view.homecard.FitnessHomeViewModule$cleanUp$1
            /* JADX WARN: Code restructure failed: missing block: B:11:0x0021, code lost:
                r1 = r2.this$0.homeCardViewModel;
             */
            /* JADX WARN: Code restructure failed: missing block: B:4:0x0008, code lost:
                r1 = r2.this$0.homeCardViewModel;
             */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final void run() {
                /*
                    r2 = this;
                    com.amazon.alexa.fitness.view.homecard.FitnessHomeViewModule r0 = com.amazon.alexa.fitness.view.homecard.FitnessHomeViewModule.this
                    androidx.lifecycle.Observer r0 = com.amazon.alexa.fitness.view.homecard.FitnessHomeViewModule.access$getTitleObserver$p(r0)
                    if (r0 == 0) goto L19
                    com.amazon.alexa.fitness.view.homecard.FitnessHomeViewModule r1 = com.amazon.alexa.fitness.view.homecard.FitnessHomeViewModule.this
                    com.amazon.alexa.fitness.api.afx.HomeCardViewModel r1 = com.amazon.alexa.fitness.view.homecard.FitnessHomeViewModule.access$getHomeCardViewModel$p(r1)
                    if (r1 == 0) goto L19
                    androidx.lifecycle.LiveData r1 = r1.getTitle()
                    if (r1 == 0) goto L19
                    r1.removeObserver(r0)
                L19:
                    com.amazon.alexa.fitness.view.homecard.FitnessHomeViewModule r0 = com.amazon.alexa.fitness.view.homecard.FitnessHomeViewModule.this
                    androidx.lifecycle.Observer r0 = com.amazon.alexa.fitness.view.homecard.FitnessHomeViewModule.access$getSubTitleObserver$p(r0)
                    if (r0 == 0) goto L32
                    com.amazon.alexa.fitness.view.homecard.FitnessHomeViewModule r1 = com.amazon.alexa.fitness.view.homecard.FitnessHomeViewModule.this
                    com.amazon.alexa.fitness.api.afx.HomeCardViewModel r1 = com.amazon.alexa.fitness.view.homecard.FitnessHomeViewModule.access$getHomeCardViewModel$p(r1)
                    if (r1 == 0) goto L32
                    androidx.lifecycle.LiveData r1 = r1.getSubTitle()
                    if (r1 == 0) goto L32
                    r1.removeObserver(r0)
                L32:
                    com.amazon.alexa.fitness.view.homecard.FitnessHomeViewModule r0 = com.amazon.alexa.fitness.view.homecard.FitnessHomeViewModule.this
                    java.util.ArrayList r0 = com.amazon.alexa.fitness.view.homecard.FitnessHomeViewModule.access$getObservers$p(r0)
                    r0.clear()
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.fitness.view.homecard.FitnessHomeViewModule$cleanUp$1.run():void");
            }
        });
    }

    @Override // com.amazon.alexa.viewprovider.api.view.ViewModule
    @NotNull
    public View getView() {
        ThemeUtil.setTheme(this.context);
        final View view = LayoutInflater.from(this.context).inflate(R.layout.home_card_layout, (ViewGroup) null);
        this.mainHandler.post(new Runnable() { // from class: com.amazon.alexa.fitness.view.homecard.FitnessHomeViewModule$getView$1
            @Override // java.lang.Runnable
            public final void run() {
                HomeCardViewModel homeCardViewModel;
                ArrayList arrayList;
                HomeCardViewModel homeCardViewModel2;
                ArrayList arrayList2;
                LiveData<String> subTitle;
                LiveData<String> title;
                FitnessHomeViewModule fitnessHomeViewModule = FitnessHomeViewModule.this;
                Observer<String> observer = new Observer<String>() { // from class: com.amazon.alexa.fitness.view.homecard.FitnessHomeViewModule$getView$1.1
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(@NotNull String title2) {
                        Intrinsics.checkParameterIsNotNull(title2, "title");
                        TextView titleTextView = (TextView) view.findViewById(R.id.title);
                        Intrinsics.checkExpressionValueIsNotNull(titleTextView, "titleTextView");
                        titleTextView.setText(title2);
                    }
                };
                homeCardViewModel = FitnessHomeViewModule.this.homeCardViewModel;
                if (homeCardViewModel != null && (title = homeCardViewModel.getTitle()) != null) {
                    title.observeForever(observer);
                }
                arrayList = FitnessHomeViewModule.this.observers;
                arrayList.add(observer);
                fitnessHomeViewModule.titleObserver = observer;
                FitnessHomeViewModule fitnessHomeViewModule2 = FitnessHomeViewModule.this;
                Observer<String> observer2 = new Observer<String>() { // from class: com.amazon.alexa.fitness.view.homecard.FitnessHomeViewModule$getView$1.3
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(@NotNull String subTitle2) {
                        Intrinsics.checkParameterIsNotNull(subTitle2, "subTitle");
                        TextView subTitleTextView = (TextView) view.findViewById(R.id.subtitle);
                        Intrinsics.checkExpressionValueIsNotNull(subTitleTextView, "subTitleTextView");
                        subTitleTextView.setText(subTitle2);
                    }
                };
                homeCardViewModel2 = FitnessHomeViewModule.this.homeCardViewModel;
                if (homeCardViewModel2 != null && (subTitle = homeCardViewModel2.getSubTitle()) != null) {
                    subTitle.observeForever(observer2);
                }
                arrayList2 = FitnessHomeViewModule.this.observers;
                arrayList2.add(observer2);
                fitnessHomeViewModule2.subTitleObserver = observer2;
            }
        });
        view.setOnClickListener(FitnessHomeViewModule$getView$2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(view, "view");
        return view;
    }

    @Override // com.amazon.alexa.viewprovider.api.view.ViewModule
    public void prepare() {
    }
}
