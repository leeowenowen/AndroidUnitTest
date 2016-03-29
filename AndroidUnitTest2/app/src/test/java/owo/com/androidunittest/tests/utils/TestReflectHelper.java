package owo.com.androidunittest.tests.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestReflectHelper {

	public static void printMethods(Class<?> cls) {
		Method[] methods = cls.getDeclaredMethods();
		for (Method method : methods) {
			String name = method.getName();
			Class<?>[] parameterType = method.getParameterTypes();
			Class<?> returnType = method.getReturnType();
			System.out.println(name);
			System.out.println(returnType.getName());
			for (Class<?> class1 : parameterType)
				System.out.println(class1);
		}
	}

	public static <T> void setFieldValue(Object obj, String fieldName, T value) {
		try {
			Field field = obj.getClass().getDeclaredField(fieldName);
			boolean accessible = field.isAccessible();
			field.setAccessible(true);
			field.set(obj, value);
			field.setAccessible(accessible);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static <T> void setFieldValue(Class<?> cls, String fieldName, T value) {
		try {
			Field field = cls.getDeclaredField(fieldName);
			boolean accessible = field.isAccessible();
			field.setAccessible(true);
			field.set(null, value);
			field.setAccessible(accessible);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static <T> T getFieldValue(Class<?> cls, String fieldName) {
		T value = null;
		try {
			Field field = cls.getDeclaredField(fieldName);
			boolean accessible = field.isAccessible();
			field.setAccessible(true);
			value = (T) field.get(null);
			field.setAccessible(accessible);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static <T> T getFieldValue(Object obj, String fieldName) {
		T value = null;
		try {
			Field field = obj.getClass().getDeclaredField(fieldName);
			boolean accessible = field.isAccessible();
			field.setAccessible(true);
			value = (T) field.get(obj);
			field.setAccessible(accessible);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
}
