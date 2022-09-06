package com.facebook.drawee.backends.pipeline;

import com.facebook.common.internal.ImmutableList;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.Suppliers;
import com.facebook.drawee.backends.pipeline.info.ImagePerfDataListener;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class DraweeConfig {
    @Nullable
    private final ImmutableList<DrawableFactory> mCustomDrawableFactories;
    private final Supplier<Boolean> mDebugOverlayEnabledSupplier;
    @Nullable
    private final ImagePerfDataListener mImagePerfDataListener;
    @Nullable
    private final PipelineDraweeControllerFactory mPipelineDraweeControllerFactory;

    /* loaded from: classes2.dex */
    public static class Builder {
        @Nullable
        private List<DrawableFactory> mCustomDrawableFactories;
        @Nullable
        private Supplier<Boolean> mDebugOverlayEnabledSupplier;
        @Nullable
        private ImagePerfDataListener mImagePerfDataListener;
        @Nullable
        private PipelineDraweeControllerFactory mPipelineDraweeControllerFactory;

        public Builder addCustomDrawableFactory(DrawableFactory factory) {
            if (this.mCustomDrawableFactories == null) {
                this.mCustomDrawableFactories = new ArrayList();
            }
            this.mCustomDrawableFactories.add(factory);
            return this;
        }

        public DraweeConfig build() {
            return new DraweeConfig(this);
        }

        public Builder setDebugOverlayEnabledSupplier(Supplier<Boolean> debugOverlayEnabledSupplier) {
            Preconditions.checkNotNull(debugOverlayEnabledSupplier);
            this.mDebugOverlayEnabledSupplier = debugOverlayEnabledSupplier;
            return this;
        }

        public Builder setDrawDebugOverlay(boolean drawDebugOverlay) {
            return setDebugOverlayEnabledSupplier(Suppliers.of(Boolean.valueOf(drawDebugOverlay)));
        }

        public Builder setImagePerfDataListener(@Nullable ImagePerfDataListener imagePerfDataListener) {
            this.mImagePerfDataListener = imagePerfDataListener;
            return this;
        }

        public Builder setPipelineDraweeControllerFactory(PipelineDraweeControllerFactory factory) {
            this.mPipelineDraweeControllerFactory = factory;
            return this;
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Nullable
    public ImmutableList<DrawableFactory> getCustomDrawableFactories() {
        return this.mCustomDrawableFactories;
    }

    public Supplier<Boolean> getDebugOverlayEnabledSupplier() {
        return this.mDebugOverlayEnabledSupplier;
    }

    @Nullable
    public ImagePerfDataListener getImagePerfDataListener() {
        return this.mImagePerfDataListener;
    }

    @Nullable
    public PipelineDraweeControllerFactory getPipelineDraweeControllerFactory() {
        return this.mPipelineDraweeControllerFactory;
    }

    private DraweeConfig(Builder builder) {
        Supplier<Boolean> of;
        this.mCustomDrawableFactories = builder.mCustomDrawableFactories != null ? ImmutableList.copyOf(builder.mCustomDrawableFactories) : null;
        if (builder.mDebugOverlayEnabledSupplier != null) {
            of = builder.mDebugOverlayEnabledSupplier;
        } else {
            of = Suppliers.of(false);
        }
        this.mDebugOverlayEnabledSupplier = of;
        this.mPipelineDraweeControllerFactory = builder.mPipelineDraweeControllerFactory;
        this.mImagePerfDataListener = builder.mImagePerfDataListener;
    }
}
