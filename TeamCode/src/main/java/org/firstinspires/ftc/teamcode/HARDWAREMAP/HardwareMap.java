package org.firstinspires.ftc.teamcode.HARDWAREMAP;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class HardwareMap {

    private Servo SpecimenClaw;
    private DcMotor LeftDrive;
    private DcMotor RightDrive;
    private DcMotor LinearSlide;

    public void init(HardwareMap hwMap) {
        LeftDrive = hwMap.get(DcMotor.class, "motor");

    }

}
