package hsu;

import java.util.Map;
import java.util.Set;

public class Print_Environment_Variable {
	public static void main(String[] args) {
		Map<?, ?> environment_varMap;
		Set<?> key_set;
		environment_varMap = System.getenv();
		key_set = environment_varMap.keySet();
		for (Object key : key_set.toArray()) {
			System.out.print("key= " + key + ",");
			System.out.println("value= " + environment_varMap.get(key));
		}
//		String java_version = System.getProperty("java.version");
//		System.out.println("java.version="+java_version);
	}

}
