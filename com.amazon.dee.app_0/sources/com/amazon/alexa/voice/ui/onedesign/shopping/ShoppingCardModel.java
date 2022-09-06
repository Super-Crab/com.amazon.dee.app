package com.amazon.alexa.voice.ui.onedesign.shopping;

import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.annotation.UiModel;
import java.util.List;
@UiModel(builder = true)
/* loaded from: classes11.dex */
public interface ShoppingCardModel extends Parcelable {
    @NonNull
    List<? extends ProductModel> getProductList();
}
