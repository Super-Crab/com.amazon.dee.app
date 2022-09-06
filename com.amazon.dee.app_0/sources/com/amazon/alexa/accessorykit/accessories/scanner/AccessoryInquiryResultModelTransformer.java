package com.amazon.alexa.accessorykit.accessories.scanner;

import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessoryInquiryRecord;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessoryclient.common.api.AccessoryInquiryResult;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.amazon.alexa.accessorykit.accessories.AccessoryModelTransformer;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import java.io.NotSerializableException;
import java.text.ParseException;
/* loaded from: classes6.dex */
public final class AccessoryInquiryResultModelTransformer implements MapModelTransformer<AccessoryInquiryResult> {
    private final MapModelTransformer<AccessoryInquiryRecord> accessoryInquiryRecordModelTransformer;
    private final MapModelTransformer<Accessory> accessoryModelTransformer;
    private final ContainerProvider containerProvider;

    public AccessoryInquiryResultModelTransformer(ContainerProvider containerProvider) {
        Preconditions.notNull(containerProvider, "accessoryInquiryResult");
        this.containerProvider = containerProvider;
        this.accessoryModelTransformer = new AccessoryModelTransformer(containerProvider);
        this.accessoryInquiryRecordModelTransformer = new AccessoryInquiryRecordTransformer(containerProvider);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    /* renamed from: transform */
    public AccessoryInquiryResult mo626transform(ReadableMap readableMap) throws ParseException {
        throw new ParseException("Not yet implemented", 0);
    }

    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    public WritableMap transformToMap(AccessoryInquiryResult accessoryInquiryResult) throws NotSerializableException {
        Preconditions.notNull(accessoryInquiryResult, "accessoryInquiryResult");
        WritableMap map = this.containerProvider.getMap();
        map.putMap(ModelTransformer.KEY_ACCESSORY, this.accessoryModelTransformer.transformToMap(accessoryInquiryResult.getAccessory()));
        map.putMap("record", this.accessoryInquiryRecordModelTransformer.transformToMap(accessoryInquiryResult.getAccessoryInquiryRecord()));
        return map;
    }
}
