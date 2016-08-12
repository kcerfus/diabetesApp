package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kcerfus on 8/10/2016.
 */
public final class FeedReaderContract {
    public FeedReaderContract() {}

    // need these attributes for our columns values, they are all supported by SQLite
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String AUTOINCREMENT = "AUTOINCREMENT";
    private static final String NOT_NULL = "NOT NULL";
    private static final String REAL = "REAL";
    private static final String UNIQUE = "UNIQUE";

    // Here we need a class for each table(5 tables total)
    public static abstract class BloodGlucoseMeasurement implements BaseColumns {
        // Define title and column names
        public static final String TABLE_NAME = "BloodGlucoseMeasurement";
        public static final String ID = "ID";
        public static final String BGL = "BGL";
        public static final String TIME = "Time";

        //Create columns modeled after Assignment 2
        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + BloodGlucoseMeasurement.TABLE_NAME + " (" +
                        BloodGlucoseMeasurement.ID + " INTEGER PRIMARY KEY" + AUTOINCREMENT + NOT_NULL + UNIQUE + COMMA_SEP +
                        BloodGlucoseMeasurement.BGL + REAL + NOT_NULL + COMMA_SEP +
                        BloodGlucoseMeasurement.TIME + TEXT_TYPE + NOT_NULL + UNIQUE + " )";

        // Probably change later to delete certain entries if desired?
        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + BloodGlucoseMeasurement.TABLE_NAME;

        // Have an inner class extending SQLiteOpenHelper to help with long running operations
        private class FeedReaderDbHelper extends SQLiteOpenHelper {

            public FeedReaderDbHelper(Context context){
                super(context, "database.db", null, 1);
            }
            // If you change the database schema, you must increment the database version.
            public static final int DATABASE_VERSION = 1;
            public static final String DATABASE_NAME = "FeedReader.db";

            // Will populate our database with columns
            @Override
            public void onCreate(SQLiteDatabase db) {db.execSQL(SQL_CREATE_ENTRIES);}

            // Need to implement it, but we wont be changing database so its unnecessary
            @Override
            public void onUpgrade(SQLiteDatabase db, int a, int b) {}
        }
    }

    public static abstract class Diet implements BaseColumns {
        // Define title and column names
        public static final String TABLE_NAME = "DIET";
        public static final String ID = "ID";
        public static final String NAME = "Name";
        public static final String TIME = "Time";
        public static final String CALORIES = "Calories";

        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + Diet.TABLE_NAME + " (" +
                        Diet.ID + " INTEGER PRIMARY KEY" + AUTOINCREMENT + NOT_NULL + UNIQUE + COMMA_SEP +
                        Diet.NAME + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                        Diet.TIME + TEXT_TYPE + NOT_NULL + UNIQUE + COMMA_SEP +
                        Diet.CALORIES + REAL + NOT_NULL + ")";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + Diet.TABLE_NAME;

        // Have an inner class extending SQLiteOpenHelper to help with long running operations
        private class FeedReaderDbHelper extends SQLiteOpenHelper {

            public FeedReaderDbHelper(Context context){
                super(context, "database.db", null, 1);
            }
                // If you change the database schema, you must increment the database version.
                public static final int DATABASE_VERSION = 1;
                public static final String DATABASE_NAME = "FeedReader.db";

                @Override
                public void onCreate(SQLiteDatabase db) {db.execSQL(SQL_CREATE_ENTRIES);}

            // Need to implement it, but we wont be changing database so its unnecessary
                @Override
                public void onUpgrade(SQLiteDatabase db, int a, int b) {}
        }
    }

    public static abstract class Exercise implements BaseColumns {
        public static final String TABLE_NAME = "EXERCISE";
        public static final String ID = "ID";
        public static final String NAME = "Name";
        public static final String TIME = "Time";
        public static final String CALORIES = "Calories";

