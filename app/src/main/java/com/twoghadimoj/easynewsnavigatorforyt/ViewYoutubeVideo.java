package com.twoghadimoj.easynewsnavigatorforyt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ProcessLifecycleOwner;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ViewYoutubeVideo extends Activity {
    YouTubePlayer mYouTubePlayer;
    YouTubePlayerView mYouTubePlayerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_youtube_video);

        String videoURL = getIntent().getStringExtra("videoURL");

        mYouTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player_view);

        ProcessLifecycleOwner.get().getLifecycle().addObserver(mYouTubePlayerView);

        mYouTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {

                mYouTubePlayer = youTubePlayer;
                openNewsChannel(videoURL);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mYouTubePlayerView.release();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        mYouTubePlayerView.release();
    }

    public void openNewsChannel(String URL) {
        new RequestYTResponse().execute(URL);
    }
    private class RequestYTResponse extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... GET_URL) {
            URL obj = null;
            String returnResponse = "";
            try {
                obj = new URL(GET_URL[0]);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");

                int responseCode = con.getResponseCode();
//            System.out.println("GET Response Code :: " + responseCode);
                //Read Response Data
                if (responseCode == HttpURLConnection.HTTP_OK) { // success
                    BufferedReader in = new BufferedReader(new InputStreamReader(
                            con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                        if (extractVideoIDFromResponse(inputLine) != null) {
                            break;
                        }
                    }
                    in.close();

                    returnResponse = response.toString();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return returnResponse;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //Do anything with response..
            String videoID = extractVideoIDFromResponse(result);
            if (videoID != null)
                mYouTubePlayer.loadVideo(extractVideoIDFromResponse(result), 0);
            else {
                Toast.makeText(ViewYoutubeVideo.this, "No Stream Available For This Channel At This Moment.\n Try Again After Some Time.", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
        private String extractVideoIDFromResponse(String response){
            //pattern to match
            String patternString = "(\\/watch\\?)v=([^\"]*)";
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(response);

            //find match in response
            if(matcher.find()) {
                String value = matcher.group(2);
//            System.out.println(value);
                return value;
            }
            return null;
        }
}