package other.searchutil.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

import com.ecb.bean.DockingStation;

import other.searchutil.ISearcher;
import other.searchutil.SearchImpl;

@RunWith(Parameterized.class)
public class SearchImplBlackBoxTest {
	
	Object object;
	String keyword;
	Object expectedResult;
	
	public SearchImplBlackBoxTest(Object object, String keyword, Object expectedResult) {
		this.object = object;
		this.keyword = keyword;
		this.expectedResult = expectedResult;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> getAllTestCase(){
		DockingStation[] stations = new DockingStation[] {
			new DockingStation("station0", "Bãi xe thống nhất", "354A Đường Lê Duẩn, Phương Liên, Đống Đa, Hà Nội", 100, 200, 1234567),
			new DockingStation("station1", "Bãi xe giáp bát", "Giải Phóng, Giáp Bát, Hoàng Mai, Hà Nội", 100, 200, 1234567),
			
		};
		return Arrays.asList(new Object[][] {
			{stations[0], "bai thong nhat", true},
			{stations[0], "ben xe my dinh", false},
			{stations[0], "1234567", true},
			{stations[0], "354A Le duan", true},
			
			{stations[1], "giáp bát", true},
			{stations[1], "bãi xe giap bat", true},
			{stations[1], "giai phong giap bat", true},
			{stations[1], "dong da ha noi", false}
		});
	}
	
	@Test
	public void testIsMatched() {
		ISearcher iSearcher = new SearchImpl();
		assertEquals("Error not matched", iSearcher.isMatched(object, keyword), expectedResult);
	}
}
