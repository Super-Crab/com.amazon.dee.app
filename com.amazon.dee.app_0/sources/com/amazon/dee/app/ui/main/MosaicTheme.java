package com.amazon.dee.app.ui.main;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.dee.app.R;
import com.amazon.dee.app.ui.util.UiUtils;
/* loaded from: classes12.dex */
public class MosaicTheme implements AlexaTheme {
    private Context context;
    private final boolean isJasperTheme;
    private int[][] tabStates;

    public MosaicTheme(Context context) {
        this.context = context;
        int theme = getTheme();
        context.setTheme(theme);
        this.isJasperTheme = theme == 2132017808;
    }

    private int getTheme() {
        return 2132017808;
    }

    @Override // com.amazon.dee.app.ui.main.AlexaTheme
    public int getBackgroundColor() {
        return ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicBackground);
    }

    @Override // com.amazon.dee.app.ui.main.AlexaTheme
    public int getContrastColor() {
        return ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicNeutral10);
    }

    @Override // com.amazon.dee.app.ui.main.AlexaTheme
    public int getNavigationBarBackgroundColor() {
        return getBackgroundColor();
    }

    @Override // com.amazon.dee.app.ui.main.AlexaTheme
    public int getNotificationIconColor() {
        return ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicStatus20);
    }

    @Override // com.amazon.dee.app.ui.main.AlexaTheme
    public int getSystemUiVisibility() {
        if (!this.isJasperTheme || !UiUtils.isLightMode(this.context)) {
            return 0;
        }
        int i = Build.VERSION.SDK_INT;
        return 8208;
    }

    @Override // com.amazon.dee.app.ui.main.AlexaTheme
    public ColorStateList getTabIconColor() {
        if (this.tabStates == null) {
            this.tabStates = new int[][]{new int[]{16843518}, new int[]{16842913}, new int[]{-16842913}};
        }
        return new ColorStateList(this.tabStates, new int[]{ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicNeutral30), ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicAction40), ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicNeutral30)});
    }

    @Override // com.amazon.dee.app.ui.main.AlexaTheme
    public int getTabSelectionBarColor() {
        return ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicAction40);
    }

    @Override // com.amazon.dee.app.ui.main.AlexaTheme
    public ColorStateList getTabTextColor() {
        if (this.tabStates == null) {
            this.tabStates = new int[][]{new int[]{16843518}, new int[]{16842913}, new int[]{-16842913}};
        }
        return new ColorStateList(this.tabStates, new int[]{ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicNeutral30), ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicAction40), ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicNeutral30)});
    }
}
