package com.amazon.alexa.mosaic.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.mosaic.components.ThemeUtil;
/* loaded from: classes9.dex */
public class ListItemLink extends ListItem {
    private static final String TAG = ListItemLink.class.getSimpleName();
    protected TextView linkTextView;

    public ListItemLink(Context context) {
        this(context, null);
    }

    @Override // com.amazon.alexa.mosaic.view.ListItem
    void inflateLayout() {
        ViewGroup.inflate(getContext(), R.layout.mosaic_list_item_link, this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.mosaic.view.ListItem, android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        setLinkClickable(this.clickable);
        setLinkText(this.linkText);
        ViewUtils.setMaxLines(this, R.id.primaryText, this.primaryTextMaxLines);
        ViewUtils.setMaxLines(this, R.id.secondaryText, this.secondaryTextMaxLines);
    }

    public void setLinkClickable(boolean z) {
        this.clickable = z;
        this.linkTextView = (TextView) findViewById(R.id.linkText);
        TextView textView = this.linkTextView;
        if (textView == null) {
            LogUtils.e(TAG, "Null link TextView");
        } else if (z) {
            textView.setTextColor(ThemeUtil.getColorFromAttribute(getContext(), R.attr.mosaicAction10));
        } else {
            textView.setTextColor(ThemeUtil.getColorFromAttribute(getContext(), R.attr.mosaicNeutral10));
        }
    }

    public void setLinkText(CharSequence charSequence) {
        String str = TAG;
        LogUtils.d(str, "Setting link text to: \"" + ((Object) charSequence) + EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
        if (charSequence != null) {
            setLinkText(charSequence.toString());
        } else {
            LogUtils.e(TAG, "Cannot update link text with null CharSequence");
        }
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.linkTextView = (TextView) findViewById(R.id.linkText);
        if (this.linkTextView != null && onClickListener != null) {
            setLinkClickable(true);
            this.linkTextView.setOnClickListener(onClickListener);
            return;
        }
        LogUtils.e(TAG, "linkTextView is null");
        setLinkClickable(false);
    }

    public ListItemLink(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ListItemLink(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setLinkText(String str) {
        this.linkTextView = (TextView) findViewById(R.id.linkText);
        TextView textView = this.linkTextView;
        if (textView == null) {
            LogUtils.e(TAG, "linkTextView is null");
        } else if (str != null) {
            textView.setText(str);
            this.linkTextView.setVisibility(0);
        } else {
            textView.setVisibility(8);
        }
    }
}
