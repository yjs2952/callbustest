package main;

import java.util.Arrays;
import java.util.function.Predicate;

public class CallBus {
    private static final int MON = 0;
    private static final int SUN = 6;
    private static final int FIRST_HOUR_OF_DAY = 0;
    private static final int LAST_HOUR_OF_DAY = 23;

    private static final int END_HOUR = 3;
    private static final int BASE_START_HOUR = 23;
    private static final int WED_START_HOUR = 22;

    public boolean isServiceTime(int day, int hourOfDay) {
        validationCheck(day, hourOfDay);
        return DayOfWeek.isServiceTime(day, hourOfDay);
    }

    private void validationCheck(int day, int hourOfDay){
        if (day < MON || day > SUN) {
            throw new IllegalArgumentException("요일은 0 ~ 6 사이의 숫자를 입력해 주세요.");
        }

        if (hourOfDay < FIRST_HOUR_OF_DAY || hourOfDay > LAST_HOUR_OF_DAY) {
            throw new IllegalArgumentException("시간은 0 ~ 23 사이의 숫자를 입력해 주세요.");
        }
    }

    public static void main(String[] args) {

        CallBus callBus = new CallBus();
        System.out.println("월요일은 오전 시간 입력시 false 를 리턴한다. : " + callBus.isServiceTime(0, 3));
        System.out.println("수요일은 22시 입력시 true 를 리턴한다. : " + callBus.isServiceTime(2, 22));
        System.out.println("일요일은 오후 입력시 false 를 리턴한다. : " + callBus.isServiceTime(6, 23));
        System.out.println("나머지 요일은 23 ~ 3 입력시 true 를 리턴한다. : " + callBus.isServiceTime(1, 23));
        System.out.println("나머지 요일은 23 ~ 3 외 시간을 입력시 false 를 리턴한다. : " + callBus.isServiceTime(3, 4));
        System.out.println("요일은 0 ~ 6 외 숫자 입력 시 예외가 발생한다. : ");
        System.out.println(callBus.isServiceTime(-1, 4));
    }

    public enum DayOfWeek {
        MON(0, (hourOfDay) -> hourOfDay >= CallBus.BASE_START_HOUR),
        WED(2, (hourOfDay) -> hourOfDay <= CallBus.END_HOUR || hourOfDay >= CallBus.WED_START_HOUR),
        SUN(6, (hourOfDay) -> hourOfDay <= CallBus.END_HOUR),
        OTHER(-1, (hourOfDay) -> hourOfDay <= CallBus.END_HOUR || hourOfDay >= CallBus.BASE_START_HOUR);

        private final int day;
        private final Predicate<Integer> expression;

        DayOfWeek(int day, Predicate<Integer> expression) {
            this.day = day;
            this.expression = expression;
        }

        public static boolean isServiceTime(int day, int hourOfDay){
            DayOfWeek dayOfWeek = Arrays.stream(values())
                    .filter(dow -> dow.day == day)
                    .findFirst()
                    .orElse(OTHER);

            return dayOfWeek.expression.test(hourOfDay);
        }
    }
}


