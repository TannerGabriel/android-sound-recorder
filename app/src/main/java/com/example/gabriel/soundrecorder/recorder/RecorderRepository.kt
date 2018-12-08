package com.example.gabriel.soundrecorder.recorder

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.media.MediaRecorder
import android.opengl.Visibility
import android.os.Build
import android.os.Environment
import android.os.Handler
import android.support.design.widget.FloatingActionButton
import android.view.View
import android.widget.Toast
import com.example.gabriel.soundrecorder.R.id.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import android.widget.TextView
import com.example.gabriel.soundrecorder.MainActivity
import com.example.gabriel.soundrecorder.R
import com.example.gabriel.soundrecorder.util.VisibilityConst
import java.io.File


class RecorderRepository(){


    companion object {
        @Volatile
        private var instance: RecorderRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: RecorderRepository().also { instance = it }
            }
    }



    private var output: String? = null
    private var mediaRecorder: MediaRecorder? = null

    init {
        try{
            // create a File object for the parent directory
            val recorderDirectory = File(Environment.getExternalStorageDirectory().absolutePath+"/soundrecorder/")
            // have the object build the directory structure, if needed.
            recorderDirectory.mkdirs()
        }catch (e: IOException){
            e.printStackTrace()
        }

        mediaRecorder = MediaRecorder()
        output = Environment.getExternalStorageDirectory().absolutePath + "/soundrecorder/recording.mp3"

        mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mediaRecorder?.setOutputFile(output)
    }

    @SuppressLint("RestrictedApi")
    fun startRecording() {
        try {
            println("Starting recording!")
            mediaRecorder?.prepare()
            mediaRecorder?.start()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    @SuppressLint("RestrictedApi")
    fun stopRecording(){
        mediaRecorder?.stop()
        mediaRecorder?.release()

        initRecorder()
    }

    @TargetApi(Build.VERSION_CODES.N)
    @SuppressLint("RestrictedApi")
    fun pauseRecording(){
        mediaRecorder?.pause()
    }

    @TargetApi(Build.VERSION_CODES.N)
    @SuppressLint("RestrictedApi")
    fun resumeRecording(){
        mediaRecorder?.resume()
    }

    private fun initRecorder() {
        mediaRecorder = MediaRecorder()

        mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mediaRecorder?.setOutputFile(output)
    }

}