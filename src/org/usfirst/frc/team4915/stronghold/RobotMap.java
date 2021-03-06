package org.usfirst.frc.team4915.stronghold;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class RobotMap {

    // Define channels for the drive train motors
    public static final int driveTrainRightBackMotor = 13;
    public static final int driveTrainRightFrontMotor = 12;

    public static final int driveTrainLeftBackMotor = 11;
    public static final int driveTrainLeftFrontMotor = 10;

    public static CANTalon leftBackMotor;
    public static CANTalon rightBackMotor;
    public static CANTalon leftFrontMotor;
    public static CANTalon rightFrontMotor;

    // Solenoid for two speed gear system for the drive train
    public static final int SOLENOID_CHANNEL_PRIMARY = 0;
    public static final int SOLENOID_CHANNEL_SECONDARY = 1;
    public static DoubleSolenoid doubleSolenoid;

    /* Gyro specific constants - Initialization takes place in RobotMapInit() */
    public final static int GYRO_PORT = 0;
    public static Gyro gyro;

    public static CANTalon intakeLeftMotor;
    public static CANTalon intakeRightMotor;
    public static CANTalon aimMotor;
    public static Servo launcherServo;

    /*
     * IntakeLauncher specific constants - Initialization takes place in
     * RobotMapInit()
     */
    /* FIXME: Initialize IntakeLauncher's ports */

    private static final int INTAKE_LEFT_MOTOR_ID = 14;
    private static final int INTAKE_RIGHT_MOTOR_ID = 15;
    private static final int AIM_MOTOR_ID = 16;

    private static final int BOULDER_SWITCH_PORT = 0;
    private static final int LAUNCHER_BOTTOM_SWITCH_PORT = 1;
    private static final int LAUNCHER_TOP_SWITCH_PORT = 2;

    private static final int LAUNCHER_SERVO_PORT = 17;
    // not actual port values

    private static final double AIM_MOTOR_FORWARD_SOFT_LIMIT = 1.0;
    private static final double AIM_MOTOR_REVERSE_SOFT_LIMIT = 0.0;
    private static final double AIM_MOTOR_P = 0; // TODO
    private static final double AIM_MOTOR_I = 0; // TODO
    private static final double AIM_MOTOR_D = 0; // TODO

    public static DigitalInput boulderSwitch;
    public static DigitalInput launcherTopSwitch;
    public static DigitalInput launcherBottomSwitch;

    /*
     * Initialize the various robot modules
     */
    public static void init() {
        // conditionally initialize the modules
        if (ModuleManager.DRIVE_MODULE_ON) {
            leftBackMotor = new CANTalon(driveTrainLeftBackMotor);
            rightBackMotor = new CANTalon(driveTrainRightBackMotor);
            leftFrontMotor = new CANTalon(driveTrainLeftFrontMotor);
            rightFrontMotor = new CANTalon(driveTrainRightFrontMotor);
            // TODO: Invert motors here if needed: someMotor.setInverted(true)
            doubleSolenoid = new DoubleSolenoid(SOLENOID_CHANNEL_PRIMARY, SOLENOID_CHANNEL_SECONDARY);
            /*
             * TODO: Initialize the Talon drive motors 1. establish follower
             * mode: we have 4 motor controls, but need to give commands to two
             * of them 2. establish speed control mode 3. on motors w/ encoders
             * set feedbackdevice to quadEncoder 4. optional: if driving jerky,
             * set PID values
             */

            //THe back motors are the follower motors
            //follower mode for right side
            rightBackMotor.changeControlMode(CANTalon.TalonControlMode.Follower);
            rightBackMotor.set(rightFrontMotor.getDeviceID());
            //follower mode for left side

            leftBackMotor.changeControlMode(CANTalon.TalonControlMode.Follower);
            leftBackMotor.set(leftFrontMotor.getDeviceID());

            System.out.println("ModuleManager RobotMap Initialize: DriveTrain Nothing to initalize... Moving on!");
        }

        if (ModuleManager.INTAKELAUNCHER_MODULE_ON) {
            intakeLeftMotor = new CANTalon(INTAKE_LEFT_MOTOR_ID);
            intakeRightMotor = new CANTalon(INTAKE_RIGHT_MOTOR_ID);
            aimMotor = new CANTalon(AIM_MOTOR_ID);
            launcherServo = new Servo(LAUNCHER_SERVO_PORT);
            boulderSwitch = new DigitalInput(BOULDER_SWITCH_PORT);
            launcherTopSwitch = new DigitalInput(LAUNCHER_TOP_SWITCH_PORT);
            launcherBottomSwitch = new DigitalInput(LAUNCHER_BOTTOM_SWITCH_PORT);
            System.out.println("ModuleManager RobotMap initialized: IntakeLauncher");

            // setup the motor
            aimMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
            aimMotor.setForwardSoftLimit(AIM_MOTOR_FORWARD_SOFT_LIMIT);
            aimMotor.setReverseSoftLimit(AIM_MOTOR_REVERSE_SOFT_LIMIT);
            aimMotor.enableForwardSoftLimit(true);
            aimMotor.enableReverseSoftLimit(true);
            aimMotor.ConfigFwdLimitSwitchNormallyOpen(true);
            aimMotor.ConfigRevLimitSwitchNormallyOpen(true);
        }

        if (ModuleManager.GYRO_MODULE_ON) {
            System.out.println("ModuleManager RobotMap initalize TODO: TODO Initialize Gyro!"); // gyro
                                                                                                // instantiation
            gyro = new AnalogGyro(GYRO_PORT);
        }
    }
}
