package com.capgemini.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class PositionalStringParserEngine {
	


	protected String fixedString;

	public PositionalStringParserEngine() {
		super();
	}

	public PositionalStringParserEngine(String fixedString) {
		this.fixedString = fixedString;
	}

	public void parse(Object instance) throws Exception{
		String strValue = null;
		Object valueToSet = null;
		PositionalStringParser psp = null;
		Field[] fields = instance.getClass().getDeclaredFields();

		for (Field field : fields) {

			
			if (field.isAnnotationPresent(PositionalStringParser.class)) {
				
				
				psp = field.getAnnotation(PositionalStringParser.class);
				field.setAccessible(true);
				
	
				
				strValue = fixedString.substring(psp.start(), psp.end());
				
		
							

				// Assegnazione in base al tipo
				if (String.class.isAssignableFrom(field.getType())) {

					/*
					 * STRING
					 */
					valueToSet = strValue;

				} else if (BigInteger.class.isAssignableFrom(field.getType())) {

					/*
					 * BIG INTEGER
					 */
					strValue = trimAllWhiteSpaces(strValue);
					if (psp.forceConversion() == true) {
						
						try {
							valueToSet = Integer.parseInt(strValue);
						} catch (Exception e) {
							valueToSet = BigInteger.ZERO;
						}
						
					} else {
						valueToSet = new BigInteger(strValue);
					}

				} else if (Integer.class.isAssignableFrom(field.getType())) {

					/*
					 * INTEGER
					 */
					strValue = trimAllWhiteSpaces(strValue);
					valueToSet = Integer.parseInt(strValue);

				} else if (Double.class.isAssignableFrom(field.getType())) {

					/*
					 * LONG
					 */
					strValue = trimAllWhiteSpaces(strValue);
					valueToSet = Long.parseLong(strValue);

				} else if (BigDecimal.class.isAssignableFrom(field.getType())) {

					/*
					 * BIG DECIMAL
					 */
					strValue = trimAllWhiteSpaces(strValue);
					//strValue=trimFrontZeroes(strValue);
					strValue=strValue.replace(",", "."); // notazione dei decimali col punto
					if (psp.forceConversion() == true) {

						try {
							valueToSet = new BigDecimal(strValue);
						} catch (Exception e) {
							valueToSet = BigDecimal.ZERO;
						}

					} else {
						valueToSet = new BigDecimal(strValue);
					}
					

				}  else if (Float.class.isAssignableFrom(field.getType())) {

					/*
					 * FLOAT
					 */
					strValue = trimAllWhiteSpaces(strValue);
					valueToSet = Float.parseFloat(strValue);

				} else if (Double.class.isAssignableFrom(field.getType())) {

					/*
					 * DOUBLE
					 */
					strValue = trimAllWhiteSpaces(strValue);
					valueToSet = Double.parseDouble(strValue);

				} else if (Short.class.isAssignableFrom(field.getType())) {

					/*
					 * SHORT
					 */
					strValue = trimAllWhiteSpaces(strValue);
					valueToSet = Short.parseShort(strValue);
					
				} else if (field.getType().toString().equals("byte")) {
					
		        	/*
		        	 * BYTE (PRIMITIVE)
		        	 */
					strValue = trimAllWhiteSpaces(strValue);
					valueToSet = Byte.parseByte(strValue);
					
				} else if (field.getType().toString().equals("short")) {

		        	/*
		        	 * SHORT (PRIMITIVE)
		        	 */
					strValue = trimAllWhiteSpaces(strValue);
					valueToSet = Short.parseShort(strValue);
					
				} else if (field.getType().toString().equals("int")) {

		        	/*
		        	 * INT (PRIMITIVE)
		        	 */
					strValue = trimAllWhiteSpaces(strValue);
					
					if (psp.forceConversion() == true) {
						
						try {
							valueToSet = Integer.parseInt(strValue);
						} catch (Exception e) {
							valueToSet = 0;
						}
						
					} else {
						valueToSet = Integer.parseInt(strValue);
					}
					
					
				} else if (field.getType().toString().equals("long")) {

		        	/*
		        	 * LONG (PRIMITIVE)
		        	 */
					strValue = trimAllWhiteSpaces(strValue);
					valueToSet = Long.parseLong(strValue);
					
				} else if (field.getType().toString().equals("float")) {

		        	/*
		        	 * FLOAT (PRIMITIVE)
		        	 */
					strValue = trimAllWhiteSpaces(strValue);
					valueToSet = Float.parseFloat(strValue);
					
				} else if (field.getType().toString().equals("double")) {

		        	/*
		        	 * DOUBLE (PRIMITIVE)
		        	 */
					strValue = trimAllWhiteSpaces(strValue);
					valueToSet = Double.parseDouble(strValue);
					
				} else if (Date.class.isAssignableFrom(field.getType())) {
					
					/*
		        	 * DATE
		        	 */
					valueToSet = null; //TODO
					
				} else if(List.class.isAssignableFrom(field.getType())){
					
		
					
					/*
					 * MULTIPLE TAB_OPE OBJECTS
					 */					
					
					List<Object> objectList = new ArrayList<Object>();
						
					if(( strValue.length()%psp.tabOpeLenght() == 0 )) {
					
						
						int i=0;
						int size = strValue.length()/psp.tabOpeLenght();
						String substr = null;
						
						Object obj = null;
						
						
						PositionalStringParserEngine pspe = null;
						
						
						for(i=0; i < (strValue.length()-size); i=i+size){
							obj = new Object();
						
							substr = strValue.substring(i, i+size);
							pspe = new PositionalStringParserEngine(substr);
							pspe.parse(obj);
							objectList.add(obj);
						}
						
						valueToSet = objectList;
						
					} else {
					
						throw new Exception("Lunghezza stringa NON è divisibile per il numero previsto di tab");
					}
					
				}
				

				try {
					field.set(instance, valueToSet);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	protected String trimAllWhiteSpaces(String str) {
		return str;
	}
	
	protected String trimFrontZeroes(String str){
		return str;
	}

}
