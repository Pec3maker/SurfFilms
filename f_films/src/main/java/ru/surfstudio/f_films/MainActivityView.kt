package ru.surfstudio.f_films

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import dagger.hilt.android.AndroidEntryPoint
import ru.surfstudio.android.filmssurf.f_films.R
import ru.surfstudio.android.filmssurf.f_films.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        supportFragmentManager.commit {
            add<FilmsFragmentView>(R.id.main_fragment_container)
            addToBackStack(null)
        }
    }
}