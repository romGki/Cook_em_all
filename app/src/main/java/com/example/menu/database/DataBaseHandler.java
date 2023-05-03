package com.example.menu.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataBaseHandler extends SQLiteOpenHelper
{

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "ex_recipes.db";
    private final String DB_PATH = "/data/data/com.example.menu/databases";

    SQLiteDatabase db;

    private final Context mContext;


    public DataBaseHandler(Context mContext, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(mContext, DB_NAME, factory, DB_VERSION);

        this.mContext = mContext;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {}

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    private boolean checkDB()
    {
        try
        {
            String myPath = "/data/data/com.example.menu/databases/ex_recipes.db";
            final File dbFile = new File(myPath);

            if(dbFile.exists())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    private void copyDB() throws IOException
    {
        try
        {
           InputStream mInputStream = mContext.getAssets().open(DB_NAME);
           String outFileName = "/data/data/com.example.menu/databases/ex_recipes.db";
           OutputStream mOutputStream = new FileOutputStream(outFileName);

           byte[] buffer = new byte[1024];
           int length;
           while ((length = mInputStream.read(buffer))>0)
           {
               mOutputStream.write(buffer, 0, length);
           }

           mOutputStream.flush();
           mOutputStream.close();
           mInputStream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void createDB() throws IOException
    {
        boolean dbExist = checkDB();

        if(!dbExist)
        {
            this.getReadableDatabase();
            this.close();

            try
            {
                copyDB();
            }
            catch (IOException e)
            {
                e.printStackTrace();
                throw new Error("Error copying database");
            }
            finally
            {
                this.close();
            }
        }
    }

    @Override
    public synchronized void close()
    {
        if(db != null)
        {
            db.close();
        }

        SQLiteDatabase.releaseMemory();

        super.close();
    }

    public String loadHandler()
    {
        try
        {
            createDB();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        String result = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Recipes", null);

        if(cursor.moveToFirst())
        {
            do
            {
                int result_0 = cursor.getInt(0);
                String result_1 = cursor.getString(1);
                String result_2 = cursor.getString(2);
                String result_3 = cursor.getString(3);

                result += String.valueOf(result_0) + " " + result_1 + " " + result_2 + " " + result_3 + System.getProperty("line.separator");
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return result;
    }
}