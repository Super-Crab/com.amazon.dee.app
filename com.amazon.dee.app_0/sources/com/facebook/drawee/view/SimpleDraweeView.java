package com.facebook.drawee.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.util.AttributeSet;
import androidx.annotation.DrawableRes;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.R;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class SimpleDraweeView extends GenericDraweeView {
    private static Supplier<? extends AbstractDraweeControllerBuilder> sDraweecontrollerbuildersupplier;
    private AbstractDraweeControllerBuilder mControllerBuilder;

    public SimpleDraweeView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
        init(context, null);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        int resourceId;
        try {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("SimpleDraweeView#init");
            }
            if (isInEditMode()) {
                getTopLevelDrawable().setVisible(true, false);
                getTopLevelDrawable().invalidateSelf();
            } else {
                Preconditions.checkNotNull(sDraweecontrollerbuildersupplier, "SimpleDraweeView was not initialized!");
                this.mControllerBuilder = sDraweecontrollerbuildersupplier.mo6895get();
            }
            if (attrs != null) {
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.SimpleDraweeView);
                if (obtainStyledAttributes.hasValue(R.styleable.SimpleDraweeView_actualImageUri)) {
                    setImageURI(Uri.parse(obtainStyledAttributes.getString(R.styleable.SimpleDraweeView_actualImageUri)), (Object) null);
                } else if (obtainStyledAttributes.hasValue(R.styleable.SimpleDraweeView_actualImageResource) && (resourceId = obtainStyledAttributes.getResourceId(R.styleable.SimpleDraweeView_actualImageResource, -1)) != -1) {
                    if (isInEditMode()) {
                        setImageResource(resourceId);
                    } else {
                        setActualImageResource(resourceId);
                    }
                }
                obtainStyledAttributes.recycle();
            }
        } finally {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
        }
    }

    public static void initialize(Supplier<? extends AbstractDraweeControllerBuilder> draweeControllerBuilderSupplier) {
        sDraweecontrollerbuildersupplier = draweeControllerBuilderSupplier;
    }

    public static void shutDown() {
        sDraweecontrollerbuildersupplier = null;
    }

    protected AbstractDraweeControllerBuilder getControllerBuilder() {
        return this.mControllerBuilder;
    }

    public void setActualImageResource(@DrawableRes int resourceId) {
        setActualImageResource(resourceId, null);
    }

    public void setImageRequest(ImageRequest request) {
        setController(this.mControllerBuilder.setImageRequest(request).mo6881setOldController(getController()).mo6879build());
    }

    @Override // com.facebook.drawee.view.DraweeView, android.widget.ImageView
    public void setImageResource(int resId) {
        super.setImageResource(resId);
    }

    @Override // com.facebook.drawee.view.DraweeView, android.widget.ImageView
    public void setImageURI(Uri uri) {
        setImageURI(uri, (Object) null);
    }

    public void setActualImageResource(@DrawableRes int resourceId, @Nullable Object callerContext) {
        setImageURI(UriUtil.getUriForResourceId(resourceId), callerContext);
    }

    public void setImageURI(@Nullable String uriString) {
        setImageURI(uriString, (Object) null);
    }

    public SimpleDraweeView(Context context) {
        super(context);
        init(context, null);
    }

    public void setImageURI(Uri uri, @Nullable Object callerContext) {
        setController(this.mControllerBuilder.mo6880setCallerContext(callerContext).mo6876setUri(uri).mo6881setOldController(getController()).mo6879build());
    }

    public SimpleDraweeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SimpleDraweeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    @TargetApi(21)
    public SimpleDraweeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    public void setImageURI(@Nullable String uriString, @Nullable Object callerContext) {
        setImageURI(uriString != null ? Uri.parse(uriString) : null, callerContext);
    }
}
