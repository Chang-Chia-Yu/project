package com.simplerssreader;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextToSpeech textToSpeech;
    static final int check = 111;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);
        String t1="1.蘋果日報";
        String t2="2.聯合日報";
        String t3="3.Google新聞";
        TextView tv = (TextView)this.findViewById(R.id.text);
        tv.setText(t1);
        TextView tv2 = (TextView)this.findViewById(R.id.text2);
        tv2.setText(t2);
        TextView tv3 = (TextView)this.findViewById(R.id.text3);
        tv3.setText(t3);

        textToSpeech = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    String ex="請輕觸螢幕說出想選擇報讀的新聞";
                    String t1="1.蘋果日報";
                    String t2="2.聯合日報";
                    String t3="3.Google新聞";
                    textToSpeech.setLanguage(Locale.CHINESE);
                    textToSpeech.speak(ex, TextToSpeech.QUEUE_ADD, null);
                    textToSpeech.speak(t1, TextToSpeech.QUEUE_ADD, null);
                    textToSpeech.speak(t2, TextToSpeech.QUEUE_ADD, null);
                    textToSpeech.speak(t3, TextToSpeech.QUEUE_ADD, null);
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == check && resultCode == RESULT_OK) {
            ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String result = results.get(0);
            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
            Intent intent = new Intent();


            switch (result) {
                case "聯合日報":
                    intent.setClass(MainActivity.this,choose_udn.class);
                    break;
                case "蘋果日報":
                    intent.setClass(MainActivity.this,choose_apple.class);
                    break;
                case "Google新聞":
                    intent.setClass(MainActivity.this,choose_google.class);
                    break;
            }
            startActivity(intent);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onClickVoice(View view) {
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak up, Please!");
        startActivityForResult(i, check);
    }
    






}