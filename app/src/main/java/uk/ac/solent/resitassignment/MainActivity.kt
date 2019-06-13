package uk.ac.solent.resitassignment
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.hardware.SensorManager.SENSOR_DELAY_GAME
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity(), SensorEventListener {

    lateinit var sensorManager: SensorManager
    lateinit var image: ImageView
    lateinit var accel: Sensor
    lateinit var magnetSet: Sensor

    var currentDegree = 0.0f
    var lastAccel = FloatArray(3)
    var lastMagnet = FloatArray(3)
    var lastAccelerometerSet = false
    var lastMagnetometerSet = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image = findViewById(R.id.image1) as ImageView

        val sMgr = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accel = sMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        magnetSet = sMgr.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

        sMgr.registerListener(this, accel, SensorManager.SENSOR_DELAY_UI)
    }
    override fun onResume(){
        super.onResume()

        sensorManager.registerListener(this, accel, SENSOR_DELAY_GAME)
        sensorManager.registerListener(this, magnetSet, SENSOR_DELAY_GAME)
    }
    override fun onPause(){
        super.onPause()
        sensorManager.unregisterListener(this, accel)
        sensorManager.unregisterListener(this, magnetSet)

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {

    }
}
