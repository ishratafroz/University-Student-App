package com.example.myappforuniversity.ui.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myappforuniversity.R;

public class AboutFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View myView= inflater.inflate(R.layout.about_fragment, container, false);
        WebView myWebView=myView.findViewById(R.id.webview1);
        myWebView.loadUrl("https://www.kuet.ac.bd/index.php/welcome/about_kuet");

        WebSettings webSettings=myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);   // external app attack thke bachte
        myWebView.setWebViewClient(new WebViewClient()); // external browser e jate na jay
        return myView;
    }
}