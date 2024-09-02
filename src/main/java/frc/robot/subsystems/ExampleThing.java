package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ExampleThing extends SubsystemBase{

    // Unique fields get added here, such as motor objects

    public ExampleThing(int i) {
        // Construction stuff goes here...
        int ID = i;
    }
    public void setState() {
        // Tell the thing to do the thing!
    }
    public double getState() {
        // Ask the thing whats going on!!
        return 0;
    }

    @Override  // This override is important because we are extending a base class with this method!
    public void periodic() {
        // Put all the code you want to run periodically here...
        // IMPORTANT! Once you instantiate this subsystem, periodic will run automatically in the command scheduler
    }
}