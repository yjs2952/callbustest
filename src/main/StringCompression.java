package main;

public class StringCompression {

    public String compress(String any) {
        validationCheck(any);

        StringBuilder sb = new StringBuilder();
        String[] split = any.split("");
        int sameCharCount = 0;

        for (int i = 0; i < split.length; i += sameCharCount) {
            sameCharCount = getSameCharCount(split, i);
            sb.append(sameCharCount).append(split[i]);
        }
        return sb.toString();
    }

    public String decompress(String compressed) {
        validationCheck(compressed);

        StringBuilder resultStringBuilder = new StringBuilder();
        String[] split = compressed.split("");

        for (int i = 0; i < split.length; i++) {
            StringBuilder numberStringBuilder = new StringBuilder();

            while (canConvertToNumber(split[i])) {
                numberStringBuilder.append(split[i]);
                i++;
            }

            int number = toInteger(numberStringBuilder.toString());
            String value = split[i];

            for (int j = 0; j < number; j++) {
                resultStringBuilder.append(value);
            }
        }
        return resultStringBuilder.toString();
    }

    private int getSameCharCount(String[] split, int index) {
        int sameCharCount = 1;

        while (true) {
            int currentIndex = index + sameCharCount;
            if (currentIndex >= split.length || !split[currentIndex - 1].equals(split[currentIndex])) {
                break;
            }
            sameCharCount++;
        }
        return sameCharCount;
    }

    private void validationCheck(String str) {
        if (str.replaceAll(" ", "").isEmpty()) {
            throw new IllegalArgumentException("공백 혹은 빈 문자열을 입력할 수 없습니다.");
        }
    }

    private boolean canConvertToNumber(String input) {
        return Character.isDigit(input.charAt(0));
    }

    private int toInteger(String input) {
        int number = 0;
        try {
            number = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("정수로 변환할 수 없는 문자 입니다.");
            e.printStackTrace();
        }
        return number;
    }

    public static void main(String[] args) {
        StringCompression sc = new StringCompression();
        System.out.println("AAABBBCCC 입력 시 3A3B3C 를 리턴한다. : " + sc.compress("AAABBBCCC"));
        System.out.println("ZZZAAAAAAAAAABBCCQAA 입력 시 3Z10A2B2C1Q2 를 리턴 한다. : " + sc.compress("ZZZAAAAAAAAAABBCCQAA"));

        System.out.println("3Z10A2B2C185A 입력 시  ZZZAAAAAAAAAABBCCAAAAA…A 를 리턴한다. : " + sc.decompress("3Z10A2B2C185A"));
        System.out.println("3A3B3C 입력 시 AAABBBCCC 를 리턴한다. : " + sc.decompress("3A3B3C"));

        System.out.println("ZZZAAAAAAAAAABBCCQAA 를 압축한 후 다시 압축 해제한 결과와 원본이 같아야 한다. : " + "ZZZAAAAAAAAAABBCCQAA".equals(sc.decompress(sc.compress("ZZZAAAAAAAAAABBCCQAA"))));
        System.out.println("3Z10A2B2C185A 를 압축 해제한 후 다시 압축한 결과와 원본이 같아야 한다. " + "3Z10A2B2C185A".equals(sc.compress(sc.decompress("3Z10A2B2C185A"))));
    }
}
