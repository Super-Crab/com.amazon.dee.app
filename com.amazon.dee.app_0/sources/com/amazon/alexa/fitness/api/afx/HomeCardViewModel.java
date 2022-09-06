package com.amazon.alexa.fitness.api.afx;

import androidx.lifecycle.LiveData;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: HomeCardViewModel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H&J\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H&R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/fitness/api/afx/HomeCardViewModel;", "", "subTitle", "Landroidx/lifecycle/LiveData;", "", "getSubTitle", "()Landroidx/lifecycle/LiveData;", "title", "getTitle", "setSubTitle", "", "value", "setTitle", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface HomeCardViewModel {
    @NotNull
    LiveData<String> getSubTitle();

    @NotNull
    LiveData<String> getTitle();

    void setSubTitle(@NotNull String str);

    void setTitle(@NotNull String str);
}