        private static String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + Exercise.TABLE_NAME + " (" +
                        Exercise.ID + " INTEGER PRIMARY KEY" + AUTOINCREMENT + NOT_NULL + UNIQUE + COMMA_SEP +
                        Exercise.NAME + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                        Exercise.TIME + TEXT_TYPE + NOT_NULL + UNIQUE + COMMA_SEP +
                        Exercise.CALORIES + REAL + NOT_NULL + ")";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + Exercise.TABLE_NAME;

        // Have an inner class extending SQLiteOpenHelper to help with long running operations
        private class FeedReaderDbHelper extends SQLiteOpenHelper {

            public FeedReaderDbHelper(Context context){
                super(context, "database.db", null, 1);
            }
            // If you change the database schema, you must increment the database version.
            public static final int DATABASE_VERSION = 1;
            public static final String DATABASE_NAME = "FeedReader.db";

            @Override
            public void onCreate(SQLiteDatabase db) {db.execSQL(SQL_CREATE_ENTRIES);}

            // Need to implement it, but we wont be changing database so its unnecessary
            @Override
            public void onUpgrade(SQLiteDatabase db, int a, int b) {}
        }
    }

    public static abstract class Medication implements BaseColumns {
        public static final String TABLE_NAME = "MEDICATION";
        public static final String ID = "ID";
        public static final String NAME = "Name";
        public static final String TIME = "Time";
        public static final String DOSE = "Dose";

        private static String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + Medication.TABLE_NAME + " (" +
                        Medication.ID + " INTEGER PRIMARY KEY" + AUTOINCREMENT + NOT_NULL + UNIQUE + COMMA_SEP +
                        Medication.NAME + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                        Medication.TIME + TEXT_TYPE + NOT_NULL + UNIQUE + COMMA_SEP +
                        Medication.DOSE + REAL + NOT_NULL + ")";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + Medication.TABLE_NAME;

        // Have an inner class extending SQLiteOpenHelper to help with long running operations
        private class FeedReaderDbHelper extends SQLiteOpenHelper {

            public FeedReaderDbHelper(Context context){
                super(context, "database.db", null, 1);
            }
            // If you change the database schema, you must increment the database version.
            public static final int DATABASE_VERSION = 1;
            public static final String DATABASE_NAME = "FeedReader.db";

            @Override
            public void onCreate(SQLiteDatabase db) {db.execSQL(SQL_CREATE_ENTRIES);}

            // Need to implement it, but we wont be changing database so its unnecessary
            @Override
            public void onUpgrade(SQLiteDatabase db, int a, int b) {}
        }
    }

    public static abstract class Regimen implements BaseColumns {
        public static final String TABLE_NAME = "REGIMEN";
        public static final String ID = "ID";
        public static final String DIET_NAME = "DietName";
        public static final String EXERCISE_NAME = "ExerciseName";
        public static final String MEDICATION_NAME = "MedicationName";
        public static final String TIME = "Time";

        private static String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + Regimen.TABLE_NAME + " (" +
                        Regimen.ID + " INTEGER PRIMARY KEY" + AUTOINCREMENT + NOT_NULL + UNIQUE + COMMA_SEP +
                        Regimen.DIET_NAME + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                        Regimen.EXERCISE_NAME + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                        Regimen.MEDICATION_NAME + TEXT_TYPE +  NOT_NULL + COMMA_SEP +
                        Regimen.TIME + TEXT_TYPE + NOT_NULL + UNIQUE + ")";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + Regimen.TABLE_NAME;

        // Have an inner class extending SQLiteOpenHelper to help with long running operations
        private class FeedReaderDbHelper extends SQLiteOpenHelper {

            public FeedReaderDbHelper(Context context){
                super(context, "database.db", null, 1);
            }
            // If you change the database schema, you must increment the database version.
            public static final int DATABASE_VERSION = 1;
            public static final String DATABASE_NAME = "FeedReader.db";

            @Override
            public void onCreate(SQLiteDatabase db) {db.execSQL(SQL_CREATE_ENTRIES);}

            // Need to implement it, but we wont be changing database so its unnecessary
            @Override
            public void onUpgrade(SQLiteDatabase db, int a, int b) {}
        }
    }
}
