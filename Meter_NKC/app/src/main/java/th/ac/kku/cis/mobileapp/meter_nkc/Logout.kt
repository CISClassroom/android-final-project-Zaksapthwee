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



class Logout : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    var  mAuthListener:FirebaseAuth.AuthStateListener? = null
    private val TAG: String = "logout_Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_logout)

        mAuth = FirebaseAuth.getInstance()

        val user = mAuth!!.currentUser
        showemail.text = user!!.email
        showeuid.text = user.uid

        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val users = firebaseAuth.currentUser
            if (users == null){
                startActivity(Intent(this@Logout,LoginActivity::class.java))
                finish()
            }
        }

        btn_signout.setOnClickListener{
            mAuth!!.signOut()
            Toast.makeText(this,"Signed out", Toast.LENGTH_LONG).show()
            Log.d(TAG,"Emaul was empty!!")
            startActivity(Intent(this@Logout,LoginActivity::class.java))
            finish()
        }
    }
//    override fun onStart() {
//        super.onStart()
//        mAuth!!.addAuthStateListener { mAuthListener }
//    }

//    override fun onStop() {
//        super.onStop()
//        if (mAuthListener !=null) {
//            mAuth!!.removeAuthStateListener { mAuthListener }
//        }
//    }

//    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//        if (keyCode == KeyEvent.KEYCODE_BACK){
//            moveTaskToBack(true)
//        }
//        return super.onKeyDown(keyCode, event)
//    }

}
