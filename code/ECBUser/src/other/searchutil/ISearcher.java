package other.searchutil;

import java.util.List;

public interface ISearcher {
	public List<Object> searchOn(List<? extends Object> listObject, String keyword) ;
	public boolean isMatched(Object object, String keyword) ;
}
