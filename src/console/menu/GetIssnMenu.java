package console.menu;

import java.util.ArrayList;

public class GetIssnMenu  extends MenuGeneratorImpl {
    
    @Override
    public String getAnswer() {
        ArrayList<String> lst=new ArrayList<>();
        lst.add("");
        lst.add("МЕНЮ ПОИСКА ИЗДАНИЯ ПО ИНДЕКСУ (ISSN)");
        lst.add("Введите 3-8 цифр подписного индекса:");
        lst.add("> "); 
        return generate(lst);
    }
}
