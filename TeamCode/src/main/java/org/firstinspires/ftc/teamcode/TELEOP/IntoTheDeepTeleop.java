package org.firstinspires.ftc.teamcode.TELEOP;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
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

@TeleOp(name = "Euler Drive")

public class IntoTheDeepTeleop extends OpMode {

    /**Here is where you declare your variables and OpMode Members*/
    private ElapsedTime runtime = new ElapsedTime();
    private Servo SpecimenClaw;
    private DcMotor LeftDrive;
    private DcMotor RightDrive;
    private DcMotor LinearSlide;

    String Spec_Claw_State;
    double Spec_Claw_Open;

    /** "init" runs once upon hitting the INIT button*/
    @Override
    public void init() {
        LeftDrive  = hardwareMap.get(DcMotor.class, "LeftDrive");
        RightDrive  = hardwareMap.get(DcMotor.class, "RightDrive");
        LinearSlide  = hardwareMap.get(DcMotor.class, "LinearSlide");
        SpecimenClaw = hardwareMap.get(Servo.class,"SpecimenClaw" );

        SpecimenClaw.setDirection(Servo.Direction.FORWARD);
        Spec_Claw_Open = 0.6;
        Spec_Claw_State = "CLOSED";
        SpecimenClaw.setPosition(0);
        LeftDrive.setDirection(DcMotor.Direction.FORWARD);
        RightDrive.setDirection(DcMotor.Direction.FORWARD);
        LinearSlide.setDirection(DcMotor.Direction.FORWARD);
        LeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LinearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LinearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
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
        Drive_Controls();
        Specimen_Claw_Controls();
        Linear_Slide_Controls();
        Telemetry_Outputs();
    }

    /** "stop" runs once upon the OpMode stopping with limited functionality*/
    @Override
    public void stop() {

    }

    private void Telemetry_Outputs() {
        telemetry.addData("Specimen Claw", Spec_Claw_State);
        telemetry.addData("slide pos", LinearSlide.getCurrentPosition());
        telemetry.update();
    }

    private void Drive_Controls() {
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
            LeftMotorCalc += -((gamepad1.right_stick_x / Math.abs(gamepad1.right_stick_x)) * Math.pow(gamepad1.right_stick_x, 2));
            RightMotorCalc += -((gamepad1.right_stick_x / Math.abs(gamepad1.right_stick_x)) * Math.pow(gamepad1.right_stick_x, 2));
        }
        LeftDrive.setPower(LeftMotorCalc);
        RightDrive.setPower(RightMotorCalc);
    }

    private void Specimen_Claw_Controls() {
        if (gamepad1.a) {
            Spec_Claw_State = "OPEN";
            SpecimenClaw.setPosition(Spec_Claw_Open);
        }
        if (gamepad1.b) {
            Spec_Claw_State = "CLOSED";
            SpecimenClaw.setPosition(0);
        }
    }

    private void Linear_Slide_Controls() {
        if (!(gamepad1.dpad_up || gamepad1.dpad_down)) {
            LinearSlide.setPower(0);
        } else if (gamepad1.dpad_up) {
            LinearSlide.setPower(0.1);
        } else {
            LinearSlide.setPower(-0.1);
        }
    }

}