package org.firstinspires.ftc.teamcode.autonomous.testing;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.Hardware22;
import org.firstinspires.ftc.teamcode.autonomous.PathType;
import org.firstinspires.ftc.teamcode.autonomous.enums.Position;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;

import java.util.ArrayList;

public class RedTrajectoryGenerator extends TrajectoryGenerator {
    SampleMecanumDrive drive;
    Position position;
    TrajectoryGenerator generator;
    public RedTrajectoryGenerator(SampleMecanumDrive drive, Position position) {
        super(drive);
        this.position = position;
        this.drive = drive;
    }


    public ArrayList<ArrayList<Trajectory>> generateTrajectories() {
        ArrayList<Double[]> trajectory1 = new ArrayList<>();
        ArrayList<Double[]> trajectory2 = new ArrayList<>();
        ArrayList<Double[]> trajectory3 = new ArrayList<>();

        ArrayList<ArrayList<Trajectory>> finalTrajs = new ArrayList<>();

        if (position == Position.BACK) {
            Vector2d vector = new Vector2d(7.75, -61.5);
            Pose2d startPose = new Pose2d(vector, Math.toRadians(90));

            drive.setPoseEstimate(startPose);

            generateTrajectoryListItem(-7, -42, 105, PathType.LINE_TO_LINEAR, trajectory1);
            // moving to the shipping hub


            ArrayList<Trajectory> compTraj1 = compileTrajectoryList(startPose, trajectory1);

            finalTrajs.add(compTraj1);


        } else if (position == Position.FRONT) {
            Vector2d vector = new Vector2d(-40.75, -61.5);
            Pose2d startPose = new Pose2d(vector, Math.toRadians(90));

            drive.setPoseEstimate(startPose);


            generateTrajectoryListItem(-30, -47, 50, 0, PathType.SPLINE_TO_LINEAR, trajectory1);
            // moving to the shipping hub
            generateTrajectoryListItem(-22, -36.5, PathType.LINE_TO_CONSTANT, trajectory1);
            // getting in position to dump

            generateTrajectoryListItem(-60, -57, 120, PathType.LINE_TO_LINEAR, trajectory2);
            // moving to the duck wheel
            generateTrajectoryListItem(-62, -59, 90, PathType.LINE_TO_LINEAR, trajectory2);
            //nudging up to the duck wheel

            generateTrajectoryListItem(-50, -68.25, 0, PathType.LINE_TO_LINEAR, trajectory3);
            // getting in position to cross the field and park
            generateTrajectoryListItem(50, -71.25, 0, PathType.LINE_TO_LINEAR, trajectory3);
            // hugging the wall and moving into the warehouse


            ArrayList<Trajectory> compTraj1 = compileTrajectoryList(startPose, trajectory1);
            ArrayList<Trajectory> compTraj2 = compileTrajectoryList(compTraj1.get(compTraj1.size() - 1).end(), trajectory2);
            ArrayList<Trajectory> compTraj3 = compileTrajectoryList(compTraj2.get(compTraj2.size() - 1).end(), trajectory3);

            finalTrajs.add(compTraj1);
            finalTrajs.add(compTraj2);
            finalTrajs.add(compTraj3);

        }
        return finalTrajs;
    }
}