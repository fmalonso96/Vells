package com.example.vells.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.vells.R
import com.example.vells.data.model.CatType
import com.example.vells.data.model.Communicator
import com.example.vells.databinding.ActivityMainBinding
import com.example.vells.ui.configuration.ConfigurationFragment
import com.example.vells.ui.home.view.HomeFragment
import com.example.vells.ui.products.view.NewProductFragment
import com.example.vells.ui.products.view.ProductsFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, Communicator {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(toolbarMain)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbarMain, R.string.open, R.string.close)
        toggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        setToolbarTitle("Menu Principal")
        changeFragment(HomeFragment())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayout.closeDrawer(GravityCompat.START)
        when (item.itemId) {
            R.id.navHome -> {
                changeFragment(HomeFragment())
                setToolbarTitle(item.title.toString())
            }
            R.id.navProducts -> {
                changeFragment(ProductsFragment())
                setToolbarTitle(item.title.toString())
            }
            R.id.navConfiguration -> {
                changeFragment(ConfigurationFragment())
                setToolbarTitle(item.title.toString())
            }
        }
        return true
    }

    fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    fun changeFragment(frag: Fragment) {
        val fragment = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.fragmentContainer, frag).commit()
    }

    override fun navigateToNewProduct() {
        changeFragment(NewProductFragment())
        setToolbarTitle("Nuevo Producto")
    }

    override fun navigateToProducts() {
        changeFragment(ProductsFragment())
        setToolbarTitle("Productos")
    }
}