package org.firstinspires.ftc.teamcode.TELEOP;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.TEST.MecanumTest;

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

    private MecanumTest MecanumTest;
    private double TriggerMinimum = 0.1;

    public ElapsedTime runtime = new ElapsedTime();
    public String SpecClawState;
    public double SpecClawOpen;
    public double LinearSlideSpeed;
    public double HypotenuseArmSpeed;
    public double SlideRaised;
    public double HypotenuseExtended;

    public Servo SpecimenClaw;
    public DcMotorEx LinearSlide;
    public DcMotorEx HypotenuseArm;
    public DcMotorEx FrontLeft;
    public DcMotorEx FrontRight;
    public DcMotorEx BackLeft;
    public DcMotorEx BackRight;
    public CRServo Spintake;




    /** "init" runs once upon hitting the INIT button*/
    @Override
    public void init() {
        LinearSlide = hardwareMap.get(DcMotorEx.class, "LinearSlide");
        SpecimenClaw = hardwareMap.get(Servo.class, "SpecimenClaw");
        HypotenuseArm = hardwareMap.get(DcMotorEx.class, "HypotenuseArm");
        Spintake = hardwareMap.get(CRServo.class, "Spintake");
        FrontLeft = hardwareMap.get(DcMotorEx.class, "FrontLeft");
        BackLeft = hardwareMap.get(DcMotorEx.class, "BackLeft");
        FrontRight = hardwareMap.get(DcMotorEx.class, "FrontRight");
        BackRight = hardwareMap.get(DcMotorEx.class, "BackRight");

        SpecClawOpen = 0.6;
        SpecimenClaw.setPosition(0);
        SpecClawState = "CLOSED";
        LinearSlideSpeed = 0.1;
        HypotenuseArmSpeed = 1;

        FrontLeft.setDirection(DcMotorEx.Direction.FORWARD);
        FrontRight.setDirection(DcMotorEx.Direction.FORWARD);
        BackLeft.setDirection(DcMotorEx.Direction.FORWARD);
        BackRight.setDirection(DcMotorEx.Direction.FORWARD);
        LinearSlide.setDirection(DcMotorEx.Direction.FORWARD);
        HypotenuseArm.setDirection(DcMotorEx.Direction.FORWARD);
        SpecimenClaw.setDirection(Servo.Direction.FORWARD);
        Spintake.setDirection(CRServo.Direction.FORWARD);

        FrontLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        FrontRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        BackLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        BackRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        LinearSlide.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        HypotenuseArm.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        FrontLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        FrontRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        BackLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        BackRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        LinearSlide.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        HypotenuseArm.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }

    /** "init_loop" runs repeatedly after hitting INIT until the play button is hit or the OpMode is stopped*/
    @Override
    public void init_loop() {
        Specimen_Claw_Controls();
        Specimen_Claw_State_Handler();
    }

    /** "start" runs once upon hitting the play button*/
    @Override
    public void start() {
        runtime.reset();
    }

    /** "loop" runs repeatedly after hitting play until the OpMode is stopped*/
    @Override
    public void loop() {
        telemetry.addData("Status", "Run Time: " + runtime);
        //Drive_Controls();
        MecanumDrive();
        Specimen_Claw_Controls();
        Linear_Slide_Controls();
        Hypotenuse_Arm_Controls();
        Spintake_Controls();
        Specimen_Claw_State_Handler();
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
            if (SpecClawState.equals("CLOSED")) {
                SpecimenClaw.setPosition(SpecClawOpen);
            } else {
                SpecimenClaw.setPosition(0);
            }
        }
    }

    private void Linear_Slide_Controls() {
        if (!(gamepad1.right_bumper || gamepad1.left_bumper)) {
            LinearSlide.setPower(0);
        } else if (gamepad1.right_bumper) {
            LinearSlide.setPower(LinearSlideSpeed);
        } else {
            LinearSlide.setPower(-LinearSlideSpeed);
        }
    }

    private void Hypotenuse_Arm_Controls() {
        if (!(gamepad1.right_trigger > TriggerMinimum || gamepad1.left_trigger > TriggerMinimum)) {
            HypotenuseArm.setPower(0);
        } else if (gamepad1.right_trigger > TriggerMinimum) {
            HypotenuseArm.setPower(HypotenuseArmSpeed);
        } else  {
            HypotenuseArm.setPower(-HypotenuseArmSpeed);
        }
    }

    private void Spintake_Controls() {
        if (!(gamepad1.dpad_up || gamepad1.dpad_down)) {
            Spintake.setPower(0);
        } else if (gamepad1.dpad_up) {
            Spintake.setPower(1);
        } else {
            Spintake.setPower(-1);
        }
    }

    public void Specimen_Claw_State_Handler() {
        if (SpecimenClaw.getPosition() == 0) {
            SpecClawState = "CLOSED";
        } else {
            SpecClawState = "OPEN";
        }
    }

    public void MecanumDrive() {

        double max;

        // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
        double axial   = -gamepad1.left_stick_y;  // Note: pushing stick forward gives negative value
        double lateral =  gamepad1.left_stick_x;
        double yaw     =  gamepad1.right_stick_x;

        // Combine the joystick requests for each axis-motion to determine each wheel's power.
        // Set up a variable for each drive wheel to save the power level for telemetry.
        double leftFrontPower  = axial + lateral + yaw;
        double rightFrontPower = axial - lateral - yaw;
        double leftBackPower   = axial - lateral + yaw;
        double rightBackPower  = axial + lateral - yaw;

        // Normalize the values so no wheel power exceeds 100%
        // This ensures that the robot maintains the desired motion.
        max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0) {
            leftFrontPower  /= max;
            rightFrontPower /= max;
            leftBackPower   /= max;
            rightBackPower  /= max;
        }

        // Send calculated power to wheels
        FrontLeft.setPower(leftFrontPower);
        FrontRight.setPower(rightFrontPower);
        BackLeft.setPower(leftBackPower);
        BackRight.setPower(rightBackPower);
    }
}

