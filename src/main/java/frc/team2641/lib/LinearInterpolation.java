package frc.team2641.lib;

import java.util.Arrays;

public class LinearInterpolation {
  public static double calculate(double[] x, double[] y, double targetX) throws IllegalArgumentException {
    if (x.length != y.length) {
      throw new IllegalArgumentException("x and y must be of the same length");
    }
    if (x.length == 1) {
      throw new IllegalArgumentException("x must contain more than one value");
    }
    double[] dx = new double[x.length - 1];
    double[] dy = new double[x.length - 1];
    double[] slopes = new double[x.length - 1];
    double[] intercepts = new double[x.length - 1];

    for (int i = 0; i < x.length - 1; i++) {
      dx[i] = x[i + 1] - x[i];
      if (dx[i] == 0) {
        throw new IllegalArgumentException("x must be monotonic, a duplicate x-value was found: " + x[i]);
      }
      if (dx[i] < 0) {
        throw new IllegalArgumentException("x must be sorted");
      }
      dy[i] = y[i + 1] - y[i];
      slopes[i] = dy[i] / dx[i];
      intercepts[i] = y[i] - slopes[i] * x[i];
    }

    double targetY = 0;
    if (targetX > x[x.length - 1]) {
      targetY = y[y.length - 1];
    } else if (targetX < x[0]) {
      targetY = y[0];
    } else {
      int index = Arrays.binarySearch(x, targetX);
      if (index < -1) {
        index = -index - 2;
        targetY = slopes[index] * targetX + intercepts[index];
      } else {
        targetY = y[index];
      }
    }

    return targetY;
  }
}
