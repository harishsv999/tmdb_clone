package com.harish.tmdb.presentation.activities

import com.harish.tmdb.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.harish.tmdb.app.Controller
import com.harish.tmdb.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private val dispatcher = CompositeDisposable()
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    lateinit var mainMenu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))

        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.mainContainer.id) as NavHostFragment
        navController = navHostFragment.navController
        setContentView(binding.root)
        setSupportActionBar(binding.mainToolBar)
        navController.addOnDestinationChangedListener(this)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(false)
            setDisplayShowHomeEnabled(false)
            setDisplayUseLogoEnabled(false)
        }
    }

    override fun onBackPressed() {
        lifecycleScope.launch {
            navController.currentBackStack.collect {
                Log.i("SIZE", it.size.toString())
                if (it.isNotEmpty()) {
                    navController.navigateUp()
                } else {
                    Toast.makeText(this@MainActivity, "Back Pressed!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        mainMenu = menu
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.searchAction -> {
                navController.navigate(R.id.action_dashBoardFragment_to_searchFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        dispatcher.clear()
        super.onDestroy()
    }

    override fun onDestinationChanged(
        controller: NavController, destination: NavDestination, arguments: Bundle?
    ) {
        Log.i(
            "DESTINATION",
            "Home: ${destination.id == R.id.dashBoardFragment} Search: ${destination.id == R.id.searchFragment}"
        )
        when (destination.id) {
            R.id.dashBoardFragment -> {

            }

            R.id.searchFragment -> {
                mainMenu.findItem(R.id.searchAction).isVisible = false
            }
        }
    }
}