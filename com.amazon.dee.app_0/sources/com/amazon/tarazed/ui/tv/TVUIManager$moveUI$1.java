package com.amazon.tarazed.ui.tv;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.amazon.tarazed.R;
import com.amazon.tarazed.ui.tv.TVUIManager;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TVUIManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.ui.tv.TVUIManager$moveUI$1", f = "TVUIManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes13.dex */
public final class TVUIManager$moveUI$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    private CoroutineScope p$;
    final /* synthetic */ TVUIManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TVUIManager$moveUI$1(TVUIManager tVUIManager, Continuation continuation) {
        super(2, continuation);
        this.this$0 = tVUIManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TVUIManager$moveUI$1 tVUIManager$moveUI$1 = new TVUIManager$moveUI$1(this.this$0, completion);
        tVUIManager$moveUI$1.p$ = (CoroutineScope) obj;
        return tVUIManager$moveUI$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public final Object mo12248invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TVUIManager$moveUI$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        int[][] iArr;
        View view;
        View view2;
        View view3;
        View view4;
        float[] fArr;
        TVUIManager.Companion unused;
        TVUIManager.Companion unused2;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            TVUIManager tVUIManager = this.this$0;
            int uiLocation$TarazedAndroidLibrary_release = tVUIManager.getUiLocation$TarazedAndroidLibrary_release() + 1;
            unused = TVUIManager.Companion;
            iArr = TVUIManager.UI_ALIGNMENTS;
            tVUIManager.setUiLocation$TarazedAndroidLibrary_release(uiLocation$TarazedAndroidLibrary_release % iArr.length);
            view = this.this$0.tvUIView;
            ViewGroup.LayoutParams layoutParams = null;
            FrameLayout frameLayout = view != null ? (FrameLayout) view.findViewById(R.id.virtual_remote_container) : null;
            view2 = this.this$0.tvUIView;
            ImageView imageView = view2 != null ? (ImageView) view2.findViewById(R.id.tv_scrim) : null;
            view3 = this.this$0.tvUIView;
            RelativeLayout relativeLayout = view3 != null ? (RelativeLayout) view3.findViewById(R.id.tv_ui) : null;
            view4 = this.this$0.tvUIView;
            LinearLayout linearLayout = view4 != null ? (LinearLayout) view4.findViewById(R.id.hold_for_options) : null;
            ViewGroup.LayoutParams layoutParams2 = frameLayout != null ? frameLayout.getLayoutParams() : null;
            if (layoutParams2 != null) {
                RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) layoutParams2;
                ViewGroup.LayoutParams layoutParams4 = imageView != null ? imageView.getLayoutParams() : null;
                if (layoutParams4 != null) {
                    RelativeLayout.LayoutParams layoutParams5 = (RelativeLayout.LayoutParams) layoutParams4;
                    ViewGroup.LayoutParams layoutParams6 = relativeLayout != null ? relativeLayout.getLayoutParams() : null;
                    if (layoutParams6 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
                    }
                    RelativeLayout.LayoutParams layoutParams7 = (RelativeLayout.LayoutParams) layoutParams6;
                    if (linearLayout != null) {
                        layoutParams = linearLayout.getLayoutParams();
                    }
                    if (layoutParams != null) {
                        RelativeLayout.LayoutParams layoutParams8 = (RelativeLayout.LayoutParams) layoutParams;
                        unused2 = TVUIManager.Companion;
                        fArr = TVUIManager.SCRIM_ROTATIONS;
                        imageView.setRotation(fArr[this.this$0.getUiLocation$TarazedAndroidLibrary_release()]);
                        this.this$0.setAlignment(layoutParams5);
                        this.this$0.setAlignment(layoutParams7);
                        int uiLocation$TarazedAndroidLibrary_release2 = this.this$0.getUiLocation$TarazedAndroidLibrary_release();
                        if (uiLocation$TarazedAndroidLibrary_release2 == 0) {
                            layoutParams3.removeRule(3);
                            layoutParams8.addRule(3, R.id.virtual_remote_container);
                        } else if (uiLocation$TarazedAndroidLibrary_release2 == 1) {
                            layoutParams3.removeRule(3);
                            layoutParams8.addRule(3, R.id.virtual_remote_container);
                        } else if (uiLocation$TarazedAndroidLibrary_release2 == 2) {
                            layoutParams3.addRule(3, R.id.hold_for_options);
                            layoutParams8.removeRule(3);
                        } else if (uiLocation$TarazedAndroidLibrary_release2 == 3) {
                            layoutParams3.addRule(3, R.id.hold_for_options);
                            layoutParams8.removeRule(3);
                        }
                        frameLayout.requestLayout();
                        return Unit.INSTANCE;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
                }
                throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
            }
            throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
