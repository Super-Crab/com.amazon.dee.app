package com.drew.metadata.exif.makernotes;

import com.amazonaws.services.s3.model.BucketVersioningConfiguration;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.StringValue;
import com.drew.metadata.TagDescriptor;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/* loaded from: classes2.dex */
public class ReconyxUltraFireMakernoteDescriptor extends TagDescriptor<ReconyxUltraFireMakernoteDirectory> {
    public ReconyxUltraFireMakernoteDescriptor(@NotNull ReconyxUltraFireMakernoteDirectory reconyxUltraFireMakernoteDirectory) {
        super(reconyxUltraFireMakernoteDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    @Nullable
    public String getDescription(int i) {
        switch (i) {
            case 0:
                return ((ReconyxUltraFireMakernoteDirectory) this._directory).getString(i);
            case 10:
                return String.format("0x%08X", ((ReconyxUltraFireMakernoteDirectory) this._directory).getInteger(i));
            case 14:
                return String.format("%d", ((ReconyxUltraFireMakernoteDirectory) this._directory).getInteger(i));
            case 18:
                return String.format("0x%08X", ((ReconyxUltraFireMakernoteDirectory) this._directory).getInteger(i));
            case 22:
                return String.format("%d", ((ReconyxUltraFireMakernoteDirectory) this._directory).getInteger(i));
            case 24:
            case 31:
            case 38:
            case 45:
            case 52:
                return ((ReconyxUltraFireMakernoteDirectory) this._directory).getString(i);
            case 53:
                int[] intArray = ((ReconyxUltraFireMakernoteDirectory) this._directory).getIntArray(i);
                if (intArray != null) {
                    return String.format("%d/%d", Integer.valueOf(intArray[0]), Integer.valueOf(intArray[1]));
                }
                return null;
            case 55:
                return String.format("%d", ((ReconyxUltraFireMakernoteDirectory) this._directory).getInteger(i));
            case 59:
                String string = ((ReconyxUltraFireMakernoteDirectory) this._directory).getString(i);
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
                    return simpleDateFormat.format(simpleDateFormat.parse(string));
                } catch (ParseException unused) {
                    return null;
                }
            case 67:
                return getIndexedDescription(i, "New", "Waxing Crescent", "First Quarter", "Waxing Gibbous", "Full", "Waning Gibbous", "Last Quarter", "Waning Crescent");
            case 68:
            case 70:
                return String.format("%d", ((ReconyxUltraFireMakernoteDirectory) this._directory).getInteger(i));
            case 72:
                return getIndexedDescription(i, BucketVersioningConfiguration.OFF, "On");
            case 73:
                Double doubleObject = ((ReconyxUltraFireMakernoteDirectory) this._directory).getDoubleObject(i);
                DecimalFormat decimalFormat = new DecimalFormat("0.000");
                if (doubleObject != null) {
                    return decimalFormat.format(doubleObject);
                }
                return null;
            case 75:
                StringValue stringValue = ((ReconyxUltraFireMakernoteDirectory) this._directory).getStringValue(i);
                if (stringValue != null) {
                    return stringValue.toString();
                }
                return null;
            case 80:
                return ((ReconyxUltraFireMakernoteDirectory) this._directory).getString(i);
            default:
                return super.getDescription(i);
        }
    }
}
