package com.amazon.alexa.voice.ui.onedesign.local.search;

import com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel;
import com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchItem;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.voice.ui.onedesign.local.search.-$$Lambda$LocalSearchPresenter$7qYWBsexoS4tyowgpic76sfHB7Y  reason: invalid class name */
/* loaded from: classes11.dex */
public final /* synthetic */ class $$Lambda$LocalSearchPresenter$7qYWBsexoS4tyowgpic76sfHB7Y implements Function {
    public static final /* synthetic */ $$Lambda$LocalSearchPresenter$7qYWBsexoS4tyowgpic76sfHB7Y INSTANCE = new $$Lambda$LocalSearchPresenter$7qYWBsexoS4tyowgpic76sfHB7Y();

    private /* synthetic */ $$Lambda$LocalSearchPresenter$7qYWBsexoS4tyowgpic76sfHB7Y() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        LocalSearchItem build;
        build = new LocalSearchItem.Builder().title(r1.getName()).imageUrl(r1.getImageUrl()).dataSource(r1.getDataSource()).rating(r1.getRating()).starRatingUrl(r1.getStarRatingUrl()).reviewCount(r1.getReviewCount()).spendiness(r1.getSpendiness()).distanceValue(r1.getDistanceValue()).distanceUnit(r1.getDistanceUnit()).categories(r1.getCategories()).address(r1.getAddress()).displayProviderInfo(true).tag((LocalCardModel.BusinessModel) obj).build();
        return build;
    }
}
