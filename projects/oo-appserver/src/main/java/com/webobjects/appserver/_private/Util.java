package com.webobjects.appserver._private;

import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSMutableDictionary;

public class Util {

	public static <E, V> NSDictionary<E, V> dictionaryBySettingObjectForKey( NSDictionary<E, V> original, V object, E key ) {

		if( original == null ) {
			return original;
		}

		NSMutableDictionary<E, V> d = original.mutableClone();
		d.setObjectForKey( object, key );
		return d.immutableClone();
	}

	public static <E, V> NSDictionary<E, V> dictionaryByRemovingObjectForKey( NSDictionary<E, V> original, E key ) {

		if( original == null ) {
			return original;
		}

		NSMutableDictionary<E, V> d = original.mutableClone();
		d.removeObjectForKey( key );
		return d.immutableClone();
	}
}