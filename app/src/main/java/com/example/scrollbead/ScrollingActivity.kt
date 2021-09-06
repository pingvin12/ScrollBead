package com.example.scrollbead

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlinx.android.synthetic.main.content_scrolling.*
import kotlin.system.exitProcess


public class Tippelo
{
    public var list = mutableListOf<Int>()
    private var points: Int = 0

    init {
        genList()
    }

    public fun genList()
    {
        if(list.isEmpty()) {
            for (i in 0..10) {
                list.add((0..10).random())
            }
        }
    }

    public fun doTipp(curTipp: Int)
    {
        if(list.contains(curTipp) && curTipp != 0)
        {
            points += 3
            list.remove(curTipp)
        }
        else if(!list.contains(curTipp) && curTipp != 0)
        {
            points-=3
        }
        else if(list.contains(curTipp) && curTipp == 0)
        {
            points+=1
        }

    }



    public fun getCountOfElementsInList(): Int {
        return list.count()
    }

    public fun getPontok(): Int {
        return points
    }

}

class ScrollingActivity : AppCompatActivity() {

    var tippelo = Tippelo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title
        curnumberid.minValue = 0
        curnumberid.maxValue = 10


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    fun Exit()
    {
        finish()
        exitProcess(0)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if(item.itemId == R.id.action_exit){
            Exit()
        }
        if(item.itemId == R.id.action_about)
        {
            val intent = Intent(this, about::class.java)
            startActivity(intent)

        }

        if(item.itemId == R.id.action_settings)
        {
            val intent = Intent(this, settingsActivity::class.java)
            startActivity(intent)
            
        }


      return true
    }

    fun doTipp(view: View) {
        val curTipp = findViewById<NumberPicker>(R.id.curnumberid).value
        var Pontok: TextView = findViewById<TextView>(R.id.pointtxtid)
        var CountList : TextView = findViewById(R.id.countofelements)

        tippelo.doTipp(curTipp)
        Pontok.text = "${R.string.point_txt} ${tippelo.getPontok()}"
        CountList.text = "$${R.string.listcount} ${tippelo.getCountOfElementsInList()}"

        if(tippelo.getCountOfElementsInList() == 0)
        {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("${R.string.youwin}")
                .setCancelable(false)
                .setPositiveButton(
                    "OK"
                ) { dialog, id ->

                }
            val alert = builder.create()
            alert.show()
        }

    }

    fun doSurrender(view: View) {

        val builder = AlertDialog.Builder(this)
        builder.setMessage("${R.string.gaveupmsg} ${tippelo.list}")
            .setCancelable(false)
            .setTitle("${R.string.gaveuptitle}")
            .setPositiveButton(
                "OK"
            ) { dialog, id ->
        

            }
        val alert = builder.create()
        alert.show()


    }
}