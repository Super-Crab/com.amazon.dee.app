package com.horcrux.svg;

import android.graphics.Matrix;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsConstants;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.MatrixMathHelper;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.TransformHelper;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import java.lang.reflect.Array;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.apache.logging.log4j.util.ProcessIdUtil;
/* loaded from: classes3.dex */
class RenderableViewManager extends ViewGroupManager<VirtualView> {
    private static final float CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER = 5.0f;
    private static final double EPSILON = 1.0E-5d;
    private static final int PERSPECTIVE_ARRAY_INVERTED_CAMERA_DISTANCE_INDEX = 2;
    private final String mClassName;
    private final SVGClass svgClass;
    private static final MatrixDecompositionContext sMatrixDecompositionContext = new MatrixDecompositionContext();
    private static final double[] sTransformDecompositionArray = new double[16];
    private static final SparseArray<RenderableView> mTagToRenderableView = new SparseArray<>();
    private static final SparseArray<Runnable> mTagToRunnable = new SparseArray<>();

    /* renamed from: com.horcrux.svg.RenderableViewManager$2  reason: invalid class name */
    /* loaded from: classes3.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType;
        static final /* synthetic */ int[] $SwitchMap$com$horcrux$svg$RenderableViewManager$SVGClass = new int[SVGClass.values().length];

