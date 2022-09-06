package com.amazon.comms.ringservice.util;

import android.util.Pair;
import com.amazon.comms.calling.service.VideoConstraints;
import com.amazon.comms.log.CommsLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.webrtc.LocalAudioVideoShim;
/* loaded from: classes12.dex */
public class VideoConstraintsManager {
    private static final CommsLogger log = CommsLogger.getLogger(VideoConstraintsManager.class);
    private VideoConstraints dynamicVideoConstraints = null;
    private VideoConstraints remoteRequestedVideoConstraints = null;
    private VideoConstraints localVideoConstraints = null;
    private VideoConstraints reducedVideoConstraints = null;
    private VideoConstraints thermalVideoConstraints = null;
    private VideoConstraints constraintsUntilFirstIceConn = null;

    public static List<Pair<String, Integer>> convertVideoConstraintToMediaConstraintList(VideoConstraints videoConstraints) {
        return convertVideoConstraintToMediaConstraintList(new ArrayList(), videoConstraints);
    }

    public static VideoConstraints getVideoConstraints(Map<Integer, VideoConstraints> map, int i) {
        Map.Entry floorEntry = new TreeMap(map).floorEntry(Integer.valueOf(i));
        if (floorEntry == null) {
            log.e("Unable to find a matching VideoConstraint in the map");
            return null;
        }
        return (VideoConstraints) floorEntry.getValue();
    }

    private boolean isResolutionAndFpsNonZero(VideoConstraints videoConstraints) {
        return videoConstraints != null && videoConstraints.getVideoHeight() > 0 && videoConstraints.getVideoWidth() > 0 && videoConstraints.getVideoFps() > 0;
    }

    public static VideoConstraints minVideoConstraints(VideoConstraints videoConstraints, VideoConstraints videoConstraints2) {
        return videoConstraints2 == null ? videoConstraints : (videoConstraints != null && videoConstraints.compareTo(videoConstraints2) < 0) ? videoConstraints : videoConstraints2;
    }

    private static VideoConstraints minimizeResolutionAndFpsIndependently(VideoConstraints videoConstraints, VideoConstraints videoConstraints2) {
        if (videoConstraints2 == null) {
            return videoConstraints;
        }
        if (videoConstraints == null) {
            return videoConstraints2;
        }
        int videoWidth = videoConstraints.getVideoWidth() * videoConstraints.getVideoHeight();
        int videoWidth2 = videoConstraints2.getVideoWidth() * videoConstraints2.getVideoHeight();
        int videoFps = videoConstraints.getVideoFps();
        int videoFps2 = videoConstraints2.getVideoFps();
        if (videoWidth == videoWidth2 && videoConstraints.getVideoFps() == videoConstraints2.getVideoFps()) {
            return videoConstraints;
        }
        int videoWidth3 = videoConstraints2.getVideoWidth();
        int videoHeight = videoConstraints2.getVideoHeight();
        int videoFps3 = videoConstraints2.getVideoFps();
        if (videoWidth2 == 0 || (videoWidth > 0 && videoWidth2 > 0 && videoWidth2 > videoWidth)) {
            videoWidth3 = videoConstraints.getVideoWidth();
            videoHeight = videoConstraints.getVideoHeight();
        }
        if (videoFps2 == 0 || (videoFps2 > 0 && videoFps > 0 && videoFps2 > videoFps)) {
            videoFps3 = videoFps;
        }
        return new VideoConstraints(videoWidth3, videoHeight, videoFps3);
    }

    public VideoConstraints getConstraintsUntilFirstIceConn() {
        return this.constraintsUntilFirstIceConn;
    }

    public VideoConstraints getCurrentVideoConstraints() {
        return minimizeResolutionAndFpsIndependently(minVideoConstraints(minVideoConstraints(minVideoConstraints(minVideoConstraints(this.reducedVideoConstraints, this.localVideoConstraints), this.remoteRequestedVideoConstraints), this.dynamicVideoConstraints), this.thermalVideoConstraints), this.constraintsUntilFirstIceConn);
    }

    public VideoConstraints getLocalVideoConstraints() {
        return this.localVideoConstraints;
    }

    public VideoConstraints getReducedVideoConstraints() {
        return this.reducedVideoConstraints;
    }

    public VideoConstraints getRemoteRequestedVideoConstraints() {
        return this.remoteRequestedVideoConstraints;
    }

    public VideoConstraints getThermalVideoConstraints() {
        return this.thermalVideoConstraints;
    }

    public void printAllConstraints() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("dynamicVideoConstraints: ");
        outline107.append(this.dynamicVideoConstraints);
        outline107.append(" , ");
        outline107.append(" localVideoConstraints: ");
        outline107.append(this.localVideoConstraints);
        outline107.append(" , ");
        outline107.append(" remoteRequestedVideoConstraints: ");
        outline107.append(this.remoteRequestedVideoConstraints);
        outline107.append(" , ");
        outline107.append(" reducedVideoConstraints: ");
        outline107.append(this.reducedVideoConstraints);
        outline107.append(" , ");
        outline107.append(" thermalVideoConstraints: ");
        outline107.append(this.thermalVideoConstraints);
        outline107.append(" , ");
        outline107.append(" constraintsUntilFirstIceConn: ");
        outline107.append(this.constraintsUntilFirstIceConn);
        log.i(outline107.toString());
    }

    public void setConstraintsUntilFirstIceConn(VideoConstraints videoConstraints) {
        this.constraintsUntilFirstIceConn = videoConstraints;
    }

    public void setDynamicVideoConstraints(VideoConstraints videoConstraints) {
        this.dynamicVideoConstraints = videoConstraints;
    }

    public void setLocalVideoConstraints(VideoConstraints videoConstraints) {
        this.localVideoConstraints = videoConstraints;
    }

    public void setReducedVideoConstraints(VideoConstraints videoConstraints) {
        this.reducedVideoConstraints = videoConstraints;
    }

    public void setRemoteRequestedVideoConstraints(VideoConstraints videoConstraints) {
        this.remoteRequestedVideoConstraints = videoConstraints;
    }

    public void setThermalVideoConstraints(VideoConstraints videoConstraints) {
        this.thermalVideoConstraints = videoConstraints;
    }

    public static List<Pair<String, Integer>> convertVideoConstraintToMediaConstraintList(List<Pair<String, Integer>> list, VideoConstraints videoConstraints) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(Pair.create("maxWidth", Integer.valueOf(videoConstraints.getVideoWidth())));
        list.add(Pair.create("maxHeight", Integer.valueOf(videoConstraints.getVideoHeight())));
        list.add(Pair.create(LocalAudioVideoShim.MAX_VIDEO_FPS_CONSTRAINT, Integer.valueOf(videoConstraints.getVideoFps())));
        return list;
    }
}
