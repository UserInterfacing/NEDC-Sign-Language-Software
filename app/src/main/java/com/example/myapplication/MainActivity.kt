package com.example.myapplication

import android.Manifest
import android.bluetooth.*
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.example.myapplication.levels.GameScreen1
import com.example.myapplication.levels.LevelOne
import java.util.*

class MainActivity : ComponentActivity() {

    private lateinit var bluetoothAdapter: BluetoothAdapter
    private lateinit var bluetoothManager: BluetoothManager
    private var bluetoothGatt: BluetoothGatt? = null
    private var receivedMessage by mutableStateOf("")
    private var currentScreen by mutableStateOf("home")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothAdapter = bluetoothManager.adapter

        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp(
                        modifier = Modifier.padding(innerPadding),
                        receivedMessage = receivedMessage,
                        currentScreen = currentScreen,
                        onChangeScreen = { screen -> currentScreen = screen }
                    )
                }
            }
        }

        // Start Bluetooth scanning when the activity is created
        if (bluetoothAdapter.isEnabled) {
            startBluetoothScan()
        }
    }

    private fun startBluetoothScan() {
        val bluetoothLeScanner: BluetoothLeScanner = bluetoothAdapter.bluetoothLeScanner
        println("connected")
        val scanCallback = object : ScanCallback() {
            override fun onScanResult(callbackType: Int, result: ScanResult?) {
                result?.device?.let { device ->
                    if (ActivityCompat.checkSelfPermission(
                            this@MainActivity,
                            Manifest.permission.BLUETOOTH_CONNECT
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        // Handle permission request
                        return
                    }
                    if (device.name == "NEDC_GLOVE") { // Replace with actual device name
                        bluetoothLeScanner.stopScan(this)
                        device.connectGatt(this@MainActivity, false, gattCallback)
                    }
                }
            }
        }

        bluetoothLeScanner.startScan(scanCallback)
    }

    // GATT Callback for BLE connection
    // GATT Callback for BLE connection
    private val gattCallback = object : BluetoothGattCallback() {
        override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int) {
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                if (ActivityCompat.checkSelfPermission(
                        this@MainActivity,
                        Manifest.permission.BLUETOOTH_CONNECT
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // Handle permission request
                    return
                }
                gatt.discoverServices()
            }
        }

        override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {
            val serviceUUID = UUID.fromString("6E400001-B5A3-F393-E0A9-E50E24DCCA9E") // BLESerial Service UUID
            val characteristicUUID = UUID.fromString("6E400003-B5A3-F393-E0A9-E50E24DCCA9E") // TX Characteristic UUID

            val service = gatt.getService(serviceUUID)
            val characteristic = service?.getCharacteristic(characteristicUUID)

            if (characteristic != null) {
                if (ActivityCompat.checkSelfPermission(
                        this@MainActivity,
                        Manifest.permission.BLUETOOTH_CONNECT
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // Handle permission request
                    return
                }
                gatt.setCharacteristicNotification(characteristic, true)
                val descriptor = characteristic.getDescriptor(UUID.fromString("00002902-0000-1000-8000-00805f9b34fb"))
                descriptor.value = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE
                gatt.writeDescriptor(descriptor)
            }
        }

        override fun onCharacteristicChanged(
            gatt: BluetoothGatt,
            characteristic: BluetoothGattCharacteristic
        ) {
            val receivedData = characteristic.value.toString(Charsets.UTF_8)
            Log.d("BLESerial", "Received: $receivedData")

            // Call the translator method with the received data
            val translatedMessage = GloveTranslator.translator(receivedData)

            // Update the UI with the received data
            receivedMessage = translatedMessage // Update the received message state
            //currentScreen = "receivedMessage" // Navigate to receivedMessage screen
        }
    }

}

@Composable
fun MyApp(
    modifier: Modifier = Modifier,
    receivedMessage: String,
    currentScreen: String,
    onChangeScreen: (String) -> Unit
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("stats") { StatsScreen(navController) }
        composable("settings") { SettingsScreen(navController) }
        composable("game") { GameActivity(navController) }
        composable("translator") { TranslatorScreen(navController) }
        composable("level1") { LevelOne(navController) }
        composable("gameScreen1") { GameScreen1(navController) }
    }
}