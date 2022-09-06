package com.amazon.alexa.redesign.view.viewtypes;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.redesign.R;
import com.amazon.alexa.redesign.entity.viewtypes.HeaderModel;
import com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel;
import com.amazon.alexa.redesign.utils.Constants;
import com.amazon.alexa.redesign.utils.TestIdUtil;
import com.amazon.alexa.redesign.view.templates.TemplateHelperUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
/* loaded from: classes10.dex */
public class HeaderView extends LinearLayout implements ViewType {
    @VisibleForTesting
    TextView titleTextView;

    public HeaderView(Context context) {
        super(context);
    }

    private void styleTitle() {
        this.titleTextView.setTextColor(ThemeUtil.getColorFromAttribute(getContext(), R.attr.mosaicNeutral10));
        TemplateHelperUtil.scaleTextViewWithFontFireOS(this.titleTextView, getContext(), R.integer.amahc_fireos_font_scaling_small_text);
    }

    @Override // com.amazon.alexa.redesign.view.viewtypes.ViewType
    public void bind(ViewTypeModel viewTypeModel, Map<String, Object> map, String str) {
        if (viewTypeModel instanceof HeaderModel) {
            this.titleTextView.setText(((HeaderModel) viewTypeModel).getTitle());
            styleTitle();
        }
        if (Constants.AutomationConstants.isQABuild) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("_Title_");
            outline107.append((Object) this.titleTextView.getText());
            TestIdUtil.setTestId(this.titleTextView, (String) map.get("contentProvider"), (String) map.get("contentType"), outline107.toString());
        }
    }

    public HeaderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public HeaderView(Context context, ViewGroup viewGroup, LayoutInflater layoutInflater) {
        super(context);
        this.titleTextView = (TextView) layoutInflater.inflate(R.layout.amahc_header_view, viewGroup, true).findViewById(R.id.title);
    }
}
