package com.tequip.superchef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

public class privacy_policy extends AppCompatActivity {
    ImageView back_btn;
    private String webUrl = "https://sites.google.com/view/superchef-privacy-policy/home";
    WebView privacy_policy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        back_btn = findViewById(R.id.privacy_policy_back_id);
        privacy_policy = findViewById(R.id.privacy_policy_web_view_id);
        privacy_policy.getSettings().setJavaScriptEnabled(true);
        privacy_policy.loadUrl(webUrl);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (privacy_policy.canGoBack()) {
            privacy_policy.goBack();
        } else {
            super.onBackPressed();
        }
    }
}