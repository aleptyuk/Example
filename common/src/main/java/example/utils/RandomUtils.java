package example.utils;

import example.enums.CharacterSet;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static example.config.AppConfigProvider.MIN_PASS_LENGTH;
import static example.enums.CharacterSet.*;


public class RandomUtils {
    private static final int DEFAULT_NAME_LENGTH = 7;

    private static Random random = new Random();

    private static String generateRandomString(long charactersCount, CharacterSet charactersSet) {
        if (charactersCount < 0) {
            throw new IllegalArgumentException("Characters count must be greater then 0");
        }
        if (StringUtils.isEmpty(charactersSet.getCharacters())) {
            throw new IllegalArgumentException("Characters set must be defined and contain at least one character");
        }
        List chars = Arrays.asList(charactersSet.getCharacters().split(""));
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < charactersCount; i++) {
            stringBuilder.append(getRandomListElement(chars));
        }
        return stringBuilder.toString();
    }

    public static <T> T getRandomListElement(List<T> listCollection) {
        if (listCollection == null || listCollection.isEmpty()) {
            throw new IllegalArgumentException("Source collection must be defined and contain at least one element");
        }
        int randomIndex = random.nextInt(listCollection.size());
        return listCollection.get(randomIndex);
    }

    public static String getRandomString(long charCount, CharacterSet charactersSet, boolean usePrefix) {
        return generateRandomString(charCount, charactersSet);
    }


    public static String generateName(String prefix) {
        return prefix + RandomUtils.getRandomString(DEFAULT_NAME_LENGTH, ENGLISH_ALPHABET, false);
    }

    public static String generateNameWithSuffixes(String suffixes) {
        return RandomUtils.getRandomString(DEFAULT_NAME_LENGTH, ENGLISH_ALPHABET, false) + suffixes;
    }

    public static String generateName() {
        return RandomUtils.getRandomString(DEFAULT_NAME_LENGTH, ENGLISH_ALPHABET, false);
    }

    public static String generateDesription() {
        return generateName() + generateName();
    }

    public static String generatePass() {
        return RandomUtils.getRandomString(MIN_PASS_LENGTH, NUMERIC, false);
    }

    public static String generateVerifyIncorrectCode() {
        return RandomUtils.getRandomString(4, NUMERIC, false);
    }

    public static String generateVerifyThreeNumberCode() {
        return RandomUtils.getRandomString(3, NUMERIC, false);
    }

    public static String generateVerifyFiveNumberCode() {
        return RandomUtils.getRandomString(5, NUMERIC, false);
    }

    public static String generateVerifyCodeWithLetter() {
        return RandomUtils.getRandomString(1, ENGLISH_ALPHABET, false);
    }

    public static String generateVerifyCodeWithLettersEng() {
        return RandomUtils.getRandomString(2, ENGLISH_ALPHABET, false);
    }

    public static String generateVerifyCodeWithLettersRu() {
        return RandomUtils.getRandomString(2, RUSSIAN_ALPHABET, false);
    }

    public static String generatePassSevenSymbols() {
        return RandomUtils.getRandomString(7, NUMERIC, false);
    }

    public static String generatePassFirstPoint() {
        return "." + RandomUtils.getRandomString(MIN_PASS_LENGTH - 1, NUMERIC, false);
    }

    public static String generatePassSixteen() {
        return RandomUtils.getRandomString(16, NUMERIC, false);
    }

    public static boolean generateBoolean() {
        return random.nextInt(2) == 0;
    }

    public static String generateEmail() {
        return generateName() + "@" + generateName() + ".com";
    }

    public static String generatePhoneNumber() {
        return "+" + RandomUtils.getRandomString(12, NUMERIC, false);
    }

    public static String generatePhoneNumberBelarus() {
        return "29"
                + RandomUtils.getRandomString(1, NUMERIC_WITHOUT_NULL, false)
                + RandomUtils.getRandomString(6, NUMERIC, false);
    }

    public static String generateNameSixtyOne() {
        return RandomUtils.getRandomString(61, ENGLISH_ALPHABET, false);
    }

    public static String generateDescriptionTwoHundredFiftySix() {
        return RandomUtils.getRandomString(256, ENGLISH_ALPHABET, false);
    }

    public static String generateNameSixtyEng() {
        return RandomUtils.getRandomString(60, ENGLISH_ALPHABET, false);
    }

    public static String generateNameSixtyRu() {
        return RandomUtils.getRandomString(60, RUSSIAN_ALPHABET, false);
    }

    public static String generateDescriptionTwoHundredFiftyFiveEng() {
        return RandomUtils.getRandomString(255, ENGLISH_ALPHABET, false);
    }

    public static String generateDescriptionTwoHundredFiftyFiveRu() {
        return RandomUtils.getRandomString(255, RUSSIAN_ALPHABET, false);
    }

    public static String generateNumeric() {
        return RandomUtils.getRandomString(10, NUMERIC, false);
    }

    public static String generateSpecialChar() {
        return RandomUtils.getRandomString(32, SPECIAL_CHARS, false);
    }

}
