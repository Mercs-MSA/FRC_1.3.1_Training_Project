// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.ExampleThing;

/**
 * This is the main Robot Container class that contains all major robot subsystems
 * 
 */
public class RobotContainer {
  private final CommandXboxController driverJoystick = new CommandXboxController(0); 

  /* Subsystems */
  public static final ExampleThing m_example1 = new ExampleThing(1);
  public static final ExampleThing m_example2 = new ExampleThing(2);
  public static final Arm m_arm = new Arm();
  public static final Intake m_intake = new Intake();

  /**
   * This is the Robot Container class constructor
   * 
   */
  public RobotContainer() {
  }

  /**
   * This is a method for periodically polling controllers. Note that this isn't meant to be used for normal controls using commands, but for testing purposes
   * 
   */
  public void runManualCommands() {
    if (driverJoystick.rightBumper().getAsBoolean()) {
      m_arm.setArmPosition(3.5);
    }
    else if (driverJoystick.leftBumper().getAsBoolean()) {
      m_arm.setArmPosition(0.0);
    }

    if (driverJoystick.button(1).getAsBoolean()) {
      m_intake.setIntakeMotorRunningForward();
    }
    else if (driverJoystick.button(2).getAsBoolean()) {
      m_intake.setIntakeMotorRunningReverse();
    }
    else if (driverJoystick.button(3).getAsBoolean()) {
      m_intake.setIntakeMotorStop();
    }
  }
}