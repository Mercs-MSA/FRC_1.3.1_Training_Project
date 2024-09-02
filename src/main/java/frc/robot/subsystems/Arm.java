package frc.robot.subsystems;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.NeutralOut;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;

public class Arm extends SubsystemBase{
    // Hardware
    private final TalonFX armMotor;
    // Status Signals
    private final StatusSignal<Double> internalPositionRotations;
    private final StatusSignal<Double> velocityRps;
    private final StatusSignal<Double> appliedVoltage;
    private final StatusSignal<Double> supplyCurrent;
    private final StatusSignal<Double> tempCelsius;
    // Note that the next line of code assumes "Pro" version of TalonFX firmware
    private final PositionVoltage armMotorVoltagePosition = new PositionVoltage(0, 0, true, 0, 0, false, false, false);
    // Config
    private final TalonFXConfiguration configArmMotor = new TalonFXConfiguration();
    public static Boolean statusOK = false;

    public Arm(){
        armMotor = new TalonFX(ArmConstants.motorID, "rio");
        CANcoderConfiguration armEncoderConfig = new CANcoderConfiguration();
        armEncoderConfig.MagnetSensor.SensorDirection = SensorDirectionValue.Clockwise_Positive;
        configArmMotor.Slot0.kP = ArmConstants.leaderKP;
        configArmMotor.Slot0.kI = ArmConstants.leaderKI;
        configArmMotor.Slot0.kD = ArmConstants.leaderKD;
        configArmMotor.Voltage.PeakForwardVoltage = ArmConstants.fwdVolt;
        configArmMotor.Voltage.PeakReverseVoltage  = ArmConstants.revVolt;;
        configArmMotor.MotorOutput.Inverted =
        ArmConstants.motorInverted
            ? InvertedValue.Clockwise_Positive
            : InvertedValue.CounterClockwise_Positive;
        configArmMotor.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        configArmMotor.Feedback.RotorToSensorRatio = ArmConstants.rotorToSensorRatio;
        configArmMotor.Feedback.SensorToMechanismRatio = ArmConstants.sensorToMechanismRatio;
        internalPositionRotations = armMotor.getPosition();
        velocityRps = armMotor.getVelocity();
        appliedVoltage = armMotor.getMotorVoltage();
        supplyCurrent = armMotor.getSupplyCurrent();
        tempCelsius = armMotor.getDeviceTemp();
        BaseStatusSignal.setUpdateFrequencyForAll(
            100,
            internalPositionRotations,
            velocityRps,
            appliedVoltage,
            supplyCurrent,
            tempCelsius);
        StatusCode status = StatusCode.StatusCodeNotInitialized;
        for (int i = 0; i < 20; ++i) {
            status = armMotor.getConfigurator().apply(configArmMotor);
            if (status.isOK())
                statusOK = true;
                SmartDashboard.putBoolean("Arm Status", statusOK);
                break;
        }
        if (!status.isOK()) {
            System.out.println("Could not apply Arm configs, error code: " + status.toString());
        }
        setArmAbsolutePosition(0.0);  // we assume that the starting position of the mechanism is at the "Zero" location      
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Arm Motor Temperature", armMotor.getDeviceTemp().getValueAsDouble());
        SmartDashboard.putNumber("Arm Motor Position", armMotor.getPosition().getValueAsDouble());
    }

    /**
     * Returns the Arm's current position
     *
     * @return Arm's current position in units of Rotations
     */
    public double getArmPosition(){
        return armMotor.getPosition().getValueAsDouble();
    }

    /**
     * Sets the Arm's commanded position
     * 
     * @param pos Arm commanded position to go to in units of Rotations
     */
    public void setArmPosition(double pos) {
        armMotor.setControl(armMotorVoltagePosition.withPosition(pos));
    }

    /**
     * Sets the Arm's absolute position at current location
     * 
     * @param pos Arm absolute position to set at the current physical location as double in units of Rotations
     */
    private void setArmAbsolutePosition(double pos) {
        armMotor.setPosition(pos);
    }

    /**
     * Sets the Arm's braking mode
     * 
     * @param enabled Brake Mode to change to as Boolean (true = Brake, false = Coast)
     */
    public void setBrakeMode(boolean enabled){
        armMotor.setNeutralMode(enabled ? NeutralModeValue.Brake : NeutralModeValue.Coast);
    }

    /**
     * Sets the Arm's control state to complete deactivation
     */
    public void setArmModeToStop(){
        armMotor.setControl(new NeutralOut());
    }
}