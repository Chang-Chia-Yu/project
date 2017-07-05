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

import static com.simplerssreader.RssFragment.LINK_RES;



public class choose_google extends AppCompatActivity {
    private TextToSpeech textToSpeech;
    public static final String HOTNEWS_LINK = "https://news.google.com/news/rss/headlines?ned=tw&hl=zh-TW";
    public static final String SPORTS_LINK = "https://news.google.com/news/rss/headlines/section/topic/SPORTS.zh-TW_tw/%E9%AB%94%E8%82%B2?ned=tw&hl=zh-TW";
    public static final String ENTERTAINMENT_LINK = "https://news.google.com/news/rss/headlines/section/topic/ENTERTAINMENT.zh-TW_tw/%E5%A8%9B%E6%A8%82?ned=tw&hl=zh-TW";
    public static final String HEALTH_LINK = "https://news.google.com/news/rss/headlines/section/topic/HEALTH.zh-TW_tw/%E5%81%A5%E5%BA%B7?ned=tw&hl=zh-TW";
    public static final String FINANCE_LINK = "https://news.google.com/news/rss/headlines/section/topic/BUSINESS.zh-TW_tw/%E8%B2%A1%E7%B6%93?ned=tw&hl=zh-TW";
    public static final String WORLD_LINK = "https://news.google.com/news/rss/headlines/section/topic/WORLD.zh-TW_tw/%E5%9C%8B%E9%9A%9B?ned=tw&hl=zh-TW";
    static final int check = 111;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_udn);
        String t1="1.焦點新聞";
        String t2="2.體育新聞";
        String t3="3.娛樂新聞";
        String t4="4.健康新聞";
        String t5="5.財經新聞";
        String t6="6.國際新聞";
        TextView tv = (TextView)this.findViewById(R.id.text);
        tv.setText(t1);
        TextView tv2 = (TextView)this.findViewById(R.id.text2);
        tv2.setText(t2);
        TextView tv3 = (TextView)this.findViewById(R.id.text3);
        tv3.setText(t3);
        TextView tv4 = (TextView)this.findViewById(R.id.text4);
        tv4.setText(t4);
        TextView tv5 = (TextView)this.findViewById(R.id.text5);
        tv5.setText(t5);
        TextView tv6 = (TextView)this.findViewById(R.id.text6);
        tv6.setText(t6);

        textToSpeech = new TextToSpeech(choose_google.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    String ex="請輕觸螢幕說出想選擇報讀的新聞";
                    String t1="1.焦點新聞";
                    String t2="2.體育新聞";
                    String t3="3.娛樂新聞";
                    String t4="4.健康新聞";
                    String t5="5.財經新聞";
                    String t6="6.國際新聞";
                    textToSpeech.setLanguage(Locale.CHINESE);
                    textToSpeech.speak(ex, TextToSpeech.QUEUE_ADD, null);
                    textToSpeech.speak(t1, TextToSpeech.QUEUE_ADD, null);
                    textToSpeech.speak(t2, TextToSpeech.QUEUE_ADD, null);
                    textToSpeech.speak(t3, TextToSpeech.QUEUE_ADD, null);
                    textToSpeech.speak(t4, TextToSpeech.QUEUE_ADD, null);
                    textToSpeech.speak(t5, TextToSpeech.QUEUE_ADD, null);
                    textToSpeech.speak(t6, TextToSpeech.QUEUE_ADD, null);
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
            intent.setClass(choose_google.this,Main.class);

            switch (result) {
                case "焦點新聞":
                    intent.putExtra(LINK_RES, HOTNEWS_LINK);
                    break;
                case "體育新聞":
                    intent.putExtra(LINK_RES, SPORTS_LINK);
                    break;
                case "娛樂新聞":
                    intent.putExtra(LINK_RES, ENTERTAINMENT_LINK);
                    break;
                case "健康新聞":
                    intent.putExtra(LINK_RES, HEALTH_LINK);
                    break;
                case "財經新聞":
                    intent.putExtra(LINK_RES, FINANCE_LINK);
                    break;
                case "國際新聞":
                    intent.putExtra(LINK_RES, WORLD_LINK);
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