package com.amazon.alexa.voice.ui.onedesign.list;

import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.annotation.UiModel;
import java.util.List;
@UiModel(builder = true)
/* loaded from: classes11.dex */
public interface ListCardModel extends Parcelable {

    @UiModel(builder = true)
    /* loaded from: classes11.dex */
    public interface ItemModel extends Parcelable {
        @NonNull
        CharSequence getName();
    }

    @NonNull
    List<? extends ItemModel> getItemList();

    @NonNull
    String getListType();
}
