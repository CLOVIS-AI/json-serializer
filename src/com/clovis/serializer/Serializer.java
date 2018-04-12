/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clovis.serializer;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Ivan Canet
 */
public final class Serializer {
    
    public final JsonObject save(Object o){
        JsonObject json = new JsonObject();
        
        // Get all fields that have the annotation.
        List<Field> fields = Stream
                .of(o.getClass().getDeclaredFields())
                .filter(f -> f.getAnnotation(JsonSerializer.class) != null)
                .collect(Collectors.toList());
        
        // For each field, save it as specified
        for(Field f : fields){
            JsonSerializer a = f.getAnnotation(JsonSerializer.class);
            //json.add(a.fieldName(), f.get);
        }
        
        return json;
    }
    
    static void addObject(JsonObject json, Field f, JsonSerializer a, Object o){
        try {
            Object content = f.get(o);
            System.out.println("Object to serialize: <" + content + ">, of type: <" 
                    + content.getClass() + ">");
            Class c = content.getClass();
            if      (c == Integer.class)    json.add(f.getName(), f.getInt(o));
            else if (c == Short.class)      json.add(f.getName(), f.getShort(o));
            else if (c == Byte.class)       json.add(f.getName(), f.getByte(o));
            else if (c == Long.class)       json.add(f.getName(), f.getLong(o));
            else if (c == Float.class)      json.add(f.getName(), f.getFloat(o));
            else if (c == Double.class)     json.add(f.getName(), f.getDouble(o));
            else if (c == Boolean.class)    json.add(f.getName(), f.getBoolean(o));
            else if (content instanceof Collection) {
                JsonArray j = new JsonArray();
                
            }
            
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(Serializer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
