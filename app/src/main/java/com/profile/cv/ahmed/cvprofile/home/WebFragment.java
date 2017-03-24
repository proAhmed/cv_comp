package com.profile.cv.ahmed.cvprofile.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.profile.cv.ahmed.cvprofile.R;
import com.profile.cv.ahmed.cvprofile.main.MainActivity;


/**
 * Created by ahmed on 3/1/2016.
 */
public class WebFragment extends Fragment {
    private WebView webView;
    String link;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web,container,false);
        Bundle bundle = getArguments();
        link = bundle.getString("link");
        webView = (WebView) view.findViewById(R.id.webView);

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(link);
        webView.getSettings().setJavaScriptEnabled(true);

        return view;

    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        getActivity().findViewById(R.id.imageToggle).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    MainActivity mainActivity = (MainActivity) getActivity();
//                    mainActivity.showSecondaryMenu();
//                } catch (Exception e) {
//
//                }
//            }
//        });
//        TextView tv = (TextView) getActivity().findViewById(R.id.textTitle);
//        tv.setVisibility(View.GONE);
//        ImageView img = (ImageView) getActivity().findViewById(R.id.logo);
//        img.setVisibility(View.VISIBLE);
//        getActivity().findViewById(R.id.imageToggleCategory).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                MainActivity mainActivity = (MainActivity) getActivity();
//                if (mainActivity != null)
//                    mainActivity.toggle();
//            }
//        });
//    }
}
