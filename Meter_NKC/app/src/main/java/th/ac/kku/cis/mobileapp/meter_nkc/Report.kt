package th.ac.kku.cis.mobileapp.meter_nkc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.ListView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import th.ac.kku.cis.mobileapp.meter_nkc.Adapter.Metter_Adapter
import th.ac.kku.cis.mobileapp.meter_nkc.Model.Dormitory

class Report : AppCompatActivity() {

    val TAG = "logcat"
    private lateinit var mRecyclerView : ListView
    private lateinit var  mDatabaseRef : DatabaseReference
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var mFirebaseDatabase: FirebaseDatabase

    lateinit var items : MutableList<Dormitory>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_menu)
        setContentView(R.layout.activity_report)

        bindingData()

    }

    private fun bindingData() {
        mRecyclerView = findViewById(R.id.list_recycler_view)
        items = mutableListOf()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("meternkc")
        mDatabaseRef!!.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(this@Report, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }

            override fun onDataChange(p0: DataSnapshot) {
                Log.i(TAG,"mDatabaseRef $mDatabaseRef")
                if(p0!!.exists()){
                    items.clear()
                    for (e in p0.children){
                        val rec = e.getValue(Dormitory::class.java)
                        items.add(rec!!)
                        Log.i(TAG,"items $items")
                        Log.i(TAG,"p0 $p0")
                    }
                    val adapter = Metter_Adapter(this@Report,R.layout.custom_list_item,items)
                    mRecyclerView.adapter = adapter
                    Log.i(TAG,"SHOW $items")

                }


            }

        })
        mRecyclerView.setOnItemClickListener{ parent, view, position, id ->
            var i = Intent(this,list_item::class.java)
            i.putExtra("id",items[position].id)
            i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(i)
//            Toast.makeText(this,items[position].textDetail, Toast.LENGTH_LONG).show()
//            Log.i(TAG,"show "+items[position].textDetail)

        }

//        btback.setOnClickListener {
//            var i = Intent(this, Student_Activity::class.java)
//            i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
//            startActivity(i)
//        }

    }
}
