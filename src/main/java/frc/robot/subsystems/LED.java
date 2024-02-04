// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LED extends SubsystemBase {
	private final SerialPort serial;

	public boolean holdingNote;
	public boolean readyToShoot;

	public LED(SerialPort.Port port) {
		serial = new SerialPort(9600, port);

		serial.setWriteBufferMode(SerialPort.WriteBufferMode.kFlushOnAccess);
	}

	@Override
	public void periodic() {
		// Set LED animations
		if (!DriverStation.isDSAttached()) {
			setDisconnectedAnimation();
		} else if (DriverStation.isEStopped()) {
			setEStopAnimation();
		} else if (readyToShoot) {
			setReadyToShootAnimation();
		} else if (holdingNote) {
			setHoldingNoteAnimation();
		} else if (DriverStation.isAutonomous() && DriverStation.isDisabled()) {
			setAutoDisabledAnimation();
		} else if (DriverStation.isAutonomous() && DriverStation.isEnabled()) {
			setAutoEnabledAnimation();
		} else if (DriverStation.isTeleop() && DriverStation.isDisabled()) {
			setTeleopDisabledAnimation();
		} else if (DriverStation.isTeleop() && DriverStation.isEnabled()) {
			setTeleopEnabledAnimation();
		}
	}

	public void setDisconnectedAnimation() {
		serial.writeString("dp\n");
	}
	
	public void setEStopAnimation() {
		serial.writeString("es\n");
	}
	
	public void setAutoDisabledAnimation() {
		serial.writeString("ad\n");
	}

	public void setAutoEnabledAnimation() {
		serial.writeString("ae\n");
	}

	public void setTeleopDisabledAnimation() {
		serial.writeString("td\n");
	}

	public void setTeleopEnabledAnimation() {
		serial.writeString("te\n");
	}

	public void setHoldingNoteAnimation() {
		serial.writeString("hn\n");
	}

	public void setReadyToShootAnimation() {
		serial.writeString("rs\n");
	}
}
