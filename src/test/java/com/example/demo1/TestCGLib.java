package com.example.demo1;
import java.lang.reflect.Field;
import java.beans.PropertyDescriptor;
import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.core.ReflectUtils;
public class TestCGLib {

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
        BeanGenerator beanGenerator = new BeanGenerator();

        beanGenerator.addProperty("username", String.class);
        beanGenerator.addProperty("password", String.class);
        Object obj = beanGenerator.create();

        Field[] fields = obj.getClass().getDeclaredFields();

        PropertyDescriptor[] pds = ReflectUtils.getBeanProperties(obj.getClass());
        int i = 0;
        for (PropertyDescriptor pd : pds) {
            System.out.println(pd.getName() + ":" + pd.getDisplayName());//password:password
            pd.setValue(pd.getName(), i);
            System.out.println(pd.getValue(pd.getName()));
            i++;
        }

        for (Field field : fields) {
            System.out.println(field);//private java.lang.String net.sf.cglib.empty.Object$$BeanGeneratorByCGLIB$$558c3ebf.$cglib_prop_username
            System.out.println(field.getName());//$cglib_prop_username
            System.out.println(field.getType());//class java.lang.String
            String setMethodName = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
            System.out.println(setMethodName);//set$cglib_prop_username
            field.setAccessible(true);
            field.set(obj, "111");
            System.out.println(field.get(obj));//111

        }
    }
}