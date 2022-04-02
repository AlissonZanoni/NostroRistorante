package alisson.zanoni.nostroristorante;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import alisson.zanoni.nostroristorante.model.UsuarioModel;

public class DBHelper extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "restaurante.db";
    private static final int VERSAO_BANCO = 1;

    public DBHelper(@Nullable Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(UsuarioModel.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
