package com.loopie.uvtfitnesstracker;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.MissingResourceException;

public class SecondFragment extends Fragment {
    private static int FIRST_ELEMENT = 0;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        ArrayList<Exercise> peopleList = new ArrayList<>();
        try {
            JSONArray ja = new JSONArray(loadJSONFromAsset());
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jsonObject = ja.getJSONObject(i);
                try {
                    try {
                        //int id = getActivity().getResources().getIdentifier(jsonObject.getJSONArray("png").getString(0), "drawable", getActivity().getPackageName());
                        Exercise exercise = new Exercise(jsonObject.getString("title"), "file:///android_asset/" + jsonObject.getJSONArray("png").getString(0) + ".png");
                        peopleList.add(exercise);
                    } catch (MissingResourceException ex) {

                    }
                } catch (JSONException ex) {
                    Log.e("jsontest", "PNG NOT FOUND");
                }
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        ListView mListView = (ListView) getView().findViewById(R.id.listView);
        ExerciseListAdapter adapter = new ExerciseListAdapter(getActivity(), R.layout.adapter_view_layout, peopleList);
        mListView.setAdapter(adapter);
        super.onViewCreated(view, savedInstanceState);
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("exercises.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}