package com.amazon.whisperjoin.common.sharedtypes.protobuf.ble;

import com.amazon.whisperjoin.common.sharedtypes.ble.WhisperJoinBlePacket;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.DataSerializationError;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer;
import com.amazon.whisperjoin.protobuf.ProtobufWhisperJoinBlePacketClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
/* loaded from: classes13.dex */
public class ProtoWhisperJoinBlePacket implements TypeSerializer<WhisperJoinBlePacket> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    /* renamed from: deserialize */
    public WhisperJoinBlePacket mo5427deserialize(byte[] bArr) {
        try {
            ProtobufWhisperJoinBlePacketClass.ProtobufWhisperJoinBlePacket parseFrom = ProtobufWhisperJoinBlePacketClass.ProtobufWhisperJoinBlePacket.parseFrom(bArr);
            return new WhisperJoinBlePacket(parseFrom.getPacketKey(), parseFrom.getChunkIndex(), parseFrom.getAdditionalChunks(), parseFrom.getPayload().toByteArray());
        } catch (InvalidProtocolBufferException e) {
            throw new DataSerializationError(GeneratedOutlineSupport1.outline36(e, GeneratedOutlineSupport1.outline107("Illegal data: ")), e);
        }
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.protobuf.TypeSerializer
    public byte[] serialize(WhisperJoinBlePacket whisperJoinBlePacket) {
        return ProtobufWhisperJoinBlePacketClass.ProtobufWhisperJoinBlePacket.newBuilder().setPacketKey(whisperJoinBlePacket.getPacketKey()).setChunkIndex(whisperJoinBlePacket.getChunkIndex()).setAdditionalChunks(whisperJoinBlePacket.hasAdditionalChunks()).setPayload(ByteString.copyFrom(whisperJoinBlePacket.getPayload())).mo10084build().toByteArray();
    }
}
