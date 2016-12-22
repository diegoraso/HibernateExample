package com.capgemini.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Annotation per la valorizzazioen di un oggetto 
 * tramite parsing posizionale di una stringa in modo posizione
 * 
 * @author clusardi
 * @version 1.0
 * 
 * Creation Date: 2016-11-14
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PositionalStringParser {

	public int start();

	public int end();
	
	public int tabOpeLenght() default 1;
	
	public boolean forceConversion() default false;

}