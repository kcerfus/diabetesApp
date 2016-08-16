package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kcerfus on 8/16/2016.
 */
public class WundergradsDb extends SQLiteOpenHelper {

    public static final String dbName = "wundergrads.db";
    private SQLiteDatabase db;

    // need these attributes for our columns values, they are all supported by SQLite
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String AUTOINCREMENT = "AUTOINCREMENT";
    private static final String NOT_NULL = "NOT NULL";
    private static final String REAL = "REAL";
    private static final String UNIQUE = "UNIQUE";

    //Universal attributes for multiple tables
    String time = "TIME";

    //BGL table attributes
    String bglTable = "BGL";
    String bglId = "ID";
    String bglLevel = "LEVEL";

    //DIET table attributes
    String dietTable = "DIET";
    String dietId = "ID";
    String dietName = "NAME";
    String dietCals = "CALORIES";

    //EXERCISE table attributes
    String exerTable = "EXERCISE";
    String exerId = "ID";
    String exerName = "NAME";
    String exerCals = "CALORIES";

    //MEDICATION table attributes
    String medTable = "MEDICATION";
    String medId = "ID";
    String medName = "NAME";
    String dose = "DOSE";

    //REGIMEN table attributes
    String regTable = "REGIMEN";
    String dietTypeName = "DIET NAME";
    String exerTypeName = "EXERCISE NAME";
    String medTypeName = "MEDICATION NAME";

    public WundergradsDb(Context context) {
        super(context, dbName, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " +bglTable + " (" +
                bglId + " INTEGER PRIMARY KEY" + AUTOINCREMENT + NOT_NULL + UNIQUE + COMMA_SEP +
                bglLevel + REAL + NOT_NULL + COMMA_SEP +
                time + TEXT_TYPE + NOT_NULL + UNIQUE + " )");
        sqLiteDatabase.execSQL("CREATE TABLE " +dietTable + " (" +
                dietId + " INTEGER PRIMARY KEY" + AUTOINCREMENT + NOT_NULL + UNIQUE + COMMA_SEP +
                dietName + TEXT_TYPE + NOT_NULL + UNIQUE +
                time + TEXT_TYPE + NOT_NULL + UNIQUE +
                dietCals + REAL + NOT_NULL + " )");
        sqLiteDatabase.execSQL("CREATE TABLE " +exerTable + " (" +
                exerId + " INTEGER PRIMARY KEY" + AUTOINCREMENT + NOT_NULL + UNIQUE + COMMA_SEP +
                exerName + TEXT_TYPE + NOT_NULL + UNIQUE +
                time + TEXT_TYPE + NOT_NULL + UNIQUE +
                exerCals + REAL + NOT_NULL + " )");
        sqLiteDatabase.execSQL("CREATE TABLE " + medTable + " (" +
                medId + " INTEGER PRIMARY KEY" + AUTOINCREMENT + NOT_NULL + UNIQUE + COMMA_SEP +
                medName + TEXT_TYPE + NOT_NULL + UNIQUE +
                time + TEXT_TYPE + NOT_NULL + UNIQUE +
                dose + REAL + NOT_NULL + " )");
        sqLiteDatabase.execSQL("CREATE TABLE " + regTable + " (" +
                medId + " INTEGER PRIMARY KEY" + AUTOINCREMENT + NOT_NULL + UNIQUE + COMMA_SEP +
                dietTypeName + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                exerTypeName + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                medTypeName + TEXT_TYPE + NOT_NULL + COMMA_SEP +
                time + REAL + NOT_NULL + " )");
    }

    // Wont use but mine as well implement it
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ bglTable);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ dietTable);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ exerTable);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ medTable);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ regTable);
        onCreate(sqLiteDatabase);
    }

}
