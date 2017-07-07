package com.simplerssreader;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class RssFragment extends Fragment implements OnItemClickListener {
    public static final String LINK_RES = "LINK";
    private String link;
    private List<RssItem> rssItems;
    private ProgressBar progressBar;
    private ListView listView;
    private TextToSpeech tts;

    public static RssFragment getInstance(String link){
        RssFragment instance = new RssFragment();
        Bundle bundle = new Bundle();
        bundle.putString(LINK_RES,link);
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            link = getArguments().getString(LINK_RES);
            startService();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rss_fragment_layout, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        listView = (ListView) view.findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        return view;
    }

    private void startService() {
        Intent intent = new Intent(getActivity(), RssService.class);
        intent.putExtra(LINK_RES,link);
        getActivity().startService(intent);
    }

    /**
     * Once the {@link RssService} finishes its task, the result is sent to this BroadcastReceiver
     */
    private BroadcastReceiver resultReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            progressBar.setVisibility(View.GONE);
            rssItems = (List<RssItem>) intent.getSerializableExtra(RssService.ITEMS);
            RssItem itt;
            itt= rssItems.get(1);
            Toast.makeText(getActivity(), itt.getTitle(), Toast.LENGTH_LONG).show();
            if (rssItems != null) {
                RssAdapter adapter = new RssAdapter(getActivity(), rssItems);
                listView.setAdapter(adapter);
            } else {
                Toast.makeText(getActivity(), "An error occurred while downloading the rss feed.",
                        Toast.LENGTH_LONG).show();
            }
            createLanguageTTS();
        }
    };

    private void createLanguageTTS() {
        if (tts == null) {
            tts = new TextToSpeech(getContext(),new  TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status != TextToSpeech.ERROR) {
                        tts.setLanguage(Locale.CHINESE);
                        speakAllTitles();
                    }
                }
            });
        }
    }

    //@TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void speakAllTitles(){
        for (int i = 0; i < rssItems.size() ; i ++ )

               // tts.speak( rssItems.get(i).getTitle(), TextToSpeech.QUEUE_ADD, null , String.valueOf(i));
                tts.speak( rssItems.get(i).getTitle(), TextToSpeech.QUEUE_ADD, null);


    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        RssAdapter adapter = (RssAdapter) parent.getAdapter();
        RssItem item = (RssItem) adapter.getItem(position);
        Uri uri = Uri.parse(item.getLink());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(RssService.ACTION_RSS_PARSED);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(resultReceiver, intentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        if(tts != null){
            tts.shutdown();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(resultReceiver);
        if(tts != null){
            tts.shutdown();
        }
    }


}