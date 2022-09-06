package com.amazon.alexa.accessorykit.accessories.session.system;

import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.ArrayModelTransformer;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import java.io.NotSerializableException;
import java.text.ParseException;
import java.util.List;
/* loaded from: classes6.dex */
public class StringArrayModelTransformer implements ArrayModelTransformer<List<String>> {
    private final ContainerProvider containerProvider;

    public StringArrayModelTransformer(ContainerProvider containerProvider) {
        this.containerProvider = containerProvider;
    }

    @Override // com.amazon.alexa.accessorykit.accessories.ArrayModelTransformer
    /* renamed from: transform  reason: collision with other method in class */
    public List<String> mo597transform(ReadableArray readableArray) throws ParseException {
        throw new ParseException("Not supported", 0);
    }

    @Override // com.amazon.alexa.accessorykit.accessories.ArrayModelTransformer
    public WritableArray transformToArray(List<String> list) throws NotSerializableException {
        WritableArray array = this.containerProvider.getArray();
        for (String str : list) {
            array.pushString(str);
        }
        return array;
    }
}
