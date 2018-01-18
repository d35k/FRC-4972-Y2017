
package org.usfirst.frc.team4972.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DigitalInput;
// import edu.wpi.first.wpilibj.Victor;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();

	/**
	 * Robot motorlarının sıraları, pwm'ler değişse de sol arka, sol ön, sağ arka sağ ön sıralaması değişmiyor
	 * PWM 1 - Sol arka
	 * PWM 2 - SOl ön
	 * PWM 3 - Sağ arka
	 * PWM 4 - Sağ ön
	 * We can use this for Joystick.
	 * PWM 0 - Left
	 * PWM 1 - Right
	 */
	
	//Robot Drive FRC'nin hazırladığı kütüphanedeki bir fonksiyon, üstü çizili olaiblir fakat sıkıntı yok, kullanılıyor.
	//Burada Motor sürücü farketmeksizin motorların hangi PWM'e bağlandığını tanıtıyoruz.

	RobotDrive haraketMotorlari = new RobotDrive(1, 2, 3, 4);


	/**
	 * Joystick ise bu şekilde tanımlanıyor, yanında bulunan 0 hangi usb portunda olduğu, hangi portta olduğunu driverstation'da görebiliyoruz. Dikkat edilmeli buna.
	 */
	Joystick joystick = new Joystick(0);

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {

		/**
		 * For Auto Generated Robot Codes
		 */
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		autoSelected = chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			break;
		case defaultAuto:
		default:
			// Put default auto code here
			break;
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		/**
		 * Bu kısım, isOperatorControl, Otonomdan çıkmış, teleop moduna yani bizim kontrolümüze geçmiş anlamına geliyor. 
		 * IsEnabled ise, robot driverstation'dan enabled edilmişse.
		 * While dönmüsüne sokmamızın sebebi, bu iki şart sağlandıkça çalışması gerekiği için.
		 */
		while(isOperatorControl() & isEnabled()){
			/**
			 * arcadeDrive ise, RobotDrive içerisinde bulunan, joystick ile robotun hareket için belirlediğimiz motorlarını kontorl etmemizi sağlayan method. İÇerisine yukarıda tanımladığımız motorları ve joystick'i gönderiyoruz.
			 */
			haraketMotorlari.arcadeDrive(joystick);
			
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}
