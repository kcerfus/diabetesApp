package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by kcerfus on 8/10/2016.
 */
public final class DiabetesSqlHelper extends SQLiteOpenHelper{

    // need these attributes for our columns values, they are all supported by SQLite
    private static final String TEXT_TYPE = " TEXT ";
    private static final String COMMA_SEP = " , ";
    private static final String AUTOINCREMENT = " AUTOINCREMENT ";
    private static final String NOT_NULL = " NOT NULL ";
    private static final String REAL = " REAL ";
    private static final String UNIQUE = " UNIQUE ";
    private static final String AND = " AND ";
    private static final String BETWEEN = " BETWEEN ";

    private static String dbName = "Diabetes.db";
    private static SQLiteDatabase db;
    public static final int DATABASE_VERSION = 1;

    public DiabetesSqlHelper(Context context){
            super(context, dbName, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(BloodGlucoseMeasurement.SQL_CREATE_ENTRIES);
        sqLiteDatabase.execSQL(Medication.SQL_CREATE_ENTRIES);
        sqLiteDatabase.execSQL(Diet.SQL_CREATE_ENTRIES);
        sqLiteDatabase.execSQL(Exercise.SQL_CREATE_ENTRIES);
        sqLiteDatabase.execSQL(Regimen.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(BloodGlucoseMeasurement.SQL_DELETE_ENTRIES);
        sqLiteDatabase.execSQL(Medication.SQL_DELETE_ENTRIES);
        sqLiteDatabase.execSQL(Diet.SQL_DELETE_ENTRIES);
        sqLiteDatabase.execSQL(Exercise.SQL_DELETE_ENTRIES);
        sqLiteDatabase.execSQL(Regimen.SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    // method to add values to new row in table
    public boolean insertBgl(int bgl, String date, String time) {
        db = super.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(BloodGlucoseMeasurement.BGL, bgl);
        values.put(BloodGlucoseMeasurement.DATE, date);
        values.put(BloodGlucoseMeasurement.TIME, time);

        long addedToTable = db.insert(BloodGlucoseMeasurement.TABLE_NAME, null, values);
        //If addedToTable is -1, nothing was added
        return addedToTable != -1;
    }
        public Cursor queryBgl(String maxBgl, String minBgl, String startDate, String endDate, String earliestTime, String latestTime) {
            db = super.getReadableDatabase();
            String query = "SELECT * FROM " + BloodGlucoseMeasurement.TABLE_NAME + " WHERE ";
            query+=getBglMaxMinString(minBgl,maxBgl) + AND + getDateString(startDate,endDate,BloodGlucoseMeasurement.DATE) + AND + getTimeString(earliestTime,latestTime,BloodGlucoseMeasurement.TIME);
            Cursor c = db.rawQuery(query,null);
            return c;
        }

        private String getBglMaxMinString(String bglMin, String bglMax) {
            String result = "";
            if(!(bglMin.equals("") || bglMax.equals("")))
                result = BloodGlucoseMeasurement.BGL + BETWEEN + bglMin + AND + bglMax;
            else if(bglMin.equals("") && !(bglMax.equals("")))
                result = BloodGlucoseMeasurement.BGL + " < " + bglMax;
            else if(!(bglMin.equals("") && bglMax.equals("")))
                result = BloodGlucoseMeasurement.BGL + " > " + bglMin;
            return result;
        }

        // UNIVERSAL WILL WORK WITH ANY TABLE
        private String getDateString(String startDate, String endDate, String colName) {
            String result = "";
            if(!(startDate.equals("") || endDate.equals("")))
                result = colName + BETWEEN + startDate + AND + endDate;
            else if(startDate.equals("") && !(endDate.equals("")))
                result = colName + " < " + endDate;
            else if(!(startDate.equals("") && endDate.equals("")))
                result = colName + " > " + startDate;
            return result;
        }

        // UNIVERSAL WILL WORK WITH ANY TABLE
        private String getTimeString(String startTime, String endTime, String colName){
            String result = "";
            if(!(startTime.equals("") || endTime.equals("")))
                result = colName + BETWEEN + startTime + AND + endTime;
            else if(startTime.equals("") && !(endTime.equals("")))
                result = colName + " < " + endTime;
            else if(!(startTime.equals("") && endTime.equals("")))
                result = colName + " > " + startTime;
            return result;
        }

        public boolean insertDiet(String name, String date, String time) {
            db = super.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(Diet.NAME,name);
            values.put(Diet.DATE, date);
            values.put(Diet.TIME,time);

            long addedToTable = db.insert(Diet.TABLE_NAME,null,values);
            //If addedToTable is -1, nothing was added
            return addedToTable != -1;
        }

    // method to add values to new row in table
    public boolean insertMedication(String name, String date, String time) {
        db = super.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Medication.NAME,name);
        values.put(Medication.DATE,date);
        values.put(Medication.TIME,time);

        long addedToTable = db.insert(Medication.TABLE_NAME,null,values);
        //If addedToTable is -1, nothing was added
        return addedToTable != -1;
    }

    public boolean insertExercise(String name, String date, String time) {
        db = super.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Exercise.NAME,name);
        values.put(Exercise.TIME,time);

        long addedToTable = db.insert(Exercise.TABLE_NAME,null,values);
        //If addedToTable is -1, nothing was added
        return addedToTable != -1;
    }

    public boolean insertRegimen(String name, String type, String time) {
        db = super.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Regimen.REGIMEN_NAME, name);
        values.put(Regimen.REGIMEN_TYPE, type);
        values.put(Regimen.TIME, time);

        long addedToTable = db.insert(Regimen.TABLE_NAME, null, values);
        //If addedToTable is -1, nothing was added
        return addedToTable != -1;
    }

    // Here we need a class for each table(5 tables total)
    public static abstract class BloodGlucoseMeasurement implements BaseColumns {
        // Define title and column names
        public static final String TABLE_NAME = "BloodGlucoseMeasurement";
        public static final String ID = "ID";
        public static final String BGL = "BGL";
        public static final String DATE = "Date";
        public static final String TIME = "Time";

        //Create columns modeled after Assignment 2
        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + BloodGlucoseMeasurement.TABLE_NAME + " (" +
                        BloodGlucoseMeasurement.ID + " INTEGER PRIMARY KEY" + AUTOINCREMENT + NOT_NULL + UNIQUE + COMMA_SEP +
                        BloodGlucoseMeasurement.BGL + REAL + NOT_NULL + COMMA_SEP +
                        BloodGlucoseMeasurement.DATE + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                        BloodGlucoseMeasurement.TIME + TEXT_TYPE + NOT_NULL + " )";

        // Probably change later to delete certain entries if desired?
        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + BloodGlucoseMeasurement.TABLE_NAME;
    }

    public static abstract class Diet implements BaseColumns {
        // Define title and column names
        public static final String TABLE_NAME = "DIET";
        public static final String ID = "ID";
        public static final String NAME = "Name";
        public static final String DATE = "Date";
        public static final String TIME = "Time";


        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + Diet.TABLE_NAME + " (" +
                        Diet.ID + " INTEGER PRIMARY KEY" + AUTOINCREMENT + NOT_NULL + UNIQUE + COMMA_SEP +
                        Diet.NAME + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                        Diet.DATE + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                        Diet.TIME + TEXT_TYPE + NOT_NULL + ")";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + Diet.TABLE_NAME;
        }

    public static abstract class Exercise implements BaseColumns {
        public static final String TABLE_NAME = "EXERCISE";
        public static final String ID = "ID";
        public static final String NAME = "Name";
        public static final String DATE = "Date";
        public static final String TIME = "Time";

        private static String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + Exercise.TABLE_NAME + " (" +
                        Exercise.ID + " INTEGER PRIMARY KEY" + AUTOINCREMENT + NOT_NULL + UNIQUE + COMMA_SEP +
                        Exercise.NAME + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                        Exercise.DATE + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                        Exercise.TIME + TEXT_TYPE + NOT_NULL +  ")";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + Exercise.TABLE_NAME;

    }

    public static abstract class Medication implements BaseColumns {
        public static final String TABLE_NAME = "MEDICATION";
        public static final String ID = "ID";
        public static final String NAME = "Name";
        public static final String DATE = "Date";
        public static final String TIME = "Time";

        private static String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + Medication.TABLE_NAME + " (" +
                        Medication.ID + " INTEGER PRIMARY KEY" + AUTOINCREMENT + NOT_NULL + UNIQUE + COMMA_SEP +
                        Medication.NAME + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                        Medication.DATE + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                        Medication.TIME + TEXT_TYPE + NOT_NULL + ")";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + Medication.TABLE_NAME;
    }

    public static abstract class Regimen implements BaseColumns {
        public static final String TABLE_NAME = "REGIMEN";
        public static final String ID = "ID";
        public static final String REGIMEN_NAME = "RegimenName";
        public static final String REGIMEN_TYPE = "RegimenType";
        public static final String TIME = "Time";

        private static String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + Regimen.TABLE_NAME + " (" +
                        Regimen.ID + " INTEGER PRIMARY KEY" + AUTOINCREMENT + NOT_NULL + UNIQUE + COMMA_SEP +
                        Regimen.REGIMEN_NAME + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                        Regimen.REGIMEN_TYPE + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                        Regimen.TIME + TEXT_TYPE + NOT_NULL + UNIQUE + ")";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + Regimen.TABLE_NAME;
    }
}
