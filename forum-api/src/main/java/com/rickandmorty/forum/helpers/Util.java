package com.rickandmorty.forum.helpers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {

    public static String localDateTimeToString(LocalDateTime localDateTime){
        return DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(localDateTime);
    }

    public static LocalDateTime stringToLocalDateTime(String s){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return LocalDateTime.parse(s, formatter);
    }


}
