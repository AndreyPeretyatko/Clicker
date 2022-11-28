package com.fastclicker


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.ContactsContract.CommonDataKinds.Note
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.radefffactory.fastclicker.R


class MainActivity : AppCompatActivity() {

    lateinit var button: Button

    lateinit var tv_time: TextView
    lateinit var tv_clicks: TextView

    lateinit var b_start: Button
    lateinit var b_click: Button

    var currentTime = 10
    var currentClicks = 0

    //Создаёт таймер
    lateinit var timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Инициализирует объекты
        button = findViewById(R.id.button)

        tv_time = findViewById(R.id.tv_time)
        tv_clicks = findViewById(R.id.tv_clicks)

        b_start = findViewById(R.id.b_start)
        b_click = findViewById(R.id.b_click)

        //Убирает активность кнопки во время игры
        b_click.isEnabled = false

        b_start.setOnClickListener {
            //Начать новую игру
            currentTime = 10 // 10 секунды игры
            currentClicks = 0

            tv_time.text = "Time: $currentTime"
            tv_clicks.text = "Clicks: $currentClicks"

            //Меняет статус кнопки
            b_start.isEnabled = false
            b_click.isEnabled = true

            //Запускает таймер
            timer.start()
        }

        b_click.setOnClickListener {
            //Увеличивает клики и отображает их
            currentClicks++;
            tv_clicks.text = "Clicks: $currentClicks"
        }

        //Инициализирует таймер
        timer = object : CountDownTimer(
            10000,
            1000
        ) { //10000 = 10 секунд игры, 1000 = обновляеться каждую секунду
            override fun onTick(millisUntilFinished: Long) {
                //Отображает секунды
                currentTime--
                val time = currentTime + 1 //
                tv_time.text = "Time: $time"
            }

            override fun onFinish() {
                //Заканчивает таймер
                tv_time.text = "Time: 0"

                //Меняет статус кнопки
                b_start.isEnabled = true
                b_click.isEnabled = false

                button.setOnClickListener {
                    val intent = Intent(this@MainActivity, Notepad::class.java)
                    startActivity(intent)
                }
        }
            }
        }

}

