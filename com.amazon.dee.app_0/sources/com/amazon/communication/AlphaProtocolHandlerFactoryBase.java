package com.amazon.communication;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dp.framework.StreamCodec;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes12.dex */
public abstract class AlphaProtocolHandlerFactoryBase implements ProtocolHandlerFactory {
    private static final String PROTOCOL_NAME_PREFIX = "A:";
    protected final Map<String, String> mLocalParameters;
    protected final StreamCodec mStreamCodec;

    public AlphaProtocolHandlerFactoryBase(StreamCodec streamCodec, Map<String, String> map) {
        this.mStreamCodec = streamCodec;
        this.mLocalParameters = map;
    }

    @Override // com.amazon.communication.ProtocolHandlerFactory
    public Map<String, String> getLocalProtocolParameters(ProtocolHandler protocolHandler) {
        Encoding encodingType;
        Map<String, String> map = this.mLocalParameters;
        if (protocolHandler == null || (encodingType = protocolHandler.getEncodingType()) == null) {
            return map;
        }
        HashMap hashMap = new HashMap(this.mLocalParameters);
        hashMap.put(AlphaProtocolHandlerBase.CHOSEN_ENCODING_KEY, encodingType.name());
        return hashMap;
    }

    @Override // com.amazon.communication.ProtocolHandlerFactory
    public String getProtocolName() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(PROTOCOL_NAME_PREFIX);
        outline107.append(this.mStreamCodec.getName());
        return outline107.toString();
    }
}
