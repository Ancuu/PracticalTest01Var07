package ro.pub.cs.systems.eim.practicaltest01var07

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PracticalTest01Var07MainActivity : AppCompatActivity() {

    private lateinit var field1: EditText
    private lateinit var field2: EditText
    private lateinit var field3: EditText
    private lateinit var field4: EditText
    private lateinit var setButton: Button
    private lateinit var startServiceButton: Button
    private lateinit var stopServiceButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_var07_main)

        // Inițializare elemente UI
        field1 = findViewById(R.id.field1)
        field2 = findViewById(R.id.field2)
        field3 = findViewById(R.id.field3)
        field4 = findViewById(R.id.field4)
        setButton = findViewById(R.id.set_button)
        startServiceButton = findViewById(R.id.start_service_button)
        stopServiceButton = findViewById(R.id.stop_service_button)

        // Listener pentru butonul "Set"
        setButton.setOnClickListener {
            try {
                // Verificăm dacă toate câmpurile conțin numere
                val values = listOf(
                    field1.text.toString().toInt(),
                    field2.text.toString().toInt(),
                    field3.text.toString().toInt(),
                    field4.text.toString().toInt()
                )

                // Pornește activitatea secundară și transmite valorile
                val intent = Intent(this, PracticalTest01Var07SecondaryActivity::class.java)
                intent.putExtra("values", values.toIntArray())
                startActivityForResult(intent, 1)
            } catch (e: NumberFormatException) {
                // Dacă cel puțin un câmp nu conține numere, afișăm un mesaj de eroare
                Toast.makeText(this, "Toate câmpurile trebuie să conțină numere!", Toast.LENGTH_SHORT).show()
            }
        }

        // Pornire serviciu la apăsare pe buton
        startServiceButton.setOnClickListener {
            val intent = Intent(this, PracticalTest01Var07Service::class.java)
            startService(intent)
        }

        // Oprire serviciu la apăsare pe buton
        stopServiceButton.setOnClickListener {
            val intent = Intent(this, PracticalTest01Var07Service::class.java)
            stopService(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK) {
            val sum = data?.getIntExtra("sum", -1)
            val product = data?.getIntExtra("product", -1)

            // Verificăm ce rezultat a fost returnat
            if (sum != null && sum != -1) {
                Toast.makeText(this, "Sum: $sum", Toast.LENGTH_SHORT).show()
            }
            if (product != null && product != -1) {
                Toast.makeText(this, "Product: $product", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        // Oprire serviciu când activitatea devine invizibilă
        val intent = Intent(this, PracticalTest01Var07Service::class.java)
        stopService(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Oprire definitivă a serviciului când activitatea este distrusă
        val intent = Intent(this, PracticalTest01Var07Service::class.java)
        stopService(intent)
    }
}
