package com.amazon.alexa.accessorykit.accessories;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import java.io.NotSerializableException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes6.dex */
public class ArrayModelTransformerHelper<T> implements ArrayModelTransformer<List<T>> {
    private final ContainerProvider containerProvider;
    private final MapModelTransformer<T> transformer;

    public ArrayModelTransformerHelper(ContainerProvider containerProvider, MapModelTransformer<T> mapModelTransformer) {
        Preconditions.notNull(containerProvider, "containerProvider");
        Preconditions.notNull(mapModelTransformer, "transformer");
        this.containerProvider = containerProvider;
        this.transformer = mapModelTransformer;
    }

    @Override // com.amazon.alexa.accessorykit.accessories.ArrayModelTransformer
    public /* bridge */ /* synthetic */ WritableArray transformToArray(Object obj) throws NotSerializableException {
        return transformToArray((List) ((List) obj));
    }

    @Override // com.amazon.alexa.accessorykit.accessories.ArrayModelTransformer
    /* renamed from: transform  reason: collision with other method in class */
    public List<T> mo597transform(ReadableArray readableArray) throws ParseException {
        Preconditions.notNull(readableArray, "array");
        ArrayList arrayList = new ArrayList(readableArray.size());
        for (int i = 0; i < readableArray.size(); i++) {
            arrayList.add(this.transformer.mo626transform(readableArray.mo6944getMap(i)));
        }
        return arrayList;
    }

    public WritableArray transformToArray(List<T> list) throws NotSerializableException {
        Preconditions.notNull(list, "list");
        WritableArray array = this.containerProvider.getArray();
        for (T t : list) {
            array.pushMap(this.transformer.transformToMap(t));
        }
        return array;
    }
}
