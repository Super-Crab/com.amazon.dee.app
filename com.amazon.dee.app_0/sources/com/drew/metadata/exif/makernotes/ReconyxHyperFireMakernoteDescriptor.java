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
public class ReconyxHyperFireMakernoteDescriptor extends TagDescriptor<ReconyxHyperFireMakernoteDirectory> {
    public ReconyxHyperFireMakernoteDescriptor(@NotNull ReconyxHyperFireMakernoteDirectory reconyxHyperFireMakernoteDirectory) {
        super(reconyxHyperFireMakernoteDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    @Nullable
    public String getDescription(int i) {
        switch (i) {
            case 0:
                return String.format("%d", ((ReconyxHyperFireMakernoteDirectory) this._directory).getInteger(i));
            case 2:
                return ((ReconyxHyperFireMakernoteDirectory) this._directory).getString(i);
            case 12:
                return ((ReconyxHyperFireMakernoteDirectory) this._directory).getString(i);
            case 14:
                int[] intArray = ((ReconyxHyperFireMakernoteDirectory) this._directory).getIntArray(i);
                if (intArray != null) {
                    return String.format("%d/%d", Integer.valueOf(intArray[0]), Integer.valueOf(intArray[1]));
                }
                return null;
            case 18:
                return String.format("%d", ((ReconyxHyperFireMakernoteDirectory) this._directory).getInteger(i));
            case 22:
                String string = ((ReconyxHyperFireMakernoteDirectory) this._directory).getString(i);
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
                    return simpleDateFormat.format(simpleDateFormat.parse(string));
                } catch (ParseException unused) {
                    return null;
                }
            case 36:
                return getIndexedDescription(i, "New", "Waxing Crescent", "First Quarter", "Waxing Gibbous", "Full", "Waning Gibbous", "Last Quarter", "Waning Crescent");
            case 38:
            case 40:
                return String.format("%d", ((ReconyxHyperFireMakernoteDirectory) this._directory).getInteger(i));
            case 42:
                StringValue stringValue = ((ReconyxHyperFireMakernoteDirectory) this._directory).getStringValue(i);
                if (stringValue != null) {
                    return stringValue.toString();
                }
                return null;
            case 72:
            case 74:
            case 76:
            case 78:
                return String.format("%d", ((ReconyxHyperFireMakernoteDirectory) this._directory).getInteger(i));
            case 80:
                return getIndexedDescription(i, BucketVersioningConfiguration.OFF, "On");
            case 82:
                return String.format("%d", ((ReconyxHyperFireMakernoteDirectory) this._directory).getInteger(i));
            case 84:
                Double doubleObject = ((ReconyxHyperFireMakernoteDirectory) this._directory).getDoubleObject(i);
                DecimalFormat decimalFormat = new DecimalFormat("0.000");
                if (doubleObject != null) {
                    return decimalFormat.format(doubleObject);
                }
                return null;
            case 86:
                return ((ReconyxHyperFireMakernoteDirectory) this._directory).getString(i);
            default:
                return super.getDescription(i);
        }
    }
}
