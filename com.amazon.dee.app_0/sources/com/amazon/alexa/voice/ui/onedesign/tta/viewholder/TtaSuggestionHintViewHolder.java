package com.amazon.alexa.voice.ui.onedesign.tta.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.amazon.alexa.voice.ui.cards.util.StringUtils;
import com.amazon.alexa.voice.ui.onedesign.constants.Constants;
import com.amazon.alexa.voice.ui.onedesign.rv.RvActionHandler;
import com.amazon.alexa.voice.ui.onedesign.rv.RvDataProvider;
import com.amazon.alexa.voice.ui.onedesign.rv.RvViewHolder;
import com.amazon.alexa.voice.ui.onedesign.tta.items.SuggestionHintTtaItem;
import com.amazon.alexa.voice.ui.onedesign.util.ViewUtils;
import com.amazon.alexa.vox.ui.onedesign.R;
/* loaded from: classes11.dex */
public class TtaSuggestionHintViewHolder extends RvViewHolder {
    private final TextView suggestionHintTitleView;

    public TtaSuggestionHintViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, RvActionHandler rvActionHandler, RvDataProvider rvDataProvider) {
        super(layoutInflater.inflate(R.layout.text_ui_od_tta_suggestion_hint_item, viewGroup, false), rvActionHandler, rvDataProvider);
        this.suggestionHintTitleView = (TextView) this.itemView.findViewById(R.id.suggestion_hint_title);
        this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.viewholder.-$$Lambda$TtaSuggestionHintViewHolder$Yj5TE26PfDileuU-hI0GY-d9fAc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TtaSuggestionHintViewHolder.this.lambda$new$0$TtaSuggestionHintViewHolder(view);
            }
        });
    }

    private CharSequence decorateSuggestion(CharSequence charSequence, String str) {
        return ViewUtils.getStyledSpan(1, StringUtils.quoteText(charSequence), str);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.rv.RvViewHolder
    public void bind(Object obj) {
        super.bind(obj);
        this.suggestionHintTitleView.setText(decorateSuggestion(((SuggestionHintTtaItem) obj).getSuggestionTitle(), String.valueOf(this.dataProvider.get(Constants.Keys.INPUT_TEXT_KEY))));
    }

    public /* synthetic */ void lambda$new$0$TtaSuggestionHintViewHolder(View view) {
        handleAction(1, this.itemView);
    }
}
