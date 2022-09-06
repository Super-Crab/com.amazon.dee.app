package com.amazon.alexa.voice.ui.onedesign.list;

import com.amazon.alexa.voice.ui.onedesign.list.ListCardModel;
import com.amazon.alexa.voice.ui.onedesign.list.ListItem;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.voice.ui.onedesign.list.-$$Lambda$ListPresenter$7NhOpnsc313TF7NkNgBXSVOhoaU  reason: invalid class name */
/* loaded from: classes11.dex */
public final /* synthetic */ class $$Lambda$ListPresenter$7NhOpnsc313TF7NkNgBXSVOhoaU implements Function {
    public static final /* synthetic */ $$Lambda$ListPresenter$7NhOpnsc313TF7NkNgBXSVOhoaU INSTANCE = new $$Lambda$ListPresenter$7NhOpnsc313TF7NkNgBXSVOhoaU();

    private /* synthetic */ $$Lambda$ListPresenter$7NhOpnsc313TF7NkNgBXSVOhoaU() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        ListItem build;
        build = new ListItem.Builder().name(r1.getName()).tag((ListCardModel.ItemModel) obj).build();
        return build;
    }
}
