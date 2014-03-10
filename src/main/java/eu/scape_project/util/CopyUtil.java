/*
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package eu.scape_project.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import eu.scape_project.model.TechnicalMetadataList;

/**
*
* @author frank asseg
*
*/
public abstract class CopyUtil {
    @SuppressWarnings("unchecked")
    public static <T> T deepCopy(Class<?> type, T obj) {
        if (obj==null){
            return null;
        }
        if (type == String.class) {
            return (T) new String((String) obj);
        } else if (type == Integer.class || type == int.class) {
            return (T) (Integer) obj;
        } else if (type == BigInteger.class) {
            return (T) (BigInteger) obj;
        } else if (type == Long.class || type == long.class) {
            return (T) (Long) obj;
        } else if (type == Double.class || type == double.class) {
            return (T) (Double) obj;
        } else if (type == Float.class || type == float.class) {
            return (T) (Float) obj;
        } else if (type == Short.class || type == short.class) {
            return (T) (Short) obj;
        } else if (type == Boolean.class || type == boolean.class) {
            return (T) (Boolean) obj;
        } else if (type == Date.class) {
            return (T) new Date(((Date) obj).getTime());
        } else if (obj instanceof List) {
            List<Object> origList = (List<Object>) obj;
            if (origList != null) {
                List<Object> newList = new ArrayList<Object>(origList.size());
                for (Object element : origList) {
                    newList.add(deepCopy(element.getClass(), element));
                }
                return (T) newList;
            }
        } else if (obj instanceof double[]) {
            double[] origArr = (double[]) obj;
            double[] newArr = new double[origArr.length];
            for (int i = 0; i < origArr.length; i++) {
                newArr[i] = origArr[i];
            }
            return (T) newArr;
        } else if (obj instanceof byte[]) {
            byte[] origArr = (byte[]) obj;
            byte[] newArr = new byte[origArr.length];
            for (int i = 0; i < origArr.length; i++) {
                newArr[i] = origArr[i];
            }
            return (T) newArr;
        } else if (obj instanceof Object[]) {
            Object[] origArr = (Object[]) obj;
            Object[] newArr = new Object[origArr.length];
            for (int i = 0; i < origArr.length; i++) {
                newArr[i] = deepCopy(origArr[i].getClass(), origArr[i]);
            }
            return (T) newArr;
        }

        T copy = null;
        if (type.isEnum()) {
            return obj;
        }
        if (obj instanceof JAXBElement){
            JAXBElement orig = (JAXBElement) obj;
            copy = (T) new JAXBElement(orig.getName(),orig.getDeclaredType(),orig.getValue());
        }else if (obj instanceof QName){
            return obj;
        }else if (obj instanceof Class){
            return obj;
        }else if (obj instanceof TechnicalMetadataList) {
        	copy = (T) new TechnicalMetadataList.Builder()
        		.records(((TechnicalMetadataList) obj).getRecords())
        		.build();
    	}else {
            for (Constructor<?> c : type.getDeclaredConstructors()) {
                if (c.getParameterTypes().length == 0) {
                    if (!c.isAccessible()) {
                        c.setAccessible(true);
                    }
                    try {
                        copy = (T) c.newInstance();
                    } catch (Exception e) {
                        throw new RuntimeException(
                                "Unable to instantiate copied object", e);
                    }
                }
            }
        }
        if (copy == null) {
            throw new RuntimeException("Unable to instantaiate copy of type "
                    + type.getName());
        }
        for (Field f : obj.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            if (!Modifier.isStatic(f.getModifiers())) {
                try {
                    Object val = f.get(obj);
                    if (val != null) {
                        f.set(copy, deepCopy(val.getClass(), val));
                    }
                } catch (Exception e) {
                    try {
                        throw new RuntimeException(
                                "Unable to deep copy object of type "
                                        + f.getType().getName() + " and value "
                                        + f.get(obj), e);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
        return copy;
    }
}