package com.amazon.alexa.sendtoapp.activitycard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.view.ContextThemeWrapper;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.font.FontResolver;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.sendtoapp.R;
import com.amazon.alexa.sendtoapp.activitycard.model.Card;
import com.amazon.alexa.sendtoapp.activitycard.model.v1.Icon;
import com.amazon.alexa.sendtoapp.activitycard.model.v1.TintConfig;
import com.amazon.alexa.viewprovider.api.event.EventCapture;
import com.amazon.alexa.viewprovider.api.view.ViewModule;
import com.bumptech.glide.Glide;
/* loaded from: classes10.dex */
public class SendToAppViewModule implements ViewModule {
    private final Card card;
    private final Context context;
    private final EventCapture eventCapture;

    public SendToAppViewModule(Card card, EventCapture eventCapture, Context context) {
        this.card = card;
        this.eventCapture = eventCapture;
        this.context = context;
    }

    private void bind(View view, Card card, Context context) {
        bindIcon((ImageView) view.findViewById(R.id.send_to_app_card_icon), card.getCustomData().getIcon(), context);
        bindTitle((TextView) view.findViewById(R.id.send_to_app_card_title), card.getCustomData().getTitle());
        bindSubtitle((TextView) view.findViewById(R.id.send_to_app_card_subtitle), card.getCustomData().getSubtitle());
    }

    private void bindIcon(ImageView imageView, Icon icon, Context context) {
        if (icon.getTintConfig().equals(TintConfig.CLIENT_THEME)) {
            imageView.setColorFilter(ThemeUtil.getColorFromAttribute(context, R.attr.mosaicIcon100));
        }
        Glide.with(context.getApplicationContext()).mo6762load(icon.getUrl()).into(imageView);
    }

    private void bindSubtitle(TextView textView, String str) {
        textView.setText(str);
        textView.setTypeface(FontResolver.getFont(this.context, Font.AMAZON_EMBER_REGULAR));
    }

    private void bindTitle(TextView textView, String str) {
        textView.setText(str);
        textView.setTypeface(FontResolver.getFont(this.context, Font.AMAZON_EMBER_BOLD));
    }

    private int getCurrentTheme() {
        return R.style.Theme_Mosaic_Jasper;
    }

    @Override // com.amazon.alexa.viewprovider.api.view.ViewModule
    public void cleanUp() {
    }

    @Override // com.amazon.alexa.viewprovider.api.view.ViewModule
    public View getView() {
        View inflate = LayoutInflater.from(new ContextThemeWrapper(this.context, getCurrentTheme())).inflate(R.layout.send_to_app_card, (ViewGroup) null);
        bind(inflate, this.card, this.context);
        inflate.setOnClickListener(new CardClickListener(this.eventCapture, this.card, this.context));
        return inflate;
    }

    @Override // com.amazon.alexa.viewprovider.api.view.ViewModule
    public void prepare() {
    }
}
