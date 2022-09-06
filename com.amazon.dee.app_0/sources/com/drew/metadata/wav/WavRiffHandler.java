package com.drew.metadata.wav;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.drew.imaging.riff.RiffHandler;
import com.drew.lang.ByteArrayReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import java.io.IOException;
/* loaded from: classes2.dex */
public class WavRiffHandler implements RiffHandler {
    @NotNull
    private String _currentList = "";
    @NotNull
    private final WavDirectory _directory = new WavDirectory();

    public WavRiffHandler(@NotNull Metadata metadata) {
        metadata.addDirectory(this._directory);
    }

    @Override // com.drew.imaging.riff.RiffHandler
    public void processChunk(@NotNull String str, @NotNull byte[] bArr) {
        WavDirectory wavDirectory;
        String str2;
        try {
            if (!str.equals(WavDirectory.CHUNK_FORMAT)) {
                if (!str.equals("data")) {
                    if (!WavDirectory._tagIntegerMap.containsKey(str)) {
                        return;
                    }
                    this._directory.setString(WavDirectory._tagIntegerMap.get(str).intValue(), new String(bArr).substring(0, bArr.length - 1));
                    return;
                }
                try {
                    if (!this._directory.containsTag(4)) {
                        return;
                    }
                    double length = bArr.length / this._directory.getDouble(4);
                    int i = (int) length;
                    Integer valueOf = Integer.valueOf(i / ((int) Math.pow(60.0d, 2.0d)));
                    Integer valueOf2 = Integer.valueOf((i / ((int) Math.pow(60.0d, 1.0d))) - (valueOf.intValue() * 60));
                    this._directory.setString(16, String.format("%1$02d:%2$02d:%3$02d", valueOf, valueOf2, Integer.valueOf((int) Math.round((length / Math.pow(60.0d, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR)) - (valueOf2.intValue() * 60)))));
                    return;
                } catch (MetadataException unused) {
                    this._directory.addError("Error calculating duration: bytes per second not found");
                    return;
                }
            }
            ByteArrayReader byteArrayReader = new ByteArrayReader(bArr);
            byteArrayReader.setMotorolaByteOrder(false);
            short int16 = byteArrayReader.getInt16(0);
            short int162 = byteArrayReader.getInt16(2);
            int int32 = byteArrayReader.getInt32(4);
            int int322 = byteArrayReader.getInt32(8);
            short int163 = byteArrayReader.getInt16(12);
            if (int16 == 1) {
                this._directory.setInt(6, byteArrayReader.getInt16(14));
                wavDirectory = this._directory;
                str2 = WavDirectory._audioEncodingMap.get(Integer.valueOf(int16));
            } else if (WavDirectory._audioEncodingMap.containsKey(Integer.valueOf(int16))) {
                wavDirectory = this._directory;
                str2 = WavDirectory._audioEncodingMap.get(Integer.valueOf(int16));
            } else {
                wavDirectory = this._directory;
                str2 = "Unknown";
            }
            wavDirectory.setString(1, str2);
            this._directory.setInt(2, int162);
            this._directory.setInt(3, int32);
            this._directory.setInt(4, int322);
            this._directory.setInt(5, int163);
        } catch (IOException e) {
            this._directory.addError(e.getMessage());
        }
    }

    @Override // com.drew.imaging.riff.RiffHandler
    public boolean shouldAcceptChunk(@NotNull String str) {
        return str.equals(WavDirectory.CHUNK_FORMAT) || (this._currentList.equals(WavDirectory.LIST_INFO) && WavDirectory._tagIntegerMap.containsKey(str)) || str.equals("data");
    }

    @Override // com.drew.imaging.riff.RiffHandler
    public boolean shouldAcceptList(@NotNull String str) {
        if (str.equals(WavDirectory.LIST_INFO)) {
            this._currentList = WavDirectory.LIST_INFO;
            return true;
        }
        this._currentList = "";
        return false;
    }

    @Override // com.drew.imaging.riff.RiffHandler
    public boolean shouldAcceptRiffIdentifier(@NotNull String str) {
        return str.equals(WavDirectory.FORMAT);
    }
}
