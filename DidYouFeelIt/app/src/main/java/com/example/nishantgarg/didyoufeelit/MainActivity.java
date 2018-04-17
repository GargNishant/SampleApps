package com.example.nishantgarg.didyoufeelit;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class MainActivity extends Activity {
    /*The URL from which the data will be fetched*/
    private static final String  USGS_URL=
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-05-02&minfelt=50&minmagnitude=5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create a new Thread
        DidYouFeelItAsyncTask event=new DidYouFeelItAsyncTask();
        //Execute the thread
        event.execute();
    }

    //The updateUI method is used for updating the main_activity.xml's View to show the contents we extracted from the JSON from online
    private void updateUI(Earthquake earthquake) {
        TextView titleTextView = (TextView) findViewById(R.id.title);
        titleTextView.setText(earthquake.etitle);

        TextView tsunamiTextView = (TextView) findViewById(R.id.number_of_people);
        // Now why do we used R.String.num_people_felt_it is unknown to me right now, please check if possible
        tsunamiTextView.setText(getString(R.string.num_people_felt_it, earthquake.numberOfPeople));

        TextView magnitudeTextView = (TextView) findViewById(R.id.perceived_magnitude);
        magnitudeTextView.setText(earthquake.strength);
    }

    // The inner class which extends and overrides th AsyncTask interface.
    private class DidYouFeelItAsyncTask extends AsyncTask<URL, Void,Earthquake>{

        //This is the method where the fetching of data from the server takes place
        @Override
        protected Earthquake doInBackground(URL... urls) {
            URL USGS_Url=createURL(USGS_URL);
            String jsonResponse= null;
            try {
                jsonResponse = makeHTTPConnection(USGS_Url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Earthquake event=extractDataFromJson(jsonResponse);
            return event;
        }

        //After fetching and converting it to proper event object, this method is used.
        @Override
        protected void onPostExecute(Earthquake earthquake) {
            updateUI(earthquake);
        }
    }

    //Creates URL object from the USGS_URL String.
    private static URL createURL(String usgsUrl) {
        URL USGSUrl=null;

        //Try-Catch block is used to handle if valid URL cannot be formed due to wrong String
        try{
            USGSUrl=new URL(usgsUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return USGSUrl;
    }
    /**
     *This method creates a connection to the server from the URL object created in above method and checks the response of the server.
     * If the server response is not as expected then it closes the connection and return null jsonResponse.
     */
    private static String makeHTTPConnection(URL usgs_url) throws IOException {
        String jsonResponse=null;
        if (usgs_url==null){
            return jsonResponse;
        }

        //The urlConnection is the object which will interact with the server
        HttpURLConnection urlConnection=null;

        //InputStream is used to take the response from the server. As the response is over time and maybe very large InputStream is used
        InputStream inputStream=null;
        try{
            //urlConnection opens the connection
            urlConnection=(HttpURLConnection)usgs_url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);

            /*This line is important because we should set the type of Request we want to make to the server. The USGS Server takes "GET" as the command for
            our purpose*/
            urlConnection.setRequestMethod("GET");
            /*Now urlConnection will connect to the server and we can request the data from it. But the request should fall under the above mentioned
            * setRequestMethod*/
            urlConnection.connect();
            //
            if(urlConnection.getResponseCode()==200){
                inputStream=urlConnection.getInputStream();
                jsonResponse=readFromInputStream(inputStream);
            }
            else{Log.i("Error", urlConnection.getResponseMessage()+urlConnection.getResponseCode());}
        }
        catch(IOException e){
            e.printStackTrace();
        }

        /*The code in the finally block is used to close the connection and free up the input Stream. This is a must have block of code as it free up
        * resources regardless of whether jsonResponse was successful or not*/
        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    //This block reads the InputStream from the server and converts the complete response into human Readable characters
    private static String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    //This method extracts the data from the jsonResponse that we got from the above method
    private Earthquake extractDataFromJson(String jsonResponse) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(jsonResponse)) {
            return null;
        }
        try {
            JSONObject baseJsonResponse = new JSONObject(jsonResponse);
            JSONArray featureArray = baseJsonResponse.getJSONArray("features");
            // If there are results in the features array
            if (featureArray.length() > 0) {
            // Extract out the first feature (which is an earthquake)
            JSONObject firstFeature = featureArray.getJSONObject(0);
            JSONObject properties = firstFeature.getJSONObject("properties");
            // Extract out the title, number of people, and perceived strength values
            String title = properties.getString("title");
            String numberOfPeople = properties.getString("felt");
            String perceivedStrength = properties.getString("cdi");
            // Create a new {@link Event} object
            return new Earthquake(title, numberOfPeople, perceivedStrength);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
