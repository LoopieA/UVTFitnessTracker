package com.loopie.uvtfitnesstracker.repo;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.loopie.uvtfitnesstracker.converters.Converters;
import com.loopie.uvtfitnesstracker.dao.ExerciseDao;
import com.loopie.uvtfitnesstracker.dao.ExerciseProgramsDao;
import com.loopie.uvtfitnesstracker.dao.ExerciseRepsDao;
import com.loopie.uvtfitnesstracker.dao.ProgramsDao;
import com.loopie.uvtfitnesstracker.dao.SubProgramsDao;
import com.loopie.uvtfitnesstracker.models.Exercise;
import com.loopie.uvtfitnesstracker.models.ExerciseProgramsMany;
import com.loopie.uvtfitnesstracker.models.ExerciseReps;
import com.loopie.uvtfitnesstracker.models.Programs;
import com.loopie.uvtfitnesstracker.models.SubPrograms;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.MissingResourceException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Exercise.class, ExerciseProgramsMany.class, Programs.class, SubPrograms.class, ExerciseReps.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class RoomMyDatabase extends RoomDatabase {
    public abstract ExerciseDao exerciseDao();
    public abstract ProgramsDao programsDao();
    public abstract ExerciseProgramsDao exerciseProgramsDao();
    public abstract SubProgramsDao subProgramsDao();
    public abstract ExerciseRepsDao exerciseRepsDao();
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
                                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                                    boolean firstStart = preferences.getBoolean("firstStart", true);
                                    databaseWriteExecutor.execute(() -> {
                                        if (firstStart) {
                                            populateDB(context);
                                            SharedPreferences.Editor editor = preferences.edit();
                                            editor.putBoolean("firstStart", false);
                                            editor.apply();
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

    public static void populateDB(Context context) {
        ExerciseDao dao = INSTANCE.exerciseDao();
        ExerciseProgramsDao exprogDao = INSTANCE.exerciseProgramsDao();
        ProgramsDao progDao = INSTANCE.programsDao();
        SubProgramsDao subProgDao = INSTANCE.subProgramsDao();
        //dao.deleteAll();
        Programs programtest = new Programs("Upper/Lower Program");
        SubPrograms subprogram1 = new SubPrograms("Monday", 1);
        SubPrograms subprogram2 = new SubPrograms("Tuesday", 1);
        programtest.setProgramsid(1);
        progDao.insert(programtest);
        subprogram1.setsubprogramsid(1);
        subprogram2.setsubprogramsid(2);
        subProgDao.insert(subprogram1);
        subProgDao.insert(subprogram2);
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
    }

    public static String loadJSONFromAsset(Context context) {
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


