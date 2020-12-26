package com.uabc.amc.cinemareview.components

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.*
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.google.firebase.Timestamp
import com.uabc.amc.cinemareview.R
import com.uabc.amc.cinemareview.pages.MovieDescriptionItem
import com.uabc.amc.cinemareview.services.FirestoreCollection
import com.uabc.amc.cinemareview.services.FirestoreFirebase
import com.uabc.amc.cinemareview.services.SQLiteService
import com.uabc.amc.cinemareview.utils.ToastMessage
import com.uabc.amc.cinemareview.utils.ToastMessage2
import kotlinx.android.synthetic.main.activity_movie_description_item.*
import kotlinx.android.synthetic.main.fragment_movie_add_review_dialog.*
import kotlinx.android.synthetic.main.fragment_movie_add_review_dialog.movie_list_review_add_review
import java.lang.Integer.parseInt

class MovieAddReviewDialogFragment(private val intent: Intent, private val appCompact: MovieDescriptionItem) : DialogFragment(), FirestoreFirebase {
    private lateinit var toolbar: Toolbar
    private var starsCounter = 0
    private var stars = listOf<ImageButton>()

    companion object {
        val TAG = "TAG TEXT"

        fun display(fragmentManager: FragmentManager, intent: Intent, appCompact: MovieDescriptionItem): MovieAddReviewDialogFragment {
            val dialog = MovieAddReviewDialogFragment(intent, appCompact)
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

        stars = listOf<ImageButton>(
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

                starsCounter = 0
                var counterTemp = 0
                for(item in stars) {
                    counterTemp++
                    if(it == item) {
                        starsCounter = counterTemp
                        resetColorStars(item, R.color.colorAccent)
                        return@setOnClickListener
                    } else resetColorStars(item, R.color.colorAccent)
                }
            }
        }

        onLoadDataIntent()
    }

    private fun onLoadDataIntent() {
        val manager = toolbar.parent as ViewManager

        if(intent.getStringExtra("REVIEW_TEXT") != null && intent.getStringExtra("REVIEW_STARS") != null) {

            val commentIntent = intent.getStringExtra("REVIEW_TEXT")
            val starsIntent = parseInt(intent.getStringExtra("REVIEW_STARS") as String)
            var counter = 0

            comment_review.setText(commentIntent)
            stars.forEach {
                if(counter < starsIntent) resetColorStars(it, R.color.colorAccent)
                counter++
            }

            manager.removeView(toolbar)
            toolbarDelete.setOnMenuItemClickListener { menuItem ->
                when(menuItem.itemId) {
                    R.id.delete_movie_review -> {
                        deleteReview()
                        true
                    }
                    else -> false
                }
            }

        } else manager.removeView(toolbarDelete)
    }

    private fun deleteReview() {
        // Value ID's Movie
        val idDocument = intent.getStringExtra("MOVIE_ID_DOCUMENT")!!
        val idMovie = intent.getStringExtra("MOVIE_ID")!!
        val user = SQLiteService.getUser()

        FirestoreCollection("categories").document(idDocument)
            .collection("movies").document(idMovie)
            .collection("reviews").document(user[0]).delete().addOnCompleteListener {
                appCompact.deleteReview()
                appCompact.updateDataFirebaseFirestore()
                dismiss()
            }
    }

    private fun resetColorStars(item: ImageView, color: Int) {
        ImageViewCompat.setImageTintList(item, ColorStateList.valueOf(ContextCompat.getColor(requireContext(), color)))
    }

    override fun updateDataFirebaseFirestore() {
        // Value ID's Movie
        val idDocument = intent.getStringExtra("MOVIE_ID_DOCUMENT")!!
        val idMovie = intent.getStringExtra("MOVIE_ID")!!
        val user = SQLiteService.getUser()

        movie_list_review_add_review.setOnClickListener {
            if(!comment_review.text?.isNotEmpty()!!) {
                context?.let { ctx -> ToastMessage(ToastMessage.Companion.ERROR.REVIEW_EMPTY, ctx) }
                return@setOnClickListener
            }

            val commentReview = hashMapOf(
                "author" to user[1],
                "stars" to starsCounter.toString(),
                "text" to comment_review.text.toString(),
                "date" to Timestamp.now()
            )

            FirestoreCollection("categories").document(idDocument)
                .collection("movies").document(idMovie)
                .collection("reviews").document(user[0])
                .set(commentReview).addOnCompleteListener {
                    updateUserHistory(user[0], idDocument, idMovie, starsCounter.toString())
                    appCompact.updateDataFirebaseFirestore()
                    dismiss()

                }.addOnFailureListener {
                    context?.let { ctx -> ToastMessage(ToastMessage.Companion.ERROR.REVIEW_UNSAVED, ctx) }
                }
        }
    }

    private fun updateUserHistory(uid: String, documentId: String, movieId: String, starsUpdate: String) {

        FirestoreCollection("user").document(uid).collection("history")
            .document(movieId).set(hashMapOf(
                "collection" to documentId,
                "document" to movieId,
                "created" to Timestamp.now(),
                "name" to intent.getStringExtra("MOVIE_NAME"),
                "director" to intent.getStringExtra("MOVIE_DIRECTOR"),
                "stars" to starsUpdate,
                "categories" to intent.getStringExtra("MOVIE_CATEGORIES"),
                "sinopsis" to intent.getStringExtra("MOVIE_SINOPSIS"),
                "duration" to intent.getStringExtra("MOVIE_DURATION"),
                "cover" to intent.getStringExtra("MOVIE_BANNER"),
                "image" to intent.getStringExtra("MOVIE_COVER")
            )).addOnCompleteListener {}
    }
}