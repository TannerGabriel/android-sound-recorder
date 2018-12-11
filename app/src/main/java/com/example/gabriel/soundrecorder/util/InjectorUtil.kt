package com.example.gabriel.soundrecorder.util

import android.content.Context
import com.example.gabriel.soundrecorder.player.RecordingRepository
import com.example.gabriel.soundrecorder.player.RecordingViewModelProvider
import com.example.gabriel.soundrecorder.recorder.RecorderRepository
import com.example.gabriel.soundrecorder.recorder.RecorderViewModelProvider

object InjectorUtils {
    fun provideRecorderViewModelFactory(): RecorderViewModelProvider {
        val recorderRepository = RecorderRepository.getInstance()
        return RecorderViewModelProvider(recorderRepository)
    }

    fun provideRecordingViewModelFactory(): RecordingViewModelProvider {
        val recordingRepository = RecordingRepository.getInstance()
        return RecordingViewModelProvider(recordingRepository)
    }
}