        static {
            try {
                $SwitchMap$com$horcrux$svg$RenderableViewManager$SVGClass[SVGClass.RNSVGGroup.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$horcrux$svg$RenderableViewManager$SVGClass[SVGClass.RNSVGPath.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$horcrux$svg$RenderableViewManager$SVGClass[SVGClass.RNSVGCircle.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$horcrux$svg$RenderableViewManager$SVGClass[SVGClass.RNSVGEllipse.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$horcrux$svg$RenderableViewManager$SVGClass[SVGClass.RNSVGLine.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$horcrux$svg$RenderableViewManager$SVGClass[SVGClass.RNSVGRect.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$horcrux$svg$RenderableViewManager$SVGClass[SVGClass.RNSVGText.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$horcrux$svg$RenderableViewManager$SVGClass[SVGClass.RNSVGTSpan.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$horcrux$svg$RenderableViewManager$SVGClass[SVGClass.RNSVGTextPath.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$horcrux$svg$RenderableViewManager$SVGClass[SVGClass.RNSVGImage.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$horcrux$svg$RenderableViewManager$SVGClass[SVGClass.RNSVGClipPath.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$horcrux$svg$RenderableViewManager$SVGClass[SVGClass.RNSVGDefs.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$horcrux$svg$RenderableViewManager$SVGClass[SVGClass.RNSVGUse.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$horcrux$svg$RenderableViewManager$SVGClass[SVGClass.RNSVGSymbol.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$horcrux$svg$RenderableViewManager$SVGClass[SVGClass.RNSVGLinearGradient.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$horcrux$svg$RenderableViewManager$SVGClass[SVGClass.RNSVGRadialGradient.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$horcrux$svg$RenderableViewManager$SVGClass[SVGClass.RNSVGPattern.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$horcrux$svg$RenderableViewManager$SVGClass[SVGClass.RNSVGMask.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$horcrux$svg$RenderableViewManager$SVGClass[SVGClass.RNSVGMarker.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$horcrux$svg$RenderableViewManager$SVGClass[SVGClass.RNSVGForeignObject.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            $SwitchMap$com$facebook$react$bridge$ReadableType = new int[ReadableType.values().length];
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Number.ordinal()] = 1;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.String.ordinal()] = 2;
            } catch (NoSuchFieldError unused22) {
            }
        }
    }

    /* loaded from: classes3.dex */
    static class CircleViewManager extends RenderableViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public CircleViewManager() {
            super(SVGClass.RNSVGCircle);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        protected /* bridge */ /* synthetic */ void addEventEmitters(@Nonnull ThemedReactContext themedReactContext, @Nonnull View view) {
            super.addEventEmitters(themedReactContext, (VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
        /* renamed from: createShadowNodeInstance */
        public /* bridge */ /* synthetic */ ReactShadowNode mo10136createShadowNodeInstance() {
            return super.mo10136createShadowNodeInstance();
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        @Nonnull
        /* renamed from: createViewInstance */
        protected /* bridge */ /* synthetic */ View mo12880createViewInstance(@Nonnull ThemedReactContext themedReactContext) {
            return super.mo12880createViewInstance(themedReactContext);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
        protected /* bridge */ /* synthetic */ void onAfterUpdateTransaction(@Nonnull View view) {
            super.onAfterUpdateTransaction((VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void onDropViewInstance(@Nonnull View view) {
            super.onDropViewInstance((VirtualView) view);
        }

        @ReactProp(name = "cx")
        public void setCx(CircleView circleView, Dynamic dynamic) {
            circleView.setCx(dynamic);
        }

        @ReactProp(name = "cy")
        public void setCy(CircleView circleView, Dynamic dynamic) {
            circleView.setCy(dynamic);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((VirtualView) view, f);
        }

        @ReactProp(name = "r")
        public void setR(CircleView circleView, Dynamic dynamic) {
            circleView.setR(dynamic);
        }
    }

    /* loaded from: classes3.dex */
    static class ClipPathViewManager extends GroupViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public ClipPathViewManager() {
            super(SVGClass.RNSVGClipPath);
        }
    }

    /* loaded from: classes3.dex */
    static class DefsViewManager extends RenderableViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public DefsViewManager() {
            super(SVGClass.RNSVGDefs);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        protected /* bridge */ /* synthetic */ void addEventEmitters(@Nonnull ThemedReactContext themedReactContext, @Nonnull View view) {
            super.addEventEmitters(themedReactContext, (VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
        /* renamed from: createShadowNodeInstance */
        public /* bridge */ /* synthetic */ ReactShadowNode mo10136createShadowNodeInstance() {
            return super.mo10136createShadowNodeInstance();
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        @Nonnull
        /* renamed from: createViewInstance */
        protected /* bridge */ /* synthetic */ View mo12880createViewInstance(@Nonnull ThemedReactContext themedReactContext) {
            return super.mo12880createViewInstance(themedReactContext);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
        protected /* bridge */ /* synthetic */ void onAfterUpdateTransaction(@Nonnull View view) {
            super.onAfterUpdateTransaction((VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void onDropViewInstance(@Nonnull View view) {
            super.onDropViewInstance((VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((VirtualView) view, f);
        }
    }

    /* loaded from: classes3.dex */
    static class EllipseViewManager extends RenderableViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public EllipseViewManager() {
            super(SVGClass.RNSVGEllipse);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        protected /* bridge */ /* synthetic */ void addEventEmitters(@Nonnull ThemedReactContext themedReactContext, @Nonnull View view) {
            super.addEventEmitters(themedReactContext, (VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
        /* renamed from: createShadowNodeInstance */
        public /* bridge */ /* synthetic */ ReactShadowNode mo10136createShadowNodeInstance() {
            return super.mo10136createShadowNodeInstance();
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        @Nonnull
        /* renamed from: createViewInstance */
        protected /* bridge */ /* synthetic */ View mo12880createViewInstance(@Nonnull ThemedReactContext themedReactContext) {
            return super.mo12880createViewInstance(themedReactContext);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
        protected /* bridge */ /* synthetic */ void onAfterUpdateTransaction(@Nonnull View view) {
            super.onAfterUpdateTransaction((VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void onDropViewInstance(@Nonnull View view) {
            super.onDropViewInstance((VirtualView) view);
        }

        @ReactProp(name = "cx")
        public void setCx(EllipseView ellipseView, Dynamic dynamic) {
            ellipseView.setCx(dynamic);
        }

        @ReactProp(name = "cy")
        public void setCy(EllipseView ellipseView, Dynamic dynamic) {
            ellipseView.setCy(dynamic);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((VirtualView) view, f);
        }

        @ReactProp(name = "rx")
        public void setRx(EllipseView ellipseView, Dynamic dynamic) {
            ellipseView.setRx(dynamic);
        }

        @ReactProp(name = "ry")
        public void setRy(EllipseView ellipseView, Dynamic dynamic) {
            ellipseView.setRy(dynamic);
        }
    }

    /* loaded from: classes3.dex */
    static class ForeignObjectManager extends GroupViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public ForeignObjectManager() {
            super(SVGClass.RNSVGForeignObject);
        }

        @ReactProp(name = "height")
        public void setHeight(ForeignObjectView foreignObjectView, Dynamic dynamic) {
            foreignObjectView.setHeight(dynamic);
        }

        @ReactProp(name = "width")
        public void setWidth(ForeignObjectView foreignObjectView, Dynamic dynamic) {
            foreignObjectView.setWidth(dynamic);
        }

        @ReactProp(name = ReactProperties.HereMapMarker.X)
        public void setX(ForeignObjectView foreignObjectView, Dynamic dynamic) {
            foreignObjectView.setX(dynamic);
        }

        @ReactProp(name = ReactProperties.HereMapMarker.Y)
        public void setY(ForeignObjectView foreignObjectView, Dynamic dynamic) {
            foreignObjectView.setY(dynamic);
        }
    }

    /* loaded from: classes3.dex */
    static class GroupViewManager extends RenderableViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public GroupViewManager() {
            super(SVGClass.RNSVGGroup);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        protected /* bridge */ /* synthetic */ void addEventEmitters(@Nonnull ThemedReactContext themedReactContext, @Nonnull View view) {
            super.addEventEmitters(themedReactContext, (VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
        /* renamed from: createShadowNodeInstance */
        public /* bridge */ /* synthetic */ ReactShadowNode mo10136createShadowNodeInstance() {
            return super.mo10136createShadowNodeInstance();
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        @Nonnull
        /* renamed from: createViewInstance */
        protected /* bridge */ /* synthetic */ View mo12880createViewInstance(@Nonnull ThemedReactContext themedReactContext) {
            return super.mo12880createViewInstance(themedReactContext);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
        protected /* bridge */ /* synthetic */ void onAfterUpdateTransaction(@Nonnull View view) {
            super.onAfterUpdateTransaction((VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void onDropViewInstance(@Nonnull View view) {
            super.onDropViewInstance((VirtualView) view);
        }

        @ReactProp(name = "font")
        public void setFont(GroupView groupView, @Nullable ReadableMap readableMap) {
            groupView.setFont(readableMap);
        }

        @ReactProp(name = "fontSize")
        public void setFontSize(GroupView groupView, Dynamic dynamic) {
            JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
            int ordinal = dynamic.getType().ordinal();
            if (ordinal == 2) {
                javaOnlyMap.putDouble("fontSize", dynamic.asDouble());
            } else if (ordinal != 3) {
                return;
            } else {
                javaOnlyMap.putString("fontSize", dynamic.asString());
            }
            groupView.setFont(javaOnlyMap);
        }

        @ReactProp(name = "fontWeight")
        public void setFontWeight(GroupView groupView, Dynamic dynamic) {
            JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
            int ordinal = dynamic.getType().ordinal();
            if (ordinal == 2) {
                javaOnlyMap.putDouble("fontWeight", dynamic.asDouble());
            } else if (ordinal != 3) {
                return;
            } else {
                javaOnlyMap.putString("fontWeight", dynamic.asString());
            }
            groupView.setFont(javaOnlyMap);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((VirtualView) view, f);
        }

        GroupViewManager(SVGClass sVGClass) {
            super(sVGClass);
        }
    }

    /* loaded from: classes3.dex */
    static class ImageViewManager extends RenderableViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public ImageViewManager() {
            super(SVGClass.RNSVGImage);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        protected /* bridge */ /* synthetic */ void addEventEmitters(@Nonnull ThemedReactContext themedReactContext, @Nonnull View view) {
            super.addEventEmitters(themedReactContext, (VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
        /* renamed from: createShadowNodeInstance */
        public /* bridge */ /* synthetic */ ReactShadowNode mo10136createShadowNodeInstance() {
            return super.mo10136createShadowNodeInstance();
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        @Nonnull
        /* renamed from: createViewInstance */
        protected /* bridge */ /* synthetic */ View mo12880createViewInstance(@Nonnull ThemedReactContext themedReactContext) {
            return super.mo12880createViewInstance(themedReactContext);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
        protected /* bridge */ /* synthetic */ void onAfterUpdateTransaction(@Nonnull View view) {
            super.onAfterUpdateTransaction((VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void onDropViewInstance(@Nonnull View view) {
            super.onDropViewInstance((VirtualView) view);
        }

        @ReactProp(name = "align")
        public void setAlign(ImageView imageView, String str) {
            imageView.setAlign(str);
        }

        @ReactProp(name = "height")
        public void setHeight(ImageView imageView, Dynamic dynamic) {
            imageView.setHeight(dynamic);
        }

        @ReactProp(name = "meetOrSlice")
        public void setMeetOrSlice(ImageView imageView, int i) {
            imageView.setMeetOrSlice(i);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((VirtualView) view, f);
        }

        @ReactProp(name = "src")
        public void setSrc(ImageView imageView, @Nullable ReadableMap readableMap) {
            imageView.setSrc(readableMap);
        }

        @ReactProp(name = "width")
        public void setWidth(ImageView imageView, Dynamic dynamic) {
            imageView.setWidth(dynamic);
        }

        @ReactProp(name = ReactProperties.HereMapMarker.X)
        public void setX(ImageView imageView, Dynamic dynamic) {
            imageView.setX(dynamic);
        }

        @ReactProp(name = ReactProperties.HereMapMarker.Y)
        public void setY(ImageView imageView, Dynamic dynamic) {
            imageView.setY(dynamic);
        }
    }

    /* loaded from: classes3.dex */
    static class LineViewManager extends RenderableViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public LineViewManager() {
            super(SVGClass.RNSVGLine);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        protected /* bridge */ /* synthetic */ void addEventEmitters(@Nonnull ThemedReactContext themedReactContext, @Nonnull View view) {
            super.addEventEmitters(themedReactContext, (VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
        /* renamed from: createShadowNodeInstance */
        public /* bridge */ /* synthetic */ ReactShadowNode mo10136createShadowNodeInstance() {
            return super.mo10136createShadowNodeInstance();
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        @Nonnull
        /* renamed from: createViewInstance */
        protected /* bridge */ /* synthetic */ View mo12880createViewInstance(@Nonnull ThemedReactContext themedReactContext) {
            return super.mo12880createViewInstance(themedReactContext);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
        protected /* bridge */ /* synthetic */ void onAfterUpdateTransaction(@Nonnull View view) {
            super.onAfterUpdateTransaction((VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void onDropViewInstance(@Nonnull View view) {
            super.onDropViewInstance((VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((VirtualView) view, f);
        }

        @ReactProp(name = "x1")
        public void setX1(LineView lineView, Dynamic dynamic) {
            lineView.setX1(dynamic);
        }

        @ReactProp(name = "x2")
        public void setX2(LineView lineView, Dynamic dynamic) {
            lineView.setX2(dynamic);
        }

        @ReactProp(name = "y1")
        public void setY1(LineView lineView, Dynamic dynamic) {
            lineView.setY1(dynamic);
        }

        @ReactProp(name = "y2")
        public void setY2(LineView lineView, Dynamic dynamic) {
            lineView.setY2(dynamic);
        }
    }

    /* loaded from: classes3.dex */
    static class LinearGradientManager extends RenderableViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public LinearGradientManager() {
            super(SVGClass.RNSVGLinearGradient);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        protected /* bridge */ /* synthetic */ void addEventEmitters(@Nonnull ThemedReactContext themedReactContext, @Nonnull View view) {
            super.addEventEmitters(themedReactContext, (VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
        /* renamed from: createShadowNodeInstance */
        public /* bridge */ /* synthetic */ ReactShadowNode mo10136createShadowNodeInstance() {
            return super.mo10136createShadowNodeInstance();
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        @Nonnull
        /* renamed from: createViewInstance */
        protected /* bridge */ /* synthetic */ View mo12880createViewInstance(@Nonnull ThemedReactContext themedReactContext) {
            return super.mo12880createViewInstance(themedReactContext);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
        protected /* bridge */ /* synthetic */ void onAfterUpdateTransaction(@Nonnull View view) {
            super.onAfterUpdateTransaction((VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void onDropViewInstance(@Nonnull View view) {
            super.onDropViewInstance((VirtualView) view);
        }

        @ReactProp(name = "gradient")
        public void setGradient(LinearGradientView linearGradientView, ReadableArray readableArray) {
            linearGradientView.setGradient(readableArray);
        }

        @ReactProp(name = "gradientTransform")
        public void setGradientTransform(LinearGradientView linearGradientView, @Nullable ReadableArray readableArray) {
            linearGradientView.setGradientTransform(readableArray);
        }

        @ReactProp(name = "gradientUnits")
        public void setGradientUnits(LinearGradientView linearGradientView, int i) {
            linearGradientView.setGradientUnits(i);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((VirtualView) view, f);
        }

        @ReactProp(name = "x1")
        public void setX1(LinearGradientView linearGradientView, Dynamic dynamic) {
            linearGradientView.setX1(dynamic);
        }

        @ReactProp(name = "x2")
        public void setX2(LinearGradientView linearGradientView, Dynamic dynamic) {
            linearGradientView.setX2(dynamic);
        }

        @ReactProp(name = "y1")
        public void setY1(LinearGradientView linearGradientView, Dynamic dynamic) {
            linearGradientView.setY1(dynamic);
        }

        @ReactProp(name = "y2")
        public void setY2(LinearGradientView linearGradientView, Dynamic dynamic) {
            linearGradientView.setY2(dynamic);
        }
    }

    /* loaded from: classes3.dex */
    static class MarkerManager extends GroupViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public MarkerManager() {
            super(SVGClass.RNSVGMarker);
        }

        @ReactProp(name = "align")
        public void setAlign(MarkerView markerView, String str) {
            markerView.setAlign(str);
        }

        @ReactProp(name = "markerHeight")
        public void setMarkerHeight(MarkerView markerView, Dynamic dynamic) {
            markerView.setMarkerHeight(dynamic);
        }

        @ReactProp(name = "markerUnits")
        public void setMarkerUnits(MarkerView markerView, String str) {
            markerView.setMarkerUnits(str);
        }

        @ReactProp(name = "markerWidth")
        public void setMarkerWidth(MarkerView markerView, Dynamic dynamic) {
            markerView.setMarkerWidth(dynamic);
        }

        @ReactProp(name = "meetOrSlice")
        public void setMeetOrSlice(MarkerView markerView, int i) {
            markerView.setMeetOrSlice(i);
        }

        @ReactProp(name = "minX")
        public void setMinX(MarkerView markerView, float f) {
            markerView.setMinX(f);
        }

        @ReactProp(name = "minY")
        public void setMinY(MarkerView markerView, float f) {
            markerView.setMinY(f);
        }

        @ReactProp(name = "orient")
        public void setOrient(MarkerView markerView, String str) {
            markerView.setOrient(str);
        }

        @ReactProp(name = "refX")
        public void setRefX(MarkerView markerView, Dynamic dynamic) {
            markerView.setRefX(dynamic);
        }

        @ReactProp(name = "refY")
        public void setRefY(MarkerView markerView, Dynamic dynamic) {
            markerView.setRefY(dynamic);
        }

        @ReactProp(name = "vbHeight")
        public void setVbHeight(MarkerView markerView, float f) {
            markerView.setVbHeight(f);
        }

        @ReactProp(name = "vbWidth")
        public void setVbWidth(MarkerView markerView, float f) {
            markerView.setVbWidth(f);
        }
    }

    /* loaded from: classes3.dex */
    static class MaskManager extends GroupViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public MaskManager() {
            super(SVGClass.RNSVGMask);
        }

        @ReactProp(name = "height")
        public void setHeight(MaskView maskView, Dynamic dynamic) {
            maskView.setHeight(dynamic);
        }

        @ReactProp(name = "maskContentUnits")
        public void setMaskContentUnits(MaskView maskView, int i) {
            maskView.setMaskContentUnits(i);
        }

        @ReactProp(name = "maskTransform")
        public void setMaskTransform(MaskView maskView, @Nullable ReadableArray readableArray) {
            maskView.setMaskTransform(readableArray);
        }

        @ReactProp(name = "maskUnits")
        public void setMaskUnits(MaskView maskView, int i) {
            maskView.setMaskUnits(i);
        }

        @ReactProp(name = "width")
        public void setWidth(MaskView maskView, Dynamic dynamic) {
            maskView.setWidth(dynamic);
        }

        @ReactProp(name = ReactProperties.HereMapMarker.X)
        public void setX(MaskView maskView, Dynamic dynamic) {
            maskView.setX(dynamic);
        }

        @ReactProp(name = ReactProperties.HereMapMarker.Y)
        public void setY(MaskView maskView, Dynamic dynamic) {
            maskView.setY(dynamic);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class MatrixDecompositionContext extends MatrixMathHelper.MatrixDecompositionContext {
        final double[] perspective = new double[4];
        final double[] scale = new double[3];
        final double[] skew = new double[3];
        final double[] translation = new double[3];
        final double[] rotationDegrees = new double[3];

        MatrixDecompositionContext() {
        }
    }

    /* loaded from: classes3.dex */
    static class PathViewManager extends RenderableViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public PathViewManager() {
            super(SVGClass.RNSVGPath);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        protected /* bridge */ /* synthetic */ void addEventEmitters(@Nonnull ThemedReactContext themedReactContext, @Nonnull View view) {
            super.addEventEmitters(themedReactContext, (VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
        /* renamed from: createShadowNodeInstance */
        public /* bridge */ /* synthetic */ ReactShadowNode mo10136createShadowNodeInstance() {
            return super.mo10136createShadowNodeInstance();
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        @Nonnull
        /* renamed from: createViewInstance */
        protected /* bridge */ /* synthetic */ View mo12880createViewInstance(@Nonnull ThemedReactContext themedReactContext) {
            return super.mo12880createViewInstance(themedReactContext);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
        protected /* bridge */ /* synthetic */ void onAfterUpdateTransaction(@Nonnull View view) {
            super.onAfterUpdateTransaction((VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void onDropViewInstance(@Nonnull View view) {
            super.onDropViewInstance((VirtualView) view);
        }

        @ReactProp(name = "d")
        public void setD(PathView pathView, String str) {
            pathView.setD(str);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((VirtualView) view, f);
        }
    }

    /* loaded from: classes3.dex */
    static class PatternManager extends GroupViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public PatternManager() {
            super(SVGClass.RNSVGPattern);
        }

        @ReactProp(name = "align")
        public void setAlign(PatternView patternView, String str) {
            patternView.setAlign(str);
        }

        @ReactProp(name = "height")
        public void setHeight(PatternView patternView, Dynamic dynamic) {
            patternView.setHeight(dynamic);
        }

        @ReactProp(name = "meetOrSlice")
        public void setMeetOrSlice(PatternView patternView, int i) {
            patternView.setMeetOrSlice(i);
        }

        @ReactProp(name = "minX")
        public void setMinX(PatternView patternView, float f) {
            patternView.setMinX(f);
        }

        @ReactProp(name = "minY")
        public void setMinY(PatternView patternView, float f) {
            patternView.setMinY(f);
        }

        @ReactProp(name = "patternContentUnits")
        public void setPatternContentUnits(PatternView patternView, int i) {
            patternView.setPatternContentUnits(i);
        }

        @ReactProp(name = "patternTransform")
        public void setPatternTransform(PatternView patternView, @Nullable ReadableArray readableArray) {
            patternView.setPatternTransform(readableArray);
        }

        @ReactProp(name = "patternUnits")
        public void setPatternUnits(PatternView patternView, int i) {
            patternView.setPatternUnits(i);
        }

        @ReactProp(name = "vbHeight")
        public void setVbHeight(PatternView patternView, float f) {
            patternView.setVbHeight(f);
        }

        @ReactProp(name = "vbWidth")
        public void setVbWidth(PatternView patternView, float f) {
            patternView.setVbWidth(f);
        }

        @ReactProp(name = "width")
        public void setWidth(PatternView patternView, Dynamic dynamic) {
            patternView.setWidth(dynamic);
        }

        @ReactProp(name = ReactProperties.HereMapMarker.X)
        public void setX(PatternView patternView, Dynamic dynamic) {
            patternView.setX(dynamic);
        }

        @ReactProp(name = ReactProperties.HereMapMarker.Y)
        public void setY(PatternView patternView, Dynamic dynamic) {
            patternView.setY(dynamic);
        }
    }

    /* loaded from: classes3.dex */
    static class RadialGradientManager extends RenderableViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public RadialGradientManager() {
            super(SVGClass.RNSVGRadialGradient);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        protected /* bridge */ /* synthetic */ void addEventEmitters(@Nonnull ThemedReactContext themedReactContext, @Nonnull View view) {
            super.addEventEmitters(themedReactContext, (VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
        /* renamed from: createShadowNodeInstance */
        public /* bridge */ /* synthetic */ ReactShadowNode mo10136createShadowNodeInstance() {
            return super.mo10136createShadowNodeInstance();
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        @Nonnull
        /* renamed from: createViewInstance */
        protected /* bridge */ /* synthetic */ View mo12880createViewInstance(@Nonnull ThemedReactContext themedReactContext) {
            return super.mo12880createViewInstance(themedReactContext);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
        protected /* bridge */ /* synthetic */ void onAfterUpdateTransaction(@Nonnull View view) {
            super.onAfterUpdateTransaction((VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void onDropViewInstance(@Nonnull View view) {
            super.onDropViewInstance((VirtualView) view);
        }

        @ReactProp(name = "cx")
        public void setCx(RadialGradientView radialGradientView, Dynamic dynamic) {
            radialGradientView.setCx(dynamic);
        }

        @ReactProp(name = "cy")
        public void setCy(RadialGradientView radialGradientView, Dynamic dynamic) {
            radialGradientView.setCy(dynamic);
        }

        @ReactProp(name = "fx")
        public void setFx(RadialGradientView radialGradientView, Dynamic dynamic) {
            radialGradientView.setFx(dynamic);
        }

        @ReactProp(name = "fy")
        public void setFy(RadialGradientView radialGradientView, Dynamic dynamic) {
            radialGradientView.setFy(dynamic);
        }

        @ReactProp(name = "gradient")
        public void setGradient(RadialGradientView radialGradientView, ReadableArray readableArray) {
            radialGradientView.setGradient(readableArray);
        }

        @ReactProp(name = "gradientTransform")
        public void setGradientTransform(RadialGradientView radialGradientView, @Nullable ReadableArray readableArray) {
            radialGradientView.setGradientTransform(readableArray);
        }

        @ReactProp(name = "gradientUnits")
        public void setGradientUnits(RadialGradientView radialGradientView, int i) {
            radialGradientView.setGradientUnits(i);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((VirtualView) view, f);
        }

        @ReactProp(name = "rx")
        public void setRx(RadialGradientView radialGradientView, Dynamic dynamic) {
            radialGradientView.setRx(dynamic);
        }

        @ReactProp(name = "ry")
        public void setRy(RadialGradientView radialGradientView, Dynamic dynamic) {
            radialGradientView.setRy(dynamic);
        }
    }

    /* loaded from: classes3.dex */
    static class RectViewManager extends RenderableViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public RectViewManager() {
            super(SVGClass.RNSVGRect);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        protected /* bridge */ /* synthetic */ void addEventEmitters(@Nonnull ThemedReactContext themedReactContext, @Nonnull View view) {
            super.addEventEmitters(themedReactContext, (VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
        /* renamed from: createShadowNodeInstance */
        public /* bridge */ /* synthetic */ ReactShadowNode mo10136createShadowNodeInstance() {
            return super.mo10136createShadowNodeInstance();
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        @Nonnull
        /* renamed from: createViewInstance */
        protected /* bridge */ /* synthetic */ View mo12880createViewInstance(@Nonnull ThemedReactContext themedReactContext) {
            return super.mo12880createViewInstance(themedReactContext);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
        protected /* bridge */ /* synthetic */ void onAfterUpdateTransaction(@Nonnull View view) {
            super.onAfterUpdateTransaction((VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void onDropViewInstance(@Nonnull View view) {
            super.onDropViewInstance((VirtualView) view);
        }

        @ReactProp(name = "height")
        public void setHeight(RectView rectView, Dynamic dynamic) {
            rectView.setHeight(dynamic);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((VirtualView) view, f);
        }

        @ReactProp(name = "rx")
        public void setRx(RectView rectView, Dynamic dynamic) {
            rectView.setRx(dynamic);
        }

        @ReactProp(name = "ry")
        public void setRy(RectView rectView, Dynamic dynamic) {
            rectView.setRy(dynamic);
        }

        @ReactProp(name = "width")
        public void setWidth(RectView rectView, Dynamic dynamic) {
            rectView.setWidth(dynamic);
        }

        @ReactProp(name = ReactProperties.HereMapMarker.X)
        public void setX(RectView rectView, Dynamic dynamic) {
            rectView.setX(dynamic);
        }

        @ReactProp(name = ReactProperties.HereMapMarker.Y)
        public void setY(RectView rectView, Dynamic dynamic) {
            rectView.setY(dynamic);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class RenderableShadowNode extends LayoutShadowNode {
        RenderableShadowNode() {
        }

        @ReactPropGroup(names = {ViewProps.ALIGN_SELF, ViewProps.ALIGN_ITEMS, ViewProps.COLLAPSABLE, ViewProps.FLEX, ViewProps.FLEX_BASIS, ViewProps.FLEX_DIRECTION, ViewProps.FLEX_GROW, ViewProps.FLEX_SHRINK, ViewProps.FLEX_WRAP, ViewProps.JUSTIFY_CONTENT, ViewProps.OVERFLOW, ViewProps.ALIGN_CONTENT, "display", ViewProps.POSITION, "right", ViewProps.TOP, ViewProps.BOTTOM, "left", "start", "end", "width", "height", ViewProps.MIN_WIDTH, "maxWidth", ViewProps.MIN_HEIGHT, "maxHeight", ViewProps.MARGIN, ViewProps.MARGIN_VERTICAL, ViewProps.MARGIN_HORIZONTAL, ViewProps.MARGIN_LEFT, ViewProps.MARGIN_RIGHT, ViewProps.MARGIN_TOP, ViewProps.MARGIN_BOTTOM, ViewProps.MARGIN_START, ViewProps.MARGIN_END, ViewProps.PADDING, ViewProps.PADDING_VERTICAL, ViewProps.PADDING_HORIZONTAL, ViewProps.PADDING_LEFT, ViewProps.PADDING_RIGHT, ViewProps.PADDING_TOP, ViewProps.PADDING_BOTTOM, ViewProps.PADDING_START, ViewProps.PADDING_END, ViewProps.BORDER_WIDTH, ViewProps.BORDER_START_WIDTH, ViewProps.BORDER_END_WIDTH, ViewProps.BORDER_TOP_WIDTH, ViewProps.BORDER_BOTTOM_WIDTH, ViewProps.BORDER_LEFT_WIDTH, ViewProps.BORDER_RIGHT_WIDTH})
        public void ignoreLayoutProps(int i, Dynamic dynamic) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public enum SVGClass {
        RNSVGGroup,
        RNSVGPath,
        RNSVGText,
        RNSVGTSpan,
        RNSVGTextPath,
        RNSVGImage,
        RNSVGCircle,
        RNSVGEllipse,
        RNSVGLine,
        RNSVGRect,
        RNSVGClipPath,
        RNSVGDefs,
        RNSVGUse,
        RNSVGSymbol,
        RNSVGLinearGradient,
        RNSVGRadialGradient,
        RNSVGPattern,
        RNSVGMask,
        RNSVGMarker,
        RNSVGForeignObject
    }

    /* loaded from: classes3.dex */
    static class SymbolManager extends GroupViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public SymbolManager() {
            super(SVGClass.RNSVGSymbol);
        }

        @ReactProp(name = "align")
        public void setAlign(SymbolView symbolView, String str) {
            symbolView.setAlign(str);
        }

        @ReactProp(name = "meetOrSlice")
        public void setMeetOrSlice(SymbolView symbolView, int i) {
            symbolView.setMeetOrSlice(i);
        }

        @ReactProp(name = "minX")
        public void setMinX(SymbolView symbolView, float f) {
            symbolView.setMinX(f);
        }

        @ReactProp(name = "minY")
        public void setMinY(SymbolView symbolView, float f) {
            symbolView.setMinY(f);
        }

        @ReactProp(name = "vbHeight")
        public void setVbHeight(SymbolView symbolView, float f) {
            symbolView.setVbHeight(f);
        }

        @ReactProp(name = "vbWidth")
        public void setVbWidth(SymbolView symbolView, float f) {
            symbolView.setVbWidth(f);
        }
    }

    /* loaded from: classes3.dex */
    static class TSpanViewManager extends TextViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public TSpanViewManager() {
            super(SVGClass.RNSVGTSpan);
        }

        @ReactProp(name = "content")
        public void setContent(TSpanView tSpanView, @Nullable String str) {
            tSpanView.setContent(str);
        }
    }

    /* loaded from: classes3.dex */
    static class TextPathViewManager extends TextViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public TextPathViewManager() {
            super(SVGClass.RNSVGTextPath);
        }

        @ReactProp(name = "href")
        public void setHref(TextPathView textPathView, String str) {
            textPathView.setHref(str);
        }

        @ReactProp(name = MetricsConstants.NativeFetch.METHOD)
        public void setMethod(TextPathView textPathView, @Nullable String str) {
            textPathView.setMethod(str);
        }

        @ReactProp(name = "midLine")
        public void setSharp(TextPathView textPathView, @Nullable String str) {
            textPathView.setSharp(str);
        }

        @ReactProp(name = "side")
        public void setSide(TextPathView textPathView, @Nullable String str) {
            textPathView.setSide(str);
        }

        @ReactProp(name = "spacing")
        public void setSpacing(TextPathView textPathView, @Nullable String str) {
            textPathView.setSpacing(str);
        }

        @ReactProp(name = "startOffset")
        public void setStartOffset(TextPathView textPathView, Dynamic dynamic) {
            textPathView.setStartOffset(dynamic);
        }
    }

    /* loaded from: classes3.dex */
    static class TextViewManager extends GroupViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public TextViewManager() {
            super(SVGClass.RNSVGText);
        }

        @ReactProp(name = "baselineShift")
        public void setBaselineShift(TextView textView, Dynamic dynamic) {
            textView.setBaselineShift(dynamic);
        }

        @ReactProp(name = "dx")
        public void setDeltaX(TextView textView, Dynamic dynamic) {
            textView.setDeltaX(dynamic);
        }

        @ReactProp(name = "dy")
        public void setDeltaY(TextView textView, Dynamic dynamic) {
            textView.setDeltaY(dynamic);
        }

        @ReactProp(name = "font")
        public void setFont(TextView textView, @Nullable ReadableMap readableMap) {
            textView.setFont(readableMap);
        }

        @ReactProp(name = "inlineSize")
        public void setInlineSize(TextView textView, Dynamic dynamic) {
            textView.setInlineSize(dynamic);
        }

        @ReactProp(name = "lengthAdjust")
        public void setLengthAdjust(TextView textView, @Nullable String str) {
            textView.setLengthAdjust(str);
        }

        @ReactProp(name = "alignmentBaseline")
        public void setMethod(TextView textView, @Nullable String str) {
            textView.setMethod(str);
        }

        @ReactProp(name = "rotate")
        public void setRotate(TextView textView, Dynamic dynamic) {
            textView.setRotate(dynamic);
        }

        @ReactProp(name = "textLength")
        public void setTextLength(TextView textView, Dynamic dynamic) {
            textView.setTextLength(dynamic);
        }

        @ReactProp(name = "verticalAlign")
        public void setVerticalAlign(TextView textView, @Nullable String str) {
            textView.setVerticalAlign(str);
        }

        @ReactProp(name = ReactProperties.HereMapMarker.X)
        public void setX(TextView textView, Dynamic dynamic) {
            textView.setPositionX(dynamic);
        }

        @ReactProp(name = ReactProperties.HereMapMarker.Y)
        public void setY(TextView textView, Dynamic dynamic) {
            textView.setPositionY(dynamic);
        }

        TextViewManager(SVGClass sVGClass) {
            super(sVGClass);
        }
    }

    /* loaded from: classes3.dex */
    static class UseViewManager extends RenderableViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public UseViewManager() {
            super(SVGClass.RNSVGUse);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        protected /* bridge */ /* synthetic */ void addEventEmitters(@Nonnull ThemedReactContext themedReactContext, @Nonnull View view) {
            super.addEventEmitters(themedReactContext, (VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
        /* renamed from: createShadowNodeInstance */
        public /* bridge */ /* synthetic */ ReactShadowNode mo10136createShadowNodeInstance() {
            return super.mo10136createShadowNodeInstance();
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        @Nonnull
        /* renamed from: createViewInstance */
        protected /* bridge */ /* synthetic */ View mo12880createViewInstance(@Nonnull ThemedReactContext themedReactContext) {
            return super.mo12880createViewInstance(themedReactContext);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
        protected /* bridge */ /* synthetic */ void onAfterUpdateTransaction(@Nonnull View view) {
            super.onAfterUpdateTransaction((VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.ViewManager
        public /* bridge */ /* synthetic */ void onDropViewInstance(@Nonnull View view) {
            super.onDropViewInstance((VirtualView) view);
        }

        @ReactProp(name = "height")
        public void setHeight(UseView useView, Dynamic dynamic) {
            useView.setHeight(dynamic);
        }

        @ReactProp(name = "href")
        public void setHref(UseView useView, String str) {
            useView.setHref(str);
        }

        @Override // com.horcrux.svg.RenderableViewManager, com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((VirtualView) view, f);
        }

        @ReactProp(name = "width")
        public void setWidth(UseView useView, Dynamic dynamic) {
            useView.setWidth(dynamic);
        }

        @ReactProp(name = ReactProperties.HereMapMarker.X)
        public void setX(UseView useView, Dynamic dynamic) {
            useView.setX(dynamic);
        }

        @ReactProp(name = ReactProperties.HereMapMarker.Y)
        public void setY(UseView useView, Dynamic dynamic) {
            useView.setY(dynamic);
        }
    }

    private static void decomposeMatrix() {
        MatrixDecompositionContext matrixDecompositionContext = sMatrixDecompositionContext;
        double[] dArr = matrixDecompositionContext.perspective;
        double[] dArr2 = matrixDecompositionContext.scale;
        double[] dArr3 = matrixDecompositionContext.skew;
        double[] dArr4 = matrixDecompositionContext.translation;
        double[] dArr5 = matrixDecompositionContext.rotationDegrees;
        if (isZero(sTransformDecompositionArray[15])) {
            return;
        }
        double[][] dArr6 = (double[][]) Array.newInstance(double.class, 4, 4);
        double[] dArr7 = new double[16];
        for (int i = 0; i < 4; i++) {
            for (int i2 = 0; i2 < 4; i2++) {
                double[] dArr8 = sTransformDecompositionArray;
                int i3 = (i * 4) + i2;
                double d = dArr8[i3] / dArr8[15];
                dArr6[i][i2] = d;
                if (i2 == 3) {
                    d = 0.0d;
                }
                dArr7[i3] = d;
            }
        }
        dArr7[15] = 1.0d;
        if (isZero(MatrixMathHelper.determinant(dArr7))) {
            return;
        }
        if (isZero(dArr6[0][3]) && isZero(dArr6[1][3]) && isZero(dArr6[2][3])) {
            dArr[2] = 0.0d;
            dArr[1] = 0.0d;
            dArr[0] = 0.0d;
            dArr[3] = 1.0d;
        } else {
            MatrixMathHelper.multiplyVectorByMatrix(new double[]{dArr6[0][3], dArr6[1][3], dArr6[2][3], dArr6[3][3]}, MatrixMathHelper.transpose(MatrixMathHelper.inverse(dArr7)), dArr);
        }
        System.arraycopy(dArr6[3], 0, dArr4, 0, 3);
        double[][] dArr9 = (double[][]) Array.newInstance(double.class, 3, 3);
        for (int i4 = 0; i4 < 3; i4++) {
            dArr9[i4][0] = dArr6[i4][0];
            dArr9[i4][1] = dArr6[i4][1];
            dArr9[i4][2] = dArr6[i4][2];
        }
        dArr2[0] = MatrixMathHelper.v3Length(dArr9[0]);
        dArr9[0] = MatrixMathHelper.v3Normalize(dArr9[0], dArr2[0]);
        dArr3[0] = MatrixMathHelper.v3Dot(dArr9[0], dArr9[1]);
        dArr9[1] = MatrixMathHelper.v3Combine(dArr9[1], dArr9[0], 1.0d, -dArr3[0]);
        dArr3[0] = MatrixMathHelper.v3Dot(dArr9[0], dArr9[1]);
        dArr9[1] = MatrixMathHelper.v3Combine(dArr9[1], dArr9[0], 1.0d, -dArr3[0]);
        dArr2[1] = MatrixMathHelper.v3Length(dArr9[1]);
        dArr9[1] = MatrixMathHelper.v3Normalize(dArr9[1], dArr2[1]);
        dArr3[0] = dArr3[0] / dArr2[1];
        dArr3[1] = MatrixMathHelper.v3Dot(dArr9[0], dArr9[2]);
        dArr9[2] = MatrixMathHelper.v3Combine(dArr9[2], dArr9[0], 1.0d, -dArr3[1]);
        dArr3[2] = MatrixMathHelper.v3Dot(dArr9[1], dArr9[2]);
        dArr9[2] = MatrixMathHelper.v3Combine(dArr9[2], dArr9[1], 1.0d, -dArr3[2]);
        dArr2[2] = MatrixMathHelper.v3Length(dArr9[2]);
        dArr9[2] = MatrixMathHelper.v3Normalize(dArr9[2], dArr2[2]);
        dArr3[1] = dArr3[1] / dArr2[2];
        dArr3[2] = dArr3[2] / dArr2[2];
        if (MatrixMathHelper.v3Dot(dArr9[0], MatrixMathHelper.v3Cross(dArr9[1], dArr9[2])) < FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) {
            for (int i5 = 0; i5 < 3; i5++) {
                dArr2[i5] = dArr2[i5] * (-1.0d);
                double[] dArr10 = dArr9[i5];
                dArr10[0] = dArr10[0] * (-1.0d);
                double[] dArr11 = dArr9[i5];
                dArr11[1] = dArr11[1] * (-1.0d);
                double[] dArr12 = dArr9[i5];
                dArr12[2] = dArr12[2] * (-1.0d);
            }
        }
        dArr5[0] = MatrixMathHelper.roundTo3Places((-Math.atan2(dArr9[2][1], dArr9[2][2])) * 57.29577951308232d);
        dArr5[1] = MatrixMathHelper.roundTo3Places((-Math.atan2(-dArr9[2][0], Math.sqrt((dArr9[2][2] * dArr9[2][2]) + (dArr9[2][1] * dArr9[2][1])))) * 57.29577951308232d);
        dArr5[2] = MatrixMathHelper.roundTo3Places((-Math.atan2(dArr9[1][0], dArr9[0][0])) * 57.29577951308232d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static RenderableView getRenderableViewByTag(int i) {
        return mTagToRenderableView.get(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invalidateSvgView(VirtualView virtualView) {
        SvgView svgView = virtualView.getSvgView();
        if (svgView != null) {
            svgView.invalidate();
        }
        if (virtualView instanceof TextView) {
            ((TextView) virtualView).getTextContainer().clearChildCache();
        }
    }

    private static boolean isZero(double d) {
        return !Double.isNaN(d) && Math.abs(d) < 1.0E-5d;
    }

    private static void resetTransformProperty(View view) {
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
        view.setRotation(0.0f);
        view.setRotationX(0.0f);
        view.setRotationY(0.0f);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        view.setCameraDistance(0.0f);
    }

    static void runWhenViewIsAvailable(int i, Runnable runnable) {
        mTagToRunnable.put(i, runnable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setRenderableView(int i, RenderableView renderableView) {
        mTagToRenderableView.put(i, renderableView);
        Runnable runnable = mTagToRunnable.get(i);
        if (runnable != null) {
            runnable.run();
            mTagToRunnable.delete(i);
        }
    }

    private static void setTransformProperty(View view, ReadableArray readableArray) {
        TransformHelper.processTransform(readableArray, sTransformDecompositionArray);
        decomposeMatrix();
        view.setTranslationX(PixelUtil.toPixelFromDIP((float) sMatrixDecompositionContext.translation[0]));
        view.setTranslationY(PixelUtil.toPixelFromDIP((float) sMatrixDecompositionContext.translation[1]));
        view.setRotation((float) sMatrixDecompositionContext.rotationDegrees[2]);
        view.setRotationX((float) sMatrixDecompositionContext.rotationDegrees[0]);
        view.setRotationY((float) sMatrixDecompositionContext.rotationDegrees[1]);
        view.setScaleX((float) sMatrixDecompositionContext.scale[0]);
        view.setScaleY((float) sMatrixDecompositionContext.scale[1]);
        double[] dArr = sMatrixDecompositionContext.perspective;
        if (dArr.length > 2) {
            float f = (float) dArr[2];
            if (f == 0.0f) {
                f = 7.8125E-4f;
            }
            float f2 = (-1.0f) / f;
            float f3 = DisplayMetricsHolder.getScreenDisplayMetrics().density;
            view.setCameraDistance(f3 * f3 * f2 * CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    @Nonnull
    public String getName() {
        return this.mClassName;
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
    public Class<RenderableShadowNode> getShadowNodeClass() {
        return RenderableShadowNode.class;
    }

    @ReactProp(name = "clipPath")
    public void setClipPath(VirtualView virtualView, String str) {
        virtualView.setClipPath(str);
    }

    @ReactProp(name = "clipRule")
    public void setClipRule(VirtualView virtualView, int i) {
        virtualView.setClipRule(i);
    }

    @ReactProp(name = "display")
    public void setDisplay(VirtualView virtualView, String str) {
        virtualView.setDisplay(str);
    }

    @ReactProp(name = "fill")
    public void setFill(RenderableView renderableView, @Nullable Dynamic dynamic) {
        renderableView.setFill(dynamic);
    }

    @ReactProp(defaultFloat = 1.0f, name = "fillOpacity")
    public void setFillOpacity(RenderableView renderableView, float f) {
        renderableView.setFillOpacity(f);
    }

    @ReactProp(defaultInt = 1, name = "fillRule")
    public void setFillRule(RenderableView renderableView, int i) {
        renderableView.setFillRule(i);
    }

    @ReactProp(name = "markerEnd")
    public void setMarkerEnd(VirtualView virtualView, String str) {
        virtualView.setMarkerEnd(str);
    }

    @ReactProp(name = "markerMid")
    public void setMarkerMid(VirtualView virtualView, String str) {
        virtualView.setMarkerMid(str);
    }

    @ReactProp(name = "markerStart")
    public void setMarkerStart(VirtualView virtualView, String str) {
        virtualView.setMarkerStart(str);
    }

    @ReactProp(name = "mask")
    public void setMask(VirtualView virtualView, String str) {
        virtualView.setMask(str);
    }

    @ReactProp(name = "matrix")
    public void setMatrix(VirtualView virtualView, Dynamic dynamic) {
        virtualView.setMatrix(dynamic);
    }

    @ReactProp(name = "name")
    public void setName(VirtualView virtualView, String str) {
        virtualView.setName(str);
    }

    @ReactProp(name = ViewProps.ON_LAYOUT)
    public void setOnLayout(VirtualView virtualView, boolean z) {
        virtualView.setOnLayout(z);
    }

    @ReactProp(name = ViewProps.POINTER_EVENTS)
    public void setPointerEvents(VirtualView virtualView, @androidx.annotation.Nullable String str) {
        if (str == null) {
            virtualView.setPointerEvents(PointerEvents.AUTO);
        } else {
            virtualView.setPointerEvents(PointerEvents.valueOf(str.toUpperCase(Locale.US).replace(ProcessIdUtil.DEFAULT_PROCESSID, "_")));
        }
    }

    @ReactProp(name = "propList")
    public void setPropList(RenderableView renderableView, @Nullable ReadableArray readableArray) {
        renderableView.setPropList(readableArray);
    }

    @ReactProp(name = "responsible")
    public void setResponsible(VirtualView virtualView, boolean z) {
        virtualView.setResponsible(z);
    }

    @ReactProp(name = "stroke")
    public void setStroke(RenderableView renderableView, @Nullable Dynamic dynamic) {
        renderableView.setStroke(dynamic);
    }

    @ReactProp(name = "strokeDasharray")
    public void setStrokeDasharray(RenderableView renderableView, @Nullable ReadableArray readableArray) {
        renderableView.setStrokeDasharray(readableArray);
    }

    @ReactProp(name = "strokeDashoffset")
    public void setStrokeDashoffset(RenderableView renderableView, float f) {
        renderableView.setStrokeDashoffset(f);
    }

    @ReactProp(defaultInt = 1, name = "strokeLinecap")
    public void setStrokeLinecap(RenderableView renderableView, int i) {
        renderableView.setStrokeLinecap(i);
    }

    @ReactProp(defaultInt = 1, name = "strokeLinejoin")
    public void setStrokeLinejoin(RenderableView renderableView, int i) {
        renderableView.setStrokeLinejoin(i);
    }

    @ReactProp(defaultFloat = 4.0f, name = "strokeMiterlimit")
    public void setStrokeMiterlimit(RenderableView renderableView, float f) {
        renderableView.setStrokeMiterlimit(f);
    }

    @ReactProp(defaultFloat = 1.0f, name = "strokeOpacity")
    public void setStrokeOpacity(RenderableView renderableView, float f) {
        renderableView.setStrokeOpacity(f);
    }

    @ReactProp(name = "strokeWidth")
    public void setStrokeWidth(RenderableView renderableView, Dynamic dynamic) {
        renderableView.setStrokeWidth(dynamic);
    }

    @ReactProp(name = ViewProps.TRANSFORM)
    public void setTransform(VirtualView virtualView, Dynamic dynamic) {
        if (dynamic.getType() != ReadableType.Array) {
            return;
        }
        ReadableArray asArray = dynamic.asArray();
        if (asArray == null) {
            resetTransformProperty(virtualView);
        } else {
            setTransformProperty(virtualView, asArray);
        }
        Matrix matrix = virtualView.getMatrix();
        virtualView.mTransform = matrix;
        virtualView.mTransformInvertible = matrix.invert(virtualView.mInvTransform);
    }

    @ReactProp(name = "vectorEffect")
    public void setVectorEffect(RenderableView renderableView, int i) {
        renderableView.setVectorEffect(i);
    }

    private RenderableViewManager(SVGClass sVGClass) {
        this.svgClass = sVGClass;
        this.mClassName = sVGClass.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public void addEventEmitters(@Nonnull ThemedReactContext themedReactContext, @Nonnull VirtualView virtualView) {
        super.addEventEmitters(themedReactContext, (ThemedReactContext) virtualView);
        virtualView.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() { // from class: com.horcrux.svg.RenderableViewManager.1
            @Override // android.view.ViewGroup.OnHierarchyChangeListener
            public void onChildViewAdded(View view, View view2) {
                if (view instanceof VirtualView) {
                    RenderableViewManager.this.invalidateSvgView((VirtualView) view);
                }
            }

            @Override // android.view.ViewGroup.OnHierarchyChangeListener
            public void onChildViewRemoved(View view, View view2) {
                if (view instanceof VirtualView) {
                    RenderableViewManager.this.invalidateSvgView((VirtualView) view);
                }
            }
        });
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
    /* renamed from: createShadowNodeInstance */
    public LayoutShadowNode mo10136createShadowNodeInstance() {
        return new RenderableShadowNode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    @Nonnull
    /* renamed from: createViewInstance  reason: collision with other method in class */
    public VirtualView mo12880createViewInstance(@Nonnull ThemedReactContext themedReactContext) {
        switch (this.svgClass.ordinal()) {
            case 0:
                return new GroupView(themedReactContext);
            case 1:
                return new PathView(themedReactContext);
            case 2:
                return new TextView(themedReactContext);
            case 3:
                return new TSpanView(themedReactContext);
            case 4:
                return new TextPathView(themedReactContext);
            case 5:
                return new ImageView(themedReactContext);
            case 6:
                return new CircleView(themedReactContext);
            case 7:
                return new EllipseView(themedReactContext);
            case 8:
                return new LineView(themedReactContext);
            case 9:
                return new RectView(themedReactContext);
            case 10:
                return new ClipPathView(themedReactContext);
            case 11:
                return new DefsView(themedReactContext);
            case 12:
                return new UseView(themedReactContext);
            case 13:
                return new SymbolView(themedReactContext);
            case 14:
                return new LinearGradientView(themedReactContext);
            case 15:
                return new RadialGradientView(themedReactContext);
            case 16:
                return new PatternView(themedReactContext);
            case 17:
                return new MaskView(themedReactContext);
            case 18:
                return new MarkerView(themedReactContext);
            case 19:
                return new ForeignObjectView(themedReactContext);
            default:
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unexpected type ");
                outline107.append(this.svgClass.toString());
                throw new IllegalStateException(outline107.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public void onAfterUpdateTransaction(@Nonnull VirtualView virtualView) {
        super.onAfterUpdateTransaction((RenderableViewManager) virtualView);
        invalidateSvgView(virtualView);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(@Nonnull VirtualView virtualView) {
        super.onDropViewInstance((RenderableViewManager) virtualView);
        mTagToRenderableView.remove(virtualView.getId());
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
    public void setOpacity(@Nonnull VirtualView virtualView, float f) {
        virtualView.setOpacity(f);
    }
}
