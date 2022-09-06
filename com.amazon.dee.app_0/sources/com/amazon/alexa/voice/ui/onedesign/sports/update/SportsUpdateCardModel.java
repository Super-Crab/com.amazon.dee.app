package com.amazon.alexa.voice.ui.onedesign.sports.update;

import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.annotation.UiModel;
import com.amazon.alexa.voice.ui.onedesign.sports.update.game.GameModel;
import java.util.List;
@UiModel(builder = true)
/* loaded from: classes11.dex */
public interface SportsUpdateCardModel extends Parcelable {
    @NonNull
    List<? extends GameModel> getGameList();

    @NonNull
    CharSequence getTitle();
}
