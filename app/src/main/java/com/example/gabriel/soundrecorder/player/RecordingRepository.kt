package com.example.gabriel.soundrecorder.player

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Environment
import java.io.File
import java.nio.file.Files

class RecordingRepository{
    companion object {
        @Volatile
        private var instance: RecordingRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: RecordingRepository().also { instance = it }
            }


        fun playRecording(context: Context, title: String){
            val path = Uri.parse(Environment.getExternalStorageDirectory().absolutePath+"/soundrecorder/$title")
            val mediaPlayer: MediaPlayer? = MediaPlayer().apply {
                setAudioStreamType(AudioManager.STREAM_MUSIC)
                setDataSource(context, path)
                prepare()
                start()
            }
        }
    }

    private val recorderDirectory = File(Environment.getExternalStorageDirectory().absolutePath+"/soundrecorder/")
    private var file : ArrayList<String>? = null

    init {
        file = ArrayList<String>()
        getRecording()
    }

    private fun getRecording(){
        val files: Array<out File>? = recorderDirectory.listFiles()
        for(i in files!!){
            println(i.name)
            file?.add(i.name)
        }
    }

    fun getRecordings() = file

}