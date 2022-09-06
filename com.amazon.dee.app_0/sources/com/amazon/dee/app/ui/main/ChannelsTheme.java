package com.amazon.dee.app.ui.main;

import android.content.Context;
import android.content.res.ColorStateList;
import androidx.appcompat.content.res.AppCompatResources;
import com.amazon.dee.app.R;
/* loaded from: classes12.dex */
public class ChannelsTheme implements AlexaTheme {
    private Context context;

    public ChannelsTheme(Context context) {
        this.context = context;
    }

    @Override // com.amazon.dee.app.ui.main.AlexaTheme
    public int getBackgroundColor() {
        return this.context.getResources().getColor(R.color.default_rn_background_channels);
    }

    @Override // com.amazon.dee.app.ui.main.AlexaTheme
    public int getContrastColor() {
        return this.context.getResources().getColor(R.color.white);
    }

    @Override // com.amazon.dee.app.ui.main.AlexaTheme
    public int getNavigationBarBackgroundColor() {
        return getBackgroundColor();
    }

    @Override // com.amazon.dee.app.ui.main.AlexaTheme
    public int getNotificationIconColor() {
        return this.context.getResources().getColor(R.color.electric_blue);
    }

    @Override // com.amazon.dee.app.ui.main.AlexaTheme
    public int getSystemUiVisibility() {
        return 0;
    }

    @Override // com.amazon.dee.app.ui.main.AlexaTheme
    public ColorStateList getTabIconColor() {
        return AppCompatResources.getColorStateList(this.context, R.color.ic_main_tab_color);
    }

    @Override // com.amazon.dee.app.ui.main.AlexaTheme
    public int getTabSelectionBarColor() {
        return getContrastColor();
    }

    @Override // com.amazon.dee.app.ui.main.AlexaTheme
    public ColorStateList getTabTextColor() {
        return AppCompatResources.getColorStateList(this.context, R.color.ic_main_tab_text_color);
    }
}
