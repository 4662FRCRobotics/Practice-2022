// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.List;

import com.fasterxml.jackson.databind.node.BooleanNode;

import org.photonvision.PhotonCamera;
import org.photonvision.common.hardware.VisionLEDMode;
import org.photonvision.targeting.PhotonTrackedTarget;
import org.photonvision.targeting.TargetCorner;

import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Vision extends SubsystemBase {
  /** Creates a new Vision. */
  PhotonCamera camera = new PhotonCamera("fwdCamera");
  double m_dyaw;
  double m_dpitch;
  double m_darea;
  double m_dskew;
  boolean m_bhaveTarget;


  public Vision() {
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
SmartDashboard.putBoolean("targetfound", m_bhaveTarget);
SmartDashboard.putNumber("targetyaw", m_dyaw);
  }

  public void enableHubTargeting() {
    camera.setDriverMode(false);
    camera.setPipelineIndex(0);
    camera.setLED(VisionLEDMode.kOn);
    m_bhaveTarget = false;
  }

  public void disableHubTargeting() {
    camera.setDriverMode(true);
    camera.setLED(VisionLEDMode.kOff);
  }

  public void getTarget() {
    var result = camera.getLatestResult();
    boolean hasTargets = result.hasTargets();
    if (hasTargets) {
      PhotonTrackedTarget target = result.getBestTarget();
      // double latencySeconds = result.getLatencyMillis() / 1000.0;
      m_dyaw = target.getYaw();
      m_dpitch = target.getPitch();
      m_darea = target.getArea();
      m_dskew = target.getSkew();
      Transform2d pose = target.getCameraToTarget();
      List<TargetCorner> corners = target.getCorners();
      m_bhaveTarget = true;
    }

    
  }

  public boolean haveTarget() {
    return m_bhaveTarget;
  }

}
