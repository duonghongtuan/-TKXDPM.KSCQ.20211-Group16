package api;

public class EcoBikeApiFactory {
	private static EcoBikeApi instance;
	
	public static EcoBikeApi getInstance(){
		if (instance == null){ synchronized(EcoBikeApiFactory.class){
		if (instance == null){ instance = new EcobikeApiImpl();
		} }
		}
		return instance;
	}
	
}
