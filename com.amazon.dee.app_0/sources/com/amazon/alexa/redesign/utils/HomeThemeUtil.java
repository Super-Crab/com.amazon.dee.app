package com.amazon.alexa.redesign.utils;

import android.content.Context;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.redesign.R;
/* loaded from: classes10.dex */
public final class HomeThemeUtil {
    private HomeThemeUtil() {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int returnMosaicColor(String str, Context context) {
        char c;
        switch (str.hashCode()) {
            case -1194063784:
                if (str.equals("icon10")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1194063753:
                if (str.equals("icon20")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1194063722:
                if (str.equals("icon30")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1194063691:
                if (str.equals("icon40")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -1194063660:
                if (str.equals("icon50")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -1194063629:
                if (str.equals("icon60")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -1194063598:
                if (str.equals("icon70")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -1194063567:
                if (str.equals("icon80")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -1194063536:
                if (str.equals("icon90")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1638728408:
                if (str.equals("icon100")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 1638728439:
                if (str.equals("icon110")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return ThemeUtil.getColorFromAttribute(context, R.attr.mosaicIcon10);
            case 1:
                return ThemeUtil.getColorFromAttribute(context, R.attr.mosaicIcon20);
            case 2:
                return ThemeUtil.getColorFromAttribute(context, R.attr.mosaicIcon30);
            case 3:
                return ThemeUtil.getColorFromAttribute(context, R.attr.mosaicIcon40);
            case 4:
                return ThemeUtil.getColorFromAttribute(context, R.attr.mosaicIcon50);
            case 5:
                return ThemeUtil.getColorFromAttribute(context, R.attr.mosaicIcon60);
            case 6:
                return ThemeUtil.getColorFromAttribute(context, R.attr.mosaicIcon70);
            case 7:
                return ThemeUtil.getColorFromAttribute(context, R.attr.mosaicIcon80);
            case '\b':
                return ThemeUtil.getColorFromAttribute(context, R.attr.mosaicIcon90);
            case '\t':
                return ThemeUtil.getColorFromAttribute(context, R.attr.mosaicIcon100);
            case '\n':
                return ThemeUtil.getColorFromAttribute(context, R.attr.mosaicIcon110);
            default:
                return ThemeUtil.getColorFromAttribute(context, R.attr.mosaicAction30);
        }
    }
}
