package com.example.gabriel.soundrecorder.util

import android.content.Context
import com.example.gabriel.soundrecorder.recorder.RecorderRepository
import com.example.gabriel.soundrecorder.recorder.RecorderViewModelProvider

object InjectorUtils {
    fun provideRecorderViewModelFactory(): RecorderViewModelProvider {
        val recorderRepository = RecorderRepository.getInstance()
        return RecorderViewModelProvider(recorderRepository)
    }

}