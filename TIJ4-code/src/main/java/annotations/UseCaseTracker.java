package annotations;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zhangchunhui on 2018/7/22.
 */
public class UseCaseTracker {

    public static void trackUseCases(List<Integer> useCases, Class<?> cl) {
        for (Method m :cl.getDeclaredMethods()){
            UseCase uc = m.getDeclaredAnnotation(UseCase.class);
            if (uc!=null){
                System.out.println("Found UseCase:"+uc.id()+" "+uc.description());
                useCases.remove(new Integer(uc.id()));
            }
        }
        for (Integer i : useCases){
            System.out.println("Warning:Missing UseCase-"+i);
        }
    }

    public static void main(String[] args) {

        List<Integer> useCases = new ArrayList<>();
        Collections.addAll(useCases,47,48,49,50);
        trackUseCases(useCases,PasswordUtils.class);
    }
}
