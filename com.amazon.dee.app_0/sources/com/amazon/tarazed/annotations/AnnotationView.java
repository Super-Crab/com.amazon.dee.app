package com.amazon.tarazed.annotations;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import com.amazon.tarazed.annotations.drawables.AnnotationDrawable;
import com.amazon.tarazed.annotations.handlers.AnnotationRemovalHandler;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AnnotationView.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\nJ\u0010\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0012H\u0014J\u0006\u0010\u0013\u001a\u00020\u000eJ\u0006\u0010\u0014\u001a\u00020\u000eR2\u0010\b\u001a&\u0012\f\u0012\n \u000b*\u0004\u0018\u00010\n0\n \u000b*\u0012\u0012\f\u0012\n \u000b*\u0004\u0018\u00010\n0\n\u0018\u00010\f0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/tarazed/annotations/AnnotationView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "(Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Landroid/content/Context;)V", "annotations", "", "Lcom/amazon/tarazed/annotations/drawables/AnnotationDrawable;", "kotlin.jvm.PlatformType", "", "addAnnotation", "", "annotation", "onDraw", "canvas", "Landroid/graphics/Canvas;", AnnotationRemovalHandler.EVENT_TYPE_REMOVE_ALL_ANNOTATIONS, AnnotationRemovalHandler.EVENT_TYPE_REMOVE_LAST_ANNOTATION, "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AnnotationView extends View {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "AnnotationView";
    private HashMap _$_findViewCache;
    private final List<AnnotationDrawable> annotations;
    private final TarazedSessionLogger logger;

    /* compiled from: AnnotationView.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/tarazed/annotations/AnnotationView$Companion;", "", "()V", "TAG", "", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AnnotationView(@NotNull TarazedSessionLogger logger, @NotNull Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.logger = logger;
        this.annotations = Collections.synchronizedList(new ArrayList());
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            this._$_findViewCache.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    public final void addAnnotation(@NotNull AnnotationDrawable annotation) {
        Intrinsics.checkParameterIsNotNull(annotation, "annotation");
        this.annotations.add(annotation);
        TarazedSessionLogger tarazedSessionLogger = this.logger;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("addAnnotation - number of annotations: ");
        outline107.append(this.annotations.size());
        tarazedSessionLogger.d(TAG, outline107.toString());
        postInvalidate();
    }

    @Override // android.view.View
    protected void onDraw(@NotNull Canvas canvas) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        TarazedSessionLogger tarazedSessionLogger = this.logger;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onDraw - number of annotations: ");
        outline107.append(this.annotations.size());
        tarazedSessionLogger.d(TAG, outline107.toString());
        for (AnnotationDrawable annotationDrawable : this.annotations) {
            annotationDrawable.draw(canvas);
        }
    }

    public final void removeAllAnnotations() {
        this.logger.d(TAG, "removeAllAnnotations - clearing paths");
        this.annotations.clear();
        postInvalidate();
    }

    public final void removeLastAnnotation() {
        if (this.annotations.isEmpty()) {
            this.logger.i(TAG, "removeLastAnnotation - no annotations to remove");
            return;
        }
        List<AnnotationDrawable> list = this.annotations;
        list.remove(list.size() - 1);
        TarazedSessionLogger tarazedSessionLogger = this.logger;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("removeLastAnnotation - number of annotations: ");
        outline107.append(this.annotations.size());
        tarazedSessionLogger.d(TAG, outline107.toString());
        postInvalidate();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Deprecated(message = "This constructor is only for Android tools support; do not use.")
    public AnnotationView(@NotNull Context context) {
        this(new TarazedSessionLogger(context), context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        if (isInEditMode()) {
            return;
        }
        throw new Exception("This constructor is only meant to be called by Android tools");
    }
}
