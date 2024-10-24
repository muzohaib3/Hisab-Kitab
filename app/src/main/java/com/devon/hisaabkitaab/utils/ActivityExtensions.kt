package com.devon.hisaabkitaab.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Base64.DEFAULT
import android.util.Base64.decode
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

fun <T : AppCompatActivity> AppCompatActivity.gotoActivity(targetActivityClass: Class<T>) {
    val intent = Intent(this, targetActivityClass)
    startActivity(intent)
}

fun AppCompatActivity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun hideActionBar(){

}


fun View.click(it: (View) -> Unit) {
    this.setOnClickListener(it)
}

fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>) =
    ViewModelProvider.NewInstanceFactory().create(viewModelClass)


fun <T : AppCompatActivity> Fragment.gotoActivityFromFragment(targetActivityClass: Class<T>) {
    val intent = Intent(requireActivity(), targetActivityClass)
    startActivity(intent)
}


fun AppCompatActivity.setTransparentStatusBarColor(color: Int) {
    val window: Window = window
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window.setDecorFitsSystemWindows(false)
    } else {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = ContextCompat.getColor(this, color)
}



fun View.makeVisible() { this.visibility = View.VISIBLE }
fun View.makeInvisible() { this.visibility = View.INVISIBLE }
fun View.makeGone() { this.visibility = View.GONE }
fun View.enable() { isEnabled = true }
fun View.disable() { isEnabled = false }
fun View.isVisible() : Boolean = this.visibility == View.VISIBLE
fun EditText.clear() { text.clear() }
fun EditText.moveCursorToEnd() = setSelection(text.length)
fun EditText.moveCursorToStart() = setSelection(0)
fun TextView.clear() { text = "" }

fun View.onClick(onClick: (View) -> Unit) = setOnClickListener {
    onClick(it)
}

fun View.onLongClick(onLongClick: (View) -> Unit) = setOnLongClickListener {
    onLongClick(it)
    false
}
//fun log(vararg values : String)
//{
//    val charsToReplace = mutableMapOf(
//        "[" to "[\n",
//        "]" to "]\n",
//        "{" to "{\n",
//        "}" to "}\n",
//        "\"," to "\",\n",
//        "(" to "( ",
//        ")" to " )",
//        "\"}" to "\"\n}",
//        ",{" to ",\n{"
//    )
//    if (values.isNotEmpty()) for (i in values.indices) println(
//        values[i].replaceChars(
//            charsToReplace
//        )
//    )
//}
//fun Activity.makeMeTransparent() {
//    val uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN
//    window.decorView.systemUiVisibility = uiOptions
//
//    requestWindowFeature(Window.FEATURE_NO_TITLE)
//
//    tryCatch({
//        window.decorView.systemUiVisibility =
//            View.SYSTEM_UI_FLAG_IMMERSIVE or
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
//                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
//                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
//                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
//                    View.SYSTEM_UI_FLAG_FULLSCREEN
//    }, {
//        toast("Unable to make activity Full Screen.")
//    })
//}
fun Activity.makeFullScreen()
{
    window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

    window.decorView.systemUiVisibility =
        View.SYSTEM_UI_FLAG_IMMERSIVE or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
}
//@SuppressLint("SimpleDateFormat")
//fun convertToMillis(dateTime: String) : Long
//{
//    return try
//    {
//        log("Date: $dateTime")
//        val toParse : String = dateTime // Results in 9/12/2022 8:01:00 AM
//
//        val formatter = SimpleDateFormat("MM/dd/yyyy h:mm:ss a") // I assume d-M, you may refer to M-d for month-day instead.
//
//        val date : Date = formatter.parse(toParse) // You will need try/catch around this
//
//        date.time
//    }
//    catch (e: Exception)
//    {
//        log("Exception e: $e")
//        0L
//    }
//}


val charsToReplace = mutableMapOf(
    "\\b" to "",
    "\\n" to "",
    "\\r" to "",
    "\\t" to "",
    "\\" to "",
    "\\\\" to ""
)

fun String.replaceChars(values: Map<String, String>): String
{
    var text = this

    values.forEach { (t, u) ->
        text = text.replace(t, u)
    }

    return text
}



fun returnJson(data : String) : String = "{${
    data
        .substringAfter("[{")
        .substringBefore("}]")
        .substringAfter("{")
        .substringBefore("}")
}}".replaceChars(charsToReplace)

fun returnArrayJson(data : String) : String = "[{${
    data.substringAfter("[{").substringBefore("}]")
}}]".replaceChars(charsToReplace)



fun checkFailedCondition(it : String) : Boolean =
    it.contains("Failed:") || it.contains("Exception:") || it.contains("Not Success:")



fun Activity.toast(message:String)
{
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun getBmpFromBase64(encodedImage : String) : Bitmap?
{
    return try
    {
        val decodedString = decode(encodedImage, DEFAULT)

        BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    }
    catch (e : Exception)
    {
        null
    }
}

fun Activity.makeMeTransparent()
{
    val uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN
    window.decorView.systemUiVisibility = uiOptions
}

fun tryCatch(tryBlock : () -> Unit,
             catchBlock : (t : Throwable) -> Unit,
    //catchBlock : ((t : Throwable) -> Unit)? = null,
             finalBlock : (() -> Unit)? = null
)
{
    try
    {
        tryBlock()
    }
    catch (e : Exception)
    {
        catchBlock(e)
        //catchBlock?.invoke(e)
    }
    finally
    {
        //finalBlock?.invoke()
    }
}



fun View.makeScrollableInsideScrollView()
{
    setOnTouchListener { v, event ->
        v.parent.requestDisallowInterceptTouchEvent(true)
        when (event.action and MotionEvent.ACTION_MASK)
        {
            MotionEvent.ACTION_UP ->
            {
                v.parent.requestDisallowInterceptTouchEvent(false)
                //It is required to call performClick() in onTouch event.
                performClick()
            }
        }
        false
    }
}

fun String.checkEmptyReturnNA() : String = if (this != null && this.isEmpty() || this == "-") "N/A" else this
fun String.checkEmptyReturnValue() : String = if (this != null && this.isEmpty() || this == " ") "0" else this
fun String.checkEmptyReturnBlank() : String = if (this != null && this.isEmpty() || this == "-" || this == "N/A") "" else this
fun String.fixResponse() : String = this.replace("\\", "", true)



fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

val Context.layoutInflater : LayoutInflater
    get() = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater