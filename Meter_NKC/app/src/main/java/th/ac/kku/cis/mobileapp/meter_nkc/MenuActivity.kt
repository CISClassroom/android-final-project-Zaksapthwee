package th.ac.kku.cis.mobileapp.meter_nkc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_logout.*
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.activity_menu.btn_signoutt


class MenuActivity : AppCompatActivity() {
    var mAuth: FirebaseAuth? = null
    var  mAuthListener:FirebaseAuth.AuthStateListener? = null
    private val TAG: String = "testcast"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_menu)

        mAuth = FirebaseAuth.getInstance()


        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val users = firebaseAuth.currentUser
            if (users == null){
                startActivity(Intent(this@MenuActivity,LoginActivity::class.java))
                finish()
            }
        }

        btn_signoutt.setOnClickListener{
            mAuth!!.signOut()
            Toast.makeText(this,"Signed out", Toast.LENGTH_LONG).show()
            Log.d(TAG,"Emaul was empty!!")
            startActivity(Intent(this@MenuActivity,LoginActivity::class.java))
            finish()
        }
        Menu()



    }

    private fun Menu() {
        val DormitoryName1 = dorm1.getText().toString()
        val DormitoryName2 = dorm2.getText().toString()
        val DormitoryName3 = dorm3.getText().toString()
        Log.i(TAG,DormitoryName1)
        drom_m1.setOnClickListener{
            var i = Intent(this@MenuActivity,detail::class.java)
            i.putExtra("dorm",DormitoryName1)
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(i)
        }
        drom_m2.setOnClickListener{
            var i = Intent(this@MenuActivity,detail::class.java)
            i.putExtra("dorm",DormitoryName2)
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(i)
        }
        drom_m3.setOnClickListener{
            var i = Intent(this@MenuActivity,detail::class.java)
            i.putExtra("dorm",DormitoryName3)
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(i)
        }
        drom_m4.setOnClickListener{
            var i = Intent(this@MenuActivity,Report::class.java)
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(i)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            moveTaskToBack(true)
        }
        return super.onKeyDown(keyCode, event)
    }



}
