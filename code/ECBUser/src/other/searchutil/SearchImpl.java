package other.searchutil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ecb.bean.DockingStation;

 class TestClass
{
    private String x="bách khoa";
    private int y;
    private boolean z;
}
 class Test
{
    public static void main(String[] args) throws SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException 
    {
    	
        Class clazz = new TestClass().getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) 
        {
        	field.setAccessible(true);
            System.out.println("fieldName: "+field.getName()+", fieldType: "+field.get((Object)new TestClass()));
        }
    }
}

public class SearchImpl implements ISearcher{
	
	public static String removeAccent(String s) {
		  
		  String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		  Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		  return pattern.matcher(temp).replaceAll("");
	}
	
	public Map<String, String> getObjectFields(Object obj) {
		try {
				Map<String, String> map = new HashMap<String, String>();
				Class clazz = DockingStation.class;
		        Field[] fields = clazz.getDeclaredFields();
		        for (Field field : fields) 
		        {
		        	field.setAccessible(true);
		        	if(field.get(obj) != null)
		        		map.put(field.getName(), field.get(obj).toString());
		        }
	        return map;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String makeSmoothKeyword(String keyword) {
		return String.join(".*", removeAccent(keyword).replaceAll(" ", "")
				.split(""));
	}

	@Override
	public List<Object> searchOn(List<? extends Object> listObject, String keyword) {
		String newKey = makeSmoothKeyword(keyword);
		List<Object> result = new ArrayList<Object>();
		for(Object obj: listObject) {
			for(String fieldValue: getObjectFields(obj).values()) {
				fieldValue = removeAccent(fieldValue);
			    Pattern pattern = Pattern.compile(newKey, Pattern.CASE_INSENSITIVE);
				Matcher matcher = pattern.matcher(fieldValue);
			    boolean matchFound = matcher.find();
			    if(matchFound) {
			      result.add(obj);
			      break;
			    } else {
			    }
			}
		}
		return result;
	}

	@Override
	public boolean isMatched(Object obj, String keyword) {
		String newKey = makeSmoothKeyword(keyword);
		for(String fieldValue: getObjectFields(obj).values()) {
			fieldValue = removeAccent(fieldValue);
		    Pattern pattern = Pattern.compile(newKey, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(fieldValue);
		    boolean matchFound = matcher.find();
		    if(matchFound) {
		      return true;
		    } 
		}
		return false;
	}

	
	public static void main(String[] args) throws Exception{
//		Test.main(null);
//		System.out.println(new SearchImpl().searchOn(Arrays.asList(new TestClass(), new TestClass(), new TestClass()) ,  "bach khoáa"  ));
	}

}
