package org.firstinspires.ftc.teamcode.UNIVERSAL;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Robot extends OpMode {

    //Below are variables that will be used in multiple codes

    public ElapsedTime runtime = new ElapsedTime();
    public String SpecClawState;
    public double SpecClawOpen;
    public double LinearSlideSpeed;
    public double HypotenuseArmSpeed;
    public double SlideRaised;
    public double HypotenuseExtended;

    //Below is the hardware map

    public Servo SpecimenClaw;
    public DcMotorEx LinearSlide;
    public DcMotorEx HypotenuseArm;
    public DcMotorEx FrontLeft;
    public DcMotorEx FrontRight;
    public DcMotorEx BackLeft;
    public DcMotorEx BackRight;
    public CRServo Spintake;

    public void Initialize(){

        LinearSlide  = hardwareMap.get(DcMotorEx.class, "LinearSlide");
        SpecimenClaw = hardwareMap.get(Servo.class,"SpecimenClaw");
        HypotenuseArm = hardwareMap.get(DcMotorEx.class,"HypotenuseArm");
        Spintake = hardwareMap.get(CRServo.class,"Spintake");
        FrontLeft  = hardwareMap.get(DcMotorEx.class, "FrontLeft");
        BackLeft  = hardwareMap.get(DcMotorEx.class, "BackLeft");
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

        telemetry.addData("Status", "Initialized");

    }

    public void Specimen_Claw_State_Handler() {
        if (SpecimenClaw.getPosition() == 0) {
            SpecClawState = "CLOSED";
        } else {
            SpecClawState = "OPEN";
        }
    }

    public void Telemetry_Outputs() {
        telemetry.addData("Specimen Claw", SpecClawState);
        telemetry.addData("slide pos", LinearSlide.getCurrentPosition());
        telemetry.addData("left trigger", gamepad1.left_trigger);
        telemetry.addData("right trigger", gamepad1.right_trigger);
        telemetry.addData("hypotenuse", HypotenuseArm.getCurrentPosition());
        telemetry.update();
    }

    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }
}
