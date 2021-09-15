package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="Drive Forward", group="Exercises")
public class DriveForward extends LinearOpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;

    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.get(DcMotor.class, "left_motor");
        rightMotor = hardwareMap.get(DcMotor.class, "right_motor");

        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Mode", "Initialized");
        telemetry.update();

        waitForStart();

        telemetry.addData("Mode", "Running");
        telemetry.update();

        leftMotor.setPower(0.25);
        rightMotor.setPower(0.25);

        sleep(2000);

        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
}
