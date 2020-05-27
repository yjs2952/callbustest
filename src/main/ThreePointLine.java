package main;

public class ThreePointLine {
    private static final int X_POSITION = 0;
    private static final int Y_POSITION = 1;

    public boolean isStraightLine(int[]... points) {
        if (isSamePositionXOrY(points)) {
            return true;
        }
        return isSameInclinations(getInclinations(points));
    }

    private boolean isSameInclinations(double[] inclinations) {
        for (int i = 1; i < inclinations.length; i++) {
            if (inclinations[i - 1] != inclinations[i]) {
                return false;
            }
        }
        return true;
    }

    private double[] getInclinations(int[][] points) {
        double[] inclinations = new double[points.length - 1];
        for (int i = 1; i < points.length; i++) {
            inclinations[i - 1] = (double) (points[i][Y_POSITION] - points[i - 1][Y_POSITION]) / (points[i][X_POSITION] - points[i - 1][X_POSITION]);
        }
        return inclinations;
    }

    private boolean isSamePositionXOrY(int[][] points) {
        int sameXCount = 0;
        int sameYCount = 0;

        for (int i = 1; i < points.length; i++) {
            sameXCount = points[i - 1][X_POSITION] == points[i][X_POSITION] ? ++sameXCount : sameXCount;
            sameYCount = points[i - 1][Y_POSITION] == points[i][Y_POSITION] ? ++sameYCount : sameYCount;
        }
        return sameXCount == 2 || sameYCount == 2;
    }

    public static void main(String[] args) {
        ThreePointLine threePointLine = new ThreePointLine();
        int[][] input = {{0, 0}, {1, 1}, {2, 2}};
        int[][] input2 = {{0, 0}, {1, 2}, {2, 2}};
        int[][] input3 = {{0, 1}, {1, 1}, {2, 1}};
        int[][] input4 = {{-1, -1}, {0, 0}, {2, 2}};

        System.out.println("{0, 0}, {1, 1}, {2, 2} 입력 시 true 를 리턴한다. : " + threePointLine.isStraightLine(input));
        System.out.println("{0, 0}, {1, 2}, {2, 2} 입력 시 false 를 리턴한다. : " + threePointLine.isStraightLine(input2));
        System.out.println("{0, 1}, {1, 1}, {2, 1} 입력 시 true 를 리턴한다. : " + threePointLine.isStraightLine(input3));
        System.out.println("{-1, -1}, {0, 0}, {2, 2} 입력 시 true 를 리턴한다. : " + threePointLine.isStraightLine(input4));
    }
}
