package com.alcntml.myapplication.util.async

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.os.AsyncTask
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import com.alcntml.myapplication.util.async.interfaces.AsyncBlurListener
import com.alcntml.myapplication.util.BlurUtil
import java.lang.Exception
import java.lang.ref.WeakReference

public class BlurTask(context: Context, blurView: View, asyncBlurListener: AsyncBlurListener) : AsyncTask<Void, Void, Bitmap>() {

    private val contextReference: WeakReference<Context> = WeakReference<Context>(context);
    private val viewReference: WeakReference<View> = WeakReference<View>(blurView);
    private val listenerReference: WeakReference<AsyncBlurListener> = WeakReference<AsyncBlurListener>(asyncBlurListener);
    private var position: Int = -1

    public fun setPosition(position: Int){
        this.position = position
    }

    public fun getPosition(): Int{
        return position
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun doInBackground(vararg params: Void): Bitmap? {
        try {
            val mContext = contextReference.get()
            val mBlurView = viewReference.get()
            val blurUtil: BlurUtil = BlurUtil()
            val bitmap: Bitmap? = blurUtil.getBitmapFromView(mBlurView!!, Color.parseColor("#000000"))
            if (bitmap != null) {
                return blurUtil.blur(mContext!!, bitmap)
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
        return null
    }

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)
        if (result != null) {
            listenerReference.get()?.onLoad(result)
        }
    }
}