package com.drew.metadata.jpeg;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
/* loaded from: classes2.dex */
public class HuffmanTablesDescriptor extends TagDescriptor<HuffmanTablesDirectory> {
    public HuffmanTablesDescriptor(@NotNull HuffmanTablesDirectory huffmanTablesDirectory) {
        super(huffmanTablesDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    @Nullable
    public String getDescription(int i) {
        return i != 1 ? super.getDescription(i) : getNumberOfTablesDescription();
    }

    @Nullable
    public String getNumberOfTablesDescription() {
        Integer integer = ((HuffmanTablesDirectory) this._directory).getInteger(1);
        if (integer == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(integer);
        sb.append(integer.intValue() == 1 ? " Huffman table" : " Huffman tables");
        return sb.toString();
    }
}
