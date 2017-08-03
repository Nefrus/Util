package util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * 
 * @author xiachenxiang
 *
 * @param <T>
 */
public class SetDeclaredField<T> {
	/**
	 * 
	 * @param t：要填充的对象
	 * @param map：对应的数据（String|Date|Float|Integer）
	 * @return
	 */
	public static <T> T getObject(T t, Map<String, Object> map) {
		try {

			Field[] filed = t.getClass().getDeclaredFields();
			for (int i = 0; i < filed.length; i++) {
				filed[i].setAccessible(true);
				if (filed[i].getType().equals(String.class)) {
					filed[i].set(t, map.get(filed[i].getName()));

				} else if (filed[i].getType().equals(Date.class)) {

					filed[i].set(t, (Date) map.get(filed[i].getName()));

				} else if (filed[i].getType().equals(Float.class)) {
					filed[i].set(t, ((BigDecimal) map.get(filed[i].getName())).floatValue());

				} else if (filed[i].getType().equals(Integer.class)) {
					filed[i].set(t, (Integer) map.get(filed[i].getName()));
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return t;
	}

	public static <T> void getObjectFor(T t) {
		try {

			Field[] filed = t.getClass().getDeclaredFields();
			for (int i = 0; i < filed.length; i++) {
				System.out.print(
						"t.set" + filed[i].getName().substring(0, 1).toUpperCase() + filed[i].getName().substring(1));
				if (filed[i].getType().equals(String.class)) {
					System.out.println("(map.get(\"" + filed[i].getName() + "\")+\"\");");
					// filed[i].set(t, map.get(filed[i].getName()));

				} else if (filed[i].getType().equals(Date.class)) {
					System.out.println("((Date)map.get(\"" + filed[i].getName() + "\"));");
					// filed[i].set(t, (Date) map.get(filed[i].getName()));

				} else if (filed[i].getType().equals(Float.class)) {
					System.out.println("(((BigDecimal)map.get(\"" + filed[i].getName() + "\")).floatValue());");
					// filed[i].set(t, ((BigDecimal) map.get(filed[i].getName())).floatValue());

				} else if (filed[i].getType().equals(Integer.class)) {
					System.out.println("((Integer)map.get(\"" + filed[i].getName() + "\"));");
					// filed[i].set(t, (Integer) map.get(filed[i].getName()));
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	public static <T> void getDeclaredField(T t) {

		Field[] filed = t.getClass().getDeclaredFields();
		for (int i = 0; i < filed.length; i++) {
			System.out.println("	 * "+filed[i].getName() + ":"
					+ filed[i].getType().toString().substring(filed[i].getType().toString().lastIndexOf('.')+1)+" \\\\");
		}

	}

	public static <T> void getObjectBy(T t) {
	
			Field[] filed = t.getClass().getDeclaredFields();
			for (int i = 0; i < filed.length; i++) {
				System.out.println("map.put(\"" + filed[i].getName() + "\",);");
			}
	
	}
}
