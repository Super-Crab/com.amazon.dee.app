package com.amazon.photos.autosave.internal.preferences;

import com.amazon.photos.uploader.QueueConstraint;
import com.amazon.photos.uploader.QueueManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PreferenceUploadQueueHelper.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0015\u0010\u0004\u001a\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b2\u0015\u0010\t\u001a\u00110\n¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000b¢\u0006\u0002\b\f"}, d2 = {"<anonymous>", "", "p1", "Lcom/amazon/photos/uploader/QueueManager;", "p2", "", "Lkotlin/ParameterName;", "name", "queueName", "p3", "Lcom/amazon/photos/uploader/QueueConstraint;", "constraint", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final /* synthetic */ class PreferenceUploadQueueHelper$handlePreference$3 extends FunctionReference implements Function3<QueueManager, String, QueueConstraint, Unit> {
    public static final PreferenceUploadQueueHelper$handlePreference$3 INSTANCE = new PreferenceUploadQueueHelper$handlePreference$3();

    PreferenceUploadQueueHelper$handlePreference$3() {
        super(3);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public final String getName() {
        return "removeQueueConstraint";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(QueueManager.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final String getSignature() {
        return "removeQueueConstraint(Ljava/lang/String;Lcom/amazon/photos/uploader/QueueConstraint;)V";
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(QueueManager queueManager, String str, QueueConstraint queueConstraint) {
        invoke2(queueManager, str, queueConstraint);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull QueueManager p1, @NotNull String p2, @NotNull QueueConstraint p3) {
        Intrinsics.checkParameterIsNotNull(p1, "p1");
        Intrinsics.checkParameterIsNotNull(p2, "p2");
        Intrinsics.checkParameterIsNotNull(p3, "p3");
        p1.removeQueueConstraint(p2, p3);
    }
}
