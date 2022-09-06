package com.drew.metadata.jpeg;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
/* loaded from: classes2.dex */
public class JpegDescriptor extends TagDescriptor<JpegDirectory> {
    public JpegDescriptor(@NotNull JpegDirectory jpegDirectory) {
        super(jpegDirectory);
    }

    @Nullable
    public String getComponentDataDescription(int i) {
        JpegComponent component = ((JpegDirectory) this._directory).getComponent(i);
        if (component == null) {
            return null;
        }
        return component.getComponentName() + " component: " + component;
    }

    @Nullable
    public String getDataPrecisionDescription() {
        String string = ((JpegDirectory) this._directory).getString(0);
        if (string == null) {
            return null;
        }
        return GeneratedOutlineSupport1.outline72(string, " bits");
    }

    @Override // com.drew.metadata.TagDescriptor
    @Nullable
    public String getDescription(int i) {
        int i2;
        if (i != -3) {
            if (i == 3) {
                return getImageWidthDescription();
            }
            if (i == 0) {
                return getDataPrecisionDescription();
            }
            if (i == 1) {
                return getImageHeightDescription();
            }
            switch (i) {
                case 6:
                    i2 = 0;
                    break;
                case 7:
                    return getComponentDataDescription(1);
                case 8:
                    i2 = 2;
                    break;
                case 9:
                    return getComponentDataDescription(3);
                default:
                    return super.getDescription(i);
            }
            return getComponentDataDescription(i2);
        }
        return getImageCompressionTypeDescription();
    }

    @Nullable
    public String getImageCompressionTypeDescription() {
        return getIndexedDescription(-3, "Baseline", "Extended sequential, Huffman", "Progressive, Huffman", "Lossless, Huffman", null, "Differential sequential, Huffman", "Differential progressive, Huffman", "Differential lossless, Huffman", "Reserved for JPEG extensions", "Extended sequential, arithmetic", "Progressive, arithmetic", "Lossless, arithmetic", null, "Differential sequential, arithmetic", "Differential progressive, arithmetic", "Differential lossless, arithmetic");
    }

    @Nullable
    public String getImageHeightDescription() {
        String string = ((JpegDirectory) this._directory).getString(1);
        if (string == null) {
            return null;
        }
        return GeneratedOutlineSupport1.outline72(string, " pixels");
    }

    @Nullable
    public String getImageWidthDescription() {
        String string = ((JpegDirectory) this._directory).getString(3);
        if (string == null) {
            return null;
        }
        return GeneratedOutlineSupport1.outline72(string, " pixels");
    }
}
