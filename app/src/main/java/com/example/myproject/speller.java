package com.example.myproject;

//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//
//import android.Manifest;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.os.Build;
//import android.os.Bundle;
//import android.speech.RecognitionListener;
//import android.speech.RecognizerIntent;
//import android.speech.SpeechRecognizer;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//import java.util.Locale;
//
//public class speller extends AppCompatActivity implements View.OnTouchListener  {
//
//    EditText editText;
//    ImageView imageView;
//
//    public static final Integer RecordAudioRequestCode = 1;
//
//    private SpeechRecognizer speechRecognizer;
//    AlertDialog.Builder alertSpeechDialog;
//    AlertDialog alertDialog;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_speller);
//
//
//        editText = findViewById(R.id.editText);
//        imageView = findViewById(R.id.imageView2);
//
//        if (ContextCompat.checkSelfPermission(speller.this, android.Manifest.permission.RECORD_AUDIO) !=
//                PackageManager.PERMISSION_GRANTED) {
//            checkPermission();
//        }
//        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
//
//        final Intent speechIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
//                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//
//        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
//
//        speechRecognizer.setRecognitionListener(new RecognitionListener() {
//            @Override
//            public void onReadyForSpeech(Bundle params) {
//
//            }
//
//            @Override
//            public void onBeginningOfSpeech() {
//
//                ViewGroup viewGroup = findViewById(android.R.id.content);
//                View dialogView = LayoutInflater.from(speller.this).inflate(R.layout.alertcustom,
//                        viewGroup, false);
//
//                alertSpeechDialog = new AlertDialog.Builder(speller.this);
//                alertSpeechDialog.setMessage("Listening");
//                alertSpeechDialog.setView(dialogView);
//                alertDialog = alertSpeechDialog.create();
//                alertDialog.show();
//
//
//            }
//
//            @Override
//            public void onRmsChanged(float v) {
//
//            }
//
//            @Override
//            public void onBufferReceived(byte[] buffer) {
//
//            }
//
//            @Override
//            public void onEndOfSpeech() {
//
//            }
//
//            @Override
//            public void onError(int error) {
//
//            }
//
//            @Override
//            public void onResults(Bundle bundle) {
//
//                imageView.setImageResource(R.drawable.baseline_mic_24);
//                ArrayList<String> arrayList = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
//                editText.setText(arrayList.get(0));
//                alertDialog.dismiss();
//
//            }
//
//            @Override
//            public void onPartialResults(Bundle partialResults) {
//
//            }
//
//            @Override
//            public void onEvent(int eventType, Bundle params) {
//
//            }
//        });
//
//        imageView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
//                    speechRecognizer.stopListening();
//                }
//                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//                    imageView.setImageResource(R.drawable.baseline_mic_24);
//                    speechRecognizer.startListening(speechIntent);
//
//                }
//                return false;
//            }
//        });
//    }
//
//    private void checkPermission() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            ActivityCompat.requestPermissions(speller.this, new String[]{
//                    Manifest.permission.RECORD_AUDIO}, RecordAudioRequestCode);
//        }
//
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        speechRecognizer.destroy();
//
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        if (requestCode == RecordAudioRequestCode && grantResults.length > 0) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(this, "Permission Granted", Toast.LENGTH_LONG).show();
//            }
//        }
//
//    }
//
//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        return false;
//    }
//}
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Locale;

public class speller extends AppCompatActivity {
    EditText editText;
    ImageView imageView;
    SpeechRecognizer speechRecognizer;
    AlertDialog alertDialog;
    TextToSpeech textToSpeech;

    public static final int RecordAudioRequestCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speller);

        editText = findViewById(R.id.editText);
        imageView = findViewById(R.id.imageView2);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            checkPermission();
        }

        // Initialize the alertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Listening...");
        alertDialog = builder.create();

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        final Intent speechIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {}

            @Override
            public void onBeginningOfSpeech() {
                // Show the alertDialog when speech recognition starts
                alertDialog.show();
            }

            @Override
            public void onRmsChanged(float rmsdB) {}

            @Override
            public void onBufferReceived(byte[] buffer) {}

            @Override
            public void onEndOfSpeech() {}

            @Override
            public void onError(int error) {}

            @Override
            public void onResults(Bundle bundle) {
                imageView.setImageResource(R.drawable.baseline_mic_24);
                ArrayList<String> arrayList = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (arrayList != null && !arrayList.isEmpty()) {
                    String spelledWord = arrayList.get(0);
                    for (int i = 0; i < spelledWord.length(); i++) {
                        String letter = String.valueOf(spelledWord.charAt(i));
                        textToSpeech.speak(letter, TextToSpeech.QUEUE_ADD, null, null);
                        try {
                            Thread.sleep(1000); // Adjust delay as needed
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                // Dismiss the alertDialog after spelling the word
                alertDialog.dismiss();
            }

            @Override
            public void onPartialResults(Bundle partialResults) {}

            @Override
            public void onEvent(int eventType, Bundle params) {}
        });

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    speechRecognizer.stopListening();
                }
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    speechRecognizer.startListening(speechIntent);
                }
                return false;
            }
        });

        // Initialize TextToSpeech
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    // Text-to-speech initialized successfully
                } else {
                    // Text-to-speech initialization failed
                    Toast.makeText(speller.this, "Text-to-speech initialization failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO}, RecordAudioRequestCode);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        speechRecognizer.destroy();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RecordAudioRequestCode && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

