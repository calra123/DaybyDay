package com.calc.daybyday

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt1.setOnClickListener {
            var file = et1.text.toString().replace('/', '-')
            try {

                var osw = OutputStreamWriter(openFileOutput(file, Activity.MODE_PRIVATE))
                osw.write(et2.text.toString())

                osw.flush()
                osw.close()

                Toast.makeText(this, " Saved", Toast.LENGTH_SHORT).show()

                et2.setText(" ")
                et1.setText(" ")


            } catch (e: IOException) {
                Toast.makeText(this, "Not Saved", Toast.LENGTH_SHORT).show()

            }
        }

        bt2.setOnClickListener {

            var file = et1.text.toString().replace('/', '-')
            if (fileList().contains(file)) {

                try {

                    var fin = InputStreamReader(openFileInput(file))
                    var br = BufferedReader(fin)
                    var line = br.readLine()
                    var all = StringBuilder()
                    while (line != null) {
                        all.append(line + '\n')
                        line = br.readLine()
                    }

                    et2.setText(all)

                    br.close()
                    fin.close()

                } catch (e: IOException) {

                    Toast.makeText(this, "Not Data recorded for that day", Toast.LENGTH_SHORT).show()
                    et2.setText(" ")
                }


            }
        }
    }
}
