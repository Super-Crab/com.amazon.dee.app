package com.amazon.alexa.redesign.view.viewtypes;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.redesign.R;
import com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel;
import java.util.Map;
/* loaded from: classes10.dex */
public class BorderedTextButtonView extends TextButtonView {
    public BorderedTextButtonView(Context context) {
        super(context);
    }

    private void styleTextButton() {
        this.textButton.setTextColor(ThemeUtil.getColorFromAttribute(getContext(), ThemeUtil.isLightMode(getContext()) ? R.attr.mosaicBackground : R.attr.mosaicNeutral10));
        this.textButton.setBackgroundColor(ThemeUtil.getColorFromAttribute(getContext(), R.attr.mosaicAction20));
    }

    @Override // com.amazon.alexa.redesign.view.viewtypes.TextButtonView, com.amazon.alexa.redesign.view.viewtypes.ViewType
    public void bind(ViewTypeModel viewTypeModel, Map<String, Object> map, @NonNull String str) {
        super.bind(viewTypeModel, map, str);
        styleTextButton();
    }

    public BorderedTextButtonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BorderedTextButtonView(Context context, ViewGroup viewGroup, LayoutInflater layoutInflater) {
        super(context, viewGroup, layoutInflater);
    }
}
