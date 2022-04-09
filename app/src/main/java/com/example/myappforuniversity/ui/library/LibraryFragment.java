package com.example.myappforuniversity.ui.library;

import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myappforuniversity.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class LibraryFragment extends Fragment {
 RelativeLayout relativeLayout; Button b1;
    WebView webView; ProgressBar progressBarWeb; ProgressDialog progressDialog;
    private String webUrl = "https://library.kuet.ac.bd/";
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.library_fragment, container, false);
          webView = view.findViewById(R.id.webview2); webView.loadUrl(webUrl);
         b1=view.findViewById(R.id.butoninternet);
        relativeLayout=view.findViewById(R.id.relativelayout);
        checkConnection();
        progressBarWeb=view.findViewById(R.id.libp1);
        Window window= getActivity().getWindow();
        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setTitle("Loading please wait");
        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Dexter.withContext(getActivity()).withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE).withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        DownloadManager.Request request=new DownloadManager.Request(Uri.parse(url));
                        request.setMimeType(contentDisposition);
                        String cookies= CookieManager.getInstance().getCookie(url);
                        request.addRequestHeader("cookie",cookies); request.addRequestHeader("user_Agent",userAgent);
                        request.setDescription("Downloading File......."); request.setTitle(URLUtil.guessFileName(url,contentDisposition,mimetype));
                        request.allowScanningByMediaScanner(); request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,URLUtil.guessFileName(url,contentDisposition,mimetype));
                    DownloadManager downloadManager=(DownloadManager)getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
                   downloadManager.enqueue(request);
                        Toast.makeText(getActivity(),"Downloading File",Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) { }
                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest(); }
                }).check();
            }
        });
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkConnection();
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
    progressBarWeb.setVisibility(View.VISIBLE);
    progressBarWeb.setProgress(newProgress);progressDialog.show();
                //setTitle("Loading");
                if(newProgress==100){
                    progressBarWeb.setVisibility(View.GONE); //setTitle(view.getTitle());
              progressDialog.dismiss();
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
                //return super.shouldOverrideUrlLoading(view, url);
            }
        });
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(false);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.canGoBack();
        webView.setOnKeyListener(new View.OnKeyListener(){
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_BACK) && event.getAction() == MotionEvent.ACTION_UP && webView.canGoBack()) {
                    webView.goBack();return true; }
                return false; }
            });
        return view; }
    public void checkConnection()
    {
        ConnectivityManager connectivityManager= (ConnectivityManager)
            getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileNetwork=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if(wifi.isConnected()) { webView.loadUrl(webUrl);
       webView.setVisibility(View.VISIBLE); relativeLayout.setVisibility(View.GONE);
        } else if(mobileNetwork.isConnected()){ webView.loadUrl(webUrl);
webView.setVisibility(View.VISIBLE); relativeLayout.setVisibility(View.GONE);
        } else {
            webView.setVisibility(View.GONE); relativeLayout.setVisibility(View.VISIBLE); } }}