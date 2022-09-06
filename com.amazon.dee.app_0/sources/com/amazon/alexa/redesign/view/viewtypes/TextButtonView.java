package com.amazon.alexa.redesign.view.viewtypes;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.font.FontResolver;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.redesign.R;
import com.amazon.alexa.redesign.entity.viewtypes.TextButtonModel;
import com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel;
import com.amazon.alexa.redesign.utils.Constants;
import com.amazon.alexa.redesign.utils.TestIdUtil;
import com.amazon.alexa.redesign.view.templates.TemplateHelperUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
/* loaded from: classes10.dex */
public class TextButtonView extends LinearLayout implements ViewType {
    protected Button textButton;
    protected LinearLayout textButtonLinearLayout;

    public TextButtonView(Context context) {
        super(context);
    }

    private void styleTextButton() {
        this.textButton.setTextColor(ThemeUtil.getColorFromAttribute(getContext(), R.attr.mosaicAction10));
        this.textButton.setBackgroundColor(0);
        this.textButton.setTypeface(FontResolver.getFont(getContext(), Font.AMAZON_EMBER_BOLD));
        this.textButton.setGravity(17);
        this.textButton.setLetterSpacing(0.05f);
        TemplateHelperUtil.scaleTextViewWithFontFireOS(this.textButton, getContext(), R.integer.amahc_fireos_font_scaling_button);
    }

    public void bind(ViewTypeModel viewTypeModel, Map<String, Object> map, String str) {
        if (viewTypeModel instanceof TextButtonModel) {
            this.textButton.setText(((TextButtonModel) viewTypeModel).getButtonText());
            styleTextButton();
            this.textButtonLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            if (!Constants.AutomationConstants.isQABuild) {
                return;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("_");
            outline107.append((Object) this.textButton.getText());
            outline107.append("_button");
            TestIdUtil.setTestId(this.textButton, (String) map.get("contentProvider"), (String) map.get("contentType"), outline107.toString());
        }
    }

    public TextButtonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TextButtonView(Context context, ViewGroup viewGroup, LayoutInflater layoutInflater) {
        super(context);
        View inflate = layoutInflater.inflate(R.layout.amahc_text_button, viewGroup, true);
        this.textButton = (Button) inflate.findViewById(R.id.text_button);
        this.textButtonLinearLayout = (LinearLayout) inflate.findViewById(R.id.text_button_linear_layout);
    }
}
