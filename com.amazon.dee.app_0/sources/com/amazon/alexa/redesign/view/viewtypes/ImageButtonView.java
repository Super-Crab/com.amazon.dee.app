package com.amazon.alexa.redesign.view.viewtypes;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.redesign.R;
import com.amazon.alexa.redesign.entity.viewtypes.ImageButtonModel;
import com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel;
import com.amazon.alexa.redesign.utils.Constants;
import com.amazon.alexa.redesign.utils.TestIdUtil;
import com.bumptech.glide.Glide;
import java.util.Map;
/* loaded from: classes10.dex */
public class ImageButtonView extends LinearLayout implements ViewType {
    private Context context;
    @VisibleForTesting
    ImageButton imageButton;

    public ImageButtonView(Context context) {
        super(context);
    }

    @Override // com.amazon.alexa.redesign.view.viewtypes.ViewType
    public void bind(@NonNull ViewTypeModel viewTypeModel, @NonNull Map<String, Object> map, @NonNull String str) {
        if (viewTypeModel instanceof ImageButtonModel) {
            Glide.with(getContext().getApplicationContext()).mo6762load(((ImageButtonModel) viewTypeModel).getButtonImageURL()).into(this.imageButton);
            this.imageButton.setColorFilter(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicIcon80));
            if (Constants.AutomationConstants.isQABuild) {
                TestIdUtil.setTestId(this.imageButton, (String) map.get("contentProvider"), (String) map.get("contentType"), "_button");
            } else if (!str.isEmpty()) {
                this.imageButton.setContentDescription(String.format(this.context.getResources().getString(R.string.amahc_double_tap_to_activate), str));
            } else {
                this.imageButton.setContentDescription(this.context.getResources().getString(R.string.amahc_button_talkback));
            }
        }
    }

    public ImageButtonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ImageButtonView(Context context, ViewGroup viewGroup, LayoutInflater layoutInflater) {
        super(context);
        this.imageButton = (ImageButton) layoutInflater.inflate(R.layout.amahc_image_button, viewGroup, true).findViewById(R.id.image_button);
        this.context = context;
    }
}
