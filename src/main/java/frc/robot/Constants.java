package frc.robot;

public final class Constants {
    public static class ArmConstants{
        public static int motorID = 20;
        public static boolean motorInverted = true;

        /* Leader Motor PID Values */
        public static final double leaderKP = 1.5;
        public static final double leaderKI = 0.0;
        public static final double leaderKD = 0.0;
        public static final double leaderKF = 0.0;

        public static double fwdVolt = 14;
        public static double revVolt = -14;
        public static int rotorToSensorRatio = 1;
        public static int sensorToMechanismRatio = 1;
    }
}