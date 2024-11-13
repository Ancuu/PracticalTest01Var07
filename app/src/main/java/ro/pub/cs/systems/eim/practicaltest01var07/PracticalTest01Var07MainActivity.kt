package ro.pub.cs.systems.eim.practicaltest01var07

import android.app.Activity
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

    private var sum: Int = 0 // Variabilă pentru sumă
    private var product: Int = 0 // Variabilă pentru produs

    companion object {
        private const val SECOND_ACTIVITY_REQUEST_CODE = 1
        private const val SUM_KEY = "sum"
        private const val PRODUCT_KEY = "product"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_var07_main)

        // Inițializare elemente UI
        field1 = findViewById(R.id.field1)
        field2 = findViewById(R.id.field2)
        field3 = findViewById(R.id.field3)
        field4 = findViewById(R.id.field4)
        setButton = findViewById(R.id.set_button)

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
                startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE)
            } catch (e: NumberFormatException) {
                // Dacă cel puțin un câmp nu conține numere, afișăm un mesaj de eroare
                Toast.makeText(this, "Toate câmpurile trebuie să conțină numere!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            sum = data?.getIntExtra("sum", 0) ?: 0
            product = data?.getIntExtra("product", 0) ?: 0

            // Afișăm rezultatele primite
            Toast.makeText(this, "Sum: $sum, Product: $product", Toast.LENGTH_SHORT).show()
        }
    }

    // Salvăm variabilele în cazul distrugerii activității
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SUM_KEY, sum)
        outState.putInt(PRODUCT_KEY, product)
    }

    // Restaurăm variabilele la recrearea activității
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        sum = savedInstanceState.getInt(SUM_KEY, 0)
        product = savedInstanceState.getInt(PRODUCT_KEY, 0)

        // Afișăm din nou rezultatele salvate
        Toast.makeText(this, "Restored -> Sum: $sum, Product: $product", Toast.LENGTH_SHORT).show()
    }
}
