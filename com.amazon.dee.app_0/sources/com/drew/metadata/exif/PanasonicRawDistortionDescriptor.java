package com.drew.metadata.exif;

import com.amazonaws.services.s3.model.BucketVersioningConfiguration;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.lang.Rational;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
/* loaded from: classes2.dex */
public class PanasonicRawDistortionDescriptor extends TagDescriptor<PanasonicRawDistortionDirectory> {
    public PanasonicRawDistortionDescriptor(@NotNull PanasonicRawDistortionDirectory panasonicRawDistortionDirectory) {
        super(panasonicRawDistortionDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    @Nullable
    public String getDescription(int i) {
        return i != 2 ? i != 11 ? i != 4 ? i != 5 ? i != 7 ? i != 8 ? i != 9 ? super.getDescription(i) : getDistortionParam09Description() : getDistortionParam08Description() : getDistortionCorrectionDescription() : getDistortionScaleDescription() : getDistortionParam04Description() : getDistortionParam11Description() : getDistortionParam02Description();
    }

    @Nullable
    public String getDistortionCorrectionDescription() {
        Integer integer = ((PanasonicRawDistortionDirectory) this._directory).getInteger(7);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue() & 15;
        return intValue != 0 ? intValue != 1 ? GeneratedOutlineSupport1.outline69("Unknown (", integer, ")") : "On" : BucketVersioningConfiguration.OFF;
    }

    @Nullable
    public String getDistortionParam02Description() {
        Integer integer = ((PanasonicRawDistortionDirectory) this._directory).getInteger(2);
        if (integer == null) {
            return null;
        }
        return new Rational(integer.intValue(), 32678L).toString();
    }

    @Nullable
    public String getDistortionParam04Description() {
        Integer integer = ((PanasonicRawDistortionDirectory) this._directory).getInteger(4);
        if (integer == null) {
            return null;
        }
        return new Rational(integer.intValue(), 32678L).toString();
    }

    @Nullable
    public String getDistortionParam08Description() {
        Integer integer = ((PanasonicRawDistortionDirectory) this._directory).getInteger(8);
        if (integer == null) {
            return null;
        }
        return new Rational(integer.intValue(), 32678L).toString();
    }

    @Nullable
    public String getDistortionParam09Description() {
        Integer integer = ((PanasonicRawDistortionDirectory) this._directory).getInteger(9);
        if (integer == null) {
            return null;
        }
        return new Rational(integer.intValue(), 32678L).toString();
    }

    @Nullable
    public String getDistortionParam11Description() {
        Integer integer = ((PanasonicRawDistortionDirectory) this._directory).getInteger(11);
        if (integer == null) {
            return null;
        }
        return new Rational(integer.intValue(), 32678L).toString();
    }

    @Nullable
    public String getDistortionScaleDescription() {
        Integer integer = ((PanasonicRawDistortionDirectory) this._directory).getInteger(5);
        if (integer == null) {
            return null;
        }
        return Integer.toString(1 / ((integer.intValue() / 32768) + 1));
    }

    @Nullable
    public String getWbTypeDescription(int i) {
        Integer integer = ((PanasonicRawDistortionDirectory) this._directory).getInteger(i);
        if (integer == null) {
            return null;
        }
        return super.getLightSourceDescription(integer.shortValue());
    }
}
