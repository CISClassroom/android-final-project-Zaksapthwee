package th.ac.kku.cis.mobileapp.meter_nkc


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_menu.*
import org.threeten.bp.Instant
import th.ac.kku.cis.mobileapp.meter_nkc.Model.Dormitory


class detail : AppCompatActivity() {
    private var TAG = "testcast"
    lateinit var mDatabaseRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_detail)
        mDatabaseRef = FirebaseDatabase.getInstance().reference

        val bundle = intent

        var get_namedorm1 = bundle?.getStringExtra("drom_m1")

        Log.i(TAG,"show $bundle")

        var setname = findViewById<TextView>(R.id.namedorm)

//        setname.text = bundle.toString()

        btn_save.setOnClickListener{
            showDialog()
        }
    }

    private fun showDialog() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("ยืนยันการบันทึก")
        alertDialog.setMessage("คุณต้องการบันทึกข้อมูลหรือไม่?")
        alertDialog.setNegativeButton("ยกเลิก") { dialog, which ->
            dialog.dismiss()
        }
        alertDialog.setPositiveButton("ยืนยัน") { dialog, which ->
            val now = Instant.now()
            val dialog : Dormitory = Dormitory.create()
            val obj = mDatabaseRef.child("meternkc").push()
            Log.i(TAG,"mDatabaseRef$mDatabaseRef")
//            dialog.Dorm = .text.toString()
            dialog.floor = edit_floor.text.toString()
            dialog.RoomNum = edit_RoomNum.text.toString()
            dialog.Meter = edit_Meter.text.toString()

            dialog.id = obj.key
            obj.setValue(dialog)
            Toast.makeText(applicationContext, "บันทึกข้อมูลเรียบร้อยแล้ว", Toast.LENGTH_SHORT).show()
            Log.i(TAG,"dialog$dialog")

                var i = Intent(this, detail::class.java)
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(i)

        }
        val dialog: AlertDialog = alertDialog.create()
        dialog.show()

    }



}

