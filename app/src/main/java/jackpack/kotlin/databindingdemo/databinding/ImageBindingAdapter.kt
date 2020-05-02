package jackpack.kotlin.databindingdemo.databinding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jackpack.kotlin.databindingdemo.R


/**
 * Created by Mg Kaung on 5/2/2020.
 */

object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageUrl(imageView: ImageView, url: String) {

        if (url == null) {
            imageView.setImageResource(R.drawable.logo);
        } else {
            Glide.with(imageView.getContext())
                .setDefaultRequestOptions(
                    RequestOptions()
                        .circleCrop()
                )
                .load(url)
                .placeholder(R.drawable.logo)
                .into(imageView)
        }
    }
}