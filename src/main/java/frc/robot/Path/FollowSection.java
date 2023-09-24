// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Path;

import java.util.function.Consumer;
import java.util.function.Supplier;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.chassis.Chassis;

public class FollowSection extends CommandBase {
  /** Creates a new FollowSection. */
  PathPoint first;
  PathPoint second;
  TrapezoidMotion trapezoid;
  Subsystem subsystem;
  double maxSpeed;
  double maxAcceleration;
  Consumer<ChassisSpeeds> setSpeeds; 
  Supplier<Pose2d> getPose;
  Supplier<Translation2d> getVelocity;
  public FollowSection(PathPoint first, PathPoint second, double maxSpeed, double maxAcceleration, 
    Subsystem subsystem, Consumer<ChassisSpeeds> setSpeeds, Supplier<Pose2d> getPose, Supplier<Translation2d> getVelocity) {
    this.first = first;
    this.second = second;
    
    this.subsystem = subsystem;
    this.maxSpeed = maxSpeed;
    this.maxAcceleration = maxAcceleration;
    this.setSpeeds = setSpeeds;
    this.getPose = getPose;
    this.getVelocity = getVelocity;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    trapezoid = new TrapezoidMotion(second.pose.getTranslation().minus(first.pose.getTranslation()).getNorm(), 
      second.velocitySize, maxSpeed, maxAcceleration, 
      ()->{return getPose.get().getTranslation().minus(first.pose.getTranslation()).rotateBy(
        Rotation2d.fromDegrees(-first.velociotyTranslation.getAngle().getDegrees())).getNorm();}
      , ()->{return getVelocity.get().getNorm();});
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double spvel = trapezoid.calculate();
    Translation2d spvelTranslation = new Translation2d(spvel, first.velociotyTranslation.getAngle().getDegrees());
    setSpeeds.accept(new ChassisSpeeds(spvelTranslation.getX(), spvelTranslation.getY(), 0));
    System.out.println("SPVE" + spvel);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return trapezoid.isFinished();
  }
}