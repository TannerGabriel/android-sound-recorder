package com.example.gabriel.soundrecorder

import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Toast
import com.example.gabriel.soundrecorder.R.id.*
import com.example.gabriel.soundrecorder.recorder.RecorderViewModel
import com.example.gabriel.soundrecorder.util.InjectorUtils
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private var viewModel: RecorderViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()

        fab_start_recording.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                val permissions = arrayOf(android.Manifest.permission.RECORD_AUDIO, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                ActivityCompat.requestPermissions(this, permissions,0)
            } else {
                startRecording()
            }
        }

        fab_stop_recording.setOnClickListener{
            stopRecording()
        }

        fab_pause_recording.setOnClickListener {
            pauseRecording()
        }

        fab_resume_recording.setOnClickListener {
            resumeRecording()
        }
    }

    private fun initUI() {
        //Get the viewmodel factory
        val factory = InjectorUtils.provideRecorderViewModelFactory()

        //Getting the viewmodel
        viewModel = ViewModelProviders.of(this, factory).get(RecorderViewModel::class.java)
    }


    @SuppressLint("RestrictedApi")
    private fun startRecording() {
        viewModel?.startRecording()
        fab_start_recording.visibility = View.INVISIBLE
        fab_stop_recording.visibility = View.VISIBLE
        fab_pause_recording.visibility = View.VISIBLE
        fab_recordings.visibility = View.INVISIBLE
        fab_resume_recording.visibility = View.INVISIBLE
    }

    @SuppressLint("RestrictedApi")
    private fun stopRecording(){
        viewModel?.stopRecording()
        fab_start_recording.visibility = View.VISIBLE
        fab_stop_recording.visibility = View.INVISIBLE
        fab_pause_recording.visibility = View.INVISIBLE
        fab_recordings.visibility = View.VISIBLE
        fab_resume_recording.visibility = View.INVISIBLE
    }

    @TargetApi(Build.VERSION_CODES.N)
    @SuppressLint("RestrictedApi")
    private fun pauseRecording(){
        viewModel?.pauseRecording()

        fab_start_recording.visibility = View.INVISIBLE
        fab_stop_recording.visibility = View.VISIBLE
        fab_pause_recording.visibility = View.INVISIBLE
        fab_recordings.visibility = View.INVISIBLE
        fab_resume_recording.visibility = View.VISIBLE
    }

    @TargetApi(Build.VERSION_CODES.N)
    @SuppressLint("RestrictedApi")
    private fun resumeRecording(){
        viewModel?.resumeRecording()

        fab_start_recording.visibility = View.INVISIBLE
        fab_stop_recording.visibility = View.VISIBLE
        fab_pause_recording.visibility = View.VISIBLE
        fab_recordings.visibility = View.INVISIBLE
        fab_resume_recording.visibility = View.INVISIBLE
    }

}
