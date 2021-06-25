/*
 * Copyright (c) 2020 Razeware LLC
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 * 
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.kotlincoroutinesfundamentals

import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL

/**
 * Main Screen
 */
class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    // Switch to AppTheme for displaying the activity
    setTheme(R.style.AppTheme)

    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)



    // Your code

        Log.d("TaskThread", Thread.currentThread().name)
        GlobalScope.launch(context = Dispatchers.IO) {
          Log.d("TaskThread", Thread.currentThread().name)
          val imageUrl = URL("https://wallpaperplay.com/walls/full/1/c/7/38027.jpg")

          val connection = imageUrl.openConnection() as HttpURLConnection
          connection.doInput = true
          connection.connect()

          val inputStream = connection.inputStream
          val bitmap = BitmapFactory.decodeStream(inputStream)

           launch(Dispatchers.Main) {
             Log.d("TaskThread", Thread.currentThread().name)
             image.setImageBitmap(bitmap)
           }
        }
  }
}
// ------------- First Method to Thread (background processing)
//    Thread(Runnable {
//      val imageUrl = URL("https://wallpaperplay.com/walls/full/1/c/7/38027.jpg")
//
//      val connection = imageUrl.openConnection() as HttpURLConnection
//      connection.doInput = true
//      connection.connect()
//
//      val inputStream = connection.inputStream
//         val bitmap = BitmapFactory.decodeStream(inputStream)
//
//      runOnUiThread{ image.setImageBitmap(bitmap)}
//
//    }).start()
//

