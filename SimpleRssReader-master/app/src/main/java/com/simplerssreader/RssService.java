package com.simplerssreader;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.List;

import static com.simplerssreader.RssFragment.LINK_RES;

public class RssService extends IntentService {

    public static final String ITEMS = "items";
    public static final String ACTION_RSS_PARSED = "com.simplerssreader.ACTION_RSS_PARSED";

    public RssService() {
        super("RssService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(Constants.TAG, "Service started");
        String link = intent.getStringExtra(LINK_RES);
        Log.d(Constants.TAG, "Link : " + link);

        List<RssItem> rssItems = null;
        try {
            PcWorldRssParser parser = new PcWorldRssParser();
            rssItems = parser.parse(getInputStream(link));
        } catch (XmlPullParserException | IOException e) {
            Log.w(e.getMessage(), e);
        }

        // Send result
        Intent resultIntent = new Intent(ACTION_RSS_PARSED);
        resultIntent.putExtra(ITEMS, (Serializable) rssItems);
        LocalBroadcastManager.getInstance(this).sendBroadcast(resultIntent);
    }

    public InputStream getInputStream(String link) {
        try {
            URL url = new URL(link);
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            Log.w(Constants.TAG, "Exception while retrieving the input stream", e);
            return null;
        }
    }
}
