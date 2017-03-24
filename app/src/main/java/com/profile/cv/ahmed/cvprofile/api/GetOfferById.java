package com.profile.cv.ahmed.cvprofile.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.profile.cv.ahmed.cvprofile.R;
import com.profile.cv.ahmed.cvprofile.interfaces.OnLoadingComplete;
import com.profile.cv.ahmed.cvprofile.model.OfferModelMain;
import com.profile.cv.ahmed.cvprofile.model.OfferSingleMain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class GetOfferById extends AsyncTask<String, Void, OfferSingleMain> {

	private String URL =  "http://cv-campany.com/API/ar/offer/i/";
	private ProgressDialog dialog;
	private OnLoadingComplete callback;
	private Context context;

	public GetOfferById(Context context, OnLoadingComplete cb, int id) {
		dialog = new ProgressDialog(context);
		callback = cb;
		this.context = context;
		URL = URL+id;
		Log.d("mmm",URL);
  	}

	@Override
	protected void onPreExecute() {
		this.dialog.setMessage(context.getResources().getString(
				R.string.loading));
		this.dialog.setCancelable(false);
		this.dialog.show();
	}

	@Override
	protected OfferSingleMain doInBackground(String... params) {

		String responseJSON = null;
		try {
			responseJSON = invokeJSONWS();
			Log.d("ttt55",responseJSON);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Gson gson = new Gson();

		OfferSingleMain obj = null;
		if (responseJSON != null && responseJSON.length() > 1) {

			GsonBuilder gb = new GsonBuilder();
			gb.serializeNulls();
			gson = gb.create();
			try {
				obj = gson.fromJson(responseJSON, OfferSingleMain.class);
			} catch (com.google.gson.JsonSyntaxException ex) {
				ex.printStackTrace();
				Log.d("ttt66",ex.toString());

			}
		}
		return obj;

	}

	@Override
	protected void onPostExecute(OfferSingleMain result) {
		if (dialog.isShowing()) {
			dialog.dismiss();
		}

		if (result != null) {
			callback.onSuccess(result);
		} else {
			callback.onFailure();
		}
	}

	private String invokeJSONWS() throws IOException {
		HttpURLConnection httpConn = null;

		InputStream in = null;
		int response = -1;
		String responseJSON;
		java.net.URL url = new URL(URL);
		URLConnection conn = url.openConnection();
		if (!(conn instanceof HttpURLConnection))
			throw new IOException("Not an HTTP connection");

		try {
			  httpConn = (HttpURLConnection) conn;
			httpConn.setRequestMethod("GET");
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);

			httpConn.connect();

			response = httpConn.getResponseCode();

			if (response == HttpURLConnection.HTTP_OK) {
				in = conn.getInputStream();
			}

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));
			StringBuilder out = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				out.append(line);
			}

			responseJSON = out.toString();

		} catch (Exception e) {
			throw new IOException("Error connecting");
		}finally {
			httpConn.disconnect();
		}
		return responseJSON;
	}
}
