package com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup;

import android.os.Build;
import android.os.LocaleList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.voice.ui.onedesign.ftue.R;
import com.amazon.alexa.voice.ui.onedesign.ftue.language.Language;
import com.amazon.alexa.voice.ui.onedesign.util.CharSequenceUtils;
import com.amazon.alexa.voice.ui.onedesign.util.TextLocaleUtil;
import java.util.ArrayList;
import java.util.Locale;
/* loaded from: classes11.dex */
public final class LanguageCombinationListItemViewHolder extends RecyclerView.ViewHolder {
    private static final String LANGUAGE_COMBINATION_SEPARATOR = " / ";
    private final View itemSelectorView;
    private final TextView subTitleView;
    private final TextView titleView;

    public LanguageCombinationListItemViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        super(layoutInflater.inflate(R.layout.voice_ui_od_languages_list_item, viewGroup, false));
        this.titleView = (TextView) this.itemView.findViewById(R.id.itemTitle);
        this.subTitleView = (TextView) this.itemView.findViewById(R.id.itemSubTitle);
        this.itemSelectorView = this.itemView.findViewById(R.id.itemSelectorView);
    }

    public void bind(LanguageCombinationListItem languageCombinationListItem, final View.OnClickListener onClickListener) {
        String str;
        LanguageGroup languageGroup = languageCombinationListItem.getLanguageGroup();
        int size = languageGroup.getLanguages().size();
        ArrayList arrayList = new ArrayList();
        Language primaryLanguage = languageGroup.getPrimaryLanguage();
        arrayList.add(new Locale(primaryLanguage.getLanguage(), primaryLanguage.getCountry()));
        CharSequence capitalize = CharSequenceUtils.capitalize(primaryLanguage.getDisplayLanguage());
        CharSequence capitalize2 = CharSequenceUtils.capitalize(primaryLanguage.getDisplayCountry());
        if (size > 1) {
            Language language = languageGroup.getLanguages().get(1);
            arrayList.add(new Locale(language.getLanguage(), language.getCountry()));
            str = ((Object) capitalize) + LANGUAGE_COMBINATION_SEPARATOR + ((Object) CharSequenceUtils.capitalize(language.getDisplayLanguage()));
            capitalize2 = ((Object) capitalize2) + LANGUAGE_COMBINATION_SEPARATOR + ((Object) CharSequenceUtils.capitalize(language.getDisplayCountry()));
        } else {
            str = capitalize;
        }
        int i = Build.VERSION.SDK_INT;
        LocaleList localeList = new LocaleList((Locale[]) arrayList.toArray(new Locale[arrayList.size()]));
        this.titleView.setText(TextLocaleUtil.wrapTextInLocaleSpan(str, localeList));
        this.titleView.setTextLocales(localeList);
        this.subTitleView.setText(TextLocaleUtil.wrapTextInLocaleSpan(capitalize2, localeList));
        this.subTitleView.setTextLocales(localeList);
        this.itemSelectorView.setVisibility(languageCombinationListItem.isSelected() ? 0 : 8);
        this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.-$$Lambda$LanguageCombinationListItemViewHolder$T7-TBmcdZUTgJb4iu-QyOtl5G4c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LanguageCombinationListItemViewHolder.this.lambda$bind$0$LanguageCombinationListItemViewHolder(onClickListener, view);
            }
        });
    }

    public /* synthetic */ void lambda$bind$0$LanguageCombinationListItemViewHolder(View.OnClickListener onClickListener, View view) {
        this.itemSelectorView.setVisibility(0);
        onClickListener.onClick(view);
    }
}
