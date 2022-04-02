package alisson.zanoni.nostroristorante.dao;

import android.database.sqlite.SQLiteDatabase;

import alisson.zanoni.nostroristorante.DBHelper;

public class AbstractDAO {
    protected SQLiteDatabase db;
    protected DBHelper db_helper;

    protected final void open() {
        db = db_helper.getWritableDatabase();
    }

    protected final void close() {
        db_helper.close();
    }
}
