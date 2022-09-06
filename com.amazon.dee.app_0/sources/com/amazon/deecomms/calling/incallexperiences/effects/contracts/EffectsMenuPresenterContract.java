package com.amazon.deecomms.calling.incallexperiences.effects.contracts;

import androidx.annotation.Nullable;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.model.EffectIcon;
/* loaded from: classes12.dex */
public interface EffectsMenuPresenterContract {
    boolean areEffectsAvailable();

    boolean isEffectsMenuOpen();

    void onEffectButtonTapped(@Nullable EffectIcon effectIcon);

    void onEffectsAvailable();

    void onEffectsNotAvailable();

    void onMenuButtonTapped();

    boolean shouldShowMenuButton();

    void tearDownMenu();
}
