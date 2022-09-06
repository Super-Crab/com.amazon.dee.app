package com.amazon.alexa.redesign.view.viewtypes;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.redesign.R;
import com.amazon.alexa.redesign.entity.viewtypes.ImageModel;
import com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel;
import com.amazon.alexa.redesign.utils.Constants;
import com.amazon.alexa.redesign.utils.TestIdUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import java.util.Map;
/* loaded from: classes10.dex */
public class ImageView extends LinearLayout implements ViewType {
    @VisibleForTesting
    android.widget.ImageView imageView;
    @VisibleForTesting
    View placeholder;

    public ImageView(Context context) {
        super(context);
    }

    @Override // com.amazon.alexa.redesign.view.viewtypes.ViewType
    public void bind(ViewTypeModel viewTypeModel, Map<String, Object> map, String str) {
        if (viewTypeModel instanceof ImageModel) {
            Glide.with(getContext().getApplicationContext()).mo6762load(((ImageModel) viewTypeModel).getImageUri()).mo1615apply(new RequestOptions().mo1589format(DecodeFormat.PREFER_RGB_565)).mo1619listener(new RequestListener<Drawable>() { // from class: com.amazon.alexa.redesign.view.viewtypes.ImageView.1
                @Override // com.bumptech.glide.request.RequestListener
                public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                    return false;
                }

                @Override // com.bumptech.glide.request.RequestListener
                public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                    ImageView.this.placeholder.setVisibility(0);
                    return false;
                }
            }).into(this.imageView);
            if (!Constants.AutomationConstants.isQABuild) {
                return;
            }
            TestIdUtil.setTestId(this.imageView, (String) map.get("contentProvider"), (String) map.get("contentType"), "_button");
        }
    }

    public ImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ImageView(Context context, ViewGroup viewGroup, LayoutInflater layoutInflater) {
        super(context);
        View inflate = layoutInflater.inflate(R.layout.amahc_image_view, viewGroup, true);
        this.imageView = (android.widget.ImageView) inflate.findViewById(R.id.image);
        this.placeholder = inflate.findViewById(R.id.place_holder);
    }
}
