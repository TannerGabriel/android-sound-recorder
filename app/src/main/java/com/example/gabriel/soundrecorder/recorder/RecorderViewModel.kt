package com.example.gabriel.soundrecorder.recorder

import android.arch.lifecycle.ViewModel

class RecorderViewModel(val recorderRepository: RecorderRepository): ViewModel() {
    fun startRecording() = recorderRepository.startRecording()

    fun stopRecording() = recorderRepository.stopRecording()

    fun pauseRecording() = recorderRepository.pauseRecording()

    fun resumeRecording() = recorderRepository.resumeRecording()

    fun getRecordingTime() = recorderRepository.getRecordingTime()

}