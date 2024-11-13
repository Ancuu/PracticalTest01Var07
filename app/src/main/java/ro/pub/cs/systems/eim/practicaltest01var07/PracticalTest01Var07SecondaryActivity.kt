package ro.pub.cs.systems.eim.practicaltest01var07

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PracticalTest01Var07SecondaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_var07_secondary)

        // Preluăm valorile din intenție
        val values = intent.getIntArrayExtra("values") ?: return

        // Setăm valorile în câmpurile TextView
        findViewById<TextView>(R.id.field1_secondary).text = values[0].toString()
        findViewById<TextView>(R.id.field2_secondary).text = values[1].toString()
        findViewById<TextView>(R.id.field3_secondary).text = values[2].toString()
        findViewById<TextView>(R.id.field4_secondary).text = values[3].toString()

        // Listener pentru butonul "Sum"
        findViewById<Button>(R.id.sum_button).setOnClickListener {
            val sum = values.sum()
            val returnIntent = Intent()
            returnIntent.putExtra("sum", sum)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }

        // Listener pentru butonul "Product"
        findViewById<Button>(R.id.product_button).setOnClickListener {
            val product = values.reduce { acc, i -> acc * i }
            val returnIntent = Intent()
            returnIntent.putExtra("product", product)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }
}
