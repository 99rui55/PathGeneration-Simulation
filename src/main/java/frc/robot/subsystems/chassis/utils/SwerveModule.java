// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
package frc.robot.subsystems.chassis.utils;

// import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.DemandType;
// import com.ctre.phoenix.motorcontrol.NeutralMode;
// import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
// import com.ctre.phoenix.motorcontrol.can.TalonFX;
// import com.ctre.phoenix.sensors.CANCoder;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import frc.robot.subsystems.chassis.ChassisConstants;
import frc.robot.subsystems.chassis.ChassisConstants.SwerveModuleConstants;
import frc.robot.subsystems.chassis.utils.simulation.Gyro;
import frc.robot.subsystems.chassis.utils.simulation.SimulateMotor;
import frc.robot.utilities.UtilsGeneral;

/**
 * A swerve module
 */
public class SwerveModule implements Sendable {
    private double desiredVelocity, desiredAngle;
    private final SimulateMotor moveMotor, angleMotor;
    private final Gyro absoluteEncoder;

    /**
     * Creates a new SwerveModule
     * 
     * @param constants The constants for the module
     */
    public SwerveModule(SwerveModuleConstants constants) {
        moveMotor = new SimulateMotor();
        angleMotor = new SimulateMotor();
        absoluteEncoder = new Gyro();

        desiredAngle = 0;
        desiredVelocity = 0;
        configureDevices();
    }

    /**
     * Configures the devices to their default values
     */
    private void configureDevices() {
        // moveMotor.configFactoryDefault();
        // angleMotor.configFactoryDefault();
        // absoluteEncoder.configFactoryDefault();

        // moveMotor.config_kP(0, SwerveModuleConstants.VELOCITY_KP);
        // moveMotor.config_kI(0, SwerveModuleConstants.VELOCITY_KI);

        // angleMotor.config_kP(0, SwerveModuleConstants.ANGLE_KP);
        // angleMotor.config_kI(0, SwerveModuleConstants.ANGLE_KI);
        // angleMotor.config_kD(0, SwerveModuleConstants.ANGLE_KD);
        // angleMotor.config_kF(0, SwerveModuleConstants.ANGLE_KF);
        // angleMotor.configMaxIntegralAccumulator(0,
        // SwerveModuleConstants.MAX_ACCUM_INTEGRAL);

        // SupplyCurrentLimitConfiguration currentLimit = new
        // SupplyCurrentLimitConfiguration();
        // currentLimit.currentLimit = SwerveModuleConstants.MAX_CURRENT_ANGLE;
        // currentLimit.enable = true;

        // angleMotor.configSupplyCurrentLimit(currentLimit);

        // angleMotor.configMotionCruiseVelocity(SwerveModuleConstants.MAX_VELOCITY_ANGLE);
        // angleMotor.configMotionAcceleration(SwerveModuleConstants.MAX_ACCELERATION_ANGLE);
        // angleMotor.configMotionSCurveStrength(SwerveModuleConstants.S_CURVE_STRENGTH_ANGLE);

        // angleMotor.setNeutralMode(NeutralMode.Brake);
        // moveMotor.setNeutralMode(NeutralMode.Brake);
    }

    /**
     * Gets the angle of the module, accounting for the offset
     * 
     * @return The angle of the module, between 0 and 360 degrees
     */
    public double getAngle() {

        return angleMotor.getSelectedSensorPosition() / ChassisConstants.SwerveModuleConstants.PULSE_PER_DEGREE;
    }

    public void setPower(double power) {
    }

    /**
     * Gets the angle of the module as a Rotation2d
     * 
     * @return The angle of the module as a Rotation2d
     */
    public Rotation2d getAngleRotation() {
        return Rotation2d.fromDegrees(getAngle());
    }

    /**
     * Gets the velocity of the module
     * 
     * @return The velocity of the module, in meters per second
     */
    public double getVelocity() {
        return moveMotor.getSelectedSensorVelocity() / SwerveModuleConstants.PULSE_PER_METER * 10;
    }

    /**
     * Sets the velocity of the module
     * 
     * @param velocity The velocity to set the module to, in meters per second
     */
    public void setVelocity(double velocity) {
        desiredVelocity = velocity;
        moveMotor.setVelocity(velocity);
    }

    /**
     * Calculates the target angle for the module
     * 
     * @param targetAngle The target angle, in degrees
     * @return The target angle, in encoder pulses
     */
    private double calculateTarget(double targetAngle) {
        double difference = UtilsGeneral.getAngleDifference(getAngle(), targetAngle);
        return angleMotor.getSelectedSensorPosition() + (difference * SwerveModuleConstants.PULSE_PER_DEGREE);
    }

    /**
     * Sets the angle of the module
     * 
     * @param angle The angle to set the module to, in degrees
     */
    public void setAngle(double angle) {
        desiredAngle = angle;
        angleMotor.setAngle(calculateTarget(angle));
    }

    /**
     * Stops the angle motor
     */
    public void stopAngleMotor() {
        // angleMotor.set(ControlMode.PercentOutput, 0);
        angleMotor.setVelocity(0);
    }

    /**
     * Stops the move motor
     */
    public void stopMoveMotor() {
        // moveMotor.set(ControlMode.PercentOutput, 0);
        moveMotor.setVelocity(0);
    }

    /**
     * Stops the module
     */
    public void stop() {
        stopAngleMotor();
        stopMoveMotor();
    }

    /**
     * Gets the state of the module
     * 
     * @return The state of the module
     */
    public SwerveModuleState getState() {
        return new SwerveModuleState(getVelocity(), getAngleRotation());
    }

    /**
     * Sets the state of the module
     * 
     * @param state The state to set the module to
     */
    public void setState(SwerveModuleState state) {
        setVelocity(state.speedMetersPerSecond);
        setAngle(state.angle.getDegrees());
    }

    /**
     * Sets the power of the velocity motor
     * 
     * @param power The power to set the velocity motor to
     */
    public void setVelocityPower(double power) {
        // moveMotor.set(ControlMode.PercentOutput, power);
    }

    /**
     * Sets the neutral mode of the module
     * 
     * @param isBreak Whether the module should be in brake mode or in coast mode
     */
    public void setNeutralMode(boolean isBreak) {
        // angleMotor.setNeutralMode(isBreak ? NeutralMode.Brake : NeutralMode.Coast);
        // moveMotor.setNeutralMode(isBreak ? NeutralMode.Brake : NeutralMode.Coast);
    }

    /**
     * Sets the offset of the module to the current angle
     */
    public void calibrateOffset() {
    }

    /**
     * Gets the distance the module has traveled, in meters
     * 
     * @return The distance the module has traveled, in meters
     */
    public double getDistance() {
        return moveMotor.getSelectedSensorPosition() / SwerveModuleConstants.PULSE_PER_METER;
    }

    /**
     * Gets the position of the module
     * 
     * @return The position of the module
     */
    public SwerveModulePosition getPosition() {
        return new SwerveModulePosition(getDistance(), getAngleRotation());
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        UtilsGeneral.addDoubleProperty(builder, "Angle", this::getAngle, 2);
        builder.addDoubleProperty("Velocity", this::getVelocity, null);

        builder.addDoubleProperty("Desired Velocity", () -> desiredVelocity, null);
        builder.addDoubleProperty("Desired Angle", () -> desiredAngle, null);
    }

}
