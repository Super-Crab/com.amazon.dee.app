package com.amazon.alexa.accessorykit.accessories.session.firmware;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.kota.BinaryMetadata;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import java.text.ParseException;
/* loaded from: classes6.dex */
public class BinaryMetadataModelTransformer implements MapModelTransformer<BinaryMetadata> {
    @VisibleForTesting
    static final String JSON_BINARY_TYPE = "binaryType";
    @VisibleForTesting
    static final String JSON_MD5_SUM = "md5Sum";
    private final ContainerProvider containerProvider;

    public BinaryMetadataModelTransformer(ContainerProvider containerProvider) {
        Preconditions.notNull(containerProvider, "containerProvider");
        this.containerProvider = containerProvider;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    /* renamed from: transform */
    public BinaryMetadata mo626transform(ReadableMap readableMap) throws ParseException {
        throw new ParseException("Not supported", 0);
    }

    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    public WritableMap transformToMap(BinaryMetadata binaryMetadata) {
        WritableMap map = this.containerProvider.getMap();
        map.putString(JSON_BINARY_TYPE, binaryMetadata.getBinaryType());
        map.putString(JSON_MD5_SUM, binaryMetadata.getMd5Sum());
        return map;
    }
}
