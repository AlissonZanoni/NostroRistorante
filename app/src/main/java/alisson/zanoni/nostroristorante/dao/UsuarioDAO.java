package alisson.zanoni.nostroristorante.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import alisson.zanoni.nostroristorante.DBHelper;
import alisson.zanoni.nostroristorante.model.UsuarioModel;

public class UsuarioDAO extends AbstractDAO{

    private final String[] colunas =
            {
                    UsuarioModel.COLUNA_ID,
                    UsuarioModel.COLUNA_NOME,
                    UsuarioModel.COLUNA_SOBRENOME,
                    UsuarioModel.COLUNA_DATANASCIMENTO,
                    UsuarioModel.COLUNA_EMAIL,
                    UsuarioModel.COLUNA_SENHA,
                    UsuarioModel.COLUNA_TIPO_USUARIO,
            };

    public UsuarioDAO(final Context context) {
        db_helper = new DBHelper(context);
    }

    public ArrayList<UsuarioModel> Select() {

        open();

        Cursor cursor = db.query
                (
                        UsuarioModel.tabela,
                        colunas,
                        null,
                        null,
                        null,
                        null,
                        null
                );
        cursor.moveToFirst();
        ArrayList<UsuarioModel> arl = new ArrayList<>();

        while (!cursor.isAfterLast()) {
            arl.add(CursorToStructure(cursor));
            cursor.moveToNext();
        }

        close();

        return arl;
    }

    public final UsuarioModel CursorToStructure(Cursor cursor) {
        UsuarioModel model = new UsuarioModel();
        model.setId(cursor.getLong(0));
        model.setNome(cursor.getString(1));
        model.setSobrenome(cursor.getString(2));
        model.setDataNascimento(cursor.getString(3));
        model.setEmail(cursor.getString(4));
        model.setSenha(cursor.getString(5));
        model.setTipoUsuario(cursor.getInt(6));
        return model;
    }

    public ArrayList<String> SelectEmail() {

        open();

        Cursor cursor = db.query
                (
                        UsuarioModel.tabela,
                        colunas,
                        null,
                        null,
                        null,
                        null,
                        null
                );
        cursor.moveToFirst();
        ArrayList<String> arl = new ArrayList<>();

        while (!cursor.isAfterLast()) {
            arl.add(CursorToStructureEmail(cursor));
            cursor.moveToNext();
        }

        close();

        return arl;
    }

    public final String CursorToStructureEmail(Cursor cursor) {
        return cursor.getString(4);
    }

    public UsuarioModel SelectUsuarioEmailLogin(String email) {

        open();

        Cursor cursor = db.query
                (
                        UsuarioModel.tabela,
                        colunas,
                        null,
                        null,
                        null,
                        null,
                        null
                );
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            UsuarioModel user = CursorToStructure(cursor);
            if(user.getEmail().equals(email)) {
                close();
                return user;
            }
            cursor.moveToNext();
        }

        close();

        return null;
    }

    public long Insert(final String nome, final String sobrenome, final String dataNascimento, final String email, final String senha) {

        open();

        ContentValues values = new ContentValues();
        values.put(UsuarioModel.COLUNA_NOME, nome);
        values.put(UsuarioModel.COLUNA_SOBRENOME, sobrenome);
        values.put(UsuarioModel.COLUNA_DATANASCIMENTO, dataNascimento);
        values.put(UsuarioModel.COLUNA_EMAIL, email);
        values.put(UsuarioModel.COLUNA_SENHA, senha);
        values.put(UsuarioModel.COLUNA_TIPO_USUARIO, 0);

        long result = db.insert(UsuarioModel.tabela, null, values);

        close();

        return result;
    }
}
