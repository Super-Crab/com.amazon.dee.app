package com.google.protobuf;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessage.Builder;
import com.google.protobuf.MessageOrBuilder;
/* loaded from: classes3.dex */
public class SingleFieldBuilder<MType extends GeneratedMessage, BType extends GeneratedMessage.Builder, IType extends MessageOrBuilder> implements GeneratedMessage.BuilderParent {
    private BType builder;
    private boolean isClean;
    private MType message;
    private GeneratedMessage.BuilderParent parent;

    public SingleFieldBuilder(MType mtype, GeneratedMessage.BuilderParent builderParent, boolean z) {
        if (mtype != null) {
            this.message = mtype;
            this.parent = builderParent;
            this.isClean = z;
            return;
        }
        throw new NullPointerException();
    }

    private void onChanged() {
        GeneratedMessage.BuilderParent builderParent;
        if (this.builder != null) {
            this.message = null;
        }
        if (!this.isClean || (builderParent = this.parent) == null) {
            return;
        }
        builderParent.markDirty();
        this.isClean = false;
    }

    public MType build() {
        this.isClean = true;
        return getMessage();
    }

    public SingleFieldBuilder<MType, BType, IType> clear() {
        MessageLiteOrBuilder messageLiteOrBuilder = this.message;
        if (messageLiteOrBuilder == null) {
            messageLiteOrBuilder = this.builder;
        }
        this.message = (MType) messageLiteOrBuilder.mo10094getDefaultInstanceForType();
        BType btype = this.builder;
        if (btype != null) {
            btype.dispose();
            this.builder = null;
        }
        onChanged();
        return this;
    }

    public void dispose() {
        this.parent = null;
    }

    public BType getBuilder() {
        if (this.builder == null) {
            this.builder = (BType) this.message.newBuilderForType(this);
            this.builder.mo10096mergeFrom(this.message);
            this.builder.markClean();
        }
        return this.builder;
    }

    public MType getMessage() {
        if (this.message == null) {
            this.message = (MType) this.builder.mo10085buildPartial();
        }
        return this.message;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [BType extends com.google.protobuf.GeneratedMessage$Builder, IType extends com.google.protobuf.MessageOrBuilder] */
    /* JADX WARN: Type inference failed for: r0v1, types: [MType extends com.google.protobuf.GeneratedMessage, IType extends com.google.protobuf.MessageOrBuilder] */
    public IType getMessageOrBuilder() {
        BType btype = this.builder;
        return btype != 0 ? btype : this.message;
    }

    @Override // com.google.protobuf.AbstractMessage.BuilderParent
    public void markDirty() {
        onChanged();
    }

    public SingleFieldBuilder<MType, BType, IType> mergeFrom(MType mtype) {
        if (this.builder == null) {
            Message message = this.message;
            if (message == message.mo10094getDefaultInstanceForType()) {
                this.message = mtype;
                onChanged();
                return this;
            }
        }
        getBuilder().mo10096mergeFrom(mtype);
        onChanged();
        return this;
    }

    public SingleFieldBuilder<MType, BType, IType> setMessage(MType mtype) {
        if (mtype != null) {
            this.message = mtype;
            BType btype = this.builder;
            if (btype != null) {
                btype.dispose();
                this.builder = null;
            }
            onChanged();
            return this;
        }
        throw new NullPointerException();
    }
}
