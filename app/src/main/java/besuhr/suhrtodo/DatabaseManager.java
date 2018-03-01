package besuhr.suhrtodo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "todoDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_TODO = "todo";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DAY = "day";
    private static final String MONTH = "month";
    private static final String YEAR = "year";

    public DatabaseManager(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        //build sql create statement
        String sqlCreate = "create table " + TABLE_TODO + "( " + ID;
        sqlCreate += " integer primary key autoincrement, " + NAME;
        sqlCreate += " text, " + DAY + " real ";
        sqlCreate += " text, " + MONTH + " real ";
        sqlCreate += " text, " + YEAR + " real )";

        db.execSQL(sqlCreate);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //Drop old table if exists
        db.execSQL("drop table if exists " + TABLE_TODO);
        //Recreate the table
        onCreate(db);
    }

    public void insert(Todo todo){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_TODO;
        sqlInsert += " values( null, '" + todo.getName();
        sqlInsert += "', '" + todo.getDay() + "', '" + todo.getMonth() + "', '" + todo.getYear() + "' ) ";

        db.execSQL(sqlInsert);
        db.close();
    }

    public void deleteById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from " + TABLE_TODO;
        sqlDelete += " where " + ID + " = " + id;

        db.execSQL(sqlDelete);
        db.close();
    }

    public void updateById(int id, String name, int day, int month, int year){
        SQLiteDatabase db = this.getWritableDatabase();

        String sqlUpdate = "update " + TABLE_TODO;
        sqlUpdate += " set " + NAME + " = '" + name + "', ";
        sqlUpdate += DAY + " = '" + day + "'";
        sqlUpdate += MONTH + " = '" + month + "'";
        sqlUpdate += YEAR + " = '" + year + "'";
        sqlUpdate += " where " +  ID + " = " + id;

        db.execSQL(sqlUpdate);
        db.close();
    }

    public ArrayList<Todo> selectAll(){
        String sqlQuery = "select * from " + TABLE_TODO;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Todo> todos = new ArrayList<Todo>();
        while(cursor.moveToNext()){
            Todo currentTodo
                    = new Todo(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4));
            todos.add(currentTodo);
        }
        db.close();
        //cursor.close();
        return todos;
    }

    public Todo selectById(int id){
        String sqlQuery = "select * from " + TABLE_TODO;
        sqlQuery += " where " + ID + " = " + id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        Todo todo = null;
        if(cursor.moveToFirst())
            todo = new Todo(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4));
        //db.close();
        //cursor.close();
        return todo;
    }
}