package com.amazon.alexa.voice.ui.onedesign.tta.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.amazon.alexa.voice.ui.onedesign.constants.Constants;
import com.amazon.alexa.voice.ui.onedesign.rv.RvActionHandler;
import com.amazon.alexa.voice.ui.onedesign.rv.RvDataProvider;
import com.amazon.alexa.voice.ui.onedesign.rv.RvViewHolder;
import com.amazon.alexa.voice.ui.onedesign.tta.items.SuggestionLinkTtaItem;
import com.amazon.alexa.voice.ui.onedesign.util.TextLocaleUtil;
import com.amazon.alexa.voice.ui.onedesign.util.ViewUtils;
import com.amazon.alexa.vox.ui.onedesign.R;
/* loaded from: classes11.dex */
public class TtaSuggestionLinkViewHolder extends RvViewHolder {
    private final TextView suggestionLinkDescriptionView;
    private final TextView suggestionLinkTitleView;

    public TtaSuggestionLinkViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, RvActionHandler rvActionHandler, RvDataProvider rvDataProvider) {
        super(layoutInflater.inflate(R.layout.text_ui_od_tta_suggestion_link_item, viewGroup, false), rvActionHandler, rvDataProvider);
        this.suggestionLinkTitleView = (TextView) this.itemView.findViewById(R.id.suggestion_link_title);
        this.suggestionLinkDescriptionView = (TextView) this.itemView.findViewById(R.id.suggestion_link_description);
        this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.tta.viewholder.-$$Lambda$TtaSuggestionLinkViewHolder$svMqqG7-KXGJio-U5NGFy9ISRHU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TtaSuggestionLinkViewHolder.this.lambda$new$0$TtaSuggestionLinkViewHolder(view);
            }
        });
    }

    private CharSequence decorateSuggestion(CharSequence charSequence, String str) {
        return ViewUtils.getStyledSpan(1, charSequence, str);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.rv.RvViewHolder
    public void bind(Object obj) {
        super.bind(obj);
        SuggestionLinkTtaItem suggestionLinkTtaItem = (SuggestionLinkTtaItem) obj;
        this.suggestionLinkTitleView.setText(decorateSuggestion(suggestionLinkTtaItem.getSuggestionTitle(), String.valueOf(this.dataProvider.get(Constants.Keys.INPUT_TEXT_KEY))));
        CharSequence suggestionDescription = suggestionLinkTtaItem.getSuggestionDescription();
        if (TextLocaleUtil.isEmpty(suggestionDescription)) {
            this.suggestionLinkDescriptionView.setVisibility(8);
            return;
        }
        this.suggestionLinkDescriptionView.setVisibility(0);
        this.suggestionLinkDescriptionView.setText(suggestionDescription);
    }

    public /* synthetic */ void lambda$new$0$TtaSuggestionLinkViewHolder(View view) {
        handleAction(1, this.itemView);
    }
}
