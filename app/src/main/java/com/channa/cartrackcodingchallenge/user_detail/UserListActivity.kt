package com.channa.cartrackcodingchallenge.user_detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.channa.cartrackcodingchallenge.MyApplication
import com.channa.cartrackcodingchallenge.R
import com.channa.cartrackcodingchallenge.data.source.UserRepository
import com.channa.cartrackcodingchallenge.data.source.remote.response.UserResponse
import kotlinx.android.synthetic.main.activity_user_list.*
import kotlinx.android.synthetic.main.user_list.*
import javax.inject.Inject


/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [UserDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class UserListActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "UserListActivity"
    }

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    @Inject
    lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        (application as MyApplication).applicationComponent?.inject(this)

        val userListViewModel = ViewModelProviders.of(this).get(UserListViewModel::class.java)
        userListViewModel.init(userRepository)

        setSupportActionBar(toolbar)
        toolbar.title = title

        if (user_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        userListViewModel.getUsers().observe(this, Observer {
            if (it.error == null) {

                setupRecyclerView(rv_user_list, it.userResponseList!!)

                it.userResponseList?.forEach { userResponse ->
                    Log.d(TAG, "UserListActivity getUsersSuccess: ${userResponse.name}")

                }
            } else
                Log.e(TAG, "UserListActivity getUsersError: ${it.error}")
        })

    }

    private fun setupRecyclerView(recyclerView: RecyclerView, userResponseList: List<UserResponse>) {
        recyclerView.adapter =
                UserRecyclerViewAdapter(
                        this,
                        userResponseList,
                        twoPane
                )
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }
}
