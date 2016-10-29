package com.javarush.test.level36.lesson10.bonus01;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Осваиваем ClassLoader и Reflection
Аргументом для класса Solution является абсолютный путь к пакету,
например, "C:\JavaRushHomeWork\src\com\javarush\test\level36\lesson10\bonus01\data\second".
Имя пакета может содержать File.separator.
В этом пакете находятся только скомпилированные классы.
Известно, что каждый класс имеет конструктор без параметров и реализует интерфейс HiddenClass.
Считайте все классы с файловой системы, создайте фабрику - реализуйте метод getHiddenClassObjectByKey.
Известно, что есть только один класс, простое имя которого начинается с String key без учета регистра.
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution("C:\\Users\\MVTitov\\JavaRushHomeWork\\out\\production\\JavaRushHomeWork\\com\\javarush\\test\\level36\\lesson10\\bonus01\\data\\second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplse"));
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplf"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        String[] classFiles = new File(packageName).list();

        for (String classFile : classFiles) {
            final String finalPath = packageName+File.separator;
            ClassLoader loader = new ClassLoader()
            {
                @Override
                protected Class<?> findClass(String name) throws ClassNotFoundException
                {
                    try
                    {
                        byte[] temp = Files.readAllBytes(Paths.get(finalPath + name +".class"));
                        return defineClass(null,temp,0,temp.length);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                        return super.findClass(name);
                    }
                }
            };
            String className = classFile.substring(0,classFile.length()-6);
            try {
                Class clazz = loader.loadClass(className);
                hiddenClasses.add(clazz);
            } catch (Exception e) {
                throw new ClassNotFoundException();
            }
        }

    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        HiddenClass requiredObject = null;
        for (Class hiddenClass : hiddenClasses) {
            if (hiddenClass.getSimpleName().toLowerCase().startsWith(key.toLowerCase())) {
                try {
                    Constructor<HiddenClass> constructor = hiddenClass.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    requiredObject = constructor.newInstance();
                } catch (Exception e) {
                }

            }
        }
        return requiredObject;
    }


}

