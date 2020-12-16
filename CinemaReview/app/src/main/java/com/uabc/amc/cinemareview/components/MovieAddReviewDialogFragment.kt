package com.uabc.amc.cinemareview.components

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.uabc.amc.cinemareview.R
import com.uabc.amc.cinemareview.services.FirestoreFirebase
import kotlinx.android.synthetic.main.fragment_movie_add_review_dialog.*

class MovieAddReviewDialogFragment : DialogFragment(), FirestoreFirebase {
    private lateinit var toolbar: Toolbar

    companion object {
        val TAG = "TAG TEXT"

        fun display(fragmentManager: FragmentManager): MovieAddReviewDialogFragment {
            val dialog = MovieAddReviewDialogFragment()
            dialog.show(fragmentManager, TAG)
            return dialog
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme)
    }

    override fun onStart() {
        super.onStart()

        if(dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog!!.window?.setLayout(width, height)
            dialog!!.window?.setWindowAnimations(R.style.AppTheme_Slide)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(R.layout.fragment_movie_add_review_dialog, container, false)
        toolbar = view.findViewById(R.id.toolbar)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setNavigationOnClickListener { dismiss() }
        updateDataFirebaseFirestore()

        val stars = listOf<ImageButton>(
            star_one,
            star_two,
            star_three,
            star_four,
            star_five
        )

        stars.forEach { star ->
           for(item in stars) resetColorStars(item, R.color.colorPrimary)

            star.setOnClickListener {
                for(item in stars) resetColorStars(item, R.color.colorPrimary)

                for(item in stars) {
                    if(it == item) {
                        resetColorStars(item, R.color.colorAccent)
                        return@setOnClickListener
                    } else resetColorStars(item, R.color.colorAccent)
                }
            }
        }

    }

    private fun resetColorStars(item: ImageView, color: Int) {
        ImageViewCompat.setImageTintList(item, ColorStateList.valueOf(ContextCompat.getColor(requireContext(), color)))
    }

    override fun updateDataFirebaseFirestore() {
        movie_list_review_add_review.setOnClickListener { dismiss() }
    }
}