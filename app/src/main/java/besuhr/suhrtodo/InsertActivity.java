package besuhr.suhrtodo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity{
    private DatabaseManager dbManager;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        setContentView(R.layout.activity_insert);
    }

    public void insert(View v){
        //Retrieve name and Due Date
        EditText nameEditText = findViewById(R.id.input_name);
        String name = nameEditText.getText().toString();
        DatePicker dueEditText = findViewById(R.id.input_due);
        int dueDay = dueEditText.getDayOfMonth();
        int dueMonth = dueEditText.getMonth();
        int dueYear = dueEditText.getYear();

        //insert new todolist item in database
        Todo todo = new Todo(0,name,dueDay,dueMonth,dueYear);
        dbManager.insert(todo);
        Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show();


        //Clear data
        nameEditText.setText("");
    }




    public void goBack(View v)
    {
        this.finish();
    }
}
