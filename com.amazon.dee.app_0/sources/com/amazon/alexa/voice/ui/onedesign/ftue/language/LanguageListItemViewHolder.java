package com.amazon.alexa.voice.ui.onedesign.ftue.language;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.voice.ui.onedesign.ftue.R;
import com.amazon.alexa.voice.ui.onedesign.util.CharSequenceUtils;
import com.amazon.alexa.voice.ui.onedesign.util.TextLocaleUtil;
import java.util.Locale;
/* loaded from: classes11.dex */
public final class LanguageListItemViewHolder extends RecyclerView.ViewHolder {
    private final View itemSelectorView;
    private final TextView subTitleView;
    private final TextView titleView;

    public LanguageListItemViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        super(layoutInflater.inflate(R.layout.voice_ui_od_languages_list_item, viewGroup, false));
        this.titleView = (TextView) this.itemView.findViewById(R.id.itemTitle);
        this.subTitleView = (TextView) this.itemView.findViewById(R.id.itemSubTitle);
        this.itemSelectorView = this.itemView.findViewById(R.id.itemSelectorView);
    }

    public void bind(LanguageListItem languageListItem, final View.OnClickListener onClickListener) {
        Locale locale = new Locale(languageListItem.getLanguage().getLanguage(), languageListItem.getLanguage().getCountry());
        this.titleView.setText(TextLocaleUtil.wrapTextInLocaleSpan(CharSequenceUtils.capitalize(languageListItem.getLanguage().getDisplayLanguage()), locale));
        this.titleView.setTextLocale(locale);
        this.subTitleView.setText(TextLocaleUtil.wrapTextInLocaleSpan(CharSequenceUtils.capitalize(languageListItem.getLanguage().getDisplayCountry()), locale));
        this.subTitleView.setTextLocale(locale);
        this.itemSelectorView.setVisibility(languageListItem.isSelected() ? 0 : 8);
        this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.ftue.language.-$$Lambda$LanguageListItemViewHolder$bWj4Ji_rVXwqeqyjLZ2GK5sabEQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LanguageListItemViewHolder.this.lambda$bind$0$LanguageListItemViewHolder(onClickListener, view);
            }
        });
    }

    public /* synthetic */ void lambda$bind$0$LanguageListItemViewHolder(View.OnClickListener onClickListener, View view) {
        this.itemSelectorView.setVisibility(0);
        onClickListener.onClick(view);
    }
}
