package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Head;

public class MechanismCommands {
	public static Command ShootAmp(Arm arm, Head head) {
		return new InstantCommand(() -> arm.setArmTarget(ArmConstants.AMP_ANGLE))
				.andThen(new InstantCommand(() -> arm.setElevatorTarget(ElevatorConstants.MAX_HEIGHT)))
				.andThen(head.SpinUpShooterForAmp());
	}
	
	/**
	 * will wait until finished shooting to finish command
	 * @param arm
	 * @param head
	 * @param drivetrain subsystem
	 * @return
	 */
	public static Command ShootSpeaker(Arm arm, Head head, Drivetrain drivetrain) {
		double distance = drivetrain.getDistanceToSpeaker();
		return ShootSpeaker(arm, head, distance);
	}

	/**
	 * will wait until finished shooting to finish command
	 * @param arm
	 * @param head
	 * @param distance in meters
	 * @return
	 */
	public static Command ShootSpeaker(Arm arm, Head head, double distance){
		return Commands.runOnce(() -> arm.setArmTargetByDistance(distance))
				.andThen(head.ShootAtPosition(distance));
	}
	
	/** will finish after piece has been shot
	 * 
	 * @param arm subsystem
	 * @param head subsystem
	 * @return Command
	 */
	public static Command ShootSpeakerSubwoofer(Arm arm, Head head) {
		return Commands.runOnce(() -> arm.setArmTargetByDistance(1.27))
				.andThen(() -> arm.setElevatorTarget(ElevatorConstants.MAX_HEIGHT))
				.andThen(head.SpinUpShooterAtPosition(0))
				.andThen(arm.WaitUntilArmAtTarget())
				.andThen(arm.WaitUntilElevatorAtTarget())
				.andThen(head.ShootAtPosition(0));
	}
	
	/** will finish after piece has been shot
	 * 
	 * @param arm subsystem
	 * @param head subsystem
	 * @return Command
	 */
	public static Command ShootSpeakerPodium(Arm arm, Head head) {
		return Commands.runOnce(() -> arm.setArmTargetByDistance(Constants.PODIUM_DISTANCE)) // todo where should elevator be?
				.andThen(head.ShootAtPosition(Constants.PODIUM_DISTANCE));
	}
	
	/** will finish after piece has been intaken
	 * 
	 * @param arm subsystem
	 * @param head subsystem
	 * @return Command
	 */
	public static Command IntakeSource(Arm arm, Head head) {
		return Commands.runOnce(() -> arm.setArmTarget(ArmConstants.SOURCE_ANGLE))
				.andThen(() -> arm.setElevatorTarget(ElevatorConstants.MAX_HEIGHT))
				.andThen(head.IntakePiece());
	}
	
	
	public static Command IntakeGround(Arm arm, Head head) {
		return Commands.runOnce(() -> arm.setArmTarget(5.))
				.andThen(() -> arm.setElevatorTarget(ElevatorConstants.MAX_HEIGHT))
				.andThen(head.IntakePiece());
	}
}
