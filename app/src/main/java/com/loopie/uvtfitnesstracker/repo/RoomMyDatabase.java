package com.loopie.uvtfitnesstracker.repo;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.loopie.uvtfitnesstracker.dao.ExerciseDao;
import com.loopie.uvtfitnesstracker.dao.ExerciseProgramsDao;
import com.loopie.uvtfitnesstracker.dao.ProgramsDao;
import com.loopie.uvtfitnesstracker.models.Exercise;
import com.loopie.uvtfitnesstracker.models.ExerciseProgramsMany;
import com.loopie.uvtfitnesstracker.models.Programs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.MissingResourceException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Exercise.class, ExerciseProgramsMany.class, Programs.class}, version = 1, exportSchema = false)
public abstract class RoomMyDatabase extends RoomDatabase {
    public abstract ExerciseDao exerciseDao();
    public abstract ProgramsDao programsDao();
    public abstract ExerciseProgramsDao exerciseProgramsDao();
    private static volatile RoomMyDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static RoomMyDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RoomMyDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RoomMyDatabase.class, "room_database")
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                                    super.onOpen(db);
                                    // If you want to keep data through app restarts,
                                    // comment out the following block
                                    databaseWriteExecutor.execute(() -> {
                                        // Populate the database in the background.
                                        // If you want to start with more words, just add them.
                                        ExerciseDao dao = INSTANCE.exerciseDao();
                                        ExerciseProgramsDao exprogDao = INSTANCE.exerciseProgramsDao();
                                        ProgramsDao progDao = INSTANCE.programsDao();
                                        //dao.deleteAll();
                                        ExerciseProgramsMany ex1 = new ExerciseProgramsMany(1,1);
                                        ExerciseProgramsMany ex2 = new ExerciseProgramsMany(2,2);
                                        Programs programtest = new Programs(1, "Upper/Lower Program");
                                        progDao.insert(programtest);
                                        exprogDao.insert(ex1);
                                        exprogDao.insert(ex2);

                                        try {
                                            JSONArray ja = new JSONArray(loadJSONFromAsset(context));
                                            for (int i = 0; i < ja.length(); i++) {
                                                JSONObject jsonObject = ja.getJSONObject(i);
                                                try {
                                                    try {
                                                        Exercise exercise = new Exercise(jsonObject.getString("title"), "file:///android_asset/" + jsonObject.getJSONArray("png").getString(0) + ".png");
                                                        dao.insert(exercise);
                                                    } catch (MissingResourceException ex) {

                                                    }
                                                } catch (JSONException ex) {
                                                    Log.e("jsontest", "PNG NOT FOUND");
                                                }
                                            }
                                        } catch (JSONException ex) {
                                            ex.printStackTrace();
                                        }
                                    });
                                }
                            })
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    static public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("exercises.json");
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


