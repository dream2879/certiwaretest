package com.certiware.backend;

import java.lang.reflect.Field;

public class Log {
	
	public static void setLog(Object object, String tab){
		
		try {			
		
			Class<?> c = object.getClass();		
			
			Field[] fields = c.getDeclaredFields();
			
			for (Field field : fields) {
				field.setAccessible(true);
				
				Object v = field.get(object);
				if(v != null){
					System.out.println(tab + field.getName() + ":" + v.toString());
				}else{
					System.out.println(tab + field.getName() + ":null");
				}		
		}
		}catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}

}
