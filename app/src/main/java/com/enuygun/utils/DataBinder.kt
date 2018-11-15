// Safe here as method are used by Data binding
@file:Suppress("unused")

package com.enuygun.utils

import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.support.v4.graphics.drawable.RoundedBitmapDrawable
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.webkit.WebView
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.enuygun.base.BaseRecyclerAdapter



@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: BaseRecyclerAdapter<*, *, *>) {
    view.adapter = adapter
}


@BindingAdapter("layoutManager")
fun setLayoutManager(view: RecyclerView, layoutManager: RecyclerView.LayoutManager) {
    view.layoutManager = layoutManager
}

@BindingAdapter("dividerItemDecoration")
fun setDividerItemDecoration(view: RecyclerView, dividerItemDecoration: DividerItemDecoration) {
    view.addItemDecoration(dividerItemDecoration)
}






@BindingAdapter("setToolbarTitle")
fun setToolbarTitle(view: Toolbar, title: String) {
    view.title = title
}


@BindingAdapter("isToolbarBackButtonEnable")
fun isToolbarBackButtonEnable(view: Toolbar, isEnable: Boolean) {

}


@BindingAdapter("app:image_url_circle")
fun loadCircularImage(v: ImageView, imgUrl: String) {
    Glide.with(v.context)
            .load(imgUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(v);
}




@BindingAdapter("app:image_url","app:image_from_video_url",requireAll = false)
fun loadImage(v: ImageView, imgUrl: String?,videoUrl:String?) {

    if (!imgUrl.isNullOrEmpty()){
        Glide.with(v.context).load(imgUrl).into(v)
    }
    else if(!videoUrl.isNullOrEmpty()){
        retriveVideoFrameFromVideo(videoUrl!!,onSuccess = {
            b ->
            v.setImageBitmap(b)

        },onError = {

        })

    }
}

@BindingAdapter("app:image_url_without_cache")
fun loadImageWithoutCache(v: ImageView, imgUrl: String) {
    Glide.with(v.context)
            .load(imgUrl).apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)).into(v)
}



@BindingAdapter("app:toOutputDateFormat")
fun toOutputDateFormat(v:TextView,dateStr:String?){
    if(dateStr!=null){
        val inputStr = dateStr.toOutputDateFormat("yyyy-MM-dd'T'HH:mm:ss","dd MMM yyy - hh:mm")
        v.setText(inputStr)
    }
}



@BindingAdapter("app:svgAssetPath")
fun svgAssetPath(v:WebView,svgAssetPath:String?){
    if(svgAssetPath!=null){
        v.showSvgFile(svgAssetPath)
    }
}


@BindingAdapter("visibleGone")
fun showHide(view: View, show: Boolean?) {
    if(show!=null){
        view.visibility = if (show) View.VISIBLE else View.GONE
    }
}







@BindingAdapter("backgroundResource")
fun setBackgroundResource(view: View, resource: Int) {
    view.setBackgroundResource(resource)
}

@BindingAdapter("focusChangeListener")
fun setFocusChangeListener(editText: EditText, focusChangeListener : View.OnFocusChangeListener) {
    if(focusChangeListener!=null){
        editText.setOnFocusChangeListener(focusChangeListener)
    }

}


@BindingAdapter("app:colorSchemeResources")
fun bindRefreshColor(swipeRefreshLayout: SwipeRefreshLayout,  vararg colors: Int) {
    swipeRefreshLayout.setColorSchemeColors(*colors)
}


