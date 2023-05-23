package com.example.menu.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.menu.R;
import com.example.menu.models.RecipeModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DataBaseHandler extends SQLiteOpenHelper
{
    private int[] images = {R.drawable.recipe1, R.drawable.recipe2, R.drawable.recipe3, R.drawable.recipe4,
            R.drawable.recipe5, R.drawable.recipe6, R.drawable.recipe7, R.drawable.recipe8, R.drawable.recipe9,
            R.drawable.recipe10, R.drawable.recipe11, R.drawable.recipe12, R.drawable.recipe13, R.drawable.recipe14};

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
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {}

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

    public ArrayList<RecipeModel> loadHandler()
    {
        int i = 0;

        try
        {
            createDB();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        ArrayList<RecipeModel> result = new ArrayList<RecipeModel>();
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
                String result_4 = cursor.getString(4);
                String result_5= cursor.getString(5);
                String result_6 = cursor.getString(6);
                String result_7 = cursor.getString(7);
                String result_8 = cursor.getString(8);
                String result_9 = cursor.getString(9);

                RecipeModel recipe = new RecipeModel(result_0, result_1, result_2, result_3, result_4, result_5, images[i], result_6, result_7, result_8, result_9);
                result.add(recipe);

                if(images.length - 1 == i)
                {
                    i = 0;
                }
                else
                {
                    i++;
                }
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return result;
    }
}
