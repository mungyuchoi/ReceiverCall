package com.mungyu.receivercall;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.util.Log;

import java.util.HashMap;

public class MySoundPlayer {
    public static final int DING_DONG = R.raw.sound;

    private static SoundPool soundPool;
    private static HashMap<Integer, Integer> soundPoolMap;

    // sound media initialize
    public static void initSounds(Context context) {
        Log.i("MQ!", "initSounds");
        if (soundPool != null) {
            soundPool.stop(DING_DONG);
        }
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();

        soundPoolMap = new HashMap(2);
        soundPoolMap.put(DING_DONG, soundPool.load(context, DING_DONG, 1));
    }

    public static void play(int raw_id) {
        Log.i("MQ!", "play");
        if (soundPoolMap.containsKey(raw_id)) {
            soundPool.play(soundPoolMap.get(raw_id), 1, 1, 1, 0, 1f);
        }
    }
}
