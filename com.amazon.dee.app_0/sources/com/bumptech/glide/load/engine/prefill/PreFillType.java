package com.bumptech.glide.load.engine.prefill;

import android.graphics.Bitmap;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.bumptech.glide.util.Preconditions;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes6.dex */
public final class PreFillType {
    @VisibleForTesting
    static final Bitmap.Config DEFAULT_CONFIG = Bitmap.Config.RGB_565;
    private final Bitmap.Config config;
    private final int height;
    private final int weight;
    private final int width;

    /* loaded from: classes6.dex */
    public static class Builder {
        private Bitmap.Config config;
        private final int height;
        private int weight;
        private final int width;

        public Builder(int i) {
            this(i, i);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public PreFillType build() {
            return new PreFillType(this.width, this.height, this.config, this.weight);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Bitmap.Config getConfig() {
            return this.config;
        }

        public Builder setConfig(@Nullable Bitmap.Config config) {
            this.config = config;
            return this;
        }

        public Builder setWeight(int i) {
            if (i > 0) {
                this.weight = i;
                return this;
            }
            throw new IllegalArgumentException("Weight must be > 0");
        }

        public Builder(int i, int i2) {
            this.weight = 1;
            if (i > 0) {
                if (i2 > 0) {
                    this.width = i;
                    this.height = i2;
                    return;
                }
                throw new IllegalArgumentException("Height must be > 0");
            }
            throw new IllegalArgumentException("Width must be > 0");
        }
    }

    PreFillType(int i, int i2, Bitmap.Config config, int i3) {
        this.config = (Bitmap.Config) Preconditions.checkNotNull(config, "Config must not be null");
        this.width = i;
        this.height = i2;
        this.weight = i3;
    }

    public boolean equals(Object obj) {
        if (obj instanceof PreFillType) {
            PreFillType preFillType = (PreFillType) obj;
            return this.height == preFillType.height && this.width == preFillType.width && this.weight == preFillType.weight && this.config == preFillType.config;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Bitmap.Config getConfig() {
        return this.config;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getHeight() {
        return this.height;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getWeight() {
        return this.weight;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getWidth() {
        return this.width;
    }

    public int hashCode() {
        return ((this.config.hashCode() + (((this.width * 31) + this.height) * 31)) * 31) + this.weight;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PreFillSize{width=");
        outline107.append(this.width);
        outline107.append(", height=");
        outline107.append(this.height);
        outline107.append(", config=");
        outline107.append(this.config);
        outline107.append(", weight=");
        return GeneratedOutlineSupport1.outline85(outline107, this.weight, JsonReaderKt.END_OBJ);
    }
}
