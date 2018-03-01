package besuhr.suhrtodo;

import android.content.Context;
import android.widget.Button;

public class TodoButton extends Button {
    private Todo todo;

    public TodoButton(Context context, Todo newTodo){
        super(context);
        todo = newTodo;
    }

    public int getDue(){
        return todo.getDay();
    }
}
