package main;

public class ThreePointLine {

    private static final int X_POSITION = 0;
    private static final int Y_POSITION = 1;

    public boolean isStraightLine(int[]... point) {
        double distanceSum = getSumOfDistanceByPoint(point);
        double totalDistance = getFirstToLastPointDistance(point);
        return distanceSum == totalDistance;
    }

    private double getFirstToLastPointDistance(int[][] points) {
        return getDistance(points, points.length - 1, 0);
    }

    private double getDistance(int[][] points, int point1, int point2) {
        return Math.sqrt((points[point1][X_POSITION] - points[point2][X_POSITION]) * (points[point1][X_POSITION] - points[point2][X_POSITION])
                + (points[point1][Y_POSITION] - points[point2][Y_POSITION]) * (points[point1][Y_POSITION] - points[point2][Y_POSITION]));
    }

    private double getSumOfDistanceByPoint(int[][] point) {
        double sum = 0;
        for (int i = 1; i < point.length; i++) {
            double distanceByPoint = getDistance(point, i, i - 1);
            sum += distanceByPoint;
        }
        return sum;
    }

    public static void main(String[] args) {
        ThreePointLine threePointLine = new ThreePointLine();

        int[][] input = {{0, 0}, {1, 1}, {2, 2}};
        int[][] input2 = {{0, 0}, {1, 2}, {2, 2}};
        int[][] input3 = {{0, 1}, {1, 1}, {2, 1}};
        System.out.println("{0, 0}, {1, 1}, {2, 2} 입력 시 true 를 리턴한다. : " + threePointLine.isStraightLine(input));
        System.out.println("{0, 0}, {1, 2}, {2, 2} 입력 시 false 를 리턴한다. : " + threePointLine.isStraightLine(input2));
        System.out.println("{0, 1}, {1, 1}, {2, 1} 입력 시 true 를 리턴한다. : " + threePointLine.isStraightLine(input3));
    }
}
