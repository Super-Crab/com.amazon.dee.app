package com.amazon.alexa.cantilever;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.amazon.alexa.logging.Lib;
import com.amazon.alexa.logging.Tag;
import com.amazon.alexa.mosaic.components.ThemeUtil;
/* loaded from: classes6.dex */
public class HelpErrorViewController {
    private static final Tag TAG = LogConfig.TAGGER.tag(HelpErrorViewController.class);
    private final TextView errorSubtitleText;
    private final TextView errorTitleText;
    private final View errorView;
    private final View offlineView;

    public HelpErrorViewController(Context context, View view, View.OnClickListener onClickListener) {
        this.errorView = view.findViewById(R.id.cantilever_no_network_page);
        int textColor = getTextColor(context);
        this.errorTitleText = (TextView) this.errorView.findViewById(R.id.cantilever_error_page_title);
        this.errorTitleText.setTextColor(textColor);
        this.errorSubtitleText = (TextView) this.errorView.findViewById(R.id.cantilever_error_page_subtitle);
        this.errorSubtitleText.setTextColor(textColor);
        this.offlineView = view.findViewById(R.id.banner_text);
        ((TextView) view.findViewById(R.id.cantilever_retry)).setOnClickListener(onClickListener);
    }

    private int getTextColor(Context context) {
        ThemeUtil.setTheme(context);
        return ThemeUtil.getColorFromAttribute(context, R.attr.mosaicNeutral10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setOffline(boolean z) {
        this.offlineView.setVisibility(z ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setVisible(boolean z) {
        this.errorView.setVisibility(z ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setupNoNetworkText() {
        Lib.Log.i(TAG, "Couldn't load due to network error");
        this.errorTitleText.setText(R.string.cantilever_no_network_title);
        this.errorSubtitleText.setText(R.string.cantilever_no_network_subtitle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setupUnableToLoadText() {
        Lib.Log.i(TAG, "Couldn't load due to generic error");
        this.errorTitleText.setText(R.string.cantilever_unable_title);
        this.errorSubtitleText.setText(R.string.cantilever_unable_subtitle);
    }
}
