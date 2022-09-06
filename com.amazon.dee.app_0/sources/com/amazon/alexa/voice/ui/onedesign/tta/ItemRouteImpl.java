package com.amazon.alexa.voice.ui.onedesign.tta;

import com.amazon.alexa.voice.ui.tta.search.ActionType;
import com.amazon.alexa.voice.ui.tta.search.ItemRoute;
import com.google.auto.value.AutoValue;
import java.util.Locale;
@AutoValue
/* loaded from: classes11.dex */
public abstract class ItemRouteImpl implements ItemRoute {
    private static final String APP_PAGE = "app_page";
    private static final String EXTERNAL_LINK = "external_link";

    public static ItemRouteImpl create(String str) {
        return new AutoValue_ItemRouteImpl(str, ActionType.IN_APP);
    }

    private static ActionType mapRouteType(String str) {
        char c;
        String lowerCase = str.toLowerCase(Locale.ROOT);
        int hashCode = lowerCase.hashCode();
        if (hashCode != -4084754) {
            if (hashCode == 1167707629 && lowerCase.equals(APP_PAGE)) {
                c = 0;
            }
            c = 65535;
        } else {
            if (lowerCase.equals(EXTERNAL_LINK)) {
                c = 1;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c != 1) {
                return ActionType.UNKNOWN;
            }
            return ActionType.WEB;
        }
        return ActionType.IN_APP;
    }

    @Override // com.amazon.alexa.voice.ui.tta.search.ItemRoute
    public abstract String getRouteLink();

    @Override // com.amazon.alexa.voice.ui.tta.search.ItemRoute
    public abstract ActionType getRouteType();

    public static ItemRouteImpl create(String str, String str2) {
        return new AutoValue_ItemRouteImpl(str, mapRouteType(str2));
    }
}
