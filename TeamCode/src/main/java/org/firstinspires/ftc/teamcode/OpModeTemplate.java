package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

/**This file works as a template for any OpMode you may create
 * Make a new java class in the teleop or autonomous package
 * CTRL+A and Copy and paste this code into the new file
 * Fix the package and class by right clicking and show context actions
 * If needed change @TeleOp to @autonomous
 * Change the name to how it should appear in the driver station app
 * Lastly remove @Disabled so this file appears in the driver station app
 * Android studio will automatically import FTC libraries as needed
 * Have fun coding and remember Labels on Cables :D */

@TeleOp(name = "CHANGE ME")
@Disabled
public class OpModeTemplate extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();

    /** "init" runs once upon hitting the INIT button*/
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
    }

    /** "init_loop" runs repeatedly after hitting INIT until the play button is hit or the OpMode is stopped*/
    @Override
    public void init_loop() {

    }

    /** "start" runs once upon hitting the play button*/
    @Override
    public void start() {
        runtime.reset();
    }

    /** "loop" runs repeatedly after hitting play until the OpMode is stopped*/
    @Override
    public void loop() {
        telemetry.addData("Status", "Run Time: " + runtime.toString());
    }

    /** "stop" runs once upon the OpMode stopping with limited functionality*/
    @Override
    public void stop() {

    }
}