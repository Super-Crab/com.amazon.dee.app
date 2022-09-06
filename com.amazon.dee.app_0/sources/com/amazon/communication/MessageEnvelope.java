package com.amazon.communication;

import amazon.communication.Message;
import amazon.communication.MessageFactory;
import android.os.Parcel;
import android.os.Parcelable;
import com.dp.utils.FailFast;
import java.nio.ByteBuffer;
/* loaded from: classes12.dex */
public class MessageEnvelope implements Parcelable {
    public static final Parcelable.Creator<MessageEnvelope> CREATOR = new Parcelable.Creator<MessageEnvelope>() { // from class: com.amazon.communication.MessageEnvelope.1
        private MessageEnvelope createFromByteArrayParcel(Parcel parcel) {
            int readInt = parcel.readInt();
            int readInt2 = parcel.readInt();
            ByteBuffer[] byteBufferArr = new ByteBuffer[readInt2];
            int i = 0;
            int i2 = 0;
            while (i < readInt) {
                byte[] createByteArray = parcel.createByteArray();
                FailFast.expectTrue(createByteArray.length + i <= readInt, "Received more data than expected from a Parcel");
                byteBufferArr[i2] = ByteBuffer.wrap(createByteArray);
                i += createByteArray.length;
                i2++;
            }
            FailFast.expectEquals(readInt, i, "Expected message size did not match total number of bytes read");
            FailFast.expectEquals(readInt2, i2, "Did not read the expected number of buffers from the parcel");
            return new MessageEnvelope(MessageFactory.createMessage(byteBufferArr));
        }

        private MessageEnvelope createFromInputStreamParcel(Parcel parcel) {
            return new MessageEnvelope(MessageFactory.createMessage(new ServiceSideInputStreamProxy(LargeArrayOptimizedIInputStream.makeIInputStream(parcel.readStrongBinder()))));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public MessageEnvelope mo3279createFromParcel(Parcel parcel) {
            if (parcel.readByte() == 1) {
                return createFromInputStreamParcel(parcel);
            }
            return createFromByteArrayParcel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public MessageEnvelope[] mo3280newArray(int i) {
            return new MessageEnvelope[i];
        }
    };
    private static final byte IS_INPUT_STREAM = 1;
    private static final byte IS_NOT_INPUT_STREAM = 0;
    private static final String TAG = "TComm.MessageEnvelope";
    private Message mMessage;

    MessageEnvelope(Message message) {
        this.mMessage = message;
    }

    private void copyDataIntoParcelOrConvertToInputStream(Parcel parcel) {
        ByteBufferChainMessageImpl byteBufferChainMessageImpl = (ByteBufferChainMessageImpl) this.mMessage;
        int payloadSize = byteBufferChainMessageImpl.getPayloadSize();
        FailFast.expectTrue(payloadSize > 0, "Message payload size is not greater than 0");
        if (payloadSize > 102400) {
            putInputStreamIntoParcel(parcel);
            return;
        }
        parcel.writeByte((byte) 0);
        parcel.writeInt(payloadSize);
        ByteBuffer[] byteBuffers = byteBufferChainMessageImpl.getByteBuffers();
        parcel.writeInt(byteBuffers.length);
        int i = 0;
        int i2 = 0;
        while (i < byteBuffers.length) {
            parcel.writeByteArray(byteBuffers[i].array(), byteBuffers[i].position(), byteBuffers[i].remaining());
            i2 += byteBuffers[i].remaining();
            i++;
        }
        FailFast.expectEquals(payloadSize, i2, "Message payload size did not match the number of bytes written");
        FailFast.expectEquals(byteBuffers.length, i, "Did not write the expected number of buffers into the Parcel");
    }

    public static MessageEnvelope createInstance(Message message) {
        return new MessageEnvelope(message);
    }

    private boolean isInputStreamBackedMessage() {
        return this.mMessage instanceof InputStreamMessageImpl;
    }

    private void putInputStreamIntoParcel(Parcel parcel) {
        parcel.writeByte((byte) 1);
        parcel.writeStrongBinder(new InputStreamProxy(this.mMessage.getPayload()).asBinder());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Message toMessage() {
        Message message = this.mMessage;
        this.mMessage = null;
        return message;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (isInputStreamBackedMessage()) {
            putInputStreamIntoParcel(parcel);
        } else {
            copyDataIntoParcelOrConvertToInputStream(parcel);
        }
    }
}
