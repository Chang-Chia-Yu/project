package com.simplerssreader;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import static com.simplerssreader.RssFragment.LINK_RES;


public class choose_apple extends AppCompatActivity {
    private TextToSpeech textToSpeech;
    private TextToSpeech tts;
    public static final String HOTNEWS_LINK = "http://www.appledaily.com.tw/rss/newcreate/kind/rnews/type/hot";
    public static final String SPORTS_LINK = "http://www.appledaily.com.tw/rss/newcreate/kind/rnews/type/107";
    public static final String ENTERTAINMENT_LINK = "http://www.appledaily.com.tw/rss/newcreate/kind/rnews/type/106";
    public static final String LIFE_LINK = "http://www.appledaily.com.tw/rss/newcreate/kind/rnews/type/105";
    public static final String FINANCE_LINK = "http://www.appledaily.com.tw/rss/newcreate/kind/rnews/type/104";
    public static final String WORLD_LINK = "http://www.appledaily.com.tw/rss/newcreate/kind/rnews/type/103";
    static final int check = 111;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.Rss_choose_udn);
        String t1="1.焦點新聞";
        String t2="2.體育新聞";
        String t3="3.娛樂新聞";
        String t4="4.生活新聞";
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

        textToSpeech = new TextToSpeech(choose_apple.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    String ex="請在聽完選項後輕觸螢幕說出想選擇報讀的新聞";
                    String t1="1.焦點新聞";
                    String t2="2.體育新聞";
                    String t3="3.娛樂新聞";
                    String t4="4.生活新聞";
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
            Toast.makeText(this, "您的選擇:"+result, Toast.LENGTH_LONG).show();
            Intent intent = new Intent();
            intent.setClass(choose_apple.this,Rss_Main.class);

            switch (result) {
                case "焦點新聞":
                    tts = new TextToSpeech( choose_apple.this, new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int status) {
                            if (status != TextToSpeech.ERROR) {
                                tts.setLanguage(Locale.CHINESE);
                                tts.speak("焦點新聞", TextToSpeech.QUEUE_ADD, null);
                            }
                        }
                    });
                    intent.putExtra(LINK_RES, HOTNEWS_LINK);
                    break;
                case "體育新聞":
                    tts = new TextToSpeech( choose_apple.this, new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int status) {
                            if (status != TextToSpeech.ERROR) {
                                tts.setLanguage(Locale.CHINESE);
                                tts.speak("體育新聞", TextToSpeech.QUEUE_ADD, null);
                            }
                        }
                    });
                    intent.putExtra(LINK_RES, SPORTS_LINK);
                    break;
                case "娛樂新聞":
                    tts = new TextToSpeech( choose_apple.this, new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int status) {
                            if (status != TextToSpeech.ERROR) {
                                tts.setLanguage(Locale.CHINESE);
                                tts.speak("娛樂新聞", TextToSpeech.QUEUE_ADD, null);
                            }
                        }
                    });
                    intent.putExtra(LINK_RES, ENTERTAINMENT_LINK);
                    break;
                case "生活新聞":
                    tts = new TextToSpeech( choose_apple.this, new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int status) {
                            if (status != TextToSpeech.ERROR) {
                                tts.setLanguage(Locale.CHINESE);
                                tts.speak("生活新聞", TextToSpeech.QUEUE_ADD, null);
                            }
                        }
                    });
                    intent.putExtra(LINK_RES, LIFE_LINK);
                    break;
                case "財經新聞":
                    tts = new TextToSpeech( choose_apple.this, new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int status) {
                            if (status != TextToSpeech.ERROR) {
                                tts.setLanguage(Locale.CHINESE);
                                tts.speak("財經新聞", TextToSpeech.QUEUE_ADD, null);
                            }
                        }
                    });
                    intent.putExtra(LINK_RES, FINANCE_LINK);
                    break;
                case "國際新聞":
                    tts = new TextToSpeech( choose_apple.this, new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int status) {
                            if (status != TextToSpeech.ERROR) {
                                tts.setLanguage(Locale.CHINESE);
                                tts.speak("國際新聞", TextToSpeech.QUEUE_ADD, null);
                            }
                        }
                    });
                    intent.putExtra(LINK_RES, WORLD_LINK);
                    break;
                default:
                    tts = new TextToSpeech( choose_apple.this, new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int status) {
                            if (status != TextToSpeech.ERROR) {
                                tts.setLanguage(Locale.CHINESE);
                                tts.speak("無法辨識您的需求", TextToSpeech.QUEUE_ADD, null);
                            }
                        }
                    });
                    intent.setClass(choose_apple.this,choose_apple.class);
                    break;
            }
            startActivity(intent);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    // 利用 MotionEvent 處理觸控程序
    public boolean onTouchEvent(MotionEvent event) {
        // 判斷觸控動作
        switch( event.getAction() ) {
            case MotionEvent.ACTION_DOWN:  // 按下
                Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak up, Please!");
                startActivityForResult(i, check);
                break;
            case MotionEvent.ACTION_MOVE:  // 拖曳移動
                break;
            case MotionEvent.ACTION_UP:  // 放開
                break;
        }
        // TODO Auto-generated method stub
        return super.onTouchEvent(event);
    }

}
