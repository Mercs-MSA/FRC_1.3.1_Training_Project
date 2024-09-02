// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.ExampleThing;

public class RobotContainer {
  private final CommandXboxController driverJoystick = new CommandXboxController(0); 

  /* Subsystems */
  public static final ExampleThing m_example1 = new ExampleThing(1);
  public static final ExampleThing m_example2 = new ExampleThing(2);
  public static final Arm m_arm = new Arm();

  public RobotContainer() {
  }

  public void runManualCommands() {
    if (driverJoystick.rightBumper().getAsBoolean()) {
      m_arm.setArmPosition(3.5); 
    }
    else if (driverJoystick.leftBumper().getAsBoolean()) {
      m_arm.setArmPosition(0.0);
    }
  }
}