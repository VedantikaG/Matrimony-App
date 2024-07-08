package com.yaksha.training.matrimony.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yaksha.training.matrimony.entity.Gender;
import com.yaksha.training.matrimony.entity.Matrimony;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MasterData {

    private static Random rnd = new Random();

    public static Matrimony getMatrimony() {
        Matrimony matrimony = new Matrimony();
        matrimony.setId(1L);
        matrimony.setFullName(randomStringWithSize(10));
        matrimony.setGender(rnd.nextInt() % 2 == 0 ? Gender.M : Gender.F);
        matrimony.setAddress(randomStringWithSize(10));
        matrimony.setReligion(randomStringWithSize(10));
        matrimony.setOccupation(randomStringWithSize(10));
        matrimony.setHobbies(randomStringWithSize(10));
        return matrimony;
    }

    public static List<Matrimony> getMatrimonyList(int size) {
        Long id = 0L;
        List<Matrimony> matrimonys = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Matrimony matrimony = new Matrimony();
            matrimony.setId(++id);
            matrimony.setFullName(randomStringWithSize(10));
            matrimony.setGender(rnd.nextInt() % 2 == 0 ? Gender.M : Gender.F);
            matrimony.setAddress(randomStringWithSize(10));
            matrimony.setReligion(randomStringWithSize(10));
            matrimony.setOccupation(randomStringWithSize(10));
            matrimony.setHobbies(randomStringWithSize(10));
            matrimonys.add(matrimony);
        }
        return matrimonys;
    }


    public static Integer randomNumberWithSize(int size) {
        String alphabet = "123456789";
        return rnd.nextInt(alphabet.length());
    }

    public static String randomStringWithSize(int size) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String s = "";
        for (int i = 0; i < size; i++) {
            s = s + (String.valueOf(alphabet.charAt(rnd.nextInt(alphabet.length()))));
        }
        return s;
    }

    public static boolean randomBoolean() {
        String alphabet = "1234567890";
        Random rnd = new Random();
        return rnd.nextInt(alphabet.length()) % 2 == 0;
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            final String jsonContent = mapper.writeValueAsString(obj);

            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
