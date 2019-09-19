package com.shaz.talbook.ui.main

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.shaz.talbook.R
import com.shaz.talbook.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*


/**
 * Created by Shahbaz Akhtar on 15-09-2019.
 * @author Shahbaz Akhtar
 */
class MainActivity : BaseActivity() {

    override fun bindLayout(): Int {
        return R.layout.activity_main
    }

    override fun unbindExtras(bundle: Bundle) {

    }

    override fun initLayouts() {
        setUpNavigation()
    }

    override fun bindViewModel() {

    }

    override fun subscribeObservers() {

    }

    private fun setUpNavigation() {
        /*val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        NavigationUI.setupWithNavController(bottom_navigation, navHostFragment!!.navController)*/

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        bottom_navigation.setupWithNavController(navController)
        //NavigationUI.setupActionBarWithNavController(this, navController)

        /*val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        // get fragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)!!
        // setup custom navigator
        val navigator =
            CustomNavigator(this, navHostFragment.childFragmentManager, R.id.nav_host_fragment)
        navController.navigatorProvider += navigator
        // set navigation graph
        navController.setGraph(R.navigation.nav_graph)
        bottom_navigation.setupWithNavController(navController)*/
    }
}