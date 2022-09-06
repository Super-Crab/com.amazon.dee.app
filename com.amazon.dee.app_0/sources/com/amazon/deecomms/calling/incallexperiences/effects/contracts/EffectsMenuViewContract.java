package com.amazon.deecomms.calling.incallexperiences.effects.contracts;

import android.content.Context;
import android.graphics.drawable.Drawable;
import java.util.List;
/* loaded from: classes12.dex */
public interface EffectsMenuViewContract {
    void hideButton();

    void hideMenu();

    void refreshMenu();

    void setPresenter(EffectsMenuPresenterContract effectsMenuPresenterContract);

    void setThumbnails(List<Drawable> list);

    void showButton();

    void showGrayedOutButton();

    void showToast(Context context, int i);

    void showToast(Context context, String str);
}
