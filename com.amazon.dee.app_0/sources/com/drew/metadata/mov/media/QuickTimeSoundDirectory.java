package com.drew.metadata.mov.media;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.mov.QuickTimeDirectory;
import java.util.HashMap;
/* loaded from: classes2.dex */
public class QuickTimeSoundDirectory extends QuickTimeDirectory {
    public static final int TAG_AUDIO_FORMAT = 769;
    public static final int TAG_AUDIO_SAMPLE_RATE = 772;
    public static final int TAG_AUDIO_SAMPLE_SIZE = 771;
    public static final int TAG_NUMBER_OF_CHANNELS = 770;
    public static final int TAG_SOUND_BALANCE = 773;
    @NotNull
    protected static final HashMap<Integer, String> _tagNameMap = new HashMap<>();

    static {
        QuickTimeMediaDirectory.addQuickTimeMediaTags(_tagNameMap);
        _tagNameMap.put(769, "Format");
        _tagNameMap.put(770, "Number of Channels");
        _tagNameMap.put(771, "Sample Size");
        _tagNameMap.put(772, "Sample Rate");
        _tagNameMap.put(773, "Balance");
    }

    public QuickTimeSoundDirectory() {
        setDescriptor(new QuickTimeSoundDescriptor(this));
    }

    @Override // com.drew.metadata.mov.QuickTimeDirectory, com.drew.metadata.Directory
    @NotNull
    public String getName() {
        return "QuickTime Sound";
    }

    @Override // com.drew.metadata.mov.QuickTimeDirectory, com.drew.metadata.Directory
    @NotNull
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }
}
