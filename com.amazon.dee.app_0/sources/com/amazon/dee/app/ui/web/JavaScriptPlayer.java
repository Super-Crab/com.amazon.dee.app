package com.amazon.dee.app.ui.web;

import android.content.Context;
import android.media.MediaPlayer;
import androidx.annotation.NonNull;
import com.amazon.dee.app.R;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
/* loaded from: classes12.dex */
public class JavaScriptPlayer {
    Context context;
    MediaPlayer mediaPlayer;
    MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() { // from class: com.amazon.dee.app.ui.web.-$$Lambda$JavaScriptPlayer$RoiiMx8Q5flamQ1xzmIFYT76FGE
        @Override // android.media.MediaPlayer.OnCompletionListener
        public final void onCompletion(MediaPlayer mediaPlayer) {
            JavaScriptPlayer.this.lambda$new$0$JavaScriptPlayer(mediaPlayer);
        }
    };
    MediaPlayer.OnErrorListener onErrorListener = new MediaPlayer.OnErrorListener() { // from class: com.amazon.dee.app.ui.web.-$$Lambda$JavaScriptPlayer$k6bDobjGUdIvOdz4GnLQ6lq0i4g
        @Override // android.media.MediaPlayer.OnErrorListener
        public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            return JavaScriptPlayer.this.lambda$new$1$JavaScriptPlayer(mediaPlayer, i, i2);
        }
    };
    LinkedList<PlayerListener> playerListeners = new LinkedList<>();

    /* loaded from: classes12.dex */
    public interface PlayerListener {
        void onComplete();

        void onError(@NonNull String str);
    }

    public JavaScriptPlayer(Context context) {
        this.context = context;
    }

    public void addPlayerListener(@NonNull PlayerListener playerListener) {
        this.playerListeners.add(playerListener);
    }

    public /* synthetic */ void lambda$new$0$JavaScriptPlayer(MediaPlayer mediaPlayer) {
        Iterator<PlayerListener> it2 = this.playerListeners.iterator();
        while (it2.hasNext()) {
            it2.next().onComplete();
            it2.remove();
        }
    }

    public /* synthetic */ boolean lambda$new$1$JavaScriptPlayer(MediaPlayer mediaPlayer, int i, int i2) {
        String string;
        if (i == 100) {
            string = this.context.getString(R.string.media_player_server_died_error);
        } else if (i != 200) {
            string = this.context.getString(R.string.media_player_unknown_error);
        } else {
            string = this.context.getString(R.string.media_player_progressive_playback_error);
        }
        Iterator<PlayerListener> it2 = this.playerListeners.iterator();
        while (it2.hasNext()) {
            it2.next().onError(string);
        }
        return false;
    }

    public void play(@NonNull String str) throws IOException {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer == null) {
            this.mediaPlayer = new MediaPlayer();
            this.mediaPlayer.setOnCompletionListener(this.onCompletionListener);
            this.mediaPlayer.setOnErrorListener(this.onErrorListener);
        } else {
            mediaPlayer.reset();
        }
        this.mediaPlayer.setAudioStreamType(3);
        this.mediaPlayer.setDataSource(str);
        this.mediaPlayer.prepare();
        this.mediaPlayer.start();
    }

    public void release() {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.mediaPlayer = null;
        }
    }

    public void removePlayerListener(@NonNull PlayerListener playerListener) {
        this.playerListeners.remove(playerListener);
    }
}
