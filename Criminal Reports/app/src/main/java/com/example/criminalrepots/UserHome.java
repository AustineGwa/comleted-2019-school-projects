package com.example.criminalrepots;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.criminalrepots.dialogs.ComplaintDialog;
import com.google.firebase.auth.FirebaseAuth;

public class UserHome extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        webView = findViewById(R.id.law_webview);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getApplicationContext(), description, Toast.LENGTH_SHORT).show();
            }
            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }
        });
        webView.loadUrl("http://www.kenyalaw.org:8181/exist/kenyalex/actview.xql?actid=Const2010");

//        recyclerView = findViewById(R.id.criminal_law_list);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setHasFixedSize(true);
//        lawList = new ArrayList<>();
//
//        Law law = new Law("Rape", "10 years imprisonment");
//        Law law1 = new Law ("Murder","life sentence");
//        Law law2 = new Law("Theft","fine 50,000 and 2 years in prison");
//
//        lawList.addAll(Arrays.asList(law,law1,law2));
//        adapter = new ReportsAdapter(lawList);
//        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.lawyers_home){
            startActivity(new Intent(getApplicationContext(), LawyersActivity.class));
        }else if(id == R.id.users_complaints){
            openDialog(new ComplaintDialog());

        }else if(id == R.id.view_logout){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(),MainActivity.class ));
            finish();
        }
        return super.onOptionsItemSelected(item);

    }

    private void openDialog(DialogFragment dialog) {

        dialog.show(getSupportFragmentManager(),"Dialog");
    }
}
