package com.facebook.react.views.text;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.notificationpublisher.providers.BaseTemplateProvider;
import com.facebook.react.bridge.ReadableArray;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.ArrayList;
/* loaded from: classes2.dex */
public class ReactTypefaceUtils {
    public static final int UNSET = -1;

    public static Typeface applyStyles(@Nullable Typeface typeface, int i, int i2, @Nullable String str, AssetManager assetManager) {
        int i3 = 0;
        int style = typeface == null ? 0 : typeface.getStyle();
        if (i2 == 1 || ((style & 1) != 0 && i2 == -1)) {
            i3 = 1;
        }
        if (i == 2 || ((style & 2) != 0 && i == -1)) {
            i3 |= 2;
        }
        if (str != null) {
            typeface = ReactFontManager.getInstance().getTypeface(str, i3, i2, assetManager);
        } else if (typeface != null) {
            typeface = Typeface.create(typeface, i3);
        }
        return typeface != null ? typeface : Typeface.defaultFromStyle(i3);
    }

    public static int parseFontStyle(@Nullable String str) {
        if (TtmlNode.ITALIC.equals(str)) {
            return 2;
        }
        return BaseTemplateProvider.NON_INVITATION_KEY.equals(str) ? 0 : -1;
    }

    @Nullable
    public static String parseFontVariant(@Nullable ReadableArray readableArray) {
        if (readableArray == null || readableArray.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < readableArray.size(); i++) {
            String string = readableArray.getString(i);
            if (string != null) {
                char c = 65535;
                switch (string.hashCode()) {
                    case -1195362251:
                        if (string.equals("proportional-nums")) {
                            c = 4;
                            break;
                        }
                        break;
                    case -1061392823:
                        if (string.equals("lining-nums")) {
                            c = 2;
                            break;
                        }
                        break;
                    case -771984547:
                        if (string.equals("tabular-nums")) {
                            c = 3;
                            break;
                        }
                        break;
                    case -659678800:
                        if (string.equals("oldstyle-nums")) {
                            c = 1;
                            break;
                        }
                        break;
                    case 1183323111:
                        if (string.equals("small-caps")) {
                            c = 0;
                            break;
                        }
                        break;
                }
                if (c == 0) {
                    arrayList.add("'smcp'");
                } else if (c == 1) {
                    arrayList.add("'onum'");
                } else if (c == 2) {
                    arrayList.add("'lnum'");
                } else if (c == 3) {
                    arrayList.add("'tnum'");
                } else if (c == 4) {
                    arrayList.add("'pnum'");
                }
            }
        }
        return TextUtils.join(", ", arrayList);
    }

    public static int parseFontWeight(@Nullable String str) {
        int parseNumericFontWeight = str != null ? parseNumericFontWeight(str) : -1;
        if (parseNumericFontWeight == -1) {
            parseNumericFontWeight = 0;
        }
        if (parseNumericFontWeight == 700 || TtmlNode.BOLD.equals(str)) {
            return 1;
        }
        if (parseNumericFontWeight != 400 && !BaseTemplateProvider.NON_INVITATION_KEY.equals(str)) {
            return parseNumericFontWeight;
        }
        return 0;
    }

    private static int parseNumericFontWeight(String str) {
        if (str.length() != 3 || !str.endsWith("00") || str.charAt(0) > '9' || str.charAt(0) < '1') {
            return -1;
        }
        return (str.charAt(0) - '0') * 100;
    }
}
