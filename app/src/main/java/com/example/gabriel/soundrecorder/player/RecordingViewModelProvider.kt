package com.example.gabriel.soundrecorder.player

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.gabriel.soundrecorder.recorder.RecorderRepository
import com.example.gabriel.soundrecorder.recorder.RecorderViewModel

class RecordingViewModelProvider(val recordingRepository: RecordingRepository): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RecordingViewModel(recordingRepository) as T
    }
}