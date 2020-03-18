package com.example.datamahasiswa

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_dashboard.*
import org.json.JSONArray
import org.json.JSONObject

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val context = this

        simpan.setOnClickListener{

            var id_data :String = d_id.text.toString()
            var nama_mahasiswa :String = d_nama.text.toString()
            var nomer_mahasiswa :String = d_nomer.text.toString()
            var alamat_mahasiswa :String = d_alamat.text.toString()

            postkeserver(id_data, nama_mahasiswa, nomer_mahasiswa, alamat_mahasiswa)

            val intent = Intent(context,DashboardActivity::class.java)
            startActivity(intent)

        }



        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager= LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val users=ArrayList<User>()


        AndroidNetworking.get("http://192.168.1.11/mahasiswa/mahasiswa.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("id_data"))

                        // txt1.setText(jsonObject.optString("shubuh"))
                        var isi1=jsonObject.optString("id_data").toString()
                        var isi2=jsonObject.optString("nama_mahasiswa").toString()
                        var isi3=jsonObject.optString("nomer_mahasiswa").toString()
                        var isi4=jsonObject.optString("alamat_mahasiswa").toString()

                        users.add(User("$isi1", "$isi2", "$isi3", "$isi4"))


                    }

                    val adapter=CustomAdapter(users)
                    recyclerView.adapter=adapter


                }

                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })

        button.setOnClickListener{
            val sharedPreferences=getSharedPreferences("CEKLOGIN", Context.MODE_PRIVATE)
            val editor=sharedPreferences.edit()

            editor.putString("STATUS","0")
            editor.apply()

            startActivity(Intent(this@DashboardActivity,MainActivity::class.java))
            finish()
        }
    }

    fun postkeserver(data1:String, data2:String, data3:String, data4:String)
    {
        AndroidNetworking.post("http://192.168.1.11/mahasiswa/create_mahasiswa.php")
            .addBodyParameter("id_data", data1)
            .addBodyParameter("nama_mahasiswa", data2)
            .addBodyParameter("nomer_mahasiswa", data3)
            .addBodyParameter("alamat_mahasiswa", data4)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray) {

                }

                override fun onError(anError: ANError?) {

                }
            })
    }
}
