package hostmanager.com.br.hostmanager.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpHeaders;
import hostmanager.com.br.hostmanager.R;
import hostmanager.com.br.hostmanager.constants.Constants;

import static java.nio.charset.StandardCharsets.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        atualizarHost("192.168.1.4");
    }

    private void atualizarHost(String novoHost) {


//        String urlCall = "http://hernand.azevedo@gmail.com:69523198@dynupdate.no-ip.com/nic/update?hostname=dominiumts2.servegame.com&myip="+novoHost;
        String urlCall = "http://dynupdate.no-ip.com/nic/update?hostname=dominiumts2.servegame.com&myip="+novoHost;
        final String user = "hernand.azevedo@gmail.com";
        final String password = "69523198";
        final String encoded = Base64.encodeToString((user + ':' + password).getBytes(Charset.forName("UTF-8")),Base64.DEFAULT);
        try {
            AsyncHttpClient client = new AsyncHttpClient();
            client.addHeader(HttpHeaders.AUTHORIZATION,"Basic " + encoded);
            client.get(urlCall, new AsyncHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    if(responseBody != null) {
                        Log.i(Constants.LOG_TAG, new String(responseBody));
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    if(responseBody != null) {
                        Log.i(Constants.LOG_TAG, new String(responseBody));
                    }
                }


            });

//			String response = SyncService.sendGet(urlCall);
//			return onSuccess(response);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
//		return null;
    }

}
