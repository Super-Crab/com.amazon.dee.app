package com.amazon.alexa.redesign.view.viewtypes;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.font.FontResolver;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.redesign.R;
import com.amazon.alexa.redesign.entity.viewtypes.IconTitleSubtitleModel;
import com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel;
import com.amazon.alexa.redesign.utils.Constants;
import com.amazon.alexa.redesign.utils.HomeThemeUtil;
import com.amazon.alexa.redesign.utils.TestIdUtil;
import com.amazon.alexa.redesign.view.templates.TemplateHelperUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.bumptech.glide.Glide;
import java.util.Map;
/* loaded from: classes10.dex */
public class IconTitleSubtitleView extends LinearLayout implements ViewType {
    private Context context;
    @VisibleForTesting
    android.widget.ImageView imageView;
    @VisibleForTesting
    TextView subtitleTextView;
    protected TextView titleTextView;

    public IconTitleSubtitleView(Context context) {
        super(context);
    }

    private void bindAutomationIds(Map<String, Object> map) {
        if (Constants.AutomationConstants.isQABuild) {
            String str = "";
            String str2 = map.containsKey("contentProvider") ? (String) map.get("contentProvider") : str;
            if (map.containsKey("contentType")) {
                str = (String) map.get("contentType");
            }
            TextView textView = this.titleTextView;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("_Title_");
            outline107.append((Object) this.titleTextView.getText());
            TestIdUtil.setTestId(textView, str2, str, outline107.toString());
            TextView textView2 = this.subtitleTextView;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("_subTitle_");
            outline1072.append((Object) this.subtitleTextView.getText());
            TestIdUtil.setTestId(textView2, str2, str, outline1072.toString());
        }
    }

    private void styleSubtitle() {
        this.subtitleTextView.setTextColor(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicNeutral20));
        this.subtitleTextView.setTypeface(FontResolver.getFont(getContext(), Font.AMAZON_EMBER_REGULAR));
        TemplateHelperUtil.scaleTextViewWithFontFireOS(this.subtitleTextView, this.context, R.integer.amahc_fireos_font_scaling_extra_small_text);
    }

    public void bind(@NonNull ViewTypeModel viewTypeModel, @NonNull Map<String, Object> map, @NonNull String str) {
        if (viewTypeModel instanceof IconTitleSubtitleModel) {
            IconTitleSubtitleModel iconTitleSubtitleModel = (IconTitleSubtitleModel) viewTypeModel;
            bindIcon(iconTitleSubtitleModel);
            bindTitle(iconTitleSubtitleModel);
            bindSubtitle(iconTitleSubtitleModel);
            bindAutomationIds(map);
        }
    }

    @VisibleForTesting
    void bindIcon(IconTitleSubtitleModel iconTitleSubtitleModel) {
        if (iconTitleSubtitleModel.getImageType().equals(Constants.IMAGE_TYPE_ICON)) {
            String imageTheme = iconTitleSubtitleModel.getImageTheme();
            if (imageTheme.isEmpty()) {
                this.imageView.setColorFilter(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicIcon100));
            } else {
                this.imageView.setColorFilter(HomeThemeUtil.returnMosaicColor(imageTheme, this.context));
            }
        }
        Glide.with(this.context.getApplicationContext()).mo6762load(iconTitleSubtitleModel.getIconUrl()).into(this.imageView);
    }

    @VisibleForTesting
    void bindSubtitle(IconTitleSubtitleModel iconTitleSubtitleModel) {
        String subtitle = iconTitleSubtitleModel.getSubtitle();
        if (subtitle.isEmpty()) {
            this.subtitleTextView.setVisibility(8);
            return;
        }
        this.subtitleTextView.setVisibility(0);
        this.subtitleTextView.setText(subtitle);
        styleSubtitle();
    }

    @VisibleForTesting
    void bindTitle(IconTitleSubtitleModel iconTitleSubtitleModel) {
        this.titleTextView.setText(iconTitleSubtitleModel.getTitle());
        this.titleTextView.setTextColor(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicNeutral10));
        if (iconTitleSubtitleModel.getBold()) {
            this.titleTextView.setTypeface(FontResolver.getFont(getContext(), Font.AMAZON_EMBER_BOLD));
        } else {
            this.titleTextView.setTypeface(FontResolver.getFont(getContext(), Font.AMAZON_EMBER_REGULAR));
        }
        if (iconTitleSubtitleModel.getItalicized()) {
            this.titleTextView.setTypeface(FontResolver.getFont(getContext(), Font.AMAZON_BOOKERLY_REGULAR_ITALIC));
        }
        TemplateHelperUtil.scaleTextViewWithFontFireOS(this.titleTextView, this.context, R.integer.amahc_fireos_font_scaling_small_text);
    }

    protected void setTextOnTitleView(TextView textView, String str) {
        textView.setText(str);
    }

    public IconTitleSubtitleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public IconTitleSubtitleView(Context context, ViewGroup viewGroup, LayoutInflater layoutInflater) {
        super(context);
        View inflate = layoutInflater.inflate(R.layout.amahc_icon_title_subtitle_view, viewGroup, true);
        this.imageView = (android.widget.ImageView) inflate.findViewById(R.id.icon);
        this.titleTextView = (TextView) inflate.findViewById(R.id.title);
        this.subtitleTextView = (TextView) inflate.findViewById(R.id.subtitle);
        this.context = context;
    }
}
