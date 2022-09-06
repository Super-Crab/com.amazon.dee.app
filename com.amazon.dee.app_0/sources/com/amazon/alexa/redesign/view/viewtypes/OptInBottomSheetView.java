package com.amazon.alexa.redesign.view.viewtypes;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.font.FontResolver;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.redesign.HomeContract;
import com.amazon.alexa.redesign.R;
import com.amazon.alexa.redesign.actions.Action;
import com.amazon.alexa.redesign.entity.viewtypes.OptInBottomSheetModel;
import com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel;
import com.amazon.alexa.redesign.view.templates.TemplateHelperUtil;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import java.util.Map;
/* loaded from: classes10.dex */
public class OptInBottomSheetView extends BottomSheetDialogFragment {
    String accessibilityLabel;
    TextView bodyTextView;
    LinearLayout borderedTextButtonLinearLayout;
    BorderedTextButtonView borderedTextButtonView;
    Context context;
    TextView headerTextView;
    android.widget.ImageView imageView;
    Map<String, Object> metricData;
    OptInBottomSheetModel model;
    HomeContract.Presenter presenter;
    TextView subHeaderTextView;
    LinearLayout textButtonLinearLayout;
    TextButtonView textButtonView;

    public OptInBottomSheetView(HomeContract.Presenter presenter, ViewTypeModel viewTypeModel, Map<String, Object> map, String str) {
        this.presenter = presenter;
        if (viewTypeModel instanceof OptInBottomSheetModel) {
            this.model = (OptInBottomSheetModel) viewTypeModel;
        }
        this.metricData = map;
        this.accessibilityLabel = str;
    }

    private void bindBodyText(OptInBottomSheetModel optInBottomSheetModel) {
        this.bodyTextView.setText(optInBottomSheetModel.getBodyText());
        this.bodyTextView.setTextColor(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicNeutral20));
        this.bodyTextView.setTypeface(FontResolver.getFont(this.context, Font.AMAZON_EMBER_LIGHT));
        TemplateHelperUtil.scaleTextViewWithFontFireOS(this.bodyTextView, this.context, R.integer.amahc_fireos_font_scaling_extra_small_text);
    }

    private void bindButtons(OptInBottomSheetModel optInBottomSheetModel, Map<String, Object> map, String str) {
        ViewTypeModel viewTypeModel = optInBottomSheetModel.getButtons().get(0);
        ViewTypeModel viewTypeModel2 = optInBottomSheetModel.getButtons().get(1);
        this.textButtonView.bind(viewTypeModel, map, str);
        this.borderedTextButtonView.bind(viewTypeModel2, map, str);
        final Action action = viewTypeModel.getAction();
        final Action action2 = viewTypeModel2.getAction();
        this.textButtonLinearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.redesign.view.viewtypes.-$$Lambda$OptInBottomSheetView$9TjpYG84PVQHFB7TuVazsxZ-AQY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OptInBottomSheetView.this.lambda$bindButtons$0$OptInBottomSheetView(action, view);
            }
        });
        this.borderedTextButtonLinearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.redesign.view.viewtypes.-$$Lambda$OptInBottomSheetView$4aesVEO0OdxuAPPUxm4bPdpQLL4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OptInBottomSheetView.this.lambda$bindButtons$1$OptInBottomSheetView(action2, view);
            }
        });
    }

    private void bindHeader(OptInBottomSheetModel optInBottomSheetModel) {
        this.headerTextView.setText(optInBottomSheetModel.getHeader());
        this.headerTextView.setTextColor(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicNeutral10));
        this.headerTextView.setTypeface(FontResolver.getFont(this.context, Font.AMAZON_EMBER_BOLD));
        TemplateHelperUtil.scaleTextViewWithFontFireOS(this.headerTextView, this.context, R.integer.amahc_fireos_font_scaling_large_text);
    }

    private void bindImage(OptInBottomSheetModel optInBottomSheetModel) {
        this.imageView.setColorFilter(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicIcon100));
        Glide.with(this.context.getApplicationContext()).mo6762load(optInBottomSheetModel.getImageUrl()).into(this.imageView);
    }

    private void bindSubHeader(OptInBottomSheetModel optInBottomSheetModel) {
        this.subHeaderTextView.setText(optInBottomSheetModel.getSubHeader());
        this.subHeaderTextView.setTextColor(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicNeutral20));
        this.subHeaderTextView.setTypeface(FontResolver.getFont(this.context, Font.AMAZON_EMBER_REGULAR));
        TemplateHelperUtil.scaleTextViewWithFontFireOS(this.subHeaderTextView, this.context, R.integer.amahc_fireos_font_scaling_small_text);
    }

    public /* synthetic */ void lambda$bindButtons$0$OptInBottomSheetView(Action action, View view) {
        action.execute();
        dismiss();
    }

    public /* synthetic */ void lambda$bindButtons$1$OptInBottomSheetView(Action action, View view) {
        action.execute();
        dismiss();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.amahc_opt_in_bottom_sheet_view, viewGroup, false);
        this.imageView = (android.widget.ImageView) inflate.findViewById(R.id.bottom_sheet_icon);
        bindImage(this.model);
        this.headerTextView = (TextView) inflate.findViewById(R.id.header);
        bindHeader(this.model);
        this.subHeaderTextView = (TextView) inflate.findViewById(R.id.sub_header);
        bindSubHeader(this.model);
        this.bodyTextView = (TextView) inflate.findViewById(R.id.body_text);
        bindBodyText(this.model);
        this.textButtonLinearLayout = (LinearLayout) inflate.findViewById(R.id.button1);
        this.borderedTextButtonLinearLayout = (LinearLayout) inflate.findViewById(R.id.button2);
        this.textButtonView = new TextButtonView(this.context, this.textButtonLinearLayout, layoutInflater);
        this.borderedTextButtonView = new BorderedTextButtonView(this.context, this.borderedTextButtonLinearLayout, layoutInflater);
        bindButtons(this.model, this.metricData, this.accessibilityLabel);
        return inflate;
    }
}
