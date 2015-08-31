package com.fcu.photocollage.collage;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import com.fcu.photocollage.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Saki on 15/8/30.
 */


public class sound {

    private static MediaPlayer music;
    private static SoundPool soundPool;
    private static boolean musicSt = true; //音樂開關
    private static boolean soundSt = true; //音效開關
    private Context context;
    private static final int[] musicId = {R.raw.backgroundmusic};
    private static Map<Integer,Integer> soundMap; //音效資源id與加載過後的音源id的映射關係表

    /**
     * 初始化方法
     * @param c
     */
    public sound(Context c)
    {
        context = c;
        initMusic();
        initSound();
    }

    //初始化音效播放器
    private void initSound()
    {
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC,100);
        soundMap = new HashMap<Integer,Integer>();
        soundMap.put(R.raw.backgroundmusic, soundPool.load(context, R.raw.backgroundmusic, 1));
        soundMap.put(R.raw.backgroundmusic, soundPool.load(context, R.raw.backgroundmusic, 1));
    }

    //初始化音樂播放器
    private void initMusic()
    {
        int r = new Random().nextInt(musicId.length);
        music = MediaPlayer.create(context,musicId[r]);
        music.setLooping(true);
    }

    /**
     * 播放音效
     * @param resId 音效資源id
     */
    public static void playSound(int resId)
    {
        if(soundSt == false)
            return;

        Integer soundId = soundMap.get(resId);
        if(soundId != null)
            soundPool.play(soundId, 1, 1, 1, 0, 1);
    }

    /**
     * 切換一首音樂並播放
     */
    public void changeAndPlayMusic()
    {
        if(music != null)
            music.release();
        initMusic();
        setMusicSt(true);
    }

    /**
     * 獲得音樂開關狀態
     * @return
     */
    public static boolean isMusicSt() {
        return musicSt;
    }

    /**
     * 設置音樂開關
     * @param musicSt
     */
    public static void setMusicSt(boolean musicSt) {
        sound.musicSt = musicSt;
        if(musicSt)
            music.start();
        else
            music.stop();
    }

    /**
     * 獲得音效開關狀態
     * @return
     */
    public static boolean isSoundSt() {
        return soundSt;
    }

    /**
     * 設置音效開關
     * @param soundSt
     */
    public static void setSoundSt(boolean soundSt) {
        sound.soundSt = soundSt;
    }

    /**
     * 釋放資源
     */
    public void recyle()
    {
        music.release();
        soundPool.release();
        soundMap.clear();
    }
}

