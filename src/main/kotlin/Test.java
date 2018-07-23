import java.util.ArrayList;

public class Test {
    void test(){
        ArrayList<ArrayList<String>> listOfLists = new ArrayList<ArrayList<String>>();
        for(int i = 0; i < 10; i++)  {
            listOfLists.add(new ArrayList<String>());
        }
    }
}
