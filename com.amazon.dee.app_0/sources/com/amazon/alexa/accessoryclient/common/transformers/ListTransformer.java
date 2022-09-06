package com.amazon.alexa.accessoryclient.common.transformers;

import android.os.Bundle;
import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ListTransformer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0002\u0010\u0005J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\t\u001a\u00020\nH\u0016J\u0016\u0010\u000b\u001a\u00020\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/transformers/ListTransformer;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "", "subTransformer", "(Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;)V", "LIST_KEY", "", "fromBundle", "bundle", "Landroid/os/Bundle;", "toBundle", "ts", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ListTransformer<T> implements BundleTransformer<List<? extends T>> {
    private final String LIST_KEY;
    private final BundleTransformer<T> subTransformer;

    public ListTransformer(@NotNull BundleTransformer<T> subTransformer) {
        Intrinsics.checkParameterIsNotNull(subTransformer, "subTransformer");
        this.subTransformer = subTransformer;
        this.LIST_KEY = "LIST_KEY";
    }

    @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
    public /* bridge */ /* synthetic */ Bundle toBundle(Object obj) {
        return toBundle((List) ((List) obj));
    }

    @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
    @NotNull
    /* renamed from: fromBundle  reason: collision with other method in class */
    public List<T> mo568fromBundle(@NotNull Bundle bundle) {
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        Iterable<Bundle> parcelableArrayList = bundle.getParcelableArrayList(this.LIST_KEY);
        if (parcelableArrayList == null) {
            parcelableArrayList = Collections.emptyList();
        }
        Intrinsics.checkExpressionValueIsNotNull(parcelableArrayList, "(bundle.getParcelableArr…ions.emptyList<Bundle>())");
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(parcelableArrayList, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (Bundle bundle2 : parcelableArrayList) {
            BundleTransformer<T> bundleTransformer = this.subTransformer;
            if (bundle2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.os.Bundle");
            }
            arrayList.add(bundleTransformer.mo568fromBundle(bundle2));
        }
        return arrayList;
    }

    @NotNull
    public Bundle toBundle(@NotNull List<? extends T> ts) {
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(ts, "ts");
        Bundle bundle = new Bundle();
        String str = this.LIST_KEY;
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(ts, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (T t : ts) {
            arrayList.add(this.subTransformer.toBundle(t));
        }
        bundle.putParcelableArrayList(str, new ArrayList<>(arrayList));
        return bundle;
    }
}
