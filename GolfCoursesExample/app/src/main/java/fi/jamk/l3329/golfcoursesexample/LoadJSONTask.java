package fi.jamk.l3329.golfcoursesexample;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by peter on 16.10.2017.
 */
//class will take an URL as a string parameter and it will return loaded JSON object back to caller
public class LoadJSONTask extends AsyncTask<String, Void, JSONObject> {

    private LoadJSONTaskListener mListener;

    public interface LoadJSONTaskListener {
        public void onPostExecuteConcluded(JSONObject result);
    }

    final public void setListener(LoadJSONTaskListener listener){
        mListener = listener;
    }

    @Override
    protected JSONObject doInBackground(String... urls) {
        HttpURLConnection urlConnection = null;
        JSONObject json = null;

        try {
            URL url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            urlConnection.getInputStream()));

            StringBuilder stringBuilder = new StringBuilder();

            String line;

            while ((line = bufferedReader.readLine())!= null){
                stringBuilder.append(line).append("\n");
            }

            bufferedReader.close();
            json = new JSONObject(stringBuilder.toString());

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            if(urlConnection != null) urlConnection.disconnect();
        }
        return json;
    }

    //data is loaded, send it back to caller
    final protected void onPostExecute(JSONObject json){
        if (mListener != null) mListener.onPostExecuteConcluded(json);
    }
}
