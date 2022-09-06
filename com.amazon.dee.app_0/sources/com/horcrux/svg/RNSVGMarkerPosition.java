package com.horcrux.svg;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes3.dex */
class RNSVGMarkerPosition {
    private static boolean auto_start_reverse_;
    private static int element_index_;
    private static Point in_slope_;
    private static Point origin_;
    private static Point out_slope_;
    private static ArrayList<RNSVGMarkerPosition> positions_;
    private static Point subpath_start_;
    double angle;
    Point origin;
    RNSVGMarkerType type;

    /* renamed from: com.horcrux.svg.RNSVGMarkerPosition$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$horcrux$svg$ElementType = new int[ElementType.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$horcrux$svg$RNSVGMarkerType;

        static {
            try {
                $SwitchMap$com$horcrux$svg$ElementType[ElementType.kCGPathElementAddCurveToPoint.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$horcrux$svg$ElementType[ElementType.kCGPathElementAddQuadCurveToPoint.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$horcrux$svg$ElementType[ElementType.kCGPathElementMoveToPoint.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$horcrux$svg$ElementType[ElementType.kCGPathElementAddLineToPoint.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$horcrux$svg$ElementType[ElementType.kCGPathElementCloseSubpath.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $SwitchMap$com$horcrux$svg$RNSVGMarkerType = new int[RNSVGMarkerType.values().length];
            try {
                $SwitchMap$com$horcrux$svg$RNSVGMarkerType[RNSVGMarkerType.kStartMarker.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$horcrux$svg$RNSVGMarkerType[RNSVGMarkerType.kMidMarker.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$horcrux$svg$RNSVGMarkerType[RNSVGMarkerType.kEndMarker.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    private RNSVGMarkerPosition(RNSVGMarkerType rNSVGMarkerType, Point point, double d) {
        this.type = rNSVGMarkerType;
        this.origin = point;
        this.angle = d;
    }

    private static double BisectingAngle(double d, double d2) {
        if (Math.abs(d - d2) > 180.0d) {
            d += 360.0d;
        }
        return (d + d2) / 2.0d;
    }

    private static void ComputeQuadTangents(SegmentData segmentData, Point point, Point point2, Point point3) {
        segmentData.start_tangent = subtract(point2, point);
        segmentData.end_tangent = subtract(point3, point2);
        if (isZero(segmentData.start_tangent)) {
            segmentData.start_tangent = segmentData.end_tangent;
        } else if (!isZero(segmentData.end_tangent)) {
        } else {
            segmentData.end_tangent = segmentData.start_tangent;
        }
    }

    private static double CurrentAngle(RNSVGMarkerType rNSVGMarkerType) {
        double rad2deg = rad2deg(SlopeAngleRadians(in_slope_));
        double rad2deg2 = rad2deg(SlopeAngleRadians(out_slope_));
        int ordinal = rNSVGMarkerType.ordinal();
        if (ordinal == 0) {
            return auto_start_reverse_ ? rad2deg2 + 180.0d : rad2deg2;
        } else if (ordinal == 1) {
            return BisectingAngle(rad2deg, rad2deg2);
        } else {
            return ordinal != 2 ? FrostVideoEffectController.VIDEO_STRENGTH_CLEAR : rad2deg;
        }
    }

    private static SegmentData ExtractPathElementFeatures(PathElement pathElement) {
        SegmentData segmentData = new SegmentData();
        Point[] pointArr = pathElement.points;
        int ordinal = pathElement.type.ordinal();
        if (ordinal == 0) {
            segmentData.position = pointArr[2];
            segmentData.start_tangent = subtract(pointArr[0], origin_);
            segmentData.end_tangent = subtract(pointArr[2], pointArr[1]);
            if (isZero(segmentData.start_tangent)) {
                ComputeQuadTangents(segmentData, pointArr[0], pointArr[1], pointArr[2]);
            } else if (isZero(segmentData.end_tangent)) {
                ComputeQuadTangents(segmentData, origin_, pointArr[0], pointArr[1]);
            }
        } else if (ordinal == 1) {
            segmentData.position = pointArr[1];
            ComputeQuadTangents(segmentData, origin_, pointArr[0], pointArr[1]);
        } else if (ordinal == 2 || ordinal == 3) {
            segmentData.position = pointArr[0];
            segmentData.start_tangent = subtract(segmentData.position, origin_);
            segmentData.end_tangent = subtract(segmentData.position, origin_);
        } else if (ordinal == 4) {
            segmentData.position = subpath_start_;
            segmentData.start_tangent = subtract(segmentData.position, origin_);
            segmentData.end_tangent = subtract(segmentData.position, origin_);
        }
        return segmentData;
    }

    private static void PathIsDone() {
        positions_.add(new RNSVGMarkerPosition(RNSVGMarkerType.kEndMarker, origin_, CurrentAngle(RNSVGMarkerType.kEndMarker)));
    }

    private static double SlopeAngleRadians(Point point) {
        return Math.atan2(point.y, point.x);
    }

    private static void UpdateFromPathElement(PathElement pathElement) {
        SegmentData ExtractPathElementFeatures = ExtractPathElementFeatures(pathElement);
        out_slope_ = ExtractPathElementFeatures.start_tangent;
        int i = element_index_;
        if (i > 0) {
            RNSVGMarkerType rNSVGMarkerType = i == 1 ? RNSVGMarkerType.kStartMarker : RNSVGMarkerType.kMidMarker;
            positions_.add(new RNSVGMarkerPosition(rNSVGMarkerType, origin_, CurrentAngle(rNSVGMarkerType)));
        }
        in_slope_ = ExtractPathElementFeatures.end_tangent;
        origin_ = ExtractPathElementFeatures.position;
        ElementType elementType = pathElement.type;
        if (elementType == ElementType.kCGPathElementMoveToPoint) {
            subpath_start_ = pathElement.points[0];
        } else if (elementType == ElementType.kCGPathElementCloseSubpath) {
            subpath_start_ = new Point(FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
        }
        element_index_++;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ArrayList<RNSVGMarkerPosition> fromPath(ArrayList<PathElement> arrayList) {
        positions_ = new ArrayList<>();
        element_index_ = 0;
        origin_ = new Point(FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
        subpath_start_ = new Point(FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
        Iterator<PathElement> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            UpdateFromPathElement(it2.next());
        }
        PathIsDone();
        return positions_;
    }

    private static boolean isZero(Point point) {
        return point.x == FrostVideoEffectController.VIDEO_STRENGTH_CLEAR && point.y == FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
    }

    private static double rad2deg(double d) {
        return d * 57.29577951308232d;
    }

    private static Point subtract(Point point, Point point2) {
        return new Point(point2.x - point.x, point2.y - point.y);
    }
}
