package org.firstinspires.ftc.teamcode.TELEOP;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.TEST.MecanumTest;
import org.firstinspires.ftc.teamcode.UNIVERSAL.Robot;

/**This file works as a template for any OpMode you may create
 * Make a new java class in the teleop or autonomous package
 * CTRL+A and Copy and paste this code into the new file
 * Fix the package and class by right clicking and show context actions
 * If needed change @TeleOp to @autonomous
 * Change the name to how it should appear in the driver station app
 * Lastly remove @Disabled so this file appears in the driver station app
 * Android studio will automatically import FTC libraries as needed
 * Have fun coding and remember Labels on Cables :D
 * @noinspection ALL*/

@TeleOp(name = "Euler Drive")

public class IntoTheDeepTeleop extends OpMode {

    /**Here is where you declare your variables and OpMode Members*/

    private Robot robot;

    private MecanumTest MecanumTest;
    private double TriggerMinimum = 0.1;

    /** "init" runs once upon hitting the INIT button*/
    @Override
    public void init() {
        robot = new Robot();
        robot.Initialize();
    }

    /** "init_loop" runs repeatedly after hitting INIT until the play button is hit or the OpMode is stopped*/
    @Override
    public void init_loop() {
        Specimen_Claw_Controls();
        robot.Specimen_Claw_State_Handler();
        robot.Telemetry_Outputs();
    }

    /** "start" runs once upon hitting the play button*/
    @Override
    public void start() {
        robot.runtime.reset();
    }

    /** "loop" runs repeatedly after hitting play until the OpMode is stopped*/
    @Override
    public void loop() {
        telemetry.addData("Status", "Run Time: " + robot.runtime);
        //Drive_Controls();
        MecanumTest.MecanumDrive();
        Specimen_Claw_Controls();
        Linear_Slide_Controls();
        Hypotenuse_Arm_Controls();
        Spintake_Controls();
        robot.Specimen_Claw_State_Handler();
        robot.Telemetry_Outputs();
    }

    /** "stop" runs once upon the OpMode stopping with limited functionality*/
    @Override
    public void stop() {

    }

   /* private void Drive_Controls() {
    double LeftMotorCalc;
    double RightMotorCalc;
    if (gamepad1.right_stick_y == 0) {
            LeftMotorCalc = 0;
            RightMotorCalc = 0;
        } else {
            LeftMotorCalc = (gamepad1.right_stick_y / Math.abs(gamepad1.right_stick_y)) * Math.pow(gamepad1.right_stick_y, 2);
            RightMotorCalc = -((gamepad1.right_stick_y / Math.abs(gamepad1.right_stick_y)) * Math.pow(gamepad1.right_stick_y, 2));
        }
        if (!(gamepad1.right_stick_x == 0)) {
            LeftMotorCalc += (-(gamepad1.right_stick_x / Math.abs(gamepad1.right_stick_x)) * Math.pow(gamepad1.right_stick_x, 2));
            RightMotorCalc += (-(gamepad1.right_stick_x / Math.abs(gamepad1.right_stick_x)) * Math.pow(gamepad1.right_stick_x, 2));
        }
        robot.LeftDrive.setPower(LeftMotorCalc);
        robot.RightDrive.setPower(RightMotorCalc);
    } **/

    private void Specimen_Claw_Controls() {
        if (gamepad1.a) {
            if (robot.SpecClawState.equals("CLOSED")) {
                robot.SpecimenClaw.setPosition(robot.SpecClawOpen);
            } else {
                robot.SpecimenClaw.setPosition(0);
            }
        }
    }

    private void Linear_Slide_Controls() {
        if (!(gamepad1.right_bumper || gamepad1.left_bumper)) {
            robot.LinearSlide.setPower(0);
        } else if (gamepad1.right_bumper) {
            robot.LinearSlide.setPower(robot.LinearSlideSpeed);
        } else {
            robot.LinearSlide.setPower(-robot.LinearSlideSpeed);
        }
    }

    private void Hypotenuse_Arm_Controls() {
        if (!(gamepad1.right_trigger > TriggerMinimum || gamepad1.left_trigger > TriggerMinimum)) {
            robot.HypotenuseArm.setPower(0);
        } else if (gamepad1.right_trigger > TriggerMinimum) {
            robot.HypotenuseArm.setPower(robot.HypotenuseArmSpeed);
        } else  {
            robot.HypotenuseArm.setPower(-robot.HypotenuseArmSpeed);
        }
    }

    private void Spintake_Controls() {
        if (!(gamepad1.dpad_up || gamepad1.dpad_down)) {
            robot.Spintake.setPower(0);
        } else if (gamepad1.dpad_up) {
            robot.Spintake.setPower(1);
        } else {
            robot.Spintake.setPower(-1);
        }
    }
}

