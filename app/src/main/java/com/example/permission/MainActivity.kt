package com.example.permission

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.permission.utils.PermissionUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        call.setOnClickListener {
            PermissionUtil.permission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_PHONE_STATE
            )
                .deny {
                    Toast.makeText(this@MainActivity, "deny", Toast.LENGTH_SHORT).show()
                }
                .run(Runnable { Toast.makeText(this@MainActivity, "granted", Toast.LENGTH_SHORT).show() })
        }


    }
}
