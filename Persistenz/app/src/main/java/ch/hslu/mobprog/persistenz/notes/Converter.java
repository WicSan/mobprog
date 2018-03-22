package ch.hslu.mobprog.persistenz.notes;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by sandr on 19.03.2018.
 */

public class Converter {
    @TypeConverter
    public static Date fromTimestamp(Long value){
        return value == null ? null :  new Date(value);
    }

    @TypeConverter
    public static Long datetoTimestamp(Date date){
        return date == null ? null : date.getTime();
    }
}
