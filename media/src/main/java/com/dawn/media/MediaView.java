package com.dawn.media;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import java.io.File;

public class MediaView extends RelativeLayout {
    private SimpleExoPlayerView playerView;
    private MediaUtil mediaUtil;
    public MediaView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.view_media, this);
        mediaUtil = new MediaUtil(context);
        mediaUtil.createMedia();
        initView();
        addClickEvent();
    }

    public MediaView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_media, this);
        mediaUtil = new MediaUtil(context);
        mediaUtil.createMedia();
        initView();
        addClickEvent();
    }

    public MediaView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MediaView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initView(){
        playerView = findViewById(R.id.simpleExoPlayerView);
    }

    private void addClickEvent(){
        playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
        if(mediaUtil != null){
            mediaUtil.playMedia(playerView);
            mediaUtil.addPlayListener(new Player.EventListener() {
                @Override
                public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

                }

                @Override
                public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

                }

                @Override
                public void onLoadingChanged(boolean isLoading) {

                }

                @Override
                public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

                }

                @Override
                public void onRepeatModeChanged(int repeatMode) {

                }

                @Override
                public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

                }

                @Override
                public void onPlayerError(ExoPlaybackException error) {
                    if(mListener != null)
                        mListener.onMediaError();
                }

                @Override
                public void onPositionDiscontinuity(int reason) {

                }

                @Override
                public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

                }

                @Override
                public void onSeekProcessed() {

                }
            });
        }
    }

    /**
     * 播放视频
     * @param filePath 视频地址
     */
    public void playMedia(String filePath){
        if(TextUtils.isEmpty(filePath))
            return;
        File file = new File(filePath);
        if(!file.exists())
            return;
        Uri uri = Uri.fromFile(file);
        if(mediaUtil != null)
            mediaUtil.prepareMedia(uri, 0);
    }

    /**
     * 释放资源
     */
    public void releaseMedia(){
        if(mediaUtil != null)
            mediaUtil.releaseMedia();
    }
    private OnMediaListener mListener;
    public void addListener(OnMediaListener listener){
        mListener = listener;
    }

}
