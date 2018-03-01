package besuhr.suhrtodo;

public class Todo {
    private int id;
    private String name;
    private int day;
    private int month;
    private int year;

    public Todo(int newId, String newName, int newDay, int newMonth, int newYear){
        setId(newId);
        setName(newName);
        setDay(newDay);
        setMonth(newMonth);
        setYear(newYear);
    }

    public void setId(int newId){
        id = newId;
    }

    public void setName(String newName){
        name = newName;
    }

    public void setDay(int newDay){
        day = newDay;
    }

    public void setMonth(int newMonth){
        month = newMonth;
    }

    public void setYear(int newYear){
        year = newYear;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getDay(){
        return day;
    }

    public int getMonth(){
        return month;
    }

    public int getYear(){
        return year;
    }

    public String toString(){
        return id + " " + name + " " + day + "/" + month + "/" + year;
    }
}
