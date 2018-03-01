package besuhr.suhrtodo;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private DatabaseManager dbManager;
    private ScrollView scrollView;
    private int buttonWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbManager = new DatabaseManager(this);
        scrollView = findViewById(R.id.scrollView);
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        buttonWidth = size.x/2;
        updateView();
    }

    protected void onResume(){
        super.onResume();
        updateView();
    }

    public void updateView(){
        ArrayList<Todo> todos = dbManager.selectAll();
        if(todos.size() > 0){
            //remove subviews inside scrollview if necessary
            scrollView.removeAllViewsInLayout();

            //setup grid
            GridLayout grid = new GridLayout(this);
            grid.setRowCount((todos.size()+1)/2);
            grid.setColumnCount(2);

            TodoButton[] buttons = new TodoButton[todos.size()];
            ButtonHandler bh = new ButtonHandler();

            Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            //fill grid
            int i = 0;
            for (Todo todo : todos){
                buttons[i] = new TodoButton(this, todo);
                buttons[i].setText(todo.getName() + "\n" + todo.getDay() + "/" + todo.getMonth() + "/" + todo.getYear());

               buttons[i].setOnClickListener(bh);

                grid.addView(buttons[i], buttonWidth, GridLayout.LayoutParams.WRAP_CONTENT);
                if (todo.getYear() < mYear){
                    buttons[i].setBackgroundColor(0xFFFF0000);
                } else if (todo.getYear() == mYear && todo.getMonth() < mMonth){
                    buttons[i].setBackgroundColor(0xFFFF0000);
                } else if (todo.getYear() == mYear && todo.getMonth() == mMonth && todo.getDay() < mDay){
                    buttons[i].setBackgroundColor(0xFFFF0000);
                } else
                    buttons[i].setBackgroundColor(0xFF00FF00);
                i++;
            }
            scrollView.addView(grid);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.action_add:
                Log.w("MainActivity","Add Selected");
                Intent insertIntent = new Intent(this, InsertActivity.class);
                this.startActivity(insertIntent);
                overridePendingTransition(R.anim.slide_from_left, 0);
                return true;
            case R.id.action_delete:
                Intent deleteIntent = new Intent(this, DeleteActivity.class);
                this.startActivity(deleteIntent);
                overridePendingTransition(R.anim.fade_in_and_scale, 0);
                Log.w("MainActivity","Delete Selected");
                return true;
            case R.id.action_update:
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

   private class ButtonHandler implements View.OnClickListener {
       public void onClick(View v){

       }
   }
}
