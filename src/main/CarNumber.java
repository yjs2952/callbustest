package main;

import java.util.Arrays;
import java.util.function.Predicate;

public class CarNumber {

    private static final int NUMBER_SIZE = 9;
    private static final int COUNT = 4;
    private static final int[] CAR_NUMBER_COUNT = {2, 2, 1, 4};
    private static final String[] CAR_NUMBER_TYPE = {"char", "number", "char", "number"};

    boolean isRightCarNumFormat(String carNumber) {
        validationCheck(carNumber);

        int subStringIndex = 0;
        for (int i = 0; i < COUNT; i++) {
            String carNumberSub = carNumber.substring(subStringIndex, subStringIndex + CAR_NUMBER_COUNT[i]);
            if (!isRightCarNumberType(i, carNumberSub)) {
                return false;
            }
            subStringIndex += CAR_NUMBER_COUNT[i];
        }
        return true;
    }

    private boolean isRightCarNumberType(int i, String carNumberTypeSub) {
        for (int j = 0; j < CAR_NUMBER_COUNT[i]; j++) {
            boolean isRightType = CarNumberType.isRightType(CAR_NUMBER_TYPE[i], carNumberTypeSub.charAt(j));

            if (!isRightType) {
                return false;
            }
        }
        return true;
    }

    private void validationCheck(String carNum) {
        if (carNum.replaceAll(" ", "").isEmpty()) {
            throw new IllegalArgumentException("공백 혹은 빈 문자열을 입력할 수 없습니다.");
        }

        if (carNum.length() != NUMBER_SIZE) {
            throw new IllegalArgumentException("입렵값은 9자 이어야 합니다.");
        }
    }

    public static void main(String[] args) {
        CarNumber carNumber = new CarNumber();
        String input = "서울27가8421";
        String input2 = "경북54아0101";
        String input3 = "12오7878인천";
        String input4 = "8454경기45구";
        String input5 = "8454경기45구rnsh";
        String input6 = "   ";

        System.out.println("서울27가8421 는 true 를 리턴한다. : " + carNumber.isRightCarNumFormat(input));
        System.out.println("경북54아0101 는 true 를 리턴한다. : " + carNumber.isRightCarNumFormat(input2));
        System.out.println("12오7878인천 는 false 를 리턴한다. : " + carNumber.isRightCarNumFormat(input3));
        System.out.println("8454경기45구 는 false 를 리턴한다. : " + carNumber.isRightCarNumFormat(input4));
        System.out.println("8454경기45구rnsh 는 false 를 리턴한다. : " + carNumber.isRightCarNumFormat(input5));
        System.out.println("    는 false 를 리턴한다. : " + carNumber.isRightCarNumFormat(input6));
    }

    public enum CarNumberType {
        CHAR("char", ch -> Character.getType(ch) == 5),
        NUMBER("number", Character::isDigit);

        private final String type;
        private final Predicate<Character> expression;

        CarNumberType(String type, Predicate<Character> expression) {
            this.type = type;
            this.expression = expression;
        }

        public static boolean isRightType(String type, char input){
            return Arrays.stream(values())
                    .filter(value -> value.type.equals(type))
                    .findFirst()
                    .map(carNumberType -> carNumberType.expression.test(input))
                    .orElse(false);
        }
    }
}
