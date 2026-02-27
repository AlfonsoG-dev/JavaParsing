package application.models;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Member {
    private Class<?> myClass;
    public Member(Class<?> myClass) {
        this.myClass = myClass;
    }
    public List<String> getMethodName() {
        List<String> names = new ArrayList<>();
        Method[] methods = myClass.getDeclaredMethods();
        if(methods.length > 0) {
            for(Method m: methods) {
                String n = m.getName();
                if(!n.contains("$")) {
                    names.add(n);
                }
            }
        }
        return names;
    }
    public Map<String, List<String>> getArguments() {
        Map<String, List<String>> args = new HashMap<>();
        Method[] methods = myClass.getDeclaredMethods();
        if(methods.length > 0) {
            for(Method m: methods) {
                Class<?>[] parameterTypes = m.getParameterTypes();
                List<String> types = new ArrayList<>();
                for(Class<?> c: parameterTypes) {
                    types.add(c.getSimpleName());
                }
                args.put(m.getName(), types);
            }
        }
        return args;
    }
}
