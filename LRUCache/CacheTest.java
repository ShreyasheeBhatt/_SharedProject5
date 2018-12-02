import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

/**
 * Code to test an <tt>LRUCache</tt> implementation.
 */
public class CacheTest {
	private DataProvider<Integer, String> _provider = new Provider();
	private Cache<Integer, String> cache = new LRUCache(_provider, 5);
	
	class Provider implements DataProvider<Integer, String> {
		private HashMap<Integer, String> _hashMap; 
		
		private Provider() {
			_hashMap = new HashMap<Integer, String>();
			_hashMap.put(1,"One");
			_hashMap.put(2,"Two");
			_hashMap.put(3,"Three");
			_hashMap.put(4, "Four");
			_hashMap.put(5, "Five");
		}
		
		@Override
		public String get(Integer key) {
			return _hashMap.get(key);
		}
	}
	
	@Test
	public void missWhenEmptyCache() {
		cache.get(1);
		assertEquals(1, cache.getNumMisses());
	}
	
	public void missWhenNotInCache() {
		cache.get(2);
		assertEquals(2,cache.getNumMisses());
	}
	
	public void hitWhenInCache() {
		cache.get(1);
		assertEquals(2,cache.getNumMisses());
	}
	
	public void updateLSUIsCorrect() {
		
	}
	
	public void leastRecentlyUsedIsCorrect () 
	{
		DataProvider<Integer,String> provider = _provider;
		Cache<Integer,String> cache = new LRUCache<Integer,String>(provider, 5);
	}
}
