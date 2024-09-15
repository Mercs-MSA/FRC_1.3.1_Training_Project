package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ExampleThing extends SubsystemBase{

    // Unique fields get added here, such as motor objects

    /**
     * Constructs an "ExampleThing" object with an input parameter "i"
     * 
     * @param i This is just an example input parameter that is copied into a member variable "ID"
     */
    public ExampleThing(int i) {
        int ID = i;
    }
    /**
     * This is a method template for changing the state of the subsystem
     * 
     */
    public void setState() {
        // Tell the thing to do the thing!
    }
    /**
     * This is a method template for querying the state of the subsystem
     * 
     * @return This is just an example output that is a double
     */
    public double getState() {
        // Ask the thing whats going on!!
        return 0.0;
    }

    /**
     * This is a method template for the periodic function
     * 
     */
    @Override  // This override is important because we are extending a base class with this method!
    public void periodic() {
        // Put all the code you want to run periodically here...
        // IMPORTANT! Once you instantiate this subsystem, periodic will run automatically in the command scheduler
    }
}