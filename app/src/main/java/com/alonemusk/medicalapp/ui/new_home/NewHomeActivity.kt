package com.alonemusk.medicalapp.ui.new_home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.lifecycle.ViewModelProviders
import com.alonemusk.medicalapp.R
import com.alonemusk.medicalapp.ui.Cart.Cart
import com.alonemusk.medicalapp.ui.Search.SearchMedicine
import com.alonemusk.medicalapp.ui.Search.SearchViewModel
import com.alonemusk.medicalapp.ui.home.HomeFragment
import com.alonemusk.medicalapp.ui.utils.PrefManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class NewHomeActivity : AppCompatActivity() {
    lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        val auth = FirebaseAuth.getInstance()
        val authListener = AuthStateListener { firebaseAuth ->
            val firebaseUser = firebaseAuth.currentUser
            if (firebaseUser != null) {
                PrefManager.putString("uid",firebaseUser.uid)
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.container, HomeFragment()).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actionbar_menu, menu)
        return true
    }

    override fun onOptionsMenuClosed(menu: Menu?) {
        supportFragmentManager.beginTransaction().replace(R.id.container, HomeFragment()).commit()
        super.onOptionsMenuClosed(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.search) {
            val searchView:SearchView=item.actionView as SearchView
            searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    val list=searchViewModel.search(newText)
                    searchViewModel.search(list)
                    return true
                }

            })
            MenuItemCompat.setOnActionExpandListener(item, object : MenuItemCompat.OnActionExpandListener {
                override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                    supportFragmentManager.beginTransaction().replace(R.id.container, SearchableFragment()).commit()
                    searchMedicines()
                    return true
                }

                override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                    supportFragmentManager.beginTransaction().replace(R.id.container, HomeFragment()).commit()
                    return true
                }

            })
        }
        if(item.itemId==R.id.cart){
            startActivity(Intent(this, Cart::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    private fun searchMedicines() {

       FirebaseDatabase.getInstance().getReference("medicines").addListenerForSingleValueEvent(object: ValueEventListener{
           override fun onCancelled(p0: DatabaseError) {
           }

           override fun onDataChange(p0: DataSnapshot) {
                searchViewModel.deleteAll()

                for(searMedicine in p0.children){
                    val medicine=searMedicine.getValue(SearchMedicine::class.java)
                    searchViewModel.Insert(medicine)
                }
           }

       })
    }

}
